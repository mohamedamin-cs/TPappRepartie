package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("127.0.0.1", 1234);
      System.out.println("Connecté au serveur de calcul.");

      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
      Scanner scanner = new Scanner(System.in);

      int x;
      do {
        System.out.print("Entrez un entier (0 pour quitter) : ");
        x = scanner.nextInt();

        dos.writeInt(x);
        dos.flush();

        if (x != 0) {
          int resultat = dis.readInt();
          System.out.println("Résultat reçu du serveur (x * 5) = " + resultat);
        }
      } while (x != 0);

      scanner.close();
      dis.close();
      dos.close();
      socket.close();
      System.out.println("Déconnexion du client.");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
