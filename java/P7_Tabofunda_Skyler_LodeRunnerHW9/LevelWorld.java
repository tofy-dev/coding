import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A world that loads levels using a LevelLoader.
 * 
 * @author Ted McLeod 
 * @version 3/24/2022
 */
public abstract class LevelWorld extends World {
    
    // The level loader for this world
    private LodeRunnerLevelLoader loader;
    
    // The level
    private int level;
    
    // The level to load when default constructor is executed.
    private static final int DEFAULT_LEVEL = 1;
    private static int leftMargin = 0;
    private static int rightMargin = 0;
    private static int topMargin = 0;
    private static int bottomMargin = 0;

    /**
     * Constructs a LevelWorld that loads level 1
     */
    public LevelWorld() {
        // call the constructor for this class that takes a level
        this(DEFAULT_LEVEL); // load the default level
    }
    
    /**
     * Constructs a LevelWorld that loads the given level
     * 
     * @param level the level to load
     */
    public LevelWorld(int level) {
        // create a world the size of the level but with extra height for the hud
        // make the cell size 1 and make it unbounded so actors can go past the edges
        super(LevelLoader.getWorldDimensions(LodeRunnerLevelLoader.getLevelPath(level)).width + leftMargin + rightMargin, // world width for the level
              LevelLoader.getWorldDimensions(LodeRunnerLevelLoader.getLevelPath(level)).height + topMargin + bottomMargin, // world height for the level + HUD_HEIGHT
              1, // cell size
              false); // unbounded world
        
        // make the background black
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);
        
        // create a LodeRunnerLevelLoader to load levels in this world
        loader = new LodeRunnerLevelLoader(this);
        
        defineClassTypes();
        
        // Store the level in an instance variable
        this.level = level;
        
        // load the level
        loader.loadLevel(level, leftMargin, topMargin);
    }
    
    /**
     * Returns the level loader for this world
     * 
     * @return the level loaded for this world
     */
    public LodeRunnerLevelLoader getLoader() {
        return loader;
    }
    
    /**
     * Returns the level this world has loaded.
     * 
     * @return the level this world has loaded.
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Sets the margins (open space between the world edge and the level edge).
     * 
     * @param left space between the left edge of the world and the left edge of the level
     * @param right space between the right edge of the world and the right edge of the level
     * @param top space between the top edge of the world and the top edge of the level
     * @param bottom space between the bottom edge of the world and the bottom of the level
     */
    public static void setMargins(int left, int right, int top, int bottom) {
        leftMargin = left;
        rightMargin = right;
        topMargin = top;
        bottomMargin = bottom;
    }
    
    /**
     * returns the left margin (space between the left edge of the world and the left edge of the level)
     * 
     * @return the left margin
     */
    public static int getLeftMargin() {
        return leftMargin;
    }
    
    
    /**
     * returns the right margin (space between the right edge of the world and the right edge of the level)
     * 
     * @return the right margin
     */
    public static int getRightMargin() {
        return rightMargin;
    }
    
    
    /**
     * returns the top margin (space between the top edge of the world and the top edge of the level)
     * 
     * @return the top margin
     */
    public static int getTopMargin() {
        return topMargin;
    }
    
    
    /**
     * returns the bottom margin (space between the bottom edge of the world and the bottom edge of the level)
     * 
     * @return the bottom margin
     */
    public static int getBottomMargin() {
        return bottomMargin;
    }
    
    /**
     * Override this method to define which types of objects should be associated with which classes.
     */
    public abstract void defineClassTypes();
    
}
