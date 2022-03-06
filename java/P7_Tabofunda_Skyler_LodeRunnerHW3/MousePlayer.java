import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MousePlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MousePlayer extends Movable
{
    int mouseX;
    int mouseY;
    
    public MousePlayer() {
        setImage("player_stand.png");
    }
    
    public void act()
    {
        playerX = getX();
        playerY = getY();
        
        updateMousePos();
        movePlayer();
        fixPosition(); 
        updatePosition();
    }
    
    public void movePlayer() {
        if (!onWall() && !onBar() && !onLadder()) {
            playerY += GRAVITY;
            updateAnimation("fall", "down");
        } else if (mouseX < playerX-P_WIDTH/2) {
            if (onBar()) {
                playerY = touchingBars().get(0).getY()+8;
                playerX -= SPEED/2;
                updateAnimation("hang", "left");
            } else {
                playerX -= SPEED;
                updateAnimation("run", "left");
            }
        } else if (mouseX > playerX+P_WIDTH/2) {
            if (onBar()) {
                playerY = touchingBars().get(0).getY()+8;
                playerX += SPEED/2;
                updateAnimation("hang", "right");
            } else {
                playerX += SPEED;
                updateAnimation("run", "right");
            }
        } else if (mouseY < playerY-P_HEIGHT/2) {
            if (onLadder()) {
                playerY -= SPEED;
                updateAnimation("climb", "up");
            }
        } else if (mouseY > playerY+P_WIDTH/2) {
            if (onLadder()) {
                playerY += SPEED;
                updateAnimation("climb", "down");
            } else if (onBar()) {
                playerY += SPEED;
                updateAnimation("fall", "down");
            }
        }
    }
    
    public void updateMousePos() {
        if (Greenfoot.getMouseInfo() != null) {
            mouseX = Greenfoot.getMouseInfo().getX();
            mouseY = Greenfoot.getMouseInfo().getY();
        } else {
            mouseX = SCREEN_WIDTH/2;
            mouseY = SCREEN_HEIGHT/2;
        }
    }
}
