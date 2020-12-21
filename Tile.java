import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends Actor
{


    private int value = 2; //this instance variable tracks the current value of the tile
    private boolean keyReleased = true; //this instance variable tracks whether the key was released in checkInput(), to prevent multiple actions from one key press
    public static String key; //this instance variable is used to track the last pressed key in checkInput() in both Tile.java and Counter.java
    public static boolean moveMade = true;


    //constructor:
    public Tile() {
        GreenfootImage image = new GreenfootImage("Tile.png"); //gets the Tile image
        image.scale(100, 100); //scales the Tile image
        setImage(image); //sets the Tile image
    }


    /**
    * Act - do whatever the Tile wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
    public void act()
    {
        updateTileValue(); //this updates the number on the tile with the current value, so when the value of a tile changes, the number on it changes to reflect its new value
        if (Instructions.gameStarted) checkInput(); //if the game has started, this method checks the last pressed key, then runs moveTiles() if an arrow key was pressed to move the tile accordingly
    }


    //this updates the number on the tile with the current value, so when the value of a tile changes, the number on it changes to reflect its new value
    public void updateTileValue() {
        Color color = new Color(0,0,0,0);
        GreenfootImage image = new GreenfootImage("Tile.png");
        image.scale(100, 100);
        GreenfootImage tileValueImage = new GreenfootImage("" + value, 40, Color.BLACK, color);
        image.drawImage(tileValueImage, (image.getWidth() - tileValueImage.getWidth()) / 2, (image.getHeight() - tileValueImage.getHeight()) / 2);
        setImage(image);
    }


    //this method checks the last pressed key, then runs moveTiles() if an arrow key was pressed to move the tile accordingly
    //note: this method is slightly repetitive because making it less repetitive would also decrease readability
    public void checkInput() {
        if (key == null) { //if no key was pressed (meaning all keys have been released), set keyReleased to true
            keyReleased = true;
        }
        else if (("up").equals(key) && keyReleased) { //if the up arrow is pressed,
            setRotation(270); //the tile is moved to face upwards,
            moveTiles(); //the tile is moved,
            keyReleased = false; //keyReleased is set to false, so the key has to be released before the command can be run again,
            moveMade = true; //and moveMade is set to true so another Tile can be added
        }
        else if (("left").equals(key) && keyReleased) { //if the left arrow is pressed,
            setRotation(180); //the tile is moved to face leftwards,
            moveTiles(); //the tile is moved,
            keyReleased = false; //keyReleased is set to false, so the key has to be released before the command can be run again,
            moveMade = true; //and moveMade is set to true so another Tile can be added
        }
        else if (("down").equals(key) && keyReleased) { //if the down arrow is pressed,
            setRotation(90); //the tile is moved to face downwards,
            moveTiles(); //the tile is moved,
            keyReleased = false; //keyReleased is set to false, so the key has to be released before the command can be run again,
            moveMade = true; //and moveMade is set to true so another Tile can be added
        }
        else if (("right").equals(key) && keyReleased) { //if the right arrow is pressed,
            setRotation(0); //the tile is moved to face rightwards,
            moveTiles(); //the tile is moved,
            keyReleased = false; //keyReleased is set to false, so the key has to be released before the command can be run again,
            moveMade = true; //and moveMade is set to true so another Tile can be added
        }
        else if (("q").equals(key)) Greenfoot.stop(); //if Q is pressed, stop the game
    }


    //this method moves the tiles in the direction they are facing (they are turned to rotate the direction corresponding to the player's input in checkInput()), until there is something blocking the way of the tile.
    public void moveTiles() {
        for (int i = 0; i<4; i++) {
            if (!atBorder() && !atTile()) { //this moves the tile if it is not going to hit the border or another tile
                move(1);
            }
            else if (!atBorder() && atTile() && atTileOfSameValue()) { //if the tile is facing another tile of the same value,
                mergeTiles(); //the tiles are merged
                break; //this breaks the loop so a tile that has been removed does not try to move agaim
            }
        }
        setRotation(0); //the tile is set to face upward again, as it was before it was moved
    }


    //this method checks to see if the tile is at the edge of the world. If it is, the method returns true.
    public boolean atBorder() {
        if (((getX()==0 && getRotation()==180) || (getX()==3 && getRotation()==0)) || ((getY()==0 && getRotation()==270) || (getY()==3 && getRotation()==90))) return true;
        else return false;
    }


    //this method checks to see if the tile is next to another tile. If it is, the method returns true.
    public boolean atTile() {
        if (getRotation()==270) return getObjectsAtOffset(0, 1, Tile.class).size() != 0; //check if there is a tile one cell up
        else if (getRotation()==180) return getObjectsAtOffset(-1, 0, Tile.class).size() != 0; //check if there is a tile one cell to the left
        else if (getRotation()==90) return getObjectsAtOffset(0, -1, Tile.class).size() != 0; //check if there is a tile one cell down
        else if (getRotation()==0) return getObjectsAtOffset(1, 0, Tile.class).size() != 0; //check if there is a tile one cell to the right
        else return true;


    }
    //this method checks to see if the tile adjacent to the current tile has the same value. If it does, the method returns true.
    public boolean atTileOfSameValue() {
        if (getRotation()==270) return getObjectsAtOffset(0, 1, Tile.class).get(0).getValue() == this.value; //check value of tile one cell up
        else if (getRotation()==180) return getObjectsAtOffset(-1, 0, Tile.class).get(0).getValue() == this.value; //check value of tile one cell to the left
        else if (getRotation()==90) return getObjectsAtOffset(0, -1, Tile.class).get(0).getValue() == this.value; //check value of tile one cell down
        else if (getRotation()==0) return getObjectsAtOffset(1, 0, Tile.class).get(0).getValue() == this.value; //check value of tile one cell to the right
        else return false;
    }


    //this method merges the tiles into one tile, and increases the score.
    public void mergeTiles() {
        if (getRotation()==270) getObjectsAtOffset(0, 1, Tile.class).get(0).increaseValue(); //increase value of tile one cell up
        else if (getRotation()==180) getObjectsAtOffset(-1, 0, Tile.class).get(0).increaseValue(); //increase value of tile one cell to the left
        else if (getRotation()==90) getObjectsAtOffset(0, -1, Tile.class).get(0).increaseValue(); //increase value of tile one cell down
        else if (getRotation()==0) getObjectsAtOffset(1, 0, Tile.class).get(0).increaseValue(); //increase value of tile one cell to the right
        getWorld().removeObject(this); //remove current tile
        Counter.increaseScore(value*2); //increase score
    }


    //this method returns the value of the tile, so that adjacent tiles can see if they are able to merge with it.
    public int getValue() {
        return this.value;
    }


    public void increaseValue() {
        this.value = this.value*2;
    }
}