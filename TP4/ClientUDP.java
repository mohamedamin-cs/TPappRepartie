import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.print("Entrez votre nom d'utilisateur : ");
            String username = scanner.nextLine();
            
            InetAddress serveurIP = InetAddress.getByName("127.0.0.1");
            int port = 1234;
            
            System.out.println("Tapez vos messages (tapez 'exit' pour quitter) :");
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }
                
                String messageComplet = "[" + username + "]: " + input;
                byte[] buffer = messageComplet.getBytes();
                
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveurIP, port);
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
