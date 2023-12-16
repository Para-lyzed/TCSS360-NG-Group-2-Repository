package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import model.DataIO;
import model.Main;
import model.Tool;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.NewToolScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class NewToolScreen extends NewScreen {
    private static final String title = "Create a New Tool";
    private final JFileChooser fileChooser = new JFileChooser();

    public NewToolScreen(int width, int height, boolean inProject) {
        super(width, height, title, 3, "Price*");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldTwoTextField.fireAction();
                String name = nameTextField.getText();
                String priceString = fieldTwoTextField.getText();
                String description = descriptionTextField.getText();
                if (!name.isEmpty() && !priceString.isEmpty()) {
                    int price = ((Number) fieldTwoTextField.getValue()).intValue();
                    Tool newTool = new Tool(name, price);
                    if (!description.isEmpty()) {
                        newTool.setDescription(description);
                    }
                    fileChooser.setSelectedFile(new File(name + ".tool"));
                    int returnVal;
                    returnVal = fileChooser.showSaveDialog(Main.BASE_FRAME);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String exportPathString = fileChooser.getSelectedFile().toPath().toString();
                        if (!exportPathString.endsWith(".tool")) {
                            exportPathString += ".tool";
                        }
                        DataIO.saveTool(newTool, Path.of(exportPathString));
                    }
                    if (inProject) {
                        Main.BASE_FRAME.addNewToolToProject(newTool);
                        Main.BASE_FRAME.openProjectTools(true);
                    }
                    else {
                        Main.BASE_FRAME.switchScreen("Tools");
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
                    Main.BASE_FRAME.resetToProjects();
                }
            }
        });
    }

}
