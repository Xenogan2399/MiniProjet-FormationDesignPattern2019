package Metier;

import java.sql.Ref;
import java.util.HashSet;
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
        HashSet<Formule_Atomique> LFA = new HashSet<>();
        T3.getLitteraux(LFA);
        System.out.println(LFA);
    }

    @Override
    public boolean evaluer() {
        return connecteur.calculer(membreGauche.evaluer(),membreDroit.evaluer());
    }

    @Override
    public Function<Stack<Boolean>, Stack<Boolean>> getStackHandler() {
        Function<Stack<Boolean>, Stack<Boolean>> F1 = new Function<Stack<Boolean>, Stack<Boolean>>() {
            @Override
            public Stack<Boolean> apply(Stack<Boolean> booleans) {
                return booleans;
            }
        };
        if(!membreDroit.isFeuille() && !membreGauche.isFeuille())
        {
            final boolean[] rez1 = new boolean[2];
            F1 = membreGauche.getStackHandler().andThen(new Function<Stack<Boolean>, Stack<Boolean>>() {
                @Override
                public Stack<Boolean> apply(Stack<Boolean> booleans) {
                    rez1[0] = booleans.pop();
                    return booleans ;
                }
            }).compose(membreDroit.getStackHandler()).andThen(new Function<Stack<Boolean>, Stack<Boolean>>() {
                @Override
                public Stack<Boolean> apply(Stack<Boolean> booleans) {
                    booleans.push(rez1[0]);
                    return booleans ;
                }
            }).compose(connecteur.getTraitement_recursif()).andThen(new Function<Stack<Boolean>, Stack<Boolean>>() {
                @Override
                public Stack<Boolean> apply(Stack<Boolean> booleans) {
                    return booleans;
                }
            });
        }
        return F1;
    }

    @Override
    public void getLitteraux(HashSet<Formule_Atomique> litteraux) {
       membreGauche.getLitteraux(litteraux);
       membreDroit.getLitteraux(litteraux);
    }



    @Override
    public boolean isVariable() {
        return false;
    }

    @Override
    public boolean isFeuille() {
        return false;
    }


    public boolean calculate(Boolean ... bool){
        return getCalculator().apply(bool);
    }
}
