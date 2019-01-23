package Metier;

public class Terme {
    private Expression_Logique membreDroit;
    private Expression_Logique membreGauche;
    private Connecteur connecteur;

    public Expression_Logique getMembreDroit() {
        return membreDroit;
    }


    public Expression_Logique getMembreGauche() {
        return membreGauche;
    }

    public Connecteur getConnecteur() {
        return connecteur;
    }

}
