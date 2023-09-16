package jarvis;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JarvisTest {
    private Jarvis jarvis;

    @BeforeEach
    public void setUp() {
        // Create a Jarvis instance with a test file path
        jarvis = new Jarvis("testFilePath.txt");
    }

    @Test
    public void testGetGreeting() {
        String greeting = jarvis.getGreeting();
        assertNotNull(greeting);
        assertEquals("Hello from Jarvis.\n" + "What can I do for you, sir?", greeting);
    }

    @Test
    public void testGetResponse() {
        // Test a valid input
        String validInput = "todo read";
        String response = jarvis.getResponse(validInput);
        assertNotNull(response);
        assertTrue(response.startsWith("Got it, sir. I've added this task: "));

        // Test an invalid input that should throw an exception
        String invalidInput = "todo";
        String errorResponse = jarvis.getResponse(invalidInput);
        assertNotNull(errorResponse);
        assertTrue(errorResponse.startsWith("OOPS!"));

        // Test an exit command
        String exitInput = "bye";
        String exitResponse = jarvis.getResponse(exitInput);
        assertNotNull(exitResponse);
        assertEquals("Bye! Hope to see you again, sir.", exitResponse);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up the test file after each test
        File file = new File("testFilePath.txt");
        if (file.exists()) {
            file.delete();
        }
    }
}

