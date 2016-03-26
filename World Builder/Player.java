import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class Player extends GameObject{
   //private static final double SPEED = 5;
   private static final int WIDTH = 50;
   private static final int HEIGHT = 50;
   private static final double SPEED = 1000000;
   private static final boolean PASSABLE = false;
   private int frameWidth, frameHeight, centerX, centerY,foot, oldX, oldY;
   private char dir;
   private boolean moving;
   
   
   public Player(int x, int y, int width, int height) throws IOException{
      super(x,y, WIDTH, HEIGHT, PASSABLE, PkmnImageFactory.getImage("res/players",WIDTH,HEIGHT));
      frameWidth = width;
      frameHeight = height;
      moving = false;
      this.centerX = width/2;
      this.centerY = height/2;
      System.out.println(centerX + " " + centerY);
      dir = 's';
      foot = 0; // 0 is left 1 is right;
   }
   public void update(double delta){
      if(moving){
         if(dir == 'w'){
            super.moveY((int)(-SPEED*delta));
         }else if(dir == 'd'){
            super.moveX((int)(SPEED*delta));
         }else if(dir == 's'){
            super.moveY((int)(SPEED*delta));
         }else if(dir == 'a'){
            super.moveX((int)(-SPEED*delta));
         }
         checkIfStillMoving();
      }
   }
   public boolean getMoving(){
      return moving;
   }
   private void checkIfStillMoving(){
      if(dir == 'w' && oldY-HEIGHT>super.getY()){
         super.setY(oldY-HEIGHT);
         moving = false;
      }else if(dir == 'd' && oldX+WIDTH < super.getX()){
         super.setX(oldX+WIDTH);
         moving = false;
      }else if(dir == 's' && oldY+HEIGHT<super.getY()){
         super.setY(oldY+HEIGHT);
         moving = false;
      }else if(dir == 'a' && oldX-WIDTH > super.getX()){
         super.setX(oldX-WIDTH);
         moving = false;
      }
   }
   public boolean isMoving(){
      return moving;
   }
   public char getDir(){
      return dir;
   }
   public void draw(Graphics pen){
      pen.drawImage(getImg(),centerX-super.getWidth()/2 - 5,centerY-super.getHeight()/2 - 10,null);  //CENTER OF SCREEN NEED TO CHANGE LATER -------the minus fives are dependent on size changed to increase player image. See get image for the actual size changes. It is currently based around the size of the player being 50
   }
   public BufferedImage getImg(){
      try{
         if(!moving){
            return PkmnImageFactory.getImage("res/player"+dir,WIDTH + 10,HEIGHT + 10);
         }
         return PkmnImageFactory.getImage("res/player"+dir+foot,WIDTH + 10,HEIGHT + 10); 
      }catch(java.io.IOException e){
         System.out.println("res/player"+dir+foot);
         System.out.println("ERROR: could not find file. See stack for details");
         e.printStackTrace();
         System.exit(0);
      }
      return null;
      
      
      
   }
   public int getFrameX(){
      return (super.getX()+WIDTH/2)- frameWidth/2;
   }
   public int getFrameY(){
      return (super.getY() + HEIGHT/2) - frameHeight/2;
   }
   public void move(){
      if(!moving){
         //System.out.println("move");
         this.moving = true;
         if(foot == 0){
            foot = 1;
         }else{
            foot = 0;
         }
         oldX = super.getX();
         oldY = super.getY();
         //System.out.println(super.getX() + " " + super.getY());
      }
   }
   public void setDir(char dir){
      if(!moving){
         this.dir = dir;
      }
   }
   public int getLocalX(){
      return frameWidth/2 - WIDTH/2;
   }
   public int getLocalY(){
      return frameHeight/2 - HEIGHT / 2;
   }
   public void setMoving(boolean b){
      this.moving = b;
   }
   public void setToOld(){
      super.setX(oldX);
      super.setY(oldY);
   }
   public void setLoc(int[] loc){
      super.setX(loc[0]);
      super.setY(loc[1]);
   }
}
