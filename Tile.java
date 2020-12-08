import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    
    private int value = 2;
    
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Tile() {
        GreenfootImage image = new GreenfootImage("Tile.png");
        image.scale(100, 100);
        setImage(image);
    }
    
    public void act() 
    {
        checkInput();
    }
    
    public void checkInput() {
        if (Greenfoot.isKeyDown("up")) {
            setRotation(270);
            moveTiles();
            Counter.increaseScore(2);
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
        setRotation(0);
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
        Counter.increaseScore(value*2);
    }
}
