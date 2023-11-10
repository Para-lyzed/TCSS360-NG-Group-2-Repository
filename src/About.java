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
    private Profile owner;
    private String[] contributors = {"Nathan Grimsey", "Maple Gunn", "Cody Dukes"};
    private String version = "0.1";

    /**
     * About constructs an about object that stores an owner, version number, and contributors.
     * @param owner is the apps user.
     */
    public About(Profile owner) {
        this.owner = owner;
    }

    /**
     * getOwner gets the owner registered to the app.
     * @return owner of the app.
     */
    public Profile getOwner() {
        return this.owner;
    }

    /**
     * getOwnerString returns a string with the owner name and email.
     * @return Owner name and email as a string.
     */
    public String getOwnerString() {
        return this.owner.getName() + " (" + this.owner.getEmail() + ")";
    }

    /**
     * getVersion returns app version.
     * @return app version.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * getContributers returns project contributors.
     * @return project contributors.
     */
    public String[] getContributors() {
        return this.contributors;
    }

    /**
     * updateProfile sets new name and email for owner.
     * @param name is the new name.
     * @param email is the new email.
     */
    public void updateProfile(String name, String email) {
        this.owner = new Profile(name, email);
    }

}
