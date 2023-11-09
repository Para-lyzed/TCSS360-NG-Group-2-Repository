import javax.swing.*;

import java.awt.Font;

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
    public static Font headingOneFont = new Font("Arial", Font.BOLD, 50);
    public static Font headingTwoFont = new Font("Arial", Font.BOLD, 36);
    public static Font menuFont = new Font("Arial", Font.BOLD, 32);
    public static Font spacerFont = new Font("Arial", Font.BOLD, 75);
    public static Font baseFont = new Font("Arial", Font.PLAIN, 24);
    public static final int menuWidth = 350;
    
    public static void main(String[] args) {

        JFrame baseFrame = new BaseFrame(1000, 600, new About(new Profile()));
        baseFrame.setVisible(true);

        }
    }




