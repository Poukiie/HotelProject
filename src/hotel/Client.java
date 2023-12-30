package hotel;

import chambres.Chambre;
import commande.CommandeRepas;

import java.util.Date;
import java.util.LinkedList;

public class Client {
    private final int num;
    private final String nom;
    private final LinkedList<CommandeRepas> commandes;
    private Reservation reservation;

    public Client(int num, String nom, Reservation reservation) {
        this.num = num;
        this.nom = nom;
        this.reservation = reservation;
        this.commandes = new LinkedList<>();
    }

    public int getNum() {
        return num;
    }

    public String getNom() {
        return nom;
    }

    public LinkedList<CommandeRepas> getCommandes() {
        return commandes;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void reserver(Chambre chambre, int nbNuits, Date dateDebut, Date dateFin) {
        if (this.reservation != null) {
            System.out.println("Le client a déjà une réservation");
            return;
        }
        if (chambre.getEstAttribuee()) {
            System.out.println("La chambre est déjà attribuée");
            return;
        }

        this.reservation = new Reservation(dateDebut, dateFin, nbNuits, chambre);
        chambre.setEstAttribuee(true);
    }

    public void commander(CommandeRepas commande) {
        this.commandes.add(commande);
    }

    // modifier une réservation
    // annuler une reservation
    // supprimer une reservation


}
