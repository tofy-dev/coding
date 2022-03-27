import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Laser extends Actor
{
    int SCREEN_WIDTH, SCREEN_HEIGHT;
    int SPEED = 3;
    
    int width, height;
    
    int xPos, yPos;
    
    public Laser() {
        GreenfootImage img = new GreenfootImage("red-draught.png");
        width = img.getWidth();
        height = img.getHeight();
        setImage(img);
    }
    
    boolean isFirstRun = true;
    public void act() {
        if (isFirstRun) {
            SCREEN_WIDTH = getWorld().getWidth();
            SCREEN_HEIGHT = getWorld().getHeight();   
            findTarget();
            isFirstRun = false;
        } else {
            xPos = getX();
            yPos = getY();
            
            move(SPEED);
            
            if (isTouching(Animal.class)) {
                removeTouching(Animal.class);
                getWorld().removeObject(this);
            } else if (xPos < width/2 || xPos > SCREEN_WIDTH-width/2 || yPos < height/2) {
                getWorld().removeObject(this);
            }  
        }
    }
    
    public void findTarget() {
        List<Animal> anims = getWorld().getObjects(Animal.class);
        if (anims.size() > 0) {
            int xTar = anims.get(0).getX();
            int yTar = anims.get(0).getY();
            turnTowards(xTar, yTar);
        }
    }
}
