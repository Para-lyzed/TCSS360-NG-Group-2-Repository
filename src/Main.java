import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

/**
 * TCSS 360B
 * Team Deliverable - Iteration 1
 * Main.java
 * 
 * @author Nathan Grimsey
 * @author Maple Gunn
 *
 */
public class Main {
    public static final String HEADING_SPACER = "     ";
    public static final Font HEADING_ONE_FONT = new Font("Arial", Font.BOLD, 50);
    public static final Font HEADING_TWO_FONT = new Font("Arial", Font.BOLD, 36);
    public static final Font MENU_FONT = new Font("Arial", Font.BOLD, 32);
    public static final Font SPACER_FONT = new Font("Arial", Font.BOLD, 75);
    public static final Font BASE_FONT = new Font("Arial", Font.PLAIN, 24);
    public static final Font VERSION_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final int MENU_WIDTH = 350;
    public static final int PADDING = 20;
    public static final Insets MENU_INSETS = new Insets(PADDING, PADDING, PADDING, PADDING);
    public static final Insets PADDING_INSETS = new Insets(PADDING / 2, PADDING, PADDING, PADDING);
    public static Color BACKGROUND_COLOR = Color.WHITE;

    public static void main(String[] args) {

        JFrame baseFrame = new BaseFrame(1000, 600);
        baseFrame.setVisible(true);

        }
    }




