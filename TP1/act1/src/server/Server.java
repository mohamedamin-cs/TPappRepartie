package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    try {
      int port = 8080;
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("en atttente de la connexion duun client");
      Socket socket = serverSocket.accept();
      System.out.println("un clien,est connecté");
      socket.close();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
