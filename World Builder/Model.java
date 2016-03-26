import java.awt.*;
import java.util.*;
import java.io.*;

public class Model{
   private boolean running;
   private Player player;
   private ArrayList<GameObject> world;
   private int frameWidth, frameHeight;
   public Model (String worldName, int width, int height) throws java.io.IOException{
        this.running = true;
        this.frameWidth = width;
        this.frameHeight = height;
        this.player = new Player(0,0,width,height);       //LOOK HERE, CHANGE PLAYER DIMENSIONS LATER
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
      for(GameObject ob : world){
         if(ob.inFrame(player.getFrameX(), player.getFrameY(), frameWidth, frameHeight)){
            ob.draw(pen,player.getX(), player.getY(), player.getLocalX(), player.getLocalY());
         }
      }
      player.draw(pen);
   }
   public void update(double delta){
      player.update(delta);
      for(GameObject ob : world){
         if(ob.getClass().getName().equalsIgnoreCase("longgrass")){
            ((LongGrass)ob).checkOn(player.getRectangle(), player.getMoving());
         }
         ob.update(delta);
      }
   }
   public void movePlayer(char dir){
      player.setDir(dir);
      if(true){
         player.move();
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
   public int[] getLoc(){
      return new int[] {player.getX(), player.getY()};
   }
   public void setLoc(int[] loc){
      player.setLoc(loc);
   }
}