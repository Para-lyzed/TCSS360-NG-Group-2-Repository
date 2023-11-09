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

    public About(Profile owner) {
        this.owner = owner;
    }

    public Profile getOwner() {
        return this.owner;
    }

    public String getOwnerString() {
        return this.owner.getName() + " (" + this.owner.getEmail() + ")";
    }

    public String getVersion() {
        return this.version;
    }

    public String[] getContributors() {
        return this.contributors;
    }

    public void updateProfile(String name, String email) {
        this.owner = new Profile(name, email);
    }

}
