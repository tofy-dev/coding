import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;
import java.util.ArrayList;

public class MyWorld extends World
{
    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 400;

    Blue blue;
    Pink pink;
    Heart hrt = null;
    
    public MyWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        setPaintOrder(Heart.class, Blue.class, Pink.class, Trail.class);
        blue = new Blue();
        pink = new Pink();
        addObject(blue, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        addObject(pink, SCREEN_WIDTH/2-pink.getImage().getWidth()/2, SCREEN_HEIGHT/2);
    }
    
    public void act() {
        System.out.println(blue.prevPoses.size());
        if (pink.target == null && blue.prevPoses.size() > 0) {
            System.out.println("trl size is " + blue.trails.size());
            pink.target = blue.prevPoses.remove(0);
            if (blue.trails.size() > 0) {
                removeObject(blue.trails.remove(0));
            }
                
        }
        
        // if blue is at the pink position
        if (blue.blueX == pink.pinkX && blue.blueY == pink.pinkY) {
            pink.delayCounter = 0;
            if (hrt == null) {
                hrt = new Heart();
                addObject(hrt, pink.pinkX, pink.pinkY);
            } else {
                hrt.setLocation(pink.pinkX, pink.pinkY);
            }
        }
        
        System.out.println("delay" + pink.delayCounter);
        System.out.println("blueX: " + blue.blueX + ", blueY: " + blue.blueY);
        System.out.println("pinkX: " + pink.pinkX + ", pinkY: " + pink.pinkY);
    }
}
