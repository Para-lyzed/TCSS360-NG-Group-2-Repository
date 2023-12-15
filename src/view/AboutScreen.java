package view;

import java.util.ArrayList;

import javax.swing.*;

import model.About;
import model.Main;
/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.AboutScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class AboutScreen extends BaseScreen {
    public static final String title = "About";
    private static JLabel registeredTo;
    private static JLabel currentUser;
    private static JLabel providedBy;
    private static JLabel version;
    private static String[] contributors;
    private static ArrayList<JLabel> contributorLabels = new ArrayList<>();

    /**
     * AboutScreen creates the window with information on the app.
     *
     * @param width the width of the screen.
     * @param height the height of the screen.
     * @param about contains owner profile, and version, and contributors.
     * 
     * @author Nathan Grimsey
     */
    public AboutScreen(int width, int height) {
        super(width, height, title, 1);
        registeredTo = new JLabel("This app is registered to:");
        registeredTo.setFont(Main.HEADING_TWO_FONT);
        registeredTo.setForeground(Main.TEXT);
        currentUser = new JLabel(About.getOwnerString());
        currentUser.setFont(Main.BASE_FONT);
        currentUser.setForeground(Main.TEXT);
        providedBy = new JLabel("This app is provided by:");
        providedBy.setFont(Main.HEADING_TWO_FONT);
        providedBy.setForeground(Main.TEXT);
        version = new JLabel("Version v" + About.getVersion());
        version.setFont(Main.VERSION_FONT);
        version.setForeground(Main.TEXT);
        c.gridy++;
        add(registeredTo, c);
        c.gridy++;
        add(currentUser, c);
        c.gridy++;
        add(providedBy, c);
        contributors = About.getContributors();
        for (int i = 0; i < contributors.length; i++) {
            c.gridy++;
            contributorLabels.add(i, new JLabel(contributors[i]));
            contributorLabels.get(i).setFont(Main.BASE_FONT);
            contributorLabels.get(i).setForeground(Main.TEXT);
            add(contributorLabels.get(i), c);
        }
        c.gridy++;
        add(version, c);
    }

    /**
     * refreshProfile sets the current user to the owner.
     * 
     * @author Nathan Grimsey
     */
    public void refreshProfile() {
        currentUser.setText(About.getOwnerString());
    }

    @Override
    public void darkMode() {
        super.darkMode();
        registeredTo.setForeground(Main.TEXT);
        currentUser.setForeground(Main.TEXT);
        providedBy.setForeground(Main.TEXT);
        version.setForeground(Main.TEXT);
        for (int i = 0; i < contributorLabels.size(); i++) {
            contributorLabels.get(i).setForeground(Main.TEXT);
        }
    }
}
