package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * tests.ExpenseTest.java
 *
 * @author Maple Gunn
 *
 */
public class ExpenseTest {
    private Expense myDefaultExpense;

    @BeforeEach
    void setUp() throws Exception {
        myDefaultExpense = new Expense("Test-Expense", "none", 3, 5);
    }

    @Test
    void testExpenseName() {
        final String expectedName = "Test-Expense";

        assertEquals(expectedName, myDefaultExpense.getName(),
                "This assert tests the default Expense name");
    }

    @Test
    void testExpensePrice() {
        final int expectedPrice = 3;

        assertEquals(expectedPrice, myDefaultExpense.getPrice(),
                "This assert tests the default Expense price");
    }

}
