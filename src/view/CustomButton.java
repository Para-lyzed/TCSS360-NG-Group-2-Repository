package view;

import java.awt.Font;

import javax.swing.JButton;

import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.CustomButton.java
 * 
 * @author Nathan Grimsey
 *
 */
public class CustomButton extends JButton {
    

    /**
     * Default button with formatting applied.
     * 
     * @author Nathan Grimsey
     */
    public CustomButton() {
        super();
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
        setFocusPainted(false);
        setFont(Main.BASE_FONT);
    }

    /**
     * Default button with formatting applied and set to String name.
     * 
     * @param name of the button.
     * 
     * @author Nathan Grimsey
     */
    public CustomButton(String name) {
        super();
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
        setFocusPainted(false);
        setFont(Main.BASE_FONT);
        setText(name);
    }

    /**
     * Default button with formatting applied, set to String name, with specified Font.
     * 
     * @param name of the button.
     * @param font to set the button to.
     * 
     * @author Nathan Grimsey
     */
    public CustomButton(String name, Font font) {
        super();
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
        setFocusPainted(false);
        setFont(font);
        setText(name);
    }

    /**
     * Refrehes the colors of the UI elements to comply ith dark/light mode.
     * 
     * @author Nathan Grimsey
     */
    public void darkMode() {
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
    }
}
