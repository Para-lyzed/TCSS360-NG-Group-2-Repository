package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DataIO;
import model.Main;
import model.Project;
import model.UserSettings;

public class ProjectMaterialSelectScreen extends BaseSelectorScreen {

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
