package hotel;

import chambres.Chambre;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nbNuits;
    private Chambre chambreReservee;
    private boolean estAnnulee = false;

    public Reservation(LocalDate dateDebut, LocalDate dateFin, Chambre chambre) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbNuits = calculerNbNuits(dateDebut, dateFin);
        this.chambreReservee = chambre;
    }

    public String toString() {
        return "Réservation du " + dateDebut + " au " + dateFin + " pour la chambre " + chambreReservee.getNumero();
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Chambre getChambreReservee() {
        return chambreReservee;
    }

    public int getNbNuits() {
        return nbNuits;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setEstAnnulee(boolean estAnnulee) {
        this.estAnnulee = estAnnulee;
    }

    public void setChambreReservee(Chambre chambreReservee) {
        this.chambreReservee = chambreReservee;
    }

    public int calculerNbNuits(LocalDate dateDebut, LocalDate dateFin) {
        return(int) ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
    }
}
