import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class BandsImpl extends UnicastRemoteObject  implements Bands{
 
    // private ArrayList<Songs> songs;
    public BandsImpl() throws RemoteException {
        super();
        // try {
        //     this.name = name;
        // } catch(Exception e) {
        //     System.out.println("Something went wrong with foo!");
        // }
        
        // this.musicas = musicas;
    }

    public String createBand() throws RemoteException {
        System.out.println("Criou banda");
        return "Criou banda!!!";
     }
}
