import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    final int SCREEN_HEIGHT = getHeight();
    final int SCREEN_WIDTH = getWidth();
    
    GreenfootImage ppl1 = new GreenfootImage("ppl1.png");
    
    public MyWorld()
    {    
        super(600, 400, 1, false);
        
        // add pedestrian
        Pedestrian ped = new Pedestrian();
        addObject(ped, SCREEN_WIDTH/2, SCREEN_HEIGHT-ppl1.getHeight()/2);
    }
    
    public void act() {
        if (Greenfoot.getRandomNumber(100) == 0) {
            Fruit fruit = new Fruit();
            GreenfootImage img = fruit.getImage();
            
            int fruitX = Greenfoot.getRandomNumber(SCREEN_WIDTH-img.getWidth()/2) + img.getWidth()/2;
            int fruitY = -img.getWidth()/2;
            addObject(fruit, fruitX, fruitY);
        }
    }
}