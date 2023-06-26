# FIT3077_project

Technical Documentation:

Java Pre-requisites:
1. Java 18
2. Java(TM) SE Runtime Environment (build 18+36-2087)
3. Java HotSpot(TM) 64-Bit Server VM (build 18+36-2087, mixed mode, sharing)
4. IntelliJ IDEA Community Edition 2020.2 x64

Instructions on how to generate the java executable file (.jar) on Intellij:
1. On the top bar, go to "File", then "Project Structure".
2. Click on "Artefacts" and select "+" to add a new artefact, then select "From module with dependencies".
3. Under the "Main Class", click on the file icon to the right.
4. A popup will appear, for you to select the main class from the project. Click "Ok" once done.
5. Ensure the option "Extract to the target JAR" is enabled, before selecting "Ok".
6. The filepath for the executable will be present in the menu (typically it does to \out\artefacts\project_jar). Click "Ok" once all is alright.
7. To build the .jar file, Go To "Build", then "Build Artefacts".
8. A popup with the sample jar file wil appear. Next to it, select the "build" Action ("Rebuild" for rebuilding after changes).
9. The .jar file will be built under \out\artefacts\project_jar.

Ref: https://youtu.be/_XQjs1xGtaU

Documentation of Errors:
1. Please do not disturb any of the image files names or filepath, as it would cause the program to throw an excapetion if it cannot find the correct images for the game (returns null -> IllegalArgumentException).



Game Information:

Instructions to run the 9MM program (java executable):
1. Download the zip file containing the 9MM Program
2. Export the contents (the directory: project) into the following filepath: "C:\Users\user"
3. Open a new terminal
4. Run the command: java -jar "C:\Users\user\project\out\artifacts\project_jar\project.jar"
5. Or alternatively, navigate to \out\artifacts\ and click on the project.jar file to run it.

Game Modes:
1. PvP - Player vs Player
2. PvC - Player vs Computer
3. Tutorial Game - Tutorial Game Mode, with Game guide and automatic hints to teach the player how to play the game.

Instructions on how to play the game:
1. Place the pieces on the positions on the game board. The turn of the player is displayed on the "Turn" indicator at the bottom of the game board (Either white or black).
2. If a Mill is formed (3 pieces in a line), you can click on an opponent's piece to remove it, as long as it is not in an existing mill.
3. Once all pieces have been placed onto the board, click on a piece, then click on another position to move the piece. You will only be able to move a white piece during white's turn, and a black piece during black's turn.
4. When there are only 3 pieces left, you can jump (fly) a piece to any empty position on the board.
5. The game ends when a player has only 2 pieces remaining, or no legal moves can be made.