package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(1234);
      System.out.println("Serveur mult แม่ (Multifonction) en attente...");

      Socket socket = serverSocket.accept();
      System.out.println("Client connecté : " + socket.getRemoteSocketAddress());

      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

      boolean continuer = true;
      while (continuer) {
        int choix = dis.readInt();

        if (choix == 5) {
          System.out.println("Déconnexion demandée par le client.");
          continuer = false;
          break;
        }

        double a = dis.readDouble();
        double b = dis.readDouble();
        double res = 0;

        switch (choix) {
          case 1:
            res = a + b;
            break;
          case 2:
            res = a - b;
            break;
          case 3:
            res = a * b;
            break;
          case 4:
            if (b != 0) {
              res = a / b;
            } else {
              res = Double.NaN;
            }
            break;
          default:
            res = 0;
            break;
        }

        dos.writeDouble(res);
        dos.flush();
        System.out
            .println("Opération " + choix + " exécutée pour les valeurs " + a + " et " + b + ". Résultat : " + res);
      }

      dis.close();
      dos.close();
      socket.close();
      serverSocket.close();
      System.out.println("Serveur arrêté proprement.");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
