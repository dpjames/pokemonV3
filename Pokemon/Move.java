public class Move{
   private String name;
   private int baseDamage;
   public Move(String name){
      this.name = name;
   }
   public String toString(){
      return name;
   }
   public int getDamage(){
      return baseDamage;
   }
   public void setDamage(int d){
      this.baseDamage = d;
   }
}