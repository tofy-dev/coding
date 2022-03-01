import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        setBackground("bathroom-tile.jpg");
        Ball ball = new Ball();
        addObject(ball, getWidth()/2, getHeight()/4);
        
        for (int i = 0; i < 5; i++) { 
            Brick brick = new Brick();
            GreenfootImage img = brick.getImage();
            int xPos = getWidth()/2 - 5/2*img.getWidth() + i*img.getWidth();
            addObject(brick, xPos, getHeight()/2);
        }
    }
}
