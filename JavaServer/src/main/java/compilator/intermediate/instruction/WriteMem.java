package compilator.intermediate.instruction;

import compilator.ast.Expression;
import compilator.intermediate.Instruction;

/**
 * Created by alistarle on 22/04/2016.
 */
public class WriteMem extends Instruction{

    //TODO Behaviour of memory managment with array
    private Expression expressionLeft;
    private Expression expressionRight;

    public WriteMem(Expression expressionLeft, Expression expressionRight) {
        this.expressionRight = expressionRight;
        this.expressionLeft = expressionLeft;
    }

    public Expression getExpressionLeft() {
        return expressionLeft;
    }

    public Expression getExpressionRight() {
        return expressionRight;
    }

    @Override
    public String toString() {
        return "\t"+expressionLeft + " := " + expressionRight + "\n";
    }
}
