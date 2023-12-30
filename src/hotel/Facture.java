package hotel;

import commande.CommandeRepas;

public class Facture {
    private Client client;
    private double sommeTotale;

    public Facture(Client client) {
        this.client = client;
        this.sommeTotale = calculSommeFacture();
    }

    public double calculSommeFacture() {
        // somme du prix de la chambre * nb de nuits
        double sommeChambre = this.client.getReservation()
                .getChambreReservee().getPrixNuit() * this.client.getReservation().getNbNuits();
        double sommeRepas = 0;
        // et du prix des repas
        for (CommandeRepas commande : this.client.getCommandes()) {
            sommeRepas += commande.getSommeCommande();
        }
        return sommeChambre + sommeRepas;
    }
}
