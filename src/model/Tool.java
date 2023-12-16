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

    /**
     * Tool is an expense into an object that the user can reuse in future Projects 
     * without having to add its price.
     * 
     * @param name tool name.
     * @param price tool price.
     * 
     * @author Maple Gunn
     */
    public Tool(String name, int price) {
        super(name, "Tool", price, 1);
    }

}