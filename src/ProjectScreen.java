import java.awt.BorderLayout;
import java.awt.Color;

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
        setLayout(new BorderLayout());
        add(heading, BorderLayout.NORTH);
    }

}
