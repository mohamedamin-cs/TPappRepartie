import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTCP {
  public static void main(String[] args) {
    String adresseServeur = "127.0.0.1";
    int port = 1234;

    try (Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress(adresseServeur, port));
      System.out.println("Connecté au serveur avec succès !");

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      String reponseServeur = in.readLine();
      System.out.println(reponseServeur);

      Scanner scanner = new Scanner(System.in);
      System.out.print("Entrez un message à envoyer au serveur : ");
      String msg = scanner.nextLine();
      out.println(msg);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
