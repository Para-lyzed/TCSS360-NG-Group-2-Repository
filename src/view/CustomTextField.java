package view;

import java.awt.Font;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import model.Main;

public class CustomTextField extends JFormattedTextField {
    
    public CustomTextField() {
        super();
        setFont(Main.BASE_FONT);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    public CustomTextField(NumberFormat numberFormat) {
        super(numberFormat);
        setFont(Main.BASE_FONT);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    public CustomTextField(DateFormat dateFormat) {
        super(dateFormat);
        setFont(Main.BASE_FONT);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    public CustomTextField(Font font) {
        super();
        setFont(font);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    public CustomTextField(NumberFormat numberFormat, Font font) {
        super(numberFormat);
        setFont(font);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    public CustomTextField(DateFormat dateFormat, Font font) {
        super(dateFormat);
        setFont(font);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    public void darkMode() {
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
    }

    public void fireAction() {
        fireActionPerformed();
    }

}
