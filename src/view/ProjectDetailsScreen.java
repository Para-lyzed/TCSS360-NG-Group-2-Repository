package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import model.DataIO;
import model.Main;
import model.Project;
import model.UserSettings;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectDetailsScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectDetailsScreen extends NewScreen {
    private static final String title = "Details";
    private static final JFileChooser fileChooser = new JFileChooser();

    public ProjectDetailsScreen(int width, int height, Project project) {
        super(width, height, title, 3, "Budget*");
        menuHeading(false);
        nameTextField.setText(project.getName());
        fieldTwoTextField.setText(String.valueOf(project.getBudget()));
        String description = project.getDescription();
        if (description != null) {
            descriptionTextField.setText(description);
        }
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldTwoTextField.fireAction();
                String oldName = project.getName();
                String name = nameTextField.getText();
                String budgetString = fieldTwoTextField.getText();
                String newDescription = descriptionTextField.getText();
                if (!name.isEmpty() && !budgetString.isEmpty()) {
                    int budget = 0;
                    try {
                        budget = Integer.parseInt(budgetString);
                    }
                    catch (Exception error) {

                    }
                    project.setName(name);
                    project.setBudget(budget);
                    if (!newDescription.isEmpty()) {
                        project.setDescription(newDescription);
                    }
                    fileChooser.setSelectedFile(new File(Main.userSettings.getFilePathFromName(oldName, UserSettings.PROJECT).toString()));
                    int returnVal;
                    returnVal = fileChooser.showSaveDialog(Main.BASE_FRAME);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String exportPathString = fileChooser.getSelectedFile().toPath().toString();
                        if (!exportPathString.endsWith(".proj")) {
                            exportPathString += ".proj";
                        }
                        Main.userSettings.removeFromRecent(oldName, UserSettings.PROJECT);
                        DataIO.saveProject(project, Path.of(exportPathString));
                    }
                }
                else {
                    inputError();
                }
            }
        });
        remove(cancelButton);
    }

}
