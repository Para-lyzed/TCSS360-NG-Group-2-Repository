import java.io.*;
import java.nio.file.Path;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 2
 * DataIO.java
 *
 * @author Cody Dukes
 *
 */

public class DataIO {
    /**
     * saveProgramData saves user data to a specified filePath.
     * @param filePath the filePath the data should be saved to.
     * @author Cody Dukes
     */
    public static void saveProgramData(Path filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(About.getOwner());

            objectOut.close();
            fileOut.close();

        } catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);

        } catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filePath, filePath);
        }
    }

    /**
     * loadProgramData loads the data saved at a specified filePath.
     * @param filePath the filePath the data should be saved to.
     * @author Cody Dukes
     */
    public static Profile loadProgramData(Path filePath){
        Profile user = null;

        try {
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            user = (Profile) objectIn.readObject();

            objectIn.close();
            fileIn.close();

        } catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filePath + " not found.", filePath);

        } catch (IOException error) {
            System.out.printf("\nError occurred while reading file " + filePath, filePath);

        } catch (ClassNotFoundException error) {
            System.out.printf("\nCould not read class from file " + filePath, filePath);
        }

        return user;
    }

//    public boolean saveProject(Project project, Path filePath) { return false; }
//    public boolean loadProject(Project project, Path filePath) { return false; }
//    public boolean saveTool(Tool tool, Path filePath){ return false; }
//    public boolean loadTool(){ return false; }
//    public boolean saveMaterial(Material material, Path filePath) { return false; }
//    public boolean loadMaterial() { return false; }
//    public boolean exportAll(Path filePath) { return false; }
//    public boolean importAll(Path filePath) { return false; }

}
