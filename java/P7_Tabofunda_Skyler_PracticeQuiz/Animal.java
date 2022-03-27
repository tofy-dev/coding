import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

public class Animal extends Actor
{
    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;
    int SPEED = 3;
    
    int width, height;
    int xPos, yPos;
    
    String direction = "right";

    public Animal() {
        GreenfootImage img = new GreenfootImage("sheep.png");
        width = img.getWidth();
        height = img.getHeight();
        setImage(img);
    }
    
    boolean isFirstRun = true;
    public void act() {
        if (isFirstRun) {
            SCREEN_WIDTH = getWorld().getWidth();
            SCREEN_HEIGHT = getWorld().getHeight();
            isFirstRun = false;
        } else {
            updateDir();
            move();
        }
    }
    
    public void updateDir() {
        xPos = getX();
        yPos = getY();
        
    }
    
    public void move() {
        if (Greenfoot.getRandomNumber(20) == 0)
            moveRandomly();
        
        if (direction == "right") {
            int dist = Math.min(SCREEN_WIDTH - (getX()+width/2), SPEED);
            xPos += dist;
            if (dist != SPEED) moveRandomly("right");
        } else if (direction == "left") {
            int dist = Math.min(getX()-width/2, SPEED);
            xPos -= dist;
            if (dist != SPEED) moveRandomly("left");
        } else if (direction == "up") {
            int dist = Math.min(getY()-width/2, SPEED);
            yPos -= dist;
            if (dist != SPEED) moveRandomly("up");
        } else if (direction == "down") {
            int dist = Math.min(SCREEN_HEIGHT - (getY()+height/2), SPEED);
            yPos += dist;
            if (dist != SPEED) moveRandomly("down");
        }
        
        updatePos();
    }
    
    public void moveRandomly() {
        int randDir = Greenfoot.getRandomNumber(4);
        if (randDir == 0) {
            direction = "right";
        } else if (randDir == 1) {
            direction = "left";
        } else if (randDir == 2) {
            direction = "up";
        } else if (randDir == 3) {
            direction = "down";
        }
    }
    
    public void moveRandomly(String notDir) {
        ArrayList<String> allDirs = new ArrayList<String>(Arrays.asList("left", "right", "up", "down"));
        allDirs.remove(allDirs.indexOf(notDir));
        direction = allDirs.get(Greenfoot.getRandomNumber(3));
    }
    
    public void updatePos() {
        setLocation(xPos, yPos);
    }
}
