import java.io.*;
import java.nio.file.Path;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * DataIO.java
 *
 * @author Cody Dukes
 * @author Nathan Grimsey
 *
 */

public class DataIO {
    /**
     * saveProgramData saves user data to a specified filePath.
     * @param filePath the filePath the data should be saved to.
     * @return boolean that is false if save encountered an error.
     * @author Cody Dukes
     */
    public static boolean saveProgramData(Path filePath) {
        boolean successfulSave = false;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(Main.userSettings);

            objectOut.close();
            fileOut.close();

            successfulSave = true;
        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);

        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
        }
        return successfulSave;
    }

    /**
     * loadProgramData loads the data saved at a specified filePath.
     * @param filePath the filePath the data should be saved to.
     * @return boolean that is false if load encountered an error.
     * @author Cody Dukes
     */
    public static boolean loadProgramData(Path filePath){
        boolean successfulLoad = false;
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Main.userSettings = (UserSettings) objectIn.readObject();
            About.updateProfile(Main.userSettings.getProfile());

            objectIn.close();
            fileIn.close();

            successfulLoad = true;
        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while reading file " + filePath, filePath);
        }
        catch (ClassNotFoundException error) {
            System.out.printf("\nCould not read class from file " + filePath, filePath);
        }
        return successfulLoad;
    }

    /**
     * importProgramData imports program data from a file that was previously exported from MPP.
     * @param filePath the path of the file to import data from.
     * @return boolean that is false if there was an error encountered in importing.
     * 
     * @author Nathan Grimsey
     */
    public static boolean importProgramData(Path filePath) {
        boolean success = loadProgramData(filePath);
        if (success) {
            success = saveProgramData(Main.PROJECT_DATA_FILE_PATH);
        }

        return success;
    }

    /**
     * Saves a project to the given location.
     * @param project the Project to be saved.
     * @param filePath a filePath chosen by the user.
     * @return boolean that is false if there was an error encountered in saving.
     * 
     * @author Cody Dukes
     * @author Nathan Grimsey
     */
    public boolean saveProject(Project project, Path filePath) {
        boolean successfulSave = false;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(project);

            objectOut.close();
            fileOut.close();

            successfulSave = true;
        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
        }

        if (successfulSave) {
            UserSettings.updateMostRecentProject(project.getName(), filePath);
        }

        return successfulSave;
    }

    /**
     * Loads a project from the given location.
     * @param filePath a filePath chosen by the user.
     * @return a Profile that has been deserialized from the given filePath.
     * 
     * @author Cody Dukes
     * @author Nathan Grimsey
     */
    public Project loadProject(Path filePath) throws ClassCastException, InvalidClassException {
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Project importedProject = (Project) objectIn.readObject();
            UserSettings.updateMostRecentProject(importedProject.getName(), filePath);

            objectIn.close();
            fileIn.close();

            return importedProject;
        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while reading file " + filePath, filePath);
        }
        catch (ClassNotFoundException error) {
            System.out.printf("\nCould not read class from file " + filePath, filePath);
        }
        return null;
    }

//    public boolean saveTool(Tool tool, Path filePath){ return false; }
//    public boolean loadTool(){ return false; }
//    public boolean saveMaterial(Material material, Path filePath) { return false; }
//    public boolean loadMaterial() { return false; }
//    public boolean exportAll(Path filePath) { return false; }
//    public boolean importAll(Path filePath) { return false; }

}
