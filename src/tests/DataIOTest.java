package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
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
    private Tool myTestTool;
    private Material myTestMaterial;
    private Profile myFakeProfile;
    private UserSettings myDefaultSettings;
    private Project myTestProject;
    private Path saveToolPath = Path.of("myTestTool.tool");
    private Path saveMaterialPath = Path.of("testMaterial.mat");
    private Path saveProjectPath = Path.of("testProject.proj");

    @BeforeEach
    void setUp() throws Exception {
        Main.userSettings = new UserSettings();

        myTestProject = new Project("Test-Project", 500);
        myTestTool = new Tool("Toy-Hammer", 15);
        myTestTool.setDescription("A small rubber hammer");
        myTestMaterial = new Material("Rope", "Binders", 6);
        myFakeProfile = new Profile("John Baker", "jBaker@email.com");
        myDefaultSettings = new UserSettings();
    }

    @AfterEach
    void finishUp() throws Exception {
        File deleteToolFile = new File(saveToolPath.toString());
        deleteToolFile.delete();

        File deleteMaterialFile = new File(saveMaterialPath.toString());
        deleteMaterialFile.delete();

        File deleteProjectFile = new File(saveProjectPath.toString());
        deleteProjectFile.delete();
    }

    @Test
    void testSaveProject() {
        assertTrue(DataIO.saveProject(myTestProject, saveProjectPath));
    }

    @Test
    void testLoadProject() {
        DataIO.saveProject(myTestProject, saveProjectPath);
        final String expectedName = myTestProject.getName();
        final int expectedBudget = myTestProject.getBudget();

        Project myLoadedProject;

        try {
            myLoadedProject = DataIO.loadProject(saveProjectPath);

            assertEquals(myLoadedProject.getName(), expectedName,
                    "This assert test that the loaded project name matches the saved projects name.");


            assertEquals(myLoadedProject.getBudget(), expectedBudget,
                    "This assert test that the loaded project budget matches the saved projects budget.");

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    @Test
    void testSaveTool() {
        assertTrue(DataIO.saveTool(myTestTool, saveToolPath));
    }

    @Test
    void testLoadTool() {
        DataIO.saveTool(myTestTool, saveToolPath);

        Tool myLoadedTool;

        try {
            myLoadedTool = DataIO.loadTool(saveToolPath);

            assertEquals(myTestTool.compareTo(myLoadedTool), 0);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    @Test
    void testSaveMaterial() {
        assertTrue(DataIO.saveMaterial(myTestMaterial, saveMaterialPath));
    }

    @Test
    void testLoadMaterial() {
        DataIO.saveMaterial(myTestMaterial, saveMaterialPath);

        Material myLoadedMaterial;

        try {
            myLoadedMaterial = DataIO.loadMaterial(saveMaterialPath);

            assertEquals(myTestMaterial.compareTo(myLoadedMaterial), 0);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
