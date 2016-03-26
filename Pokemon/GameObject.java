import java.awt.*;
import java.io.*;
import java.awt.image.*;
public abstract class GameObject{
   private boolean passable;
   private int x,y,width,height;
   private BufferedImage img;
   public GameObject(int x, int y, int width, int height, boolean passable,BufferedImage img){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.passable = passable;
      this.img = img;
   }
   public BufferedImage getImg(){
      return img;
   }
   public int getX(){
      return x;
   }
   public int getY(){
      return y;
   }
   public void moveX(int delta){
      x+=delta;
   }
   public void moveY(int delta){
      y+=delta;
   }
   public boolean inFrame(int x, int y, int width, int height){
      Rectangle frame = new Rectangle(x,y,width,height);
      return this.getRectangle().intersects(frame) || frame.contains(this.getRectangle());
      //return (this.x > x-width/2) && (this.x < x + width/2) && (this.y>y-height/2) && (this.y<y+height/2);
   }
   public Rectangle getRectangle(){
      return new Rectangle(x,y,width,height);
   }
   public boolean Collides(GameObject other){
      if(this.getRectangle().intersects(other.getRectangle())){
         return true;
      }
      return false;
   }
   public int getHeight(){
      return height;
   }
   public int getWidth(){
      return width;
   }
   public void draw(Graphics pen, int x, int y, int px, int py){
      //pen.drawImage(img, px - (x - this.x), py - (y - this.y),null);
      pen.drawImage(img, this.x - (x-px), this.y - (y-py), null);
      //pen.setColor(Color.BLUE);
     // pen.fillRect(this.x - (x-px), this.y - (y-py), this.getWidth(), this.getHeight());
      //pen.setColor(Color.WHITE);
      //System.out.println("---"+ (this.x - (x-px)) + " " + (this.y - (y-py)) + " ");
   }
   public boolean inWay(int x, int y, char dir, int width, int height){
      int dx = 0;
      int dy = 0;
      if(dir == 'w'){
         dy = -(height-1);
      }else if(dir == 'd'){
         dx = width-1;
      }else if(dir == 's'){
         dy = (height-1);
      }else if(dir == 'a'){
         dx = -(width-1);
      }
      Rectangle r1 = new Rectangle(x+dx, y+dy, width, height);
      if(getRectangle().intersects(r1) && !passable){
         //System.out.println(r1 + " " + getRectangle());
      }
      return getRectangle().intersects(r1) && !passable;
   }
   public void setX(int x){
      this.x = x;
   }
   public void setY(int y){
      this.y = y;
   }
   
   
   
   
   
   
   
   
   
   
   
   public void update(double delta){
      
   }
}