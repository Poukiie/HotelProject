package chambres;

public class ChambreLuxeDouble extends Chambre {
    private final boolean baignoireSpa;
    private final boolean equipementHautGamme;
    private final boolean serviceRepas;

    public ChambreLuxeDouble() {
        super("double", 380.90, 2, false);
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
