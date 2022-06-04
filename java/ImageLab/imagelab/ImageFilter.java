package imagelab;
import imagelab.*;

/**
	The ImageFilter interface is implemented by the respective image filters.
*/
public interface ImageFilter {

	public void filter(ImgProvider ip);		// does the filtering
	public ImgProvider getImgProvider();	// returns the resulting ImgProvider
	public String getMenuLabel();			// used to label filter's menu
}

