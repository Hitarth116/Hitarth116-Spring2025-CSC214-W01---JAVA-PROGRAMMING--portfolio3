import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    
    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    
    @Test
    public void testNewBoardIsEmpty() {
        char[] boardState = board.getBoard();
        
        // A new board should have cells 1-9 (not X or O)
        for (int i = 0; i < boardState.length; i++) {
            assertEquals((char)('1' + i), boardState[i]);
        }
    }
    
    @Test
    public void testIsCellOccupied() {
        // Initially no cells are occupied
        for (int i = 0; i < 9; i++) {
            assertFalse(board.isCellOccupied(i));
        }
        
        // Occupy a cell
        board.makeMove(4, 'X');
        assertTrue(board.isCellOccupied(4));
        assertFalse(board.isCellOccupied(0));
    }
    
    @Test
    public void testMakeMove() {
        board.makeMove(0, 'X');
        board.makeMove(4, 'O');
        
        char[] boardState = board.getBoard();
        assertEquals('X', boardState[0]);
        assertEquals('O', boardState[4]);
        assertEquals('3', boardState[2]); // Unchanged
    }
    
    @Test
    public void testCheckWinnerHorizontal() {
        // Create horizontal win for X in the top row
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        
        int[] winningCombo = board.checkWinner('X');
        assertNotNull(winningCombo);
        assertArrayEquals(new int[]{0, 1, 2}, winningCombo);
        
        // No win for O
        assertNull(board.checkWinner('O'));
    }
    
    @Test
    public void testCheckWinnerVertical() {
        // Create vertical win for O in the middle column
        board.makeMove(1, 'O');
        board.makeMove(4, 'O');
        board.makeMove(7, 'O');
        
        int[] winningCombo = board.checkWinner('O');
        assertNotNull(winningCombo);
        assertArrayEquals(new int[]{1, 4, 7}, winningCombo);
        
        // No win for X
        assertNull(board.checkWinner('X'));
    }
    
    @Test
    public void testCheckWinnerDiagonal() {
        // Create diagonal win for X
        board.makeMove(0, 'X');
        board.makeMove(4, 'X');
        board.makeMove(8, 'X');
        
        int[] winningCombo = board.checkWinner('X');
        assertNotNull(winningCombo);
        assertArrayEquals(new int[]{0, 4, 8}, winningCombo);
    }
    
    @Test
    public void testIsFull() {
        assertFalse(board.isFull());
        
        // Fill the board
        for (int i = 0; i < 9; i++) {
            board.makeMove(i, i % 2 == 0 ? 'X' : 'O');
        }
        
        assertTrue(board.isFull());
    }
    
    @Test
    public void testReset() {
        // Make some moves
        board.makeMove(0, 'X');
        board.makeMove(4, 'O');
        
        // Reset
        board.reset();
        
        // Check that the board is back to initial state
        char[] boardState = board.getBoard();
        for (int i = 0; i < boardState.length; i++) {
            assertEquals((char)('1' + i), boardState[i]);
        }
    }
}