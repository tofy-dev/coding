import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bat extends Actor {
    GreenfootImage bat_01 = new GreenfootImage("bat_01.png");
    GreenfootImage bat_02 = new GreenfootImage("bat_02.png");
    
    int frameCounter = 0;
    int speed = 3;
    int batX;
    int batY;
    int mouseX;
    int mouseY;
    
    public Bat() {
        batX = 300;
        batY = 200;
    }
    
    public void act() {
        // update mouse information
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo != null) {
           mouseX = mouseInfo.getX();
           mouseY = mouseInfo.getY();
        }
        
        // move bat towards mouse
        if (batX < mouseX - speed) {
            batX += speed;
        } else if (batX > mouseX + speed) {
            batX -= speed;
        }
        
        if (batY < mouseY - speed) {
            batY += speed;
        } else if (batY > mouseY + speed) {
            batY -= speed;
        }
        
            setLocation(batX, batY);
            turnTowards(mouseX, mouseY);
        
        // flapping animation
        frameCounter++;
        if (frameCounter == 10) {
           setImage(bat_01);
        } else if (frameCounter == 20) {
           setImage(bat_02);
           frameCounter = 0;
        }
    }
}
