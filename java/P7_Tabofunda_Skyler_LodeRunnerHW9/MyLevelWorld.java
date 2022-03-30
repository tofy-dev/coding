import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * use this class as a starting point for your Lode Runner. Transfer any extra methods or fields you need from your original class.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyLevelWorld extends LevelWorld {
    private Color BRICK_BLUE = new Color(41, 177, 255);
    
    private Text scorePlat, livesPlat, levelPlat;
    private int level, lives, score, newScore; 
    private Player player;
    
    // static initialization code - runs when the class is loaded, before the main method is called.
    // You can only call static methods and only access static fields.
    // In this case, we need to initialize the margins BEFORE the world is created
    static {
        // set the margins (open space) on the left, right, top and bottom sides of the world
        // The level will be drawn with the given spaces open on each side
        // Set the bottom margin to the height of your HUD
        LevelWorld.setMargins(0, 0, 0, 50);
    }
    
    public MyLevelWorld() {
        this(0, 5, 1); // load level 1, with 5 lives, on level 1
    }
    
    public MyLevelWorld(int score, int lives, int level) {
        super(level);
        
        this.score = score;
        this.lives = lives;
        this.level = level;
        newScore = score;
        
        player = getObjects(Player.class).get(0);
        drawHUD();
    }
    
    public void act() {
        if (player.isGold())
            updateScore();
        if (player.isTouchingEnemy()) {
            if (--lives == 0) {
                Greenfoot.setWorld(new EndScreen("YOU LOST!"));
            } else {
                Greenfoot.setWorld(new MyLevelWorld(score, lives, level));
            }
        } else if (getObjects(Gold.class).size() < 1) {
            try {
                Greenfoot.setWorld(new MyLevelWorld(newScore, lives, ++level));
            } catch(Exception e) {
                Greenfoot.setWorld(new EndScreen("YOU WON!"));
            }
        }
    }
    
    // draw score, lives, level
    public void drawHUD() {
        int platW = getWidth()/3, platH = new GreenfootImage("bricks.png").getWidth();
        scorePlat = new Text("SCORE " + score, 2*platH, BRICK_BLUE, Color.BLACK);
        addObject(scorePlat, scorePlat.getImage().getWidth()/2, getHeight()-scorePlat.getImage().getHeight()/2);
        
        livesPlat = new Text("LIVES " + lives, 2*platH, BRICK_BLUE, Color.BLACK);
        addObject(livesPlat, getWidth()/2, getHeight()-scorePlat.getImage().getHeight()/2);
        
        levelPlat = new Text("LEVEL " + level, 2*platH, BRICK_BLUE, Color.BLACK);
        addObject(levelPlat, getWidth()-levelPlat.getImage().getWidth()/2, getHeight()-scorePlat.getImage().getHeight()/2);
    }
    
    public void updateScore() {
        int platW = getWidth()/3, platH = new GreenfootImage("bricks.png").getWidth();
        newScore += 250;
        scorePlat.setImage(new GreenfootImage("SCORE " + newScore, 2*platH, BRICK_BLUE, Color.BLACK));
        scorePlat.setLocation(scorePlat.getImage().getWidth()/2, getHeight()-scorePlat.getImage().getHeight()/2);
    }
    
    
    @Override
    public void defineClassTypes() {
        // define which classes represent walls, ladders, bars, players, and enemies
        // TODO: REPLACE WITH YOUR CLASSES
        getLoader().setWallClass(Brick.class);
        getLoader().setPlayerClass(Player.class);
        getLoader().setLadderClass(Ladder.class); // you can remove this if you have no ladders in your game
        getLoader().setBarClass(Bar.class); // you can remove this if you have no bars in your game
        getLoader().setEnemyClass(Enemy.class); // you can remove this if you have no enemies in your game
        getLoader().setGoldClass(Gold.class); // you can remove this if you have no gold in your game
        getLoader().setFalseWallClass(FalseBrick.class);
    }
}
