package Metier;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

public enum Connecteurs_Base implements Connecteur{
    OR,
    AND;

    @Override
    public boolean evaluer(String A, String B,HashMap<String,Boolean> domaine) {
        HashMap<String,Boolean> param = new HashMap<>();
        if(this.compareTo(Connecteurs_Base.AND) == 0)
        {
            param.put(A,domaine.get(A));
            param.put(B,domaine.get(B));
            return andEvaluator.apply(param);
        }
        else{
            return orEvaluator.apply(param);
        }
    }

    public Function<HashMap<String,Boolean>,Boolean> getEvaluator(){

    }

    private static Function<HashMap<String, Boolean>, Boolean> andEvaluator = stringBooleanHashMap -> {
        boolean b = true;
        for(String s:stringBooleanHashMap.keySet()){
            b = b && stringBooleanHashMap.get(s);
        }
        return b;
    };


    private static Function<HashMap<String, Boolean>, Boolean> orEvaluator = stringBooleanHashMap -> {
        boolean b = false;
        for(String s:stringBooleanHashMap.keySet()){
            b = b || stringBooleanHashMap.get(s);
        }
        return b;
    };

}
