/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * ProjectScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectScreen extends BaseMainMenuScreen {
    // private static JLabel headingUnspaced = new JLabel("Projects");
    // private static JLabel headingSpaced = new JLabel(Main.headingSpacer + "Projects");
    private static final String title = "Projects";

    /**
     * Constructs a project screen that the user can use to select or create a project.
     * @param width the width of the window
     * @param height the height of the window
     */
    public ProjectScreen(int width, int height) {
        super(width, height, title, 1);
    }
}
