package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import model.Main;
import model.Material;
import model.Project;
import model.Tool;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.BaseFrame.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseFrame extends JFrame {
    private static final String defaultTitlePrefix = "MVP Project Planner - ";
    private static String titlePrefix;
    private static JLayeredPane lPane;
    private static Menu mainMenu;
    private static Menu projectMenu;
    private static ProjectSelectScreen projectSelectScreen;
    private static ToolSelectScreen toolSelectScreen;
    private static MaterialSelectScreen materialSelectScreen;

    private static AboutScreen aboutScreen;
    private static SettingScreen settingScreen;
    private static NewProjectScreen newProjectScreen;
    private static NewToolScreen newToolScreen;
    private static NewMaterialScreen newMaterialScreen;
    private static ProjectOverviewScreen projectOverviewScreen;
    private static EditToolScreen editToolScreen;
    private static EditMaterialScreen editMaterialScreen;
    private static BaseScreen currentScreen;
    public static boolean menuOpen;

    /**
     * BaseFrame contains the entire window for the app.
     * 
     * @param width sets the width of the window.
     * @param height sets the height of the window.
     * 
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
        toolSelectScreen = new ToolSelectScreen(getWidth(), getHeight());
        materialSelectScreen = new MaterialSelectScreen(getWidth(), getHeight());
        aboutScreen = new AboutScreen(getWidth(), getHeight());
        settingScreen = new SettingScreen(getWidth(), getHeight());
        mainMenu = new Menu(true, getHeight());
        projectMenu = new Menu(false, getHeight());
        newProjectScreen = new NewProjectScreen(getWidth(), getHeight());
        newToolScreen = new NewToolScreen(getWidth(), getHeight());
        newMaterialScreen = new NewMaterialScreen(getWidth(), getHeight());
        menuOpen = false;
        add(lPane, BorderLayout.CENTER);
        currentScreen = projectSelectScreen;
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        lPane.add(currentScreen, BorderLayout.CENTER, 1);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                try {
                    Thread.sleep(15); // This helps to prevent glitches where the panel isn't properly resized.
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
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
     * 
     * @param screenName is the name of the screen to switch to.
     * 
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
                projectSelectScreen.refresh();
                lPane.add(projectSelectScreen, BorderLayout.CENTER, 1);
                currentScreen = projectSelectScreen;
                titlePrefix = defaultTitlePrefix;
                break;

            case "Tools":
                toolSelectScreen.refresh();
                lPane.add(toolSelectScreen, BorderLayout.CENTER, 1);
                currentScreen = toolSelectScreen;
                titlePrefix = defaultTitlePrefix;
                break;

            case "Materials":
                materialSelectScreen.refresh();
                lPane.add(materialSelectScreen, BorderLayout.CENTER, 1);
                currentScreen = materialSelectScreen;
                titlePrefix = defaultTitlePrefix;
                break;
            
            case "Settings":
                lPane.add(settingScreen, BorderLayout.CENTER, 1);
                currentScreen = settingScreen;
                break;
            
            case "About":
                aboutScreen.refreshProfile();
                lPane.add(aboutScreen, BorderLayout.CENTER, 1);
                currentScreen = aboutScreen;
                break;
            
            case "Create a New Project":
                newProjectScreen = new NewProjectScreen(getWidth(), getHeight());
                lPane.add(newProjectScreen, BorderLayout.CENTER, 1);
                lPane.remove(mainMenu);
                currentScreen = newProjectScreen;
                break;

            case "Create a New Tool":
                newToolScreen = new NewToolScreen(getWidth(), getHeight());
                lPane.add(newToolScreen, BorderLayout.CENTER, 1);
                lPane.remove(mainMenu);
                currentScreen = newToolScreen;
                break;

            case "Create a New Material":
                newMaterialScreen = new NewMaterialScreen(getWidth(), getHeight());
                lPane.add(newMaterialScreen, BorderLayout.CENTER, 1);
                lPane.remove(mainMenu);
                currentScreen = newMaterialScreen;
                break;

            case "Overview":
                lPane.add(projectOverviewScreen, BorderLayout.CENTER, 1);
                currentScreen = projectOverviewScreen;
                break;

            case "Edit Tool":
                lPane.add(editToolScreen, BorderLayout.CENTER, 1);
                lPane.remove(mainMenu);
                currentScreen = editToolScreen;
                break;

            case "Edit Material":
                lPane.add(editMaterialScreen, BorderLayout.CENTER, 1);
                lPane.remove(mainMenu);
                currentScreen = editMaterialScreen;
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
                    mainMenu.closeMenu();
                    break;

                case "Tools":
                    lPane.remove(toolSelectScreen);
                    mainMenu.closeMenu();
                    break;

                case "Materials":
                    lPane.remove(materialSelectScreen);
                    mainMenu.closeMenu();
                    break;
        
                case "Settings":
                    lPane.remove(settingScreen);
                    mainMenu.closeMenu();
                    break;
        
                case "About":
                    lPane.remove(aboutScreen);
                    mainMenu.closeMenu();
                    break;

                case "Create a New Project":
                    lPane.remove(newProjectScreen);
                    break;
                
                case "Create a New Tool":
                    lPane.remove(newToolScreen);
                    lPane.add(mainMenu, BorderLayout.WEST, 0);
                    break;

                case "Create a New Material":
                    lPane.remove(newMaterialScreen);
                    lPane.add(mainMenu, BorderLayout.WEST, 0);
                    break;

                case "Overview":
                    lPane.remove(projectOverviewScreen);
                    projectMenu.closeMenu();
                    break;

                case "Edit Tool":
                    lPane.remove(editToolScreen);
                    break;

                case "Edit Material":
                    lPane.remove(editMaterialScreen);
                    break;
            }
            menuOpen(false);
            repaint();
        }
    }

    /**
     * menuOpen handles UI elements when the menu is open or closed.
     * 
     * @param isOpen is whether the menu is open.
     * 
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

                case "Tools":
                    toolSelectScreen.menuHeading(isOpen);
                    toolSelectScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;

                case "Materials":
                    materialSelectScreen.menuHeading(isOpen);
                    materialSelectScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
                
                case "Settings":
                    settingScreen.menuHeading(isOpen);
                    settingScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
                
                case "About":
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

    /**
     * Opens a Project into a new ProjectOverviewScreen.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProject(Project project) {
        projectOverviewScreen = new ProjectOverviewScreen(getWidth(), getHeight(), project);
        switchScreen("Overview");
        titlePrefix = project.getName() + " - ";
        setTitle(titlePrefix + "Overview");
        lPane.add(projectMenu, BorderLayout.WEST, 0);
    }

    /**
     * Opens a Tool into a new ToolScreen.
     * 
     * @param tool
     * 
     * @author Nathan Grimsey
     */
    public void openTool(Tool tool) {
        editToolScreen = new EditToolScreen(getWidth(), getHeight(), tool);
        switchScreen("Edit Tool");
        titlePrefix = tool.getName() + " - ";
        setTitle(titlePrefix + "Edit Tool");
    }

    /**
     * Opens a Material into a new MaterialScreen.
     * 
     * @param material
     * 
     * @author Nathan Grimsey
     */
    public void openMaterial(Material material) {
        editMaterialScreen = new EditMaterialScreen(getWidth(), getHeight(), material);
        switchScreen("Edit Material");
        titlePrefix = material.getName() + " - ";
        setTitle(titlePrefix + "Edit Material");
    }

    /**
     * Takes the user back to the main menu.
     * 
     * @author Nathan Grimsey
     */
    public void resetToProjects() {
        lPane.remove(projectMenu);
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        switchScreen("Projects");
    }

    /**
     * Takes the user back to the main menu.
     * 
     * @author Nathan Grimsey
     */
    public void resetToTools() {
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        switchScreen("Tools");
    }

    /**
     * Takes the user back to the main menu.
     * 
     * @author Nathan Grimsey
     */
    public void resetToMaterials() {
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        switchScreen("Materials");
    }

}
