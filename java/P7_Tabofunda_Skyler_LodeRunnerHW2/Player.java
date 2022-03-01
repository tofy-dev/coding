import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    int SCREEN_WIDTH = 600;
    int SCREEN_HEIGHT = 400;
    
    int P_WIDTH = new GreenfootImage("player_stand.png").getWidth();
    int P_HEIGHT = new GreenfootImage("player_stand.png").getHeight();
    
    int GRAVITY = 3;
    int SPEED = 2;
    
    int playerX;
    int playerY;
    
    int runFrame = 0;
    int climbFrame = 0;
    int animDelay = 0;
    
    public void Player() {
        setImage("player_stand.png");
    }
    
    public void act() {
        playerX = getX();
        playerY = getY();
        
        // check if falling
        if (onLadder() || wallAtOffset(0, P_HEIGHT/2)) {
            movePlayer();
        } else {
            playerY += GRAVITY;
            setImage("player_stand.png");
        }
        
        fixPosition(); 
        updatePosition();
    }
    
    // ========================================================================================
    // MOVEMENT FUNCTIONS
    // ========================================================================================
    // moves player + controls animations
    private void movePlayer() {
        if (Greenfoot.isKeyDown("left")) {
            if (!wallAtOffset(-P_WIDTH/2, 0) && playerX > P_WIDTH/2) playerX -= SPEED;
            updateAnimation("run", "left");
        } else if (Greenfoot.isKeyDown("right")) {
            if (!wallAtOffset(P_WIDTH/2, 0) && playerX < SCREEN_WIDTH - P_WIDTH/2) playerX += SPEED;
            updateAnimation("run", "right");
        } else if (Greenfoot.isKeyDown("up") && onLadder()) {
            if (!wallAtOffset(0, -P_HEIGHT/2)) playerY -= SPEED;
            updateAnimation("climb", "up");
        } else if (Greenfoot.isKeyDown("down") && onLadder()) {
            if (!wallAtOffset(0, P_HEIGHT/2)) playerY += SPEED;
            updateAnimation("climb", "down");
        }
    }
    // makes sure player didn't fall too far down + is stuck in wall
    private void fixPosition() {
        if (wallAtOffset(0, -P_HEIGHT/2)) { // up
            while (wallAtOffset(0, P_HEIGHT/2-1)) {
                playerY++;
                updatePosition();
            }
        }
        if (wallAtOffset(0, P_HEIGHT/2)) { //down
            while (wallAtOffset(0, P_HEIGHT/2-1)) {
                playerY--;
                updatePosition();
            }
        }
        if (wallAtOffset(-P_WIDTH/2, 0)) { //left
            while (wallAtOffset(-P_WIDTH/2, 0)) {
                playerX++;
                updatePosition();
            }
        }
        if (wallAtOffset(P_WIDTH/2, 0)) { //right
            while (wallAtOffset(P_WIDTH/2, 0)) {
                playerX--;
                updatePosition();
            }
        }
    }
    // sets player position to its X and Y coordinates
    private void updatePosition() {
        setLocation(playerX, playerY);
    }
    // ========================================================================================
    // ANIMATION FUNCTIONS
    // ========================================================================================
    private void updateAnimation(String animType, String dir) {
        if (animDelay <= 0) {
            animDelay = 5;
            GreenfootImage img = new GreenfootImage(10, 10);
            
            if (animType == "run") {
                img = new GreenfootImage("player_run_0" + runFrame + ".png");
                if (dir == "right" && ++runFrame > 3) runFrame = 0;
                else if (dir == "left" && --runFrame < 0) runFrame = 3;
                // flip if going left
                if (dir == "left") {
                    img.mirrorHorizontally();
                }
            } else if (animType == "climb") {
                img = new GreenfootImage("player_climb_ladder.png");
                if (dir == "up" && ++climbFrame > 1) climbFrame = 0;
                else if (dir == "down" && --climbFrame < 0) climbFrame = 1;
                // flip every alt frame
                if (climbFrame == 1) img.mirrorHorizontally();
            } else if (animType == "fall") {
                
            }
            
            setImage(img);
        } else {
            animDelay--;
        }
    }
    
    // ========================================================================================
    // DETECTION FUNCTIONS
    // ========================================================================================
    private boolean wallAtOffset(int dx, int dy) {
        return getOneObjectAtOffset(dx, dy, Brick.class) != null;
    }
    
    private boolean onLadder() {
        return getOneObjectAtOffset(-P_WIDTH/2, P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(P_WIDTH/2, P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(P_WIDTH/2, -P_HEIGHT/2, Ladder.class) != null
        ||     getOneObjectAtOffset(-P_WIDTH/2, -P_HEIGHT/2, Ladder.class) != null;
    }
}
