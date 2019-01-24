package Metier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.function.Function;

public abstract class Expression_Logique implements Refutable {

    Function<Stack<Boolean>,Stack<Boolean>> stackHandler ;

    public Function<Stack<Boolean>, Stack<Boolean>> getStackHandler() {
        return stackHandler;
    }

    public void setStackHandler(Function<Stack<Boolean>, Stack<Boolean>> stackHandler) {
        this.stackHandler = stackHandler;
    }



    @Override
    public abstract boolean evaluer();


    @Override
    public Expression_Logique getExpression() {
        return this;
    }



}
