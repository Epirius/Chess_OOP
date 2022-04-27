# Chess_OOP

<img src="/src/resources/knightB.png" align="left" width="128px" height="128px"/>
Chess by Felix Kaasa

- see "Class Diagram.png" for an overview of the program.
- see this video for a short example of the program running: https://streamable.com/zm6jyp
- this project is made up of 4650 total lines, of which 3110 are lines of code.
#

 ## Installing and running the program
make sure your computer has java 17 runtime (or later) installed ([guide](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)).

there are two options when it comes to installing the program. you can download a compiled jar file, or you can download the source code and compile it yourself.

 - Option 1:
   - download the jar file from [this link](https://git.app.uib.no/Felix.Kaasa/chess_oop/-/blob/998fc004c196130c7ba2a75e02455c25fdad6ed8/oblig2.jar)
   - open a terminal and cd to the location of the file. (on Windows you can click on the file path in the explorer then type cmd and press enter)
   - on linux computers: right-click on the file, click properties, click on the permissions tab, and make sure "is executable" is selected.
   - then enter the command:
   ```
   java -jar oblig2.jar
   ```

 - Option 2:
   - open a terminal and cd into a folder where you want to download the project, and run the commands:
   ```
   git clone https://git.app.uib.no/Felix.Kaasa/chess_oop.git
   cd chess_oop
   ```

 - then in an ide (for example intellij) run the Main method in /src/main/java/Main/Main.java

 ## About this project
 this program is an implementation of chess with:
- possibility of playing local multiplayer or against ai.
- choose which side you want to play as against the ai.
- an AI that uses minimax with alpha beta pruning.
- possibility of resizing the screen.
- possibility of creating a new game after the old one is over.
- possibility of undoing moves.
- choose how many minutes the clock will start with, and number of seconds that will be added to the clock after a move.
- display all possible moves for a selected piece.
- text and image buttons that can execute arbitrary lambda functions when clicked.

## License 
- [<img src="https://img.shields.io/badge/license-MIT-green"/>](https://choosealicense.com/licenses/mit/)
 This project is published under an MIT license 

## Attributes
- [![CC-BY-4.0](https://licensebuttons.net/l/by-nc/4.0/88x31.png)](https://creativecommons.org/licenses/by/4.0/) I use GraphicHelperMethods.java which is code i imported from semsteroppgave 1.
- [![CC0](https://i.creativecommons.org/p/zero/1.0/88x31.png)](https://creativecommons.org/publicdomain/zero/1.0/) [The chess pieces](https://opengameart.org/content/chess-pieces-and-a-board) are public domain CC0  that i modified with a white outline.
