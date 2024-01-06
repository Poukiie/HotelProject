package hotel;

import commande.CommandeRepas;

import java.io.Serializable;

public class Facture implements Serializable {
    private final Client client;
    private final String sommeTotale;

    public Facture(Client client) {
        this.client = client;
        this.sommeTotale = calculSommeFacture();
    }

    public String calculSommeFacture() {
        // somme du prix de la chambre * nb de nuits
        double sommeChambre = this.client.getReservation()
                .getChambreReservee().getPrixNuit() * this.client.getReservation().getNbNuits();
        double sommeRepas = 0;
        // et du prix des repas
        for (CommandeRepas commande : this.client.getCommandes()) {
            sommeRepas += commande.getSommeCommande();
        }
        // arrêter à 2 chiffres après la virgule
        double total = sommeChambre + sommeRepas;
        return String.format("%.2f", total);
    }

    public String toString() {
        StringBuilder facture = new StringBuilder();

        facture.append("Facture pour le client ").append(this.client.getNom()).append(" :\n");
        if (this.client.getReservation() != null) {
            facture.append("Réservation : ").append(this.client.getReservation().toString()).append("\n");
            // total commande
            facture.append("Total : ").append(this.client.getReservation()
                    .getChambreReservee().getPrixNuit() * this.client.getReservation().getNbNuits()).append("€\n");
        } else {
            facture.append("Aucune réservation en cours.\n");
        }
        if (!this.client.getCommandes().isEmpty()) {
            facture.append("Commandes :\n");
            for (CommandeRepas commande : this.client.getCommandes()) {
                facture.append(commande.toString()).append("\n");
            }
        } else {
            facture.append("Aucune commande enregistrée.\n");
        }
        facture.append("Total : ").append(this.sommeTotale).append("€");
        return facture.toString();
    }
}
