import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class StartScreen extends Menu {
    Text title = new Text();
    Text button = new Text();
    
    int DELAY_AMT = 50;
    int frame = 0;
    
    public StartScreen() { 
        super();
        title = drawText("LODE RUNNER", 100, BRICK_BLUE, Color.BLACK); 
        title.setLocation(getWidth()/2, title.getImage().getHeight()/2);
        
        button = drawText("CLICK TO PLAY", 70, BUTTON_RED, Color.BLACK);
        button.setLocation(getWidth()/2, getHeight()/2);
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
        if (isHovering(button) && mi.getButton() == 1) {
            MyLevelWorld lvl = new MyLevelWorld();
            Greenfoot.setWorld(lvl);
        }
    }
    
    public void updateButton(boolean isRed) {
        Color fClr = isRed ? BUTTON_RED : Color.BLACK;

        if (isHovering(button)) {
            button.setImage(new GreenfootImage("CLICK TO PLAY", 80, Color.BLACK, BUTTON_RED));
        } else {
            button.setImage(new GreenfootImage("CLICK TO PLAY", 70, fClr, Color.BLACK));
        }
    }
}
