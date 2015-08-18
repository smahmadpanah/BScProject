/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Mohammad
 */
public class PINIRewriter {

    private MyLinkedList pdg; // pdg = G
    private String sourceCode, CopyOfSourceCode, rewritedSourceCode; // sourceCode = M | CopyOfSourceCode = M' | rewritedSourceCode = result
    private HashSet<LinkedList<Node>> F; // the set of paths

    private HashMap<LinkedList<Node>, Boolean> typeOfPaths; // each path is explicit (TRUE) or implicit (FALSE)
    private HashMap<LinkedList<Node>, ArrayList<String>> executionConditions; // execution conditions for nodes N satisfying... 
    private Node sourceNode, destinationNode;

    public PINIRewriter(MyLinkedList pdg) {

        this.pdg = pdg;
        sourceCode = pdg.getFirst().getNodeIdAndStmt();

        F = new HashSet<>(); // paths
        typeOfPaths = new HashMap<>();
        executionConditions = new HashMap<>();

        initializeF(); //initializing paths

        rewritedSourceCode = reWrite(); //algorithm method

    }

    private void initializeF() {
        ArrayList<Node> lowOutputs = new ArrayList<>();

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
                for (int i = 0; i < path.size() - 2; i++) { //Start ~~> X -data-> N ~~>P'
                    Node X = path.get(i);
                    Node N = path.get(i + 1);
                    if (X.getDataDepsForThisNode().contains(N)) {
                        executionConditionsForThisPath.add(findExecutionCondition(N));
                    }
                }
                executionConditions.put(path, executionConditionsForThisPath);
                System.out.println(executionConditionsForThisPath);
            }

            return CopyOfSourceCode;
        }
    }

    private void breadthFirst(LinkedList<Node> visited) {

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
            }
            if (parent.getNextPointer1() != null) {

                Node temp = parent.getNextPointer1(); //agar sharte bargharar Bood
                HashSet<Node> worklist = new HashSet<>();
                worklist.add(temp);
                while (!found && !temp.getStatement().equals("STOP") && !worklist.isEmpty()) {
                    temp = worklist.iterator().next();
                    temp.isVisited = true;
                    if (temp.getNodeID() == node.getNodeID()) {
                        found = true;
                    }
                    else {
                        worklist.remove(temp);
                        for (Node l : temp.succ()) {
                            if (!l.isVisited) {
                                worklist.add(l);
                            }
                        }
                    }
                }
                if (found) {
                    executionCondition += " (" + parent.getStatement() + " : TRUE) &&";
                }
            }
            if (!found && parent.getNextPointer2() != null) {

                Node temp = parent.getNextPointer2(); //agar sharte bargharar Nabood
                HashSet<Node> worklist = new HashSet<>();
                worklist.add(temp);
                while (!found && !temp.getStatement().equals("STOP") && !worklist.isEmpty()) {
                    temp = worklist.iterator().next();
                    temp.isVisited = true;
                    if (temp.getNodeID() == node.getNodeID()) {
                        found = true;
                    }
                    else {
                        worklist.remove(temp);
                        for (Node l : temp.succ()) {
                            if (!l.isVisited) {
                                worklist.add(l);
                            }
                        }
                    }
                }
                if (found) {
                    executionCondition += " (" + parent.getStatement() + " : FALSE) &&";
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

        if (executionCondition.endsWith("&&TRUE")) {
            executionCondition = executionCondition.replace("&&TRUE", "");
        }

        return executionCondition;
    }
}
