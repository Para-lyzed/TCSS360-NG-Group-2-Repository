/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * Profile.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class Profile {
    private String name;
    private String email;


    public Profile(String name, String email) {
        this.name = name;
        this.email = email;

    }

    public Profile() {
        this.name = "No name registered";
        this.email = "No email registered";
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
    
}
