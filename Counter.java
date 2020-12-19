import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/** 2048 */
/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{

    private static int score; //this instance variable tracks the current score and is used to set the score in updateScoreOnCounter()
    private boolean keyReleased = true; //this instance variable tracks whether the key was released in checkInput(), to prevent multiple actions from one key press


    //the constructor sets the score to 0 when the game is started
    public Counter() {
        score = 0;
    }

    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateScoreOnCounter(); //this updates the number on the counter with the current score, so when the score changes, the number on the counter changes to reflect the new score
        checkInput(); //this method checks if an arrow key has been pressed, to determine if the player made a move. If so, it runs addTile() to add another tile to the board.
    }

    
    //this method is called in the Tile class to increase the score  
    public static void increaseScore(int scoreAddition) {
        score+=scoreAddition;
    }

    //this updates the number on the counter with the current score, so when the score changes, the number on the counter changes to reflect the new score
    public void updateScoreOnCounter() {
        Color color = new Color(0,0,0,0);
        GreenfootImage image = new GreenfootImage("Counter.png");
        image.scale(96, 16);
        GreenfootImage scoreNumberImage = new GreenfootImage(" " + score, 20, Color.BLACK, color);
        image.drawImage(scoreNumberImage, ( image.getWidth() - scoreNumberImage.getWidth()) / 2, (image.getHeight() - scoreNumberImage.getHeight()) / 2);
        setImage(image);
    }


    //this method checks if an arrow key has been pressed, to determine if the player made a move. If so, it runs addTile() to add another tile to the board.
    public void checkInput() {
        Tile.key = Greenfoot.getKey(); //set instance variable key to the last pressed key      new
        //this method uses the key instance variable in the Tile class, because Greenfoot.getKey() returns the key pressed (or null if no keys have been pressed) since the last time Greenfoot.getKey() was called
        if (Tile.key == null) {
            keyReleased = true;
        }
        else if ((("up").equals(Tile.key) || ("left").equals(Tile.key) || ("down").equals(Tile.key) || ("right").equals(Tile.key)) && keyReleased) { //if any of the arrow keys are pressed, another tile is added to the grid
            addTile();
            keyReleased = false;
        }

    }

    //this method adds a new tile to the board in an empty space
    public void addTile() {
        int X;
        int Y;
        int tests = 0;
        do {
            X = Greenfoot.getRandomNumber(4); //the method finds a random space on the board - x axis
            Y = Greenfoot.getRandomNumber(4); //the method finds a random space on the board - y axis
            tests++;
            if (tests > 98) Greenfoot.stop(); //if there are no more spots left on the board, the game stops to avoid an infinte loop
        } while (getWorld().getObjectsAt(X, Y, Tile.class).size() != 0); //the method checks to see if there is already a tile in the space which was randomly selected. If there is, the method chooses random numbers again
        getWorld().addObject(new Tile(), X, Y); //once an empty space is found, a new tile is added
    }
    
}
