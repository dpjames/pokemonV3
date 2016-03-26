import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class View extends JFrame{
   private JPanel p;
   private Graphics pen;
   private BufferedImage frame;
   private int width,height;
   public View(int width, int height, Model m){
      super("Pokemon 3.0");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      p = new JPanel();
      p.setPreferredSize(new Dimension(width, height));
      add(p);
      pack();
      setUpKeys(m);
      setVisible(true);
      try{Thread.sleep(250);}catch(Exception e){}
      frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      pen = frame.getGraphics();
      this.width = width;
      this.height = height;
   }
   public Graphics getPen(){
      return pen;
   }
   public void drawFrame(){
      p.getGraphics().drawImage(frame, 0, 0, width, height, null);
      pen.fillRect(0,0,width,height);  //MIGHT NOT BE NEEDED LATER ON. CHECK IT
   }
   private void setUpKeys(Model m){
      //Forward
      p.getInputMap().put(KeyStroke.getKeyStroke("W"),"forward");
      p.getActionMap().put("forward", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                m.movePlayer('w');
            }
        });
      //Right
      p.getInputMap().put(KeyStroke.getKeyStroke("D"),"right");
      p.getActionMap().put("right", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                m.movePlayer('d');
            }
        });
      //Backward
      p.getInputMap().put(KeyStroke.getKeyStroke("S"),"backward");
      p.getActionMap().put("backward", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                m.movePlayer('s');
            }
        });
      //Left
      p.getInputMap().put(KeyStroke.getKeyStroke("A"),"left");
      p.getActionMap().put("left", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                m.movePlayer('a');
            }
        });
   }
   //this is for world building to add a key adapter for ease.
   public JPanel getPanel(){
      return p;
   }
}