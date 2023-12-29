package hotel;

import commande.CommandeRepas;

import java.util.LinkedList;

public class Client {
    private int num;
    private String nom;
    private LinkedList<CommandeRepas> commandes;
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

    // add
}
