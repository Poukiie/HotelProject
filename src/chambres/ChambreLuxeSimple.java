package chambres;

public class ChambreLuxeSimple extends Chambre {
    private final boolean baignoireSpa;
    private final boolean equipementHautGamme;
    private final boolean serviceRepas;

    public ChambreLuxeSimple(int numero) {
        super(numero, "simple", 280.90, 2, false);
        this.baignoireSpa = true;
        this.equipementHautGamme = true;
        this.serviceRepas = true;
    }

    public boolean getBaignoireSpa() {
        return baignoireSpa;
    }

    public boolean getEquipementHautGamme() {
        return equipementHautGamme;
    }

    public boolean getServiceRepas() {
        return serviceRepas;
    }

    public String toString() {
        return "Chambre " + getNumero() + " (lit" + getTypeLit() + ", " + getPrixNuit() + "€ par nuit)\n" +
                "Etat : " + (getEstAttribuee() ? "attribuée" : "libre") + "\n" +
                "Nombre de douches : " + getNbDouches() + "\n" +
                "Baignoire spa : oui\n" +
                "Equipement haut de gamme : oui\n" +
                "Service repas : oui\n";
    }

}
