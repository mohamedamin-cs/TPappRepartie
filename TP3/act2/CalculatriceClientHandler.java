import java.io.*;
import java.net.*;

public class CalculatriceClientHandler implements Runnable {
    private Socket socket;
    private int numeroClient;
    
    private static int compteurGlobalOperations = 0;

    public CalculatriceClientHandler(Socket socket, int numeroClient) {
        this.socket = socket;
        this.numeroClient = numeroClient;
    }

    private static synchronized void incrementerCompteur() {
        compteurGlobalOperations++;
        System.out.println("[COMPTEUR GLOBAL] Nombre total d'opérations traitées par le serveur : " + compteurGlobalOperations);
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            System.out.println("Traitement lancé pour le client n°" + numeroClient + " (" + socket.getRemoteSocketAddress()  + ")");

            Operation operation = (Operation) ois.readObject();
            System.out.println("Opération reçue du client " + numeroClient + " : " + operation.getOp1() + " " + operation.getOperateur() + " " + operation.getOp2());

            double res = 0;
            switch (operation.getOperateur()) {
                case '+': res = operation.getOp1() + operation.getOp2(); break;
                case '-': res = operation.getOp1() - operation.getOp2(); break;
                case '*': res = operation.getOp1() * operation.getOp2(); break;
                case '/': 
                    if(operation.getOp2() != 0) res = operation.getOp1() / operation.getOp2();
                    break;
            }
            operation.setResultat(res);

            incrementerCompteur();

            oos.writeObject(operation);
            System.out.println("Résultat " + res + " renvoyé au client " + numeroClient);

        } catch (Exception e) {
            System.out.println("Erreur avec le client n°" + numeroClient + " : " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
