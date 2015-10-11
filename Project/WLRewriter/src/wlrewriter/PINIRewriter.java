/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mohammad
 */
public class PINIRewriter {

    private MyLinkedList pdg; // pdg = G
    private String sourceCode, CopyOfSourceCode, rewritedSourceCode, sourceCodeForPSNI; // sourceCode = M | CopyOfSourceCode = M' | rewritedSourceCode = result
    private HashSet<LinkedList<Node>> F; // the set of paths

    private HashMap<LinkedList<Node>, Boolean> typeOfPaths; // each path is explicit (TRUE) or implicit (FALSE)
    private HashMap<LinkedList<Node>, ArrayList<String>> executionConditions; // execution conditions for nodes N satisfying... 
    private HashMap<LinkedList<Node>, String> pathConditions; // conjunction of executionConditions for each path
    private Node sourceNode, destinationNode;

    private ArrayList<Node> lowOutputs;

    public String fileName;

    public PINIRewriter(MyLinkedList pdg) {

        this.pdg = pdg;
        sourceCode = pdg.getFirst().getNodeIdAndStmt();
//        System.out.println(sourceCode);

        F = new HashSet<>(); // paths
        typeOfPaths = new HashMap<>();
        executionConditions = new HashMap<>();
        pathConditions = new HashMap<>();

        initializeF(); //initializing paths

        rewritedSourceCode = reWrite(); //algorithm method
        sourceCodeForPSNI = rewritedSourceCode;
        //omit Node IDs 
        String pattern = "#[0-9]+:";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(rewritedSourceCode);
        rewritedSourceCode = m.replaceAll("");

        try {
            fileName = YYParser.getSourceCodeFileName().replace(".wl", "");
            PrintStream writer = new PrintStream(new File(fileName + "-PINI.wl"));
            writer.print(rewritedSourceCode);
            writer.close();
            System.out.println("$$$PINI REWRITER --> Check out: " + fileName + "-PINI.wl");
            GUI.terminal.append("PINI REWRITER is completed --> Check out: " + fileName + "-PINI.wl");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found!");
            GUI.terminal.appendError("File Not Found!");
        }
    }

    private void initializeF() {
        lowOutputs = new ArrayList<>();

        for (Node node : pdg.getNodeSet()) {
            node.isVisited = false;
            if (node.getStatement().startsWith("outL ")) {
                lowOutputs.add(node);
            }
        }

        if (!pdg.getFirst().getContolDep().isEmpty()) {
            ArrayList<Node> highInputs = new ArrayList<>();

            for (Node n : pdg.getFirst().getContolDep()) {

                for (Node temp : pdg.getNodeSet()) {
                    if (temp.getNodeID() == n.getNodeID()) {
                        n.setAssignedVariable(temp.getAssignedVariable());
                        n.setVariablesOfNode(temp.getVariablesOfNode());
                        n.setContolDep(temp.getContolDep());
                        n.setDataDepsForThisNode(temp.getDataDepsForThisNode());
                        break;
                    }
                }

                if (n.getAssignedVariable() != null) {
                    if (n.getAssignedVariable().type.equals("high") && n.getVariablesOfNode().isEmpty()) { //the node is high input
                        highInputs.add(n);
                    }
                }
            }

            for (Node q : highInputs) {
                sourceNode = q;

                for (Node w : lowOutputs) {
                    destinationNode = w;

                    LinkedList<Node> visited = new LinkedList<>();
                    visited.add(sourceNode);
                    breadthFirst(visited);

                    /*                  // find explicit flows
                     LinkedList<Node> visitedExplicit = new LinkedList<>();
                     visitedExplicit.add(sourceNode);
                     explicitFlows(visitedExplicit);
                     */
                }
            }

            /*          System.out.println("Explicit Flows:");
             for (LinkedList<Node> path : explicitPaths) {
             path.add(0, pdg.getFirst());
             printPath(path);
             }
             */
            for (LinkedList<Node> path : F) {
                boolean isExplicitFlow = true;
                for (int i = 0; i < path.size() - 1; i++) {
                    Node n = path.get(i);
                    Node q = path.get(i + 1);

                    if (n.getContolDep().contains(q)) { // ba node ba'di ya control dep dare ya data dep, nemishe joftesh bashe
                        isExplicitFlow = false;
                        break;
                    }
                }
                path.add(0, pdg.getFirst());

                typeOfPaths.put(path, isExplicitFlow);

                printPath(path);
            }
        }

    }

