# Fishing Frenzy (JavaFX Game)

## 1. Program Overview:
### Description:
Fishing Frenzy is a game built in Node.js using JavaFX. In this game, your main objective is to catch as many fish as possible, become rich, and prove your worth in the world of fishing. You're able to progress through the game using upgrades to your depth and hook capacity, which allow you to catch rarer and more fish. When actively fishing, you can move your hook by using (A, D, &larr;, &rarr;, or clicking and dragging with your mouse.)
### Intended Audience:
Fishing Frenzy is a game made for those of us that enjoy fishing or have an enjoyment in playing a variety of video games. Fishing Frenzy is made to be played by everyone and has very simple rules that are employed to make the game enjoyable for all ages.

### Highlights:
Fishing Frenzy is a game that can be run on all computers from anywhere in the world. The game allows a wide variety of people to have something to enjoy in their free time and truly take a break from the world around them.

## 2. Feature List:
- Implement mouse event handlers :heavy_check_mark:
- Implement button event handlers :heavy_check_mark:
- Create upgrade buttons :heavy_check_mark:
- Create a start button that prevents actions beyond starting the game :heavy_check_mark:
- Implement fish characters: :heavy_check_mark:
  - Move from wall to wall :heavy_check_mark:
  - Various speeds :heavy_check_mark:
  - Variety of fish :heavy_check_mark:
  - Different value per variety :heavy_check_mark:
  - Chance for golden fish (2x value) :heavy_check_mark:
- Implement a character that the player will act as :heavy_check_mark:
- When a fish comes in contact with a hook, it should become attached to the hook (Added to counter and turned invisble) :heavy_check_mark: 
- Implement a limit that stops the fish from attaching to the hook if the limit is met :heavy_check_mark:
- Implement a maximum depth for the hook, and make it so that the farther down the line goes, the more valuable the fish gets :heavy_check_mark:
- Add simple designs to the fish, boat, fisherman, and backdrop to create a vivid game environment. :heavy_check_mark:

## 3. Known Bugs/Limitations:
- Collisions are detected as a box around both the fish and the hook so they're not exactly perfect when detecting a collision
- The maximum depth is a result of the size of the background image and can only be changed if the image is extended  

## 4. Step-by-Step User Guide:
### Launching the Game:
- Download the Full JDK (JDK 21 LTS) for your operating system on [BellSoft](https://bell-sw.com/pages/downloads/#jdk-21-lts)
- Once downloaded, open up the installer and follow the prompts
- Download the game via a .zip file
- Unzip the contents of the file
- Navigate in your terminal or command prompt to the Fishing Frenzy folder
  - Using `cd` to navigate up a directory
  - Using `cd ..` to navigate down a directory
  - Using `ls` to view the contents of a directory
- Once inside the `\Fishing-Frenzy` directory:
  - Execute the following lines of code 
  ```
  cd .\demo\src\main\java\
  javac *.java
  java Game
  ```
  - Now, enjoy the game!
### Interacting with the Game:
- Click the **Start Game!** button to begin the game
- Once clicked, the fishing hook will drop down to your current maximum depth
- To move your hook:
  - (A, D)
  - (&larr;, &rarr;);
  - Click the hook and drag
    
- Your goal is to collect your limit of fish before you reach the top
- Each fish is worth an assigned value (Golden fish = 2x value):
  - Tuna ($20)
  - Butterfly Fish ($30)
  - Salmon ($40)
  - Yellow Tang ($45)
  - Anglerfish ($60)
- With money, you can upgrade your depth or hook capacity to catch rarer fish or more fish

### Major Features:
- **Start Game Button**: <br>
  Starts a TranslateTransition that moves the background image up by the current depth and then moves up at a speed proportional to the depth.
- **Depth Upgrade Button** <br>
  Increases the current depth by 50 <br>
  (If the player both has enough money and hasn't reached the maximum depth of 5600).
- **Hook Capacity Button** <br>
  Increases the hook capacity by 2 <br>
  (If the player both has enough money and hasn't reached the maximum hook capacity of 50).
- **Hook Movement** <br>
  Awaits the user's input of (A, D, &larr;, &rarr;, or mouse click + drag), while the hook is moving upward to move the hook 7 units in its respective direction.
- **Collisions** <br>
  Uses calculated bounds around each respective fish to generate a "box" that is used to detect when the hook has crossed into it.

## 5. Assets:
- [Anglerfish](https://opengameart.org/content/anglerfish)
- [Butterfly Fish](https://www.pinterest.com/pin/pixel-art-fish-design--487585097161705344/)
- [Salmon](https://www.vecteezy.com/vector-art/27711801-a-single-fish-in-pixel-art-style)
- [Tuna](https://www.freepik.com/premium-vector/tuna-fish-icon-pixel-art-illustration-8-bit_33501191.htm)
- [Yellow Tang](https://www.freepik.com/premium-vector/pixel-art-puffer-fish-design_250779490.htm)

## 6. Authors:
- Jackson Bizzell @J-Bizzy
- Wesley Caddell @caddell26w/@caddellw