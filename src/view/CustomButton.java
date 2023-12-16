package view;

import java.awt.Font;

import javax.swing.JButton;

import model.Main;

public class CustomButton extends JButton {
    

    public CustomButton() {
        super();
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
        setFocusPainted(false);
        setFont(Main.BASE_FONT);
    }

    public CustomButton(String name) {
        super();
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
        setFocusPainted(false);
        setFont(Main.BASE_FONT);
        setText(name);
    }

    public CustomButton(String name, Font font) {
        super();
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
        setFocusPainted(false);
        setFont(font);
        setText(name);
    }

    public void darkMode() {
        setBackground(Main.BUTTON_BACKGROUND);
        setForeground(Main.TEXT);
    }
}
