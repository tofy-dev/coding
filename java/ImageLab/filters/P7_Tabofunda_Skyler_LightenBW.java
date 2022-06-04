package filters;
import imagelab.*;
import java.util.*;
import javax.swing.JOptionPane;

/*
 * Skyler Tabofunda
 * P7
 * 5/12
 * LightenBW
 */
public class P7_Tabofunda_Skyler_LightenBW implements ImageFilter {
    ImgProvider filteredImage;

    public void filter (ImgProvider ip) {
        Double per = Double.parseDouble(JOptionPane.showInputDialog("Enter Lighten Percentage"));
        
        short[][] im = ip.getBWImage();
        int rows = im.length;
        int cols = im[0].length;
        short[][] newImg = new short[rows][cols];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                short pixel = (short)((im[r][c])+(255-im[r][c])*(per/100.0));
                newImg[r][c] = pixel;
            }
        }

        filteredImage = new ImgProvider();
        filteredImage.setBWImage(newImg);
        filteredImage.showPix("Lighten (BW)");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    public String getMenuLabel() {
        return "Lighten (BW)";
    }

}