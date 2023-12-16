package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.About;
import model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * tests.AboutTest.java
 *
 * @author Cody Dukes
 *
 */

public class AboutTest {
    private Profile myDefaultProfile;
    private Profile myFakeProfile;

    @BeforeEach
    void setUp() throws Exception {
        myDefaultProfile = new Profile();
        myFakeProfile = new Profile("John Baker", "jBaker@email.com");
    }

    @Test
    void testAboutGetOwner() {
        final Profile expectedOwner = myDefaultProfile;

        assertEquals(expectedOwner.getName(), About.getOwner().getName(),
                "This assert tests the getOwner by comparing default names");

        assertEquals(expectedOwner.getEmail(), About.getOwner().getEmail(),
                "This assert tests the getOwner by comparing default emails");
    }

    @Test
    void testAboutGetOwnerString() {
        final String expectedString = "No name registered (No email registered)";

        assertEquals(expectedString, About.getOwnerString(),
                "This assert tests the getOwnerString method");
    }

    @Test
    void testAboutGetVersion() {
        final String expectedVersion = "1.0";

        assertEquals(expectedVersion, About.getVersion(),
                "This assert tests the getVersion method");
    }

    @Test
    void testAboutGetContributors() {
        final String[] expectedContributors = {"Nathan Grimsey", "Maple Gunn", "Cody Dukes"};

        for (int i = 0; i < expectedContributors.length; i++) {
            assertEquals(expectedContributors[i], About.getContributors()[i],
                    "This assert tests the getContributors method");
        }
    }

    @Test
    void testAboutUpdateProfile1() {
        final String expectedName = myFakeProfile.getName();
        final String expectedEmail = myFakeProfile.getEmail();

        About.updateProfile(expectedName, expectedEmail);

        assertEquals(expectedName, About.getOwner().getName(),
                "This assert tests if the updateProfile method updates the profile name");

        assertEquals(expectedEmail, About.getOwner().getEmail(),
                "This assert tests if the updateProfile method updates the profile email");
    }

    @Test
    void testAboutUpdateProfile2() {
        final String expectedName = myFakeProfile.getName();
        final String expectedEmail = myFakeProfile.getEmail();

        About.updateProfile(myFakeProfile);

        assertEquals(expectedName, About.getOwner().getName(),
                "This assert tests if the updateProfile method updates the profile name");

        assertEquals(expectedEmail, About.getOwner().getEmail(),
                "This assert tests if the updateProfile method updates the profile email");
    }
}
