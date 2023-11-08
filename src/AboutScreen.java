
import javax.swing.*;
import java.awt.*;

public class AboutScreen extends JFrame {


    public AboutScreen(int x, int y) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1));

        this.setSize(x, y);
        this.add(p);
        JLabel owner = new JLabel("This app is registered to: me");
        JLabel contributors = new JLabel("This app is provided by: Nathan, Cody, Maple");
        p.add(owner);
        p.add(contributors, BorderLayout.SOUTH);





    }

    
}
