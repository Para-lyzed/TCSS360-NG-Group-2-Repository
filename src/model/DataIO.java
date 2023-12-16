package model;

import java.io.*;
import java.nio.file.Path;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.DataIO.java
 *
 * @author Cody Dukes
 * @author Nathan Grimsey
 *
 */

public class DataIO {
    /**
     * saveProgramData saves UserSettings to a specified Path.
     * 
     * @param filePath the Path the data should be saved to.
     * @return boolean that is false if save encountered an error.
     *
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
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();

        }
        catch (IOException error) {
            System.out.println("\nError occurred while writing to file " + filePath);
            error.printStackTrace();
        }
        return successfulSave;
    }

    /**
     * loadProgramData loads the UserSettings object saved at a specified Path.
     * 
     * @param filePath the Path the data should be loaded from.
     * @return boolean that is false if load encountered an error.
     *
     * @author Cody Dukes
     */
    public static boolean loadProgramData(Path filePath){
        boolean successfulLoad = false;
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Main.userSettings = (UserSettings) objectIn.readObject();
            Main.userSettings.verifySelf();
            About.updateProfile(Main.userSettings.getProfile());

            objectIn.close();
            fileIn.close();

            successfulLoad = true;
        }
        catch (FileNotFoundException error) {
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();
        }
        catch (IOException error) {
            System.out.println("\nError occurred while reading file " + filePath);
            error.printStackTrace();
        }
        catch (ClassNotFoundException error) {
            System.out.println("\nCould not read class from file " + filePath);
            error.printStackTrace();
        }
        return successfulLoad;
    }

    /**
     * importProgramData imports UserSettings from a file that was previously exported from MPP.
     * 
     * @param filePath the Path of the file to import data from.
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
     * Saves a Project to the given location.
     * 
     * @param project the Project to be saved.
     * @param filePath a Path chosen by the user.
     * @return boolean that is false if there was an error encountered in saving.
     * 
     * @author Cody Dukes
     * @author Nathan Grimsey
     */
    public static boolean saveProject(Project project, Path filePath) {
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
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();
        }
        catch (IOException error) {
            System.out.println("\nError occurred while writing to file " + filePath);
            error.printStackTrace();
        }

        if (successfulSave) {
            Main.userSettings.updateMostRecent(project.getName(), filePath, 0);
        }

        return successfulSave;
    }

    /**
     * Loads a Project from the given location.
     * 
     * @param filePath a Path chosen by the user.
     * @return a Profile that has been deserialized from the given filePath.
     * 
     * @author Cody Dukes
     * @author Nathan Grimsey
     */
    public static Project loadProject(Path filePath) throws ClassCastException, InvalidClassException {
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Project importedProject = (Project) objectIn.readObject();
            Main.userSettings.updateMostRecent(importedProject.getName(), filePath, 0);

            objectIn.close();
            fileIn.close();

            return importedProject;
        }
        catch (FileNotFoundException error) {
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();
        }
        catch (IOException error) {
            System.out.println("\nError occurred while reading file " + filePath);
            error.printStackTrace();
        }
        catch (ClassNotFoundException error) {
            System.out.println("\nCould not read class from file " + filePath);
            error.printStackTrace();
        }
        return null;
    }

    /**
     * Saves a Tool to the given location.
     * 
     * @param tool the Tool to be saved.
     * @param filePath a Path chosen by the user.
     * @return boolean that is false if there was an error encountered in saving.
     *
     * @author Cody Dukes
     */
    public static boolean saveTool(Tool tool, Path filePath){
        boolean successfulSave = false;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(tool);

            objectOut.close();
            fileOut.close();

            successfulSave = true;
        }
        catch (FileNotFoundException error) {
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();
        }
        catch (IOException error) {
            System.out.println("\nError occurred while writing to file " + filePath);
            error.printStackTrace();
        }

        if (successfulSave) {
            Main.userSettings.updateMostRecent(tool.getName(), filePath, 1);
        }

        return successfulSave;
    }

    /**
     * Loads a Tool from the given location.
     * 
     * @param filePath a Path chosen by the user.
     * @return a Tool that has been deserialized from the given filePath.
     *
     * @author Cody Dukes
     */
    public static Tool loadTool(Path filePath) throws ClassCastException, InvalidClassException {
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Tool importedTool = (Tool) objectIn.readObject();
            Main.userSettings.updateMostRecent(importedTool.getName(), filePath, 1);

            objectIn.close();
            fileIn.close();

            return importedTool;
        }
        catch (FileNotFoundException error) {
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();
        }
        catch (IOException error) {
            System.out.println("\nError occurred while reading file " + filePath);
            error.printStackTrace();
        }
        catch (ClassNotFoundException error) {
            System.out.println("\nCould not read class from file " + filePath);
            error.printStackTrace();
        }
        return null;
    }

    /**
     * Saves a Material to the given location.
     * 
     * @param material the Material to be saved.
     * @param filePath a Path chosen by the user.
     * @return boolean that is false if there was an error encountered in saving.
     *
     * @author Cody Dukes
     */
    public static boolean saveMaterial(Material material, Path filePath) {
        boolean successfulSave = false;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(material);

            objectOut.close();
            fileOut.close();

            successfulSave = true;
        }
        catch (FileNotFoundException error) {
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();
        }
        catch (IOException error) {
            System.out.println("\nError occurred while writing to file " + filePath);
            error.printStackTrace();
        }

        if (successfulSave) {
            Main.userSettings.updateMostRecent(material.getName(), filePath, 2);
        }

        return successfulSave;
    }

    /**
     * Loads a Material from the given location.
     * 
     * @param filePath a Path chosen by the user.
     * @return a Material that has been deserialized from the given filePath.
     *
     * @author Cody Dukes
     */
    public static Material loadMaterial(Path filePath) throws ClassCastException, InvalidClassException {
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Material importedMaterial = (Material) objectIn.readObject();
            Main.userSettings.updateMostRecent(importedMaterial.getName(), filePath, 2);

            objectIn.close();
            fileIn.close();

            return importedMaterial;
        }
        catch (FileNotFoundException error) {
            System.out.println("\nFile " + filePath + " not found.");
            error.printStackTrace();
        }
        catch (IOException error) {
            System.out.println("\nError occurred while reading file " + filePath);
            error.printStackTrace();
        }
        catch (ClassNotFoundException error) {
            System.out.println("\nCould not read class from file " + filePath);
            error.printStackTrace();
        }
        return null;
    }

}
