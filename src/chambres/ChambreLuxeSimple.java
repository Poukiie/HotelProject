package chambres;

public class ChambreLuxeSimple extends Chambre {
    private final boolean baignoireSpa;
    private final boolean equipementHautGamme;
    private final boolean serviceRepas;

    public ChambreLuxeSimple() {
        super("simple", 280.90, 2, false);
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

}
