package commande;

import java.io.Serializable;
import java.util.LinkedList;

public class CommandeRepas implements Serializable {
    private final LinkedList<Plat> plats;
    private double sommeCommande;

    public CommandeRepas() {
        this.plats = new LinkedList<>();
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

    public String toString() {
        StringBuilder commande = new StringBuilder();
        for (Plat plat : this.plats) {
            commande.append(plat.toString()).append("\n");
        }
        commande.append("Total : ").append(this.sommeCommande).append("€");
        return commande.toString();
    }
}
