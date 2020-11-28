import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;

public class BandsServer extends UnicastRemoteObject implements Bands {
   public BandsServer() throws RemoteException {
      super();
   }

   public static void main(String[] args) {
      try {
      Bands obj = new BandsImpl();
         Naming.rebind("rmi://127.0.0.1/Bands", obj);
      } catch (Exception ex) {
         System.out.println("Exception: " + ex.getMessage());
      } 
   }

   public String createBand() throws RemoteException {
      System.out.println("Criou banda");
      return "Criou banda!!!";
   }

}