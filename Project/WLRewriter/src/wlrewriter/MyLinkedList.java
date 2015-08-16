/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Mohammad
 */
public class MyLinkedList {

    private Node first, last;
    private HashSet<Node> nodeSet; //list of nodes    
    
    public MyLinkedList(Node node) {

        first = node;
        last = node;
        nodeSet = new HashSet<Node>();
        nodeSet.add(node);
        
    }

    /**
     * nextPointer1 and previousPointer1 are updated.
     *
     * @param list
     */
    public void merge(MyLinkedList list) {
        last.setNextPointer1(list.getFirst());
        list.getFirst().addPreviousPointer(last);
        last = list.getLast();

        nodeSet.addAll(list.getNodeSet());

    }
   

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public HashSet<Node> getNodeSet() {
        return nodeSet;
    }

    public void printNodeSet() {
        for (Iterator<Node> it = nodeSet.iterator(); it.hasNext();) {
            Node temp = it.next();
            System.out.println("Node #: " + temp.getNodeID() + " | Node Statement: " + temp.getStatement());

        }

    }

}
