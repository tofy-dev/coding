package filters;
import imagelab.*;
import java.util.*;
import javax.swing.JOptionPane;

/*
 * Skyler Tabofunda
 * P7
 * 5/15
 * 3D effect
 */
public class P7_Tabofunda_Skyler_3D implements ImageFilter {
	ImgProvider filteredImage;

	public void filter (ImgProvider ip) {
		short[][] oR = ip.getRed(); // get a 2D array of all original red values
		short[][] oG = ip.getGreen(); // get a 2D array of all original green values
		short[][] oB = ip.getBlue(); // get a 2D array of all original blue values
		short[][] oA = ip.getAlpha(); // get a 3D array of all original alpha values (transparency)
		
		int rows = oR.length; // height is the number of rows which is the length of the outer array
		int cols = oR[0].length; // width is the number of columns which is the length of each inner array
		
		short[][] nR = new short[rows][cols]; // declare a new array of red values of size height rows x width columns
		short[][] nG = new short[rows][cols]; // declare a new array of green values of size height rows x width columns
		short[][] nB = new short[rows][cols]; // declare a new array of blue values of size height rows x width columns
		short[][] nA = new short[rows][cols]; // declare a new array of alpha values of size height rows x width columns
		
		int off = Integer.parseInt(JOptionPane.showInputDialog("Enter 3D Skew"));
		for (int r = 0; r < rows; r++) { // loop through all the rows
		    for (int c = off; c < cols-off; c++) { // for each row, loop through all the columns
		        nR[r][c-off] = oR[r][c];
		        nG[r][c] = oG[r][c];
		        nB[r][c+off] = oB[r][c];
		        nA[r][c] = oA[r][c];
		    }
		}
		
		filteredImage = new ImgProvider(); // initialize a new ImgProvider object
		filteredImage.setColors(nR, nG, nB, nA); // create the image from the arrays of new color values
		filteredImage.showPix("3D"); // display the image on screen with the given title
	}

	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	public String getMenuLabel() {
		return "3D"; // this will be the name of the filter in the filters menu
	}
}
        
