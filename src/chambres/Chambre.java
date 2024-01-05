package chambres;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Chambre {
    private final int numeroChambre;
    private final String typeLit;
    private final double prixNuit;
    private final int nbDouches;
    private boolean estAttribuee;
    private Map<LocalDate, Boolean> disponibilites;

    public Chambre(int numeroChambre, String typeLit, double prixNuit, int nbDouches, boolean estAttribuee) {
        this.numeroChambre = numeroChambre;
        this.typeLit = typeLit;
        this.prixNuit = prixNuit;
        this.nbDouches = nbDouches;
        this.estAttribuee = estAttribuee;
        this.disponibilites = new HashMap<>();
    }

    public int getNumero() {
        return numeroChambre;
    }

    public String getTypeLit() {
        return typeLit;
    }

    public double getPrixNuit() {
        return prixNuit;
    }

    public boolean getEstAttribuee() {
        return estAttribuee;
    }

    public int getNbDouches() {
        return nbDouches;
    }

    public Map<LocalDate, Boolean> getDisponibilites() {
        return disponibilites;
    }

    public void setEstAttribuee(boolean estAttribuee) {
        this.estAttribuee = estAttribuee;
    }

    public String toString() {
        return "Chambre " + this.numeroChambre + " (lit " + this.typeLit + ", " + this.prixNuit + "€ par nuit)\n" +
                "Etat : " + (this.estAttribuee ? "attribuée" : "libre") + "\n" +
                "Nombre de douches : " + this.nbDouches + "\n";
    }

    public boolean getDisponibilite (LocalDate date) {
        return !disponibilites.containsKey(date) || disponibilites.get(date);
    }

    public void setDisponibilites(LocalDate date, boolean disponible) {
        disponibilites.put(date, disponible);
    }
}
