/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mohammad
 */
public class PSNIRewriter {

    private MyLinkedList pdg; // pdg = G
    private HashSet<LinkedList<Node>> D; // the set of paths
    private String sourceCode; //source code : M' that output of PINIRewriter
    private ArrayList<Node> loopGuardNodes;
    private Node sourceNode, destinationNode;

    public PSNIRewriter(PINIRewriter pini) {
        pdg = pini.getPdg();
        sourceCode = pini.getRewritedSourceCode();

        D = new HashSet<>(); // paths

        initializeD(); //initializing paths

        reWrite();

    }

    private void initializeD() {
        loopGuardNodes = new ArrayList<>();

        for (Node node : pdg.getNodeSet()) {
            node.isVisited = false;
            if (node.getContolDep().contains(node)) { // if node has a loop on itself, so it is a loop guard node
                loopGuardNodes.add(node);
            }
        }

        if (!pdg.getFirst().getContolDep().isEmpty()) {
            ArrayList<Node> highInputs = new ArrayList<>();

            for (Node n : pdg.getFirst().getContolDep()) {
                if (n.getAssignedVariable() != null) {
                    if (n.getAssignedVariable().type.equals("high") && n.getVariablesOfNode().isEmpty()) { //the node is high input
                        highInputs.add(n);
                    }
                }

            }

            for (Node q : highInputs) {
                sourceNode = q;

                for (Node w : loopGuardNodes) {
                    destinationNode = w;

                    LinkedList<Node> visited = new LinkedList<>();
                    visited.add(sourceNode);
                    breadthFirst(visited);

                }
            }

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
                D.add(new LinkedList<>(visited));
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

    private void reWrite() {
        if (D.isEmpty()) { // if there is no path, finished!
            return;
        }

        int H = 0; // H = max{height(n) | n is a node on the PDG}
        for (Node n : pdg.getNodeSet()) {
            if (!n.getStatement().equals("STOP")) {
                n.height = computeHeight(n);
//                System.out.println("Node #" + n.getNodeID() + " --> " + n.height);
                if (n.height > H) {
                    H = n.height;
                }
            }
        }
//        System.out.println("H = " + H);

        HashSet<Node> loopsInD = new HashSet<>(); // nodes that are representing a loop on some path f exists in D
        for (LinkedList<Node> f : D) { //f : path
            for (Node w : loopGuardNodes) {
                if (f.contains(w)) {
                    loopsInD.add(w);
                }
            }
        }

//        for(Node nodenode : loopsInD){
//            System.out.println(nodenode.getNodeID());
//        }
        for (int h = H; h > 0; h--) { //for h = H to 1 do
            for (Node n : loopsInD) {
                if (n.height == h) {
                    String r = loopAnalyzer(n); //loop method here does not need to be exists

                    if (r.equals("FALSE")) {
                        boolean flag = false;

                        for (LinkedList<Node> f : D) {
                            for (int i = 0; i < f.size() - 1; i++) {
                                Node p = f.get(i);
                                Node q = f.get(i + 1);
                                if (q.getNodeID() == n.getNodeID()) {
                                    for (Node lnode : n.getParentOfControlDep()) {
                                        if (lnode.getNodeID() == p.getNodeID()) {
                                            flag = true;
                                        }
                                    }
                                }
                            }
                        }

                        if (flag) { //if X -c-> n appears on at least one path f exists in D
                            sourceCode = sourceCode.replace(loop(n), "if " + guard(n) + " then \n" + body(n) + "\nendif");
                        }

                    }
                    else {
                        if (!r.equals("TRUE")) {
                            sourceCode = sourceCode.replace(loop(n), "if " + r + " then " + loop(n) + "\nendif");
                        }
                    }

                }
            }
        }

    }

    private int computeHeight(Node n) {
        Node current = n;
        int counter = 0;
        while (current.getNodeID() != pdg.getFirst().getNodeID()) {

            HashSet<Node> tempParents = current.getParentOfControlDep();

            Iterator<Node> it = tempParents.iterator();

            Node tempParent = it.next();
            if (tempParent.getNodeID() == current.getNodeID()) { // agar be khodesh bood, bayad avaz she
                tempParent = it.next();
            }
            current = tempParent;
            counter++;

        }
        return counter;

    }

    private void printPath(LinkedList<Node> path) {

        for (Node node : path) {
            System.out.print("#" + node.getNodeID() + " " + node.getStatement());
            if (path.indexOf(node) != path.size() - 1) {
                System.out.print("  ->  ");
            }
        }
        System.out.println();

    }

    private String loop(Node n) {
        return n.getNodeIdAndStmt();
    }

    // junk function
    private String loopAnalyzer(Node loopNode) {

        if (guard(loopNode).equals("TRUE") || guard(loopNode).equals("true")) {
            return "FALSE";
        }
        if (guard(loopNode).equals("FALSE") || guard(loopNode).equals("false")) {
            return "TRUE";
        }
        
        ////////human analysis:
        
        
        ////////if human analysis does not work, now use AProvE:
        String loopTemp = loopNode.getNodeIdAndStmt();
//        String pattern = "#[0-9]+:";
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(loopTemp);
//        loopTemp = m.replaceAll("");

//        loopTemp = loopTemp.replace("or", "||");
//        loopTemp = loopTemp.replace("and", "&&");
//        loopTemp = loopTemp.replace("NOP", "");
////        loopTemp = loopTemp.replace("inL", "int");
//loopTemp = loopTemp.replace("inH", "int");
//loopTemp = loopTemp.replace("outL", "printf");
//loopTemp = loopTemp.replace("outH", "printf");        
//loopTemp = loopTemp.replace("outH BOT", "");
//loopTemp = loopTemp.replace("outL BOT", "");
//loopTemp = loopTemp.replace("if", "if(");
//loopTemp = loopTemp.replace("then", ") {");
//loopTemp = loopTemp.replace("endif", "}");
        AProvE ape = new AProvE(loopTemp);

//        if (Math.random() > 0.5) {
//            return "TRUE";
//        }
        return "FALSE";
    }

    private String guard(Node n) {
        return n.getStatement();
    }

    private String body(Node n) {
        String body = n.getNodeIdAndStmt();
        body = body.replace("while #" + n.getNodeID() + ":" + n.getStatement() + " do \n", "");

        return body;
    }

}
