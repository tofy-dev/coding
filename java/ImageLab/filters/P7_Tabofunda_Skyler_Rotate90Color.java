package filters;
import imagelab.*;

/*
 * Skyler Tabofunda
 * P7
 * 5/12
 * Rotate 90 BW
 */
public class P7_Tabofunda_Skyler_Rotate90Color implements ImageFilter {
    ImgProvider filteredImage;

    public void filter (ImgProvider ip) {
        short[][] oR = ip.getRed(); // get a 2D array of all original red values
        short[][] oG = ip.getGreen(); // get a 2D array of all original green values
        short[][] oB = ip.getBlue(); // get a 2D array of all original blue values
        short[][] oA = ip.getAlpha(); // get a 3D array of all original alpha values (transparency)
    
        int rows = oR.length; // height is the number of rows which is the length of the outer array
        int cols = oR[0].length; // width is the number of columns which is the length of each inner array
    
        short[][] nR = new short[cols][rows]; // declare a new array of red values of size height rows x width columns
        short[][] nG = new short[cols][rows]; // declare a new array of green values of size height rows x width columns
        short[][] nB = new short[cols][rows]; // declare a new array of blue values of size height rows x width columns
        short[][] nA = new short[cols][rows]; // declare a new array of alpha values of size height rows x width columns

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                nR[c][rows-1-r] = oR[r][c];
                nG[c][rows-1-r] = oG[r][c];
                nB[c][rows-1-r] = oB[r][c];
                nA[c][rows-1-r] = oA[r][c];
            }
        }

        filteredImage = new ImgProvider();
        filteredImage.setColors(nR, nG, nB, nA);
        filteredImage.showPix("Rotate 90 Color");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    public String getMenuLabel() {
        return "Rotate (Color)";
    }

}