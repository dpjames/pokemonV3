import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public abstract class PkmnImageFactory{
   public static BufferedImage getImage(String dir, int width, int height) throws IOException{
      return resize(ImageIO.read(new File(dir)),width,height);
   }
   private static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}  
}