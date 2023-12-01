import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * SettingScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class SettingScreen extends BaseMainMenuScreen {
    public static final String title = "Settings";
    private static final JLabel setProfile = new JLabel("Set new name and email");
    private static final JLabel name = new JLabel("Name:");
    private static final JTextField nameTextField = new JTextField(16);
    private static final JLabel email = new JLabel("Email:");
    private static final JTextField emailTextField = new JTextField(16);
    private static final JButton submitButton = new JButton("Submit");
    private static final JButton importSettingsButton = new JButton("Import Settings");
    private static final JButton exportSettingsButton = new JButton("Export Settings");
    private static final JFileChooser fileChooser = new JFileChooser();

    /**
     * SettingScreen displays the setting screen.
     * @param width sets the width of the window.
     * @param height sets the height of the window.
     * @param about contains owner profile, and version, and contributors.
     * @author Nathan Grimsey
     */
    public SettingScreen(int width, int height) {
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
                DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
                nameTextField.setText("");
                emailTextField.setText("");
            }
        });
        add(submitButton, c);
        fileChooser.setFileFilter(new FileNameExtensionFilter("MPP Settings File", "mpp"));
        c.gridy++;
        importSettingsButton.setFont(Main.BASE_FONT);
        importSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setSelectedFile(new File(""));
                int returnVal;
                returnVal = fileChooser.showOpenDialog(Main.BASE_FRAME);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    DataIO.importProgramData(fileChooser.getSelectedFile().toPath());
                }
            }
        });
        add(importSettingsButton, c);
        c.gridx = 1;
        exportSettingsButton.setFont(Main.BASE_FONT);
        exportSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setSelectedFile(new File(Main.PROJECT_DATA_FILE_PATH.toString()));
                int returnVal;
                returnVal = fileChooser.showSaveDialog(Main.BASE_FRAME);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String exportPathString = fileChooser.getSelectedFile().toPath().toString();
                    if (!exportPathString.endsWith(".mpp")) {
                        exportPathString += ".mpp";
                    }
                    DataIO.saveProgramData(Paths.get(exportPathString));
                }
            }
        });
        add(exportSettingsButton, c);
    }
}