import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * BaseMainMenuScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseSelectorScreen extends BaseScreen {

    protected static final JLabel searchLabel = new JLabel("Search: ");
    protected static final JFileChooser fileChooser = new JFileChooser();
    protected JTextField searchBar;
    protected JScrollPane scrollablePane;
    // protected String[] recentFiles = { "Value 1", "Value 2", "Value 3", "Value 4", "Value 5", "Value 6", "Value 7", "Value 8", "Value 9", "Value 10", "Value 11", "Value 12", "Value 13", "Value 14", "Value 15", "Value 16" };
    protected ArrayList<String> recentFiles = new ArrayList<>();
    protected JList<String> listPane;
    protected JButton createButton;
    protected JButton importButton;

    public BaseSelectorScreen(int width, int height, String title, String createButtonName, String importButtonName) {
        super(width, height, title, 2);
        searchLabel.setFont(Main.BASE_FONT);
        this.c.fill = GridBagConstraints.NONE;
        this.c.gridwidth = 1;
        this.c.gridy++;
        this.add(searchLabel, c);
        this.searchBar = new JTextField();
        this.searchBar.setFont(Main.BASE_FONT);
        this.c.fill = GridBagConstraints.HORIZONTAL;
        this.c.gridx = 1;
        this.c.weightx = 50;
        this.add(this.searchBar, c);
        this.listPane = new JList<String>(this.recentFiles.toArray(new String[recentFiles.size()]));
        this.listPane.setFont(Main.BASE_FONT);
        this.listPane.setBackground(Main.SECONDARY_BACKGROUND);
        this.listPane.setOpaque(true);
        this.scrollablePane = new JScrollPane(this.listPane);
        this.c.fill = GridBagConstraints.BOTH;
        this.c.gridx = 0;
        this.c.gridy++;
        this.c.weighty = 20;
        this.c.gridwidth = 2;
        this.c.weightx = 1;
        this.add(this.scrollablePane, c);
        this.createButton = new JButton(createButtonName);
        this.createButton.setFont(Main.BASE_FONT);
        this.c.fill = GridBagConstraints.NONE;
        this.c.gridy++;
        this.c.gridwidth = 1;
        this.c.weighty = 1;
        this.add(createButton, c);
        this.importButton = new JButton(importButtonName);
        this.importButton.setFont(Main.BASE_FONT);
        this.c.gridx++;
        this.c.gridwidth = 1;
        this.add(importButton, c);
    }

}