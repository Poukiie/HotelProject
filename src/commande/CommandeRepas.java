package commande;

import java.util.LinkedList;

public class CommandeRepas {
    private LinkedList<Plat> plats;
    private double sommeCommande;

    public CommandeRepas() {
        // pas sûr pour le get plats
        this.plats = getPlats();
        this.sommeCommande = 0;
    }

    public LinkedList<Plat> getPlats() {
        return plats;
    }

    public double getSommeCommande() {
        return sommeCommande;
    }

    public void ajouterPlat(Plat plat) {
        this.plats.add(plat);
        this.sommeCommande += plat.getPrix();
    }
}
