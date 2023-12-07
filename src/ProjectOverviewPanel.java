import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * ProjectOverviewPanel.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectOverviewPanel extends JPanel {
    protected GridBagConstraints c;
    protected JScrollPane contentScrollPane;
    protected JPanel contentPanel;
    protected JButton addButton;
    protected JButton updateButton;

    public ProjectOverviewPanel(String addButtonName) {
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
        contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth(), contentScrollPane.getHeight()));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 10;
        c.weighty = 20;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        add(contentScrollPane, c);
        addButton = new JButton(addButtonName);
        addButton.setFont(Main.TABLE_FONT);
        c.gridy++;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        add(addButton, c);
        updateButton = new JButton("Update");
        updateButton.setFont(Main.TABLE_FONT);
    }
}