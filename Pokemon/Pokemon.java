import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class Pokemon{
   private String name;
   private Move[] moves;
   private int speed,hp,lvl,maxHP;
   public Pokemon(String name, Move[] moves, int speed, int hp, int lvl){
      this.name = name;
      this.moves = moves;
      this.speed = speed;
      this.hp = hp;
      this.lvl = lvl;
      this.maxHP = hp;
      
   }
   public Pokemon(Pokemon pkmn){
      this.name = pkmn.name;
      this.moves = pkmn.moves;
      this.speed = pkmn.speed;
      this.hp = pkmn.hp;
      this.lvl = pkmn.lvl;
      this.maxHP = pkmn.maxHP;
   }
   public int getMaxHP(){
      return maxHP;
   }
   
   public String toString(){
      return name;
   }
   
   public Move getMove(int i){
      return moves[i];
   }
   
   public void update(){
      
   }
   
   public void draw(Graphics pen, int x, int y){
      try{
         pen.drawImage(PkmnImageFactory.getImage("res/"+name,200,200), x,y,null);
      }catch (java.io.IOException e){
         System.out.println("could not find image for pokemon: " + name);
      }
   }
   public int getSpeed(){
      return speed;
   }
   public void drawMoves(Graphics pen){
      pen.setColor(Color.BLACK);
      pen.setFont(new Font("", Font.BOLD, 20));
      pen.drawString(moves[0].toString(), 65,400);
      pen.drawString(moves[1].toString(), 390,400);
      pen.drawString(moves[2].toString(), 65,425);
      pen.drawString(moves[3].toString(), 390,425);
      pen.setColor(Color.WHITE);
   }
   public int getHP(){
      return hp;
   }
   public Move getMove(){
      Random rand = new Random();
      return moves[rand.nextInt(4)];
   }
   public void hurt(int d){
      hp-=d;
      System.out.println(name + " " + hp);
   }
}