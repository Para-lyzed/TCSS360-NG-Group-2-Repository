package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * tests.ProfileTest.java
 *
 * @author Cody Dukes
 *
 */
public class ProfileTest {
    private Profile myDefaultProfile;

    /**
     * Creates a profile before each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        myDefaultProfile = new Profile("John Baker", "jBaker@email.com");
    }

    /**
     * Tests getName.
     */
    @Test
    void testProfileName() {
        final String expectedName = "John Baker";

        assertEquals(expectedName, myDefaultProfile.getName(),
                "This assert tests the default Profile name");
    }

    /**
     * Tests getEmail.
     */
    @Test
    void testProfileEmail() {
        final String expectedEmail = "jBaker@email.com";

        assertEquals(expectedEmail, myDefaultProfile.getEmail(),
                "This assert tests the default Profile email");
    }

    /**
     * Tests setProfile by using 2 strings.
     */
    @Test
    void testSetProfile() {
        final String expectedName = "New Name";
        final String expectedEmail = "newEmail@email.com";

        myDefaultProfile.set("New Name", "newEmail@email.com");

        assertEquals(expectedName, myDefaultProfile.getName(),
                "This assert tests the ability to set a Profile name");

        assertEquals(expectedEmail, myDefaultProfile.getEmail(),
                "This assert tests the ability to set a Profile email");
    }

    /**
     * Tests if two profiles return as equal.
     */
    @Test
    void testProfileEqualsTrue() {
        final boolean expectedEquals = true;
        Profile mySecondProfile = new Profile("John Baker", "jBaker@email.com");

        assertEquals(expectedEquals, myDefaultProfile.equals(mySecondProfile));
    }

    /**
     * Tests whether two profiles should NOT be equal.
     */
    @Test
    void testProfileEqualsFalse() {
        final boolean expectedEquals = false;
        Profile mySecondProfile = new Profile("NOT John Baker", "NOTjBaker@email.com");

        assertEquals(expectedEquals, myDefaultProfile.equals(mySecondProfile));
    }
}
