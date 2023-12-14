package model;

import java.awt.GridBagConstraints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.NewScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class NewScreen extends BaseScreen {

    protected static final int gridWidth = 6;
    protected static final JLabel nameLabel = new JLabel("Name*");
    protected JTextField nameTextField = new JTextField();
    protected JLabel fieldTwoLabel;
    protected JTextField fieldTwoTextField = new JTextField();
    protected static final JLabel descriptionLabel = new JLabel("Description");
    protected JTextField descriptionTextField = new JTextField();
    protected static final JLabel requiredFieldsLabel = new JLabel("* Required fields");
    protected static final JButton saveButton = new JButton("Save");
    protected static final JButton cancelButton = new JButton("Cancel");

    public NewScreen(int width, int height, String title, int firstFieldsWidth, String fieldTwoString) {
        super(width, height, title, gridWidth);
        menuHeading(true);
        fieldTwoLabel = new JLabel(fieldTwoString);
        nameLabel.setFont(Main.HEADING_TWO_FONT);
        nameTextField.setFont(Main.BASE_FONT);
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
        fieldTwoTextField.setFont(Main.BASE_FONT);
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
        descriptionTextField.setFont(Main.BASE_FONT);
        requiredFieldsLabel.setFont(Main.BASE_FONT);
        saveButton.setFont(Main.BASE_FONT);
        cancelButton.setFont(Main.BASE_FONT);
        this.c.fill = GridBagConstraints.BOTH;
        this.c.gridy++;
        this.c.gridwidth = firstFieldsWidth;
        add(nameLabel, c);
        this.c.gridx = firstFieldsWidth;
        add(fieldTwoLabel, c);
        this.c.gridx = 0;
        this.c.gridy++;
        add(nameTextField, c);
        this.c.gridx = firstFieldsWidth;
        add(fieldTwoTextField, c);
        this.c.gridwidth = gridWidth;
        this.c.gridx = 0;
        this.c.gridy++;
        add(descriptionLabel, c);
        this.c.gridy++;
        add(descriptionTextField, c);
        this.c.gridy++;
        add(requiredFieldsLabel, c);
        this.c.gridy++;
        this.c.fill = GridBagConstraints.NONE;
        this.c.gridwidth = 1;
        add(saveButton, c);
        this.c.gridx++;
        add(cancelButton, c);
    }

    protected void inputError() {
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setBackground(Main.TEXT_ERROR);
        }
        if (fieldTwoTextField.getText().isEmpty()) {
            fieldTwoTextField.setBackground(Main.TEXT_ERROR);
        }
    }

}
