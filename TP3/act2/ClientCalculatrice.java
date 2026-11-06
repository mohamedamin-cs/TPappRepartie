import java.io.*;
import java.net.*;

public class ClientCalculatrice {
    public static void main(String[] args) {
        String adresseServeur = "127.0.0.1";
        int port = 1234;

        try (Socket socket = new Socket(adresseServeur, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            Operation operation = new Operation(85, '*', 4);
            oos.writeObject(operation);
            System.out.println("Objet envoyé au serveur : 85 * 4");

            Operation reponse = (Operation) ois.readObject();
            System.out.println("Résultat reçu du serveur -> " + reponse.getResultat());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
