import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends Actor
{

    public static boolean gameStarted; //this instance variable tracks whether the user has pressed a key when the instructions are shown to start the game

    
    //constructor:
    public Instructions() {
        gameStarted = false;
    }

    
    /**
     * Act - do whatever the Instructions wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //when the user presses any key on the instructions screen, the instructions are removed and the Tile and Counter objects can respond to input
    public void act() {
        if (!gameStarted) {
            if (Greenfoot.getKey() != null) {
                gameStarted = true;
                getWorld().removeObject(this);
            }
        }
    }    
}
