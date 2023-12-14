package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import model.Tool;
import org.junit.jupiter.api.BeforeEach;

public class ToolTest {
    private Tool myDefaultTool;

    @BeforeEach
    void setUp() throws Exception {
        myDefaultTool = new Tool("Toy-Hammer", 15);
    }
}
