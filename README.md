# Nine Men Morris using JFrame
This project aims to develop an object-oriented design and fully functional implementation of a Nine Men's Morris game.

## Team Members
* [Ong Li Ching](https://github.com/li9406)
* Muhammad Abdullah Akif
* Subhan Saadat Khan
* How Yu Chern

## Game Description
Nine Men's Morris is a strategic based game for two players. The game board consists of 24 positions. Each player has 9 pieces initially. There are 3 phrases in the game:
1. Placing piece
   * The game begins with an empty board
   * A player can place a piece onto any empty position
   * If a player forms a "mill" by aligning 3 pieces in a row (vertically or horizontally), he/she can remove one of the opponent's pieces from the board
2. Moving piece
   * Once all 9 pieces onto the board have been placed onto the board, a player can slide one of his pieces to an empty adjacent position (vertically or horizontally)
   *  A player can try to form a "mill" and remove the opponent's pieces as in phrase 1
3. Jumping piece
   * When a player has 3 pieces left, he/she can jump one piece to any empty position on the board

A player wins when the opponent has less than 3 pieces or is unable to make a legal move.

## Project Structure
This project is divided into 4 sprints, with each sprint focusing on specific goals and deliveries. The breakdown of the sprints is as follow:
### Sprint 1
1. List out all the user stories
2. Design a domain model
3. Draw low-fi prototype/UI design

### Sprint 2
1. Implement basic game mechanics:
   * Game board
   * Move piece
2. Implement a graphic user interface (GUI) using JFrame

### Sprint 3
1. Implement fully functionable game with all core features

### Sprint 4
1. Implement additional game features
2. Perform thorough testing and bug fixing

## Features
This project includes the following features:
1. Interactive GUI for an engaging game experience
2. Single player mode with an computer opponent
3. Two player mode for playing against a friend
4. Game state tracking, including the current player turn and number of pieces to be placed onto the board
5. Hightlighting of all next legal moves by clicking the hint option
6. Restart option to reset the game and begin anew 
7. Tutorial mode that guides players throughout the rules and gameplay mechanics
   
## Getting Started
To set up and run the game, follow these steps:
1. Clone the project repository from GitHub: git clone https://github.com/li9406/nine-men-morris.git
2. Open the project in your preferred Java IDE
3. Run the game by executing the main class (Main.java)
