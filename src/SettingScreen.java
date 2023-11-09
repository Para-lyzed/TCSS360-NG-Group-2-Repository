import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * SettingScreen.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class SettingScreen extends JPanel {
    private JLabel heading = new JLabel("     Settings");
    private JLabel setProfile = new JLabel("Set new name and email");
    private JLabel name = new JLabel("Name:");
    private JTextField nameTextField = new JTextField(16);
    private JLabel email = new JLabel("Email:");
    private JTextField emailTextField = new JTextField(16);
    private JButton submitButton = new JButton("Submit");

    public SettingScreen(int width, int height, About about, AboutScreen aboutScreen) {
        setBackground(Color.WHITE);
        setBounds(0, 0, width, height);
        setOpaque(true);
        heading.setFont(Main.headingOneFont);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 4;
        add(heading, c);
        c.gridy++;
        setProfile.setFont(Main.headingTwoFont);
        add(setProfile, c);
        c.gridy++;
        c.gridwidth = 1;
        name.setFont(Main.baseFont);
        add(name, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        nameTextField.setFont(Main.baseFont);
        add(nameTextField, c);
        c.gridx++;
        c.ipadx = 0;
        email.setFont(Main.baseFont);
        add(email, c);
        c.gridx++;
        c.ipadx = getWidth() / 4;
        emailTextField.setFont(Main.baseFont);
        add(emailTextField, c);
        c.gridy++;
        c.gridx = 0;
        c.ipadx = 0;
        submitButton.setFont(Main.baseFont);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about.updateProfile(nameTextField.getText(), emailTextField.getText());
                System.out.println(nameTextField.getText() + " " + emailTextField.getText());
                nameTextField.setText("");
                emailTextField.setText("");
                aboutScreen.refreshProfile();
            }
        });
        add(submitButton, c);
    }

}