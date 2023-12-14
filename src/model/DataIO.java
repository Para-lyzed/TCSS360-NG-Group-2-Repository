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
     * saveProgramData saves user data to a specified filePath.
     * @param filePath the filePath the data should be saved to.
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
            System.out.printf("\nFile " + filePath + " not found.", filePath);

        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
            error.printStackTrace();
        }
        return successfulSave;
    }

    /**
     * loadProgramData loads the data saved at a specified filePath.
     * @param filePath the filePath the data should be loaded from.
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
     * @param project the model.Project to be saved.
     * @param filePath a filePath chosen by the user.
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
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
            error.printStackTrace();
        }

        if (successfulSave) {
            Main.userSettings.updateMostRecent(project.getName(), filePath, 0);
        }

        return successfulSave;
    }

    /**
     * Loads a project from the given location.
     * @param filePath a filePath chosen by the user.
     * @return a model.Profile that has been deserialized from the given filePath.
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

    /**
     * Saves a tool to the given location.
     * @param tool the model.Tool to be saved.
     * @param filePath a filePath chosen by the user.
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
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
            error.printStackTrace();
        }

        if (successfulSave) {
            Main.userSettings.updateMostRecent(tool.getName(), filePath, 1);
        }

        return successfulSave;
    }

    /**
     * Loads a tool from the given location.
     * @param filePath a filePath chosen by the user.
     * @return a model.Tool that has been deserialized from the given filePath.
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

    /**
     * Saves a material to the given location.
     * @param material the model.Material to be saved.
     * @param filePath a filePath chosen by the user.
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
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
            error.printStackTrace();
        }

        if (successfulSave) {
            Main.userSettings.updateMostRecent(material.getName(), filePath, 2);
        }

        return successfulSave;
    }

    /**
     * Loads a material from the given location.
     * @param filePath a filePath chosen by the user.
     * @return a model.Material that has been deserialized from the given filePath.
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

    /**
     * exportAll exports all data to a specified filePath.
     * @param filePath the filePath the data should be exported to.
     * @return boolean that is false if exporting encountered an error.
     *
     * @author Cody Dukes
     */
    public boolean exportAll(Path filePath) {
        boolean successfulExport = false;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(Main.userSettings.getRecentProjectsList());
            objectOut.writeObject(Main.userSettings.getRecentToolsList());
            objectOut.writeObject(Main.userSettings.getRecentMaterialsList());

            objectOut.close();
            fileOut.close();

            successfulExport = true;
        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while exporting to file " + filePath, filePath);
            error.printStackTrace();
        }

        return successfulExport;
    }

    /**
     * importAll imports all data to a specified filePath.
     * @param filePath the filePath the data should be imported from.
     * @return boolean that is false if importing encountered an error.
     *
     * @author Cody Dukes
     */
    public boolean importAll(Path filePath) {
        boolean successfulImport = false;
        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Main.userSettings = (UserSettings) objectIn.readObject();
            Main.userSettings.verifySelf();
            About.updateProfile(Main.userSettings.getProfile());

            objectIn.close();
            fileIn.close();

            successfulImport = true;
        }
        catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);
        }
        catch (IOException error) {
            System.out.printf("\nError occurred while importing file " + filePath, filePath);
        }
        catch (ClassNotFoundException error) {
            System.out.printf("\nCould not read class from file " + filePath, filePath);
        }
        return successfulImport;
    }
}
