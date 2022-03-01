import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Banana extends Actor
{
    public void act()
    {
        World world = getWorld();
        boolean isIntersecting = getIntersectingObjects(Bat.class).size() > 0;
        if (isIntersecting) { world.removeObject(this); } 
    }
}
