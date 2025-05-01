Tic Tac Toe Game
A Java implementation of the classic Tic Tac Toe game with both human and computer players.
Project Overview
This project implements a console-based Tic Tac Toe game in Java using Gradle as the build system. The game supports:

Player vs. Computer gameplay
Game state tracking
Logging of game results
JUnit tests for all components

Directory Structure
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
Prerequisites

Java JDK 8 or higher
Gradle (tested with Gradle 6.x, compatible up to 9.0)

Building the Project
To build the project, run:
bashgradle clean build
This will compile all source files and run the tests.
Running the Game
To start the game, run:
bashgradle run
Alternatively, after building, you can run the game directly:
bashjava -cp build/classes/java/main TicTacToe
Running Tests
To run the test suite:
bashgradle test
Test results will be available in build/reports/tests/test/index.html.
