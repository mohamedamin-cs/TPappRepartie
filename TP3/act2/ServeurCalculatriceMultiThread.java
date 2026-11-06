import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurCalculatriceMultiThread {
    public static void main(String[] args) {
        int port = 1234;
        int clientCount = 0;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur Calculatrice Multi-thread en écoute sur le port " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                clientCount++;

                CalculatriceClientHandler handler = new CalculatriceClientHandler(socket, clientCount);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
