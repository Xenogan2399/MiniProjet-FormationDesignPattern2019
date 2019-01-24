package Metier;

import java.sql.Ref;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Function;

public class Terme_NE extends Expression_Logique implements Atome_NE{
    private Atome_NE membreDroit;
    private Atome_NE membreGauche;
    private Connecteurs_Base connecteur;

    public Terme_NE(Atome_NE membreDroit, Atome_NE membreGauche, Connecteurs_Base connecteur) {
        this.membreDroit = membreDroit;
        this.membreGauche = membreGauche;
        this.connecteur = connecteur;
    }


    @Override
    public Function<Boolean[], Boolean> getCalculator() {
        return new Function<Boolean[], Boolean>() {
            @Override
            public Boolean apply(Boolean[] booleans) {
                Stack<Boolean> SB = new Stack<>();
                for(Boolean b:booleans){
                    SB.push(b);
                }
                SB = getStackHandler().apply(SB);
                return SB.peek();
            }
        };
    }

    public static void main(String[] param){
        Variable V1 = new Variable("A");
        V1.setValeur(true);
        Variable V2 = new Variable("B");
        V2.setValeur(false);
        Variable V3 = new Variable("C");
        V3.setValeur(true);
        Variable V4 = new Variable("D");
        V4.setValeur(false);
        Terme_NE T1 = new Terme_NE(V1,V2,Connecteurs_Base.AND);
        Terme_NE T2 = new Terme_NE(V3,V4,Connecteurs_Base.OR);
        Terme_NE T3 = new Terme_NE(T1,T2,Connecteurs_Base.OR);
        LinkedList<Refutable> LR = new LinkedList<>();
        LR.add(V1);
        LR.add(V2);
        LR.add(V3);
        LR.add(V4);
        LR.add(T1);
        LR.add(T2);
        LR.add(T3);
        /*for(Refutable R:LR){
            System.out.println(R.evaluer());
        }*/
        System.out.println(T3.calculate(true,true,true,false));
    }

    @Override
    public boolean evaluer() {
        return connecteur.calculer(membreGauche.evaluer(),membreDroit.evaluer());
    }

    @Override
    public Function<Stack<Boolean>, Stack<Boolean>> getStackHandler() {
        if(stackHandler != null)
            return stackHandler;
        Function<Stack<Boolean>, Stack<Boolean>> F1 = new Function<Stack<Boolean>, Stack<Boolean>>() {
            @Override
            public Stack<Boolean> apply(Stack<Boolean> booleans) {
                return booleans;
            }
        };
        Function<Stack<Boolean>, Stack<Boolean>> F2 = new Function<Stack<Boolean>, Stack<Boolean>>() {
            @Override
            public Stack<Boolean> apply(Stack<Boolean> booleans) {
                return booleans;
            }
        };
        if(membreGauche.getClass().getName().equalsIgnoreCase("Terme_NE"))
        {
            F1 = membreGauche.getStackHandler();
        }
        if(membreDroit.getClass().getName().equalsIgnoreCase("Terme_NE"))
        {
            F2 = membreDroit.getStackHandler();
        }

        return F1.compose(F2).compose(connecteur.getTraitement_recursif());
    }



    public boolean calculate(Boolean ... bool){
        return getCalculator().apply(bool);
    }
}
