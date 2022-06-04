import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public class Movable extends Actor
{
    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;
    
    int P_WIDTH = new GreenfootImage("player_stand.png").getWidth();
    int P_HEIGHT = new GreenfootImage("player_stand.png").getHeight();
    
    int GRAVITY = 3;
    int SPEED = 2;
    int LADDER_SPEED = new GreenfootImage("ladder.png").getWidth();
    
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
        SCREEN_WIDTH = getWorld().getWidth();
        SCREEN_HEIGHT = getWorld().getHeight();
        
        playerX = getX();
        playerY = getY();
        
        movePlayer();
        updatePosition();
        fixPosition();
    }
    
    // ========================================================================================
    // MOVEMENT FUNCTIONS
    // ========================================================================================
    protected void movePlayer() {
        if (!onWall() && !onBar() && !onLadder()) {
            moveBy(0, GRAVITY);
            setAnimation("fall");
            if (onWall() || onLadder()) setAnimation("idle");
        } else {
            switch(dir) {
            case "left":
                if (onBar()) {
                    playerY = touchingBars().get(0).getY()+8;
                    moveBy(-SPEED*3/2, 0);
                    setAnimation("hang", "left");
                } else {
                    if (onWall() || touchingLadders().size() == 1) moveBy(-SPEED, 0);
                    else if (onLadder()) moveBy(-LADDER_SPEED, 0);
                    setAnimation("run", "left");
                }
                break;
            case "right":
                if (onBar()) {
                    playerY = touchingBars().get(0).getY()+8;
                    moveBy(SPEED*3/2, 0);
                    setAnimation("hang", "right");
                } else {
                    if (onWall() || touchingLadders().size() == 1) moveBy(SPEED, 0);
                    else if (onLadder()) moveBy(LADDER_SPEED, 0);
                    setAnimation("run", "right");
                }
                break;
            case "up":
                if (onLadder() && touchingLadders().size() > 0) {
                    playerX = touchingLadders().get(0).getX();
                    moveBy(0, -SPEED);
                    setAnimation("climb", "up");
                }
                break;
            case "down":
                if (onLadder() && touchingLadders().size() > 0) {
                    playerX = touchingLadders().get(0).getX();
                    moveBy(0, SPEED);
                    setAnimation("climb", "down");
                } else if (onBar()) {
                    moveBy(0, 2*SPEED);
                }
                break;
            }
        }
    }
    
    protected void moveBy(int dx, int dy) {
        playerX += dx;
        playerY += dy;
        updatePosition();
    }
    
    protected void fixPosition() {
        while (wallAtOffset(-P_WIDTH/2, 0) || playerX < P_WIDTH/2) { playerX++; updatePosition(); }                 // left
        while (wallAtOffset(P_WIDTH/2, 0) || playerX > SCREEN_WIDTH - P_WIDTH/2) { playerX--; updatePosition(); }   // right
        while (!onBar() && wallAtOffset(0, -P_HEIGHT/2+1)) { playerY++; updatePosition(); }                         // up
        while (wallAtOffset(0, P_HEIGHT/2-1)) { playerY--; updatePosition(); }                                      // down
    }
    
    protected void updatePosition() {
        setLocation(playerX, playerY);
    }
    // ========================================================================================
    // ANIMATION FUNCTIONS
    // ========================================================================================
    String lastAnim;
    private void setAnimation(String animType, String dir) {
        if (animDelay <= 0 || lastAnim != animType) {
            GreenfootImage img = new GreenfootImage(10, 10);
            switch (animType) {
            case "run":
                img = new GreenfootImage(type + "_run_0" + runFrame + ".png");
                if (dir == "left") img.mirrorHorizontally();
                if (dir == "right" && ++runFrame > 3) runFrame = 0;
                else if (dir == "left" && --runFrame < 0) runFrame = 3;
                animDelay = 5;
                break;
            case "climb":
                img = new GreenfootImage(type + "_climb_ladder.png");
                if (climbFrame == 1) img.mirrorHorizontally();
                if (dir == "up" && ++climbFrame > 1) climbFrame = 0;
                animDelay = 20;
                break;
            case "hang":
                img = new GreenfootImage(type + "_bar_hang_0" + hangFrame + ".png");
                if (dir == "left") img.mirrorHorizontally();
                if (dir == "right" && ++hangFrame > 1) hangFrame = 0;
                else if (dir == "left" && --hangFrame < 0) hangFrame = 1;
                animDelay = 10;  
                break;
            }
            lastAnim = animType;
            setImage(img);
        } else {
            animDelay--;
        }
    }
    
    private void setAnimation(String animType) {
        GreenfootImage img = new GreenfootImage(10, 10);
        
        animDelay = 1;
        if (animType.equals("fall")) {
            img = new GreenfootImage(type + "_bar_hang_00.png");
        } else if (animType.equals("idle")) {
            img = new GreenfootImage(type + "_stand.png");
        }
        
        lastAnim = animType;        
        setImage(img);
    }
    
    // ========================================================================================
    // DETECTION FUNCTIONS
    // ========================================================================================
    public boolean onWall() {
        return wallAtOffset(-P_WIDTH/2, P_HEIGHT/2) || wallAtOffset(P_WIDTH/2, P_HEIGHT/2);
    }
    
    private boolean onLadder() {
        return getOneObjectAtOffset(-P_WIDTH/2, P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(P_WIDTH/2, P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(P_WIDTH/2, -P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(-P_WIDTH/2, -P_HEIGHT/2, Ladder.class) != null;
    }
    
    public boolean onBar() {
        return touchingBars().size() > 0;
    }
    
    
    private boolean wallAtOffset(int dx, int dy) {
        return getOneObjectAtOffset(dx, dy, Brick.class) != null;
    }
    
    private ArrayList<Bar> touchingBars() {
        ArrayList<Bar> ret = new ArrayList<Bar>();
        ret.addAll(getObjectsInRange(P_HEIGHT, Bar.class));
        for (int i = ret.size()-1; i >= 0; i--) {
            Bar b = ret.get(i);
            if (b.getY() < playerY-P_HEIGHT/2 || b.getY() > playerY-P_HEIGHT/4) { // a
                ret.remove(ret.indexOf(b));
            }
        }
        return ret;
    }
    
    private ArrayList<Ladder> touchingLadders() {
        ArrayList<Ladder> ret = new ArrayList<Ladder>();
        ret.addAll(getObjectsInRange(P_HEIGHT, Ladder.class));
        System.out.println(ret.size());
        return ret;
    }
}
