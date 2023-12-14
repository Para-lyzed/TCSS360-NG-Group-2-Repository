package model;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.*;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.ImportExportTest.java
 * 
 * @author Nathan Grimsey
 */
public class ImportExportTest {
    
    /**
     * Tests saving and loading a model.Profile.
     * @author Nathan Grimsey
     */
    @Test
    public void testSaveAndLoadProfile() {
        final Profile testProfile = new Profile("Testname", "Testemail");
        final Path testPath = Paths.get("src/testdir/programdata.mpp");
        About.updateProfile(testProfile);
        DataIO.saveProgramData(testPath);
        About.updateProfile(new Profile());
        DataIO.loadProgramData(testPath);
        Assertions.assertTrue(testProfile.equals(About.getOwner()));
    }
    
}
