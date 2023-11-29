import java.util.List;
import java.awt.Image;
public class Expense implements Comparable<Expense> {
    protected String name;
    protected String category;
    protected String description;
    protected int price;
    protected int quantity;
    protected List<Image> images;

    // Constructor
    public Expense(String name, String category, int price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    // Getter
    public String getCategory() {
        return category;
    }

    // Setter
    public void setCategory(String category) {
        this.category = category;
    }

    // Getter
    public String getDescription() {
        return description;
    }

    // Setter
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter
    public int getPrice() {
        return price;
    }

    // Setter
    public void setPrice(int price) {
        this.price = price;
    }

    // Getter
    public int getQuantity() {
        return quantity;
    }

    // Setter
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter
    public List<Image> getImages() {
        return images;
    }

    // Setter
    public void setImages(List<Image> images) {
        this.images = images;
    }

    // Method
    public void addImage(Image image) {
        // Implementation
    }

    // Method
    public void set(String name, String category, String description, int price, int quantity) {
        // Implementation
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