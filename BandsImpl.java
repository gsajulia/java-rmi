import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BandsImpl extends UnicastRemoteObject implements Bands {

    private ArrayList<Banda> base = new ArrayList<Banda>();

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
        System.out.println("Criou banda");
        return "Criou banda!!!";
    }

    public ArrayList<Banda> readJson() {
        ArrayList<Banda> list = new ArrayList<Banda>();
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        JSONArray songs = new JSONArray();
        JSONArray bands = new JSONArray();

        try {
            ArrayList<Musica> listsongs;

            try {
                jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/banco.json"));
                bands = (JSONArray) jsonObject.get("bandas");
            } catch (Exception e) {
                FileWriter file = new FileWriter("src/banco.json");
            }

            for (int i = 0; i < bands.size(); i++) {
                listsongs = new ArrayList<Musica>();
                JSONObject band = (JSONObject) bands.get(i);
                songs = (JSONArray) band.get("musicas");
                for (int j = 0; j < songs.size(); j++) {
                    JSONObject song = (JSONObject) songs.get(j);
                    listsongs.add(new Musica((String) song.get("nome"), (String) song.get("album")));
                }
                list.add(new Banda((String) band.get("nome"), listsongs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(list.size());

        return list;
    }

}
