package chambres;

public class ChambreNormaleDouble extends Chambre {
    private final boolean balcon;
    private final boolean wifiGratuit;

    public ChambreNormaleDouble(int numero) {
        super(numero, "double", 105.30, 1, false);
        this.balcon = false;
        this.wifiGratuit = true;
    }

    public boolean getBalcon() {
        return balcon;
    }

    public boolean getWifiGratuit() {
        return wifiGratuit;
    }

    public String toString() {
        return "Chambre " + getNumero() + " (lit " + getTypeLit() + ", " + getPrixNuit() + "€ par nuit)\n" +
                "Etat : " + (getEstAttribuee() ? "attribuée" : "libre") + "\n" +
                "Nombre de douches : " + getNbDouches() + "\n" +
                "Accès balcon : oui\n" +
                "Wi-Fi gratuit: oui\n";
    }
}
