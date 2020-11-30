import java.rmi.Naming;
import java.util.Scanner;
import java.rmi.*;
import java.rmi.registry.*;

public class BandsClient {
   public static void main(String[] args) {
      try {
         int option;
         String data, response;
         Scanner scanner = new Scanner(System.in);
         boolean status;

         System.out.println("Opcoes\n\n 1 - Listar todos\n 2 - Excluir \n 3 - Adicionar Banda \n 4 - Buscar");
         option =1;// scanner.nextInt();
         // scanner.nextLine();

         if (option == 2) {
            System.out.println("Digite o nome da banda que deseja excluir:");
         } else if (option == 3) {
            System.out.println("Digite o nome da banda que deseja adicionar:");
         } else if (option == 4) {
            System.out.println("Digite o nome da banda que deseja buscar:");
         }

         // data = scanner.nextLine();

         System.out.println("LOOKUP");
         var reg = LocateRegistry.getRegistry(1099); //ai se tirar a porta do client tem que tirar essa linha
         Bands obj = (Bands)reg.lookup("Bands"); //da pra tirar o :1099
         System.out.println(obj);
         System.out.println("RECEIVED");
         var res = obj.createBand();
         System.out.println("CREATED, RES: " + res);

         // Bands obj = (Bands)Naming.lookup("//" + args[0] + "/Bands"); 

         switch (option) {
            case 1:
               // jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/banco.json"));
               // String response;
               // response = jsonObject.toJSONString();

               // System.out.println(response);
               break;
            case 2:
               // status = deleteBand(data, base);

               // if (status) {
               // System.out.println("Deletado com sucesso!");
               // } else {
               // System.out.println("Não pode ser deletado!");
               // }
               break;
            case 3:
               // status = createBand(data, base);
               System.out.println("sdauhdsauds");
               response = obj.createBand();


               break;
            case 4:
               // String searchResponse = findBand(data, base);
               // System.out.println(searchResponse);
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
}
