
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.*;

public class ImportExportTest {
    
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
