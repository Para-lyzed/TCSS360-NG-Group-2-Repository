package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.BaseSelectorScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseSelectorScreen extends BaseScreen {

    private final JLabel searchLabel = new JLabel("Search: ");

    protected final JFileChooser fileChooser = new JFileChooser();
    protected CustomTextField searchBar;
    protected JScrollPane scrollablePane;
    // TODO: Remove this when no longer necessary
    // protected String[] recentFiles = { "Value 1", "Value 2", "Value 3", "Value 4", "Value 5", "Value 6", "Value 7", "Value 8", "Value 9", "Value 10", "Value 11", "Value 12", "Value 13", "Value 14", "Value 15", "Value 16" };
    protected ArrayList<String> recentFiles = new ArrayList<>();
    protected JList<String> listPane;
    protected CustomButton openButton;
    protected CustomButton deleteButton;
    protected CustomButton createButton;
    protected CustomButton importButton;

    /**
     * Parent class for all Selector screens. Sets up the scrollable panel, searching, 
     * and buttons.
     * 
     * @param width of the panel.
     * @param height of the panel.
     * @param title of the panel.
     * @param type String representation to set button to "New <type>" or "Import <type>".
     * @param userSettingsType int constant used for UserSettings methods.
     * @param fileExtension String of file extension.
     * 
     * @author Nathan Grimsey
     */
    public BaseSelectorScreen(int width, int height, String title, String type, int userSettingsType, String fileExtension) {
        super(width, height, title, 4);
        searchLabel.setFont(Main.BASE_FONT);
        searchLabel.setForeground(Main.TEXT);
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridy++;
        add(searchLabel, c);
        searchBar = new CustomTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridwidth = 3;
        c.weightx = 50;
        add(searchBar, c);
        listPane = new JList<String>(recentFiles.toArray(new String[recentFiles.size()]));
        listPane.setFont(Main.BASE_FONT);
        listPane.setForeground(Main.TEXT);
        listPane.setBackground(Main.SECONDARY_BACKGROUND);
        listPane.setOpaque(true);
        scrollablePane = new JScrollPane(listPane);
        scrollablePane.setBorder(Main.BORDER);
        scrollablePane.getVerticalScrollBar().setUI(Main.SCROLL_BAR);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy++;
        c.weighty = 20;
        c.gridwidth = 4;
        c.weightx = 1;
        add(scrollablePane, c);
        openButton = new CustomButton("Open");
        openButton.setEnabled(false);
        c.fill = GridBagConstraints.NONE;
        c.gridy++;
        c.gridwidth = 1;
        c.weighty = 1;
        add(openButton, c);
        deleteButton = new CustomButton("Delete");
        deleteButton.setEnabled(false);
        c.gridx++;
        add(deleteButton, c);
        createButton = new CustomButton("New " + type);
        c.gridx++;
        add(createButton, c);
        importButton = new CustomButton("Import " + type);
        c.gridx++;
        add(importButton, c);

        recentFiles = Main.userSettings.getRecentFilesList(userSettingsType);
        updateListPaneEntries();
        searchBar.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchBar.getText();
                if (searchText.isEmpty()){
                    recentFiles = Main.userSettings.getRecentFilesList(userSettingsType);
                }
                else {
                    recentFiles = Main.searchFiles(searchText, userSettingsType);
                }
                updateListPaneEntries();
            }
        });
        listPane.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listPane.getSelectedValue() != null) {
                    openButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                }
                else {
                    openButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BASE_FRAME.switchScreen("Create a New " + type);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = listPane.getSelectedValue();
                File deleteFile = new File(Main.userSettings.getFilePathFromName(fileName, userSettingsType).toString());
                Main.userSettings.removeFromRecent(fileName, userSettingsType);
                recentFiles.remove(fileName);
                updateListPaneEntries();
                deleteFile.delete();
            }
        });
        fileChooser.setFileFilter(new FileNameExtensionFilter("MPP " + type + " File", fileExtension));
    }

    /**
     * Updates the entries in listPane.
     * 
     * @author Nathan Grimsey
     */
    protected void updateListPaneEntries() {
        listPane.setListData(recentFiles.toArray(new String[recentFiles.size()]));
    }

    @Override
    public void darkMode() {
        super.darkMode();
        searchLabel.setForeground(Main.TEXT);
        searchBar.darkMode();
        listPane.setForeground(Main.TEXT);
        listPane.setBackground(Main.SECONDARY_BACKGROUND);
        openButton.darkMode();
        deleteButton.darkMode();
        createButton.darkMode();
        importButton.darkMode();
    }

}