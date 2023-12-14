import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * UserSettings.java
 *
 * @author Cody Dukes
 * @author Nathan Grimsey
 *
 */
public class UserSettings implements Serializable {

    private Profile userProfile;
    private HashMap<String, String> recentProjectsMap;
    private ArrayList<String> recentProjectsList;
    private HashMap<String, String> recentToolsMap;
    private ArrayList<String> recentToolsList;
    private HashMap<String, String> recentMaterialsMap;
    private ArrayList<String> recentMaterialsList;
    private boolean darkMode;

    /**
     * UserSettings constructs a UserSettings object that contains a profile, a Map, and a list.
     * @param profile is a profile containing a name and email.
     * @param projectsMap is a map containing the recent projects.
     * @param projectsList is a list containing the recent projects.
     * @param toolsMap is a map containing the recent tools.
     * @param toolsList is a list containing the recent tools.
     * @param materialsMap is a map containing the recent materials.
     * @param materialsList is a list containing the recent materials.
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
     * updateMostRecent moves the current element to index 0 of the recentElementsList or,
     * if it does not yet exist, adds it to the front of the list and adds its name and filePath to
     * the recentElementsMap.
     * @param name the name of the current project to update as most recent.
     * @param filePath the path to the most recent project.
     * @param type dictates whether to update projects, tools, or materials using type indicators
     *            0, 1, and 2 respectively.
     *
     * @author Cody Dukes
     */
    public void updateMostRecent(String name, Path filePath, int type) {
        switch (type) {
            case 0:
                if (recentProjectsList.contains(name)) {
                    recentProjectsList.remove(name);
                }
                recentProjectsList.add(0, name);
                recentProjectsMap.put(name, filePath.toString());
                break;

            case 1:
                if (recentToolsList.contains(name)) {
                    recentToolsList.remove(name);
                }
                recentToolsList.add(0, name);
                recentToolsMap.put(name, filePath.toString());
                break;

            case 2:
                if (recentMaterialsList.contains(name)) {
                    recentMaterialsList.remove(name);
                }
                recentMaterialsList.add(0, name);
                recentMaterialsMap.put(name, filePath.toString());
                break;

            default:
                System.out.printf("\nInvalid type " + type + ", only 0, 1, and 2 permitted.", type);
                return;
        }

        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * Removes an element from the recents list.
     * @param name the name of the Project to remove.
     * @param type dictates which element to remove using type indicators
     *            0 (projects), 1 (tools), and 2 (materials).
     *
     * @author Nathan Grimsey
     * @author Cody Dukes
     */
    public void removeProject(String name, int type) {
        switch (type) {
            case 0:
                recentProjectsList.remove(name);
                recentProjectsMap.remove(name);
                break;

            case 1:
                recentToolsList.remove(name);
                recentToolsMap.remove(name);
                break;

            case 2:
                recentMaterialsList.remove(name);
                recentMaterialsMap.remove(name);
                break;

            default:
                System.out.printf("\nInvalid type " + type + ", only 0, 1, and 2 permitted.", type);
                return;
        }

        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * getFilePathFromName returns the filePath associated with the elements name.
     * @param name the name of the project to return a filePath to.
     * @param type dictates which kind of filePath to get using type indicators
     *            0 (projects), 1 (tools), and 2 (materials).
     * @return filePath to the chosen element.
     *
     * @author Cody Dukes
     */
    public Path getFilePathFromName(String name, int type) {
        String path;
        switch (type) {
            case 0:
                path = recentProjectsMap.get(name);
                break;

            case 1:
                path = recentToolsMap.get(name);
                break;

            case 2:
                path = recentMaterialsMap.get(name);
                break;

            default:
                System.out.printf("\nInvalid type " + type + ", only 0, 1, and 2 permitted.", type);
                return null;
        }

        if (path == null) {
            return null;
        }
        return Path.of(path);
    }

    /**
     * getRecentProjectsList returns the list of recent projects.
     * @return recentProjectsList the list of recent projects.
     *
     * @author Cody Dukes
     */
    public ArrayList<String> getRecentProjectsList() {
        return recentProjectsList;
    }

    /**
     * getRecentToolsList returns the list of recent tools.
     * @return recentToolList the list of recent tools.
     *
     * @author Cody Dukes
     */
    public ArrayList<String> getRecentToolsList() {
        return recentToolsList;
    }

    /**
     * getRecentMaterialsList returns the list of recent materials.
     * @return recentMaterialsList the list of recent materials.
     *
     * @author Cody Dukes
     */
    public ArrayList<String> getRecentMaterialsList() {
        return recentMaterialsList;
    }

    /**
     * getProfile returns profile object.
     * @return Profile object.
     *
     * @author Cody Dukes
     */
    public Profile getProfile() {
        return userProfile;
    }

    /**
     * Gets the darkMode boolean.
     * @return boolean of whether the user is in dark mode or not.
     */
    public boolean getDarkMode() {
        return darkMode;
    }

    /**
     * Sets the darkMode boolean.
     * @param mode the value to set the darkMode boolean to.
     */
    public void setDarkMode(boolean mode) {
        darkMode = mode;
    }

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
    }
}
