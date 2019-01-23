package Metier;

public class Constante extends Formule_Atomique {

    public boolean valeur;

    public Constante(String idf, boolean valeur) {
        super(idf);
        this.valeur=valeur;
    }


}
