package imagelab;
import imagelab.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
ImgProvider is responsible for one image.
@author Aaron Gordon
*/
public class ImgProvider extends Component {

	boolean 		isLoaded;	//does this imgProvider hold an image?
	int 			pixheight;
	int 			pixwidth;
	Image 			img;		//the raw image
	short [][]		red, green, blue, alpha;	//holds four components of the image
	PixelGrabber  	grab;
	int [] 			pix;		//holds the pixels from the image
	protected int 	xinc= 0;	//x increment for trimming
	protected int 	yinc = 0;	//y increment for trimming
	protected String imgName;	//holds this image's name
	protected ImageLab lab;
	protected static int count = 0;
	protected int id;			//used to distiguish one ImgProvider from another
	private static final long serialVersionUID = 46243992627639L;

	public ImgProvider() {
		this("");
	}

	public ImgProvider(String name) {
		imgName = name;
		isLoaded = false;
		id = ++count;
	}

	public int getid() {
		return id;
	}

	/**
	SetBWImage takes a 2D array of short and creates
	an Image object to hold the passed informatin as
	a black and white image.
	@param img The 2D array of short
	*/
	public void setBWImage(short [][] img) {

		int spot = 0;	// index into pix
		int tmp;
		int alpha = 255;
		pixheight = img.length;
		pixwidth  = img[0].length;
		pix = new int[pixheight * pixwidth];

		for (int r = 0; r<pixheight; r++) {
			for (int c=0; c< pixwidth; c++) {
				tmp = alpha;
				tmp = tmp << 8;
				tmp += img[r][c];
				tmp = tmp << 8;
				tmp += img[r][c];
				tmp = tmp << 8;
				tmp += img[r][c];
				pix[spot++] = tmp;
			} // for c
		} // for r
		separateColors();
		isLoaded = true;
	}

	/** returns image in black and white */
	public short[][] getBWImage() {

		// read in image into pix[]
		if (!isLoaded)
			readinImage();
		toBW();	// convert it to black and white

		// copy from int []pix to short [][]b and filter outliers
		short [][] b = new short[pixheight][pixwidth];
		int spot = 0;
		short tmp;

		for (int r = 0; r<pixheight; r++) {
			for (int c=0; c<pixwidth; c++) {
				tmp = (short) (pix[spot++] & 255);
				b[r][c] = tmp;
			} // for c
		} // for r

		//showImage(b,"B & W with compressed range of values");
		return b;
	}

