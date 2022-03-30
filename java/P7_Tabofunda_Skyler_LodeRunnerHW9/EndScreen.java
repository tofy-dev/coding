import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EndScreen extends Menu {
    public EndScreen(String caption) {
        super();
        Text winText = new Text(caption, 100, BRICK_BLUE, Color.BLACK);
        addObject(winText, getWidth()/2, getHeight()/2);
    }
}
