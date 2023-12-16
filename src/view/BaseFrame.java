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
    private static ProjectExpenseScreen projectExpenseScreen;
    private static ProjectDetailsScreen projectDetailsScreen;
    private static ProjectToolScreen projectToolScreen;
    private static ProjectLogScreen projectLogScreen;
    private static ProjectToolSelectScreen projectToolSelectScreen;
    private static ProjectMaterialSelectScreen projectMaterialSelectScreen;
    private static EditToolScreen editToolScreen;
    private static EditMaterialScreen editMaterialScreen;
    private static BaseScreen currentScreen;
    private static Project currentWorkingProject;
    private static boolean currentlyInsideProject;
    public static boolean menuOpen;
    public static final int EXPENSES = 0;
    public static final int TOOLS = 1;
    public static final int LOGS = 2;
    public static final int DETAILS = 3;
    public static final int BUDGET = 4;
    public static final int PROJECT_SETTINGS = 5;

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
        newToolScreen = new NewToolScreen(getWidth(), getHeight(), false);
        newMaterialScreen = new NewMaterialScreen(getWidth(), getHeight(), false);
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
     * Returns true if the given String equals the current screen title.
     * 
     * @param screenName the String to test.
     * @return true if String is equal to the title of current screen.
     * 
     * @author Nathan Grimsey
     */
    public boolean titleEqual(String screenName) {
        if (currentScreen.title.equals(screenName)) {
            return true;
        }
        else {
            return false;
        }
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
                if (currentlyInsideProject) {
                    newToolScreen = new NewToolScreen(getWidth(), getHeight(), true);
                    lPane.remove(projectMenu);
                }
                else {
                    newToolScreen = new NewToolScreen(getWidth(), getHeight(), false);
                    lPane.remove(mainMenu);
                }
                lPane.add(newToolScreen, BorderLayout.CENTER, 1);
                currentScreen = newToolScreen;
                break;

            case "Create a New Material":
                if (currentlyInsideProject) {
                    newMaterialScreen = new NewMaterialScreen(getWidth(), getHeight(), true);
                    lPane.remove(projectMenu);
                }
                else {
                    newMaterialScreen = new NewMaterialScreen(getWidth(), getHeight(), false);
                    lPane.remove(mainMenu);
                }
                lPane.add(newMaterialScreen, BorderLayout.CENTER, 1);
                currentScreen = newMaterialScreen;
                break;

            case "Expenses":
                projectExpenseScreen.refreshBudget();
                lPane.add(projectExpenseScreen, BorderLayout.CENTER, 1);
                currentScreen = projectExpenseScreen;
                break;

            case "Details":
                lPane.add(projectDetailsScreen, BorderLayout.CENTER, 1);
                currentScreen = projectDetailsScreen;
                break;

            case "Project Tools":
                lPane.add(projectToolScreen, BorderLayout.CENTER, 1);
                currentScreen = projectToolScreen;
                break;

            case "Import Tool":
                lPane.add(projectToolSelectScreen, BorderLayout.CENTER, 1);
                currentScreen = projectToolSelectScreen;
                break;

            case "Import Material":
                lPane.add(projectMaterialSelectScreen, BorderLayout.CENTER, 1);
                currentScreen = projectMaterialSelectScreen;
                break;

            case "Logs":
                lPane.add(projectLogScreen, BorderLayout.CENTER, 1);
                currentScreen = projectLogScreen;
                break;

            case "Edit Tool":
                if (currentlyInsideProject) {
                    lPane.remove(projectMenu);
                }
                else {
                    lPane.remove(mainMenu);
                }
                lPane.add(editToolScreen, BorderLayout.CENTER, 1);
                currentScreen = editToolScreen;
                break;

            case "Edit Material":
                lPane.add(editMaterialScreen, BorderLayout.CENTER, 1);
                lPane.remove(mainMenu);
                currentScreen = editMaterialScreen;
                break;
        
            default:
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

                case "Expenses":
                    lPane.remove(projectExpenseScreen);
                    projectMenu.closeMenu();
                    break;

                case "Details":
                    lPane.remove(projectDetailsScreen);
                    projectMenu.closeMenu();
                    break;

                case "Project Tools":
                    lPane.remove(projectToolScreen);
                    projectMenu.closeMenu();
                    break;

                case "Import Tool":
                    lPane.remove(projectToolSelectScreen);
                    projectMenu.closeMenu();
                    break;
                
                case "Import Material":
                    lPane.remove(projectMaterialSelectScreen);
                    projectMenu.closeMenu();
                    break;

                case "Logs":
                    lPane.remove(projectLogScreen);
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
                
                case "Expenses":
                    projectExpenseScreen.menuHeading(isOpen);
                    projectExpenseScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;

                case "Details":
                    projectDetailsScreen.menuHeading(isOpen);
                    projectDetailsScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;

                case "Project Tools":
                    projectToolScreen.menuHeading(isOpen);
                    projectToolScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;

                case "Import Tool":
                    projectToolSelectScreen.menuHeading(isOpen);
                    projectToolSelectScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;

                case "Import Material":
                    projectMaterialSelectScreen.menuHeading(isOpen);
                    projectMaterialSelectScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;

                case "Logs":
                    projectLogScreen.menuHeading(isOpen);
                    projectLogScreen.setBounds(yBound, 0, getWidth() - yBound, getHeight());
                    break;
            
                default:
                    break;
            }
            repaint();
    }

    /**
     * Opens a Project into a new ProjectExpenseScreen.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProject(Project project) {
        projectExpenseScreen = new ProjectExpenseScreen(getWidth(), getHeight(), project);
        switchScreen("Expenses");
        titlePrefix = project.getName() + " - ";
        setTitle(titlePrefix + "Expenses");
        lPane.add(projectMenu, BorderLayout.WEST, 0);
        currentWorkingProject = project;
        currentlyInsideProject = true;
    }

    /**
     * Opens the current Project into ProjectExpenseScreen.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProjectExpenses(boolean addMenu) {
        if (addMenu) {
            lPane.add(projectMenu, BorderLayout.WEST, 0);
        }
        projectExpenseScreen = new ProjectExpenseScreen(getWidth(), getHeight(), currentWorkingProject);
        switchScreen("Expenses");
        setTitle(titlePrefix + "Expenses");
    }

    /**
     * Opens the current Project into ProjectDetailsScreen.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProjectDetails(boolean addMenu) {
        if (addMenu) {
            lPane.add(projectMenu, BorderLayout.WEST, 0);
        }
        projectDetailsScreen = new ProjectDetailsScreen(getWidth(), getHeight(), currentWorkingProject);
        switchScreen("Details");
        setTitle(titlePrefix + "Details");
    }

    /**
     * Opens the current Project into ProjectToolScreen.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProjectTools(boolean addMenu) {
        if (addMenu) {
            lPane.add(projectMenu, BorderLayout.WEST, 0);
        }
        projectToolScreen = new ProjectToolScreen(getWidth(), getHeight(), currentWorkingProject);
        switchScreen("Project Tools");
        setTitle(titlePrefix + "Project Tools");
    }

    /**
     * Opens a ProjectToolSelectScreen to select a Tool to add.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProjectToolSelect(boolean addMenu) {
        if (addMenu) {
            lPane.add(projectMenu, BorderLayout.WEST, 0);
        }
        projectToolSelectScreen = new ProjectToolSelectScreen(getWidth(), getHeight(), currentWorkingProject);
        switchScreen("Import Tool");
        setTitle(titlePrefix + "Import Tool");
    }

    /**
     * Opens a ProjectMaterialSelectScreen to select a Material to add.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProjectMaterialSelect(boolean addMenu) {
        if (addMenu) {
            lPane.add(projectMenu, BorderLayout.WEST, 0);
        }
        projectMaterialSelectScreen = new ProjectMaterialSelectScreen(getWidth(), getHeight(), currentWorkingProject);
        switchScreen("Import Material");
        setTitle(titlePrefix + "Import Material");
    }

    /**
     * Opens the current Project into ProjectLogScreen.
     * 
     * @param project
     * 
     * @author Nathan Grimsey
     */
    public void openProjectLogs(boolean addMenu) {
        if (addMenu) {
            lPane.add(projectMenu, BorderLayout.WEST, 0);
        }
        projectLogScreen = new ProjectLogScreen(getWidth(), getHeight(), currentWorkingProject);
        switchScreen("Logs");
        setTitle(titlePrefix + "Logs");
    }

    /**
     * Adds a tool to the current project when the user creates a new one.
     * 
     * @param tool the tool to add.
     */
    public void addNewToolToProject(Tool tool) {
        currentWorkingProject.addTool(tool);
    }

    /**
     * Adds a tool to the current project when the user creates a new one.
     * 
     * @param tool the tool to add.
     */
    public void addNewMaterialToProject(Material material) {
        currentWorkingProject.addExpense(material, 1);
    }

    /**
     * Opens a Tool into a new ToolScreen.
     * 
     * @param tool
     * 
     * @author Nathan Grimsey
     */
    public void openTool(Tool tool) {
        editToolScreen = new EditToolScreen(getWidth(), getHeight(), tool, currentlyInsideProject);
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
     * Takes the user back to the Project Select.
     * 
     * @author Nathan Grimsey
     */
    public void resetToProjects() {
        lPane.remove(projectMenu);
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        switchScreen("Projects");
        currentlyInsideProject = false;
    }

    /**
     * Takes the user back to the Tool Select.
     * 
     * @author Nathan Grimsey
     */
    public void resetToTools() {
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        switchScreen("Tools");
    }

    /**
     * Takes the user back to the Material Select.
     * 
     * @author Nathan Grimsey
     */
    public void resetToMaterials() {
        lPane.add(mainMenu, BorderLayout.WEST, 0);
        switchScreen("Materials");
    }

    /**
     * Updates the UI elements of the screens to follow the current dark mode setting.
     * 
     * @author Nathan Grimsey
     */
    public void darkMode() {
        mainMenu.darkMode();
        projectMenu.darkMode();
        projectSelectScreen.darkMode();
        toolSelectScreen.darkMode();
        materialSelectScreen.darkMode();
        aboutScreen.darkMode();
        settingScreen.darkMode();
        newProjectScreen.darkMode();
        newToolScreen.darkMode();
        newMaterialScreen.darkMode();
    }

}
