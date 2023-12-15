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
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridy++;
        add(searchLabel, c);
        searchBar = new JTextField();
        searchBar.setFont(Main.BASE_FONT);
        searchBar.setForeground(Main.TEXT);
        searchBar.setBackground(Main.TEXT_BOX_BACKGROUND);
        searchBar.setCaretColor(Main.CARET);
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
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy++;
        c.weighty = 20;
        c.gridwidth = 4;
        c.weightx = 1;
        add(scrollablePane, c);
        openButton = new JButton("Open");
        openButton.setFont(Main.BASE_FONT);
        openButton.setForeground(Main.TEXT);
        openButton.setBackground(Main.BUTTON_BACKGROUND);
        openButton.setEnabled(false);
        c.fill = GridBagConstraints.NONE;
        c.gridy++;
        c.gridwidth = 1;
        c.weighty = 1;
        add(openButton, c);
        deleteButton = new JButton("Delete");
        deleteButton.setFont(Main.BASE_FONT);
        deleteButton.setForeground(Main.TEXT);
        deleteButton.setBackground(Main.BUTTON_BACKGROUND);
        deleteButton.setEnabled(false);
        c.gridx++;
        add(deleteButton, c);
        createButton = new JButton("New " + type);
        createButton.setFont(Main.BASE_FONT);
        createButton.setForeground(Main.TEXT);
        createButton.setBackground(Main.BUTTON_BACKGROUND);
        c.gridx++;
        add(createButton, c);
        importButton = new JButton("Import " + type);
        importButton.setFont(Main.BASE_FONT);
        importButton.setForeground(Main.TEXT);
        importButton.setBackground(Main.BUTTON_BACKGROUND);
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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmDialog.setVisible(true);
            }
        });
        fileChooser.setFileFilter(new FileNameExtensionFilter("MPP " + type + " File", fileExtension));
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
        searchBar.setCaretColor(Main.CARET);
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