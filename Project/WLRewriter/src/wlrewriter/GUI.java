/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;

/**
 *
 * @author Mohammad
 */
public class GUI extends JFrame {

//    private JButton pdg, pini, psni;
    private JButton help, browse, clear, execute, clearLog;
    private JFileChooser inputFileBrowser;
    public static ColorPane /*sourceCodeTextArea,*/ terminal;
    private JScrollPane /*scroll,*/ terminalScroll;
    private JRadioButton pini, pdg, psni;
    private ButtonGroup group;
    private JLabel label;
    private RSyntaxTextArea sourceCodeTextArea;
    private RTextScrollPane scroll;
    public static boolean isSyntacticallyCorrect = true;
//    private JPanel p1, p2, p3, p4, p5;

    public GUI() {
        super("WL Rewriter 1.0");

//        panel = new JPanel();
//        try {
//            pdg = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("icon.png"))));
//        } catch (IOException ex) {
//            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pdg.setPreferredSize(new Dimension(30, 50));
        /**
         * ************************************************************************
         * pdg = new JButton("PDG"); pdg.setPreferredSize(new Dimension(100,
         * 40)); pini = new JButton("PINI"); pini.setPreferredSize(new
         * Dimension(100, 40)); psni = new JButton("PSNI");
         * psni.setPreferredSize(new Dimension(100, 40));
         */
        pdg = new JRadioButton("Make Program Dependence Graph");
        pdg.setMnemonic(KeyEvent.VK_1);
        pdg.setToolTipText("Alt + 1");
        pdg.setActionCommand("PDG");
        pdg.setForeground(new Color(13, 145, 22));
        pdg.setSelected(true);

        pini = new JRadioButton("Rewrite in Progress Insensitive mode");
        pini.setMnemonic(KeyEvent.VK_2);
        pini.setToolTipText("Alt + 2");
        pini.setActionCommand("PINI");
        pini.setForeground(new Color(44, 94, 232));

        psni = new JRadioButton("Rewrite in Progress Sensitive mode");
        psni.setMnemonic(KeyEvent.VK_3);
        psni.setToolTipText("Alt + 3");
        psni.setActionCommand("PSNI");
        psni.setForeground(new Color(123, 61, 229));
        //Group the radio buttons.
        group = new ButtonGroup();
        group.add(pdg);
        group.add(pini);
        group.add(psni);

        try {
            execute = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("execute.png"))));
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
//        execute.setForeground(new Color(145, 0, 15));
        execute.setForeground(new Color(0, 135, 22));
        execute.setMnemonic(KeyEvent.VK_F6);
        execute.setToolTipText("Alt + F6");
        add(execute);

        label = new JLabel("Current File Name: sourceCode.wl");
        add(label);

//        p1 = new JPanel();
//        p2 = new JPanel();
        add(pdg);
        add(pini);
        add(psni);

        try {
            //        p1.setLayout(new GridLayout(2, 1));
//        p1.add(p2);
//        p3 = new JPanel();
//        p3.setLayout(new GridLayout(2, 2));
            help = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("help.png"))));
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        help.setMnemonic(KeyEvent.VK_F1);
        help.setToolTipText("Alt + F1");

//        p4 = new JPanel();
        clear = new JButton("Clear");
        clear.setMnemonic(KeyEvent.VK_Q);
        clear.setToolTipText("Alt + Q");
        clear.setForeground(new Color(153, 0, 0));

        clearLog = new JButton("Clear Log");
        clearLog.setMnemonic(KeyEvent.VK_R);
        clearLog.setToolTipText("Alt + R");
//        clearLog.setPreferredSize(new Dimension(75, (int)(clear.getPreferredSize().getHeight())));

//        panel.setLayout(new GridLayout(5, 1, 0, 5));
//        panel.add(pdg);
//        panel.add(pini);
//        panel.add(psni);
        //        panel.add(new JPopupMenu.Separator());
//        panel.add(clear);
//        panel.add(help);
//        panel.setPreferredSize(new Dimension(100, 50));
        sourceCodeTextArea = new RSyntaxTextArea(14, 40);
