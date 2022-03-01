import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Ball extends Actor
{    
    int dx = 3;
    int dy = 3;
    
    public Ball() {
        setImage("ball.png");
    }
    
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        World w = getWorld();
        GreenfootImage img = getImage();
        
        // move ball
        if (getY() > w.getHeight() - img.getHeight()/2) {
            dy = -dy;
        }
        if (getY() < img.getHeight()/2) {
            dy = -dy;
        }
        if (getX() > w.getWidth() - img.getWidth()/2) {
            dx = -dx;
        }
        if (getX() < img.getWidth()/2) {
            dx = -dx;
        }
        setLocation(getX() + dx, getY() + dy);
        
        // detect collisions
        Actor obj = getOneIntersectingObject(Brick.class);
        if (obj != null) {
            if (obj.getY() < getY() || obj.getY() > getY()) {
                dy = -dy;
            }
            World world = getWorld();
            world.removeObject(obj);
        }
    }
}
