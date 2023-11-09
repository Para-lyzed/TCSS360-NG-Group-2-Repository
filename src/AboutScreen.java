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
    private JLabel registeredTo;
    private JLabel currentUser;
    private JLabel providedBy;
    private JLabel version;
    private String[] contributors;
    private About about;

    public AboutScreen(int width, int height, About about) {
        this.about = about;
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
        registeredTo = new JLabel("This app is registered to:");
        registeredTo.setFont(Main.headingTwoFont);
        currentUser = new JLabel(this.about.getOwnerString());
        currentUser.setFont(Main.baseFont);
        providedBy = new JLabel("This app is provided by:");
        providedBy.setFont(Main.headingTwoFont);
        version = new JLabel("Version v" + about.getVersion());
        version.setFont(Main.versionFont);
        c.gridy++;
        add(registeredTo, c);
        c.gridy++;
        add(currentUser, c);
        c.gridy++;
        add(providedBy, c);
        contributors = about.getContributors();
        for(int i = 0; i < contributors.length; i++) {
            c.gridy++;
            JLabel contributor = new JLabel(contributors[i]);
            contributor.setFont(Main.baseFont);
            add(contributor, c);
        }
        c.gridy++;
        add(version, c);
    }

    public void refreshProfile() {
        currentUser.setText(this.about.getOwnerString());
    }

    public void menuHeading(boolean isMenuOpen) {
        if (isMenuOpen) {
            heading.setText("About");
        }
        else {
            heading.setText("     About");
        }
    }

}