//        sourceCodeTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        sourceCodeTextArea.setCodeFoldingEnabled(true);
        sourceCodeTextArea.setFont(new Font("Courier New", Font.PLAIN, 18));
//        sourceCodeTextArea.setBackground(new Color(203, 217, 247));
        sourceCodeTextArea.setBackground(new Color(240, 248, 255));
        sourceCodeTextArea.setCurrentLineHighlightColor(new Color(220, 220, 220));
        sourceCodeTextArea.setSelectionColor(new Color(192, 192, 192));
//        SyntaxScheme ss = sourceCodeTextArea.getSyntaxScheme();
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/WL", "wlrewriter.SyntaxMaker");
        sourceCodeTextArea.setSyntaxEditingStyle("text/WL");
        scroll = new RTextScrollPane(sourceCodeTextArea);

        /*sourceCodeTextArea = new JTextArea(14, 40);
         sourceCodeTextArea.setText("//type your source code here...\n");
         Font font = new Font("Courier New", Font.PLAIN, 18);
         sourceCodeTextArea.setFont(font);
         sourceCodeTextArea.setBackground(new Color(203, 217, 247));
         sourceCodeTextArea.setForeground(Color.black);
         scroll = new JScrollPane(sourceCodeTextArea);*/
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                sourceCodeTextArea.requestFocus();
            }
        });
//        scroll.setPreferredSize(new Dimension(800, 300));
        add(scroll);

        browse = new JButton("Browse File");
        browse.setForeground(new Color(0, 0, 102));
        browse.setMnemonic(KeyEvent.VK_O);
        browse.setToolTipText("Alt + O");
//        label = new JLabel("Select an input file...");
//        add(label);
        add(browse);
        clear.setPreferredSize(browse.getPreferredSize());
        add(clear);

        add(help);

        add(clearLog);
//        p3.add(p4);

//        JPanel p2 = new JPanel();
//        p2.add(label);
//        p2.add(browse);
//        terminalPanel = new JPanel();
        terminal = new ColorPane();
//        terminalPanel.add(terminal);
        terminal.setPreferredSize(new Dimension(220, 50));
        Font font1 = new Font("Consolas", Font.PLAIN, 12);
        terminal.setFont(font1);
        terminal.setForeground(new Color(203, 217, 247));
        terminal.setBackground(Color.BLACK);
        terminal.setEditable(false);
//        terminal.setText("adsf");
//        terminal.setText(terminal.getText()+"ggg");
        terminal.setText("Log:\n");

        terminalScroll = new JScrollPane(terminal);
        add(terminalScroll);
        
        help.setFocusPainted(false);
        pdg.setFocusPainted(false);
        pini.setFocusPainted(false);
        psni.setFocusPainted(false);
        execute.setFocusPainted(false);
        clearLog.setFocusPainted(false);
        clear.setFocusPainted(false);
        browse.setFocusPainted(false);
        
        
        

        SpringLayout layout = new SpringLayout();
        Container contentPane = this.getContentPane();
        contentPane.setLayout(layout);

        layout.putConstraint(SpringLayout.WEST, pdg,
                             15,
                             SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, pdg,
                             35,
                             SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, pini,
                             0,
                             SpringLayout.WEST, pdg);
        layout.putConstraint(SpringLayout.NORTH, pini,
                             5,
                             SpringLayout.SOUTH, pdg);

        layout.putConstraint(SpringLayout.WEST, psni,
                             0,
                             SpringLayout.WEST, pini);
        layout.putConstraint(SpringLayout.NORTH, psni,
                             5,
                             SpringLayout.SOUTH, pini);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, execute,
                             0,///////
                             SpringLayout.HORIZONTAL_CENTER, terminalScroll);
        layout.putConstraint(SpringLayout.NORTH, execute,
                             5,
                             SpringLayout.SOUTH, psni);
        layout.putConstraint(SpringLayout.WEST, scroll,
                             10,
                             SpringLayout.EAST, terminalScroll);
        layout.putConstraint(SpringLayout.NORTH, scroll,
                             35,
                             SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, terminalScroll,
                             15,
                             SpringLayout.WEST, contentPane);
