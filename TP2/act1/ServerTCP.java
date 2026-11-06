import java.io.*;
import java.net.*;

public class ServerTCP {
  public static void main(String[] args) {
    int port = 1234;
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Serveur en attente de connexion sur le port " + port + "...");

      try (Socket socket = serverSocket.accept()) {
        System.out.println("Client connecté : " + socket.getInetAddress());

        BufferedReader lecteur = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter ecrivain = new PrintWriter(socket.getOutputStream(), true);

        String operation = lecteur.readLine();
        System.out.println("Opération reçue : " + operation);

        double resultat = evaluerOperation(operation);

        ecrivain.println("Résultat : " + resultat);
        System.out.println("Résultat envoyé au client : " + resultat);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static double evaluerOperation(String op) {
    try {
      op = op.replaceAll("\\s+", "");

      if (op.contains("+")) {
        String[] parts = op.split("\\+");
        return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
      } else if (op.contains("-")) {
        String[] parts = op.split("-");
        return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
      } else if (op.contains("*")) {
        String[] parts = op.split("\\*");
        return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
      } else if (op.contains("/")) {
        String[] parts = op.split("/");
        double denominateur = Double.parseDouble(parts[1]);
        if (denominateur == 0)
          throw new ArithmeticException("Division par zéro");
        return Double.parseDouble(parts[0]) / denominateur;
      }
    } catch (Exception e) {
      System.out.println("Erreur de syntaxe dans l'opération : " + e.getMessage());
    }
    return 0;
  }
}
