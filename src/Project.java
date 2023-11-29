/**
 * TCSS 360B
 * Project.java
 *
 * @author Maple Gunn
 *
 */

import com.sun.nio.file.ExtendedOpenOption;

import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

public class Project {
    private String name;
    private List<Expense> expenses;
    private List<Tool> tools;
    private List<Log> logs;
    private String description;
    private List<Image> images;

    // Constructor
    public Project(String name) {
        this.name = name;
        this.expenses = new ArrayList<>();
        this.tools = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    // Getter
    public String getName() {
        return name;
    }

    // Getter
    public List<Expense> getExpenses() {
        return expenses;
    }

    // Getter
    public List<Tool> getTools() {
        return tools;
    }

    // Getter
    public List<Log> getLogs() {
        return logs;
    }

    // Getter
    public String getDescription() {
        return description;
    }

    // Method
    public void addExpense(String category, String name, int quantity, int price) {
        // Implementation
    }

    // Method
    public void addExpense(Material material, int quantity) {
        Expense newExpense = (Expense) material;
        newExpense.setQuantity(quantity);
        expenses.add(newExpense);
    }

    // Method
    public void addExpense(Tool tool, int quantity) {
        Expense newExpense = (Expense) tool;
        newExpense.setQuantity(quantity);
        expenses.add(newExpense);
    }

    // Method
    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    // Method
    public void addLog(Log log) {
        this.logs.add(log);
    }

    // Method
    public List<Image> getImages() {
        // Implementation
        return images;
    }

    // Method
    public void addImage(Image image) {
        images.add(image);
    }

    // Method
    public void setImages(List<Image> images) {
        this.images = images;
    }

    // Method
    public void updateExpense(Expense expense, String category, String name, int quantity, int price) {
        Expense e;
        for (int i = 0; i < expenses.size()-1; i++) {

            if (expenses.get(i).compareTo(expense) == 0) {
                e = expenses.get(i);
                e.setCategory(category);
                e.setName(name);
                e.setQuantity(quantity);
                e.setPrice(price);
                break;
            }
        }
    }

    // Method
    public void updateExpense(Expense expense, Material material, int quantity) {
        Expense e;
        for (int i = 0; i < expenses.size()-1; i++) {

            if (expenses.get(i).compareTo(expense) == 0) {
                e = expenses.get(i);
                e.setCategory(material.getCategory());
                e.setName(material.getName());
                e.setQuantity(quantity);
                e.setPrice(material.getPrice());
                break;
            }
        }
    }


    // Method
    public void updateExpense(Expense expense, Tool tool, int quantity) {
        // Implementation
    }

    // Method
    public void removeExpense(Expense expense) {
        // Implementation
    }

    // Method
    public void removeTool(Tool tool) {
        // Implementation
    }

    // Method
    public void removeLog(Log log) {
        // Implementation
    }
}