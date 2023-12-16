package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.Menu.java
 * 
 * @author Nathan Grimsey
 *
 */
public class Menu extends JPanel {
    private static String[] mainEntries = {"Projects", "Tools", "Materials", "Settings", "About"};
    private static String[] projectEntries = {"Back to main menu", "Expenses", "Project Tools", "Logs", "Details"}; //, "Budget", "Project Settings"};
    private static int menuIconSize = Main.MENU_WIDTH / 7;
    private static Image darkMenuImage;
    private static Image darkBackImage;
    private static Image darkResizedMenuImage;
    private static Image darkResizedBackImage;
    private static Image lightMenuImage;
    private static Image lightBackImage;
    private static Image lightResizedMenuImage;
    private static Image lightResizedBackImage;
    private int menuHeight;
    private String[] entries;
    private GridBagConstraints c;
    private JButton menuButton;
    private JButton backButton;
    private ArrayList<JButton> menuEntries;

    /**
     * Creates a menu by which the user is able to navigate to another page.
     * 
     * @param isMainMenu true if the menu is on the main screen, else false if it is a Project menu.
     * @param height the initial height of the menu.
     * 
     * @author Nathan Grimsey
     */
    public Menu(boolean isMainMenu, int height) {
        menuHeight = height;
        if (isMainMenu) {
            entries = mainEntries;
        }
        else {
            entries = projectEntries;
        }
        setBackground(Main.MENU_BACKGROUND);
        setBounds(0, 0, Main.MENU_WIDTH, menuHeight);
        setOpaque(false);
        setLayout(new GridBagLayout());
        menuEntries = new ArrayList<>(entries.length);
        c = new GridBagConstraints();
        c.insets = Main.MENU_INSETS;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        menuButton = new JButton();
        backButton = new JButton();
        add(menuButton, c);
        try {
            darkMenuImage = ImageIO.read(getClass().getResource("/icons/DarkMenu.png"));
            darkBackImage = ImageIO.read(getClass().getResource("/icons/DarkBack.png"));
            lightMenuImage = ImageIO.read(getClass().getResource("/icons/LightMenu.png"));
            lightBackImage = ImageIO.read(getClass().getResource("/icons/LightBack.png"));
            darkResizedMenuImage = darkMenuImage.getScaledInstance(menuIconSize, menuIconSize, Image.SCALE_SMOOTH);
            darkResizedBackImage = darkBackImage.getScaledInstance(menuIconSize, menuIconSize, Image.SCALE_SMOOTH);
            lightResizedMenuImage = lightMenuImage.getScaledInstance(menuIconSize, menuIconSize, Image.SCALE_SMOOTH);
            lightResizedBackImage = lightBackImage.getScaledInstance(menuIconSize, menuIconSize, Image.SCALE_SMOOTH);
            if (Main.userSettings.getDarkMode()) {
                menuButton.setIcon(new ImageIcon(darkResizedMenuImage));
                backButton.setIcon(new ImageIcon(darkResizedBackImage));
            }
            else {
                menuButton.setIcon(new ImageIcon(lightResizedMenuImage));
                backButton.setIcon(new ImageIcon(lightResizedBackImage));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        menuButton.setBorder(null);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setOpaque(false);
        backButton.setBorder(null);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        for (int i = 0; i < entries.length; i++) {
            JButton entry = new JButton(entries[i]);
            entry.setForeground(Main.TEXT);
            entry.setBorder(null);
            entry.setBorderPainted(false);
            entry.setContentAreaFilled(false);
            entry.setFocusPainted(false);
            entry.setOpaque(false);
            entry.setFont(Main.MENU_FONT);
            entry.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    entryPressed(entry.getText());
                }
            });
            menuEntries.add(entry);
        }
        JButton spacer = new JButton();
        spacer.setBorder(null);
        spacer.setBorderPainted(false);
        spacer.setContentAreaFilled(true);
        spacer.setFocusPainted(false);
        spacer.setOpaque(true);
        spacer.setFont(Main.SPACER_FONT);
        menuEntries.add(spacer);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPressed();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMenu();
            }
        });
        c.gridy = 0;
    }

    /**
     * Handles the UI elements to open menu when the menu button is pressed.
     * 
     * @author Nathan Grimsey
     */
    public void menuPressed() {
        Main.BASE_FRAME.menuOpen(true);
        remove(menuButton);
        add(backButton, c);
        for (int i = 0; i < menuEntries.size(); i++) {
            c.gridy++;
            add(menuEntries.get(i), c);
        }
        c.gridy = 0;
        setOpaque(true);
        Main.BASE_FRAME.repaint();
    }

    /**
     * Handles the UI elements to close menu when the back button is pressed.
     * 
     * @author Nathan Grimsey
     */
    public void closeMenu() {
        Main.BASE_FRAME.menuOpen(false);
        remove(backButton);
        add(menuButton, c);
        for (int i = 0; i < menuEntries.size(); i++) {
            c.gridy++;
            remove(menuEntries.get(i));
        }
        c.gridy = 0;
        setOpaque(false);
        Main.BASE_FRAME.repaint();
    }

    /**
     * Changes the current screen when a menu entry is pressed.
     * 
     * @param entryName name of the menu entry pressed.
     * 
     * @author Nathan Grimsey
     */
    private void entryPressed(String entryName) {
        if (!Main.BASE_FRAME.titleEqual(entryName)) {
            if (entryName.equals("Back to main menu")) {
                Main.BASE_FRAME.resetToProjects();
            }
            else if (entryName.equals("Details")) {
                Main.BASE_FRAME.openProjectDetails(false);
            }
            else if (entryName.equals("Project Tools")) {
                Main.BASE_FRAME.openProjectTools(false);
            }
            else if (entryName.equals("Expenses")) {
                Main.BASE_FRAME.openProjectExpenses(false);
            }
            else if (entryName.equals("Logs")) {
                Main.BASE_FRAME.openProjectLogs(false);
            }
            else {
                Main.BASE_FRAME.switchScreen(entryName);
            }
        }
        closeMenu();
    }

    /**
     * Sets the height of the menu.
     * 
     * @param height the height to set the menu to.
     * 
     * @author Nathan Grimsey
     */
    public void setHeight(int height) {
        menuHeight = height;
        setBounds(0, 0, Main.MENU_WIDTH, menuHeight);
    }

    public void darkMode() {
        try {
            if (Main.userSettings.getDarkMode()) {
                menuButton.setIcon(new ImageIcon(darkResizedMenuImage));
                backButton.setIcon(new ImageIcon(darkResizedBackImage));
            }
            else {
                menuButton.setIcon(new ImageIcon(lightResizedMenuImage));
                backButton.setIcon(new ImageIcon(lightResizedBackImage));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < menuEntries.size(); i++) {
            menuEntries.get(i).setForeground(Main.TEXT);
        }
        setBackground(Main.MENU_BACKGROUND);
    }
}
