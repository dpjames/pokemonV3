import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
public class LongGrass extends GameObject{
   public static final int OBJECT_NUMBER = 3;
   private boolean playerOn, moving;
   private ArrayList<Pokemon> wildPokemon;
   private int detectionCount;
   private static int battleChance;
   private static boolean battle;
   private static ArrayList<Pokemon> wildPkmn;
   public LongGrass(int x, int y, int width, int height) throws java.io.IOException{
      super(x,y,width,height,true,PkmnImageFactory.getImage("res/longgrass",width,height));
      //add possible pkmn here
      this.playerOn = false;
      this.battle = false;
      this.moving = false;
      this.detectionCount = 0;
      this.battleChance = 2; //To be made a parameter later
      this.wildPokemon = new ArrayList<Pokemon>();
      Random rand = new Random();
      wildPokemon.add(new Pokemon("wild", new Move[] {new Tackle(),new Move("name"),new Move("name"),new Move("name")} , 30, 100, rand.nextInt(50)+2));
   }
   public void update(double delta){
      if(playerOn && moving && detectionCount == 1){
         tryBattle();
      }
   }
   public void tryBattle(){
      Random rand = new Random();
      int i = rand.nextInt(battleChance);
      if(i==0){
         battle = true;
         wildPkmn = this.wildPokemon;
         System.out.println("battle");
      }
   }
   public static boolean isBattle(){
      return battle;
   }
   public static void endBattle(){
      battle = false;
      wildPkmn = null;
   }
   public static ArrayList<Pokemon> getPokemon(){
      return wildPkmn;
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