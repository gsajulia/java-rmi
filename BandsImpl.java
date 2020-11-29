import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BandsImpl extends UnicastRemoteObject implements Bands {

    // private ArrayList<Songs> songs;
    public BandsImpl() throws RemoteException {
        super();
        // try {
        // this.name = name;
        // } catch(Exception e) {
        // System.out.println("Something went wrong with foo!");
        // }

        // this.musicas = musicas;
    }

    public String createBand() throws RemoteException {
        return "Criou banda!!!";
    }

    
}
