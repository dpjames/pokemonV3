import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class LongGrass extends GameObject{
   public static final int OBJECT_NUMBER = 3;
   private boolean playerOn, battle, moving;
   private int detectionCount;
   public LongGrass(int x, int y, int width, int height) throws java.io.IOException{
      super(x,y,width,height,true,PkmnImageFactory.getImage("res/longgrass",width,height));
      //add possible pkmn here
      this.playerOn = false;
      this.battle = false;
      this.moving = false;
      this.detectionCount = 0;
      
   }
   public void update(double delta){
      if(playerOn && moving && detectionCount == 1){
         tryBattle();
      }
   }
   public void tryBattle(){
      //System.out.println("battle");
      //battle random logic here;
   }
   public void checkOn(Rectangle rect, boolean moving){
      this.moving = moving;
      if(super.getRectangle().intersects(rect)){
         playerOn = true;
         detectionCount++;
      }else{
         playerOn = false;
         detectionCount = 0;
      }
   }
}