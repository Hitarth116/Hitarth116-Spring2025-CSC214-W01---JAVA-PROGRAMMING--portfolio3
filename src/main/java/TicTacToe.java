import java.util.Scanner;

public class TicTacToe {
    private static GameLog gameLog = new GameLog();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        
        System.out.println("Welcome to Tic-Tac-Toe!");
        
        while (playAgain) {
            int gameMode = selectGameMode(scanner);
            
            Board board = new Board();
            Player playerX;
            Player playerO;
            
            switch (gameMode) {
                case 1: // Human vs Human
                    System.out.print("Enter the name of Player X: ");
                    String playerXName = scanner.nextLine().trim();
                    System.out.print("Enter the name of Player O: ");
                    String playerOName = scanner.nextLine().trim();
                    
                    playerX = new Player('X', playerXName);
                    playerO = new Player('O', playerOName);
                    break;
                    
                case 2: // Human vs Computer
                    System.out.print("Enter your name: ");
                    String humanName = scanner.nextLine().trim();
                    
                    playerX = new Player('X', humanName);
                    playerO = new ComputerPlayer('O');
                    System.out.println("Great! You will go first as X, and the computer will be O.");
                    break;
                    
                case 3: // Computer vs Human
                    System.out.print("Enter your name: ");
                    humanName = scanner.nextLine().trim();
                    
                    playerX = new ComputerPlayer('X');
                    playerO = new Player('O', humanName);
                    System.out.println("Great! The computer will go first as X, and you will be O.");
                    break;
                    
                default:
                    playerX = new Player('X', "Player X");
                    playerO = new Player('O', "Player O");
                    break;
            }
            
            playGame(board, playerX, playerO, scanner);
            
            // Print the current game log
            gameLog.printLog();
            
            // Prompt to play again
            playAgain = askToPlayAgain(scanner);
        }
        
        // Save game log to disk
        gameLog.saveLog();
        System.out.println("Goodbye!");
        scanner.close();
    }
    
    // Changed to public for testing purposes
public static int selectGameMode(Scanner scanner) {
        System.out.println("\nWhat kind of game would you like to play?");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");
        
        System.out.print("What is your selection? ");
        
        int selection = -1;
        while (selection < 1 || selection > 3) {
            try {
                selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection < 1 || selection > 3) {
                    System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                    System.out.print("What is your selection? ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                System.out.print("What is your selection? ");
            }
        }
        
        return selection;
    }
    
    private static void playGame(Board board, Player playerX, Player playerO, Scanner scanner) {
        Player currentPlayer = playerX;
        boolean gameOver = false;
        
        board.printBoard();
        
        while (!gameOver) {
            int move;
            
            if (currentPlayer instanceof ComputerPlayer) {
                // Computer's turn
                System.out.println("\n" + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") is thinking...");
                ComputerPlayer computerPlayer = (ComputerPlayer) currentPlayer;
                move = computerPlayer.makeMove(board);
            } else {
                // Human's turn
                System.out.print("\n" + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), choose a cell (1-9): ");
                move = getValidMove(scanner);
                // Convert move to board index (0-based)
                move = move - 1;
            }
            
            // Check if the move is valid
            if (board.isCellOccupied(move)) {
                if (!(currentPlayer instanceof ComputerPlayer)) {
                    System.out.println("\nThat cell is already taken! Try again.");
                    board.printBoard();
                    continue;
                }
            }
            
            // Make the move
            board.makeMove(move, currentPlayer.getSymbol());
            
            // Print updated board
            board.printBoard();
            
            // Check for winner
            int[] winningCombo = board.checkWinner(currentPlayer.getSymbol());
            if (winningCombo != null) {
                System.out.println("\n" + currentPlayer.getName() + " wins!");
                board.highlightWinningLine(winningCombo);  // Highlight the winning line
                gameLog.incrementWins(currentPlayer.getSymbol());
                gameOver = true;
            } else if (board.isFull()) {
                System.out.println("\nIt's a draw!");
                gameLog.incrementWins('D');  // 'D' for Draw
                gameOver = true;
            } else {
                // Switch player
                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }
        }
    }
    
    private static int getValidMove(Scanner scanner) {
        int move = -1;
        while (true) {
            try {
                move = Integer.parseInt(scanner.nextLine().trim());
                if (move < 1 || move > 9) {
                    System.out.println("\nThat is not a valid move! Try again.");
                    System.out.print("Choose a cell (1-9): ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\nThat is not a valid move! Try again.");
                System.out.print("Choose a cell (1-9): ");
            }
        }
        return move;
    }
    
    public static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("\nWould you like to play again (yes/no)? ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                return true;
            } else if (response.equals("no")) {
                return false;
            } else {
                System.out.println("That is not a valid entry!");
            }
        }
    }
}