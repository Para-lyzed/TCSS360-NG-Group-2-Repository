/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * About.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class About {
    private static Profile owner = new Profile();
    private static String[] contributors = {"Nathan Grimsey", "Maple Gunn", "Cody Dukes"};
    private static String version = "0.1";

    /**
     * getOwner gets the owner registered to the app.
     * @return owner of the app.
     */
    public static Profile getOwner() {
        return owner;
    }

    /**
     * getOwnerString returns a string with the owner name and email.
     * @return Owner name and email as a string.
     */
    public static String getOwnerString() {
        return owner.getName() + " (" + owner.getEmail() + ")";
    }

    /**
     * getVersion returns app version.
     * @return app version.
     */
    public static String getVersion() {
        return version;
    }

    /**
     * getContributers returns project contributors.
     * @return project contributors.
     */
    public static String[] getContributors() {
        return contributors;
    }

    /**
     * updateProfile sets new name and email for owner.
     * @param name is the new name.
     * @param email is the new email.
     */
    public static void updateProfile(String name, String email) {
        owner = new Profile(name, email);
    }

}