    private String reWrite() {
        if (F.isEmpty()) { // if there is no path, finished!
            return sourceCode;
        }
        else { // if there are paths, so...
            CopyOfSourceCode = new String(sourceCode); //create a copy of M, name it M'

            for (LinkedList<Node> path : F) {
                ArrayList<String> executionConditionsForThisPath = new ArrayList<>();
                for (int i = 0; i < path.size() - 2; i++) { //Start ~~> X -data-> N ~~>P'  (-2 ro -1 kardam)
                    Node X = path.get(i);
                    Node N = path.get(i + 1);
                    if (X.getDataDepsForThisNode().contains(N)) {
                        String strCondition = findExecutionCondition(N);
//                        String strConditionX = findExecutionCondition(X);
                        if (!executionConditionsForThisPath.contains(strCondition)) { //baraye shart tekrari and nashavad
                            executionConditionsForThisPath.add(strCondition);
                        }
//                        if (!executionConditionsForThisPath.contains(strConditionX)) { //baraye shart tekrari and nashavad
//                            executionConditionsForThisPath.add(strConditionX);
//                        }
                    }
                }
//                executionConditions.put(path, executionConditionsForThisPath);
//                System.out.println("old" + executionConditionsForThisPath);

                // omit "TRUE"s that are extra
                for (int j = 0; j < executionConditionsForThisPath.size(); j++) {
                    if (executionConditionsForThisPath.get(j).equals("TRUE")) {
                        executionConditionsForThisPath.remove(executionConditionsForThisPath.get(j));
                    }
                }

                if (executionConditionsForThisPath.isEmpty()) {
                    executionConditionsForThisPath.add("TRUE");
                }

                executionConditions.put(path, executionConditionsForThisPath);
//                System.out.println(executionConditionsForThisPath);

                String pathCond = "";
                for (int j = 0; j < executionConditionsForThisPath.size(); j++) {
                    String str = executionConditionsForThisPath.get(j);
//                    if (str.contains(" : TRUE) ")) {
//                        str = str.replace(" : TRUE) ", ") ");
//                    }
//                    if (str.contains(": FALSE) ")) {
//                        str = str.replace(" : FALSE) ", ") ");
//                        str = str.replace(" (", " !(");
//                    }
                    pathCond += str;
                    if (j < executionConditionsForThisPath.size() - 1) {
                        pathCond += " and ";
                    }
//               
                }
                pathConditions.put(path, pathCond);
//                System.out.println(pathCond);

            }

            // felan bar asase NODE ID hast, na NODE STATEMENT
            for (Node n : lowOutputs) {
                int currentNodeId = n.getNodeID();

                boolean isExistedAPathForThisNode = false;
                for (LinkedList<Node> tempPath : pathConditions.keySet()) {
                    if (tempPath.getLast().getNodeID() == currentNodeId) {
                        isExistedAPathForThisNode = true;
                        break;
                    }
                }

                if (isExistedAPathForThisNode) { //for example : basic.wl test case

                    boolean isAllExplicit = true;
                    boolean flag = false; //flag for OR TRUE that means TRUE!
                    String c = ""; //the disjunction of path conditions for this OUTL command
                    for (LinkedList<Node> currentPath : pathConditions.keySet()) {
                        if (currentPath.getLast().getNodeID() == currentNodeId) {
                            String condTemp = pathConditions.get(currentPath);

                            if (!typeOfPaths.get(currentPath)) { //is not explicit
                                isAllExplicit = false;
                            }

                            if (condTemp.equals("TRUE")) { // agar dar condition ha, TRUE bashad; or kardan nadare dige
                                c = "TRUE";
                                flag = true;
                            }
                            else {
                                if (!flag) {
                                    c += "(" + condTemp + ") or ";
                                }
                            }

                        }
                    }
                    c += "END";
                    c = c.replace(") or END", ") ");
                    c = c.replace("TRUEEND", "TRUE");

//                    System.out.println("#" + n.getNodeID() + " " + n.getStatement() + " :\n\t" + c);
//                    System.out.println(n.getNodeIdAndStmt());
                    if (isAllExplicit) {
                        // replace OutL l with the statement "if c then OutL BOT else OutL l endif"
                        CopyOfSourceCode = CopyOfSourceCode.replace(n.getNodeIdAndStmt() + ";", "if " + c + " then \n\t outL BOT \n else \n\t outL " + n.getVariablesOfNode().iterator().next().name + " \nendif;\n");
                        CopyOfSourceCode = CopyOfSourceCode.replace(n.getNodeIdAndStmt(), "if " + c + " then \n\t outL BOT \n else \n\t outL " + n.getVariablesOfNode().iterator().next().name + " \nendif\n");
                    }
                    else {
                        // replace OutL l with the statement "if c then NOP else OutL l endif"
                        CopyOfSourceCode = CopyOfSourceCode.replace(n.getNodeIdAndStmt() + ";", "if " + c + " then \n\t NOP \n else \n\t outL " + n.getVariablesOfNode().iterator().next().name + " \nendif;\n");
                        CopyOfSourceCode = CopyOfSourceCode.replace(n.getNodeIdAndStmt(), "if " + c + " then \n\t NOP \n else \n\t outL " + n.getVariablesOfNode().iterator().next().name + " \nendif\n");

                    }

                }
            }

//            System.out.println("\nSOURCE CODE:\n");
//            System.out.println(CopyOfSourceCode);
            return CopyOfSourceCode;
        }
    }

