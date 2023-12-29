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
        // somme du prix de la chambre et du prix des repas
        double sommeChambre = this.client.getReservation()
                .getChambreReservee().getPrixNuit();
        double sommeRepas = 0;
        for (CommandeRepas commande : this.client.getCommandes()) {
            sommeRepas += commande.getSommeCommande();
        }
        return 0;
    }
}
