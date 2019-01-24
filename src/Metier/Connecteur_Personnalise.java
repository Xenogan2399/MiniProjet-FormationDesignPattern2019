package Metier;

import java.util.Stack;
import java.util.function.Function;

public class Connecteur_Personnalise implements Connecteur {
    private Terme_NE expression_semantique;

    public Connecteur_Personnalise(Terme_NE expression_semantique) {
        this.expression_semantique = expression_semantique;
    }

    @Override
    public boolean calculer(Stack<Boolean> S) {
        return expression_semantique.getStackHandler().apply(S).peek();
    }

    @Override
    public boolean calculer(Boolean... booleans) {
        return expression_semantique.calculate(booleans);
    }

    @Override
    public boolean evaluer() {
        /*Connecteur non évaluable*/
        return false;
    }

    @Override
    public Function<Stack<Boolean>, Stack<Boolean>> getStackHandler() {
        return expression_semantique.getStackHandler();
    }
}
