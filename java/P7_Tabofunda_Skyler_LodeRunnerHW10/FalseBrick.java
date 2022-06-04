import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FalseBrick extends Actor
{
    public FalseBrick() {
        setImage("bricks.png");
    }
    
    public void act() {
        if (isTouching(Player.class) || isTouching(Enemy.class)) {
            setImage("false_brick.png");
        }
    }
}