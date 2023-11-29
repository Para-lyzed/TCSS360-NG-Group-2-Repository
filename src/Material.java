import java.util.List;

public class Material extends Expense {

    // Constructor
    public Material(String name, String category, int price) {
        super(name, category, price, 1);

    }

    // Additional methods or overrides can be added here as needed
    // No need to re-implement getters and setters, as they are inherited
}