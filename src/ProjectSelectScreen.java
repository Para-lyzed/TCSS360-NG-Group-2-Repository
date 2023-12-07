import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * ProjectSelectScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectSelectScreen extends BaseMainMenuSelectorScreen {
    private static final String title = "Projects";
    private static final String newButtonName = "New Project";

    /**
     * Constructs a project screen that the user can use to select or create a project.
     * @param width the width of the window
     * @param height the height of the window
     * @author Nathan Grimsey
     */
    public ProjectSelectScreen(int width, int height) {
        super(width, height, title, newButtonName);
        // this.recentFiles = UserSettings.getRecentProjectsList.toArray();
        // this.listPane.setListData(this.recentFiles)
        this.searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(searchBar.getText());
                // this.recentFiles = Main.searchProject(searchBar.getText()).toArray();
            }
        });
        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BASE_FRAME.switchScreen("Create a New Project");
            }
        });
    }
}
