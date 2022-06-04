import greenfoot.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.lang.reflect.*;
import java.awt.FileDialog;
import java.awt.Dialog;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.Dimension;

/**
 * This class can be used to save and load levels saved in files.
 * 
 * @author Ted McLeod 
 * @version 3/24/2022
 */
public class LevelLoader {

    /** The root directory of the project this LevelLoader is in **/
    public static final String ROOT = System.getProperty("user.dir");

    // new line
    private static final String NL = System.lineSeparator();

    // world the level loader can load a level into or save a level from
    private World world;

    // A map defining which classes are associated with which type strings
    private final Map<String, Class<? extends Actor>> typeMap;
    
    private boolean levelJustLoaded = false;

    /**
     * Constructor that creates a LevelLoader that can load levels in the given world
     * with the given typeMap definining which symbols represent which classes
     * 
     * @param world the world this loader will load a level in
     * @param typeMap a map defining each symbol and the class associated with the symbol
     */
    public LevelLoader(World world, Map<String, Class<? extends Actor>> typeMap) {
        this.world = world;
        this.typeMap = new HashMap<>(typeMap);
    }
    
    /**
     * Constructor that creates a LevelLoader that can load levels in the given world.
     * 
     * @param world the world this loader will load a level in
     */
    public LevelLoader(World world) {
        this.world = world;
        this.typeMap = new HashMap<>();
    }
    
    /**
     * defines which class is represented by the given symbol
     * 
     * @param symbol the string representing the class
     * @param cls the class the symbol represents
     */
    public void defineType(String symbol, Class<? extends Actor> cls) {
        typeMap.put(symbol, cls);
    }
    
    /**
     * Loads the level at the given file path.
     * 
     * @param filePath the path to the file to load
     */
    public void loadLevel(String filePath) {
        loadLevel(filePath, 0, 0);
    }
    
    /**
     * Loads the level at the given file path with the given offsets.
     * 
     * @param filePath the path to the file to load
     * @param offX all actor locations are offset by this amount horizontally
     * @param offY all actor locations are offset by this amount vertically
     */
    public void loadLevel(String filePath, int offX, int offY) {
        world.removeObjects(world.getObjects(null));
        try (Scanner fileReader = new Scanner(new File(filePath));) {
            int lineNum = 1;
            if (!fileReader.hasNextLine()) {
                throw new ParseException("File is empty.", lineNum);
            } else {
                fileReader.nextLine(); // skip world dimensions
            }
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Scanner lineReader = new Scanner(line);
                if (lineReader.hasNext()) {
                    String type = lineReader.next();
                    Actor thing;
                    int x, y, w, h;
                    Class<? extends Actor> cls = typeMap.get(type);
                    if (cls == null) throw new ParseException("Invalid type: " + type, lineNum);
                    thing = getActorOfType(cls);
                    if (lineReader.hasNextInt()) x = lineReader.nextInt() + offX;
                    else throw new ParseException("Missing x coordinate." , lineNum);
                    if (lineReader.hasNextInt()) y = lineReader.nextInt() + offY;
                    else throw new ParseException("Missing y coordinate." , lineNum);
                    if (lineReader.hasNextInt()) w = lineReader.nextInt();
                    else throw new ParseException("Missing width." , lineNum);
                    if (lineReader.hasNextInt()) h = lineReader.nextInt();
                    else throw new ParseException("Missing height." , lineNum);
                    thing.getImage().scale(w, h);
                    world.addObject(thing, x, y);
                }
                lineNum++;
            }
        } catch (FileNotFoundException err) {
            err.printStackTrace(); // file does not exist
        } catch (ParseException perr) {
            perr.printStackTrace(); // invalid file format
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // constructor failed
        }
        levelJustLoaded = true;
    }

    /**
     * Saves the state of all the objects in the world that are of a class in the
     * type map in the file at the given file path.
     * 
     * @param filePath the path to the file to save the level in
     */
    public void saveLevel(String filePath) {
        saveLevel(filePath, world.getWidth(), world.getHeight());
    }
    
    /**
     * Saves the state of all the objects in the world that are of a class in the
     * type map in the file at the given file path and records the desired
     * world width and height.
     * 
     * @param filePath the path to the file to save the level in
     * @param width the world width
     * @param height the world height
     */
    public void saveLevel(String filePath, int width, int height) {
        try (FileWriter fw = new FileWriter(new File(filePath));) {
            fw.write(width + " " + height + NL);
            for (Map.Entry<String, Class<? extends Actor>> entry : typeMap.entrySet()) {
                List<? extends Actor> actors = world.getObjects(entry.getValue());
                for (Actor actor : actors) {
                    fw.write(getSaveString(actor, entry.getKey()) + NL);
                }
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private String getSaveString(Actor a, String type) {
        int x = a.getX();
        int y = a.getY();
        int w = a.getImage().getWidth();
        int h = a.getImage().getHeight();
        return type + " " + x + " " + y + " " + w + " " + h;
    }

    /**
     * Prompts the user to choose a file to save to and returns the path to the file chosen.
     * If the user does not choose a file (i.e. presses cancel), it returns null.
     * 
     * @return the path to the file chosen or null if no file chosen.
     */
    public static String promptForSavePath() {
        return promptForFilePath(FileDialog.SAVE);
    }

    /**
     * Prompts the user to choose a file to load and returns the path to the file chosen.
     * If the user does not choose a file (i.e. presses cancel), it returns null.
     * 
     * @return the path to the file chosen or null if no file chosen.
     */
    public static String promptForLoadPath() {
        return promptForFilePath(FileDialog.LOAD);
    }

    private static String promptForFilePath(int saveOrLoad) {
        Dialog d = null;
        FileDialog fileDialog = new FileDialog(d, "Choose save file", saveOrLoad);
        fileDialog.setVisible(true);
        File[] files = fileDialog.getFiles();
        if (files.length > 0) return files[0].toString();
        return null;
    }

    /**
     * Returns the dimensions of the world in the file at the given path
     * 
     * @return the dimensions of the world in the file at the given path
     */
    public static Dimension getWorldDimensions(String filePath) {
        Dimension dim = null;
        try (Scanner fileReader = new Scanner(new File(filePath));) {
            int lineNum = 1;
            if (!fileReader.hasNextLine()) {
                throw new ParseException("File is empty.", lineNum);
            } else {
                Scanner dimReader = new Scanner(fileReader.nextLine());
                int w, h;
                if (dimReader.hasNextInt()) {
                    w = dimReader.nextInt();
                } else {
                    throw new ParseException("Missing world width.", lineNum);
                }
                if (dimReader.hasNextInt()) {
                    h = dimReader.nextInt();
                } else {
                    throw new ParseException("Missing world height.", lineNum);
                }
                return new Dimension(w, h);
            }
        } catch (FileNotFoundException err) {
            err.printStackTrace(); // file does not exist
        } catch (ParseException perr) {
            perr.printStackTrace(); // invalid file format
        }
        return dim;
    }

    /**
     * Get an instance of the given class. Will fail if there is not a default constructor for the class.
     */
    public Actor getActorOfType(Class<? extends Actor> cls) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<? extends Actor> constructor = cls.getConstructor();
        return constructor.newInstance();
    }
    
    /**
     * Returns true the first time it is called after a level has been loaded and then returns false after that.
     * 
     * @return true if this is the first time this method is being called after loading a level and false otherwise
     */
    public boolean isLevelJustLoaded() {
        if (levelJustLoaded) {
            levelJustLoaded = false;
            return true;
        }
        return levelJustLoaded;
    }

}