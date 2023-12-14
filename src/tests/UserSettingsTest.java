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

    @BeforeEach
    void setUp() throws Exception {
        myDefaultUserSettings = new UserSettings();
    }

    @Test
    void testProfile() {
        final Profile defaultProfile = new Profile();

        assertEquals(defaultProfile.toString(), myDefaultUserSettings.getProfile().toString(),
                "This assert tests the default Profile");
    }

}
