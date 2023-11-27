/**
 * TCSS 360B
 * Team Deliverable - Iteration 2
 * About.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class About {
    private static Profile owner = new Profile();
    private static final String[] contributors = {"Nathan Grimsey", "Maple Gunn", "Cody Dukes"};
    private static final String version = "0.1";

    /**
     * getOwner gets the owner registered to the app.
     * @return owner of the app.
     * @author Nathan Grimsey
     * @author Maple Gunn
     */
    public static Profile getOwner() {
        return owner;
    }

    /**
     * getOwnerString returns a string with the owner name and email.
     * @return Owner name and email as a string.
     * @author Nathan Grimsey
     */
    public static String getOwnerString() {
        return owner.getName() + " (" + owner.getEmail() + ")";
    }

    /**
     * getVersion returns app version.
     * @return app version.
     * @author Nathan Grimsey
     * @author Maple Gunn
     */
    public static String getVersion() {
        return version;
    }

    /**
     * getContributers returns project contributors.
     * @return project contributors.
     * @author Nathan Grimsey
     */
    public static String[] getContributors() {
        return contributors;
    }

    /**
     * updateProfile sets new Profile for owner.
     * @param name is the new name.
     * @param email is the new email.
     * @author Nathan Grimsey
     */
    public static void updateProfile(String name, String email) {
        owner.set(name, email);
    }

    /**
     * updateProfile sets new Profile for owner.
     * @param profile the Profile to update owner with.
     * @author Nathan Grimsey
     */
    public static void updateProfile(Profile profile) {
        owner = profile;
    }
}
