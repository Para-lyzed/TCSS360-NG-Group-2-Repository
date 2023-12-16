package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DataIO;
import model.Main;
import model.Project;
import model.UserSettings;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectMaterialSelectScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectMaterialSelectScreen extends BaseSelectorScreen {

    /**
     * Allows the user to add Materials into the Expenses screen.
     * 
     * @param width of the panel.
     * @param height of the panel.
     * @param project the user is editing.
     * 
     * @author Nathan Grimsey
     */
    public ProjectMaterialSelectScreen(int width, int height, Project project) {
        super(width, height, "Import Material", "Material", UserSettings.MATERIAL, "mat");
        remove(deleteButton);
        remove(importButton);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String materialName = listPane.getSelectedValue();
                try {
                    project.addExpense(DataIO.loadMaterial(Main.userSettings.getFilePathFromName(materialName, UserSettings.MATERIAL)), 1);
                }
                catch (Exception error) {
                    Main.userSettings.removeFromRecent(materialName, UserSettings.MATERIAL);
                    error.printStackTrace();
                }
                Main.BASE_FRAME.openProjectExpenses(false);
            }
        });
    }
}
