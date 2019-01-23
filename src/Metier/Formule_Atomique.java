package Metier;

public abstract class Formule_Atomique {
    private boolean negation = false;
    private String identifiant;

    public Formule_Atomique(String idf){
        identifiant = idf;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public boolean isNegation() {
        return negation;
    }

    public Formule_Atomique negationner(){
        negation = !negation;
        return this;
    }
}
