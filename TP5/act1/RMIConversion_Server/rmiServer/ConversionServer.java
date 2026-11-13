package rmiServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import rmiService.ConversionImpl;
import rmiService.IConversion;

public class ConversionServer {
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      IConversion od = new ConversionImpl();
      Naming.rebind("rmi://localhost:1099/CONVERSION", od);
      System.out.println("Serveur RMI de conversion démarré...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
