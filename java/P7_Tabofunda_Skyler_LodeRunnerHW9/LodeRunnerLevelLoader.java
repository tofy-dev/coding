import greenfoot.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * This is a specialized level loader that has convience methods for defining the symbols associated with common classes in Lode Runner.
 * It also can load a level given just the level number.
 * To load level N, it loads the Nth file in the levels folder when sorted in alphabetical order.
 * 
 * @author Ted McLeod
 * @version 3/24/2022
 */
public class LodeRunnerLevelLoader extends LevelLoader {
    
    /** The symbol representing a wall **/
    public static final String WALL = "W";
    
    /** The symbol representing gold **/
    public static final String GOLD = "G";
    
    /** The symbol representing a ladder **/
    public static final String LADDER = "L";
    
    /** The symbol representing a bar **/
    public static final String BAR = "B";
    
    /** The symbol representing a player **/
    public static final String PLAYER = "P";
    
    /** The symbol representing an enemy **/
    public static final String ENEMY = "E";
    
    /** The symbol representing a false wall **/
    public static final String FALSE_WALL = "F";
    
    /** The symbol representing a win ladder **/
    public static final String WIN_LADDER = "C";
    
    /** The symbol representing an undiggable wall **/
    public static final String UNDIGGABLE_WALL = "U";
    
    /** A constructor that creates a LodeRunnerLevelLoader to load levels in the given world.
     * 
     * @param world the world to load levels in
     */
    public LodeRunnerLevelLoader(World world) {
        super(world);
    }
    
    /**
     * sets the class that should be used to represent walls
     * 
     * @param cls the class that represents walls
     */
    public void setWallClass(Class<? extends Actor> cls) {
        defineType(WALL, cls);
    }
    
    
    /**
     * sets the class that should be used to represent gold
     * 
     * @param cls the class that represents gold
     */
    public void setGoldClass(Class<? extends Actor> cls) {
        defineType(GOLD, cls);
    }
    
    
    /**
     * sets the class that should be used to represent ladders
     * 
     * @param cls the class that represents ladders
     */
    public void setLadderClass(Class<? extends Actor> cls) {
        defineType(LADDER, cls);
    }
    
    
    /**
     * sets the class that should be used to represent bars
     * 
     * @param cls the class that represents bars
     */
    public void setBarClass(Class<? extends Actor> cls) {
        defineType(BAR, cls);
    }
    
    
    /**
     * sets the class that should be used to represent players
     * 
     * @param cls the class that represents players
     */
    public void setPlayerClass(Class<? extends Actor> cls) {
        defineType(PLAYER, cls);
    }
    
    /**
     * sets the class that should be used to represent enemies
     * 
     * @param cls the class that represents enemies
     */
    public void setEnemyClass(Class<? extends Actor> cls) {
        defineType(ENEMY, cls);
    }
    
    
    /**
     * sets the class that should be used to represent false walls
     * 
     * @param cls the class that represents false walls
     */
    public void setFalseWallClass(Class<? extends Actor> cls) {
        defineType(FALSE_WALL, cls);
    }
    
    
    /**
     * sets the class that should be used to represent win ladders
     * 
     * @param cls the class that represents win ladders
     */
    public void setWinLadderClass(Class<? extends Actor> cls) {
        defineType(WIN_LADDER, cls);
    }
    
    
    /**
     * sets the class that should be used to represent undiggable walls
     * 
     * @param cls the class that represents undiggable walls
     */
    public void setUndiggableWallClass(Class<? extends Actor> cls) {
        defineType(UNDIGGABLE_WALL, cls);
    }
    
    /**
     * Gets an array of all the levels in the levels folder or returns null if there is no levels folder.
     * 
     * @return an array of all the levels in the levels folder or null if no levels folder exists
     */
    private static String[] getLevels() {
        File levelsDir = new File(ROOT + "/levels");
        String[] levels = null;
        if (!levelsDir.exists()) {
            System.out.println("Warning: This project has no levels folder!");
        } else {
            File[] files = levelsDir.listFiles();
            ArrayList<File> levelsList = new ArrayList<>();
            for (File f : files) {
                if (f.getName().endsWith(".txt")) levelsList.add(f);
            }
            
            Collections.sort(levelsList, new Comparator<File>() {
                public int compare(File a, File b) {
                    return a.getName().compareTo(b.getName());
                }
            });
            
            levels = new String[levelsList.size()];
            for (int i = 0; i < levelsList.size(); i++) {
                levels[i] = levelsList.get(i).getAbsolutePath();
            }
        }
        return levels;
    }
    
    /**
     * returns the number of levels in the levels folder
     * 
     * @return the number of levels in the levels folder.
     */
    public static int numLevels() {
        return getLevels().length;
    }
    
    /**
     * Loads the given level with the boundaries of the level offset by the given (offX, offY)
     */
    public void loadLevel(int level, int offX, int offY) {
        loadLevel(getLevelPath(level), offX, offY);
    }

    /**
     * returns the file path to the given level, where levels are the files in the levels folder of the project.
     * For example, LodeRunnerLevelLoader.getLevelPath(3) would return the path to the third file in the levels folder
     * when they are listed alphabetically. If there is no such file, an exception will be thrown.
     * 
     * @return the file path to the given level
     */
    public static String getLevelPath(int level) {
        return getLevels()[level - 1];
    }
   
}
