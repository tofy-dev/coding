import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Menu extends World {
    private Color BRICK_BLUE = new Color(41, 177, 255);
    private Color BUTTON_RED = new Color(242, 143, 173);
    GreenfootImage bg = getBackground();
    
    Text title = new Text();
    Text button = new Text();
    
    int DELAY_AMT = 50;
    int frame = 0;
    
    public Menu() {
        super(600, 400, 1); 
        bg.setColor(Color.BLACK);
        bg.fill();
        drawTitleText();
        drawPlayButton();
    }
    
    public void act() {
        MouseInfo mi = Greenfoot.getMouseInfo();
        
        if (frame++ == 2*DELAY_AMT) {
            updateButton(true);
            frame = 0;
        } else if (frame == DELAY_AMT) {
            updateButton(false);
        }
        
        // check if game should begin
        if (isHovering() && mi.getButton() == 1) {
            Level lvl = new Level();
            Greenfoot.setWorld(lvl);
        }
    }
    
    public void updateButton(boolean isRed) {
        Color fClr = isRed ? BUTTON_RED : Color.BLACK;

        if (isHovering()) {
            button.setImage(new GreenfootImage("CLICK TO PLAY", 80, Color.BLACK, BUTTON_RED));
        } else {
            button.setImage(new GreenfootImage("CLICK TO PLAY", 70, fClr, Color.BLACK));
        }
    }
    
    public boolean isHovering() {
        MouseInfo mi = Greenfoot.getMouseInfo();
        return (mi != null &&
               mi.getX() >= button.getX()-button.getImage().getWidth()/2 &&
               mi.getX() <= button.getX()+button.getImage().getWidth()/2 &&
               mi.getY() >= button.getY()-button.getImage().getHeight()/2 &&
               mi.getY() <= button.getY()+button.getImage().getHeight()/2
        );
    }
    
    // INIT FUNCTIONS ==========================================================f===============
    public void drawTitleText() {
        GreenfootImage img = new GreenfootImage("LODE RUNNER", 100, BRICK_BLUE, Color.BLACK);
        title.setImage(img);
        addObject(title, getWidth()/2, img.getHeight()/2);
    }
    
    public void drawPlayButton() {
        GreenfootImage img = new GreenfootImage("CLICK TO PLAY", 70, BUTTON_RED, Color.BLACK);
        button.setImage(img);
        addObject(button, getWidth()/2, getHeight()/2);
    }
}
