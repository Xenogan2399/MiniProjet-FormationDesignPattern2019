package Metier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public interface Refutable {
    boolean evaluer(HashMap<String,Boolean> valeurs);
    Function<HashMap<String,Boolean>,Boolean> getEvaluator();
    Expression_Logique getExpression();
    /*Expression_Logique getMembreDroit();
    Expression_Logique getMembreGauche();
    Connecteur getConnecteur();*/
}
