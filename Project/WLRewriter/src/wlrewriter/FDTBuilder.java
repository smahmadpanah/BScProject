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

    private LinkedList cfg, FDTree; //FDTree is equal to PostDomTree
    private HashSet<Node> FDTNodes;

    public FDTBuilder(LinkedList cfg) {

        this.cfg = cfg;

        //print the cfg for test
        cfg.printNodeSet();

        //computing post dominators
        computePostDominators();

        //print dominators
        printPostDominators();

        //draw the FDT (Post Dom Tree) - Find the immediate post dominance
        makePostDomTree();

    }

    private void computePostDominators() {

        for (Node n : cfg.getNodeSet()) {
            n.setPostDominators(cfg.getNodeSet()); //PostDom(n) = NodeSet
        }

        HashSet<Node> workList = new HashSet<Node>();
        workList.add(cfg.getLast()); //WorkList = {StopNode}

        while (!workList.isEmpty()) {

            Node y = workList.iterator().next();
            workList.remove(y); // Remove any node Y from Worklist

            // New = {y} + intersect PostDom(x): x is in succ(y)
            HashSet<Node> New = new HashSet<>();
            New.add(y);

            if (y.succ().iterator().hasNext()) {
                HashSet<Node> intersect = new HashSet<>(y.succ().iterator().next().getPostDominators());

                for (Node x : y.succ()) {
                    intersect.retainAll(x.getPostDominators());
                }
                New.addAll(intersect);
            }

            if (!New.equals(y.getPostDominators())) {
                y.setPostDominators(New); //PostDom(y) = New

                workList.addAll(y.pred()); // for (each z in pred(y)) worklist += {z}

            }

        }
        // you can find post dominators for each node in getPostDominators() method 

    }

    private void printPostDominators() {

        for (Node i : cfg.getNodeSet()) {
            System.out.print("Post-Dom(" + i.getNodeID() + "): ");
            for (Node alpha : i.getPostDominators()) {
                System.out.print(alpha.getNodeID() + ", ");
            }
            System.out.println("");
        }
    }

    private void makePostDomTree() {
        FDTNodes = new HashSet<>();

        //copy nodes from CFG to FDTNodes
        for (Node p : cfg.getNodeSet()) {
            Node n = new Node(p.getNodeID(), p.getStatement());
            HashSet<Node> tempPostDom = new HashSet<>(p.getPostDominators());
            n.setPostDominators(tempPostDom);

            //eliminate the node from post-dominators to search its pred
            n.getPostDominators().remove(p);

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

            if (tempNode.getStatement().equals("STOP")) {
                FDTree.setFirst(tempNode);
            }
            else {
                if (tempNode.getStatement().equals("START")) {
                    FDTree.setLast(tempNode);
                }
                for (Iterator<Node> it2 = cfg.getNodeSet().iterator(); it2.hasNext();) {
                    Node temp2 = it2.next();
                    if (tempNode.getPostDominators().equals(temp2.getPostDominators())) {
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
        System.out.println("Post-Dom Tree is created");
    }

    private void mergeLists(LinkedList first, LinkedList second) {
        first.getFirst().addNextPointersForPostDomTree(second.getFirst());
        second.getFirst().addPreviousPointer(first.getFirst());
    }
}
