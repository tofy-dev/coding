package filters;
import imagelab.*;

/*
 * Skyler Tabofunda
 * P7
 * 5/12
 * HHReflectionBW
 */
public class P7_Tabofunda_Skyler_HHReflectionBW implements ImageFilter {
    ImgProvider filteredImage;

    public void filter (ImgProvider ip) {
        short[][] im = ip.getBWImage();
        int rows = im.length;
        int cols = im[0].length;
        short[][] newImg = new short[rows][cols];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols/2; c++) {
                newImg[r][c] = im[r][c];
                newImg[r][cols-1-c] = im[r][c];
            }
        }

        filteredImage = new ImgProvider();
        filteredImage.setBWImage(newImg);
        filteredImage.showPix("HH Reflection (BW)");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    public String getMenuLabel() {
        return "HH Reflection (BW)";
    }

}