import java.io.*;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 2
 * DataIO.java
 *
 * @author Cody Dukes
 *
 */

public class DataIO {
    public static void saveUser(String filename, Profile user) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(user);

            objectOut.close();
            fileOut.close();

        } catch (FileNotFoundException error) {
            System.out.printf("\nFile " + filename + " not found.", filename);

        } catch (IOException error) {
            System.out.printf("\nError occurred while writing to file " + filename, filename);
        }
    }

    public static Profile loadUser(String file){
        Profile user = null;

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            user = (Profile) objectIn.readObject();

            objectIn.close();
            fileIn.close();

        } catch (FileNotFoundException error) {
            System.out.printf("\nFile " + file + " not found.", file);

        } catch (IOException error) {
            System.out.printf("\nError occurred while reading file " + file, file);

        } catch (ClassNotFoundException error) {
            System.out.printf("\nCould not read class from file " + file, file);
        }

        return user;
    }
}
