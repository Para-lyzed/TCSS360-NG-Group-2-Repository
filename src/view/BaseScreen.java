package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Main;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.BaseScreen.java
 * 
 * @author Nathan Grimsey
 *
 */
public class BaseScreen extends JPanel {
    protected String title;
    protected JLabel heading;
    protected String headingUnspaced;
    protected String headingSpaced;
    protected GridBagConstraints c;

    public BaseScreen(int width, int height, String title, int headingGridWidth) {
        this.title = title;
        setBackground(Main.BACKGROUND);
        setBounds(0, 0, width, height);
        setOpaque(true);
        headingUnspaced = title;
        headingSpaced = Main.HEADING_SPACER + title;
        heading = new JLabel();
        heading.setFont(Main.HEADING_ONE_FONT);
        heading.setText(headingSpaced);
        heading.setForeground(Main.TEXT);
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = Main.PADDING_INSETS;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = headingGridWidth;
        add(heading, c);
    }

    /**
     * menuHeading gets rid of the spacing on the header if the menu is open.
     * 
     * @param isMenuOpen is if the menu is open.
     * 
     * @author Nathan Grimsey
     */
    public void menuHeading(boolean isMenuOpen) {
        if (isMenuOpen) {
            heading.setText(headingUnspaced);
        }
        else {
            heading.setText(headingSpaced);
        }
    }

    public void darkMode() {
        setBackground(Main.BACKGROUND);
        heading.setForeground(Main.TEXT);
    }
    
}
