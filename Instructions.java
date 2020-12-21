import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends Actor
{

    public static boolean gameStarted; //this instance variable tracks whether the user has pressed a key when the instructions are shown and started the game

    
    //constructor:
    public Instructions() {
        gameStarted = false; //gameStarted is initially set to false because the game has not started yet
    }

    
    /**
     * Act - do whatever the Instructions wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (!gameStarted) { //if the game has not been started yet:
            if (Greenfoot.getKey() != null) { //when the user presses any key on the instructions screen,
                gameStarted = true; //the Tile and Counter objects can respond to input,
                getWorld().removeObject(this); //the instructions are removed,
                Tile.moveMade = true; //and moveMade is initially set to true so a tile is added when the game is started
            }
        }
    }    
}
