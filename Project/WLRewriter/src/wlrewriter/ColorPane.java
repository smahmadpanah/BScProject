package wlrewriter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;

public class ColorPane extends JTextPane {

    public void append(String s, Color c) { // better implementation--uses StyleContext
        s = "> " + s + "\n";
        this.setEditable(true);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                                            StyleConstants.Foreground, c);

        int len = getDocument().getLength(); // same value as getText().length();
        setCaretPosition(len);  // place caret at the end (with no selection)
        setCharacterAttributes(aset, false);
        replaceSelection(s); // there is no selection, so inserts at caret
        this.setEditable(false);

    }
    
    
    

    public void append(String s) { // better implementation--uses StyleContext
        s = "> " + s + "\n";
        this.setEditable(true);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                                            StyleConstants.Foreground, this.getForeground());

        int len = getDocument().getLength(); // same value as getText().length();
        setCaretPosition(len);  // place caret at the end (with no selection)
        setCharacterAttributes(aset, false);
        replaceSelection(s); // there is no selection, so inserts at caret
        this.setEditable(false);

    }
    
    public void clear() { // better implementation--uses StyleContext
        String s = "Log:\n";
        this.setEditable(true);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                                            StyleConstants.Foreground, new Color(203, 217, 247));

//        int len = getDocument().getLength(); // same value as getText().length();
//        setCaretPosition(len);  // place caret at the end (with no selection)
        setText("");
        setCharacterAttributes(aset, false);
        replaceSelection(s); // there is no selection, so inserts at caret
        this.setEditable(false);

    }

    public void appendError(String s) { // better implementation--uses StyleContext
        s = "> " + s + "\n";
        this.setEditable(true);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                                            StyleConstants.Foreground, Color.red);

        int len = getDocument().getLength(); // same value as getText().length();
        setCaretPosition(len);  // place caret at the end (with no selection)
        setCharacterAttributes(aset, false);
        replaceSelection(s); // there is no selection, so inserts at caret
        this.setEditable(false);

    }

    public void appendNewLine() {
        this.setEditable(true);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                                            StyleConstants.Foreground, this.getForeground());

        int len = getDocument().getLength(); // same value as getText().length();
        setCaretPosition(len);  // place caret at the end (with no selection)
        setCharacterAttributes(aset, false);
        replaceSelection("---------------------------\n"); // there is no selection, so inserts at caret
        this.setEditable(false);
    }

}
