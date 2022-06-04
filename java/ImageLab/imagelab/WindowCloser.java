package imagelab;
import imagelab.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class WindowCloser extends WindowAdapter {

	DisplayImage theFrame;	// the frame to kill

	public WindowCloser(DisplayImage f) {
		theFrame = f;
	}

	public void windowClosing(WindowEvent e) {
		theFrame.setVisible(false);
	}

	public void windowActivated(WindowEvent e) {
		theFrame.setActive();
	}
}