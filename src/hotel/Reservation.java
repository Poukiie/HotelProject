package hotel;

import chambres.Chambre;

import java.util.Date;

public class Reservation {
    private int num;
    private Date dateDebut;
    private Date dateFin;
    private Chambre chambreReservee;
    private int nbNuits;

    public Reservation(int num, Date dateDebut, Date dateFin, Chambre chambre, Client client) {
        this.num = num;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.chambreReservee = chambre;
//        this.client = client;
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
