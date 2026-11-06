package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    try {
      // Pour tester sur deux machines distinctes, remplacer "127.0.0.1" par l'IP du
      // serveur (ex: "192.168.1.X")
      String ipServeur = "127.0.0.1";
      int port = 1234;

      Socket socket = new Socket(ipServeur, port);
      System.out.println("Connecté au serveur distant avec succès.");

      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
      Scanner scanner = new Scanner(System.in);

      int choix;
      do {
        System.out.println("\n--- MENU CALCULATRICE ---");
        System.out.println("1. Addition (+)");
        System.out.println("2. Soustraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Quitter");
        System.out.print("Choisissez une option : ");
        choix = scanner.nextInt();

        dos.writeInt(choix);
        dos.flush();

        if (choix == 5) {
          System.out.println("Fermeture de l'application client...");
          break;
        }

        if (choix >= 1 && choix <= 4) {
          System.out.print("Entrez le premier nombre (a) : ");
          double a = scanner.nextDouble();
          System.out.print("Entrez le deuxième nombre (b) : ");
          double b = scanner.nextDouble();

          dos.writeDouble(a);
          dos.writeDouble(b);
          dos.flush();

          double resultat = dis.readDouble();
          System.out.println(">> Résultat du serveur = " + resultat);
        } else {
          System.out.println("Option invalide, veuillez réessayer.");
        }

      } while (choix != 5);

      scanner.close();
      dis.close();
      dos.close();
      socket.close();
      System.out.println("Client déconnecté.");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
