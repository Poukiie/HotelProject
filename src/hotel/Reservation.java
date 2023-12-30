package hotel;

import chambres.Chambre;

import java.util.Date;

public class Reservation {
    private int num;
    private final Date dateDebut;
    private final Date dateFin;
    private int nbNuits;
    private final Chambre chambreReservee;

    public Reservation(Date dateDebut, Date dateFin, int nbNuits, Chambre chambre) {
        this.num++;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbNuits = nbNuits;
        this.chambreReservee = chambre;
    }

    public int getNum() {
        return num;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Chambre getChambreReservee() {
        return chambreReservee;
    }

    public int getNbNuits() {
        return nbNuits;
    }

    public void setNbNuits(int nbNuits) {
        this.nbNuits = nbNuits;
    }

}
