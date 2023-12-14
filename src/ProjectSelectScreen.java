import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * ProjectSelectScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectSelectScreen extends BaseSelectorScreen {
    private static final String title = "Projects";
    private static final String newButtonName = "New Project";
    private static final String importButtonName = "Import Project";

    /**
     * Constructs a project screen that the user can use to select or create a project.
     * @param width the width of the window
     * @param height the height of the window
     * @author Nathan Grimsey
     */
    public ProjectSelectScreen(int width, int height) {
        super(width, height, title, newButtonName, importButtonName);
        this.recentFiles = Main.userSettings.getRecentProjectsList();
        this.listPane.setListData(this.recentFiles.toArray(new String[this.recentFiles.size()]));
        this.searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println(searchBar.getText());
                String searchText = searchBar.getText();
                if (searchText.isEmpty()){
                    recentFiles = Main.userSettings.getRecentProjectsList();
                }
                else {
                    recentFiles = Main.searchProject(searchText);
                }
                listPane.setListData(recentFiles.toArray(new String[recentFiles.size()]));
            }
        });
        this.listPane.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String projectName = listPane.getSelectedValue();
                    Path projectPath = Main.userSettings.getFilePathFromName(projectName, 0);
                    if (projectPath != null) {
                        try {
                            Main.BASE_FRAME.openProject(DataIO.loadProject(projectPath));
                        }
                        catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                    else {
                        Main.userSettings.removeProject(projectName, 0);
                    }
                }
            }
        });
        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BASE_FRAME.switchScreen("Create a New Project");
            }
        });
        fileChooser.setFileFilter(new FileNameExtensionFilter("MPP Project File", "proj"));
        this.importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setSelectedFile(new File(""));
                int returnVal;
                returnVal = fileChooser.showOpenDialog(Main.BASE_FRAME);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        Main.BASE_FRAME.openProject(DataIO.loadProject(fileChooser.getSelectedFile().toPath()));
                    }
                    catch (Exception error) {
                        error.printStackTrace();
                    }
                }
            }
        });
    }

    public void refresh() {
        recentFiles = Main.userSettings.getRecentProjectsList();
        listPane.setListData(recentFiles.toArray(new String[recentFiles.size()]));
    }
}
