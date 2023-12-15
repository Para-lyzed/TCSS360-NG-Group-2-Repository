package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.Project.java creates projects which store expenses and logs for users to
 * manage. It is a backend class, containing the information for ProjectScreen
 * to generate a GUI.
 *
 * @author Maple Gunn
 * @author Nathan Grimsey
 *
 */
public class Project implements Serializable {
    private String name;
    private List<Expense> expenses;
    private List<Tool> tools;
    private List<Log> logs;
    private String description;
    private List<Image> images;
    private int budget;
    private int totalCost;

    /**
     * Constructor for Project objects.
     *
     * @param name is the name of the Project.
     * 
     * @author Maple Gunn
     */
    public Project(String name, int budget) {
        this.name = name;
        this.budget = budget;
        expenses = new ArrayList<>();
        tools = new ArrayList<>();
        logs = new ArrayList<>();
        images = new ArrayList<>();
        totalCost = 0;
    }

    /**
     * Getter method for name.
     *
     * @return Project name.
     * 
     * @author Maple Gunn
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the total cost of the Project whenever you add or remove an expense.
     *
     * @author Maple Gunn
     */
    public int updateTotalCost() {
        int total = 0;
        for (int i = 0; i < expenses.size(); i++) {
            total += (expenses.get(i).getPrice() * expenses.get(i).getQuantity());
        }
        return total;
    }

    /**
     * Get total cost of the Project.
     *
     * @return total cost of Project.
     *
     * @author Maple Gunn
     */
    public int getTotalCost() {
        return totalCost;
    }



    /**
     * Gets list of Expenses.
     * 
     * @return list of Expenses.
     * 
     * @author Maple Gunn
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Sets the list of Expenses.
     * 
     * @param expenseList the list of Expenses to set to.
     * 
     * @authon Nathan Grimsey
     */
    public void setExpenses(ArrayList<Expense> expenseList) {
        expenses = expenseList;
        updateTotalCost();
    }

    /**
     * Getter for Tools.
     * 
     * @return Tools.
     * 
     * @author Maple Gunn
     */
    public List<Tool> getTools() {
        return tools;
    }

    /**
     * Getter for Logs.
     * 
     * @return Logs.
     * 
     * @author Maple Gunn
     */
    public List<Log> getLogs() {
        return logs;
    }

    /**
     * Getter for description.
     * 
     * @return description.
     * 
     * @author Maple Gunn
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of a Project.
     * 
     * @author Nathan Grimsey
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Add a generic expense to the Project.
     * 
     * @author Maple Gunn
     */
    public void addExpense(String category, String name, int quantity, int price) {
        Expense newExpense = new Expense(name, category, price, quantity);
        expenses.add(newExpense);
        updateTotalCost();
    }

    /**
     * Add a Mterial as an Expense to the Project.
     * 
     * @author Maple Gunn
     */
    public void addExpense(Material material, int quantity) {
        Expense newExpense = (Expense) material;
        newExpense.setQuantity(quantity);
        expenses.add(newExpense);
        updateTotalCost();
    }

    /**
     * Add a Tool as an Expense to the Project.
     * 
     * @author Maple Gunn
     */
    public void addExpense(Tool tool, int quantity) {
        Expense newExpense = (Expense) tool;
        newExpense.setQuantity(quantity);
        expenses.add(newExpense);
        updateTotalCost();
    }

    /**
     * Add a required Tool to the Project.
     * 
     * @author Maple Gunn
     */
    public void addTool(Tool tool) {
        tools.add(tool);
    }

    /**
     * Add a Log to the Project.
     * 
     * @author Maple Gunn
     */
    public void addLog(Log log) {
        logs.add(log);
    }

    /**
     * Get images.
     * 
     * @return images.
     * 
     * @author Maple Gunn
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Add image to Project.
     * 
     * @author Maple Gunn
     */
    public void addImage(Image image) {
        images.add(image);
    }

    /**
     * Set the list of images to the one provided.
     * 
     * @author Maple Gunn
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * Get the budget of the Project.
     * 
     * @return the budget of the Project.
     * 
     * @author Nathan Grimsey
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Set the budget of the Project.
     * 
     * @author Nathan Grimsey
     */
    public void setBudget(int value) {
        budget = value;
    }

    /**
     * Update a generic Expense.
     * 
     * @author Maple Gunn
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
                updateTotalCost();
                break;
            }
        }
    }

    /**
     * Update an Expense by using a Material.
     * 
     * @author Maple Gunn
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
                updateTotalCost();
                break;
            }
        }
    }


    /**
     * Update an Expense by using a Tool.
     * 
     * @author Maple Gunn
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
                updateTotalCost();
                break;
            }
        }
    }

    /**
     * Remove an Expense.
     * 
     * @author Maple Gunn
     */
    public void removeExpense(Expense expense) {
        for (int i = 0; i < expenses.size()-1; i++) {

            if (expenses.get(i).compareTo(expense) == 0) {
                expenses.remove(i);
                updateTotalCost();
                break;
            }
        }
    }

    /**
     * Remove a required Tool from the Project.
     * 
     * @param tool the Tool to remove.
     * 
     * @author Maple Gunn
     */
    public void removeTool(Tool tool) {
        for (int i = 0; i < tools.size()-1; i++) {

            if (tools.get(i).compareTo(tool) == 0) {
                tools.remove(i);
                break;
            }
        }
    }

    /**
     * Remove a Log from the Project.
     * 
     * @param log the Log to remove.
     * 
     * @author Maple Gunn
     */
    public void removeLog(Log log) {
        for (int i = 0; i < logs.size() - 1; i++) {

            if (logs.get(i).compareTo(log) == 0) {
                logs.remove(i);
                break;
            }
        }
    }

}