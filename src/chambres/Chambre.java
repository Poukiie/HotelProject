package chambres;

public class Chambre {
    private int numero;
    private String typeLit;
    private double prixNuit;
    private int nbDouches;
    private boolean estAttribuee;

    public Chambre(int numero, String typeLit, double prixNuit, int nbDouches, boolean estAttribuee) {
        this.numero = numero;
        this.typeLit = typeLit;
        this.prixNuit = prixNuit;
        this.nbDouches = nbDouches;
        this.estAttribuee = estAttribuee;
    }

    public int getNumero() {
        return numero;
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

    public void setEstAttribuee(boolean estAttribuee) {
        this.estAttribuee = estAttribuee;
    }

    public String toString() {
        return "Chambre " + this.numero + " (" + this.typeLit + ", " + this.prixNuit + "€ par nuit)\n" +
                "Etat : " + (this.estAttribuee ? "attribuée" : "libre") + "\n" +
                "Nombre de douches : " + this.nbDouches + "\n";
    }
}
