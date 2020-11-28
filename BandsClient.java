import java.rmi.Naming;

public class BandsClient {
   public static void main(String[] args) {
      try {
         Bands obj = (Bands)Naming.lookup("//" + args[0] + "/Bands"); 
         System.out.println("Mensagem do Servidor: " + obj.hello()); 
      } catch (Exception ex) {
         System.out.println("Exception: " + ex.getMessage());
      } 
   }
}
