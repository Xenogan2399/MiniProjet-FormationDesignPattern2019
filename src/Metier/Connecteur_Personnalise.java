package Metier;

public class Connecteur_Personnalise implements Connecteur {
    private Expression_Logique_NE expression_semantique;

    @Override
    public boolean evaluer(Expression_Logique A, Expression_Logique B) {
        return false;
    }

    @Override
    public boolean evaluer(Expression_Logique A) {
        return false;
    }
}
