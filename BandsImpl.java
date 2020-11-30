import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BandsImpl extends UnicastRemoteObject implements Bands {

    BandsServer data = new BandsServer();

    // private ArrayList<Songs> songs;
    public BandsImpl() throws RemoteException {
        super();
        System.out.println("Cstr Bands aft super");
        // try {
        // this.name = name;
        // } catch(Exception e) {
        // }

        // this.musicas = musicas;
    }

    public boolean createBand(String nome) throws RemoteException {
        return data.createBand(nome);
    }

    public String listBands() throws RemoteException{
        return data.ReturnJsonToStringBands();
    }
    public boolean deleteBand(String nome) throws RemoteException{
        return data.deleteBand(nome);
    }

    public String findBand(String nome) throws RemoteException{
        return data.findBand(nome);
    }

    
}
