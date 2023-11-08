
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingScreen extends JFrame {

    public SettingScreen(int x, int y) {
        JPanel p = new JPanel();

        this.setSize(x, y);
        this.add(p);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField name = new JTextField(16);

        JLabel emailLabel = new JLabel("Email: ");
        JTextField email = new JTextField(16);

        JButton createProfile = new JButton("Set owner");



        p.add(nameLabel);
        p.add(name);
        p.add(emailLabel);
        p.add(email);

        p.add(createProfile);

        createProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile newOwner = new Profile(name.getText(), email.getText());
                System.out.println(newOwner.getName() + newOwner.getEmail());

            }
        });


    }
}
