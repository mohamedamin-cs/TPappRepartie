package rmiClient;

import java.rmi.Naming;
import rmiService.IConversion;

public class ConversionClient {
    public static void main(String[] args) {
        try {
            IConversion stub = (IConversion) Naming.lookup("rmi://localhost:1099/CONVERSION");
            double montantEuros = 500.0;
            double resultat = stub.convertirMontant(montantEuros);
            System.out.println("Montant initial : " + montantEuros + " EUR");
            System.out.println("Montant converti : " + resultat + " TND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
