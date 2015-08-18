/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Mohammad
 */
public class Node {

    private int nodeID;
    private String statement;
    private Node nextPointer1, nextPointer2; // For Successor --> in condition statements: nextPointer1 = true, nextPointer2 = false | in other statments: nextPointer1 = next node, nextPointer2 = null
    private HashSet<Node> previousPointers; // For Predecessor
    private HashSet<Node> PostDominators; // For Dominator Nodes
    private HashSet<Node> nextPointersForPostDomTree;
    private Node immediatePostDominator;
    public boolean isVisited;
    private HashSet<Node> PDF, ContolDep; //PDF is related to Control Dependeces, ControlDep is Control Dependeces for the node
    
    
    /********************************/
    private HashSet<Variable> variablesOfNode;//All variables except assigned variable.
    /********************************/
    
    private Variable assignedVariable;//The variable that is assigned. Other variables are used.    
    
    private HashSet<Node> dataDepsForThisNode; //data dependecies for this node that means which nodes are used this node's assigned variable 

    private HashSet<Node> parentOfControlDep;
    
    private HashSet<Node> parentsOfDataDep;
    
    private String nodeIdAndStmt;
    
    public Node(int nodeID, String statement) {
        this.isVisited = false;
        this.nodeID = nodeID;
        this.statement = statement;
        nextPointer1 = null;
        nextPointer2 = null;
        previousPointers = new HashSet<Node>();
        PostDominators = new HashSet<Node>();
        nextPointersForPostDomTree = new HashSet<>();
        immediatePostDominator = null;
        PDF = new HashSet<>();
        ContolDep = new HashSet<>();
        variablesOfNode = new HashSet<>();
        assignedVariable = null;
        dataDepsForThisNode = new HashSet<>();
        parentOfControlDep = null;
        parentsOfDataDep = new HashSet<>();
        parentOfControlDep = new HashSet<>();
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

    public void addPreviousPointer(Node previousPointer) {
        previousPointers.add(previousPointer);
    }

    public void setNextPointer2(Node nextPointer2) {
        this.nextPointer2 = nextPointer2;
    }

    public HashSet<Node> pred() {
        return previousPointers;
    }

    public HashSet<Node> succ() {
        HashSet<Node> successor = new HashSet<Node>();
        if (nextPointer1 != null) {
            successor.add(nextPointer1);
        }
        if (nextPointer2 != null) {
            successor.add(nextPointer2);
        }

        return successor;
    }

    public HashSet<Node> getPostDominators() {
        return PostDominators;
    }

    public void setPostDominators(HashSet<Node> PostDominators) {
        this.PostDominators = PostDominators;
    }

    public void setPreviousPointers(HashSet<Node> previousPointers) {
        this.previousPointers = previousPointers;
    }

    public void addNextPointersForPostDomTree(Node nextNode) {
        nextPointersForPostDomTree.add(nextNode);
    }

    public HashSet<Node> getNextPointersForPostDomTree() {
        return nextPointersForPostDomTree;
    }

    public Node getImmediatePostDominator() {
        return immediatePostDominator;
    }

    public void setImmediatePostDominator(Node immediatePostDominator) {
        this.immediatePostDominator = immediatePostDominator;
    }

    public HashSet<Node> getPDF() {
        return PDF;
    }

    public void setPDF(HashSet<Node> PDF) {
        this.PDF = PDF;
    }

    public HashSet<Node> getContolDep() {
        return ContolDep;
    }

    public void setContolDep(HashSet<Node> ContolDep) {
        this.ContolDep = ContolDep;
    }

    public HashSet<Variable> getVariablesOfNode() {
        return variablesOfNode;
    }

    public void addToVariablesOfNode(Variable variable) {
        this.variablesOfNode.add(variable);
    }

    public Variable getAssignedVariable() {
        return assignedVariable;
    }

    public void setAssignedVariable(Variable assignedVariable) {
        this.assignedVariable = assignedVariable;
    }

    public HashSet<Node> getDataDepsForThisNode() {
        return dataDepsForThisNode;
    }

    public HashSet<Node> getParentOfControlDep() {
        return parentOfControlDep;
    }

    public void setParentOfControlDep(HashSet parentOfControlDep) {
        this.parentOfControlDep = parentOfControlDep;
    }

    public HashSet<Node> getParentsOfDataDep() {
        return parentsOfDataDep;
    }

    public void setParentsOfDataDep(HashSet<Node> parentsOfDataDep) {
        this.parentsOfDataDep = parentsOfDataDep;
    }

    public String getNodeIdAndStmt() {
        return nodeIdAndStmt;
    }

    public void setNodeIdAndStmt(String nodeIdAndStmt) {
        this.nodeIdAndStmt = nodeIdAndStmt;
    }

    public void setVariablesOfNode(HashSet<Variable> variablesOfNode) {
        this.variablesOfNode = variablesOfNode;
    }

    public void setDataDepsForThisNode(HashSet<Node> dataDepsForThisNode) {
        this.dataDepsForThisNode = dataDepsForThisNode;
    }
    
    
    
    
    
    

}
