package chambres;

public class Chambre {
    private int numero;
    private String typeLit;
    private double prixNuit;
    private boolean estAttribuee;

    public Chambre(String typeLit, double prixNuit, boolean estAttribuee) {
        // auto increment numero
        this.numero = 1;
        this.typeLit = typeLit;
        this.prixNuit = prixNuit;
        this.estAttribuee = estAttribuee;
        this.numero++;
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
}
