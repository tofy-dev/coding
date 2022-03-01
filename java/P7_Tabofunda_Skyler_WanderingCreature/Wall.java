import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    static int HEIGHT = 20;
    static int WIDTH = 20;
    
    public Wall() {
        GreenfootImage wallImg = new GreenfootImage("wallTile.png");
        wallImg.scale(HEIGHT, WIDTH);
        setImage(wallImg);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
