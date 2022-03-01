import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public class Creature extends Actor
{
    final int BUG_W = new GreenfootImage("ladybug1.png").getWidth();
    final int BUG_H = new GreenfootImage("ladybug1.png").getHeight();
    final int SPEED = 2;
    
    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;
    
    int mapX;
    int mapY;
    
    ArrayList<ArrayList> map = new ArrayList<ArrayList>();
    int wallDim = 32;
    
    public Creature() {
        setImage(new GreenfootImage("ladybug1.png"));
    }
    
    boolean isIniting = true;
    public void act()
    {
        if (!isIniting) {
            mapMove();
            eatCoin();
            updateMap(getX(), getY());
            printMap();
        } else {
            SCREEN_WIDTH = getWorld().getWidth();
            SCREEN_HEIGHT = getWorld().getHeight();
            initMap();
            isIniting = false;
        }
    }
    
    
    // =======================================================
    // moving methods
    // =======================================================
    public void mapMove() {        
        // 1st priority: going to empty
        if (mapInfoAt(mapX + 1, mapY).equals("_") && mapX < map.get(0).size()-1) {
            setRotation(0);
        } else if (mapInfoAt(mapX - 1, mapY).equals("_") && mapX > 0) {
            setRotation(180);
        } else if (mapInfoAt(mapX, mapY + 1).equals("_") && mapY < map.size()-1) {
            setRotation(90);
        } else if (mapInfoAt(mapX, mapY - 1).equals("_") && mapY > 0) {
            setRotation(270);
        } else {
            moveRandomly();
        }
        
        move(SPEED);
    }
    
    public void moveRandomly() {
        int angle = Greenfoot.getRandomNumber(21) - 10;
        turn(angle);
        move(SPEED);
        
        if (getOneIntersectingObject(Wall.class) != null) {
            turn(180);
            move(SPEED);
        }
    }
    
    
    // =======================================================
    // map stuff
    // =======================================================
    public String mapInfoAt(int mapX, int mapY) {
        return (String) map.get(mapY).get(mapX);
    }
    
    public void updateMap(int x, int y) {
        if (x % (wallDim/2) == 0 && x % (wallDim) != 0) {
            mapX = (int) Math.floor(x / wallDim);
        }
        if (y % (wallDim/2) == 0 && y % (wallDim) != 0) {
            mapY = (int) Math.floor(y / wallDim);
        }
        map.get(mapY).set(mapX, "X");
    }
    
    public void printMap() {
        for (var i = 0; i < map.size(); i++) {
            System.out.println(map.get(i));
        }
        System.out.println("mapx: " + mapX);
        System.out.println("mapy: " + mapY);
    }
    
    public void initMap() {
        mapX = (int) Math.floor(getX()/wallDim);
        mapY = (int) Math.floor(getY()/wallDim);
        for (int y = 0; y < SCREEN_HEIGHT/wallDim; y++) {
            // add new row for map
            map.add(new ArrayList<String>());
            for (int x = 0; x < SCREEN_WIDTH/wallDim; x++) {
                // add item at (x, y) to map
                List<Wall> itemsAt = getWorld().getObjectsAt(x*wallDim+wallDim/2, y*wallDim+wallDim/2, Wall.class);
                if (itemsAt.size() == 0) {
                    map.get(y).add("_");
                } else {
                    map.get(y).add("W");
                }
            }
        }
    }
    
    
    // =======================================================    
    // helper funcs
    // =======================================================
    public void eatCoin() {
        if (getOneIntersectingObject(Treat.class) != null) {
            getWorld().removeObject(getOneIntersectingObject(Treat.class));
        }
    }
}
