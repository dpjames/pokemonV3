import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class Rock extends GameObject{
   public static final int OBJECT_NUMBER = 4;
   public Rock(int x, int y, int width, int height) throws java.io.IOException{
      super(x,y,width,height,false, PkmnImageFactory.getImage("res/rock",width,height)); //ImageIO.read(new File("res/rock"))
   }
   public void update(double delta){
   }
}