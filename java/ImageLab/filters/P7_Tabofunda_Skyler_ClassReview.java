package filters;
import imagelab.*;

/*
 * Skyler Tabofunda
 * P7
 * 5/12
 * Practice filter
 */
public class P7_Tabofunda_Skyler_ClassReview implements ImageFilter {
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

        int border = 20;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (c < cols/2) {
                    if (c<border||c>=cols/2-border||r<border||r>=rows-border) {
                        nR[r][c] = 0;
                        nG[r][c] = 0;
                        nB[r][c] = 0;
                    } else {
                        nR[r][c] = (short)(255-oR[r][c]);
                        nG[r][c] = (short)(255-oG[r][c]);
                        nB[r][c] = (short)(255-oB[r][c]);
                    }
                    nA[r][c] = oA[r][c];
                } else {
                    nR[r][c] = oR[r][c-cols/2];
                    nG[r][c] = oG[r][c-cols/2];
                    if (c<cols-5) {
                        nB[r][c] = oB[r][c+5-cols/2];
                    } else {
                        nB[r][c] = 0;
                    }
                    nA[r][c] = oA[r][c-cols/2];
                }
            }
        }

        filteredImage = new ImgProvider();
        filteredImage.setColors(nR, nG, nB, nA);
        filteredImage.showPix("Class Review");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    public String getMenuLabel() {
        return "Class Review";
    }

}