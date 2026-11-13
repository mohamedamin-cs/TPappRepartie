package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConversionImpl extends UnicastRemoteObject implements IConversion {

  public ConversionImpl() throws RemoteException {
    super();
  }

  @Override
  public double convertirMontant(double mt) throws RemoteException {
    double taux = 3.3;
    return mt * taux;
  }
}
