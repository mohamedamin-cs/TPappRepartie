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
      System.out.println("Serveur démarré, en attente d'un client...");

      Socket socket = serverSocket.accept();
      System.out.println("Client connecté.");

      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

      int x;
      while (true) {
        x = dis.readInt();
        if (x == 0) {
          System.out.println("Signal d'arrêt reçu du client (0).");
          break;
        }

        System.out.println("Nombre reçu du client : " + x);

        int resultat = x * 5;

        dos.writeInt(resultat);
        dos.flush();
      }
      dis.close();
      dos.close();
      socket.close();
      serverSocket.close();
      System.out.println("Serveur arrêté.");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
