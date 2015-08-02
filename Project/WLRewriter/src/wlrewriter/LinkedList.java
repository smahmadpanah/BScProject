/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wlrewriter;


/**
 *
 * @author Mohammad
 */
public class LinkedList {

    private Node first, last;
    
    
    public LinkedList(Node node) {
    
        first = node;
        last = node;
        
    
    }
    /**
     * nextPointer1 and previousPointer1 are updated.
     * 
     * @param list 
     */
    public void merge (LinkedList list){
        last.setNextPointer1(list.getFirst());
        list.getFirst().addPreviousPointer(last);
        last = list.getLast();
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
    
    
    
    
}
