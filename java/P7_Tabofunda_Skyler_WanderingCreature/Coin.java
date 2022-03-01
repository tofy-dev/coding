import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Coin extends Actor
{
    public Coin() {
        GreenfootImage coinImg = new GreenfootImage("Coin.png");
        coinImg.scale(40, 40);
        setImage(coinImg);
    }
    
    public boolean isColliding() {
        Wall collWall = (Wall) getOneIntersectingObject(Wall.class);
        return (collWall != null);
    }
}
