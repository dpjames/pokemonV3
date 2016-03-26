import java.util.Scanner;
public abstract class GameObjectFactory{
   public static GameObject makeObjectByNumber(String i, Scanner param) throws java.io.IOException{
      //System.out.println(i);
      if(i.equalsIgnoreCase("Grass")){
         return constructGrass(param);
      }
      if(i.equalsIgnoreCase("Dirt")){
         return constructDirt(param);
      }
      if(i.equalsIgnoreCase("LongGrass")){
         return constructLongGrass(param);
      }
      if(i.equalsIgnoreCase("Rock")){
         return constructRock(param);
      }
      if(i.equalsIgnoreCase("Tree")){
         return constructTree(param);
      }
      return null;
   }
   private static Grass constructGrass(Scanner param)throws java.io.IOException{
      return new Grass(param.nextInt(),param.nextInt(),param.nextInt(),param.nextInt()); // x, y, w, h
   }
   private static Dirt constructDirt(Scanner param)throws java.io.IOException{
      return new Dirt(param.nextInt(),param.nextInt(),param.nextInt(),param.nextInt());
   }
   private static LongGrass constructLongGrass(Scanner param)throws java.io.IOException{
      return new LongGrass(param.nextInt(),param.nextInt(),param.nextInt(),param.nextInt());
   }
   private static Rock constructRock(Scanner param)throws java.io.IOException{
      return new Rock(param.nextInt(),param.nextInt(),param.nextInt(),param.nextInt());
   }
   private static Tree constructTree(Scanner param)throws java.io.IOException{
      return new Tree(param.nextInt(),param.nextInt(),param.nextInt(),param.nextInt());
   }
}