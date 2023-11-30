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

/**
 *
 */
public class Project {
    private String name;
    private List<Expense> expenses;
    private List<Tool> tools;
    private List<Log> logs;
    private String description;
    private List<Image> images;

    /**
     * Constructor for project objects
     * @param name is the name of the project
     */
    public Project(String name) {
        this.name = name;
        this.expenses = new ArrayList<>();
        this.tools = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    /**
     * Getter method for name
     * @return project name
     * @Author Maple
     */
    public String getName() {
        return name;
    }

    /**
     * Gets list of expenses
     * @return list of expenses
     * @Author Maple
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Getter for tools
     * @return tools
     * @Author Maple
     */
    public List<Tool> getTools() {
        return tools;
    }

    /**
     * Getter for logs
     * @return logs
     * @Author Maple
     */
    public List<Log> getLogs() {
        return logs;
    }

    /**
     * Getter for description
     * @return description
     * @Author Maple
     */
    public String getDescription() {
        return description;
    }

    /**
     * Add an expense to the project
     * @Author Maple
     */
    public void addExpense(String category, String name, int quantity, int price) {
        Expense newExpense = new Expense(name, category, price, quantity);
        expenses.add(newExpense);
    }

    /**
     * Add a material to the project
     * @Author Maple
     */
    public void addExpense(Material material, int quantity) {
        Expense newExpense = (Expense) material;
        newExpense.setQuantity(quantity);
        expenses.add(newExpense);
    }

    /**
     * Add a tool to the project
     * @Author Maple
     */
    public void addExpense(Tool tool, int quantity) {
        Expense newExpense = (Expense) tool;
        newExpense.setQuantity(quantity);
        expenses.add(newExpense);
    }

    /**
     * Add a tool to the project
     * @Author Maple
     */
    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    /**
     * Add a log to the project
     * @Author Maple
     */
    public void addLog(Log log) {
        this.logs.add(log);
    }

    /**
     * Get images
     * @return Images
     * @Author Maple
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Add images to project.
     * @Author Maple
     */
    public void addImage(Image image) {
        images.add(image);
    }

    /**
     * Set an image in the list of images
     * @Author Maple
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * Update an expense
     * @Author Maple
     */
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

    /**
     * Update an expense to a material
     * @Author Maple
     */
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


    /**
     * Update an expense to a tool
     * @Author Maple
     */
    public void updateExpense(Expense expense, Tool tool, int quantity) {
        Expense e;
        for (int i = 0; i < expenses.size()-1; i++) {

            if (expenses.get(i).compareTo(expense) == 0) {
                e = expenses.get(i);
                e.setCategory(tool.getCategory());
                e.setName(tool.getName());
                e.setQuantity(quantity);
                e.setPrice(tool.getPrice());
                break;
            }
        }
    }

    /**
     * Remove an expense
     * @Author Maple
     */
    public void removeExpense(Expense expense) {
        Expense e;
        for (int i = 0; i < expenses.size()-1; i++) {

            if (expenses.get(i).compareTo(expense) == 0) {
                expenses.remove(i);
                break;
            }
        }
    }

    /**
     * Remove a tool
     * @Author Maple
     */
    public void removeTool(Tool tool) {
        // Implementation
        Expense e;
        for (int i = 0; i < tools.size()-1; i++) {

            if (tools.get(i).compareTo(tool) == 0) {
                tools.remove(i);
                break;
            }
        }
    }

/* Logs haven't been implemented yet
    // Method
    public void removeLog(Log log) {
        Expense e;
        for (int i = 0; i < logs.size()-1; i++) {

            if (logs.get(i).compareTo(log) == 0) {
                logs.remove(i);
                break;
            }
        }
    }
    }

 */
}