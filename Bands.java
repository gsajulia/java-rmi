import java.rmi.*;

public interface Bands extends Remote {
   public String hello() throws RemoteException;
}


