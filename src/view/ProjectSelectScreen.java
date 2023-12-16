package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import model.DataIO;
import model.Main;
import model.UserSettings;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectSelectScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectSelectScreen extends BaseSelectorScreen {
    private static final String title = "Projects";
    private static final String type = "Project";
    private static final String fileExtension = "proj";
    private static final int userSettingsType = UserSettings.PROJECT;

    /**
     * Constructs a Project screen that the user can use to select or create a Project.
     * 
     * @param width the width of the window.
     * @param height the height of the window.
     * 
     * @author Nathan Grimsey
     */
    public ProjectSelectScreen(int width, int height) {
        super(width, height, title, type, userSettingsType, fileExtension);
        importButton.addActionListener(new ActionListener() {
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
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = listPane.getSelectedValue();
                try {
                    Main.BASE_FRAME.openProject(DataIO.loadProject(Main.userSettings.getFilePathFromName(fileName, userSettingsType)));
                }
                catch (Exception error) {
                    Main.userSettings.removeFromRecent(fileName, userSettingsType);
                    error.printStackTrace();
                }
            }
        });
    }

    /**
     * Refreshes the list of recent files to be displayed when there is a change.
     * 
     * @author Nathan Grimsey
     */
    public void refresh() {
        recentFiles = Main.userSettings.getRecentFilesList(userSettingsType);
        updateListPaneEntries();
    }
}
