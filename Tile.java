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
    private static boolean newTileAdded = false;
    private boolean keyReleased = true; //this instance variable tracks whether the key was released in checkInput(), to prevent multiple actions from one key press
    public static String key; //this instance variable is used to track the last pressed key in checkInput() in both Tile.java and Counter.java


    //constructor:
    public Tile() {
        GreenfootImage image = new GreenfootImage("Tile.png");
        image.scale(100, 100);
        setImage(image);
    }
    /**
    * Act - do whatever the Tile wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
    public void act()
    {
        updateTileValue(); //this updates the number on the tile with the current value, so when the value of a tile changes, the number on it changes to reflect its new value
        checkInput();
    }


    //this updates the number on the tile with the current value, so when the value of a tile changes, the number on it changes to reflect its new value
    public void updateTileValue() {
        Color color = new Color(0,0,0,0);
        GreenfootImage image = new GreenfootImage("Tile.png");
        image.scale(100, 100);
        GreenfootImage tileValueImage = new GreenfootImage(" " + value, 40, Color.BLACK, color);
        image.drawImage(tileValueImage, (image.getWidth() - tileValueImage.getWidth()) / 2, (image.getHeight() - tileValueImage.getHeight()) / 2);
        setImage(image);
    }


    //this method checks the last pressed key, then runs moveTiles() if an arrow key was pressed to moves the tile accordingly
    public void checkInput() {
        //key = Greenfoot.getKey(); //set instance variable key to the last pressed key        new removed
        if (key == null) { //if no key was pressed (meaning all keys have been released), set keyReleased to true
            keyReleased = true;
        }
        else if (("up").equals(key) && keyReleased) { //if the up arrow is pressed,
            setRotation(270); //the tile is moved to face upwards,
            moveTiles(); //the tile is moved,
            keyReleased = false; //and keyReleased is set to false, so the key has to be released before the command can be run again.
        }
        else if (("left").equals(key) && keyReleased) { //if the left arrow is pressed,
            setRotation(180); //the tile is moved to face leftwards,
            moveTiles(); //the tile is moved,
            keyReleased = false; //and keyReleased is set to false, so the key has to be released before the command can be run again.
        }
        else if (("down").equals(key) && keyReleased) { //if the down arrow is pressed,
            setRotation(90); //the tile is moved to face downwards,
            moveTiles(); //the tile i //and keyReleased is set to false, so the key has to be released before the command can be run again.s moved,
            keyReleased = false;
        }
        else if (("right").equals(key) && keyReleased) { //if the right arrow is pressed,
            setRotation(0); //the tile is moved to face rightwards,
            moveTiles(); //the tile is moved,
            keyReleased = false; //and keyReleased is set to false, so the key has to be released before the command can be run again.
        }
        else if (("q").equals(key)) Greenfoot.stop();

    }


    //this method moves the tiles in the direction they are facing (they are turned to rotate the direction corresponding to the user's input in checkInput()), until there if something blocking the way of the tile.
    public void moveTiles() {
        for (int i = 0; i<4; i++) {
            if (!atBorder() && !atTile()) { //this moves the tile if it is not going to hit the border or another tile
                move(1);
            }
            else if (atTile() && atTileOfSameValue()) {
                mergeTiles(); //if the tile is of the same value, merge the tiles
                break; //this breaks the loop so a tile that has been removed does not try to move agaim
            }
        }
        setRotation(0);
        Counter.increaseScore(2); //this increases the score by 2 every move, for Counter demonstration purposes.

    }

    //this method checks to see if the tile is at the edge of the grid. If it is, the method returns true.
    public boolean atBorder() {
        if (((getX()==0 && getRotation()==180) || (getX()==3 && getRotation()==0)) || ((getY()==0 && getRotation()==270) || (getY()==3 && getRotation()==90))) return true;
        else return false;
    }

    //this method checks to see if the tile is next to another tile. If it is, the method returns true.
    public boolean atTile() {
        if (getRotation()==270) return getObjectsAtOffset(0, 1, Tile.class).size() != 0; //up
        else if (getRotation()==180) return getObjectsAtOffset(-1, 0, Tile.class).size() != 0; //left
        else if (getRotation()==90) return getObjectsAtOffset(0, -1, Tile.class).size() != 0; //down
        else if (getRotation()==0) return getObjectsAtOffset(1, 0, Tile.class).size() != 0; //right
        else return false;

    }
    //this method checks to see if the tile adjacent to the current tile has the same value. If it does, the method returns true.
    public boolean atTileOfSameValue() {
        if (getRotation()==270) return getObjectsAtOffset(0, 1, Tile.class).get(0).getValue() == this.value; //up
        else if (getRotation()==180) return getObjectsAtOffset(-1, 0, Tile.class).get(0).getValue() == this.value; //left
        else if (getRotation()==90) return getObjectsAtOffset(0, -1, Tile.class).get(0).getValue() == this.value; //down
        else if (getRotation()==0) return getObjectsAtOffset(1, 0, Tile.class).get(0).getValue() == this.value; //right
        else return false;
    }


    //this method merges the tiles into one tile, and increases the score.
    public void mergeTiles() {
        if (getRotation()==270) getObjectsAtOffset(0, 1, Tile.class).get(0).increaseValue(); //up
        else if (getRotation()==180) getObjectsAtOffset(-1, 0, Tile.class).get(0).increaseValue(); //left
        else if (getRotation()==90) getObjectsAtOffset(0, -1, Tile.class).get(0).increaseValue(); //down
        else if (getRotation()==0) getObjectsAtOffset(1, 0, Tile.class).get(0).increaseValue(); //right
        getWorld().removeObject(this);
        Counter.increaseScore(value*2);
    }

    //this method returns the value of the tile, so that adjacent tiles can see if they are able to merge with it.
    public int getValue() {
        return this.value;
    }

    public void increaseValue() {
        this.value = this.value*2;
    }
}