//        layout.putConstraint(SpringLayout.EAST, terminalScroll,
//                             0,
//                             SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, terminalScroll,
                             20,
                             SpringLayout.SOUTH, execute);
//        int m = (int) scroll.getLocation().getX();
        layout.putConstraint(SpringLayout.EAST, browse,
                             0,
                             SpringLayout.HORIZONTAL_CENTER, scroll);
        layout.putConstraint(SpringLayout.NORTH, browse,
                             10,
                             SpringLayout.SOUTH, scroll);
        layout.putConstraint(SpringLayout.WEST, clear,
                             5,
                             SpringLayout.EAST, browse);
        layout.putConstraint(SpringLayout.NORTH, clear,
                             0,
                             SpringLayout.NORTH, browse);
//        layout.putConstraint(SpringLayout.EAST, label,
//                             -5,
//                             SpringLayout.WEST, browse);
//        layout.putConstraint(SpringLayout.NORTH, label,
//                             4,
//                             SpringLayout.NORTH, browse);
        layout.putConstraint(SpringLayout.EAST, help,
                             0,
                             SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.SOUTH, help,
                             -5,
                             SpringLayout.NORTH, scroll);
        layout.putConstraint(SpringLayout.EAST, scroll,
                             -10,
                             SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scroll,
                             -40,
                             SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, terminalScroll,
                             -40,
                             SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,
                             -15,
                             SpringLayout.NORTH, scroll);
        layout.putConstraint(SpringLayout.WEST, label,
                             0,
                             SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, clearLog,
                             10,
                             SpringLayout.SOUTH, terminalScroll);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, clearLog,
                             0,
                             SpringLayout.HORIZONTAL_CENTER, terminalScroll);

//        add(p3, BorderLayout.CENTER);
//        add(p1, BorderLayout.WEST);
//        add(new Panel().add(help), BorderLayout.EAST);
        //        browse.setPreferredSize(new Dimension(100, 100));
//        add(panel, BorderLayout.EAST);
//        add(scroll, BorderLayout.CENTER);
        //        add(inputFileBrowser, BorderLayout.SOUTH);
//        add(p2, BorderLayout.WEST);
//        add(terminalScroll, BorderLayout.SOUTH);
        ButtonHandler handler = new ButtonHandler();
        pdg.addActionListener(handler);
        pini.addActionListener(handler);
        psni.addActionListener(handler);
        help.addActionListener(handler);
        browse.addActionListener(handler);
        clear.addActionListener(handler);
        execute.addActionListener(handler);
        clearLog.addActionListener(handler);

    }

    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            terminal.appendError(ex.getMessage());
        }
        GUI gui = new GUI();
        gui.setLocation(10, 10);
//        gui.setSize(1000, 500);
        gui.setMinimumSize(new Dimension(800, 500));
