package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import model.DataIO;
import model.Main;
import model.Project;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectLogScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectLogScreen extends BaseScreen {
    
    private static final String title = "Logs";
    private static final JFileChooser fileChooser = new JFileChooser();
    private CustomButton saveButton = new CustomButton("Save");
    private ProjectLogPanel logPanel;

    /**
     * Creates a screen where the user can view Logs of a Project.
     * 
     * @param width of the panel.
     * @param height of the panel.
     * @param project the user is editing.
     * 
     * @author Nathan Grimsey
     */
    public ProjectLogScreen(int width, int height, Project project) {
        super(width, height, title, 1);
        logPanel = new ProjectLogPanel(project);
        logPanel.setBorder(Main.BORDER);
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 20;
        add(logPanel, c);
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

}
