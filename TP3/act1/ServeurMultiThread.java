import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMultiThread {
    public static void main(String[] args) {
        int port = 1234;
        int compteurClients = 0;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur multi-thread démarré sur le port " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                compteurClients++;

                ClientHandler clientHandler = new ClientHandler(socket, compteurClients);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
