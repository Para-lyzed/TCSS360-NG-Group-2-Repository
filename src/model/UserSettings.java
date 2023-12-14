package model;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.UserSettings.java
 *
 * @author Cody Dukes
 * @author Nathan Grimsey
 *
 */
public class UserSettings implements Serializable {

    public static final int PROJECT = 0;
    public static final int TOOL = 1;
    public static final int MATERIAL = 2;

    private Profile userProfile;
    private HashMap<String, String> recentProjectsMap;
    private ArrayList<String> recentProjectsList;
    private HashMap<String, String> recentToolsMap;
    private ArrayList<String> recentToolsList;
    private HashMap<String, String> recentMaterialsMap;
    private ArrayList<String> recentMaterialsList;
    private boolean darkMode;

    /**
     * UserSettings constructs a UserSettings object that contains a Profile, a Map, and a List for recent files of Projects, Tools, and Materials.
     * 
     * @param profile is a Profile containing a name and email.
     * @param projectsMap is a Map containing the recent Projects.
     * @param projectsList is a List containing the recent Projects.
     * @param toolsMap is a Map containing the recent Tools.
     * @param toolsList is a List containing the recent Tools.
     * @param materialsMap is a Map containing the recent Materials.
     * @param materialsList is a List containing the recent Materials.
     * @param mode is a boolean representing whether darkMode is enabled.
     *
     * @author Cody Dukes
     */
    public UserSettings(Profile profile, HashMap<String, String> projectsMap, ArrayList<String> projectsList,
                        HashMap<String, String> toolsMap, ArrayList<String> toolsList, HashMap<String, String> materialsMap,
                        ArrayList<String> materialsList, boolean mode) {
        userProfile = profile;
        recentProjectsMap = projectsMap;
        recentProjectsList = projectsList;
        recentToolsMap = toolsMap;
        recentToolsList = toolsList;
        recentMaterialsMap = materialsMap;
        recentMaterialsList = materialsList;
        darkMode = mode;
        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * Default constructor for UserSettings object.
     *
     * @author Nathan Grimsey
     * @author Cody Dukes
     */
    public UserSettings() {
        userProfile = new Profile();
        recentProjectsMap = new HashMap<>();
        recentProjectsList = new ArrayList<>();
        recentToolsMap = new HashMap<>();
        recentToolsList = new ArrayList<>();
        recentMaterialsMap = new HashMap<>();
        recentMaterialsList = new ArrayList<>();
        darkMode = false;
    }

    /**
     * Gets the proper recent files list that corresponds with a given input type. 
     * Valid types are PROJECT (0), TOOL (1), and MATERIAL (2). An invalid type will 
     * throw an IllegalArgumentException. This is a private method, and the type int 
     * should only ever be given by the backend, hence it throws an unhandled exception.
     * 
     * @param type is an int corresponding to PROJECT (0), TOOL (1), and MATERIAL (2).
     * @return the ArrayList that corresponds to the given input type.
     * 
     * @author Nathan Grimsey
     */
    private ArrayList<String> getListFromType(int type) {
        switch (type) {
            case PROJECT:
                return recentProjectsList;
            
            case TOOL:
                return recentToolsList;
            
            case MATERIAL:
                return recentMaterialsList;
            
            default:
                throw new IllegalArgumentException("int " + type + " is not a valid selection.");
        }
    }

    /**
     * Gets the proper recent files map that corresponds with a given input type. 
     * Valid types are PROJECT (0), TOOL (1), and MATERIAL (2). An invalid type will 
     * throw an IllegalArgumentException. This is a private method, and the type int 
     * should only ever be given by the backend, hence it throws an unhandled exception.
     * 
     * @param type is an int corresponding to PROJECT (0), TOOL (1), and MATERIAL (2).
     * @return the HashMap that corresponds to the given input type.
     * 
     * @author Nathan Grimsey
     */
    private HashMap<String, String> getMapFromType(int type) {
        switch (type) {
            case PROJECT:
                return recentProjectsMap;
            
            case TOOL:
                return recentToolsMap;
            
            case MATERIAL:
                return recentMaterialsMap;
            
            default:
                throw new IllegalArgumentException("int " + type + " is not a valid selection.");
        }
    }

    /**
     * updateMostRecent moves the current element to index 0 of the recentElementsList or,
     * if it does not yet exist, adds it to the front of the list and adds its name and filePath to
     * the recentElementsMap.
     * 
     * @param name the name of the current file to update as most recent.
     * @param filePath the Path to the file to update to most recent.
     * @param type is an int corresponding to PROJECT (0), TOOL (1), and MATERIAL (2).
     *
     * @author Cody Dukes
     * @author Nathan Grimsey
     */
    public void updateMostRecent(String name, Path filePath, int type) {
        ArrayList<String> recentFilesList = getListFromType(type);
        recentFilesList.remove(name);
        recentFilesList.add(0, name);
        getMapFromType(type).put(name, filePath.toString());
        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * Removes an element from the recents list.
     * 
     * @param name the name of the element to remove.
     * @param type is an int corresponding to PROJECT (0), TOOL (1), and MATERIAL (2).
     *
     * @author Nathan Grimsey
     * @author Cody Dukes
     */
    public void removeFromRecent(String name, int type) {
        getListFromType(type).remove(name);
        getMapFromType(type).remove(name);
        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * getFilePathFromName returns the filePath associated with the element's name.
     * 
     * @param name the name of the project to return a filePath to.
     * @param type is an int corresponding to PROJECT (0), TOOL (1), and MATERIAL (2).
     * @return filePath to the chosen element.
     *
     * @author Cody Dukes
     * @author Nathan Grimsey
     */
    public Path getFilePathFromName(String name, int type) {
        return Path.of(getMapFromType(type).get(name));
    }

    /**
     * Gets a recent files list of the specified type.
     * 
     * @param type is an int corresponding to PROJECT (0), TOOL (1), and MATERIAL (2).
     * @return recent files list of the specified type.
     * 
     * @author Nathan Grimsey
     */
    public ArrayList<String> getRecentFilesList(int type) {
        return getListFromType(type);
    }

    /**
     * getProfile returns Profile object.
     * 
     * @return Profile object.
     *
     * @author Cody Dukes
     */
    public Profile getProfile() {
        return userProfile;
    }

    /**
     * Gets the darkMode boolean.
     * 
     * @return boolean of whether the user is in dark mode or not.
     */
    public boolean getDarkMode() {
        return darkMode;
    }

    /**
     * Sets the darkMode boolean.
     * 
     * @param mode the value to set the darkMode boolean to.
     */
    public void setDarkMode(boolean mode) {
        darkMode = mode;
    }

    /**
     * Verifies that all elements are initialized if they were imported as null.
     * 
     * @author Nathan Grimsey
     */
    public void verifySelf() {
        if (userProfile == null) {
            userProfile = new Profile();
        }
        if (recentProjectsMap == null) {
            recentProjectsMap = new HashMap<>();
        }
        if (recentProjectsList == null) {
            recentProjectsList = new ArrayList<>();
        }
        if (recentToolsMap == null) {
            recentToolsMap = new HashMap<>();
        }
        if (recentToolsList == null) {
            recentToolsList = new ArrayList<>();
        }
        if (recentMaterialsMap == null) {
            recentMaterialsMap = new HashMap<>();
        }
        if (recentMaterialsList == null) {
            recentMaterialsList = new ArrayList<>();
        }
    }
}
