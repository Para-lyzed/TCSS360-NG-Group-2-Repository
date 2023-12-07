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
    private boolean darkMode;

    /**
     * UserSettings constructs a UserSettings object that contains a profile, a Map, and a list.
     * @param profile is a profile containing a name and email.
     * @param projectsMap is a map containing the recent projects.
     * @param projectsList is a list containing the recent projects.
     * @author Cody Dukes
     */
    public UserSettings(Profile profile, HashMap<String, String> projectsMap, ArrayList<String> projectsList, boolean mode) {
        userProfile = profile;
        recentProjectsMap = projectsMap;
        recentProjectsList = projectsList;
        darkMode = mode;
        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * Default constructor for UserSettings object.
     * 
     * @author Nathan Grimsey
     */
    public UserSettings() {
        userProfile = new Profile();
        recentProjectsMap = new HashMap<>();
        recentProjectsList = new ArrayList<>();
        darkMode = false;
    }

    /**
     * updateMostRecentProject moves the current project to index 0 of the recentProjectsList or,
     * if it does not yet exist, adds it to the front of the list and adds its name and filePath to
     * the recentProjectsMap.
     * @param name the name of the current project to update as most recent.
     * @param filePath the path to the most recent project.
     * @author Cody Dukes
     */
    public void updateMostRecentProject(String name, Path filePath) {
        if (recentProjectsList.contains(name)) {
            recentProjectsList.remove(name);
        }
        recentProjectsList.add(0, name);
        recentProjectsMap.put(name, filePath.toString());
        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * Remove a Project from the recents list.
     * @param name the name of the Project to remove.
     * 
     * @author Nathan Grimsey
     */
    public void removeProject(String name) {
        recentProjectsList.remove(name);
        recentProjectsMap.remove(name);
        DataIO.saveProgramData(Main.PROJECT_DATA_FILE_PATH);
    }

    /**
     * getFilePathFromName returns the filePath associated with the project name.
     * @param name the name of the project to return a filePath to.
     * @return filePath to the project.
     * @author Cody Dukes
     */
    public Path getFilePathFromName(String name) {
        String path = recentProjectsMap.get(name);
        if (path.equals(null)) {
            return null;
        }
        return Path.of(path);
    }

    /**
     * getRecentProjectList returns the list of recent projects.
     * @return recentProjectList the list of recent projects.
     * @author Cody Dukes
     */
    public ArrayList<String> getRecentProjectList() {
        return recentProjectsList;
    }

    /**
     * getProfile returns profile object.
     * @return Profile object.
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
