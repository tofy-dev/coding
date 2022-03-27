import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Menu extends World {
    protected Color BRICK_BLUE = new Color(41, 177, 255);
    protected Color BUTTON_RED = new Color(242, 143, 173);
    protected GreenfootImage bg = getBackground();
    
    public Menu() {
        super(600, 400, 1);
        bg.setColor(Color.BLACK);
        bg.fill();
    }

    public Text drawText(String msg, int size, Color fClr, Color bClr) {
        Text txt = new Text();
        GreenfootImage img = new GreenfootImage(msg, size, fClr, bClr);
        txt.setImage(img);
        addObject(txt, 0, 0);
        return txt;
    }
    
    public boolean isHovering(Text button) {
        MouseInfo mi = Greenfoot.getMouseInfo();
        return (mi != null &&
               mi.getX() >= button.getX()-button.getImage().getWidth()/2 &&
               mi.getX() <= button.getX()+button.getImage().getWidth()/2 &&
               mi.getY() >= button.getY()-button.getImage().getHeight()/2 &&
               mi.getY() <= button.getY()+button.getImage().getHeight()/2
        );
    }
}
