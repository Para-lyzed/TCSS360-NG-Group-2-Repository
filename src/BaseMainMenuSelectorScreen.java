import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * BaseMainMenuScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseMainMenuSelectorScreen extends BaseMainMenuScreen {

    protected static final JLabel searchLabel = new JLabel("Search: ");
    protected JTextField searchBar;
    protected JScrollPane scrollablePane;
    // protected String[] recentFiles = { "Value 1", "Value 2", "Value 3", "Value 4", "Value 5", "Value 6", "Value 7", "Value 8", "Value 9", "Value 10", "Value 11", "Value 12", "Value 13", "Value 14", "Value 15", "Value 16" };
    protected String[] recentFiles = { "" };
    protected JList<String> listPane;
    protected JButton createButton;

    public BaseMainMenuSelectorScreen(int width, int height, String title, String createButtonName) {
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
        this.listPane = new JList<String>(this.recentFiles);
        this.listPane.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    System.out.println(selectFile());
                }
            }
        });
        this.listPane.setFont(Main.BASE_FONT);
        this.listPane.setBackground(Color.LIGHT_GRAY);
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
        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create " + createButtonName);
            }
        });
        this.c.fill = GridBagConstraints.NONE;
        this.c.gridy++;
        this.c.weighty = 1;
        this.add(createButton, c);
    }

    private String selectFile() {
        return this.listPane.getSelectedValue();
    }

}