import java.rmi.*;
import java.util.ArrayList;

public interface Bands extends Remote {
   public String createBand() throws RemoteException;
}


