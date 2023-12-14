package model;

import javax.swing.*;
/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.AboutScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class AboutScreen extends BaseScreen {
    public static final String title = "model.About";
    private static JLabel registeredTo;
    private static JLabel currentUser;
    private static JLabel providedBy;
    private static JLabel version;
    private static String[] contributors;

    /**
     * model.AboutScreen creates the window with information on the app.
     *
     * @param width the width of the screen.
     * @param height the height of the screen.
     * @param about contains owner profile, and version, and contributors.
     * @author Nathan Grimsey
     */
    public AboutScreen(int width, int height) {
        super(width, height, title, 1);
        registeredTo = new JLabel("This app is registered to:");
        registeredTo.setFont(Main.HEADING_TWO_FONT);
        currentUser = new JLabel(About.getOwnerString());
        currentUser.setFont(Main.BASE_FONT);
        providedBy = new JLabel("This app is provided by:");
        providedBy.setFont(Main.HEADING_TWO_FONT);
        version = new JLabel("Version v" + About.getVersion());
        version.setFont(Main.VERSION_FONT);
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
            contributor.setFont(Main.BASE_FONT);
            add(contributor, c);
        }
        c.gridy++;
        add(version, c);
    }

    /**
     * refreshProfile sets the current user to the owner.
     * @author Nathan Grimsey
     */
    public void refreshProfile() {
        currentUser.setText(About.getOwnerString());
    }
}
