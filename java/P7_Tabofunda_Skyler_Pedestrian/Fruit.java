import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fruit extends Actor
{
    final private int SPEED = 3;
    public Color fruitColor;
    int fruitX;
    int fruitY;
    
    public Fruit() {
        int fruitCol = Greenfoot.getRandomNumber(3) + 1;
        GreenfootImage img;
        
        if (fruitCol == 1) {
            img = new GreenfootImage("fruit1.png");
            fruitColor = Color.BLUE;
        } else if (fruitCol == 2) {
            img = new GreenfootImage("fruit2.png");
            fruitColor = Color.GREEN;
        } else {
            img = new GreenfootImage("fruit3.png");
            fruitColor = Color.ORANGE;
        }
        
        setImage(img);
    }
    
    public void act()
    {
        World world = getWorld();
        GreenfootImage img = getImage();
        
        // move fruit down
        if (getY() < world.getHeight()-img.getHeight()/2)
            setLocation(getX(), getY() + SPEED);
    }
}
