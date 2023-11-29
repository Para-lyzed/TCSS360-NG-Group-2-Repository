
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.*;

/**
 * Test class for imoprting and exporting files.
 * @author Nathan Grimsey
 */
public class ImportExportTest {
    
    /**
     * Tests saving and loading a Profile.
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
