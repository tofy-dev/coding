package filters;
import imagelab.*;

/*
 * Skyler Tabofunda
 * P7
 * 5/12
 * Practice filter
 */
public class P7_Tabofunda_Skyler_PracticeFilter implements ImageFilter {
    ImgProvider filteredImage;

    public void filter (ImgProvider ip) {
        short[][] oR = ip.getRed(); // get a 2D array of all original red values
        short[][] oG = ip.getGreen(); // get a 2D array of all original green values
        short[][] oB = ip.getBlue(); // get a 2D array of all original blue values
        short[][] oA = ip.getAlpha(); // get a 3D array of all original alpha values (transparency)
    
        int rows = oR.length; // height is the number of rows which is the length of the outer array
        int cols = oR[0].length; // width is the number of columns which is the length of each inner array
    
        short[][] nR = new short[rows][cols*3/2]; // declare a new array of red values of size height rows x width columns
        short[][] nG = new short[rows][cols*3/2]; // declare a new array of green values of size height rows x width columns
        short[][] nB = new short[rows][cols*3/2]; // declare a new array of blue values of size height rows x width columns
        short[][] nA = new short[rows][cols*3/2]; // declare a new array of alpha values of size height rows x width columns

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (c < cols/3) {
                    short max = oG[r][c]>(oR[r][c]>oB[r][c]?oR[r][c]:oB[r][c])?oG[r][c]:(oR[r][c]>oB[r][c]?oR[r][c]:oB[r][c]);
                    nR[r][c] = max;
                    nG[r][c] = max;
                    nB[r][c] = max;
                    nA[r][c] = oR[r][c];
                } else if (c < 2*cols/3) {
                    nR[r][c] = oR[r][c+cols/3];
                    nG[r][c] = oG[r][c+cols/3];
                    nB[r][c] = oB[r][c+cols/3];
                    nA[r][c] = oA[r][c+cols/3];
                } else {
                    nR[r][c] = oR[r][c-cols/3];
                    nG[r][c] = oG[r][c-cols/3];
                    nB[r][c] = oB[r][c-cols/3];
                    nA[r][c] = oA[r][c-cols/3];
                }
            }
            for (int c = 0; c < cols/2; c++) {
                if (r < rows/3) {
                    if (r<rows/3-10) {
                        nG[r][c+cols] = oG[r+10][c];
                    } else {
                        nG[r][c+cols] = 0;
                    }
                    nR[r][c+cols] = oR[r][c];
                    nB[r][c+cols] = oB[r][c];
                    nA[r][c+cols] = oA[r][c];
                } else if (r < 2*rows/3) {
                    if (c >= cols/2) {
                        nR[r][c+cols] = oR[r][c-cols/2];
                    } else {
                        nR[r][c+cols] = 0;
                    }
                    nG[r][c+cols] = oG[r][c];
                    nB[r][c+cols] = oB[r][c];
                    nA[r][c+cols] = oA[r][c];
                } else {
                    if (r >= rows*2/3 + 10) {
                        nG[r][c+cols] = oG[r-10][c];
                    } else {
                        nG[r][c+cols] = 0;
                    }
                    nR[r][c+cols] = oR[r][c];
                    nB[r][c+cols] = oB[r][c];
                    nA[r][c+cols] = oA[r][c];
                }
            }
        }

        filteredImage = new ImgProvider();
        filteredImage.setColors(nR, nG, nB, nA);
        filteredImage.showPix("Practice filter");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    public String getMenuLabel() {
        return "Practice filter";
    }

}