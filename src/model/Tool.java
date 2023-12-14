package model;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.Tool.java
 * 
 * @author Maple Gunn
 *
 */
public class Tool extends Expense {

    // Constructor
    public Tool(String name, int price) {
        // Call the constructor of the superclass (model.Expense)
        super(name, "model.Tool", price, 1);
    }

    // Additional methods or overrides can be added here as needed
    // No need to re-implement getters and setters, as they are inherited
}