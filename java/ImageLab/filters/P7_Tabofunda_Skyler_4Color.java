package filters;
import imagelab.*;
import java.util.*;
import javax.swing.JOptionPane;

/*
 * Skyler Tabofunda
 * P7
 * 5/15
 * Copies image four times, with red/yellow/blue/green dominant colors
 */
public class P7_Tabofunda_Skyler_4Color implements ImageFilter {
	ImgProvider filteredImage;

	public void filter (ImgProvider ip) {
        	short[][] im = ip.getBWImage();
		short[][] oA = ip.getAlpha(); // get a 3D array of all original alpha values (transparency)
		
		int rows = im.length; // height is the number of rows which is the length of the outer array
		int cols = im[0].length; // width is the number of columns which is the length of each inner array
		
		short[][] nR = new short[rows*2][cols*2]; // declare a new array of red values of size height rows x width columns
		short[][] nG = new short[rows*2][cols*2]; // declare a new array of green values of size height rows x width columns
		short[][] nB = new short[rows*2][cols*2]; // declare a new array of blue values of size height rows x width columns
		short[][] nA = new short[rows*2][cols*2]; // declare a new array of alpha values of size height rows x width columns
		
		for (int r = 0; r < rows; r++) { // loop through all the rows
		    for (int c = 0; c < cols; c++) { // for each row, loop through all the columns
		        nR[r][c] = im[r][c];
		        nG[r][c] = 0;
		        nB[r][c] = 0;
		        nA[r][c] = oA[r][c];
		        
        		nR[r+rows][c] = 0;
		        nG[r+rows][c] = im[r][c];
		        nB[r+rows][c] = 0;
		        nA[r+rows][c] = oA[r][c];
		        
		        nR[r][c+cols] = 0;
		        nG[r][c+cols] = 0;
		        nB[r][c+cols] = im[r][c];
		        nA[r][c+cols] = oA[r][c];

        		nR[r+rows][c+cols] = im[r][c];
		        nG[r+rows][c+cols] = im[r][c];
		        nB[r+rows][c+cols] = 0;
		        nA[r+rows][c+cols] = oA[r][c];
		    }
		}
		
		filteredImage = new ImgProvider(); // initialize a new ImgProvider object
		filteredImage.setColors(nR, nG, nB, nA); // create the image from the arrays of new color values
		filteredImage.showPix("4 Color"); // display the image on screen with the given title
	}

	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	public String getMenuLabel() {
		return "4 Color"; // this will be the name of the filter in the filters menu
	}
}
        
