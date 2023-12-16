package view;

import java.awt.GridBagConstraints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

import javax.swing.JLabel;

import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.NewScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class NewScreen extends BaseScreen {

    protected static final int gridWidth = 6;
    protected static final JLabel nameLabel = new JLabel("Name*");
    protected CustomTextField nameTextField = new CustomTextField();
    protected JLabel fieldTwoLabel;
    protected CustomTextField fieldTwoTextField = new CustomTextField(NumberFormat.getIntegerInstance());
    protected static final JLabel descriptionLabel = new JLabel("Description");
    protected CustomTextField descriptionTextField = new CustomTextField();
    protected static final JLabel requiredFieldsLabel = new JLabel("* Required fields");
    protected final CustomButton saveButton = new CustomButton("Save");
    protected final CustomButton cancelButton = new CustomButton("Cancel");

    /**
     * Parent class for all New Screens which allow users to create new elements.
     * 
     * @param width of the panel.
     * @param height of the panel. 
     * @param title of the panel.
     * @param firstFieldWidth how maniy grid spaces the first fields should cover.
     * @param fieldTwoString the name of field 2.
     * 
     * @author Nathan Grimsey
     */
    public NewScreen(int width, int height, String title, int firstFieldWidth, String fieldTwoString) {
        super(width, height, title, gridWidth);
        menuHeading(true);
        fieldTwoLabel = new JLabel(fieldTwoString);
        nameLabel.setFont(Main.HEADING_TWO_FONT);
        nameLabel.setForeground(Main.TEXT);
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nameTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        fieldTwoLabel.setFont(Main.HEADING_TWO_FONT);
        fieldTwoLabel.setForeground(Main.TEXT);
        fieldTwoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldTwoTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        descriptionLabel.setFont(Main.HEADING_TWO_FONT);
        descriptionLabel.setForeground(Main.TEXT);
        requiredFieldsLabel.setFont(Main.BASE_FONT);
        requiredFieldsLabel.setForeground(Main.TEXT);
        c.fill = GridBagConstraints.BOTH;
        c.gridy++;
        c.gridwidth = firstFieldWidth;
        add(nameLabel, c);
        c.gridx = firstFieldWidth;
        add(fieldTwoLabel, c);
        c.gridx = 0;
        c.gridy++;
        add(nameTextField, c);
        c.gridx = firstFieldWidth;
        add(fieldTwoTextField, c);
        c.gridwidth = gridWidth;
        c.gridx = 0;
        c.gridy++;
        add(descriptionLabel, c);
        c.gridy++;
        add(descriptionTextField, c);
        c.gridy++;
        add(requiredFieldsLabel, c);
        c.gridy++;
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        add(saveButton, c);
        c.gridx++;
        add(cancelButton, c);
    }

    /**
     * Triggered when user does not enter all necessary fields. Highlights invalid 
     * fields in red.
     * 
     * @author Nathan Grimsey
     */
    protected void inputError() {
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setBackground(Main.TEXT_ERROR);
        }
        if (fieldTwoTextField.getText().isEmpty()) {
            fieldTwoTextField.setBackground(Main.TEXT_ERROR);
        }
    }

}
