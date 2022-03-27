import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MousePlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Movable
{
    int targetX;
    int targetY;
    
    public Enemy() {
        setImage("enemy_stand.png");
        type = "enemy";
    }
    
    public void act() {
        updateTargetPos();
        super.act();
    }
    
    @Override
    public void movePlayer() {
        if (targetX < playerX-P_WIDTH/2) {
            dir = "left";
        } else if (targetX > playerX+P_WIDTH/2) {
            dir = "right";
        } else if (targetY < playerY-P_HEIGHT/2) {
            dir = "up";
        } else if (targetY > playerY+P_WIDTH/2) {
            dir = "down";
        } else {
            dir = "none";
        }
        super.movePlayer();
    }
    
    public void updateTargetPos() {
        Player plr = getWorld().getObjects(Player.class).get(0);
        targetX = plr.getX();
        targetY = plr.getY();
    }
}
