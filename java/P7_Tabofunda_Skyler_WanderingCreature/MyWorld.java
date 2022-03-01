import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    int SCREEN_WIDTH = getWidth();
    int SCREEN_HEIGHT = getHeight();
    
    int wallH = Wall.HEIGHT;
    int wallW = Wall.WIDTH;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        makeHorWalls();
        makeVerWalls();
        makeMidWalls();
        spawnCreatures();
        spawnCoins();
    }
    
    private void spawnCreatures() {
        for (int num = 0; num < 3; num++) {
            Creature cret = new Creature();
            int cretX = Greenfoot.getRandomNumber(SCREEN_WIDTH);
            int cretY = Greenfoot.getRandomNumber(SCREEN_HEIGHT);
            
            addObject(cret, cretX, cretY);
            do {
                cretX = Greenfoot.getRandomNumber(SCREEN_WIDTH);
                cretY = Greenfoot.getRandomNumber(SCREEN_HEIGHT);
                cret.setLocation(cretX, cretY);
            } while (cret.isColliding());
        }
    }
    
    public void spawnCoins() {
        for (int num = 0; num < 3; num++) {
            Coin coin = new Coin();
            int coinX = Greenfoot.getRandomNumber(SCREEN_WIDTH);
            int coinY = Greenfoot.getRandomNumber(SCREEN_HEIGHT);
        
            addObject(coin, coinX, coinY);
            do {
                coinX = Greenfoot.getRandomNumber(SCREEN_WIDTH);
                coinY = Greenfoot.getRandomNumber(SCREEN_HEIGHT);
                coin.setLocation(coinX, coinY);
            } while (coin.isColliding());
        }
    }
    
    private void makeHorWalls() {
        GreenfootImage wallImg = new GreenfootImage("wallTile.png");
        wallImg.scale(Wall.HEIGHT, Wall.WIDTH);
        
        for (int x = wallW/2; x < SCREEN_WIDTH; x += wallW) {
            Wall topWall = new Wall();
            addObject(topWall, x, wallH/2);
            
            Wall botWall = new Wall();
            addObject(botWall, x, SCREEN_HEIGHT - wallH/2);
        }
    }
    
    private void makeVerWalls() {
        GreenfootImage wallImg = new GreenfootImage("wallTile.png");
        wallImg.scale(Wall.HEIGHT, Wall.WIDTH);
        
        for (int y = wallH/2; y < SCREEN_HEIGHT; y += wallH) {
            Wall topWall = new Wall();
            addObject(topWall, wallW/2, y);
            
            Wall botWall = new Wall();
            addObject(botWall, SCREEN_WIDTH-wallW/2, y);
        }
    }
    
    private void makeMidWalls() {
        GreenfootImage wallImg = new GreenfootImage("wallTile.png");
        wallImg.scale(Wall.HEIGHT, Wall.WIDTH);
    
        for (int x = SCREEN_WIDTH/2 - 2*wallW; x <= SCREEN_WIDTH/2 + 2*wallW; x += wallW) {
            for (int y = SCREEN_HEIGHT/2 - 2*wallH; y <= SCREEN_HEIGHT/2 + 2*wallH; y += wallH) {
                Wall cenWall = new Wall();
                addObject(cenWall, x, y);
            }
        }
    }
}
