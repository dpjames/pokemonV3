import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
public class Battle{
   private static final int WIDTH = 15*50;
   private static final int HEIGHT = 9*50;
   private Pokemon wild;
   private ArrayList<Pokemon> playerPkmn;
   private boolean over;
   private Move[][] moves;
   private Move selectedMove, wildMove;
   private int x, y;
   private boolean moveSelected;
   private BufferedImage background;
   public Battle(ArrayList<Pokemon> playerPkmn, ArrayList<Pokemon> wildPkmn){
      Random rand = new Random();
      this.wild = new Pokemon(wildPkmn.get(rand.nextInt(wildPkmn.size())));
      this.playerPkmn = playerPkmn;
      checkPlayerHp();
      this.over = false;
      moves = new Move[2][2];
      setMoves();
      x = 0;
      y = 0;
      try{
         this.background = PkmnImageFactory.getImage("res/battleBack", WIDTH, HEIGHT);
      }catch(java.io.IOException e){
         System.out.println("Could not find image file");
         e.printStackTrace();
      }
   }
   public void checkPlayerHp(){
      int firstPkmnIndex = 0;
      for(int i = playerPkmn.size()-1; i>=0; i--){
         if(playerPkmn.get(i).getHP() > 0){
            firstPkmnIndex = i;
         }
      }
      Pokemon temp = playerPkmn.get(0);
      playerPkmn.set(0, playerPkmn.get(firstPkmnIndex));
      playerPkmn.set(firstPkmnIndex, temp);
   }
   public void setMoves(){
      Pokemon p = playerPkmn.get(0);
      moves[0][0] = p.getMove(0);
      moves[1][0] = p.getMove(1);
      moves[0][1] = p.getMove(2);
      moves[1][1] = p.getMove(3);
   }
   public void draw(Graphics pen){
      pen.drawImage(background, 0,0,null);
      wild.draw(pen, 450, 20);
      playerPkmn.get(0).draw(pen, 100, 140);
      playerPkmn.get(0).drawMoves(pen);
      drawHP(pen);
      pen.setColor(Color.BLACK);
      pen.fillRect((x*325)+45,(y*25)+385,20,20);
      
   }
   public void drawHP(Graphics pen){
      int playerHP = 100*playerPkmn.get(0).getHP()/playerPkmn.get(0).getMaxHP();
      int wildHP = 100*wild.getHP()/wild.getMaxHP();
      pen.setColor(Color.BLUE);
      pen.fillRect(0,0,wildHP*2,10);
      pen.fillRect(550,325,playerHP*2,10);
      pen.setColor(Color.BLACK);
      pen.drawString(playerPkmn.get(0).toString(),550,325);
      pen.drawString(wild.toString(),0,50);
      
   }
   public void update(){
      if(moveSelected){
         battle();
         moveSelected = false; 
      }
   }
   private void checkDead(){
      if(wild.getHP()<=0){
         this.over = true;
         return;
      }
      checkPlayerHp();
      for(Pokemon p : playerPkmn){
         if(p.getHP()>0){
            return;
         }
      }
      this.over = true;
   }
   public void battle(){
      wildMove = wild.getMove();
      if(wild.getSpeed() > playerPkmn.get(0).getSpeed()){
         battle(wild, playerPkmn.get(0),wildMove,selectedMove);
      }else {
         battle(playerPkmn.get(0), wild,selectedMove,wildMove);
      }
   }
   public void battle(Pokemon pkmn1, Pokemon pkmn2,Move move1, Move move2){
      pkmn2.hurt(move1.getDamage());
      if(pkmn2.getHP()>0){
         pkmn1.hurt(move2.getDamage());;
      }
      checkDead();
   }
   public void move(char dir){
      //this.over = true; //to change later
      //System.out.println(playerPkmn.get(0));
      //System.out.println(wild);
      if(dir == 'w'){
         y--;
         if(y<0){
            y = 0;
         }
      }else if(dir == 'd'){
         x++;
         if (x>1){
            x = 1;
         }
      }else if(dir == 's'){
         y++;
         if(y>1){
            y = 1;
         }
      }else if(dir == 'a'){
         x--;
         if(x<0){
            x = 0;
         }
      }else if(dir == 'e'){
         //select a move
         moveSelected = true;
         selectedMove = moves[x][y];
      }
   }
   public boolean isOver(){
      return over;
   }
}