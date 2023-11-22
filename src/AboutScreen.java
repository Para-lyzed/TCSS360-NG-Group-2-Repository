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
    private static JLabel heading = new JLabel("     About");
    private static JLabel registeredTo;
    private static JLabel currentUser;
    private static JLabel providedBy;
    private static JLabel version;
    private static String[] contributors;

    /**
     * AboutScreen creates the window with information on the app.
     *
     * @param width the width of the screen.
     * @param height the height of the screen.
     * @param about contains owner profile, and version, and contributors.
     */
    public AboutScreen(int width, int height) {
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
        currentUser = new JLabel(About.getOwnerString());
        currentUser.setFont(Main.baseFont);
        providedBy = new JLabel("This app is provided by:");
        providedBy.setFont(Main.headingTwoFont);
        version = new JLabel("Version v" + About.getVersion());
        version.setFont(Main.versionFont);
        c.gridy++;
        add(registeredTo, c);
        c.gridy++;
        add(currentUser, c);
        c.gridy++;
        add(providedBy, c);
        contributors = About.getContributors();
        for(int i = 0; i < contributors.length; i++) {
            c.gridy++;
            JLabel contributor = new JLabel(contributors[i]);
            contributor.setFont(Main.baseFont);
            add(contributor, c);
        }
        c.gridy++;
        add(version, c);
    }

    /**
     * refreshProfile sets the current user to the owner.
     */
    public void refreshProfile() {
        currentUser.setText(About.getOwnerString());
    }

    /**
     * menuHeading gets rid of the spacing on the header if the menu is open.
     * @param isMenuOpen is if the menu is open.
     */
    public void menuHeading(boolean isMenuOpen) {
        if (isMenuOpen) {
            heading.setText("About");
        }
        else {
            heading.setText("     About");
        }
    }

}
