package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * tests.MaterialTest.java
 *
 * @author Cody Dukes
 *
 */
public class MaterialTest {
    private Material myDefaultMaterial;

    @BeforeEach
    void setUp() throws Exception {
        myDefaultMaterial = new Material("Super Glue", "Adhesives", 25);
    }

    @Test
    void testMaterialName() {
        final String expectedName = "Super Glue";

        assertEquals(expectedName, myDefaultMaterial.getName(),
                "This assert tests the default Material name");
    }

    @Test
    void testMaterialPrice() {
        final int expectedPrice = 25;

        assertEquals(expectedPrice, myDefaultMaterial.getPrice(),
                "This assert tests the default Material price");
    }

    @Test
    void testMaterialCategory() {
        final String expectedCategory = "Adhesives";

        assertEquals(expectedCategory, myDefaultMaterial.getCategory(),
                "This assert tests the default Material category");
    }
}
