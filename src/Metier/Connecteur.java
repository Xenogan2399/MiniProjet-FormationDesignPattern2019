package Metier;

import java.util.HashMap;

public interface Connecteur {
    boolean evaluer(String A, String B, HashMap<String,Boolean> domaine_definition);
}
