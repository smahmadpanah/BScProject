/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 *
 * @author Mohammad
 */
public class PINIRewriter {

    private MyLinkedList pdg; // pdg = G
    private String sourceCode, CopyOfSourceCode, rewritedSourceCode; // sourceCode = M | CopyOfSourceCode = M' | rewritedSourceCode = result
    private HashSet<LinkedList<Node>> F; // the set of paths

    private Node sourceNode, destinationNode;

    public PINIRewriter(MyLinkedList pdg) {

        this.pdg = pdg;
        sourceCode = pdg.getFirst().getNodeIdAndStmt();

        F = new HashSet<>(); // paths
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
                }
            }

            for (LinkedList<Node> path : F) {
                path.add(0, pdg.getFirst());
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

            return CopyOfSourceCode;
        }
    }

    private void breadthFirst(LinkedList<Node> visited) {

        HashSet<Node> allDeps = visited.getLast().getContolDep();
        allDeps.addAll(visited.getLast().getDataDepsForThisNode());

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
        // in breadth-first, recursion needs to come after visiting adjacent nodes
        for (Node node : nodes) {
            if (visited.contains(node) || node.equals(destinationNode)) {
                continue;
            }

            visited.addLast(node);
            breadthFirst(visited);

            visited.removeLast();
        }

    }

    private void printPath(LinkedList<Node> visited) {
        for (Node node : visited) {
            System.out.print("#" + node.getNodeID() + " " + node.getStatement());
            if(visited.indexOf(node) != visited.size()-1)
            System.out.print("  ->  ");
        }
        System.out.println();
    }
}
