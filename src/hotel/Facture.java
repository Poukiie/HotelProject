package hotel;

import commande.CommandeRepas;

import java.io.Serializable;
import java.util.LinkedList;

public class Facture implements Serializable {
    private final Client client;
    private final Reservation reservation;
    private final LinkedList<CommandeRepas> commandes;
    private final String sommeTotale;

    public Facture(Client client, LinkedList<CommandeRepas> commandes) {
        this.client = client;
        this.reservation = client.getReservation();
        this.commandes = commandes;
        this.sommeTotale = calculSommeFacture();
    }

    private String calculSommeFacture() {
        // somme du prix de la chambre * nb de nuits
        double sommeChambre = this.reservation
                .getChambreReservee().getPrixNuit() * this.reservation.getNbNuits();
        double sommeRepas = 0;
        // et du prix des repas
        for (CommandeRepas commande : this.commandes) {
            sommeRepas += commande.getSommeCommande();
        }
        // arrêter à 2 chiffres après la virgule
        double total = sommeChambre + sommeRepas;
        return String.format("%.2f", total);
    }

    public String toString() {
        StringBuilder facture = new StringBuilder();

        facture.append("Facture pour le client ").append(this.client.getNom()).append(" :\n");
        if (this.reservation != null) {
            facture.append("Réservation : ").append(this.reservation).append("\n");
            // total commande
            facture.append("Total : ").append(this.reservation
                    .getChambreReservee().getPrixNuit() * this.reservation.getNbNuits()).append("€\n");
        } else {
            facture.append("Aucune réservation en cours.\n");
        }
        if (!this.commandes.isEmpty()) {
            facture.append("Commandes :\n");
            for (CommandeRepas commande : this.commandes) {
                facture.append(commande.toString()).append("\n");
            }
        } else {
            facture.append("Aucune commande enregistrée.\n");
        }
        facture.append("Total : ").append(this.sommeTotale).append("€");
        return facture.toString();
    }
}
