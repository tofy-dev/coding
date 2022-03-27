import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World {
    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;
    
    int animalDelay = 100;
    int laserDelay = 50;
    
    public MyWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        SCREEN_WIDTH = getWidth();
        SCREEN_HEIGHT = getHeight();
    }
    
    public void act() {
        // System.out.println(getObjects(Laser.class).size());
        if (--animalDelay <= 0) {
            // spawn animal
            Animal ani = new Animal();
            int xPos = Greenfoot.getRandomNumber(SCREEN_WIDTH-ani.width) + ani.width/2;
            int yPos = Greenfoot.getRandomNumber(SCREEN_HEIGHT-ani.height) + ani.height/2;
            addObject(ani, xPos, yPos);
            animalDelay = 100;
        }
        if (--laserDelay <= 0 && getObjects(Animal.class).size() > 0) {
            // spawn laser
            Laser lsr = new Laser();
            int xPos = SCREEN_WIDTH/2;
            int yPos = SCREEN_HEIGHT;
            addObject(lsr, xPos, yPos);
            laserDelay = 50;
        }
    }
}
