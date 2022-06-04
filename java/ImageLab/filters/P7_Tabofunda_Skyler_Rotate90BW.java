package filters;
import imagelab.*;

/*
 * Skyler Tabofunda
 * P7
 * 5/12
 * Rotate 90 BW
 */
public class P7_Tabofunda_Skyler_Rotate90BW implements ImageFilter {

    ImgProvider filteredImage;

    public void filter (ImgProvider ip) {
        short[][] im = ip.getBWImage();
        int rows = im.length;
        int cols  = im[0].length;

        short[][] newImage = new short[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                newImage[c][rows-1-r] = im[r][c];
            }
        }

        filteredImage = new ImgProvider();
        filteredImage.setBWImage(newImage);
        filteredImage.showPix("Rotate 90 BW");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    public String getMenuLabel() {
        return "Rotate (BW)";
    }

}