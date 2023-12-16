package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import model.DataIO;
import model.Main;
import model.Material;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.NewMaterialScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class NewMaterialScreen extends NewScreen {
    private static final String title = "Create a New Material";
    private static final JLabel categoryLabel = new JLabel("Category*");
    private final JFileChooser fileChooser = new JFileChooser();
    private CustomTextField categoryTextField = new CustomTextField();

    /**
     * Allows the user to create a new Material.
     * 
     * @param width of the panel.
     * @param height of the panel.
     * @param inProject true if the user is in the project view
     * 
     * @author Nathan Grimsey
     */
    public NewMaterialScreen(int width, int height, boolean inProject) {
        super(width, height, title, 2, "Price*");
        categoryLabel.setFont(Main.HEADING_TWO_FONT);
        categoryLabel.setForeground(Main.TEXT);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 2;
        add(categoryLabel, c);
        c.gridy++;
        add(categoryTextField, c);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldTwoTextField.fireAction();
                String name = nameTextField.getText();
                String priceString = fieldTwoTextField.getText();
                String category = categoryTextField.getText();
                String description = descriptionTextField.getText();
                if (!name.isEmpty() && !priceString.isEmpty() && !category.isEmpty()) {
                    int price = 0;
                    try {
                        price = Integer.parseInt(priceString);
                    }
                    catch (Exception error) {

                    }
                    Material newMaterial = new Material(name, category, price);
                    if (!description.isEmpty()) {
                        newMaterial.setDescription(description);
                    }
                    fileChooser.setSelectedFile(new File(name + ".mat"));
                    int returnVal;
                    returnVal = fileChooser.showSaveDialog(Main.BASE_FRAME);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String exportPathString = fileChooser.getSelectedFile().toPath().toString();
                        if (!exportPathString.endsWith(".mat")) {
                            exportPathString += ".mat";
                        }
                        DataIO.saveMaterial(newMaterial, Path.of(exportPathString));
                    }
                    if (inProject) {
                        Main.BASE_FRAME.addNewMaterialToProject(newMaterial);
                        Main.BASE_FRAME.openProjectExpenses(true);
                    }
                    else {
                        Main.BASE_FRAME.switchScreen("Materials");
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
                    Main.BASE_FRAME.openProjectExpenses(true);
                }
                else {
                    Main.BASE_FRAME.switchScreen("Materials");
                }
            }
        });
    }

    @Override
    protected void inputError() {
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setBackground(Main.TEXT_ERROR);
        }
        if (fieldTwoTextField.getText().isEmpty()) {
            fieldTwoTextField.setBackground(Main.TEXT_ERROR);
        }
        if (categoryTextField.getText().isEmpty()) {
            categoryTextField.setBackground(Main.TEXT_ERROR);
        }
    }

}
