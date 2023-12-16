package model;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.Material.java
 * 
 * @author Maple Gunn
 *
 */
public class Material extends Expense {

    /**
     * Material object is a type of expense the user can store outside projects.
     * 
     * @param name material name.
     * @param category material category.
     * @param price material price.
     * 
     * @author Maple Gunn
     */
    public Material(String name, String category, int price) {
        super(name, category, price, 1);

    }

}