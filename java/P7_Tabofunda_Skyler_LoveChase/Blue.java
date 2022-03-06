import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;

public class Blue extends Actor
{
    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 400;
    
    final int SPEED = 5;
    ArrayList<Point> prevPoses = new ArrayList<Point>();
    ArrayList<Trail> trails = new ArrayList<Trail>();
    
    GreenfootImage blueImg = new GreenfootImage("Blue.png");
    int blueX = SCREEN_WIDTH/2;
    int blueY = SCREEN_HEIGHT/2;
    int blueW = blueImg.getWidth();
    int blueH = blueImg.getHeight();
    
    public Blue() {
        setImage(blueImg);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("up") && blueY > blueH/2) {
            drawTrail();
            blueY -= SPEED;
        } else if (Greenfoot.isKeyDown("down") && blueY < SCREEN_HEIGHT-blueH/2) {
            drawTrail();
            blueY += SPEED;
        } else if (Greenfoot.isKeyDown("left") && blueX > blueW/2) {
            drawTrail();
            blueX -= SPEED;
        } else if (Greenfoot.isKeyDown("right") && blueX < SCREEN_WIDTH-blueW/2) {
            drawTrail();
            blueX += SPEED;
        }
        updatePos();
    } 
    
    public void updatePos() {
        setLocation(blueX, blueY);
        
        if (prevPoses.size() == 0 || lastPos().getX() != blueX || lastPos().getY() != blueY) {
            prevPoses.add(new Point(blueX, blueY));  
        }
    }
    
    public void drawTrail() {
        Trail trl;
        if (trails.size() > 100)
            trl = trails.remove(0);
        else {
            trl = new Trail();
            getWorld().addObject(trl, blueX, blueY);
        }
        
        trl.setLocation(blueX, blueY);
        trails.add(trl);
    }
    
    public Point lastPos() {
        return prevPoses.get(prevPoses.size() - 1);
    }
    
    public Point firstPos() {
        return prevPoses.get(0);
    }
}
