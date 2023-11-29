import java.util.List;

public class Tool extends Expense {

    // Constructor
    public Tool(String name, int price) {
        // Call the constructor of the superclass (Expense)
        super(name, "Tool", price, 1);
    }

    // Additional methods or overrides can be added here as needed
    // No need to re-implement getters and setters, as they are inherited
}