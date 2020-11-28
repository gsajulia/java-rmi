import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;

public class BandsServer extends UnicastRemoteObject {
   public BandsServer() throws RemoteException {
      super();
   }

   public static void main(String[] args) {

      try {
      Bands obj = new BandsImpl();
      obj.readJson();
         Naming.rebind("rmi://127.0.0.1/Bands", obj);
      } catch (Exception ex) {
         System.out.println("Exception: " + ex.getMessage());
      } 
   }

}