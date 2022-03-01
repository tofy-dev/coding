import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pedestrian extends Actor
{
    private int SPEED = 3;
    
    boolean movingRight = true;
    Color pedColor = Color.BLUE;
    int health = 5;
    int coolDown = 20;
    
    Font font = new Font(20);
    GreenfootImage ppl1;
    GreenfootImage ppl2;
    GreenfootImage ppl3;

    
    public Pedestrian() {
        ppl1 = new GreenfootImage("ppl1.png");
        ppl2 = new GreenfootImage("ppl2.png");
        ppl3 = new GreenfootImage("ppl3.png");
        setImage("ppl1.png"); 
    }
    
    public void act()
    {
        World world = getWorld();
        String key = Greenfoot.getKey();
        
        coolDown--;
        
        // move pedestrian
        if (movingRight) {
            setLocation(getX() + SPEED, getY());
            if (getX() > world.getWidth() - ppl1.getWidth()/2) {
                movingRight = false;
            }
        } else {
            setLocation(getX() - SPEED, getY());
            if (getX() < ppl1.getWidth()/2) {
                movingRight = true;
            }
        }
        
        // check collisions
        Fruit touchFruit = (Fruit) getOneIntersectingObject(Fruit.class);
        if (touchFruit != null) {
            if (touchFruit.fruitColor == Color.BLUE) {
                if (pedColor != Color.BLUE) {
                    health--;
                    movingRight = !movingRight;
                }
            } else if (touchFruit.fruitColor == Color.GREEN) {
                if (pedColor != Color.GREEN) {
                    health--;
                    movingRight = !movingRight;
                }
            } else if (touchFruit.fruitColor == Color.ORANGE) {
                if (pedColor != Color.ORANGE) {
                    health--;
                    movingRight = !movingRight;
                }
            }
            world.removeObject(touchFruit);
        }

        // change pedestrian color
        if (coolDown <= 0 && key != null && key.equals("space")) {
            if (pedColor == Color.BLUE) {
                pedColor = Color.GREEN;
            } else if (pedColor == Color.GREEN) {
                pedColor = Color.ORANGE;
            } else {
                pedColor = Color.BLUE;
            }
            coolDown = 20;
        }
        
        // if game over
        if (health <= 0) {
            gameOver();
        }
        
        setColoredImage();        
    }
    
    public void setColoredImage() {
        if (pedColor == Color.BLUE) {
            setImage("ppl1.png");
        } else if (pedColor == Color.GREEN) {
            setImage("ppl2.png");
        } else {
            setImage("ppl3.png");
        }
        getImage().setColor(Color.WHITE);
        getImage().setFont(font);
        getImage().drawString("" + health, 15, 40);
    }
    
    public void gameOver() {
        String gameText = "Game Over";
        getWorld().showText(gameText, getWorld().getWidth()/2, getWorld().getHeight()/2);
        Greenfoot.stop();
    }
}
