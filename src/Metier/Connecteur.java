package Metier;

import java.util.HashMap;
import java.util.Stack;

public interface Connecteur extends Atome_NE{
    boolean calculer(Stack<Boolean> S);
    boolean calculer(Boolean ... booleans);
}
