import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * SettingScreen.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class SettingScreen extends BaseMainMenuScreen {
    private static final String title = "Settings";
    private static final JLabel setProfile = new JLabel("Set new name and email");
    private static final JLabel name = new JLabel("Name:");
    private static final JTextField nameTextField = new JTextField(16);
    private static final JLabel email = new JLabel("Email:");
    private static final JTextField emailTextField = new JTextField(16);
    private static final JButton submitButton = new JButton("Submit");

    /**
     * SettingScreen displays the setting screen.
     * @param width sets the width of the window.
     * @param height sets the height of the window.
     * @param about contains owner profile, and version, and contributors.
     * @param aboutScreen is the JPanel with the about screen.
     */
    public SettingScreen(int width, int height, AboutScreen aboutScreen) {
        super(width, height, title, 4);
        c.gridy++;
        setProfile.setFont(Main.HEADING_TWO_FONT);
        add(setProfile, c);
        c.gridy++;
        c.gridwidth = 1;
        name.setFont(Main.BASE_FONT);
        add(name, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        nameTextField.setFont(Main.BASE_FONT);
        add(nameTextField, c);
        c.gridx++;
        c.ipadx = 0;
        email.setFont(Main.BASE_FONT);
        add(email, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        emailTextField.setFont(Main.BASE_FONT);
        add(emailTextField, c);
        c.gridy++;
        c.gridx = 0;
        c.ipadx = 0;
        submitButton.setFont(Main.BASE_FONT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About.updateProfile(nameTextField.getText(), emailTextField.getText());
                nameTextField.setText("");
                emailTextField.setText("");
                aboutScreen.refreshProfile();
            }
        });
        add(submitButton, c);
    }
}