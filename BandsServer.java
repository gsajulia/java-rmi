import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.rmi.registry.LocateRegistry;

public class BandsServer extends UnicastRemoteObject {
   private static ArrayList<Banda> base = new ArrayList<Banda>();

   public BandsServer() throws RemoteException {
      super();
   }

   public static void main(String[] args) {
      // base = readJson();
      try {
         Bands obj = new BandsImpl();
         System.setProperty("java.rmi.server.hostname", "127.0.0.1");
         // var reg = LocateRegistry.getRegistry(1099); //ai se tirar a porta do client tem que tirar essa linha
         var reg = LocateRegistry.createRegistry(1099); //ai se tirar a porta do client tem que tirar essa linha
         System.out.println("Listening");
         reg.bind("Bands", obj);
         System.out.println("Bind");
      } catch (Exception ex) {
         System.out.println("Exception: " + ex.getMessage());
      }
   }

   public static ArrayList<Banda> readJson() {
      ArrayList<Banda> list = new ArrayList<Banda>();
      JSONObject jsonObject;
      JSONParser jsonParser = new JSONParser();
      JSONArray songs = new JSONArray();
      JSONArray bands = new JSONArray();

      try {
         ArrayList<Musica> listsongs;

         try {
            jsonObject = (JSONObject) jsonParser.parse(new FileReader("banco.json"));
            bands = (JSONArray) jsonObject.get("bandas");
         } catch (Exception e) {
            FileWriter file = new FileWriter("banco.json");
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