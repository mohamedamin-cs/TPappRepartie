package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import metier.Compte;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {
  private Map<Integer, Compte> comptes = new HashMap<>();

  public BanqueImpl() throws RemoteException {
    super();
  }

  @Override
  public synchronized String creerCompte(Compte c) throws RemoteException {
    if (comptes.containsKey(c.getCode())) {
      return "Échec : Le compte numéro " + c.getCode() + " existe déjà.";
    }
    comptes.put(c.getCode(), c);
    return "Succès : Compte " + c.getCode() + " créé avec succès.";
  }

  @Override
  public synchronized String getInfoCompte(int code) throws RemoteException {
    Compte c = comptes.get(code);
    if (c == null) {
      return "Erreur : Aucun compte trouvé avec le code " + code;
    }
    return c.toString();
  }
}
