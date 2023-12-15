package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import view.BaseFrame;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.Main.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
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
    private static final Color LIGHT_BACKGROUND = Color.decode("#FAFAFA");
    private static final Color LIGHT_SECONDARY_BACKGROUND = Color.decode("#FFFFFF");
    private static final Color LIGHT_TEXT_ERROR = Color.decode("#FFADAD");
    private static final Color LIGHT_TEXT_BOX_BACKGROUND = Color.decode("#D9D9D9");
    private static final Color LIGHT_BUTTON_BACKGROUND = Color.decode("#D9D9D9");
    private static final Color LIGHT_MENU_BACKGROUND = Color.decode("#EBEBEB");
    private static final Color LIGHT_TEXT = Color.decode("#323232");
    private static final Color LIGHT_CARET = Color.decode("#242424");
    private static final Color DARK_BACKGROUND = Color.decode("#242424");
    private static final Color DARK_SECONDARY_BACKGROUND = Color.decode("#353535");
    private static final Color DARK_TEXT_ERROR = Color.decode("#4E1010");
    private static final Color DARK_TEXT_BOX_BACKGROUND = Color.decode("#444444");
    private static final Color DARK_BUTTON_BACKGROUND = Color.decode("#444444");
    private static final Color DARK_MENU_BACKGROUND = Color.decode("#303030");
    private static final Color DARK_TEXT = Color.decode("#FAFAFA");
    private static final Color DARK_CARET = Color.decode("#EBEBEB");
    public static Color BACKGROUND = DARK_BACKGROUND;
    public static Color SECONDARY_BACKGROUND = DARK_SECONDARY_BACKGROUND;
    public static Color TEXT_ERROR = DARK_TEXT_ERROR;
    public static Color TEXT_BOX_BACKGROUND = DARK_TEXT_BOX_BACKGROUND;
    public static Color TEXT = DARK_TEXT;
    public static Color BUTTON_BACKGROUND = DARK_BUTTON_BACKGROUND;
    public static Color MENU_BACKGROUND = DARK_MENU_BACKGROUND;
    public static Color CARET = DARK_CARET;
    public static final Path PROJECT_DATA_FILE_PATH = Path.of("programdata.mpp");
    public static UserSettings userSettings;

    public static void main(String[] args) {

        boolean programDataLoad = false;
        if (Files.exists(PROJECT_DATA_FILE_PATH)) {
            programDataLoad = DataIO.loadProgramData(PROJECT_DATA_FILE_PATH);
        }
        if (!programDataLoad) {
            userSettings = new UserSettings();
        }
        if (!userSettings.getDarkMode()) {
            darkMode(false);
        }
        BASE_FRAME = new BaseFrame();
        BASE_FRAME.setVisible(true);

    }

    /** Searches a list of file names of int type and returns those that contain a
     * prompted String.
     *
     * @param query the String used for the search.
     * @return list of file names matching search term.
     * 
     * @author Maple Gunn
     * @author Nathan Grimsey
     */
    public static ArrayList<String> searchFiles (String query, int type) {
        ArrayList<String> foundFiles = new ArrayList<>();
        ArrayList<String> recentFilesList = userSettings.getRecentFilesList(type);
        for (int i = 0; i < recentFilesList.size(); i++) {
            String fileName = recentFilesList.get(i);
            if (fileName.toLowerCase().contains(query.toLowerCase())) {
                foundFiles.add(fileName);
            }
        }
        return foundFiles;
    }

    public static void darkMode(boolean isDarkMode) {
        if (isDarkMode) {
            BACKGROUND = DARK_BACKGROUND;
            SECONDARY_BACKGROUND = DARK_SECONDARY_BACKGROUND;
            TEXT_ERROR = DARK_TEXT_ERROR;
            TEXT_BOX_BACKGROUND = DARK_TEXT_BOX_BACKGROUND;
            TEXT = DARK_TEXT;
            BUTTON_BACKGROUND = DARK_BUTTON_BACKGROUND;
            MENU_BACKGROUND = DARK_MENU_BACKGROUND;
            CARET = DARK_CARET;
        }
        else {
            BACKGROUND = LIGHT_BACKGROUND;
            SECONDARY_BACKGROUND = LIGHT_SECONDARY_BACKGROUND;
            TEXT_ERROR = LIGHT_TEXT_ERROR;
            TEXT_BOX_BACKGROUND = LIGHT_TEXT_BOX_BACKGROUND;
            TEXT = LIGHT_TEXT;
            BUTTON_BACKGROUND = LIGHT_BUTTON_BACKGROUND;
            MENU_BACKGROUND = LIGHT_MENU_BACKGROUND;
            CARET = LIGHT_CARET;
        }
    }
}




