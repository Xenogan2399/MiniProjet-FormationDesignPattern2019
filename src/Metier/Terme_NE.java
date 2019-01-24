package Metier;

public class Expression_Logique_NE extends Expression_Logique {
    private Expression_Logique_NE membreDroit;
    private Expression_Logique_NE membreGauche;
    private Connecteurs_Base connecteur;

    public Expression_Logique_NE(Expression_Logique_NE membreDroit, Expression_Logique_NE membreGauche, Connecteurs_Base connecteur) {
        this.membreDroit = membreDroit;
        this.membreGauche = membreGauche;
        this.connecteur = connecteur;
    }



}
