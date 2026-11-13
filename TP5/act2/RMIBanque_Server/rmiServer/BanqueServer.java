package rmiServer;

import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import rmiService.BanqueImpl;
import rmiService.IBanque;

public class BanqueServer {
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      IBanque banque = new BanqueImpl();
      Context ctx = new InitialContext();
      ctx.rebind("banqueService", banque);
      System.out.println("Serveur RMI/JNDI de Banque démarré...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
