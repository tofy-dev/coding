import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BananaWorld extends World
{
    public BananaWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Bat playerBat = new Bat();
        addObject(playerBat, 300, 200);
    }
    
    public void act() {
        int randNum = Greenfoot.getRandomNumber(100);
        if (randNum == 0) {
            Banana banana = new Banana();
            int banW = banana.getImage().getWidth();
            int banH = banana.getImage().getHeight();
            int banX = Greenfoot.getRandomNumber(getWidth() - banW) + banW/2;
            int banY = Greenfoot.getRandomNumber(getHeight() - banH) + banH/2;
            addObject(banana, banX, banY);
        }
    }
}
