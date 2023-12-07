import java.io.*;
import java.nio.file.Path;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * DataIO.java
 *
 * @author Cody Dukes
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
        boolean successfulSave = true;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(UserSettings.getProfile());

            objectOut.close();
            fileOut.close();

        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
            successfulSave = false;

        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
            successfulSave = false;
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
        boolean successfulLoad = true;
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            About.updateProfile(((UserSettings) objectIn.readObject()).getProfile());

            objectIn.close();
            fileIn.close();

        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
            successfulLoad = false;
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while reading file " + filePath, filePath);
            successfulLoad = false;
        }
        catch (ClassNotFoundException error) {
            System.out.printf("\nCould not read class from file " + filePath, filePath);
            successfulLoad = false;
        }
        return successfulLoad;
    }

    /**
     * importProgramData imports program data from a file that was previously exported from MPP.
     * @param filePath the path of the file to import data from.
     * @return boolean that is false if there was an error encountered in importing.
     */
    public static boolean importProgramData(Path filePath) {
        boolean success = loadProgramData(filePath);
        if (success) {
            success = saveProgramData(Main.PROJECT_DATA_FILE_PATH);
        }

        return success;
    }

    public boolean saveProject(Project project, Path filePath) {
        UserSettings.updateMostRecentProject(project.getName(), filePath);

        boolean successfulSave = true;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(project);

            objectOut.close();
            fileOut.close();

        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
            successfulSave = false;

        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
            successfulSave = false;
        }
        return successfulSave;
    }
    public boolean loadProject(Project project, Path filePath) {
        boolean successfulLoad = true;
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            UserSettings.updateMostRecentProject(((UserSettings) objectIn.readObject()).getRecentProjectList().get(0), filePath);

            objectIn.close();
            fileIn.close();

        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
            successfulLoad = false;
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while reading file " + filePath, filePath);
            successfulLoad = false;
        }
        catch (ClassNotFoundException error) {
            System.out.printf("\nCould not read class from file " + filePath, filePath);
            successfulLoad = false;
        }
        return successfulLoad;
    }

//    public boolean saveTool(Tool tool, Path filePath){ return false; }
//    public boolean loadTool(){ return false; }
//    public boolean saveMaterial(Material material, Path filePath) { return false; }
//    public boolean loadMaterial() { return false; }
//    public boolean exportAll(Path filePath) { return false; }
//    public boolean importAll(Path filePath) { return false; }

}