    public void breadthFirst(LinkedList<Node> visited) {

        HashSet<Node> allDeps = new HashSet<>(visited.getLast().getContolDep());
        allDeps.addAll(new HashSet<>(visited.getLast().getDataDepsForThisNode()));

        LinkedList<Node> nodes = new LinkedList<>();

        for (Node n : allDeps) {
            nodes.add(n);
        }

        for (Node node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(destinationNode)) {
                visited.add(node);
//                printPath(visited);
                F.add(new LinkedList<>(visited));
                visited.removeLast();
                break;
            }
        }
        for (Node node : nodes) {
            if (visited.contains(node) || node.equals(destinationNode)) {
                continue;
            }

            visited.addLast(node);
            breadthFirst(visited);

            visited.removeLast();
        }

    }

    private void printPath(LinkedList<Node> path) {
        if (typeOfPaths.get(path)) {
            System.out.print("*EXPLICIT ==> ");
        }

        for (Node node : path) {
            System.out.print("#" + node.getNodeID() + " " + node.getStatement());
            if (path.indexOf(node) != path.size() - 1) {
                System.out.print("  ->  ");
            }
        }
        System.out.println();

    }

    private String findExecutionCondition(Node node) {
        Node parent = null;
        for (Node alpha : node.getParentOfControlDep()) {
            if (alpha.getNodeID() != node.getNodeID()) {
                parent = alpha;
            }
        }
        String executionCondition = "";

        while (!parent.getStatement().equals("START")) {
            boolean found = false;
            for (Node n : pdg.getNodeSet()) {
                n.isVisited = false;
                n.isComeFromTrue = null;
            }

            MyQueue queue = new MyQueue();
            if (parent.getNextPointer1() != null) {
                Node temp = parent.getNextPointer1(); //agar sharte bargharar Bood
                temp.isComeFromTrue = true;
                queue.add(temp);

            }
            if (parent.getNextPointer2() != null) {

                Node temp = parent.getNextPointer2(); //agar sharte bargharar Nabood
                temp.isComeFromTrue = false;
                queue.add(temp);
            }

            while (!queue.isEmpty() && !found) {
                Node nodeTemp = queue.peek();
                nodeTemp.isVisited = true;
                if (nodeTemp.getNodeID() == node.getNodeID()) {
                    found = true;
                    if (nodeTemp.isComeFromTrue) {
                        executionCondition += " (" + parent.getStatement() + ") and";
                    }
                    else {
                        executionCondition += " !(" + parent.getStatement() + ") and";
                    }
                }
                else {
                    for (Node r : nodeTemp.succ()) {
                        if (!r.isVisited) {
                            if (nodeTemp.isComeFromTrue) {
                                r.isComeFromTrue = true;
                            }
                            else {
                                r.isComeFromTrue = false;
                            }
                            queue.add(r);
                        }
                    }
                }
            }

            Node maybeParent = null;
            for (Node beta : parent.getParentOfControlDep()) {
                if (beta.getNodeID() != parent.getNodeID()) {
                    maybeParent = beta;
                }
            }
            parent = maybeParent;
        }
        executionCondition += "TRUE";

        if (executionCondition.endsWith("andTRUE")) {
            executionCondition = executionCondition.replace("andTRUE", "");
        }

        return executionCondition;
    }

    public String getRewritedSourceCode() {
        return sourceCodeForPSNI;
    }

    public String getRewritedSourceCodeOutputFile() {
        return rewritedSourceCode;
    }

    public MyLinkedList getPdg() {
        return pdg;
    }

    public String getFileName() {
        return fileName;
    }

}
