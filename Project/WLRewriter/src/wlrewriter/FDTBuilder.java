/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * Forward Dominance Tree Builder
 *
 * @param CFG
 * @author Mohammad
 */
public class FDTBuilder {

    private LinkedList cfg, FDTree, postDomTree;
    private HashSet<Node> FDTNodes;

    public FDTBuilder(LinkedList cfg) {

        this.cfg = cfg;

        //print the cfg for test
        cfg.printNodeSet();

        //computing dominators
        computeDominators();

        //print dominators
        printDominators();

        //draw the FDT - Find the immediate dominance
        makeImmedaiteDomTree();

        //build the post dominance tree - main result tree for the class
        buildPostDomTree();
    }

    private void computeDominators() {

        for (Node n : cfg.getNodeSet()) {
            n.setDominators(cfg.getNodeSet()); //Dom(n) = NodeSet
        }

        HashSet<Node> workList = new HashSet<Node>();
        workList.add(cfg.getFirst()); //WorkList = {StartNode}

        while (!workList.isEmpty()) {

            Node y = workList.iterator().next();
            workList.remove(y); // Remove any node Y from Worklist

            // New = {y} + intersect Dom(x): x is in pred(y)
            HashSet<Node> New = new HashSet<>();
            New.add(y);

            //  try {
            HashSet<Node> intersect = new HashSet<>();
            if (y.pred().iterator().hasNext()) {
                intersect = (HashSet) y.pred().iterator().next().getDominators().clone();

                for (Node x : y.pred()) {
                    intersect.retainAll(x.getDominators());
                }
                New.addAll(intersect);
            }

            if (!New.equals(y.getDominators())) {
                y.setDominators(New); //Dom(y) = New

                workList.addAll(y.succ()); // for (each z in succ(y)) worklist += {z}

            }
//            } catch (NullPointerException ex) {
//                System.out.println("Intersect is null");
//
//            }
        }
        // you can find dominators for each node in getDominators() method 

    }

    private void printDominators() {

        for (Node i : cfg.getNodeSet()) {
            System.out.print("Dom(" + i.getNodeID() + "): ");
            for (Node alpha : i.getDominators()) {
                System.out.print(alpha.getNodeID() + ", ");
            }
            System.out.println("");
        }
    }

    private void makeImmedaiteDomTree() {
        FDTNodes = new HashSet<>();

        //copy nodes from CFG to FDTNodes
        for (Node p : cfg.getNodeSet()) {
            Node n = new Node(p.getNodeID(), p.getStatement());
            HashSet<Node> tempDom = new HashSet<>(p.getDominators());
            n.setDominators(tempDom);

            //eliminate the node from dominators to search its pred
            n.getDominators().remove(p);

            FDTNodes.add(n);

        }

        HashSet<LinkedList> listOfFDTNodes = new HashSet<>();

        for (Iterator<Node> it = FDTNodes.iterator(); it.hasNext();) {
            Node temp = it.next();

            listOfFDTNodes.add(new LinkedList(temp));

        }

        
        FDTree = new LinkedList(null);
        for (Iterator<LinkedList> it = listOfFDTNodes.iterator(); it.hasNext();) {
            LinkedList temp = it.next();
            Node tempNode = temp.getFirst();

            
            if (tempNode.getStatement().equals("START")) {
                FDTree.setFirst(tempNode);
            }
            else {
                if (tempNode.getStatement().equals("STOP")) {
                    FDTree.setLast(tempNode);
                }
                for (Iterator<Node> it2 = cfg.getNodeSet().iterator(); it2.hasNext();) {
                    Node temp2 = it2.next();
                    if (tempNode.getDominators().equals(temp2.getDominators())) {
                        for (Iterator<LinkedList> it3 = listOfFDTNodes.iterator(); it3.hasNext();) {
                            LinkedList temp3 = it3.next();
                            Node tempNode3 = temp3.getFirst();
                            if (tempNode3.getNodeID() == temp2.getNodeID()) {
                                mergeLists(temp3, temp);

                            }
                        }

                    }
                }

            }
        }
        /**
         * ********************************************************************
         */
        System.out.println("ImmediateTree");
    }

    private void mergeLists(LinkedList first, LinkedList second) {
        first.getFirst().addNextPointersForFDT(second.getFirst());
        second.getFirst().addPreviousPointer(first.getFirst());
    }

    private void buildPostDomTree() {
        postDomTree = new LinkedList(FDTree.getLast()); //STOP is the first node of post dom tree
        Node currentNode = postDomTree.getFirst();
        postDomTree.setLast(FDTree.getFirst()); // START is the last node of post dom tree

        while (!currentNode.isVisited) {
            currentNode.isVisited = true;
            if (!currentNode.getStatement().equals("START")) {
                currentNode.addNextPointersForPostDomTree(currentNode.pred().iterator().next());
                if (currentNode.pred().iterator().next().getNextPointersForFDT().size() > 1) {
                    for (Node beta : currentNode.pred().iterator().next().getNextPointersForFDT()) {
                       /////////////////////////////////////////////////////
                        if (beta.getNodeID() != currentNode.getNodeID() && !beta.isVisited) {
                            
                            currentNode.addNextPointersForPostDomTree(beta);
                        }
                    }
                }
                currentNode = currentNode.pred().iterator().next();
            }
        }
        System.out.println("PostDomTree is created.");
    }

}
