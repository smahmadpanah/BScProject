/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import GraphViz.*;

/**
 *
 * Forward Dominance Tree and Control Dependence Graph Builder
 *
 * @param CFG
 * @author Mohammad
 */
public class FDTBuilder {

    private LinkedList cfg, FDTree; //FDTree is equal to PostDomTree
    private HashSet<Node> FDTNodes;

    public FDTBuilder(LinkedList cfg) {

        this.cfg = cfg;

        this.cfg.getFirst().setNextPointer2(this.cfg.getLast()); //according to the article, it needs to connect START to STOP

        //print the cfg for test
//        cfg.printNodeSet();
        //computing post dominators
        computePostDominators();

        //print dominators
//        printPostDominators();
        //draw the FDT (Post Dom Tree) - Find the immediate post dominance
        makePostDomTree();

        //find the immediate post dom for each node - It must be unique, but I did not take risk and get an array for it
        setImmediatePostDoms();

        //print the immediate post dom for each node that they are set in the field of each node
//        printImmediatePostDoms();
        //compute PDF for each node that they will be control dependences
        computePDFs();

        //print the PDFs for all nodes in FDTNodes
//        printPDFs();
        //compute Control Dependecies from PDFs
        computeControlDep();

        //print the Control Dependecies
        printControlDeps();
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
        System.out.println("**** print Post Dominators ****");
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

    private void setImmediatePostDoms() {
        for (Node n : FDTNodes) {
            for (Node i : n.getPostDominators()) {
                boolean flag = true;
                for (Node j : n.getPostDominators()) {
                    if (!j.equals(i)) {
                        if (j.getPostDominators().contains(i)) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag == true) {
                    if (n.getImmediatePostDominator() != null) {
                        System.err.println("Immediate Post Dom for this node is not unique!!");
                    }
                    n.setImmediatePostDominator(i);

                    for (Node k : cfg.getNodeSet()) {
                        if (k.getNodeID() == n.getNodeID()) {
                            k.setImmediatePostDominator(i);
                            break;
                        }
                    }
                }

            }

        }

    }

    private void printImmediatePostDoms() {
        System.out.println("**** print Immediate Post Dominators ****");
        for (Node n : FDTNodes) {

            System.out.println("Immediate Post Dom for Node " + n.getNodeID() + " --> " + n.getStatement() + ":");

            if (!n.getStatement().equals("STOP")) { //because of being null for STOP node
                Node q = n.getImmediatePostDominator();
                System.out.println("\t" + q.getNodeID() + " --> " + q.getStatement());
            }
            else {
                System.out.println("\t");
            }

        }
    }

    private void computePDFs() {
        for (Node n : FDTNodes) {
            if (n.getNextPointersForPostDomTree().isEmpty()) { //the node is leaf of PostDomTree
                HashSet<Node> worklist = new HashSet<>();
                worklist.add(n);

                while (!worklist.isEmpty()) {
                    Node x = worklist.iterator().next();
                    worklist.remove(x);

                    worklist.addAll(x.pred()); //traverse bottom-up the postDomTree

                    HashSet<Node> currentPDF = new HashSet<>(); //PDF for node x
                    ///local///
                    for (Node xInCFG : cfg.getNodeSet()) {
                        if (xInCFG.getNodeID() == x.getNodeID()) { // Node xInCFG is x in CFG - we need pred() in CFG
                            if (!xInCFG.pred().isEmpty()) {
                                for (Node y : xInCFG.pred()) {
                                    if (y.getImmediatePostDominator() != null) {
                                        if (y.getImmediatePostDominator().getNodeID() != xInCFG.getNodeID()) {
                                            currentPDF.add(y);
                                        }
                                    }
                                    else {
                                        currentPDF.add(y);
                                    }
                                }
                            }
                            break;
                        }
                    }

                    ///up///
                    for (Node z : FDTNodes) {
                        if (z.getImmediatePostDominator() != null) {
                            if (z.getImmediatePostDominator().getNodeID() == x.getNodeID()) {

                                for (Node y : z.getPDF()) {
                                    if (y.getImmediatePostDominator() != null) {
                                        if (y.getImmediatePostDominator().getNodeID() != x.getNodeID()) {
                                            currentPDF.add(y);

                                        }
                                    }
                                    else {
                                        currentPDF.add(y);
                                    }
                                }

                            }
                        }
                    }

                    x.setPDF(currentPDF);

                }
            }
        }
    }

    private void printPDFs() {
        System.out.println("**** print FDTs ****");
        for (Node n : FDTNodes) {
            System.out.println("PDF(" + n.getNodeID() + ": " + n.getStatement() + ") = {");

            for (Node q : n.getPDF()) {
                System.out.println("\t" + q.getNodeID() + ": " + q.getStatement() + ", ");
            }
            System.out.println("}");
        }

    }

    private void computeControlDep() {
        for (Node y : FDTNodes) {
            for (Node x : FDTNodes) {
                for (Iterator<Node> it = y.getPDF().iterator(); it.hasNext();) {
                    Node w = it.next();
                    if (w.getNodeID() == x.getNodeID()) {
                        // mitavanad tekrari bashe [dar sorati ke be khodes nabayad vabastegi dashte bashe, in ja ye if mizarim : if(y.getNodeId()!=x.getNodeId())]
                        x.getContolDep().add(y);

                    }
                }
            }
        }
    }

    private void printControlDeps() {
        String CDgraph = "";
        System.out.println("\n******************\nprint Control Dependencies ");
        for (Node n : FDTNodes) {
            System.out.print("Node -->" + n.getNodeID() + ": " + n.getStatement() + " = {");

            for (Node q : n.getContolDep()) {
                System.out.print(q.getNodeID() + ": " + q.getStatement() + " | ");
                CDgraph += "\"" + "#" + n.getNodeID() + "    " + n.getStatement() + "\"" + " -> " + "\"" +"#" +q.getNodeID() + "    " + q.getStatement() + "\"" + ";\n";
            }
            System.out.println("}");
        }
        System.out.println("******************\n");

        GraphDrawer gd = new GraphDrawer();
        gd.draw(YYParser.getSourceCodeFileName() + "_CDG.", CDgraph);

    }

}
