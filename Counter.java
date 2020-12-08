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

    private static int score;

    public Counter() {
        score = 0;
    }

    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateScoreOnCounter();
    }

    public static void increaseScore(int scoreAddition) {
        score+=scoreAddition;
    }

    public void updateScoreOnCounter() {
        Color color = new Color(0,0,0,0);
        GreenfootImage image = new GreenfootImage("Counter.png");
        image.scale(96, 16);
        GreenfootImage scoreNumberImage = new GreenfootImage(" " + score, 20, Color.BLACK, color);
        image.drawImage(scoreNumberImage, ( image.getWidth() - scoreNumberImage.getWidth()) / 2, (image.getHeight() - scoreNumberImage.getHeight()) / 2);
        setImage(image);
    }
    
    //todo later: make Tile object include a Counter object as one of its variable so static variables and methods can be removed from Counter
}
