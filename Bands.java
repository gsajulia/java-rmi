import java.rmi.*;

public interface Bands extends Remote {
   public String createBand() throws RemoteException;
}


