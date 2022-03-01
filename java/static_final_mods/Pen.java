import java.awt.Point;

/** Access Modifiers (this demo focuses only on public and private)
 * since we are not making other packages or subclasses yet:
 * public: accessible from any other class
 * private: only accessible in this class
 * protected: accessible in this class, subclasses and classes in same package
 * package private (no modifier): Accessible in this class and classes in same package
 */ 
public class Pen {
    /** defaultColor is a static field, so it is a property of the class
     * rather than a property of each Pen object.
     * It is also public, which means it can be accessed directly
     * from other classes using dot notation:
     * String dc = Pen.defaultColor; // access defaultColor property of Pen
     * Pen.defaultColor = "Blue"; // change defaultColor property of Pen class
     */
    public static String defaultColor = "Black";
    
    /** WIDTH is a final instance variable. The final keyword means the value
     * cannot change once it has been initialized. Final variables (constants)
     * are named in ALL_CAPS with words separated by underscores.
     * WIDTH is also an instance variable because it is not declared with the
     * static keyword. This means each instance of Pen (i.e. each Pen object) can
     * have a different value.
     */
    public final int WIDTH;
    
    /** DEFAULT_WIDTH is:
     * public: it can be accessed from other classes using the dot notation
     * static: it belongs to the Pen class rather than to each instance of Pen.
     *         Thus, it can be accessed by saying Pen.DEFAULT_WIDTH
     * final: the value cannot be changed once it is initialized.
     *        Since it is initialized in the same line it is declared,
     *        if you wrote Pen.DEFAULT_WIDTH = 3 you would get a compiler error.
     * You can access DEFAULT_WIDTH because it is public.
     * Since it is an instance variable (not static), each instance of
     * the pen has its own value for WIDTH
     */
    public static final int DEFAULT_WIDTH = 1;

    /** position is an instance variable (it is NOT declared with the static keyword),
     * so each instance of Pen has its own position value.
     * Since position is public, it can be directly accessed from other classes,
     * using the dot notation.
     * Since position is NOT final, it can also be changed any time.
     * Example:
     * Pen myPen = new Pen(); // create a specific instance of Pen
     * Position penPos = myPen.position; // access the position of myPen
     * myPen.position = new Point(5, 8); // change the position of myPen
     */
    public Point position;
    
    /** color is an instance variable (it is NOT declared with the static keyword),
     * so each instance of Pen has its own color value.
     * Since color is private, it cannot be accessed from other classes
     */
    private String color;
    
    /** default constructor */
    public Pen() {
        color = defaultColor;
        WIDTH = DEFAULT_WIDTH;
        position = new Point();
    }
    
    /** constructor that specifies the width */
    public Pen(int width) {
        WIDTH = width;
        color = defaultColor;
        position = new Point();
    }
    
    /** constructor that specifies the width and color
     * the this keyword refers to the instance of Pen that is being
     * initialized in the constructor. Since the local variable color
     * (declared as a parameter) is the same name as the instance
     * variable color, you need to use the this keyword to access
     * the color instance variable.
     */
    public Pen(int width, String color) {
        WIDTH = width;
        this.color = color;
        position = new Point();
    }
    
    /** printDefaults is a static method, so it a method that can be called on
     * the class. You don't need to create an instance of the class to call
     * a static method.
     * Since it is public, it can be called from other classes using the dot
     * notation to call the method on the Pen class:
     * Pen.printDefaults()
     */
    public static void printDefaults() {
        System.out.println("defaultColor is " + defaultColor + " and DEFAULT_WIDTH is " + DEFAULT_WIDTH);
    }
     
    /** getter for color
     * getColor() is an instance method because it doesn't say static, so it
     * must be called on a specific instance of a Pen. It is public, so it
     * can be called from other classes using dot notation to call the method
     * on an instance of a Pen:
     * Pen thePen = new Pen(); // instantiate a Pen object
     * String c = thePen.getColor(); // call getColor() on thePen
     */
    public String getColor() {
        return color;
    }
    
    /** setter for color */
    public void setColor(String color) {
        this.color = color;
    }
    
    /** an instance method that changes the location
     * If you look at the Point API, you will see that
     * it has public instance variables x and y, so you
     * can access those values directly and set them to
     * new values.
     */
    public void moveTo(int x, int y) {
        position.x = x;
        position.y = y;
    }
    
    /** If you write a toString() method for a class, then when you print
     * an instance of your class, it will print result of calling
     * toString() on the object you printed.
     */
    public String toString() {
        return "A " + color + " Pen of width " + WIDTH + " located at (" + position.x + ", " + position.y + ")";
    }
}
