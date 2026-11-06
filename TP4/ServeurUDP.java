import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

public class ServeurUDP {
  public static void main(String[] args) {
    Set<SocketAddress> clients = new HashSet<>();
    try (DatagramSocket socket = new DatagramSocket(null)) {
      socket.bind(new InetSocketAddress(1234));
      System.out.println("Serveur UDP multi-clients démarré sur le port 1234.");

      byte[] buffer = new byte[1024];
      while (true) {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        SocketAddress clientAddress = packet.getSocketAddress();
        clients.add(clientAddress);

        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println("[" + clientAddress + "] " + message);

        for (SocketAddress client : clients) {
          if (!client.equals(clientAddress)) {
            DatagramPacket sendPacket = new DatagramPacket(
                packet.getData(), packet.getLength(),
                ((InetSocketAddress) client).getAddress(),
                ((InetSocketAddress) client).getPort());
            socket.send(sendPacket);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
