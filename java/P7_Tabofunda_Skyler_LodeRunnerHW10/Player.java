import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Movable
{
    public Player() {
        setImage("player_stand.png");
        type = "player";
    }
    
    @Override
    public void act() {
        super.act();
        handleInputs();
    }
    
    @Override
    protected void movePlayer() {
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
    
    protected void handleInputs() {
        if (Greenfoot.isKeyDown("z")) {
            System.out.println("z");
        } else if (Greenfoot.isKeyDown("c")) {
             System.out.println("c");
        }
    }
    
    // helper functions =======================================================================================
    public boolean isGold() {
        int goldVal = 250;
        if (getOneIntersectingObject(Gold.class) != null) {
            removeTouching(Gold.class);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isTouchingEnemy() {
        return getOneIntersectingObject(Enemy.class) != null;
    }
}
