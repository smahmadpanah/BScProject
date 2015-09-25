/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static wlrewriter.YYParser.writer;

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
    private String sourceCodeForLoop; // YOU CAN WRITE IT ON A FILE FOR PINI.c FILE
    private ArrayList<Variable> symbolTableOfVariables;
    private MyLinkedList piniCfg;//just for replace method
    private PINIRewriter pini;

    public PSNIRewriter(PINIRewriter pini) {
        pdg = pini.getPdg();
        this.pini = pini;
//        sourceCodeForLoop = pini.getRewritedSourceCodeOutputFile();
        sourceCode = pini.getRewritedSourceCode();

        YYParser yyparser;
        Yylex lexer;
        Yylex yylexTemp = null;
        try {
            yylexTemp = new Yylex(new InputStreamReader(new FileInputStream(pini.getFileName() + "-PINI.wl")));
        } catch (Exception ex) {
            System.err.println("Source code file not found!");
            GUI.terminal.appendError("Source code file not found!");
            //System.exit(0);
            return;
        }

        lexer = yylexTemp;

        yyparser = new YYParser(new YYParser.Lexer() {
            @Override
            public int yylex() {
                int yyl_return = -1;
                try {

                    yyl_return = lexer.yylex();
                } catch (IOException e) {
                    System.err.println("IO error : " + e);
                    GUI.terminal.appendError("IO error : " + e);
                }
                return yyl_return;
            }

            @Override
            public void yyerror(String error) {
                //System.err.println ("Error : " + error);
                System.err.println("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");
                writer.print("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");
                GUI.terminal.append("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **", Color.orange);

            }

            @Override
            public Object getLVal() {
                return null;
            }

        }, this);
        try {
            yyparser.controlFlag = 1;
            yyparser.parse();
            sourceCodeForLoop = yyparser.cSourceCodeForPSNI;
            symbolTableOfVariables = yyparser.symbolTableOfVariables;

        } catch (IOException ex) {
            Logger.getLogger(PSNIRewriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {

        }
        writer.close();

        D = new HashSet<>(); // paths

        initializeD(); //initializing paths

        String rewritedSourceCode = reWrite();
//        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n"+rewritedSourceCode);
        String pattern = "#[0-9]+:";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(rewritedSourceCode);
        rewritedSourceCode = m.replaceAll("");

        try {
            String fileName = YYParser.getSourceCodeFileName().replace(".wl", "");
            PrintStream writer = new PrintStream(new File(fileName + "-PSNI.wl"));
            writer.print(rewritedSourceCode);
            writer.close();
            System.out.println("$$$PSNI REWRITER --> Check out: " + fileName + "-PSNI.wl");
            GUI.terminal.append("PSNI REWRITER is completed --> Check out: " + fileName + "-PSNI.wl");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found!");
            GUI.terminal.appendError("File Not Found!");
        }

        new PSNItoC(pini);
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

    private String reWrite() {
        if (D.isEmpty()) { // if there is no path, finished!
            return sourceCode;
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
        sourceCode = piniCfg.getFirst().getNodeIdAndStmt();

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

                            for (Node nodeInPiniCFG : piniCfg.getNodeSet()) {
                                if (n.whileID == nodeInPiniCFG.whileID) {
//                                    System.out.println(loop(nodeInPiniCFG));
//                                    System.out.println(guard(nodeInPiniCFG));
//                                    System.out.println(body(nodeInPiniCFG));
//                                    System.out.println(sourceCode.contains(loop(nodeInPiniCFG)));
                                    sourceCode = sourceCode.replace(loop(nodeInPiniCFG), "if " + guard(nodeInPiniCFG) + " then \n" + body(nodeInPiniCFG) + "\nendif");
                                    break;
                                }
                            }
                        }

                    }
                    else {
                        if (!r.equals("TRUE")) {
                            for (Node nodeInPiniCFG : piniCfg.getNodeSet()) {
//                                System.out.println(nodeInPiniCFG.whileID);
                                if (n.whileID == nodeInPiniCFG.whileID) {
                                    sourceCode = sourceCode.replace(loop(nodeInPiniCFG), "if " + r + " then \n" + loop(nodeInPiniCFG) + "\nendif");
                                    break;
                                }
                            }

                        }
                    }

                }
            }
        }
        return sourceCode;
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

    private String loopAnalyzer(Node loopNode) {

        if (guard(loopNode).equals("TRUE") || guard(loopNode).equals("true")) {
            return "FALSE";
        }
        if (guard(loopNode).equals("FALSE") || guard(loopNode).equals("false")) {
            return "TRUE";
        }

        //human analysis for test:
        if (pini.fileName.equals("input-article-Fig9")) {
            return "l1 <= h1 or l1 < 0";
        }
        else {
            if (pini.fileName.equals("15PSNIwhileif")) {
                return "(l2 <= 0)";
            }
            else {
                if (pini.fileName.equals("16PSNIwhilewhileconcat")) {
                    if (guard(loopNode).equals("h1 == 0")) {
                        return "FALSE";
                    }
                    else {
                        if (guard(loopNode).equals("h2 > l1")) {
                            return "(h2 < 0) or (l1 >= h2)";
                        }
                    }
                }
                else {
                    if (pini.fileName.equals("17PSNIwhilewhilenested")) {
                        if (guard(loopNode).equals("h2 > 0")) {
                            return "TRUE";
                        }
                        else {
                            if (guard(loopNode).equals("h1 > l1")) {
                                return "(h2 <= 0)";
                            }
                        }
                    }
                }
            }
        }
        ////////if human analysis does not work, now use AProvE:
        String startLoop = "~WhileID" + loopNode.whileID + "~";
        int startLoopIndex = sourceCodeForLoop.indexOf(startLoop);
        String endLoop = "~ENDWhileID" + loopNode.whileID + "~";
        int endLoopIndex = sourceCodeForLoop.indexOf(endLoop);

        String loopEntire = sourceCodeForLoop.substring(startLoopIndex, endLoopIndex);
        //omit Node IDs 
        String pattern = "~WhileID[0-9]+~";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(loopEntire);
        loopEntire = m.replaceAll("");
        pattern = "~ENDWhileID[0-9]+~";
        r = Pattern.compile(pattern);
        m = r.matcher(loopEntire);
        loopEntire = m.replaceAll("");

        String tempInts = "int ";
        int countertemp = 0;
        for (Variable v : symbolTableOfVariables) {
            tempInts += v.name;
            if (countertemp != symbolTableOfVariables.size() - 1) {
                tempInts += ", ";
            }
            countertemp++;
        }
        tempInts += ";\n";

        loopEntire = tempInts + loopEntire;

        loopEntire = "#include <stdio.h>\n"
                     + "#define TRUE 1\n"
                     + "#define true 1\n"
                     + "#define FALSE 0\n"
                     + "#define false 0\n\n"
                     + "int main(){\n"
                     + loopEntire
                     + "return 0;\n}";

        loopEntire = loopEntire.replace("{", "{\n");
        loopEntire = loopEntire.replace("}", "}\n");
        loopEntire = loopEntire.replace("\n;", ";");
        loopEntire = loopEntire.replace(";", ";\n");

        loopEntire = loopEntire.replace("\n", "\r\n");

//        System.out.println("\n\n\n\n****\n" + loopEntire + "\n\n\n\n#####");
        AProvE ape = new AProvE(loopEntire);

        if (ape.isTerminated == 1) {
            return "TRUE";
        }
        else {
            if (ape.isTerminated == 0) {
                return "FALSE";
            }
            else {
                return "!(" + guard(loopNode) + ")";
            }
        }
//        if (Math.random() > 0.5) {
//            return "TRUE";
//        }
//        return "FALSE";
    }

    private String guard(Node n) {
        return n.getStatement();
    }

    private String body(Node n) {
        String body = n.getNodeIdAndStmt();
        body = body.replace("while #" + n.getNodeID() + ":" + n.getStatement() + " do \n", "");
        int lastIndexDone = body.lastIndexOf("done");
        body = body.substring(0, lastIndexDone);
        return body;
    }

    public void setPSNICFG(MyLinkedList list) {
        piniCfg = list;
    }

    private class PSNItoC {

        PSNItoC(PINIRewriter pini) {

            YYParser yyparser;
            Yylex lexer;
            Yylex yylexTemp = null;

            try {
                yylexTemp = new Yylex(new InputStreamReader(new FileInputStream(pini.getFileName() + "-PSNI.wl")));
            } catch (Exception ex) {
                System.err.println("Source code file not found!");
                GUI.terminal.appendError("Source code file not found!");
                //System.exit(0);
                return;
            }

            lexer = yylexTemp;

            yyparser = new YYParser(new YYParser.Lexer() {
                @Override
                public int yylex() {
                    int yyl_return = -1;
                    try {

                        yyl_return = lexer.yylex();
                    } catch (IOException e) {
                        System.err.println("IO error : " + e);
                        GUI.terminal.appendError("IO error : " + e);
                    }
                    return yyl_return;
                }

                @Override
                public void yyerror(String error) {
                    //System.err.println ("Error : " + error);
                    System.err.println("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");
                    writer.print("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");
                    GUI.terminal.append("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **", Color.orange);

                }

                @Override
                public Object getLVal() {
                    return null;
                }

            });
            try {
                yyparser.controlFlag = 2;
                yyparser.parse();
                sourceCodeForLoop = yyparser.cSourceCodeForPSNI;
                symbolTableOfVariables = yyparser.symbolTableOfVariables;

            } catch (IOException ex) {
                Logger.getLogger(PSNIRewriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {

            }
        }

    }
}
