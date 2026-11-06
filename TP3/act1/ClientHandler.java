import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socketClient;
    private int numeroClient;

    public ClientHandler(Socket socketClient, int numeroClient) {
        this.socketClient = socketClient;
        this.numeroClient = numeroClient;
    }

    @Override
    public void run() {
        try {
            SocketAddress remoteAddr = socketClient.getRemoteSocketAddress();
            System.out.println("Client n°" + numeroClient + " connecté depuis l'adresse : " + remoteAddr);

            PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            out.println("Bienvenue ! Vous êtes le client n°" + numeroClient);

            String message = in.readLine();
            System.out.println("Message reçu du client n°" + numeroClient + " : " + message);

            socketClient.close();
            System.out.println("Déconnexion du client n°" + numeroClient);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
