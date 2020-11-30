import java.rmi.*;
import java.util.ArrayList;

public interface Bands extends Remote {
   public String listBands() throws RemoteException;
   public boolean deleteBand(String nome) throws RemoteException;
   public boolean createBand(String nome) throws RemoteException;
   public String findBand(String nome) throws RemoteException;
}


