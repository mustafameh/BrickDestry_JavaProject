By Mustafa Mehmood Student Number : 20306551 
Brick_Destroy
main class is located in
Brick_Destroy-master/src/game/GraphicsMain.java
This is a simple arcace video game.
Player's goal is to destroy a wall with a small ball.
Scoring System On breaking type of Brick
Clay - 100
Cement - 200
Steel - 300
Crystal - 400
From the home menu the user has 4 Options 
User Can Start Game
User Can Check Info of Game
User Can Check Match History of last 20 matches
The Game has 7 Levels
- As a penelty system in the last level the user has the finish the game before the timer ends or Macth is over
- When The Macth
The game has  very simple commmand:
SPACE start/pause the game
A move left the player
D move rigth the player
ESC enter/exit pause menu
ALT+SHITF+F1 open console
The game automatically pause if the frame loses focus

Additions And Refactoring 
- Home Screen
Home Screen was redisgned with a proper logo for the game, background image increased frame size addition and rearrangemnt of buttons
- Info Screen
From the Home Screen User Has the option to Access the Info Screen Which displays information about that for that i added an Info Class which manages the creation for Frame of info scree
- Scoring System and Addition of Score Screen
User score is tracked in each match ,after every game session ( whether after all lives are lost or user manages to defet all levels), there appears a poppup asking for user name , this
name is stored along with their score in score.txt file in the resources folder for that i made a Scoring class. Scores from all the matches ever played are stored . 
In home menu user has the option to check the scores of the last 20 matches played which is handled by Score Menu. In Scoring Class a function is used that reads the score .txt file starting from the bottom to top and not the front which allowas me 
to display the pervious scores from 20 matches
- Timer and additional levels
I created a new tupe of brick called Crystal which takes 3 hits to destroy and gives additional score, it allowed me to add three more levels resulting in 7 levels in total
- For the last 7th level i added a Timer penaly system, the user has to finish the final level within 5 mins before the game stops counting score'
- Junit testing 
Unit tests were added for some classess and there functions to prove to myself, and perhaps to the users  that the works
- Java docs
Java docs were added for each and every class 

As part of refactoring Large classess like wall and brick were broken into smaller classes to make them easier to manage and to reduce decoupling
Many Variables and fucntion names were also changed for easier readablitly 
Classes were arranged in proper packages 
The project was converted to maven so adding the dependencies becomes easier 
 



