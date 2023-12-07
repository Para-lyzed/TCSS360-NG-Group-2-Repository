import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * BaseFrame.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseFrame extends JFrame {
    private static final String titlePrefix = "MVP Project Planner - ";
    private static JLayeredPane lPane;
    private static Menu mainMenu;
    private static ProjectSelectScreen projectSelectScreen;
    private static AboutScreen aboutScreen;
    private static SettingScreen settingScreen;
    private static NewProjectScreen newProjectScreen;
    private static BaseMainMenuScreen currentScreen;
    public static boolean menuOpen;

    /**
     * BaseFrame contains the entire window for the app.
     * @param width sets the width of the window.
     * @param height sets the height of the window.
     * @author Nathan Grimsey
     */
    public BaseFrame() {
        setTitle("MVP Project Planner - Projects");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Main.DEFAULT_WINDOW_WIDTH, Main.DEFAULT_WINDOW_HEIGHT));
        setMinimumSize(getPreferredSize());
        setLayout(new BorderLayout());
        lPane = new JLayeredPane();
        projectSelectScreen = new ProjectSelectScreen(getWidth(), getHeight());
        aboutScreen = new AboutScreen(getWidth(), getHeight());
        settingScreen = new SettingScreen(getWidth(), getHeight());
        mainMenu = new Menu(true, getHeight());
        newProjectScreen = new NewProjectScreen(getWidth(), getHeight());
        menuOpen = false;
        add(lPane, BorderLayout.CENTER);
        currentScreen = projectSelectScreen;
        lPane.add(currentScreen, BorderLayout.CENTER, 1);
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                if (menuOpen) {
                    currentScreen.setBounds(Main.MENU_WIDTH, 0, getWidth() - Main.MENU_WIDTH, getHeight());
                }
                else {
                    currentScreen.setBounds(0, 0, getWidth(), getHeight());
                }
                mainMenu.setHeight(getHeight());
            }
        });
    }

    /**
     * switchScreen switches the screen of the app currently being viewed.
     * @param screenName is the name of the screen to switch to.
     * @author Nathan Grimsey
     */
    public void switchScreen(String screenName) {
        if (screenName.equals(currentScreen.title)) {
            return;
        }
        boolean validName = true;
        String oldScreen = currentScreen.title;
        switch (screenName) {
            case "Projects":
                lPane.add(projectSelectScreen, BorderLayout.CENTER, 1);
                currentScreen = projectSelectScreen;
                break;
            
            case "Settings":
                lPane.add(settingScreen, BorderLayout.CENTER, 1);
                currentScreen = settingScreen;
                break;
            
            case "About":
                lPane.add(aboutScreen, BorderLayout.CENTER, 1);
                currentScreen = aboutScreen;
                aboutScreen.refreshProfile();
                break;
            
            case "Create a New Project":
                lPane.add(newProjectScreen, BorderLayout.CENTER);
                currentScreen = newProjectScreen;
                break;
        
            default:
                System.out.println("The screen name: " + screenName + " is not a valid selection");
                validName = false;
                break;
        }
        if (validName) {
            setTitle(titlePrefix + screenName);
            switch (oldScreen) {
                    case "Projects":
                        lPane.remove(projectSelectScreen);
                        break;
            
                    case "Settings":
                        lPane.remove(settingScreen);
                        break;
            
                    case "About":
                        lPane.remove(aboutScreen);

                    case "Create a New Project":
                        lPane.remove(newProjectScreen);
                }
            menuOpen(false);
            repaint();
        }
    }

    /**
     * menuOpen handles UI elements when the menu is open or closed.
     * @param isOpen is whether the menu is open.
     * @author Nathan Grimsey
     */
    public void menuOpen(boolean isOpen) {
        menuOpen = isOpen;
        int yBound;
        if (isOpen) {
            yBound = Main.MENU_WIDTH;
        }
        else {
            yBound = 0;
        }
        switch (currentScreen.title) {
                case "Projects":
                    projectSelectScreen.menuHeading(isOpen);
                    projectSelectScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
                
                case "Settings":
                    settingScreen.menuHeading(isOpen);
                    settingScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
                
                case "About":
                    aboutScreen.menuHeading(isOpen);
                    aboutScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
            
                default:
                    break;
            }
            repaint();
    }

}
