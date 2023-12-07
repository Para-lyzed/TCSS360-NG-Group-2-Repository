import java.io.Serializable;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * Profile.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class Profile implements Serializable {
    private String name;
    private String email;

    /**
     * Profile constructs a profile object that contains a name and email.
     * @param name is a name.
     * @param email is an email.
     * @author Nathan Grimsey
     * @author Maple Gunn
     */
    public Profile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Profile constructs an empty profile as a base case.
     * @author Nathan Grimsey
     */
    public Profile() {
        this.name = "No name registered";
        this.email = "No email registered";
    }

    /**
     * getName returns name of profile.
     * @return name of profile.
     * @author Nathan Grimsey
     * @author Maple Gunn
     */
    public String getName() {
        return this.name;
    }

    /**
     * getEmail returns the email of profile.
     * @return email of profile.
     * @author Nathan Grimsey
     * @author Maple Gunn
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * sets the name and email of a Profile.
     * @param name name to set Profile to.
     * @param email email to set Profile to.
     * @author Nathan Grimsey
     */
    public void set(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * determines whether two Profiles are equal.
     * @param profile the Profile to compare to.
     * @return true if the Profiles are equal.
     */
    public boolean equals(Profile profile) {
        if (this.name.equals(profile.getName()) && this.email.equals(profile.getEmail())) {
            return true;
        }
        return false;
    }
    
}
