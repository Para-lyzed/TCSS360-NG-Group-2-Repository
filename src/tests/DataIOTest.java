package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.DataIO;
import model.Profile;
import model.Tool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * tests.DataIOTest.java
 *
 * @author Cody Dukes
 *
 */
public class DataIOTest {
    private Tool myDefaultTool;
    private Profile myFakeProfile;
    private Path saveToolPath = Path.of("tooldir.testTool.tool");

    @BeforeEach
    void setUp() throws Exception {
        myDefaultTool = new Tool("Toy-Hammer", 15);
        myFakeProfile = new Profile("John Baker", "jBaker@email.com");
    }

    @Test
    void testSaveTool() {
        myDefaultTool.setDescription("A small rubber hammer");

        assertTrue(DataIO.saveTool(myDefaultTool, saveToolPath));
    }
}
