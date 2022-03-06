    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;
import java.util.ArrayList;

/**
 * Write a description of class Pink here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pink extends Actor
{
    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 400;
    
    final int P_WIDTH = new GreenfootImage("Pink.png").getWidth();
    final int P_HEIGHT = new GreenfootImage("Pink.png").getHeight();
    
    final int SPEED = 5;
    
    int pinkX = SCREEN_WIDTH/2-P_WIDTH/2;
    int pinkY = SCREEN_HEIGHT/2;
    
    Point target;
    int delayCounter = 0;
    
    public Pink() {
        setImage("Pink.png");
    }
    
    public void act() {
        // System.out.println("target:" + target);
        // System.out.println("pX: " + pinkX + " pY: " + pinkY);
        if (delayCounter >= 100 && target != null) {
            System.out.println("runs");
            if (pinkX < target.getX()) {
                if (target.getX() - pinkX <= SPEED)
                    pinkX = (int) target.getX();
                else pinkX += SPEED;
            } else if (pinkX > target.getX()) {
                if (pinkX - target.getX() <= SPEED)
                    pinkX = (int) target.getX();
                else pinkX -= SPEED;
            } else if (pinkY < target.getY()) {
                if (target.getY() - pinkY <= SPEED)
                    pinkY = (int) target.getY();
                else pinkY += SPEED;
            } else if (pinkY > target.getY()) {
                if (pinkY - target.getY() <= SPEED)
                    pinkY = (int) target.getY();
                else pinkY -= SPEED;
            }
            
            if (pinkX == target.getX() && pinkY == target.getY()) {
                target = null;
            }
            
            updatePosition();
        } else {
            delayCounter++;
        }

    }
    
    public void updatePosition() {
        setLocation(pinkX, pinkY);
    }
}
