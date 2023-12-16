package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.UIManager;

import model.Expense;
import model.Main;
import model.Project;
import model.Tool;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectToolPanel.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectToolPanel extends ProjectSecondaryPanelTemplate {
    private static final String addButtonName = "Add Existing Tool";
    private static final CustomButton createButton = new CustomButton("Create New Tool");
    private static final JLabel nameLabel = new JLabel("Name");
    private static final JLabel priceLabel = new JLabel("Price");
    private static final JLabel expenseLabel = new JLabel("Expense");
    private static final JLabel spacerLabel = new JLabel("                       ");
    private static final JLabel spacerLabelTwo = new JLabel("                       ");
    private LinkedList<Tool> projectTools;
    private int rowCount = 0;
    private Project project;

    /**
     * Displays a scrollable list of Tools in a Project.
     * 
     * @param project the user is editing.
     * 
     * @author Nathan Grimsey
     */
    public ProjectToolPanel(Project project) {
        super(addButtonName);
        this.project = project;
        projectTools = project.getTools();
        nameLabel.setFont(Main.TABLE_FONT);
        nameLabel.setForeground(Main.TEXT);
        expenseLabel.setFont(Main.TABLE_FONT);
        expenseLabel.setForeground(Main.TEXT);
        priceLabel.setFont(Main.TABLE_FONT);
        priceLabel.setForeground(Main.TEXT);
        c.gridx = 1;
        c.gridy = 1;
        add(createButton, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 8;
        c.weighty = 1;
        contentPanel.add(nameLabel, c);
        c.gridx++;
        c.weightx = 3;
        contentPanel.add(priceLabel, c);
        c.gridx++;
        c.weightx = 1;
        contentPanel.add(expenseLabel, c);
        c.gridx++;
        c.weightx = 0;
        spacerLabel.setFont(Main.TABLE_FONT);
        contentPanel.add(spacerLabel, c);
        c.gridx++;
        spacerLabelTwo.setFont(Main.TABLE_FONT);
        contentPanel.add(spacerLabelTwo, c);
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 10;
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BASE_FRAME.openProjectToolSelect(false);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BASE_FRAME.switchScreen("Create a New Tool");
            }
        });
        load();
    }

    /**
     * Loads rows of Tools from Project.
     * 
     * @author Nathan Grimsey
     */
    private void load() {
        for (int i = 0; i < projectTools.size(); i++) {
            Tool tool = projectTools.get(i);
            ProjectEntryRow entryRow = new ProjectEntryRow(tool);
            addRow(entryRow, tool);
        }
    }

    /**
     * Adds a single row to the Expense list.
     * 
     * @param entryRow the row to add.
     * @param tool the Tool associated with the row.
     * 
     * @author Nathan Grimsey
     */
    private void addRow(ProjectEntryRow entryRow, Tool tool) {
        rowCount++;
        c.gridx = 0;
        c.gridy++;
        c.weightx = 8;
        c.fill = GridBagConstraints.NONE;
        contentPanel.add(entryRow.toolName, c);
        c.gridx++;
        c.weightx = 3;
        contentPanel.add(entryRow.toolPrice, c);
        c.gridx++;
        c.weightx = 1;
        contentPanel.add(entryRow.checkBox, c);
        c.gridx++;
        c.weightx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(entryRow.deleteButton, c);
        c.gridx++;
        entryRow.updateButton.setText("Edit");
        contentPanel.add(entryRow.updateButton, c);
        contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth() - ((Integer)UIManager.get("ScrollBar.width")).intValue(), 36 * (rowCount + 2)));
        contentPanel.setSize(getPreferredSize());
        if (project.hasToolAsExpense(tool)) {
            entryRow.checkBox.setSelected(true);
        }
        entryRow.checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (entryRow.checkBox.isSelected()) {
                    project.addExpense(tool, 1);
                }
                else {
                    Expense expense = tool;
                    project.removeExpense(expense);
                }
            }
        });
        entryRow.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BASE_FRAME.openTool(tool);
            }
        });
        entryRow.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowCount--;
                if (tool != null) {
                    project.removeTool(tool);
                }
                contentPanel.remove(entryRow.toolName);
                contentPanel.remove(entryRow.toolPrice);
                contentPanel.remove(entryRow.checkBox);
                contentPanel.remove(entryRow.updateButton);
                contentPanel.remove(entryRow.deleteButton);
                contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth(), 36 * (rowCount + 2)));
                contentPanel.setSize(getPreferredSize());
            }
        });
    }

}
