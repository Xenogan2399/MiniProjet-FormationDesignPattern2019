package Metier;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Function;

public interface Atome_NE {
    boolean evaluer();
    Function<Stack<Boolean>, Stack<Boolean>> getStackHandler();
    boolean isFeuille();
    void getLitteraux(HashSet<Formule_Atomique> litteraux);
    boolean isVariable();
}
