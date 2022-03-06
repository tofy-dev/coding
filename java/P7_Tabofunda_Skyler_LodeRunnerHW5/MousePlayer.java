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
        updateMousePos();
        super.act();
    }
    
    public void movePlayer() {
        if (mouseX < playerX-P_WIDTH/2) {
            dir = "left";
        } else if (mouseX > playerX+P_WIDTH/2) {
            dir = "right";
        } else if (mouseY < playerY-P_HEIGHT/2) {
            dir = "up";
        } else if (mouseY > playerY+P_WIDTH/2) {
            dir = "down";
        } else {
            dir = "none";
        }
        super.movePlayer();
    }
    
    private void updateMousePos() {
        if (Greenfoot.getMouseInfo() != null) {
            mouseX = Greenfoot.getMouseInfo().getX();
            mouseY = Greenfoot.getMouseInfo().getY();
        } else {
            mouseX = SCREEN_WIDTH/2;
            mouseY = SCREEN_HEIGHT/2;
        }
    }
}
