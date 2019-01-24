package Metier;

import java.util.Stack;
import java.util.function.Function;

public interface Atome_NE {
    boolean evaluer();
    Function<Stack<Boolean>, Stack<Boolean>> getStackHandler();
}
