package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import model.Main;

public class CustomScrollBar extends BasicScrollBarUI {
    private static final JButton emptyButton = new JButton();

    public CustomScrollBar() {
        super();
        emptyButton.setPreferredSize(new Dimension(0, 0));
    }

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = Main.SCROLLBAR;
        this.trackColor = Main.BUTTON_BACKGROUND;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return emptyButton;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return emptyButton;
    }
}
