package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.About;
import model.DataIO;
import model.Main;
import model.Profile;

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
    private static final int checkBoxIconSize = 30;
    private static Image checkBoxDarkUnchecked;
    private static Image checkBoxDarkChecked;
    private static Image checkBoxLightUnchecked;
    private static Image checkBoxLightChecked;
    private static Image resizedCheckBoxDarkUnchecked;
    private static Image resizedCheckBoxDarkChecked;
    private static Image resizedCheckBoxLightUnchecked;
    private static Image resizedCheckBoxLightChecked;
    private static JPanel mainContent;
    private static JScrollPane scrollablePane;

    /**
     * SettingScreen displays the setting screen.
     * 
     * @param width sets the width of the window.
     * @param height sets the height of the window.
     * @param about contains owner Profile, version, and contributors.
     * 
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
        setProfile.setForeground(Main.TEXT);
        c.weighty = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        mainContent.add(setProfile, c);
        c.gridy++;
        c.gridwidth = 1;
        name.setFont(Main.BASE_FONT);
        name.setForeground(Main.TEXT);
        mainContent.add(name, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        nameTextField.setFont(Main.BASE_FONT);
        nameTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
        nameTextField.setForeground(Main.TEXT);
        mainContent.add(nameTextField, c);
        c.gridx++;
        c.ipadx = 0;
        email.setFont(Main.BASE_FONT);
        email.setForeground(Main.TEXT);
        mainContent.add(email, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        emailTextField.setFont(Main.BASE_FONT);
        emailTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
        emailTextField.setForeground(Main.TEXT);
        mainContent.add(emailTextField, c);
        fillUserDetails();
        c.gridy++;
        c.gridx = 0;
        c.ipadx = 0;
        darkModeCheckBox.setFont(Main.BASE_FONT);
        darkModeCheckBox.setForeground(Main.TEXT);
        darkModeCheckBox.setOpaque(false);
        try {
            checkBoxDarkUnchecked = ImageIO.read(getClass().getResource("../icons/DarkUnchecked.png"));
            checkBoxDarkChecked = ImageIO.read(getClass().getResource("../icons/DarkChecked.png"));
            checkBoxLightUnchecked = ImageIO.read(getClass().getResource("../icons/LightUnchecked.png"));
            checkBoxLightChecked = ImageIO.read(getClass().getResource("../icons/LightChecked.png"));
            resizedCheckBoxDarkUnchecked = checkBoxDarkUnchecked.getScaledInstance(checkBoxIconSize, checkBoxIconSize, Image.SCALE_SMOOTH);
            resizedCheckBoxDarkChecked = checkBoxDarkChecked.getScaledInstance(checkBoxIconSize, checkBoxIconSize, Image.SCALE_SMOOTH);
            resizedCheckBoxLightUnchecked = checkBoxLightUnchecked.getScaledInstance(checkBoxIconSize, checkBoxIconSize, Image.SCALE_SMOOTH);
            resizedCheckBoxLightChecked = checkBoxLightChecked.getScaledInstance(checkBoxIconSize, checkBoxIconSize, Image.SCALE_SMOOTH);
            if (Main.userSettings.getDarkMode()) {
                darkModeCheckBox.setIcon(new ImageIcon(resizedCheckBoxDarkUnchecked));
                darkModeCheckBox.setSelectedIcon(new ImageIcon(resizedCheckBoxDarkChecked));
            }
            else {
                darkModeCheckBox.setIcon(new ImageIcon(resizedCheckBoxLightUnchecked));
                darkModeCheckBox.setSelectedIcon(new ImageIcon(resizedCheckBoxLightChecked));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (Main.userSettings.getDarkMode()) {
            darkModeCheckBox.setSelected(true);
        }
        darkModeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (darkModeCheckBox.isSelected()) {
                    Main.userSettings.setDarkMode(true);
                    Main.darkMode(true);
                }
                else {
                    Main.userSettings.setDarkMode(false);
                    Main.darkMode(false);
                }
                Main.BASE_FRAME.darkMode();
                DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
                Main.BASE_FRAME.repaint();
            }
        });
        mainContent.add(darkModeCheckBox, c);
        c.gridy++;
        saveButton.setFont(Main.BASE_FONT);
        saveButton.setForeground(Main.TEXT);
        saveButton.setBackground(Main.BUTTON_BACKGROUND);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameString = nameTextField.getText();
                String emailString = emailTextField.getText();
                if (!nameString.isEmpty() && !emailString.isEmpty()) {
                    About.updateProfile(nameString, emailString);
                }
                DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
            }
        });
        mainContent.add(saveButton, c);
        fileChooser.setFileFilter(new FileNameExtensionFilter("MPP Settings File", "mpp"));
        c.gridy++;
        importSettingsButton.setFont(Main.BASE_FONT);
        importSettingsButton.setForeground(Main.TEXT);
        importSettingsButton.setBackground(Main.BUTTON_BACKGROUND);
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
        exportSettingsButton.setForeground(Main.TEXT);
        exportSettingsButton.setBackground(Main.BUTTON_BACKGROUND);
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

    private void fillUserDetails() {
        Profile user = Main.userSettings.getProfile();
        if (!(user.getName().equals(Profile.DEFAULT_NAME) || user.getEmail().equals(Profile.DEFAULT_EMAIL))) {
            nameTextField.setText(user.getName());
            emailTextField.setText(user.getEmail());
        }
    }

    @Override
    public void darkMode() {
        super.darkMode();
        mainContent.setBackground(Main.BACKGROUND);
        setProfile.setForeground(Main.TEXT);
        name.setForeground(Main.TEXT);
        nameTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
        nameTextField.setForeground(Main.TEXT);
        email.setForeground(Main.TEXT);
        emailTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
        emailTextField.setForeground(Main.TEXT);
        darkModeCheckBox.setForeground(Main.TEXT);
        saveButton.setForeground(Main.TEXT);
        saveButton.setBackground(Main.BUTTON_BACKGROUND);
        importSettingsButton.setForeground(Main.TEXT);
        importSettingsButton.setBackground(Main.BUTTON_BACKGROUND);
        exportSettingsButton.setForeground(Main.TEXT);
        exportSettingsButton.setBackground(Main.BUTTON_BACKGROUND);
        try {
             if (Main.userSettings.getDarkMode()) {
                darkModeCheckBox.setIcon(new ImageIcon(resizedCheckBoxDarkUnchecked));
                darkModeCheckBox.setSelectedIcon(new ImageIcon(resizedCheckBoxDarkChecked));
            }
            else {
                darkModeCheckBox.setIcon(new ImageIcon(resizedCheckBoxLightUnchecked));
                darkModeCheckBox.setSelectedIcon(new ImageIcon(resizedCheckBoxLightChecked));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}