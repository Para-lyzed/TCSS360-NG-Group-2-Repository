package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectSecondaryPanelTemplate.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectSecondaryPanelTemplate extends JPanel {
    protected GridBagConstraints c;
    protected JScrollPane contentScrollPane;
    protected JPanel contentPanel;
    protected CustomButton addButton;

    /**
     * Parent class for the Budget, Log, and Tool panels in the Project view.
     * 
     * @param addButtonName the String name of the button that states "Add <element>".
     * 
     * @author Nathan Grimsey
     */
    public ProjectSecondaryPanelTemplate(String addButtonName) {
        setBackground(Main.SECONDARY_BACKGROUND);
        setOpaque(true);
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        contentPanel = new JPanel();
        contentPanel.setBackground(Main.SECONDARY_BACKGROUND);
        contentPanel.setOpaque(true);
        contentPanel.setLayout(new GridBagLayout());
        contentScrollPane = new JScrollPane();
        contentScrollPane.setViewportView(contentPanel);
        contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JButton emptyButton = new JButton();
        emptyButton.setPreferredSize(new Dimension(0, 0));
        contentScrollPane.getVerticalScrollBar().setUI(Main.SCROLL_BAR);
        
        contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth(), contentScrollPane.getHeight()));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 10;
        c.weighty = 20;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        add(contentScrollPane, c);
        addButton = new CustomButton(addButtonName);
        c.gridy++;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        add(addButton, c);
    }
}