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
        GreenfootImage image = new GreenfootImage("Counter.png");
        image.scale(225, 75);
        setImage(image);
    }

    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Instructions.gameStarted) updateScoreOnCounter(); //if the game has started, this updates the number on the counter with the current score, so when the score changes, the number on the counter changes to reflect the new score
    }

    
    //this method is called in the Tile class to increase the score  
    public static void increaseScore(int scoreAddition) {
        score+=scoreAddition;
    }

    //this updates the number on the counter with the current score, so when the score changes, the number on the counter changes to reflect the new score
    public void updateScoreOnCounter() {
        Color color = new Color(0,0,0,0);
        GreenfootImage image = new GreenfootImage("Counter.png");
        image.scale(225, 75);
        GreenfootImage scoreNumberImage = new GreenfootImage("" + score, 30, Color.BLACK, color);
        image.drawImage(scoreNumberImage, ( image.getWidth() - scoreNumberImage.getWidth()) / 2, ((image.getHeight() - scoreNumberImage.getHeight()) / 2) + 10);
        setImage(image);
    }
}
