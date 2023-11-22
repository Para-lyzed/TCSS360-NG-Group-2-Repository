import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BaseMainMenuScreen extends JPanel {
    protected JLabel heading;
    protected String headingUnspaced;
    protected String headingSpaced;
    protected GridBagConstraints c;

    public BaseMainMenuScreen(int width, int height, String title, int headingGridWidth) {
        setBackground(Main.BACKGROUND_COLOR);
        setBounds(0, 0, width, height);
        setOpaque(true);
        this.headingUnspaced = title;
        this.headingSpaced = Main.HEADING_SPACER + title;
        this.heading = new JLabel();
        this.heading.setFont(Main.HEADING_ONE_FONT);
        this.heading.setText(headingSpaced);;
        setLayout(new GridBagLayout());
        this.c = new GridBagConstraints();
        this.c.insets = Main.PADDING_INSETS;
        this.c.weightx = 1;
        this.c.weighty = 1;
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.anchor = GridBagConstraints.NORTHWEST;
        this.c.gridwidth = headingGridWidth;
        add(this.heading, this.c);
    }

    /**
     * menuHeading gets rid of the spacing on the header if the menu is open.
     * @param isMenuOpen is if the menu is open.
     */
    public void menuHeading(boolean isMenuOpen) {
        if (isMenuOpen) {
            this.heading.setText(this.headingUnspaced);
        }
        else {
            this.heading.setText(this.headingSpaced);
        }
    }
    
}
