package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.About;
import model.DataIO;
import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.SettingScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class SettingScreen extends BaseScreen {
    public static final String title = "Settings";
    private static final JLabel setProfile = new JLabel("Set new name and email");
    private static final JLabel name = new JLabel("Name:");
    private static final JTextField nameTextField = new JTextField(16);
    private static final JLabel email = new JLabel("Email:");
    private static final JTextField emailTextField = new JTextField(16);
    private static final JButton saveButton = new JButton("Save");
    private static final JButton importSettingsButton = new JButton("Import Settings");
    private static final JButton exportSettingsButton = new JButton("Export Settings");
    private static final JFileChooser fileChooser = new JFileChooser();
    private static final JCheckBox darkModeCheckBox = new JCheckBox("Dark mode");
    private static JPanel mainContent;
    private static JScrollPane scrollablePane;

    /**
     * SettingScreen displays the setting screen.
     * @param width sets the width of the window.
     * @param height sets the height of the window.
     * @param about contains owner profile, and version, and contributors.
     * @author Nathan Grimsey
     */
    public SettingScreen(int width, int height) {
        super(width, height, title, 1);
        scrollablePane = new JScrollPane();
        scrollablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy++;
        c.weighty = 20;
        add(scrollablePane, c);
        mainContent = new JPanel();
        mainContent.setBackground(Main.BACKGROUND);
        scrollablePane.setViewportView(mainContent);
        scrollablePane.setBorder(BorderFactory.createEmptyBorder());
        mainContent.setLayout(new GridBagLayout());
        setProfile.setFont(Main.HEADING_TWO_FONT);
        c.weighty = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        mainContent.add(setProfile, c);
        c.gridy++;
        c.gridwidth = 1;
        name.setFont(Main.BASE_FONT);
        mainContent.add(name, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        nameTextField.setFont(Main.BASE_FONT);
        mainContent.add(nameTextField, c);
        c.gridx++;
        c.ipadx = 0;
        email.setFont(Main.BASE_FONT);
        mainContent.add(email, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        emailTextField.setFont(Main.BASE_FONT);
        mainContent.add(emailTextField, c);
        c.gridy++;
        c.gridx = 0;
        c.ipadx = 0;
        darkModeCheckBox.setFont(Main.BASE_FONT);
        darkModeCheckBox.setOpaque(false);
        if (Main.userSettings.getDarkMode()) {
            darkModeCheckBox.setEnabled(true);
        }
        darkModeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.userSettings.setDarkMode(!Main.userSettings.getDarkMode());
            }
        });
        mainContent.add(darkModeCheckBox, c);
        c.gridy++;
        saveButton.setFont(Main.BASE_FONT);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameString = nameTextField.getText();
                String emailString = emailTextField.getText();
                if (!nameString.isEmpty() && !emailString.isEmpty()) {
                    About.updateProfile(nameString, emailString);
                    nameTextField.setText("");
                    emailTextField.setText("");
                }
                DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
            }
        });
        mainContent.add(saveButton, c);
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
        mainContent.add(importSettingsButton, c);
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
        mainContent.add(exportSettingsButton, c);
        mainContent.setPreferredSize(new Dimension(scrollablePane.getWidth(), mainContent.getPreferredSize().height));
    }
}