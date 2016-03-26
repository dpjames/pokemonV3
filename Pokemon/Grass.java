import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class Grass extends GameObject{
   public static final int OBJECT_NUMBER = 2;
   public Grass(int x, int y, int width, int height) throws java.io.IOException{
      super(x,y,width,height,true, PkmnImageFactory.getImage("res/grass",width,height));
   }
   public void update(double delta){
   }
}