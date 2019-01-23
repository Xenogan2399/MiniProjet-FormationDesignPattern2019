package Metier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public class Expression_Logique implements Refutable {

    @Override
    public boolean evaluer(HashMap<String, Boolean> valeurs) {
        return false;
    }

    @Override
    public Function<HashMap<String,Boolean>, Boolean> getEvaluator() {
        return null;
    }

    @Override
    public Expression_Logique getExpression() {
        return this;
    }


}
