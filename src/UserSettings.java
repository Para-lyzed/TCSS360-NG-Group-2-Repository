import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private static Profile userProfile;
    private static Map<String, Path> recentProjectsMap;
    private static ArrayList<String> recentProjectsList;

    /**
     * UserSettings constructs a UserSettings object that contains a profile, a Map, and a list.
     * @param profile is a profile containing a name and email.
     * @param projectsMap is a map containing the recent projects.
     * @param projectsList is a list containing the recent projects.
     * @author Cody Dukes
     */
    public UserSettings(Profile profile, Map<String, Path> projectsMap, ArrayList<String> projectsList) {
        userProfile = profile;
        recentProjectsMap = projectsMap;
        recentProjectsList = projectsList;
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
    }

    /**
     * updateMostRecentProject moves the current project to index 0 of the recentProjectsList or,
     * if it does not yet exist, adds it to the front of the list and adds its name and filePath to
     * the recentProjectsMap.
     * @param name the name of the current project to update as most recent.
     * @param filePath the path to the most recent project.
     * @author Cody Dukes
     */
    public static void updateMostRecentProject(String name, Path filePath) {
        if (recentProjectsList.contains(name)) {
            recentProjectsList.remove(name);
        }
        recentProjectsList.add(0, name);
        recentProjectsMap.put(name, filePath);
    }

    /**
     * getFilePathFromName returns the filePath associated with the project name.
     * @param name the name of the project to return a filePath to.
     * @return filePath to the project.
     * @author Cody Dukes
     */
    public Path getFilePathFromName(String name) {
        return recentProjectsMap.get(name);
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
    public static Profile getProfile() {
        return userProfile;
    }
}
