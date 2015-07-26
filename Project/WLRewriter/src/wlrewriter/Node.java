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
    private Node nextPointer1, nextPointer2; // in condition statements: nextPointer1 = true, nextPointer2 = false | in other statments: nextPointer1 = next node, nextPointer2 = null

    public Node(int nodeID, String statement) {
        this.nodeID = nodeID;
        this.statement = statement;
        nextPointer1 = null;
        nextPointer2 = null;;
    }


    public int getNodeID() {
        return nodeID;
    }

    public String getStatement() {
        return statement;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Node getNextPointer1() {
        return nextPointer1;
    }

    public Node getNextPointer2() {
        return nextPointer2;
    }

    public void setNextPointer1(Node nextPointer1) {
        this.nextPointer1 = nextPointer1;
    }

    public void setNextPointer2(Node nextPointer2) {
        this.nextPointer2 = nextPointer2;
    }

    
}
