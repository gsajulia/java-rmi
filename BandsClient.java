import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.rmi.*;
import java.rmi.registry.*;

public class BandsClient {
   public static void main(String[] args) {
      ArrayList<Banda> list = new ArrayList<Banda>();
      try {
         int option = 0;
         String data = "", response;
         Scanner scanner = new Scanner(System.in);
         boolean status;

         System.out.println("LOOKUP");
         var reg = LocateRegistry.getRegistry(1099); // ai se tirar a porta do client tem que tirar essa linha
         Bands obj = (Bands) reg.lookup("Bands"); // da pra tirar o :1099
         // System.out.println(obj);
         System.out.println("Conectou");

         System.out.println("Opcoes\n\n 1 - Listar todos\n 2 - Excluir \n 3 - Adicionar Banda \n 4 - Buscar");
         option = scanner.nextInt();
         if (option == 2) {
            System.out.println("Digite o nome da banda que deseja excluir:");
            scanner.nextLine();
            data = scanner.nextLine();
         } else if (option == 3) {
            System.out.println("Digite o nome da banda que deseja adicionar:");
            scanner.nextLine();
            data = scanner.nextLine();
         } else if (option == 4) {
            System.out.println("Digite o nome da banda que deseja buscar:");
            scanner.nextLine();
            data = scanner.nextLine();
         }

         // var res = obj.createBand();
         // System.out.println("CREATED, RES: " + res);

         // Bands obj = (Bands)Naming.lookup("//" + args[0] + "/Bands");

         switch (option) {
            case 1:
               // jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/banco.json"));
               // String response;
               // response = jsonObject.toJSONString();

               // System.out.println(response);
               response = obj.listBands();
               list = convertToListJson(response);
               System.out.println("lista de músicas: ");
               list.forEach(System.out::println);

               break;
            case 2:
               if(obj.deleteBand(data)){
                  System.out.println("Banda deletada com sucesso");
               }else{
                  System.out.println("Erro ou Banda não encontrada");
               }
               break;
            case 3:
               if(obj.createBand(data)){
                  System.out.println("Banda criada com sucesso.");
               }else{
                  System.out.println("Erro ao criar banda.");
               }

               break;
            case 4:
               System.out.println(convertToListJsonBand(obj.findBand(data)).toString());
               break;
            default:
               System.out.println("Essa opção não foi encontrada.");
               break;

         }

         // System.out.println("Mensagem do Servidor: " + obj.createBand());
      } catch (Exception ex) {
         System.out.println("Exception: " + ex.getMessage());
      }
   }

   static ArrayList<Banda> convertToListJson(String response) {
      ArrayList<Banda> list = new ArrayList<Banda>();
      JSONObject jsonObject;
      JSONParser jsonParser = new JSONParser();
      JSONArray songs = new JSONArray();
      JSONArray bands = new JSONArray();

      try {
         ArrayList<Musica> listsongs;
         jsonObject = (JSONObject) jsonParser.parse(response);

         bands = (JSONArray) jsonObject.get("bandas");
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

      return list;
   }

   static Banda convertToListJsonBand(String response) {
      Banda band = new Banda();
      JSONObject jsonObject;
      JSONParser jsonParser = new JSONParser();
      JSONArray songs = new JSONArray();

      try {
          ArrayList<Musica> listsongs = new ArrayList<Musica>();
          jsonObject = (JSONObject) jsonParser.parse(response);
          songs = (JSONArray) jsonObject.get("musicas");
          for (int j = 0; j < songs.size(); j++) {
              JSONObject song = (JSONObject) songs.get(j);
              listsongs.add(new Musica((String) song.get("nome"), (String) song.get("album")));
          }
          band = new Banda(jsonObject.get("nome").toString(), listsongs);

      } catch (Exception e) {
          e.printStackTrace();
      }

      return band;
  }

}
