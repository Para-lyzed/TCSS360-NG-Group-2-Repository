import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        JFrame f = new JFrame("MVP Project Planner");
        f.setSize(480, 360);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();
        AboutScreen as = new AboutScreen(f.getWidth(), f.getHeight());
        SettingScreen ss = new SettingScreen(f.getWidth(), f.getHeight());

        f.add(p);

        JButton settings = new JButton("Settings");
        JButton about = new JButton("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                as.setVisible(true);
            }
        });

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                ss.setVisible(true);
            }
        });

        p.add(settings);
        p.add(about);




        f.setVisible(true);


        }
    }




