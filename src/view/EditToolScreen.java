package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import model.DataIO;
import model.Main;
import model.Tool;
import model.UserSettings;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.EditToolScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class EditToolScreen extends NewScreen {
    private static final String title = "Edit Tool";
    private static final JFileChooser fileChooser = new JFileChooser();

    /**
     * A screen for the user to edit an existing tool.
     * 
     * @param width of the panel.
     * @param height of the panel.
     * @param tool to edit.
     */
    public EditToolScreen(int width, int height, Tool tool, boolean inProject) {
        super(width, height, title, 3, "Price*");
        nameTextField.setText(tool.getName());
        fieldTwoTextField.setText(String.valueOf(tool.getPrice()));
        String description = tool.getDescription();
        if (description != null) {
            descriptionTextField.setText(description);
        }
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldTwoTextField.fireAction();
                String oldName = tool.getName();
                String name = nameTextField.getText();
                String priceString = fieldTwoTextField.getText();
                String newDescription = descriptionTextField.getText();
                if (!name.isEmpty() && !priceString.isEmpty()) {
                    int price = ((Number) fieldTwoTextField.getValue()).intValue();
                    tool.setName(name);
                    tool.setPrice(price);
                    if (!newDescription.isEmpty()) {
                        tool.setDescription(newDescription);
                    }
                    fileChooser.setSelectedFile(new File(Main.userSettings.getFilePathFromName(oldName, UserSettings.TOOL).toString()));
                    int returnVal;
                    returnVal = fileChooser.showSaveDialog(Main.BASE_FRAME);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String exportPathString = fileChooser.getSelectedFile().toPath().toString();
                        if (!exportPathString.endsWith(".tool")) {
                            exportPathString += ".tool";
                        }
                        Main.userSettings.removeFromRecent(oldName, UserSettings.TOOL);
                        DataIO.saveTool(tool, Path.of(exportPathString));
                    }
                    if (inProject) {
                        Main.BASE_FRAME.openProjectTools(true);
                    }
                    else {
                        Main.BASE_FRAME.resetToTools();
                    }
                }
                else {
                    inputError();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inProject) {
                        Main.BASE_FRAME.openProjectTools(true);
                    }
                    else {
                        Main.BASE_FRAME.resetToTools();
                    }
            }
        });
    }

}
