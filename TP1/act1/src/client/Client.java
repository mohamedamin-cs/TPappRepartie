package client;

import java.io.IOException;
import java.net.Socket;

public class Client {
  public static void main(String[] args) {
    try {
      System.out.println("connection...");
      String ip_serveur = "127.0.0.1";
      int port = 8080;
      Socket socket = new Socket(ip_serveur, port);
      System.out.println("connecté!");
      socket.close();
      System.out.println("connection fermé");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
