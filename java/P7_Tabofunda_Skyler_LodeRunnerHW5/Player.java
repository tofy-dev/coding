import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Movable
{
    public Player() {
        setImage("player_stand.png");
    }
    
    public void act() {
        super.act();
    }
    
    public void movePlayer() {
        if (Greenfoot.isKeyDown("left")) {
            dir = "left";
        } else if (Greenfoot.isKeyDown("right")) {
            dir = "right";
        } else if (Greenfoot.isKeyDown("up")) {
            dir = "up";
        } else if (Greenfoot.isKeyDown("down")) {
            dir = "down";
        } else {
            dir = "none";
        }
        super.movePlayer();
    }
}
