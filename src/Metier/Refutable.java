package Metier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public interface Refutable {
    boolean evaluer();
    Function<Boolean[],Boolean> getCalculator();
    Expression_Logique getExpression();
}
