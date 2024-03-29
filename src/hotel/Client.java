package hotel;

import chambres.Chambre;
import commande.CommandeRepas;
import commande.Plat;
import exception.ChambreNonDisponible;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

public class Client implements Serializable {
    private final String nom;
    private LinkedList<CommandeRepas> commandes;
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

    public void reserver(Chambre chambre, LocalDate dateDebut, LocalDate dateFin)
            throws ChambreNonDisponible {
        if (verifierDisponibilite(chambre, dateDebut, dateFin)) {
            // ex: du 15 au 17 = 3 nuits (cette fonction exclut la date de fin)
            int nbNuits = (int) ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
            this.reservation = new Reservation(dateDebut, dateFin, nbNuits, chambre);
            chambre.setEstAttribuee(true);

            // rendre la chambre occupée pour les dates choisies
            for (int i = 0; i < nbNuits; i++) {
                LocalDate date = dateDebut.plusDays(i);
                chambre.setDisponibilites(date, false);
            }
            System.out.println("Réservation effectuée avec succès.");
        } else {
            throw new ChambreNonDisponible("Chambre non disponible pour les dates choisies.");
        }
    }

    private static boolean verifierDisponibilite(Chambre chambre, LocalDate dateDebut, LocalDate dateFin) {
        try {
            // || date.isEqual(dateFin) pour inclure la date de fin
            for (LocalDate date = dateDebut; !date.isAfter(dateFin) || date.isEqual(dateFin); date = date.plusDays(1)) {
                // chambre pas dispo à cette date
                if (!chambre.getDisponibilite(date)) {
                    return false;
                }
            }
            return true; // si la chambre est dispo toutes les nuits
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void modifierReservation(LocalDate nouvelleDateDebut, LocalDate nouvelleDateFin, Chambre nouvelleChambre)
            throws ChambreNonDisponible {
        // Vérifier dispo pour les nouvelles dates ou vérifier que les dates ne changent pas
        if (verifierDisponibilite(nouvelleChambre, nouvelleDateDebut, nouvelleDateFin) ||
                (nouvelleDateDebut.isEqual(this.reservation.getDateDebut()) ||
                        nouvelleDateFin.isEqual(this.reservation.getDateFin()))) {

            // libérer les anciennes dates
            LocalDate dateDebutAvant = this.reservation.getDateDebut();
            LocalDate dateFinAvant = this.reservation.getDateFin();

            for (LocalDate date = dateDebutAvant; !date.isAfter(dateFinAvant)
                    || date.isEqual(dateFinAvant); date = date.plusDays(1)) {
                // s'il change de chambre il faut libérer l'ancienne
                if (nouvelleChambre.getNumero() != this.reservation.getChambreReservee().getNumero()) {
                    this.reservation.getChambreReservee().setDisponibilites(date, true);
                    // si personne d'autre n'a réservé la chambre, la rendre disponible
                    if (this.reservation.getChambreReservee().getDisponibilites().values().stream().allMatch(b -> b)) {
                        this.reservation.getChambreReservee().setEstAttribuee(false);
                    }
                } else {
                    nouvelleChambre.setDisponibilites(date, true);
                }
            }

            this.reservation.setChambreReservee(nouvelleChambre);
            this.reservation.getChambreReservee().setEstAttribuee(true);
            // Maj avec nouvelles dates
            this.reservation.setDateDebut(nouvelleDateDebut);
            this.reservation.setDateFin(nouvelleDateFin);
            this.reservation.setNbNuits((int) ChronoUnit.DAYS.between(nouvelleDateDebut, nouvelleDateFin) + 1);

            // Maj de la dispo pour les nuits pour la nouvelle réservation
            for (LocalDate date = nouvelleDateDebut; !date.isAfter(nouvelleDateFin); date = date.plusDays(1)) {
                nouvelleChambre.setDisponibilites(date, false);
            }

            System.out.println("Réservation modifiée avec succès.");
        } else {
            throw new ChambreNonDisponible("Chambre non disponible pour les nouvelles dates choisies.");
        }
    }

    // Annuler une reservation :
    // Passer l'etat de la reservation à "annule" et en garder une trace dans le fichier
    // Mais on supprime la reservation du client, il doit en refaire une nouvelle
    public void annulerReservation() {
        this.reservation.setEstAnnulee(true);
    }

    public void supprimerReservation() {
        LocalDate dateDebut = this.reservation.getDateDebut();
        LocalDate dateFin = this.reservation.getDateFin();
        for (LocalDate date = dateDebut; !date.isAfter(dateFin) || date.isEqual(dateFin); date = date.plusDays(1)) {
            this.reservation.getChambreReservee().setDisponibilites(date, true);
        }
        // si personne d'autre n'a réservé la chambre, la rendre disponible
        if (this.reservation.getChambreReservee().getDisponibilites().values().stream().allMatch(b -> b)) {
            this.reservation.getChambreReservee().setEstAttribuee(false);
        }
        this.commandes.clear();
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

    // Récupérer les commandes passées et vider la liste
    public LinkedList<CommandeRepas> getCommandesPassees() {
        LinkedList<CommandeRepas> commandesPassees = new LinkedList<>(this.commandes);
        this.commandes = new LinkedList<>();
        return commandesPassees;
    }

    public String toString() {
        return "Nom : " + this.nom + "\n" +
                "Réservation : " + (this.reservation != null ? this.reservation : "Aucune réservation") + "\n" +
                "Commandes : " + this.commandes + "\n";
    }
}
