package imagelab;
import imagelab.*;
import java.awt.*;
import java.awt.image.*;

/** This class is used to display an image */
public class DisPanel extends Panel {

	Image img;
	private static final long serialVersionUID = 6827365345265L;

	/**
	The constructor takes the Image object to display.
	*/
	public DisPanel(Image im) {
		img = im;
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
