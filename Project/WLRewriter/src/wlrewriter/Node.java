/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;

/**
 *
 * @author Mohammad
 */
public class Node {

    private int nodeID;
    private String statement;
    private ArrayList<Node> nextPointers, previousPointers;

    public Node(int nodeID, String statement) {
        this.nodeID = nodeID;
        this.statement = statement;
        nextPointers = new ArrayList<>(1);
        previousPointers = new ArrayList<>(1);
    }

    public ArrayList<Node> getNextPointers() {
        return nextPointers;
    }

    public void setNextPointers(ArrayList<Node> nextPointers) {
        this.nextPointers = nextPointers;
    }

    public int getNodeID() {
        return nodeID;
    }

    public String getStatement() {
        return statement;
    }

    public void setPreviousPointers(ArrayList<Node> previousPointers) {
        this.previousPointers = previousPointers;
    }

    public ArrayList<Node> getPreviousPointers() {
        return previousPointers;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

}
