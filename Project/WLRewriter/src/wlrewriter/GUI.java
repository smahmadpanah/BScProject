/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mohammad
 */
public class GUI extends JFrame {

//    private JButton pdg, pini, psni;
    private JButton help, browse, clear, execute;
    private JFileChooser inputFileBrowser;
    private JTextArea sourceCodeTextArea, terminal;
    private JScrollPane scroll, terminalScroll;
    private JRadioButton pini, pdg, psni;
    private ButtonGroup group;
//    private JLabel label;
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
        pdg = new JRadioButton("PDG");
        pdg.setActionCommand("PDG");
        pdg.setSelected(true);

        pini = new JRadioButton("PINI");
        pini.setActionCommand("PINI");

        psni = new JRadioButton("PSNI");
        psni.setActionCommand("PSNI");

        //Group the radio buttons.
        group = new ButtonGroup();
        group.add(pdg);
        group.add(pini);
        group.add(psni);

        execute = new JButton("Execute");
        add(execute);

//        p1 = new JPanel();
//        p2 = new JPanel();
        add(pdg);
        add(pini);
        add(psni);

//        p1.setLayout(new GridLayout(2, 1));
//        p1.add(p2);
//        p3 = new JPanel();
//        p3.setLayout(new GridLayout(2, 2));
        help = new JButton("Help");

//        p4 = new JPanel();
        clear = new JButton("Clear");
//        panel.setLayout(new GridLayout(5, 1, 0, 5));
//        panel.add(pdg);
//        panel.add(pini);
//        panel.add(psni);
        //        panel.add(new JPopupMenu.Separator());
//        panel.add(clear);
//        panel.add(help);
//        panel.setPreferredSize(new Dimension(100, 50));
        sourceCodeTextArea = new JTextArea(14, 40);
        Font font = new Font("Courier New", Font.PLAIN, 18);
        sourceCodeTextArea.setFont(font);
        scroll = new JScrollPane(sourceCodeTextArea);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                sourceCodeTextArea.requestFocus();
            }
        });
//        scroll.setPreferredSize(new Dimension(800, 300));
        add(scroll);
        browse = new JButton("Browse File");
//        label = new JLabel("Select an input file...");
//        add(label);
        add(browse);
        clear.setPreferredSize(browse.getPreferredSize());
        add(clear);

        add(help);
//        p3.add(p4);

//        JPanel p2 = new JPanel();
//        p2.add(label);
//        p2.add(browse);
        terminal = new JTextArea(11, 15);
        Font font1 = new Font("Times New Roman", Font.PLAIN, 14);
        terminal.setFont(font1);
        terminal.setForeground(Color.GREEN);
        terminal.setBackground(Color.BLACK);
        terminal.setEditable(false);
        terminal.setText("Log:\n");
        terminalScroll = new JScrollPane(terminal);
        add(terminalScroll);

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

    }

    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == execute) {
                if (pdg.isSelected()) {
                    System.out.println("pdg");
                    makeSourceCodeFile();
                    YYParser.mainMethod(fileName, 0);
                }
                else {
                    if (pini.isSelected()) {
                        System.out.println("pini");
                        makeSourceCodeFile();
                        YYParser.mainMethod(fileName, 1);
                    }
                    else {
                        if (psni.isSelected()) {
                            System.out.println("psni");
                            makeSourceCodeFile();
                            YYParser.mainMethod(fileName, 2);
                        }
                    }
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
                JOptionPane.showMessageDialog(null, "Help me! :)");
            }
            if (e.getSource() == clear) {
                System.out.println("clear");
                fileName = "";
                sourceCodeTextArea.setText("");
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
                    }
                }
            }
        }

    }
}
