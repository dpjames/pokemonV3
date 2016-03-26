import java.util.*;
public class Party{
   private ArrayList<Pokemon> pkmnList;
   public Party(ArrayList<Pokemon> pkmnList){
      this.pkmnList = pkmnList;
   }
   public ArrayList<Pokemon> getPokemon(){
      return pkmnList;
   }
}