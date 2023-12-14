package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.BaseFrame.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseFrame extends JFrame {
    private static final String defaultTitlePrefix = "MVP model.Project Planner - ";
    private static String titlePrefix;
    private static JLayeredPane lPane;
    private static Menu mainMenu;
    private static Menu projectMenu;
    private static ProjectSelectScreen projectSelectScreen;
    private static AboutScreen aboutScreen;
    private static SettingScreen settingScreen;
    private static NewProjectScreen newProjectScreen;
    private static ProjectOverviewScreen projectOverviewScreen;
    private static BaseScreen currentScreen;
    public static boolean menuOpen;

    /**
     * model.BaseFrame contains the entire window for the app.
     * @param width sets the width of the window.
     * @param height sets the height of the window.
     * @author Nathan Grimsey
     */
    public BaseFrame() {
        titlePrefix = defaultTitlePrefix;
        setTitle(titlePrefix + "Projects");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Main.DEFAULT_WINDOW_WIDTH, Main.DEFAULT_WINDOW_HEIGHT));
        setMinimumSize(getPreferredSize());
        setLayout(new BorderLayout());
        lPane = new JLayeredPane();
        projectSelectScreen = new ProjectSelectScreen(getWidth(), getHeight());
        aboutScreen = new AboutScreen(getWidth(), getHeight());
        settingScreen = new SettingScreen(getWidth(), getHeight());
        mainMenu = new Menu(true, getHeight());
        projectMenu = new Menu(false, getHeight());
        newProjectScreen = new NewProjectScreen(getWidth(), getHeight());
        menuOpen = false;
        add(lPane, BorderLayout.CENTER);
        currentScreen = projectSelectScreen;
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        lPane.add(currentScreen, BorderLayout.CENTER, 1);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                if (menuOpen) {
                    currentScreen.setBounds(Main.MENU_WIDTH, 0, getWidth() - Main.MENU_WIDTH, getHeight());
                }
                else {
                    currentScreen.setBounds(0, 0, getWidth(), getHeight());
                }
                mainMenu.setHeight(getHeight());
                projectMenu.setHeight(getHeight());
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
                titlePrefix = defaultTitlePrefix;
                projectSelectScreen.refresh();
                break;
            
            case "Settings":
                lPane.add(settingScreen, BorderLayout.CENTER, 1);
                currentScreen = settingScreen;
                break;
            
            case "model.About":
                lPane.add(aboutScreen, BorderLayout.CENTER, 1);
                currentScreen = aboutScreen;
                aboutScreen.refreshProfile();
                break;
            
            case "Create a New model.Project":
                newProjectScreen = new NewProjectScreen(getWidth(), getHeight());
                lPane.add(newProjectScreen, BorderLayout.CENTER, 1);
                lPane.remove(mainMenu);
                currentScreen = newProjectScreen;
                break;

            case "Overview":
                lPane.add(projectOverviewScreen, BorderLayout.CENTER, 1);
                currentScreen = projectOverviewScreen;
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
            
                    case "model.About":
                        lPane.remove(aboutScreen);
                        break;

                    case "Create a New model.Project":
                        lPane.remove(newProjectScreen);
                        break;

                    case "Overview":
                        lPane.remove(projectOverviewScreen);
                        break;
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
                
                case "model.About":
                    aboutScreen.menuHeading(isOpen);
                    aboutScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
                
                case "Overview":
                    projectOverviewScreen.menuHeading(isOpen);
                    projectOverviewScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
            
                default:
                    break;
            }
            repaint();
    }

    public void openProject(Project project) {
        projectOverviewScreen = new ProjectOverviewScreen(getWidth(), getHeight(), project);
        switchScreen("Overview");
        titlePrefix = project.getName() + " - ";
        setTitle(titlePrefix + "Overview");
        lPane.add(projectMenu, BorderLayout.WEST, 0);
    }

    public void resetToMainMenu() {
        lPane.remove(projectMenu);
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        switchScreen("Projects");
    }

}
