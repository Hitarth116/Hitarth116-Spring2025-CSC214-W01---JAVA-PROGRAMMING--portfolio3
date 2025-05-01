# Tic Tac Toe Game

A Java implementation of the classic Tic Tac Toe game with both human and computer players.

## Project Overview

This project implements a console-based Tic Tac Toe game in Java using Gradle as the build system. The game supports:
- Player vs. Computer gameplay
- Game state tracking
- Logging of game results
- JUnit tests for all components

## Directory Structure

```
port3/
├── build.gradle
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Board.java         # Game board implementation
│   │       ├── ComputerPlayer.java # AI opponent implementation
│   │       ├── GameLog.java       # Game history logging functionality
│   │       ├── Player.java        # Player class (base for human/computer)
│   │       └── TicTacToe.java     # Main game logic and entry point
│   └── test/
│       └── java/
│           ├── BoardTest.java
│           ├── ComputerPlayerTest.java
│           ├── GameLogTest.java
│           ├── PlayerTest.java
│           └── TicTacToeTest.java
```

## Prerequisites

- Java JDK 8 or higher
- Gradle (tested with Gradle 6.x, compatible up to 9.0)

## Building the Project

To build the project, run:

```bash
gradle clean build
```

This will compile all source files and run the tests.

## Running the Game

To start the game, run:

```bash
gradle run
```

Alternatively, after building, you can run the game directly:

```bash
java -cp build/classes/java/main TicTacToe
```

## Running Tests

To run the test suite:

```bash
gradle test
```

Test results will be available in `build/reports/tests/test/index.html`.

## Game Rules

1. The game is played on a 3x3 grid.
2. Players take turns placing their mark (X or O) in an empty cell.
3. The first player to get three of their marks in a row (horizontally, vertically, or diagonally) wins.
4. If all cells are filled and no player has won, the game is a draw.

## Implementation Details

- **Board**: Manages the game board state and checks for win conditions
- **Player**: Abstract base class for different player types
- **ComputerPlayer**: AI implementation for the computer opponent
- **GameLog**: Records game outcomes for historical tracking
- **TicTacToe**: Main game controller that coordinates gameplay

