package chambres;

public class ChambreNormaleSimple extends Chambre {
    private final boolean balcon;
    private final boolean wifiGratuit;

    public ChambreNormaleSimple(int numero) {
        super(numero, "simple", 73.5, 1, false);
        this.balcon = false;
        this.wifiGratuit = true;
    }

    public String toString() {
        return "Chambre " + getNumero() + " (lit " + getTypeLit() + ", " + getPrixNuit() + "€ par nuit)\n" +
                "Etat : " + (getEstAttribuee() ? "attribuée" : "libre") + "\n" +
                "Nombre de douches : " + getNbDouches() + "\n" +
                "Accès balcon : oui\n" +
                "Wi-Fi gratuit: oui\n";
    }
}
