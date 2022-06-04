import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Text extends Actor
{
    public Text(String msg, int size, Color fClr, Color bClr) {
        GreenfootImage img = new GreenfootImage(msg, size, fClr, bClr);
        this.setImage(img);
    }
}
