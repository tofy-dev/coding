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
    
    public void act()
    {
        playerX = getX();
        playerY = getY();
        
        movePlayer();
        fixPosition(); 
        updatePosition();
    }
    
    public void movePlayer() {
        if (!onWall() && !onBar() && !onLadder()) {
            playerY += GRAVITY;
            updateAnimation("fall", "down");
        } else if (Greenfoot.isKeyDown("left")) {
            if (onBar()) {
                playerY = touchingBars().get(0).getY()+8;
                playerX -= SPEED/2;
                updateAnimation("hang", "left");
            } else {
                playerX -= SPEED;
                updateAnimation("run", "left");
            }
        } else if (Greenfoot.isKeyDown("right")) {
            if (onBar()) {
                playerY = touchingBars().get(0).getY()+8;
                playerX += SPEED/2;
                updateAnimation("hang", "right");
            } else {
                playerX += SPEED;
                updateAnimation("run", "right");
            }
        } else if (Greenfoot.isKeyDown("up")) {
            if (onLadder()) {
                playerY -= SPEED;
                updateAnimation("climb", "up");
            }
        } else if (Greenfoot.isKeyDown("down")) {
            if (onLadder()) {
                playerY += SPEED;
                updateAnimation("climb", "down");
            } else if (onBar()) {
                playerY += SPEED;
                updateAnimation("fall", "down");
            }
        }
    }
}
