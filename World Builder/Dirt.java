import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class Dirt extends GameObject{
   public static final int OBJECT_NUMBER = 1;
   public Dirt(int x, int y, int width, int height) throws java.io.IOException{
      super(x,y,width,height,true, PkmnImageFactory.getImage("res/dirt",width,height));
   }
   public void update(double delta){
   }
}