	/** Reads in image (if no image yet) and trims it */
	public void readinImage() {

		if (ImageLab.debug) {
			String []fmts = ImageIO.getReaderFormatNames();
			for (int i = 0; i < fmts.length; i++)
				System.out.println("Format names " + fmts[i]);
		}

		img = getToolkit().getImage(imgName);
		if (img == null)
			System.out.println("\n\n**ImgProvider: getImage: img is null!!! ***\n\n");

		int width 	= img.getWidth(null)  - xinc;
		int height	= img.getHeight(null) - yinc;
		grab 	  	= new PixelGrabber(img,xinc, yinc, width, height, true); //forceRGB=true

		try {
			grab.grabPixels();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.err.println("ImgProvider:getBWImage: pixel grabbing failed!!");
			return;
		}
		pix = (int [])
		grab.getPixels();
		pixwidth = img.getWidth(null) - xinc;
		pixheight = img.getHeight(null) - yinc;
		isLoaded = true;
		separateColors();
		System.out.print(ImageLab.debug ? "pix width and height are: " + pixwidth + ",  " + pixheight + "\n" : "");
		try {
			Thread.sleep(300);
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
	}//readinImage

	public void save(File thefile) throws IOException {

		BufferedImage bi = new BufferedImage(pixwidth, pixheight, BufferedImage.TYPE_4BYTE_ABGR);
		bi.setRGB(0, 0, pixwidth, pixheight, pix, 0, pixwidth);
		String fileStr = thefile.toString();
		int extensionIndex = fileStr.lastIndexOf(".");
		String format;
		String noExtensionFileName = fileStr;
		if (extensionIndex == -1) {
		    format = "png";
		} else {
		    format = fileStr.substring(extensionIndex + 1);
		    noExtensionFileName = noExtensionFileName.substring(0, extensionIndex);
		}
		format = format.toLowerCase();
		if (!format.equals("png") && !format.equals("gif") && !format.equals("jpeg")) {
		    if (format.equals("jpg")) {
		        format = "jpeg";
		    } else {
		        format = "png";
		    }
		}
		thefile = new File(noExtensionFileName + "." + format);
		
		if (!ImageIO.write(bi, format, thefile))
			System.err.println("Couldn't write file - save failed");
	}

	/** cuts out x columns and y rows from NW */
	public void setTrim(int x, int y) {
		xinc = x;
		yinc = y;
	}

	/** convert from color to gray scale */
	private void toBW() {
		int  alpha, red, green, blue, black;

		for (int i = 0; i < pix.length; i++) {
			int num = pix[i];
			blue = num & 255;
			num = num >> 8;
			green = num & 255;
			num = num >> 8;
			red = num & 255;
			num = num >> 8;
			alpha = num & 255;
			black = (red + green + blue) / 3;
			num = alpha;
			num = (num << 8) + black;
			num = (num << 8) + black;
			num = (num << 8) + black;
			pix[i] = num;
		}
	}

	/** for compatibility with older versions */
	public void showImage(String name) {
		showPix(name);
	}

	/** do the actual display in a window */
	public void showPix(String name) {
		System.out.print(ImageLab.debug ? "ImgProvider:showPix:  before readIn\n" : "");
		if (!isLoaded)
			readinImage();
		System.out.print(ImageLab.debug ? "ImgProvider:showPix:  after readIn\n" : "");
		img = getToolkit().createImage(
				new MemoryImageSource(pixwidth, pixheight, pix, 0, pixwidth));
		System.out.print(ImageLab.debug ? "ImgProvider:showPix:  before displayImage\n" : "");
		DisplayImage dis = new DisplayImage(this,name);
		System.out.print(ImageLab.debug ? "ImgProvider:showPix:  after displayImage\n" : "");
		try {
			Thread.sleep(300);  // make sure image has time to display
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	/**	separateColors is responsible for pulling the image apart
	 * into it's RGB and alpha components */
	public void separateColors() {

		if (pix == null)
			return;

		alpha	= new short[pixheight][pixwidth];
		red		= new short[pixheight][pixwidth];
		green	= new short[pixheight][pixwidth];
		blue	= new short[pixheight][pixwidth];
		int spot = 0;	// index into pix

		for (int r = 0;  r < pixheight; r++) {
			for (int c = 0; c < pixwidth; c++) {
				int num = pix[spot++];
				blue[r][c]	 = (short)(num & 255);
				num = num >> 8;
				green[r][c]	 = (short)(num & 255);
				num = num >> 8;
				red[r][c]	 = (short)(num & 255);
				num = num >> 8;
				alpha[r][c]	 = (short)(num & 255);
			} // for c
		} // for r
	} // separateColors

	public void setColors(short[][]rd, short[][]g, short[][]b, short[][]al) {

		pixheight = rd.length;
		pixwidth  = rd[0].length;
		red		= new short[pixheight][pixwidth];
		green	= new short[pixheight][pixwidth];
		blue	= new short[pixheight][pixwidth];
		alpha	= new short[pixheight][pixwidth];
		pix		= new int[pixwidth * pixheight];
		int tmp;
		int spot = 0;
		for (int r = 0;  r < pixheight; r++) {
			for (int c = 0; c < pixwidth; c++) {
				red[r][c]	= rd[r][c];
				green[r][c]	= g[r][c];
				blue[r][c]	= b[r][c];
				alpha[r][c]	= al[r][c];
				tmp = alpha[r][c];
				tmp = tmp << 8;
				tmp += red[r][c];
				tmp = tmp << 8;
				tmp += green[r][c];
				tmp = tmp << 8;
				tmp += blue[r][c];
				pix[spot++] = tmp;
			} // for c
		} // for r
		isLoaded = true;
	}//setColors

	public short[][] getRed() {
		return red;
	}

	public short[][] getGreen() {
		return green;
	}

	public short[][] getBlue() {
		return blue;
	}

	public short[][] getAlpha() {
		int nrows = alpha.length;
		int ncols = alpha[0].length;
		short [][] al = new short[nrows][ncols];
		for (int r = 0; r < nrows; r++)
			for (int c = 0; c < ncols; c++)
				al[r][c] = alpha[r][c];
		return al;
	}

	public Image getImage() {
		return img;
	}

	/** called when the image represented by this ImgProvider is the
	    one currently being worked on */
	public void setActive() {
		if (lab != null)
			lab.setActive(this);
		else
			System.err.println("*** error ** ImgProvider:setActive - no lab");
	}

	public void setLab(ImageLab iml) {
		lab = iml;
	}

	public ImageLab getLab() {
		return lab;
	}

}