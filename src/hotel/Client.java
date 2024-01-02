package hotel;

import chambres.Chambre;
import commande.CommandeRepas;
import commande.Plat;
import exception.ChambreDejaReserveeException;

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
            throws ChambreDejaReserveeException {
        if (!chambre.getEstAttribuee()) {
            this.reservation = new Reservation(dateDebut, dateFin, nbNuits, chambre);
            chambre.setEstAttribuee(true);
        }
        else {
            throw new ChambreDejaReserveeException("La chambre est déjà réservée.");
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
    public void annulerReservation() {
        this.reservation.setEstAnnulee(true);
        this.reservation.getChambreReservee().setEstAttribuee(false);
    }

    public void supprimerReservation() {
        this.reservation = null;
    }

    public void commanderRepas(Plat plat, int quantite) {
        CommandeRepas commandeRepas = new CommandeRepas();

        for (int i = 0; i < quantite; i++) {
            commandeRepas.ajouterPlat(plat);
        }

        this.commandes.add(commandeRepas);

        System.out.println("Commande de repas passée avec succès pour le client " + this.nom);
    }

    public String toString() {
        return "Nom : " + this.nom + "\n" +
                "Réservation : " + (this.reservation != null ? this.reservation : "Aucune réservation") + "\n" +
                "Commandes : " + this.commandes + "\n";
    }
}
