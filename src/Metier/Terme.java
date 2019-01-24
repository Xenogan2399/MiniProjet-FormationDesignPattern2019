package Metier;

import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Function;

public class Terme extends Expression_Logique{
    private Expression_Logique membreDroit;
    private Expression_Logique membreGauche;
    private Connecteur connecteur;

    public Terme(Expression_Logique membreDroit, Expression_Logique membreGauche, Connecteur connecteur) {
        this.membreDroit = membreDroit;
        this.membreGauche = membreGauche;
        this.connecteur = connecteur;
    }

    public Expression_Logique getMembreDroit() {
        return membreDroit;
    }


    public Expression_Logique getMembreGauche() {
        return membreGauche;
    }

    public Connecteur getConnecteur() {
        return connecteur;
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
        if(membreGauche.getClass().getName().equalsIgnoreCase("Terme_NE") || membreGauche.getClass().getName().equalsIgnoreCase("Terme") )
        {
            F1 = membreGauche.getStackHandler();
        }
        if(membreDroit.getClass().getName().equalsIgnoreCase("Terme_NE") || membreGauche.getClass().getName().equalsIgnoreCase("Terme"))
        {
            F2 = membreDroit.getStackHandler();
        }
        return F1.compose(F2).compose(connecteur.getStackHandler());
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
        Terme_NE T2 = new Terme_NE(V3,V4,Connecteurs_Base.AND);
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
        Connecteur_Personnalise CCP = new Connecteur_Personnalise(T3);
        Variable V5 = new Variable("E");
        V5.setValeur(false);
        Variable V6 = new Variable("F");
        V6.setValeur(true);
        Terme T = new Terme(V5,V6,CCP);
        System.out.println(T.evaluer());
    }

    public boolean calculate(Boolean ... bool){
        return getCalculator().apply(bool);
    }


}
