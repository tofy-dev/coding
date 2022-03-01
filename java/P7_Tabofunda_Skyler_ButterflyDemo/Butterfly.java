import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Butterfly here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Butterfly extends Actor
{
    private final int maxFrame = 15;
    private int frame = 1;
    private int scaleW = 50;
    
    public Butterfly() {
        scaleImage();
    }
    
    public void act()
    {
        setImage("butterfly blue animation " + ++frame + " 1200.png");
        if (frame == maxFrame) {
            frame = 1;
        }
        scaleImage();
    }
    
    public void scaleImage() {
        GreenfootImage img = getImage();
        double oW = img.getWidth();
        double oH = img.getHeight();
        int scaleH = (int)(oH / oW * scaleW);
        img.scale(scaleW, scaleH);
    }
}
