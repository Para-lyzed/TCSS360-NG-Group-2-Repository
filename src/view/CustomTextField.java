package view;

import java.awt.Font;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.CustomTextField.java
 * 
 * @author Nathan Grimsey
 *
 */
public class CustomTextField extends JFormattedTextField {
    
    /**
     * Creates a custom colored text field.
     * 
     * @author Nathan Grimsey
     */
    public CustomTextField() {
        super();
        setFont(Main.BASE_FONT);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    /**
     * Creates a custom colored text field with a NumberFormat.
     * 
     * @param numberFormat the NumberFormat for the text field to abide by.
     * 
     * @author Nathan Grimsey
     */
    public CustomTextField(NumberFormat numberFormat) {
        super(numberFormat);
        setFont(Main.BASE_FONT);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    /**
     * Creates a custom colored text field with a DateFormat.
     * 
     * @param dateFormat the DateFormat for the text field to abide by.
     * 
     * @author Nathan Grimsey
     */
    public CustomTextField(DateFormat dateFormat) {
        super(dateFormat);
        setFont(Main.BASE_FONT);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    /**
     * Creates a custom colored text field with a Font.
     * 
     * @param font the font to set the text field to.
     * 
     * @author Nathan Grimsey
     */
    public CustomTextField(Font font) {
        super();
        setFont(font);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    /**
     * Creates a custom colored text field with a NumberFormat and a font.
     * 
     * @param numberFormat the NumberFormat for the text field to abide by.
     * @param font the font to set the text field to.
     * 
     * @author Nathan Grimsey
     */
    public CustomTextField(NumberFormat numberFormat, Font font) {
        super(numberFormat);
        setFont(font);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    /**
     * Creates a custom colored text field with a DateFormat and a font.
     * 
     * @param dateFormat the DateFormat for the text field to abide by.
     * @param font the font to set the text field to.
     * 
     * @author Nathan Grimsey
     */
    public CustomTextField(DateFormat dateFormat, Font font) {
        super(dateFormat);
        setFont(font);
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
        setBorder(Main.BORDER);
    }

    /**
     * Refreshes UI colors for dark mode.
     * 
     * @author Nathan Grimsey
     */
    public void darkMode() {
        setBackground(Main.TEXT_BOX_BACKGROUND);
        setForeground(Main.TEXT);
        setCaretColor(Main.CARET);
    }

    /**
     * Used before a field is verified to make sure it is valid.
     * 
     * @author Nathan Grimsey
     */
    public void fireAction() {
        fireActionPerformed();
    }

}
