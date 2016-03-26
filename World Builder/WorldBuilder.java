import java.awt.event.*;
import java.io.*;
import java.util.*;
public class WorldBuilder{
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
            //System.out.println("FPS: " + fps);
            fps = 0;
            fpsTimer = 0;
         }
         try{
            m.update(delta);
            m.draw(v.getPen());
            v.drawFrame();
         }catch(NullPointerException ex){
            //bad but ohwell
         }
         try{
            Thread.sleep((startTime - System.nanoTime() + (1000000000/goalFPS))/1000000);
         }catch(Exception e){
            //e.printStackTrace()
         }
      }
   }
   
   public static class Keys extends KeyAdapter{
      public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
         //reload world and graphics
			if (keyCode == KeyEvent.VK_SPACE) {
            try{
               int[] loc = m.getLoc();
               m = new Model("world.txt", width,height);
               v.dispose();
               v = new View(width,height,m);
               v.getPanel().addKeyListener(new Keys());
               m.setLoc(loc);
            }catch(java.io.IOException ex){
               System.out.println("ERROR: could not find file. See stack for details");
               ex.printStackTrace();
               System.exit(0);
            }
         }else if(keyCode == KeyEvent.VK_C){ //cleans the file
            cleanFile();
			}else{
            addStuff(keyCode);
         }
      }
      private void cleanFile(){
         long start = System.nanoTime();
         try{
            Scanner scan = new Scanner(file);
         int linenum = 0;
         String sloc = scan.nextLine();
         Scanner slocScan = new Scanner(sloc);
         int[] loc = new int[]{slocScan.nextInt(), slocScan.nextInt()};
         //ArrayList<Integer[]> cords = new ArrayList<Integer[]>();
         ArrayList<String> rawFile = new ArrayList<String>();
         while(scan.hasNextLine()){
            rawFile.add(scan.nextLine());
            //cords.add(xy);
         }
         sortFile(rawFile);
         cleanFile(rawFile);
         overWriteFile(rawFile, loc);
         
         
         }catch (FileNotFoundException e){
            e.printStackTrace();
         }
         System.out.print("Time: ");
         System.out.println((System.nanoTime() - start)/1000000);
         System.out.println("CLEAN");
      }
      private void overWriteFile(ArrayList<String> rawFile, int[] loc){
         try {
            FileWriter f = new FileWriter(file);
            f.write(loc[0] + " " + loc[1]);
            for(String str : rawFile){
               f.write("\n"+str);
            }
            f.write("\n");
            f.close();
         } catch(Exception e) {
            
         }
      }
      private void cleanFile(ArrayList<String> rawFile){
         for(int i = rawFile.size()-1; i > 0; i--){
            Scanner scan = new Scanner(rawFile.get(i));
            String name = scan.next();
            int[] xy = new int[] {scan.nextInt(), scan.nextInt()};
            Scanner compareScan = new Scanner (rawFile.get(i-1));
            String compareName = compareScan.next();
            int[] comparexy = new int[] {compareScan.nextInt(), compareScan.nextInt()};
            if(xy[0] == comparexy[0] && xy[1] == comparexy[1]){
               if(name.equals("white")){
                  rawFile.remove(i-1);
               }else if(compareName.equals("white")){
                  rawFile.remove(i);
               }else{
                  rawFile.remove(i-1);
               }
            }else if(name.equals("white")){
               rawFile.remove(i);
            }else if(i==1 && compareName.equals("white")){
               rawFile.remove(0);
            }
         }
         //System.out.println(rawFile.size());
      }
      private void sortFile(ArrayList<String> rawFile){
         //System.out.println(rawFile.size());
         for(int i = 0; i < rawFile.size(); i++){
            String s = rawFile.get(i);
            Scanner scan = new Scanner(s);
            scan.next();
            int[] xy = new int[] {scan.nextInt(), scan.nextInt()};
            int index = i;
            for(int j = i+1; j<rawFile.size(); j++){
               String compare = rawFile.get(j);
               Scanner compareScan = new Scanner(compare);
               compareScan.next();
               int[] comparexy = new int[] {compareScan.nextInt(), compareScan.nextInt()};
               if(xy[0] > comparexy[0]){
                  xy = comparexy;
                  String temp = rawFile.get(i);
                  rawFile.set(i, rawFile.get(j));
                  rawFile.set(j, temp);
               }else if(xy[0] == comparexy[0]){
                  if(xy[1]>comparexy[1]){
                     xy = comparexy;
                     String temp = rawFile.get(i);
                     rawFile.set(i, rawFile.get(j));
                     rawFile.set(j, temp);
                  }
               }
            }
         }
         //System.out.println(rawFile.size());
      }
      
      
      
      
      
      private void addStuff(int keyCode){
         
         if(keyCode == KeyEvent.VK_G){
            write("grass "+ m.getPlayer().getX() + " " + m.getPlayer().getY() + " " + 50 + " " + 50);
         }else if(keyCode == KeyEvent.VK_F){
            write("dirt "+ m.getPlayer().getX() + " " + m.getPlayer().getY() + " " + 50 + " " + 50);
         }else if(keyCode == KeyEvent.VK_L){
            write("longGrass "+ m.getPlayer().getX() + " " + m.getPlayer().getY() + " " + 50 + " " + 50);
         }else if(keyCode == KeyEvent.VK_R){
            write("rock "+ m.getPlayer().getX() + " " + m.getPlayer().getY() + " " + 50 + " " + 50);
         }else if(keyCode == KeyEvent.VK_T){
            write("tree "+ m.getPlayer().getX() + " " + m.getPlayer().getY() + " " + 50 + " " + 100);
         }else if(keyCode == KeyEvent.VK_Q){
            write("white " + m.getPlayer().getX() + " " + m.getPlayer().getY());
         }
      }
      public void write(String code) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(
					new FileWriter(file, true));
			bw.write(code);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally { // always close the file
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
					// just ignore it
				}
			}
		}
	}
   }
   
   
   private static View v;
   private static Model m;
   private static File file;
   //for building
   private static int width, height;
   public static void main(String[] args){
      int w = 15*50;
      int h = 9*50;
      String name = "world.txt";
      try{
         file = new File("res/"+name);
         m = new Model(name, w,h);
      }catch(java.io.IOException e){
         System.out.println("ERROR: could not find file. See stack for details");
         e.printStackTrace();
         System.exit(0);
      }
      v = new View(w,h,m);
      width = w;
      height = h;
      v.getPanel().addKeyListener(new Keys());
      gameLoop();
   }
}