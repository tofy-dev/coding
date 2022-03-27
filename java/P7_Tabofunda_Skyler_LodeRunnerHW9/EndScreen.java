import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EndScreen extends Menu {
    Text winText = new Text();
    
    public EndScreen() {
        super();
        winText = drawText("YOU WON!", 100, BRICK_BLUE, Color.BLACK);
        winText.setLocation(getWidth()/2, getHeight()/2);
    }
}
