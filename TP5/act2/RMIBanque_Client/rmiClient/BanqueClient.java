package rmiClient;

import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import metier.Compte;
import rmiService.IBanque;

public class BanqueClient {
  public static void main(String[] args) {
    try {
      Context ctx = new InitialContext();
      IBanque banque = (IBanque) ctx.lookup("banqueService");

      Compte c1 = new Compte(20261, 1500.0, new Date());
      Compte c2 = new Compte(20262, 4500.75, new Date());

      System.out.println(banque.creerCompte(c1));
      System.out.println(banque.creerCompte(c2));

      Compte c3 = new Compte(20261, 300.0, new Date());
      System.out.println(banque.creerCompte(c3));

      System.out.println(banque.getInfoCompte(20261));
      System.out.println(banque.getInfoCompte(20262));
      System.out.println(banque.getInfoCompte(99999));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
