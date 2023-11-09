import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * Menu.java
 * 
 * @author Nathan Grimsey
 *
 */
public class Menu extends JPanel {
    private static String[] mainEntries = {"Projects", "Tools", "Materials", "Settings", "About"};
    private static String[] projectEntries = {"Back to main menu", "Overview", "Details", "Budget", "Schedule", "Project Settings"};
    private static int menuIconSize = Main.menuWidth/7;
    private BaseFrame hostFrame;
    private String[] entries;
    private GridBagConstraints c;
    private JButton menuButton;
    private JButton backButton;
    private ArrayList<JButton> menuEntries;

    public Menu(boolean isMainMenu, int minHeight, BaseFrame hostFrame) {
        this.hostFrame = hostFrame;
        if (isMainMenu) {
            this.entries = mainEntries;
        }
        else {
            this.entries = projectEntries;
        }
        this.setBackground(Color.GRAY);
        this.setBounds(0, 0, Main.menuWidth, minHeight);
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        this.menuEntries = new ArrayList<>(this.entries.length);
        this.c = new GridBagConstraints();
        this.c.insets = Main.menuInsets;
        this.c.weightx = 1;
        this.c.weighty = 1;
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.anchor = GridBagConstraints.NORTHWEST;
        this.menuButton = new JButton();
        this.backButton = new JButton();
        this.add(this.menuButton, this.c);
        try {
            Image menuImage = ImageIO.read(getClass().getResource("icons/Menu.png"));
            Image resizedMenuImage = menuImage.getScaledInstance(menuIconSize, menuIconSize, Image.SCALE_SMOOTH);
            this.menuButton.setIcon(new ImageIcon(resizedMenuImage));
            Image backImage = ImageIO.read(getClass().getResource("icons/Back.png"));
            Image resizedBackImage = backImage.getScaledInstance(menuIconSize, menuIconSize, Image.SCALE_SMOOTH);
            this.backButton.setIcon(new ImageIcon(resizedBackImage));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.menuButton.setBorder(null);
        this.menuButton.setBorderPainted(false);
        this.menuButton.setContentAreaFilled(false);
        this.menuButton.setFocusPainted(false);
        this.menuButton.setOpaque(false);
        this.backButton.setBorder(null);
        this.backButton.setBorderPainted(false);
        this.backButton.setContentAreaFilled(false);
        this.backButton.setFocusPainted(false);
        this.backButton.setOpaque(false);
        // Font menuFont = new Font("Arial", Font.BOLD, 32);
        for (int i = 0; i < this.entries.length; i++) {
            JButton entry = new JButton(this.entries[i]);
            entry.setForeground(Color.BLACK);
            entry.setBorder(null);
            entry.setBorderPainted(false);
            entry.setContentAreaFilled(false);
            entry.setFocusPainted(false);
            entry.setOpaque(false);
            entry.setFont(Main.menuFont);
            entry.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    entryPressed(entry.getText());
                }
            });
            this.menuEntries.add(entry);
        }
        JButton spacer = new JButton();
        spacer.setBorder(null);
        spacer.setBorderPainted(false);
        spacer.setContentAreaFilled(true);
        spacer.setFocusPainted(false);
        spacer.setOpaque(true);
        // Font spacerFont = new Font("Arial", Font.BOLD, 75);
        spacer.setFont(Main.spacerFont);
        this.menuEntries.add(spacer);
        this.menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPressed();
            }
        });
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMenu();
            }
        });
        this.c.gridy = 0;
    }

    private void menuPressed() {
        this.hostFrame.menuOpen(true);
        this.remove(this.menuButton);
        this.add(this.backButton, this.c);
        for (int i = 0; i < this.menuEntries.size(); i++) {
            this.c.gridy++;
            this.add(this.menuEntries.get(i), this.c);
        }
        this.c.gridy = 0;
        this.setOpaque(true);
        this.hostFrame.pack();
        this.hostFrame.repaint();
    }

    private void closeMenu() {
        this.hostFrame.menuOpen(false);
        this.remove(this.backButton);
        this.add(this.menuButton, this.c);
        for (int i = 0; i < this.menuEntries.size(); i++) {
            this.c.gridy++;
            this.remove(this.menuEntries.get(i));
        }
        this.c.gridy = 0;
        this.setOpaque(false);
        this.hostFrame.pack();
        this.hostFrame.repaint();
    }

    private void entryPressed(String entryName) {
        this.hostFrame.switchScreen(entryName);
        closeMenu();
    }
}
