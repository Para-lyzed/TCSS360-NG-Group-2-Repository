package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Tool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * tests.ToolTest.java
 *
 * @author Cody Dukes
 *
 */
public class ToolTest {
    private Tool myDefaultTool;

    /**
     * Create a tool before each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        myDefaultTool = new Tool("Toy-Hammer", 15);
    }

    /**
     * Tests getName
     */
    @Test
    void testToolName() {
        final String expectedName = "Toy-Hammer";

        assertEquals(expectedName, myDefaultTool.getName(),
                "This assert tests the default Tool name");
    }

    /**
     * Tests getPrice.
     */
    @Test
    void testToolPrice() {
        final int expectedPrice = 15;

        assertEquals(expectedPrice, myDefaultTool.getPrice(),
                "This assert tests the default Tool price");
    }
}
