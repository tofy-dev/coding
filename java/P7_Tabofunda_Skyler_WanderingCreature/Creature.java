import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Actor
{    
    final int SPEED = 3;
    final GreenfootImage ZOMB_IMG = new GreenfootImage("zombie.png");
    
    public Creature() {
        setImage(ZOMB_IMG);

    }
    
    public void act()
    {
        int SCREEN_WIDTH = getWorld().getWidth();
        int SCREEN_HEIGHT = getWorld().getHeight();
        
        
        // orient creature
        int angle = Greenfoot.getRandomNumber(21) - 10;
        turn(angle);
        move(SPEED);
        
        // check if in wall
        int brickW = Wall.WIDTH;
        int brickH = Wall.HEIGHT;
        
        // check collisons with side walls
        
        if (isColliding()) {
            turn(180);
            move(2*SPEED);
        }
        
        // eat treat (if possible)
        Coin coin = (Coin) getOneIntersectingObject(Coin.class);
        if (coin != null) {
            getWorld().removeObject(coin);
        }
    }
    
    public boolean isColliding() {
        Wall collWall = (Wall) getOneIntersectingObject(Wall.class);
        return (collWall != null);
    }
}
