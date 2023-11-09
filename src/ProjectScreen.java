import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * ProjectScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectScreen extends JPanel {
    private JLabel heading = new JLabel("     Projects");

    public ProjectScreen(int width, int height) {
        setBackground(Color.WHITE);
        setBounds(0, 0, width, height);
        setOpaque(true);
        heading.setFont(Main.headingOneFont);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = Main.paddingInsets;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        add(heading, c);
    }

    public void menuHeading(boolean isMenuOpen) {
        if (isMenuOpen) {
            heading.setText("Projects");
        }
        else {
            heading.setText("     Projects");
        }
    }

}
