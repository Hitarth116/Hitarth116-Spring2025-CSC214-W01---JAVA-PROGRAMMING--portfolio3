import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random = new Random();
    
    public ComputerPlayer(char symbol) {
        super(symbol, "Computer");
    }
    
    public int makeMove(Board board) {
        char[] currentBoard = board.getBoard();
        
        // 1. If it's the first move (board is empty), choose a corner
        if (isBoardEmpty(currentBoard)) {
            return chooseCorner();
        }
        
        // 2. If it's the second move and center is available, choose center
        if (isSecondMove(currentBoard) && !board.isCellOccupied(4)) {
            return 4;
        }
        
        // 3. Check if computer can win
        int winningMove = findWinningMove(currentBoard, getSymbol());
        if (winningMove != -1) {
            return winningMove;
        }
        
        // 4. Check if opponent can win and block
        char opponentSymbol = (getSymbol() == 'X') ? 'O' : 'X';
        int blockingMove = findWinningMove(currentBoard, opponentSymbol);
        if (blockingMove != -1) {
            return blockingMove;
        }
        
        // 5. Choose a random available spot
        return chooseRandomAvailableSpot(currentBoard);
    }
    
    private boolean isBoardEmpty(char[] board) {
        for (char cell : board) {
            if (cell == 'X' || cell == 'O') {
                return false;
            }
        }
        return true;
    }
    
    private boolean isSecondMove(char[] board) {
        int occupiedCount = 0;
        for (char cell : board) {
            if (cell == 'X' || cell == 'O') {
                occupiedCount++;
            }
        }
        return occupiedCount == 1;
    }
    
    private int chooseCorner() {
        // Corner indices are 0, 2, 6, 8
        int[] corners = {0, 2, 6, 8};
        return corners[random.nextInt(corners.length)];
    }
    
    private int findWinningMove(char[] board, char symbol) {
        int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };
        
        for (int[] combo : winningPositions) {
            // Count how many of the player's symbols are in this winning combination
            int symbolCount = 0;
            int emptyIndex = -1;
            
            for (int index : combo) {
                if (board[index] == symbol) {
                    symbolCount++;
                } else if (board[index] != 'X' && board[index] != 'O') {
                    // This is an empty cell
                    emptyIndex = index;
                }
            }
            
            // If there are two symbols and one empty spot, we can win or block
            if (symbolCount == 2 && emptyIndex != -1) {
                return emptyIndex;
            }
        }
        
        return -1; // No winning move found
    }
    
    private int chooseRandomAvailableSpot(char[] board) {
        List<Integer> availableSpots = new ArrayList<>();
        
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                availableSpots.add(i);
            }
        }
        
        if (availableSpots.isEmpty()) {
            return -1; // No spots available
        }
        
        return availableSpots.get(random.nextInt(availableSpots.size()));
    }
}