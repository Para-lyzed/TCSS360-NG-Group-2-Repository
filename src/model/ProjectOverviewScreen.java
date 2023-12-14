package model;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * ProjectScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectOverviewScreen extends BaseScreen {
    
    private static final String title = "Overview";
    private static final JLabel expensesLabel = new JLabel("Expenses");
    private static final JLabel toolsLabel = new JLabel("Tools");
    private static final JLabel logsLabel = new JLabel("Logs");
    private static final JFileChooser fileChooser = new JFileChooser();
    private JButton saveButton = new JButton("Save");
    private Project loadedProject;
    private String budgetString;
    private JLabel budgetLabel;
    private ProjectExpensePanel expensesPanel;
    private ProjectToolPanel toolsPanel;
    private ProjectLogPanel logsPanel;

    public ProjectOverviewScreen(int width, int height, Project project) {
        super(width, height, title, 2);
        loadedProject = project;
        budgetString = " / $" + loadedProject.getBudget();
        budgetLabel = new JLabel("$" + loadedProject.getTotalCost() + budgetString);
        budgetLabel.setFont(Main.HEADING_ONE_FONT);
        c.gridx = 2;
        add(budgetLabel, c);
        expensesLabel.setFont(Main.HEADING_TWO_FONT);
        c.gridx = 0;
        c.gridy++;
        add(expensesLabel, c);
        expensesPanel = new ProjectExpensePanel(loadedProject, this);
        c.gridy++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 20;
        add(expensesPanel, c);
        toolsLabel.setFont(Main.HEADING_TWO_FONT);
        c.gridy++;
        c.fill = GridBagConstraints.NONE;
        c.weighty = 1;
        add(toolsLabel, c);
        toolsPanel = new ProjectToolPanel();
        c.gridy++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 20;
        add(toolsPanel, c);
        logsLabel.setFont(Main.HEADING_TWO_FONT);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.weighty = 1;
        add(logsLabel, c);
        logsPanel = new ProjectLogPanel();
        c.gridy++;
        c.gridwidth = 2;
        c.gridheight = 3;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 40;
        add(logsPanel, c);
        saveButton.setFont(Main.BASE_FONT);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setSelectedFile(new File(loadedProject.getName() + ".proj"));
                int returnVal;
                returnVal = fileChooser.showSaveDialog(Main.BASE_FRAME);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String exportPathString = fileChooser.getSelectedFile().toPath().toString();
                    if (!exportPathString.endsWith(".proj")) {
                        exportPathString += ".proj";
                    }
                    DataIO.saveProject(loadedProject, Paths.get(exportPathString));
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

    public void refreshBudget() {
        budgetLabel.setText("$" + loadedProject.getTotalCost() + " / $" + loadedProject.getBudget());
    }

}
