import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkInput();
    }
    
    public void checkInput() {
        if (Greenfoot.isKeyDown("up")) {
            setRotation(270);
            moveTiles();
        }
        else if (Greenfoot.isKeyDown("left")) {
            setRotation(180);
            moveTiles();
        }
        else if (Greenfoot.isKeyDown("down")) {
            setRotation(90);
            moveTiles();
        }
        else if (Greenfoot.isKeyDown("right")) {
            setRotation(0);
            moveTiles();
        }
    }

    public void moveTiles() {
        for (int i = 0; i<4; i++) if (!atBorder() && !atTile()) move(1);
    }

    public boolean atBorder() {
        //if adjacent to border do not move towards it
        return false; //for current testing purposes
    }

    public boolean atTile() {
        //if adjacent to tile do not move towards it
        return false; //for current testing purposes

        //unless the tile is of the same value; if so, merge the tiles
        //if (atTileOfSameValue()) mergeTiles();
    }

    /*public boolean atTileOfSameValue() {
    
    }*/

    public void mergeTiles() {

    }
}
