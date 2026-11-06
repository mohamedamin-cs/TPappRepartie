import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class ServeurUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(null)) {
            socket.bind(new InetSocketAddress(1234));
            System.out.println("Serveur UDP en attente sur le port 1234...");
            
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("[" + packet.getAddress() + ":" + packet.getPort() + "] " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
