package view;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

    private static final JLabel confirmDialogLabel = new JLabel("Are you sure?");
    private final JButton confirmDialogYesButton = new JButton("Yes");
    private final JButton confirmDialogNoButton = new JButton("No");
    private static JDialog confirmDialog;
    private final JLabel searchLabel = new JLabel("Search: ");

    protected final JFileChooser fileChooser = new JFileChooser();
    protected JTextField searchBar;
    protected JScrollPane scrollablePane;
    // TODO: Remove this when no longer necessary
    // protected String[] recentFiles = { "Value 1", "Value 2", "Value 3", "Value 4", "Value 5", "Value 6", "Value 7", "Value 8", "Value 9", "Value 10", "Value 11", "Value 12", "Value 13", "Value 14", "Value 15", "Value 16" };
    protected ArrayList<String> recentFiles = new ArrayList<>();
    protected JList<String> listPane;
    protected JButton openButton;
    protected JButton deleteButton;
    protected JButton createButton;
    protected JButton importButton;

    public BaseSelectorScreen(int width, int height, String title, String type, int userSettingsType, String fileExtension) {
        super(width, height, title, 4);
        searchLabel.setFont(Main.BASE_FONT);
        searchLabel.setForeground(Main.TEXT);
        this.c.fill = GridBagConstraints.NONE;
        this.c.gridwidth = 1;
        this.c.gridy++;
        this.add(searchLabel, c);
        this.searchBar = new JTextField();
        this.searchBar.setFont(Main.BASE_FONT);
        this.searchBar.setForeground(Main.TEXT);
        this.searchBar.setBackground(Main.TEXT_BOX_BACKGROUND);
        this.c.fill = GridBagConstraints.HORIZONTAL;
        this.c.gridx = 1;
        this.c.gridwidth = 3;
        this.c.weightx = 50;
        this.add(this.searchBar, c);
        this.listPane = new JList<String>(this.recentFiles.toArray(new String[recentFiles.size()]));
        this.listPane.setFont(Main.BASE_FONT);
        this.listPane.setForeground(Main.TEXT);
        this.listPane.setBackground(Main.SECONDARY_BACKGROUND);
        this.listPane.setOpaque(true);
        this.scrollablePane = new JScrollPane(this.listPane);
        this.c.fill = GridBagConstraints.BOTH;
        this.c.gridx = 0;
        this.c.gridy++;
        this.c.weighty = 20;
        this.c.gridwidth = 4;
        this.c.weightx = 1;
        this.add(this.scrollablePane, c);
        this.openButton = new JButton("Open");
        this.openButton.setFont(Main.BASE_FONT);
        this.openButton.setForeground(Main.TEXT);
        this.openButton.setBackground(Main.BUTTON_BACKGROUND);
        this.openButton.setEnabled(false);
        this.c.fill = GridBagConstraints.NONE;
        this.c.gridy++;
        this.c.gridwidth = 1;
        this.c.weighty = 1;
        this.add(openButton, c);
        this.deleteButton = new JButton("Delete");
        this.deleteButton.setFont(Main.BASE_FONT);
        this.deleteButton.setForeground(Main.TEXT);
        this.deleteButton.setBackground(Main.BUTTON_BACKGROUND);
        this.deleteButton.setEnabled(false);
        this.c.gridx++;
        this.add(deleteButton, c);
        this.createButton = new JButton("New " + type);
        this.createButton.setFont(Main.BASE_FONT);
        this.createButton.setForeground(Main.TEXT);
        this.createButton.setBackground(Main.BUTTON_BACKGROUND);
        this.c.gridx++;
        this.add(createButton, c);
        this.importButton = new JButton("Import " + type);
        this.importButton.setFont(Main.BASE_FONT);
        this.importButton.setForeground(Main.TEXT);
        this.importButton.setBackground(Main.BUTTON_BACKGROUND);
        this.c.gridx++;
        this.add(importButton, c);

        this.recentFiles = Main.userSettings.getRecentFilesList(userSettingsType);
        updateListPaneEntries();
        this.searchBar.addActionListener((ActionListener) new ActionListener() {
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
        this.listPane.addListSelectionListener(new ListSelectionListener() {
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
        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BASE_FRAME.switchScreen("Create a New " + type);
            }
        });
        confirmDialog = new JDialog();
        confirmDialog.setLocationRelativeTo(null);
        confirmDialog.setSize(200, 100);
        confirmDialog.setResizable(false);
        confirmDialog.setLayout(new FlowLayout());
        confirmDialog.setBackground(Main.BACKGROUND);
        confirmDialogLabel.setFont(Main.BASE_FONT);
        confirmDialogLabel.setForeground(Main.TEXT);
        confirmDialog.add(confirmDialogLabel);
        confirmDialogYesButton.setFont(Main.BASE_FONT);
        confirmDialogYesButton.setForeground(Main.TEXT);
        confirmDialogYesButton.setBackground(Main.BUTTON_BACKGROUND);
        confirmDialog.add(confirmDialogYesButton);
        confirmDialogNoButton.setFont(Main.BASE_FONT);
        confirmDialogNoButton.setForeground(Main.TEXT);
        confirmDialogNoButton.setBackground(Main.BUTTON_BACKGROUND);
        confirmDialog.add(confirmDialogNoButton);
        confirmDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        confirmDialog.setVisible(false);
        confirmDialogYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = listPane.getSelectedValue();
                File deleteFile = new File(Main.userSettings.getFilePathFromName(fileName, userSettingsType).toString());
                Main.userSettings.removeFromRecent(fileName, userSettingsType);
                recentFiles.remove(fileName);
                updateListPaneEntries();
                deleteFile.delete();
                confirmDialog.setVisible(false);
            }
        });
        confirmDialogNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmDialog.setVisible(false);
            }
        });
        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmDialog.setVisible(true);
            }
        });
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("MPP " + type + " File", fileExtension));
    }

    protected void updateListPaneEntries() {
        listPane.setListData(recentFiles.toArray(new String[recentFiles.size()]));
    }

    @Override
    public void darkMode() {
        super.darkMode();
        confirmDialog.setBackground(Main.BACKGROUND);
        confirmDialogLabel.setForeground(Main.TEXT);
        confirmDialogYesButton.setForeground(Main.TEXT);
        confirmDialogYesButton.setBackground(Main.BUTTON_BACKGROUND);
        confirmDialogNoButton.setForeground(Main.TEXT);
        confirmDialogNoButton.setBackground(Main.BUTTON_BACKGROUND);
        searchLabel.setForeground(Main.TEXT);
        searchBar.setForeground(Main.TEXT);
        searchBar.setBackground(Main.TEXT_BOX_BACKGROUND);
        listPane.setForeground(Main.TEXT);
        listPane.setBackground(Main.SECONDARY_BACKGROUND);
        openButton.setForeground(Main.TEXT);
        openButton.setBackground(Main.BUTTON_BACKGROUND);
        deleteButton.setForeground(Main.TEXT);
        deleteButton.setBackground(Main.BUTTON_BACKGROUND);
        createButton.setForeground(Main.TEXT);
        createButton.setBackground(Main.BUTTON_BACKGROUND);
        importButton.setForeground(Main.TEXT);
        importButton.setBackground(Main.BUTTON_BACKGROUND);
    }

}