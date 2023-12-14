package model;

import java.util.List;
import java.util.Map;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.BudgetCalc.java
 *
 * @author Cody Dukes
 *
 */
public class BudgetCalc {
    private static Map<String, Integer> expenseMap;
    private static Map<String, Integer> categoryBreakdown;
    private static List<Expense> expenseList;

    /**
     * getAllCategoryCost builds and returns a Map containing pairs of categories and their total cost..
     *
     * @param project is the project to calculate expenses from.
     *
     * @author Cody Dukes
     */
    static Map<String, Integer> getAllCategoryCosts(Project project) {
        expenseList = project.getExpenses();
        String expenseCategory;
        int expensePrice;
        int expenseQuantity;

        for (int i = 0; i < expenseList.size(); i++) {
            expenseCategory = expenseList.get(i).getCategory();
            expensePrice = expenseList.get(i).getPrice();
            expenseQuantity = expenseList.get(i).getQuantity();

            if (expenseMap.containsKey(expenseCategory)) {
                expenseMap.put(expenseCategory, expenseMap.get(expenseCategory) + (expensePrice * expenseQuantity));
            } else {
                expenseMap.put(expenseCategory, (expensePrice * expenseQuantity));
            }
        }

        return expenseMap;
    }

    /**
     * getSingleCategoryBreakdown builds and returns a Map breaking down expenses in a category by name and total cost.
     *
     * @param project is the project to calculate expenses from.
     * @param category is the category to break down.
     *
     * @author Cody Dukes
     */
    static Map<String, Integer> getSingleCategoryBreakdown(Project project, String category) {
        expenseList = project.getExpenses();
        int expenseTotal;

        // Create a list containing just the relevant expenses.
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getCategory() == category) {
                expenseTotal = expenseList.get(i).getPrice() * expenseList.get(i).getQuantity();
                categoryBreakdown.put(expenseList.get(i).getName(), expenseTotal);
            }
        }

        return categoryBreakdown;
    }
}
