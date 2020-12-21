import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    private boolean keyReleased = true; //this instance variable tracks whether the key was released in checkInput(), to prevent multiple actions from one key press

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 400x400 cells with a cell size of 100x100 pixels.
        super(4, 5, 100);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Counter counter = new Counter();
        addObject(counter,3,4);
        counter.setLocation(2,4);
        
        Instructions instructions = new Instructions();
        addObject(instructions,3,4);
        instructions.setLocation(2,2);
    }


    /**
     * Act - do whatever the MyWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * MyWorld always runs act() before the other classes, which are run in unspecified order.
     */
    public void act() {
        /*if (Tile.moveMade) {
            addTile();
            Tile.moveMade = false;
        }*/
        if (Instructions.gameStarted) checkInput(); //if the game has started, this method checks if an arrow key has been pressed to determine if the player made a move. If so, it runs addTile() to add another tile to the board.

    }
    
    //this method checks if an arrow key has been pressed, to determine if the player made a move. If so, it runs addTile() to add another tile to the board.
    public void checkInput() {
        Tile.key = Greenfoot.getKey(); //set Tile instance variable key to the last pressed key; this has to be done in the Counter class because there can be multiple tiles on the board simultaneously
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
        } while (getObjectsAt(X, Y, Tile.class).size() != 0); //the method checks to see if there is already a tile in the space which was randomly selected. If there is, the method chooses random numbers again
        addObject(new Tile(), X, Y); //once an empty space is found, a new tile is added
    }
}
