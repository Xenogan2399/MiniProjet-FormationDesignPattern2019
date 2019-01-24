package Metier;

import java.util.function.Function;

public class Variable extends Formule_Atomique{

    public Variable(String idf) {
        super(idf);
    }

    public void setValeur(boolean val){
        valeur = val;
    }

    @Override
    public Function<Boolean[], Boolean> getCalculator() {
        return new Function<Boolean[], Boolean>() {
            @Override
            public Boolean apply(Boolean[] booleans) {
                return valeur;
            }
        };
    }
}
