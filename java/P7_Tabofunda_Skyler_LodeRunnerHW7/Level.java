import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level extends World
{
    private GreenfootImage brickImg = new GreenfootImage("bricks.png");
    private int BRICK_W = brickImg.getWidth(), BRICK_H = brickImg.getHeight();
    private Color BRICK_BLUE = new Color(41, 177, 255);
    
    private GreenfootImage barImg = new GreenfootImage("bar.png");
    private int BAR_W = barImg.getWidth(), BAR_H = barImg.getHeight();
    
    private GreenfootImage gldImg = new GreenfootImage("gold.png");
    private int GLD_W = gldImg.getWidth(), GLD_H = gldImg.getHeight();
    
    private GreenfootImage scorePlat, livesPlat, levelPlat;
    private Player player;
    
    int score = 0;
    int lives = 5;
    int level = 1;
    
    
    public Level() {    
        super(600, 400, 1);
        initWorld();
        player = new Player();
        addObject(player, player.getImage().getWidth(), getHeight()-2*BRICK_H-player.getImage().getHeight()/2);
    }
    
    public void act() {
        updateScore();
        updateHUD();
    }
    
    public void updateScore() {
        if (player.wasGold()) {
            score += 250;
        }
    }
    
    // draw score, lives, level
    public void updateHUD() {
        int platW = getWidth()/3, platH = BRICK_H;
        
        scorePlat = new GreenfootImage("SCORE " + score, platH, BRICK_BLUE, Color.BLACK);
        getBackground().drawImage(scorePlat, 0, getHeight()-platH);
        
        livesPlat = new GreenfootImage("LIVES " + lives, platH, BRICK_BLUE, Color.BLACK);
        getBackground().drawImage(livesPlat, getWidth()/2-livesPlat.getWidth()/2, getHeight()-platH);
        
        levelPlat = new GreenfootImage("LEVEL " + level, platH, BRICK_BLUE, Color.BLACK);
        getBackground().drawImage(levelPlat, getWidth()-levelPlat.getWidth(), getHeight()-platH);
    }
    
    
    // WORLD SETUP FUNCTIONS ===========================================================================
    public void initWorld() {
        // draw black background
        GreenfootImage worldBg = new GreenfootImage(getWidth(), getHeight());
        worldBg.setColor(Color.BLACK);
        worldBg.fillRect(0, 0, worldBg.getWidth(), worldBg.getHeight());
        setBackground(worldBg);
        
        // draw walls
        drawBrickRow(0, BRICK_H*2, 9);
        drawBrickRow(BRICK_W*9, 0, 10);
        drawBrickRow(BRICK_W*19, BRICK_H*2, 5);
        drawBrickRow(0, getHeight()-6*BRICK_H, 12);
        drawBrickRow(0, getHeight()-2*BRICK_H);
        drawBrickRow(BRICK_W*18, 9*BRICK_H, 7);
        drawBrickRow(BRICK_W*15, getHeight()-5*BRICK_H, 10);
        drawBrickRow(BRICK_W*9, 5*BRICK_H, 10);
        
        // draw ladders
        drawLadder(BRICK_W*13, getHeight()-3*BRICK_H, 3);
        drawLadder(BRICK_W*9, getHeight()-7*BRICK_H, 6);
        drawLadder(getWidth(), getHeight()-11*BRICK_H, 6);

        // draw bars
        drawBarRow(BRICK_W*9, 6*BRICK_H, 10);
        drawBarRow(BRICK_W*9, 1*BRICK_H, 10);
        
        // draw gold
        drawGold(6*BRICK_W, getHeight()-2*BRICK_H);
        drawGold(21*BRICK_W, 9*BRICK_H);
    }
    
    // recieves leftmost brick's x & y position
    public void drawBrickRow(int sX, int sY, int num) {        
        for (int i = 0; i < num; i++) {
            int x = sX + BRICK_W*i + BRICK_W/2;
            int y = sY + BRICK_H/2;
            Brick brick = new Brick();
            addObject(brick, x, y);
        }
    }
    // draw full row
    public void drawBrickRow(int sX, int sY) {
        for (int i = 0; i < getWidth()/BRICK_W; i++) {
            int x = sX + BRICK_W*i + BRICK_W/2;
            int y = sY + BRICK_H/2;
            Brick brick = new Brick();
            addObject(brick, x, y);
        }
    }
    // draw ladder (starts at top-left of bottom ladder)
    public void drawLadder(int sX, int sY, int num) {
        for (int i = num; i >= 0; i--) {
            int x = sX - BRICK_W/2;
            int y = sY - BRICK_H*i + BRICK_H/2;
            Ladder ladder = new Ladder();
            addObject(ladder, x, y);
        }
    }
    // draw bars (starts at top-left of first bar)
    public void drawBarRow(int sX, int sY, int num) {
        for (var i = 0; i < num; i++) {
            int x = sX + BAR_W*i + BAR_W/2;
            int y = sY + BAR_H/2;
            Bar bar = new Bar();
            addObject(bar, x, y);
        }
    }
    // draw gold (starts at bottom-left of gold - shifts automatically)
    public void drawGold(int sX, int sY) {
        Gold gld = new Gold();
        addObject(gld, sX+BRICK_W/2, sY-GLD_H/2);
    }
}
