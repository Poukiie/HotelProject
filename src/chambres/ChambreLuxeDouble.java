package chambres;

public class ChambreLuxeDouble extends Chambre{
    private final boolean baignoireSpa;
    private final boolean equipementHautGamme;

    public ChambreLuxeDouble() {
        super("double", 380, false);
        this.baignoireSpa = true;
        this.equipementHautGamme = true;
    }

    public boolean getBaignoireSpa() {
        return baignoireSpa;
    }

    public boolean getEquipementHautGamme() {
        return equipementHautGamme;
    }
}