//        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setResizable(true);
        gui.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("icon.png")));
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    private class ButtonHandler implements ActionListener {

        private String fileName = "";

        private void makeSourceCodeFile() {
            PrintStream writer;
            try {
                if (fileName.equals("")) {
                    fileName = "sourceCode.wl";
                }
                writer = new PrintStream(new File(fileName));

                writer.print(sourceCodeTextArea.getText());
                writer.close();
            } catch (Exception ex) {
                System.out.println("Error in creating file.");
                terminal.appendError("Error in creating file.");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == execute) {
                if (!sourceCodeTextArea.getText().equals("")) {
                    if (pdg.isSelected()) {
                        System.out.println("pdg");
                        makeSourceCodeFile();
                        YYParser.mainMethod(fileName, 0);
                        if (isSyntacticallyCorrect) {
                            JFrame pdgFrame = new JFrame("PDG - " + fileName);
                            String fileNameTemp = fileName.substring(0, fileName.lastIndexOf(".wl"));
                            fileNameTemp += "_PDG.png";
                            File file = new File(fileNameTemp);
                            BufferedImage image = null;
                            try {
                                image = ImageIO.read(file);
                            } catch (IOException ex) {
                                System.out.println("PDG File Error");
                                terminal.appendError("PDG File Error");
                            }
                            JLabel label = new JLabel(new ImageIcon(image));
                            pdgFrame.add(label);
                            pdgFrame.pack();
//                            pdgFrame.setResizable(false);
                            pdgFrame.setLocationRelativeTo(null);
                            pdgFrame.setVisible(true);
                            /////////////////////////////////////////
                            JFrame CFrame = new JFrame("C Source Code - " + fileName);
                            String CfileNameTemp = fileName + ".c";
                            File Cfile = new File(CfileNameTemp);
                            try {
                                Scanner Cscan = new Scanner(Cfile);
                                String Ccontent = "";
                                while (Cscan.hasNextLine()) {
                                    Ccontent += Cscan.nextLine() + "\n";
                                }

                                RSyntaxTextArea CArea = new RSyntaxTextArea(Ccontent);
                                CArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                                CArea.setFont(new Font("Courier New", Font.PLAIN, 18));
                                CArea.setEditable(false);
                                JScrollPane scrollC = new JScrollPane(CArea);
                                CFrame.add(scrollC);
                                CFrame.pack();
//                                CFrame.setResizable(false);
                                CFrame.setLocationRelativeTo(null);
                                CFrame.setVisible(true);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                terminal.appendError("File Not Found");
                            }
                        }
                    }
                    else {
                        if (pini.isSelected()) {
                            try {
                                System.out.println("pini");
                                makeSourceCodeFile();
                                YYParser.mainMethod(fileName, 1);
                                if (isSyntacticallyCorrect) {
                                    JFrame piniFrame = new JFrame("PINI Rewrited Source Code - " + fileName);
                                    RSyntaxTextArea PINIsourceCodeTextArea = new RSyntaxTextArea();
                                    RTextScrollPane PINIscroll = new RTextScrollPane(PINIsourceCodeTextArea);
                                    String fileNameTemp = fileName.substring(0, fileName.lastIndexOf(".wl"));
                                    fileNameTemp += "-PINI.wl";
                                    File file = new File(fileNameTemp);
                                    Scanner scan = new Scanner(file);
                                    while (scan.hasNextLine()) {
                                        PINIsourceCodeTextArea.append(scan.nextLine() + "\n");
                                    }
                                    PINIsourceCodeTextArea.setFont(new Font("Courier New", Font.PLAIN, 18));
                                    PINIsourceCodeTextArea.setBackground(new Color(240, 248, 255));
                                    PINIsourceCodeTextArea.setCurrentLineHighlightColor(new Color(220, 220, 220));
                                    PINIsourceCodeTextArea.setSelectionColor(new Color(192, 192, 192));
                                    AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
                                    atmf.putMapping("text/WL", "wlrewriter.SyntaxMaker");
                                    PINIsourceCodeTextArea.setSyntaxEditingStyle("text/WL");
                                    PINIsourceCodeTextArea.setAutoIndentEnabled(true);
                                    PINIsourceCodeTextArea.setEditable(false);
                                    piniFrame.add(PINIscroll);
                                    piniFrame.setSize(sourceCodeTextArea.getSize());
                                    piniFrame.setLocationRelativeTo(null);
                                    piniFrame.setVisible(true);
                                    /////////////////////////////////////////
                                    JFrame CFrame = new JFrame("C Source Code - PINI - " + fileName);
                                    String CfileNameTemp = fileName + "-PINI.c";
                                    File Cfile = new File(CfileNameTemp);
                                    try {
                                        Scanner Cscan = new Scanner(Cfile);
                                        String Ccontent = "";
                                        while (Cscan.hasNextLine()) {
                                            Ccontent += Cscan.nextLine() + "\n";
                                        }

                                        RSyntaxTextArea CArea = new RSyntaxTextArea(Ccontent);
                                        CArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                                        CArea.setFont(new Font("Courier New", Font.PLAIN, 18));
                                        CArea.setEditable(false);
//                                        CFrame.add(CArea);
//                                        CFrame.pack();
//                                        CFrame.setResizable(false);
                                        JScrollPane scrollC = new JScrollPane(CArea);
                                        CFrame.add(scrollC);
                                        CFrame.pack();
                                        CFrame.setLocationRelativeTo(null);
                                        CFrame.setVisible(true);
                                    } catch (FileNotFoundException ex) {
//                                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//                                        terminal.appendError("File Not Found");
                                        System.err.println("file pini not found");
                                    }

                                }
                            } catch (FileNotFoundException ex) {
                                terminal.appendError("Error in reading file");
                            }
                        }
                        else {
                            if (psni.isSelected()) {
                                try {
                                    System.out.println("psni");
                                    makeSourceCodeFile();
                                    YYParser.mainMethod(fileName, 2);
                                    if (isSyntacticallyCorrect) {
                                        JFrame psniFrame = new JFrame("PSNI Rewrited Source Code - " + fileName);
                                        RSyntaxTextArea PSNIsourceCodeTextArea = new RSyntaxTextArea();
                                        RTextScrollPane PSNIscroll = new RTextScrollPane(PSNIsourceCodeTextArea);
                                        String fileNameTemp = fileName.substring(0, fileName.lastIndexOf(".wl"));
                                        fileNameTemp += "-PSNI.wl";
                                        File file = new File(fileNameTemp);
                                        Scanner scan = new Scanner(file);
                                        while (scan.hasNextLine()) {
                                            PSNIsourceCodeTextArea.append(scan.nextLine() + "\n");
                                        }
                                        PSNIsourceCodeTextArea.setFont(new Font("Courier New", Font.PLAIN, 18));
                                        PSNIsourceCodeTextArea.setBackground(new Color(240, 248, 255));
                                        PSNIsourceCodeTextArea.setCurrentLineHighlightColor(new Color(220, 220, 220));
                                        PSNIsourceCodeTextArea.setSelectionColor(new Color(192, 192, 192));
                                        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
                                        atmf.putMapping("text/WL", "wlrewriter.SyntaxMaker");
                                        PSNIsourceCodeTextArea.setSyntaxEditingStyle("text/WL");
                                        PSNIsourceCodeTextArea.setAutoIndentEnabled(true);
                                        PSNIsourceCodeTextArea.setEditable(false);
                                        psniFrame.add(PSNIscroll);
                                        psniFrame.setSize(sourceCodeTextArea.getSize());
                                        psniFrame.setLocationRelativeTo(null);
                                        psniFrame.setVisible(true);
                                        /////////////////////////////////////////
                                        JFrame CFrame = new JFrame("C Source Code - PINI - " + fileName);
                                        String CfileNameTemp = fileName + "-PINI.c";
                                        File Cfile = new File(CfileNameTemp);
                                        try {
                                            Scanner Cscan = new Scanner(Cfile);
                                            String Ccontent = "";
                                            while (Cscan.hasNextLine()) {
                                                Ccontent += Cscan.nextLine() + "\n";
                                            }

                                            RSyntaxTextArea CArea = new RSyntaxTextArea(Ccontent);
                                            CArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                                            CArea.setFont(new Font("Courier New", Font.PLAIN, 18));
                                            CArea.setEditable(false);
                                            JScrollPane scrollC = new JScrollPane(CArea);
                                            CFrame.add(scrollC);
                                            CFrame.pack();
//                                            CFrame.add(CArea);
//                                            CFrame.pack();
//                                            CFrame.setResizable(false);
                                            CFrame.setLocationRelativeTo(null);
                                            CFrame.setVisible(true);
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                            terminal.appendError("File Not Found");
                                        }
                                        ////////////////////////////////////////
                                        /////////////////////////////////////////
                                        JFrame CFrame1 = new JFrame("C Source Code - PSNI - " + fileName);
                                        String CfileNameTemp1 = fileName + "-PSNI.c";
                                        File Cfile1 = new File(CfileNameTemp1);
                                        try {
                                            Scanner Cscan1 = new Scanner(Cfile1);
                                            String Ccontent1 = "";
                                            while (Cscan1.hasNextLine()) {
                                                Ccontent1 += Cscan1.nextLine() + "\n";
                                            }

                                            RSyntaxTextArea CArea1 = new RSyntaxTextArea(Ccontent1);
                                            CArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                                            CArea1.setFont(new Font("Courier New", Font.PLAIN, 18));
                                            CArea1.setEditable(false);
//                                            CFrame1.add(CArea1);
                                            JScrollPane scrollC1 = new JScrollPane(CArea1);
                                            CFrame1.add(scrollC1);
//                                CFrame1.pack();
                                            CFrame1.pack();
//                                            CFrame1.setResizable(false);
                                            CFrame1.setLocationRelativeTo(null);
                                            CFrame1.setVisible(true);
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                            terminal.appendError("File Not Found");
                                        }
                                    }
                                } catch (FileNotFoundException ex) {
                                    terminal.appendError("Error in reading file");
                                }
                            }
                        }
                    }
                    terminal.appendNewLine();
                }
                else {
                    terminal.append("Source code is empty.", Color.ORANGE);
                }
            }

//            if (e.getSource() == pdg) {
//                System.out.println("pdg");
//                makeSourceCodeFile();
//                YYParser.mainMethod(fileName, 0);
//            }
//            if (e.getSource() == pini) {
//                System.out.println("pini");
//                makeSourceCodeFile();
//                YYParser.mainMethod(fileName, 1);
//            }
//            if (e.getSource() == psni) {
//                System.out.println("psni");
//                makeSourceCodeFile();
//                YYParser.mainMethod(fileName, 2);
//            }
            if (e.getSource() == help) {
                System.out.println("help");
                JFrame help = new JFrame("Help");
                String helpContent = "Type your source code in the TextArea OR Open an input file by click \"Browse\".\n"
                                     + "Then, Choose an option and click \"Run\" button:\n"
                                     + "\t-You can see the \"Program Dependence Graph\" of the source code, or\n"
                                     + "\t-You can see the result of \"Progress Insenitive\" mode of Non-interference Program Rewriter, or     \n"
                                     + "\t-You can see the result of \"Progress Senitive\" mode of Non-interference Program Rewriter\n\n"
                                     + "*Check out shortcut keys for each button or option to make yourself feel better.\n\n"
                                     + "@Version: 1.0\n"
                                     + "@Latest Update: September 17, 2015\n"
                                     + "@Author: Seyed Mohammad Mehdi Ahmadpanah\n\n"
                                     + "Any feedback is most welcomed >>> Email: \'smahmadpanah@aut.ac.ir\'";

                JTextArea helpText = new JTextArea(helpContent);
                helpText.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
                helpText.setEditable(false);
                help.add(helpText);
                help.setResizable(false);
                help.pack();
                help.setLocationRelativeTo(null);
                help.setVisible(true);
            }

            if (e.getSource() == clear) {
                int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure?", "Alert!", JOptionPane.YES_NO_OPTION);
                if (result == 0) {//YES clicked
                    System.out.println("clear");

                    fileName = "";
                    sourceCodeTextArea.setText("");
                    label.setText("Current File Name: " + "sourceCode.wl");
                }
            }

            if (e.getSource() == clearLog) {
                System.out.println("Clear Log");
                terminal.clear();

            }

            if (e.getSource() == browse) {
                System.out.println("browse");
                inputFileBrowser = new JFileChooser("./");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("While Language Files", "wl");
                inputFileBrowser.setFileFilter(filter);
//                inputFileBrowser.setPreferredSize(new Dimension(700, 400));
                int returnVal = inputFileBrowser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        Scanner scanner;
                        scanner = new Scanner(inputFileBrowser.getSelectedFile());
                        fileName = inputFileBrowser.getSelectedFile().getName();
                        System.out.println(fileName);

                        String string = "";
                        while (scanner.hasNextLine()) {
                            string += scanner.nextLine() + "\n";
                        }

                        sourceCodeTextArea.setText(string);
                    } catch (Exception ex) {
//                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Selected file can not be read");
                        terminal.appendError("Selected file can not be read");
                    }
                }
                label.setText("Current File Name: " + fileName);
            }
        }

    }
}
