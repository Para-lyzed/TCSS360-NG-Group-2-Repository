package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import view.BaseFrame;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.Main.java
 * 
 * @author Nathan Grimsey
 *
 */
public class Main {
    public static final String HEADING_SPACER = "     ";
    public static final Font HEADING_ONE_FONT = new Font("Arial", Font.BOLD, 50);
    public static final Font HEADING_TWO_FONT = new Font("Arial", Font.BOLD, 36);
    public static final Font MENU_FONT = new Font("Arial", Font.BOLD, 32);
    public static final Font SPACER_FONT = new Font("Arial", Font.BOLD, 75);
    public static final Font BASE_FONT = new Font("Arial", Font.PLAIN, 24);
    public static final Font TABLE_FONT = new Font("Arial", Font.PLAIN, 16);
    public static final Font VERSION_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final int DEFAULT_WINDOW_WIDTH = 1000;
    public static final int DEFAULT_WINDOW_HEIGHT = 700;
    public static final int MENU_WIDTH = 350;
    public static final int PADDING = 20;
    public static final int FILE_IMPORT_SETTINGS = 0;
    public static final int FILE_EXPORT_SETTINGS = 1;
    public static final int FILE_IMPORT_ALL = 2;
    public static final int FILE_EXPORT_ALL = 3;
    public static final int FILE_LOAD = 4;
    public static final int FILE_SAVE = 5;
    public static final Insets MENU_INSETS = new Insets(PADDING, PADDING, PADDING, PADDING);
    public static final Insets PADDING_INSETS = new Insets(PADDING / 2, PADDING, PADDING, PADDING);
    public static BaseFrame BASE_FRAME;
    public static Color BACKGROUND = Color.WHITE;
    public static Color SECONDARY_BACKGROUND = Color.LIGHT_GRAY;
    public static Color TEXT_ERROR = Color.RED;
    public static Color TEXT_BOX_BACKGROUND = Color.WHITE;
    public static final Path PROJECT_DATA_FILE_PATH = Paths.get("programdata.mpp");
    public static UserSettings userSettings;

    public static void main(String[] args) {

        boolean programDataLoad = false;
        if (Files.exists(PROJECT_DATA_FILE_PATH)) {
            programDataLoad = DataIO.loadProgramData(PROJECT_DATA_FILE_PATH);
        }
        if (!programDataLoad) {
            userSettings = new UserSettings();
        }
        BASE_FRAME = new BaseFrame();
        BASE_FRAME.setVisible(true);

    }

    /** Searches a list of project names and returns whichever contains a
     * prompted string
     *
     * @param str
     * @return list of searched projects
     */
    public static ArrayList<String> searchProject (String str) {
        ArrayList<String> foundProjects = new ArrayList<>();
        ArrayList<String> recentProjectsList = userSettings.getRecentProjectsList();
        for (int i = 0; i < recentProjectsList.size(); i++) {
            String projectName = recentProjectsList.get(i);
            if (projectName.toLowerCase().contains(str.toLowerCase())) {
                foundProjects.add(projectName);
            }
        }
        return foundProjects;
    }
}




