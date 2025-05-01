import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {
    
    private GameLog gameLog;
    
    @BeforeEach
    public void setUp() {
        gameLog = new GameLog();
    }
    
    @Test
    public void testInitialState() {
        assertEquals(0, gameLog.getXWins());
        assertEquals(0, gameLog.getOWins());
        assertEquals(0, gameLog.getTies());
    }
    
    @Test
    public void testIncrementXWins() {
        gameLog.incrementWins('X');
        
        assertEquals(1, gameLog.getXWins());
        assertEquals(0, gameLog.getOWins());
        assertEquals(0, gameLog.getTies());
        
        // Increment again
        gameLog.incrementWins('X');
        assertEquals(2, gameLog.getXWins());
    }
    
    @Test
    public void testIncrementOWins() {
        gameLog.incrementWins('O');
        
        assertEquals(0, gameLog.getXWins());
        assertEquals(1, gameLog.getOWins());
        assertEquals(0, gameLog.getTies());
        
        // Increment again
        gameLog.incrementWins('O');
        assertEquals(2, gameLog.getOWins());
    }
    
    @Test
    public void testIncrementTies() {
        // 'D' represents a draw/tie
        gameLog.incrementWins('D');
        
        assertEquals(0, gameLog.getXWins());
        assertEquals(0, gameLog.getOWins());
        assertEquals(1, gameLog.getTies());
        
        // Increment again
        gameLog.incrementWins('D');
        assertEquals(2, gameLog.getTies());
    }
    
    @Test
    public void testMultipleWinTypes() {
        gameLog.incrementWins('X');
        gameLog.incrementWins('O');
        gameLog.incrementWins('D');
        gameLog.incrementWins('X');
        
        assertEquals(2, gameLog.getXWins());
        assertEquals(1, gameLog.getOWins());
        assertEquals(1, gameLog.getTies());
    }
}