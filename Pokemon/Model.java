import java.awt.*;
import java.util.*;
import java.io.*;

public class Model{
   private boolean running;
   private Player player;
   private ArrayList<GameObject> world;
   private int frameWidth, frameHeight;
   private Battle battle;
   public Model (String worldName, int width, int height) throws java.io.IOException{
        this.running = true;
        this.frameWidth = width;
        this.frameHeight = height;
        this.player = new Player(0,0,width,height);
        world = new ArrayList<GameObject>();
        //world.add(new Grass(-100,-100,50,50));
        //world.add(new Rock(-150,-100,50,50));
        //world.add(new Rock(-100,-100,50,50));
        //world.add(new Tree(300,300,50,100));
        //world.add(new LongGrass(200,200,50,50));
        //world.add(new Dirt(100,100,50,50));
        readInWorld(worldName);
   }
   public void readInWorld(String name){
      try{
         Scanner worldScan = new Scanner(new File("res/"+name));
         player.setX(worldScan.nextInt());
         player.setY(worldScan.nextInt());
         worldScan.nextLine();
         while(worldScan.hasNextLine()){
            String line = worldScan.nextLine();
            Scanner lineScan = new Scanner(line);
            //figure this out
            String obType = lineScan.next(); //line format: [ClassName] [x] [y] <param> <param> .....
            world.add(GameObjectFactory.makeObjectByNumber(obType, lineScan));
         }
      }catch(Exception e){
         System.out.println("Incorrect File bruh");
         e.printStackTrace();
      }
   }
   public void setRunning(boolean running){
      this.running = running;
   }
   public boolean isRunning(){
      return running;
   }
   public void draw(Graphics pen){
      if(LongGrass.isBattle() && battle !=null){
         battleDraw(pen);
      }else{
         for(GameObject ob : world){
            if(ob.inFrame(player.getFrameX(), player.getFrameY(), frameWidth, frameHeight)){
               ob.draw(pen,player.getX(), player.getY(), player.getLocalX(), player.getLocalY());
            }
         }
         player.draw(pen);

      }
   }
   public void battleDraw(Graphics pen){
      battle.draw(pen);
   }
   public void battleUpdate(double delta){
      if(battle == null){
         battle = new Battle(player.getPokemon(), LongGrass.getPokemon());
      }
      if(battle.isOver()){
         LongGrass.endBattle();
         battle = null;
      }else{
         battle.update();
      }
   }
   public void update(double delta){
      if(LongGrass.isBattle()){
         battleUpdate(delta);
      }else{
         player.update(delta);
         for(GameObject ob : world){
            if(ob.getClass().getName().equalsIgnoreCase("longgrass")){
               ((LongGrass)ob).checkOn(player.getRectangle(), player.getMoving());
            }
            ob.update(delta);
         }
      }
   }
   public void movePlayer(char dir){
      if(!LongGrass.isBattle()){
         player.setDir(dir);
         if(canMove(dir)){
            player.move();
         }
      }else if(battle!=null){
         battle.move(dir);
      }
   }
   private boolean canMove(char c){
      for(GameObject ob : world){
         if(ob.inFrame(player.getFrameX(), player.getFrameY(), frameWidth, frameHeight)){
            if(ob.inWay(player.getX(), player.getY(), c, player.getWidth(), player.getHeight())){
               return false;
            }
         }
      }
      return true;
   }
   public Player getPlayer(){
      return player;
   }
}