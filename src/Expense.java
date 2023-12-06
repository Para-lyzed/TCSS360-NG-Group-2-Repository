import java.util.List;
import java.awt.Image;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * Expense.java creates an Expense object, which is inherited by tools and materials.
 * It contains information on expenses for projects, such as name, category,
 * and description.
 * 
 * @author Maple Gunn
 *
 */


public class Expense implements Comparable<Expense> {
    protected String name;
    protected String category;
    protected String description;
    protected int price;
    protected int quantity;
    protected List<Image> images;

    /**
     * Constructor for the Expense Object.
     * @param name is the name of the expense.
     * @param category is the expense category.
     * @param price is the price of the expense.
     * @param quantity is the quantity of that expense.
     *
     * @author Maple Gunn
     */
    public Expense(String name, String category, int price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Getter method for name
     * @return name of expense.
     *
     * @author Maple Gunn
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @author Maple Gunn
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for category.
     * @return category.
     *
     * @author Maple Gunn
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter method for category.
     *
     * @author Maple Gunn
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter method for description
     * @return category.
     *
     * @author Maple Gunn
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for category.
     *
     * @author Maple Gunn
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for price.
     * @return price.
     *
     * @author Maple Gunn
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter method for price
     *
     * @author Maple Gunn
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter method for quantity.
     * @return quantity
     *
     * @author Maple Gunn
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for quantity.
     *
     * @author Maple Gunn
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter method for images.
     * @return images.
     *
     * @author Maple Gunn
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Setter for images.
     *
     * @author Maple Gunn
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * Add image to expense.
     *
     * @author Maple Gunn
     */
    public void addImage(Image image) {
        images.add(image);
    }

    /**
     * Sets data for an expense.
     *
     * @param name
     * @param category
     * @param description
     * @param price
     * @param quantity
     *
     * @author Maple Gunn
     */
    public void set(String name, String category, String description, int price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Expense o) {
        int compareVal = 0;
        compareVal += this.name.compareTo(o.name);

        if (compareVal != 0) {
            return compareVal;
        }

        compareVal += this.category.compareTo(o.category);
        if (compareVal != 0) {
            return compareVal;
        }

        compareVal += this.price - o.price;
        return compareVal;
    }

}