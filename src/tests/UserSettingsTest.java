package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Profile;
import model.UserSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * tests.UserSettingsTest.java
 *
 * @author Maple Gunn
 *
 */
public class UserSettingsTest {

    private UserSettings myDefaultUserSettings;

    /**
     * Makes a new UserSetting before any tests.
     */
    @BeforeEach
    void setUp() throws Exception {
        myDefaultUserSettings = new UserSettings();
    }

    /**
     * Tests default profile values
     */
    @Test
    void testProfile() {
        final Profile defaultProfile = new Profile();

        assertEquals(defaultProfile.toString(), "No name registered, No email registered",
                "This assert tests the default Profile");
    }

}
