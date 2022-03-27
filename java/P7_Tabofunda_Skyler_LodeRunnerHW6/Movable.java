import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public class Movable extends Actor
{
    int SCREEN_WIDTH = 600;
    int SCREEN_HEIGHT = 400;
    
    int P_WIDTH = new GreenfootImage("player_stand.png").getWidth();
    int P_HEIGHT = new GreenfootImage("player_stand.png").getHeight();
    
    int GRAVITY = 3;
    int SPEED = 2;
    
    String type;
    String dir;
    int playerX;
    int playerY;
    
    // refactor to only use 1 frame
    int runFrame = 0;
    int climbFrame = 0;
    int hangFrame = 0;
    int animDelay = 0;
    
    public void act() {
        playerX = getX();
        playerY = getY();
        
        movePlayer();
        fixPosition(); 
        updatePosition();
    }
    
    // ========================================================================================
    // MOVEMENT FUNCTIONS
    // ========================================================================================
    protected void movePlayer() {
        if (!onWall() && !onBar() && !onLadder()) {
            playerY += GRAVITY;
            updateAnimation("fall", "down");
        } else if (dir == "left") {
            if (onBar()) {
                playerY = touchingBars().get(0).getY()+8;
                playerX -= SPEED/2;
                updateAnimation("hang", "left");
            } else {
                playerX -= SPEED;
                updateAnimation("run", "left");
            }
        } else if (dir == "right") {
            if (onBar()) {
                playerY = touchingBars().get(0).getY()+8;
                playerX += SPEED/2;
                updateAnimation("hang", "right");
            } else {
                playerX += SPEED;
                updateAnimation("run", "right");
            }
        } else if (dir == "up") {
            if (onLadder()) {
                playerY -= SPEED;
                updateAnimation("climb", "up");
            }
        } else if (dir == "down") {
            if (onLadder()) {
                playerY += SPEED;
                updateAnimation("climb", "down");
            } else if (onBar()) {
                playerY += 2*GRAVITY;
                updateAnimation("fall", "down");
            }
        }
    }
    // makes sure player didn't fall too far down + is stuck in wall
    protected void fixPosition() {
        // up
        while (!onBar() && wallAtOffset(0, -P_HEIGHT/2+1)) {
            playerY++;
            updatePosition();
        }
        //down
        while (wallAtOffset(0, P_HEIGHT/2-1)) {
            playerY--;
            updatePosition();
        }
        //left
        while (wallAtOffset(-P_WIDTH/2, 0) || playerX < P_WIDTH/2) {
            playerX++;
            updatePosition();
        }
        // right
        while (wallAtOffset(P_WIDTH/2, 0) || playerX > SCREEN_WIDTH - P_WIDTH/2) {
            playerX--;
            updatePosition();
        }
    }
    // sets player position to its X and Y coordinates
    protected void updatePosition() {
        setLocation(playerX, playerY);
    }
    // ========================================================================================
    // ANIMATION FUNCTIONS
    // ========================================================================================
    private void updateAnimation(String animType, String dir) {
        if (animDelay <= 0) {
            GreenfootImage img = new GreenfootImage(10, 10);
        
            if (animType == "run") {
                animDelay = 5;
                img = new GreenfootImage(type + "_run_0" + runFrame + ".png");
                if (dir == "left") img.mirrorHorizontally();
                // check if reset
                if (dir == "right" && ++runFrame > 3) runFrame = 0;
                else if (dir == "left" && --runFrame < 0) runFrame = 3;
            } else if (animType == "climb") {
                animDelay = 20;
                img = new GreenfootImage(type + "_climb_ladder.png");
                if (climbFrame == 1) img.mirrorHorizontally();
                // check if reset
                if (dir == "up" && ++climbFrame > 1) climbFrame = 0;
                else if (dir == "down" && --climbFrame < 0) climbFrame = 1;
            } else if (animType == "hang") {
                animDelay = 10;
                img = new GreenfootImage(type + "_bar_hang_0" + hangFrame + ".png");
                if (dir == "left") img.mirrorHorizontally();
                // check if reset
                if (dir == "right" && ++hangFrame > 1) hangFrame = 0;
                else if (dir == "left" && --hangFrame < 0) hangFrame = 1;
            } else if (animType == "fall") {
                img = new GreenfootImage(type + "_stand.png");
            }
            
            setImage(img);
        } else {
            animDelay--;
        }
    }
    
    // ========================================================================================
    // DETECTION FUNCTIONS
    // ========================================================================================
    private boolean onWall() {
        return wallAtOffset(-P_WIDTH/2, P_HEIGHT/2) || wallAtOffset(P_WIDTH/2, P_HEIGHT/2);
    }
    
    private boolean onLadder() {
        return getOneObjectAtOffset(-P_WIDTH/2, P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(P_WIDTH/2, P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(P_WIDTH/2, -P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(-P_WIDTH/2, -P_HEIGHT/2, Ladder.class) != null;
    }
    
    private boolean onBar() {
        return touchingBars().size() > 0;
    }
    
    private boolean wallAtOffset(int dx, int dy) {
        return getOneObjectAtOffset(dx, dy, Brick.class) != null;
    }
    
    private ArrayList<Bar> touchingBars() {
        List<Bar> rangeBars = getObjectsInRange(P_HEIGHT, Bar.class);
        ArrayList<Bar> ret = new ArrayList();
        for (int b = 0; b < rangeBars.size(); b++) {
            Bar bar = rangeBars.get(b);
            GreenfootImage img = bar.getImage();
            
            if (bar.getY() >= playerY-P_HEIGHT && bar.getY() <= playerY
            && !(playerX-P_WIDTH/2 > bar.getX()+img.getWidth()/2 ||
                playerX+P_WIDTH/2 < bar.getX()-img.getWidth()/2)) {
                ret.add(bar); 
            }  
        }
        return ret;
    }
}
