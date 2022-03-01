import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private final int MAX_BUTTERFLIES = 1;
    private int numButterflies;
    private int count;
    
    public MyWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        numButterflies = 0;
        count = 0;
    }
    
    public void spawnButterfly() {
        Butterfly butter = new Butterfly();
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        numButterflies++;
    }
    public void act() {
        count++;
        if (count == 100 && numButterflies < MAX_BUTTERFLIES)  {
            count = 0;
            spawnButterfly();
        }
    }
    
}
