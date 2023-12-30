package commande;

public class Plat {
    private final String nomPlat;
    private final double prix;

    public Plat(String nomPlat, double prix) {
        this.nomPlat = nomPlat;
        this.prix = prix;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public double getPrix() {
        return prix;
    }

    public String toString() {
        return this.nomPlat + " (" + this.prix + "€)";
    }
}
