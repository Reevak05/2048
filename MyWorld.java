import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    
    //constructor:
    public MyWorld()
    {
        super(4, 5, 100); //creates a new world of 4x5 cells with a cell size of 100x100 pixels
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Counter counter = new Counter(); //creates a Counter object
        addObject(counter,3,4); //adds the counter to the world
        counter.setLocation(2,4); //sets the location of the counter
        
        Instructions instructions = new Instructions(); //creates an Instructions object
        addObject(instructions,3,4); //adds the instructions to the world
        instructions.setLocation(2,2); //sets the location of the instructions
    }


    /**
     * Act - do whatever the MyWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * MyWorld always runs act() before the other classes, which are run in unspecified order.
     */
    public void act() {
        if (Instructions.gameStarted && Tile.moveMade) { //if the game has started and the player has made a move,
            Greenfoot.delay(2); //the game is momentarily delayed so the player can tell which tile was added,
            addTile(); //a tile is added to the board,
            Tile.moveMade = false; //and Tile.moveMade is set to false so multiple tiles are not added for one move
        }

        if (Instructions.gameStarted) Tile.key = Greenfoot.getKey(); //set Tile.key to the last pressed key; this has to be done in the MyWorld class because there can be multiple tiles on the board simultaneously and Greenfoot.getKey() returns the key pressed (or null if no keys have been pressed) since the last time Greenfoot.getKey() was called
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
        } while (getObjectsAt(X, Y, Tile.class).size() != 0); //checks to see if there is already a tile in the space which was randomly selected. If there is, the method chooses random numbers again
        addObject(new Tile(), X, Y); //once an empty space is found, a new tile is added
    }
}
