package hotel;

import chambres.Chambre;
import commande.CommandeRepas;
import exception.ClientAlreadyHasReservationException;

import java.time.LocalDate;
import java.util.LinkedList;

public class Client {
    private final String nom;
    private final LinkedList<CommandeRepas> commandes;
    private Reservation reservation;

    public Client(String nom, Reservation reservation) {
        this.nom = nom;
        this.reservation = reservation;
        this.commandes = new LinkedList<>();
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

    public void reserver(Chambre chambre, int nbNuits, LocalDate dateDebut, LocalDate dateFin)
            throws ClientAlreadyHasReservationException {
        // TODO: peut-être à faire autre part pour vérifier qu'un client a déjà une réservation
        if (this.reservation == null) {
            this.reservation = new Reservation(dateDebut, dateFin, nbNuits, chambre);
            chambre.setEstAttribuee(true);
        }
        else {
            throw new ClientAlreadyHasReservationException("Le client a déjà une réservation");
        }
        if (chambre.getEstAttribuee()) {
            System.out.println("La chambre est déjà attribuée");
            // TODO : gérer le cas où la chambre est déjà attribuée
        }
    }

    // modifier une réservation (à revoir)
    public void modifierReservation(LocalDate dateDebut, LocalDate dateFin, int nbNuits, Chambre chambre) {
        this.reservation.setDateDebut(dateDebut);
        this.reservation.setDateFin(dateFin);
        this.reservation.setNbNuits(nbNuits);
        this.reservation.setChambreReservee(chambre);
    }

    // Annuler une reservation :
    // Passer l'état de la réservation à "annulé" (boolean dans la classe Reservation)
    // et en garder une trace dans le fichier
    // Mais le client n'a plus sa réservation il doit en refaire une nouvelle
    // La chambre redevient également libre

    // Supprimer une reservation :
    // Supprimer l'objet Réservation, et cette réservation supprimée n'apparaitra pas dans le fichier
    public void annulerSupprimerReservation() {
        this.reservation.setEstAnnulee(true);
        this.reservation.getChambreReservee().setEstAttribuee(false);
        this.reservation = null;
    }

    public void supprimerReservation() {
        this.reservation = null;
    }

    public void commander(CommandeRepas commande) {
        this.commandes.add(commande);
    }

    public String toString() {
        return "Client " + this.nom + "\n" +
                "Réservation : " + this.reservation + "\n" +
                "Commandes : " + this.commandes + "\n";
    }
}
