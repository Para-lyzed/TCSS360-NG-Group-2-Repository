import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * BaseFrame.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseFrame extends JFrame {
    private static JLayeredPane lPane;
    private static JPanel mainMenu;
    private static ProjectScreen projectScreen;
    private static AboutScreen aboutScreen;
    private static SettingScreen settingScreen;
    private static String currentScreen;

    public BaseFrame(int width, int height, About about) {
        setTitle("MVP Project Planner - Projects");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(getPreferredSize());
        setResizable(false);
        setLayout(new BorderLayout());
        lPane = new JLayeredPane();
        projectScreen = new ProjectScreen(getWidth(), getHeight());
        aboutScreen = new AboutScreen(getWidth(), getHeight(), about);
        settingScreen = new SettingScreen(width, height, about, aboutScreen);
        mainMenu = new Menu(true, getHeight(), this);
        add(lPane, BorderLayout.CENTER);
        lPane.setBounds(0, 0, getWidth(), getHeight());
        lPane.add(projectScreen, BorderLayout.CENTER, 1);
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        currentScreen = "Projects";
    }

    public void switchScreen(String screenName) {
        System.out.println(screenName);
        if (screenName.equals(currentScreen)) {
            return;
        }
        boolean validName = true;
        switch (screenName) {
            case "Projects":
                setTitle("MVP Project Planner - Projects");
                lPane.add(projectScreen, BorderLayout.CENTER, 1);
                break;
            
            case "Settings":
                setTitle("MVP Project Planner - Settings");
                lPane.add(settingScreen, BorderLayout.CENTER, 1);
                break;
            
            case "About":
                setTitle("MVP Project Planner - About");
                lPane.add(aboutScreen, BorderLayout.CENTER, 1);
                break;
        
            default:
                System.out.println("The screen name: " + screenName + " is not a valid selection");
                validName = false;
                break;
        }
        if (validName) {
            switch (currentScreen) {
                case "Projects":
                    lPane.remove(projectScreen);
                    break;
                
                case "Settings":
                    lPane.remove(settingScreen);
                    break;
                
                case "About":
                    lPane.remove(aboutScreen);
                    break;
            
                default:
                    break;
            }
            currentScreen = screenName;
            repaint();
        }
    }

    public void menuOpen(boolean isOpen) {
        int yBound;
        if (isOpen) {
            yBound = Main.menuWidth;
        }
        else {
            yBound = 0;
        }
        switch (currentScreen) {
                case "Projects":
                    projectScreen.setBounds(yBound, 0, getWidth(), getHeight());
                    break;
                
                case "Settings":
                    settingScreen.setBounds(yBound, 0, getWidth(), getHeight());
                    break;
                
                case "About":
                    aboutScreen.setBounds(yBound, 0, getWidth(), getHeight());
                    break;
            
                default:
                    break;
            }
            repaint();
    }

}
