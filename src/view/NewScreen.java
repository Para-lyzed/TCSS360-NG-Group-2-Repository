package view;

import java.awt.GridBagConstraints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
    protected JTextField nameTextField = new JTextField();
    protected JLabel fieldTwoLabel;
    protected JTextField fieldTwoTextField = new JTextField();
    protected static final JLabel descriptionLabel = new JLabel("Description");
    protected JTextField descriptionTextField = new JTextField();
    protected static final JLabel requiredFieldsLabel = new JLabel("* Required fields");
    protected final JButton saveButton = new JButton("Save");
    protected final JButton cancelButton = new JButton("Cancel");

    public NewScreen(int width, int height, String title, int firstFieldWidth, String fieldTwoString) {
        super(width, height, title, gridWidth);
        menuHeading(true);
        fieldTwoLabel = new JLabel(fieldTwoString);
        nameLabel.setFont(Main.HEADING_TWO_FONT);
        nameLabel.setForeground(Main.TEXT);
        nameTextField.setFont(Main.BASE_FONT);
        nameTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
        nameTextField.setForeground(Main.TEXT);
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
        fieldTwoTextField.setFont(Main.BASE_FONT);
        fieldTwoTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
        fieldTwoTextField.setForeground(Main.TEXT);
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
        descriptionTextField.setFont(Main.BASE_FONT);
        descriptionTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
        descriptionTextField.setForeground(Main.TEXT);
        requiredFieldsLabel.setFont(Main.BASE_FONT);
        requiredFieldsLabel.setForeground(Main.TEXT);
        saveButton.setFont(Main.BASE_FONT);
        saveButton.setBackground(Main.BUTTON_BACKGROUND);
        saveButton.setForeground(Main.TEXT);
        cancelButton.setFont(Main.BASE_FONT);
        cancelButton.setBackground(Main.BUTTON_BACKGROUND);
        cancelButton.setForeground(Main.TEXT);
        this.c.fill = GridBagConstraints.BOTH;
        this.c.gridy++;
        this.c.gridwidth = firstFieldWidth;
        add(nameLabel, c);
        this.c.gridx = firstFieldWidth;
        add(fieldTwoLabel, c);
        this.c.gridx = 0;
        this.c.gridy++;
        add(nameTextField, c);
        this.c.gridx = firstFieldWidth;
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
