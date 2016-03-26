import java.awt.event.*;
public class Controller{
   public static void gameLoop(){
      long startTime = System.nanoTime();
      int goalFPS = 60;
      long fpsTimer = 0;
      int fps = 0;
      while(m.isRunning()){
         long now = System.nanoTime();
         long loopTime = now - startTime;
         startTime = now;
         double delta = loopTime / (1000000000.0/goalFPS);
         
         fpsTimer += loopTime;
         fps++;
         if(fpsTimer >= 1000000000.0){
            System.out.println("FPS: " + fps);
            fps = 0;
            fpsTimer = 0;
         }
         m.update(delta);
         m.draw(v.getPen());
         v.drawFrame();
         try{
            Thread.sleep((startTime - System.nanoTime() + (1000000000/goalFPS))/1000000);
         }catch(Exception e){
            //e.printStackTrace()
         }
         
      }
   }
   
   
   private static View v;
   private static Model m;
   public static void main(String[] args){
      int w = 15*50;
      int h = 9*50;
      try{
         m = new Model("world.txt", w,h);
      }catch(java.io.IOException e){
         System.out.println("ERROR: could not find file. See stack for details");
         e.printStackTrace();
         System.exit(0);
      }
      v = new View(w,h,m);
      gameLoop();
   }
}