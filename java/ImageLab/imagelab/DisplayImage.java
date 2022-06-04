package imagelab;
import imagelab.*;
import java.awt.*;
import java.awt.image.*;

public class DisplayImage extends Frame {

    Image img;
    private static int xspot = 50;      //location of subsequent windows changes
    private static int yspot = 50;
    private DisPanel pane;
    protected ImgProvider improvider;   //ImgProvider that this object is activated by
    private static final long serialVersionUID = 9274738472821L;

    /**
    The constructor takes the Image object to display and a String to use as the title
    of the window.
    */
    public DisplayImage(ImgProvider imp, String title) {

        setTitle(title);

        /* alpha of background set to 0 so transparancy of displayed image will show
         * through to monitors background (thanks Jesse) */
        // Removed because it throws a FrameNotDecorated error
        // setBackground(new Color(100, 100, 100, 0));

        int width;
        int height;
        improvider  = imp;
        img         = imp.getImage();
        pane        = new DisPanel(img);

        add(pane,"Center");

        while (-1 == (width = img.getWidth(null))) {
            System.out.print(ImageLab.debug ? "DisplayImage:constructor - first while" : "");
            try {
                Thread.sleep(50);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        while (-1 == (height = img.getHeight(null))) {
            System.out.print(ImageLab.debug ? "DisplayImage:constructor - second while" : "");
            try {
                Thread.sleep(50);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        xspot = (xspot < 700) ? xspot + 50 : 50;
        yspot = (yspot < 700) ? yspot + 40 : 50;

        setBounds(xspot, yspot, width, height + 20); // +20 to allow room for title bar
        WindowCloser wc = new WindowCloser(this);
        this.addWindowListener(wc);

        System.out.print(ImageLab.debug ? "DisplayImage:constructor - calling show\n" : "");
        setVisible(true); // show the image
        
        // correct width and height according to insets. This must be done after making the window
        // visible or insets will be 0 because they are not deteremined until the window is visible
        setBounds(xspot, yspot, width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
        
        System.out.print(ImageLab.debug ? "DisplayImage:constructor - back from calling show\n" : "");
    }
    
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible && improvider != null) {
            ImageLab lab = improvider.getLab();
            if (lab != null) {
                lab.setPreviousImageActive();
                lab.removeImageProvider(improvider);
            }
        }
    }

    /** Notification that this window has become active */
    public void setActive() {
        improvider.setActive();
    }
}