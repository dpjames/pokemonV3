import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class Tree extends GameObject{
   public static final int OBJECT_NUMBER = 5;
   public Tree(int x, int y, int width, int height) throws java.io.IOException{
      super(x,y,width,height,false, PkmnImageFactory.getImage("res/tree",width,height));
   }
   public void update(double delta){
   }
}