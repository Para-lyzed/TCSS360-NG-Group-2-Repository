package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import model.DataIO;
import model.Main;
import model.Project;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectExpenseScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectExpenseScreen extends BaseScreen {
    
    private static final String title = "Expenses";
    private static final JFileChooser fileChooser = new JFileChooser();
    private CustomButton saveButton = new CustomButton("Save");
    private Project project;
    private String budgetString;
    private JLabel budgetLabel;
    private ProjectExpensePanel expensesPanel;

    /**
     * Creates a screen where the user can view Expenses of a Project.
     * 
     * @param width of the panel.
     * @param height of the panel.
     * @param project the user is editing.
     * 
     * @author Nathan Grimsey
     */
    public ProjectExpenseScreen(int width, int height, Project project) {
        super(width, height, title, 1);
        this.project = project;
        budgetString = " / $" + project.getBudget();
        budgetLabel = new JLabel("$" + project.getTotalCost() + budgetString);
        budgetLabel.setFont(Main.HEADING_ONE_FONT);
        budgetLabel.setForeground(Main.TEXT);
        c.gridx = 1;
        add(budgetLabel, c);
        expensesPanel = new ProjectExpensePanel(project, this);
        expensesPanel.setBorder(Main.BORDER);
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 20;
        add(expensesPanel, c);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setSelectedFile(new File(project.getName() + ".proj"));
                int returnVal;
                returnVal = fileChooser.showSaveDialog(Main.BASE_FRAME);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String exportPathString = fileChooser.getSelectedFile().toPath().toString();
                    if (!exportPathString.endsWith(".proj")) {
                        exportPathString += ".proj";
                    }
                    DataIO.saveProject(project, Paths.get(exportPathString));
                }
            }
        });
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.weighty = 1;
        add(saveButton, c);
    }

    /**
     * Refreshes the budget label.
     * 
     * @author Nathan Grimsey
     */
    public void refreshBudget() {
        budgetLabel.setText("$" + project.getTotalCost() + " / $" + project.getBudget());
    }

}
