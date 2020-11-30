import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
      base = readJson();
      try {
         Bands obj = new BandsImpl();
         System.setProperty("java.rmi.server.hostname", "127.0.0.1");
         // var reg = LocateRegistry.getRegistry(1099); //ai se tirar a porta do client
         // tem que tirar essa linha
         var reg = LocateRegistry.createRegistry(1099); // ai se tirar a porta do client tem que tirar essa linha
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

      System.out.println("Base de dados lida com sucesso.");

      return list;
   }

   public ArrayList<Banda> getBase() {
      return this.base;
   }

   static void writeJson() {
      Banda band;
      JSONObject obj = new JSONObject();
      JSONArray jsBands = new JSONArray();
      try {
         File f = new File("banco.json");
         if (f.exists()) {
            f.delete();
         }
      } catch (Exception e) {
         System.err.println(e);
      }

      for (int i = 0; i < base.size(); i++) {
         JSONObject jsBand = new JSONObject();
         JSONArray jsSongs = new JSONArray();
         band = base.get(i);
         jsBand.put("nome", band.getNome());
         for (int j = 0; j < band.getMusicas().size(); j++) {
            JSONObject jsSong = new JSONObject();
            jsSong.put("nome", band.getMusicas().get(j).getNome());
            jsSong.put("album", band.getMusicas().get(j).getAlbum());
            jsSongs.add(jsSong);
         }
         jsBand.put("musicas", jsSongs);
         jsBands.add(jsBand);
      }

      obj.put("bandas", jsBands);
      try (FileWriter file = new FileWriter("banco.json")) {
         file.write(obj.toJSONString());
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public boolean deleteBand(String info) {
      boolean response = false;
      System.out.println("Deletando bandas com nome de: " + info);
      for (int i = 0; i < base.size(); i++) {
         if (base.get(i).getNome().equals(info)) {
            base.remove(i);
            response = true;
         }
      }
      writeJson();
      return response;
   }

   public boolean createBand(String nome) {
      System.out.println("Criando bandas com nome de" + nome);
      base.add(new Banda(nome, new ArrayList<Musica>()));
      writeJson();
      return true;
   }

   public  String findBand(String info) {
      System.out.println("Buscando bandas com nome de" + info);
      Banda band = null;
      String response = "";
      for (int i = 0; i < base.size(); i++) {
         if (base.get(i).getNome().equals(info)) {
            band = base.get(i);
         }
      }

      if (band != null) {
         JSONObject jsBand = new JSONObject();
         JSONArray jsSongs = new JSONArray();
         jsBand.put("nome", band.getNome());
         for (int j = 0; j < band.getMusicas().size(); j++) {
            JSONObject jsSong = new JSONObject();
            jsSong.put("nome", band.getMusicas().get(j).getNome());
            jsSong.put("album", band.getMusicas().get(j).getAlbum());
            jsSongs.add(jsSong);
         }
         jsBand.put("musicas", jsSongs);
         response = jsBand.toJSONString();
      } else {
         response = "null";
      }

      return response;
   }

   public String ReturnJsonToStringBands() {
      JSONObject jsonObject = new JSONObject();
      JSONParser jsonParser = new JSONParser();
      try {
         jsonObject = (JSONObject) jsonParser.parse(new FileReader("banco.json"));
      } catch (Exception e) {
         System.out.println(e);
      }

      return jsonObject.toJSONString();
   }

}