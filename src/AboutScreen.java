import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * AboutScreen.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class AboutScreen extends JPanel {
    private JLabel heading = new JLabel("     About");
    private JLabel currentUser;
    private JLabel contributors;
    private About about;

    public AboutScreen(int width, int height, About about) {
        this.about = about;
        setBackground(Color.WHITE);
        setBounds(0, 0, width, height);
        setOpaque(true);
        heading.setFont(Main.headingOneFont);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        add(heading, c);
        currentUser = new JLabel("This app is registered to:   " + this.about.getOwnerString());
        currentUser.setFont(Main.baseFont);
        contributors = new JLabel("This app is provided by:   " + this.about.getContributors());
        contributors.setFont(Main.baseFont);
        c.gridy++;
        add(currentUser, c);
        c.gridy++;
        add(contributors, c);
    }

    public void refreshProfile() {
        currentUser.setText("This app is registered to:   " + this.about.getOwnerString());
    }

}
