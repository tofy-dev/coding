import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    GreenfootImage brickImg = new GreenfootImage("bricks.png");
    int brickW = brickImg.getWidth();
    int brickH = brickImg.getHeight();
    
    public MyWorld()
    {    
        super(600, 400, 1);
        initWorld();
    }
    
    public void initWorld() {
        // draw black background
        GreenfootImage worldBg = new GreenfootImage(getWidth(), getHeight());
        worldBg.setColor(Color.BLACK);
        worldBg.fillRect(0, 0, worldBg.getWidth(), worldBg.getHeight());
        setBackground(worldBg);
        
        // draw walls
        drawBrickRow(0, brickH*3, 8);
        drawBrickRow(0, getHeight()-5*brickH, 12);
        drawBrickRow(0, getHeight()-brickH);
        
        // draw ladders
        drawLadder(brickW*13, getHeight()-2*brickH, 3);
        drawLadder(brickW*9, getHeight()-6*brickH, 10);
    }
    
    // recieves leftmost brick's x & y position
    public void drawBrickRow(int sX, int sY, int num) {        
        for (int i = 0; i < num; i++) {
            int x = sX + brickW*i + brickW/2;
            int y = sY + brickH/2;
            Brick brick = new Brick();
            addObject(brick, x, y);
        }
    }
    // draw full row
    public void drawBrickRow(int sX, int sY) {
        for (int i = 0; i < getWidth()/brickW; i++) {
            int x = sX + brickW*i + brickW/2;
            int y = sY + brickH/2;
            Brick brick = new Brick();
            addObject(brick, x, y);
        }
    }
    // draw ladder (starts at top-left of bottom ladder)
    public void drawLadder(int sX, int sY, int num) {
        for (int i = num; i >= 0; i--) {
            int x = sX - brickW/2;
            int y = sY - brickH*i + brickH/2;
            Ladder ladder = new Ladder();
            addObject(ladder, x, y);
        }
    }
}
