# Pong
PONG 2.0
Created by Liad, Rithvik, and Eyal 5/27/19
Our program is a recreated and re-imagined version of the classic game; Atari Pong. It will provide the average person who is bored with a leisurely (or staggeringly difficult) gaming experience. Anybody looking for a quick and casual game can turn to Pong 2.0. The primary features of our program include, various window states (menu, help, select, game, and shop), multiple difficulties (normal and hard), the keyboard controls, and graphics.

Instructions:
Our program is simple to play. A help page in the menu will give the player instructions on how to play. The menu gives the option to select the difficulty level, either normal or hard. If you select hard mode, red enemy squares will kill you when they touch you. Additional enemies will spawn in your game for every 5 levels. Once you are in a game, press W to move your paddle up and press S to move your paddle down. If you are low on health or would like to become faster, press spacebar to enter the shop. As the game progresses, your score will increase rapidly and you can use these points to buy upgrades or to refill your health bar. Press space to exit the shop. If the ball touches your wall, you lose health. You can pause the game by pressing P and press it again to resume the game. The game terminates upon losing all the health and takes you to the main menu. If you wish to exit the whole game, press the escape button.

Class List:
1. Game: represents a game, in this case Pong 2.0
2. GameObject: responsible for creating the game objects like the player, ball, etc.
3. Handler: represents a handler for private variable actions
4. Window: represents a JFrame window display
5. MouseManager: responsible for handling mouse events
6. Player: represents the paddle
7. Ball: represents the ball
8. Enemy: represents the red enemies in hard mode
9. Menu: represents the main menu screen
10. MenuParticle: represents the animated particles in the main screen
11. Spawn: spawns enemies
12. Shop: represents the game shop to upgrade/refill health and upgrade speed
13. Trail: represents the menu particle’s trail
14. ID: holds the data for various objects
15. HUD: represents the health bar, pause, and shop options
16. KeyInput: handles the key pressing logic if multiple keys are pressed at the same time
    
Responsibility List:
● Eyal:
○ Created the Ball class which creates the game ball
○ Created the Enemy class which creates the enemies in hard mode
○ Modified the Spawn class to spawn new enemies at intervals
○ Added comments
○ Bug Fixes

● Liad:
■ Resetting the size of the health bar when a new game starts
■ Making the velocity of the ball change rather than bounce back and forth
■ Fixed problem where enemies would bounce off of the paddle
■ Found how to remove the menu particles when entering a different game
state (changing from menu to help, for example)
○ Created Game class
○ Created GameObject class
○ Created Handler class
○ Created HUD class
○ Created ID enum
○ Created Shop class
○ Created KeyInput class
○ Created Menu class
○ Created MouseManager class
○ Created Spawn class
○ Created Trail class
○ Created Window class
○ Various comments and bug fixes 

● Rithvik:
○ Implemented music in Menu (background music) and Ball (pong blip)
○ Created and implemented the Player class which creates a paddle
○ Added logic in Shop to have a background image and upgrade/refill buttons
○ Worked on the help page contents in the menu page
○ Created key response logic in KeyInput for moving the paddle up and down by
pressing W and S
○ Bug Fixes
■ Fixed the bug which didn’t reset the Shop upgrade costs when you restart the game
Credit List:
● Main
■ Fixed the bug which didn’t loop the background music menu page:
○ Music reference: ​https://www.youtube.com/watch?v=FOv9XC3UXIc
○ We recreated the idea of the menu particles from the above video. In the menu
page, the particle animation was created by using the AlphaComposite class
from Java API.
Known Bug List:
● Difficult to demonstrate this bug: When the ball gets wedge between the paddle and the wall or close to top/bottom of the paddle, the ball gets stuck there until all of your health is depleted. Pressing W or S releases the ball.
● The background music plays only for the first time menu loading and does not play on subsequent games in the same session.
