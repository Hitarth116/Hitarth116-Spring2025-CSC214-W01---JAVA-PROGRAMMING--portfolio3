import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TicTacToeTest {
    
    @Test
    public void testAskToPlayAgain() {
        // Test that "yes" returns true
        boolean result = simulateUserInput("yes\n", TicTacToe::askToPlayAgain);
        assertTrue(result);
        
        // Test that "no" returns false
        result = simulateUserInput("no\n", TicTacToe::askToPlayAgain);
        assertFalse(result);
        
        // Test invalid input followed by valid input
        result = simulateUserInput("invalid\nyes\n", TicTacToe::askToPlayAgain);
        assertTrue(result);
    }
    
    @Test
    public void testSelectGameMode() {
        // This is a static method call with a more complex interaction
        // We're testing that the function correctly returns the game mode selected
        
        // Test mode 1 selection
        int mode = simulateGameModeSelection("1\n");
        assertEquals(1, mode);
        
        // Test mode 2 selection
        mode = simulateGameModeSelection("2\n");
        assertEquals(2, mode);
        
        // Test mode 3 selection
        mode = simulateGameModeSelection("3\n");
        assertEquals(3, mode);
        
        // Test invalid input followed by valid input
        mode = simulateGameModeSelection("invalid\n4\n2\n");
        assertEquals(2, mode);
    }
    
    // Helper method to simulate user input for testing the askToPlayAgain method
    private boolean simulateUserInput(String input, java.util.function.Function<java.util.Scanner, Boolean> method) {
        // Save the original System.in
        java.io.InputStream originalIn = System.in;
        
        try {
            // Set up the new System.in with our simulated input
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            
            // Call the method with a new Scanner
            return method.apply(new java.util.Scanner(System.in));
        } finally {
            // Restore the original System.in
            System.setIn(originalIn);
        }
    }
    
    // Helper method to simulate game mode selection
    private int simulateGameModeSelection(String input) {
        // Save the original System.in and System.out
        java.io.InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        
        try {
            // Redirect System.in with our simulated input
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            
            // Redirect System.out to ignore the output
            System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
            // Call the method with a new Scanner
            return TicTacToe.selectGameMode(new java.util.Scanner(System.in));
        } finally {
            // Restore the original System.in and System.out
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}