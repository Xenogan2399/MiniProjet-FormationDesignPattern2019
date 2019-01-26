package Metier;

public abstract class Formule_Atomique extends Expression_Logique implements Atome_NE  {
    protected boolean valeur;
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

    @Override
    public boolean evaluer() {
        if(negation)
            return !valeur;
        return valeur ;
    }

    public boolean getValeur(){
        return valeur;
    }


}
