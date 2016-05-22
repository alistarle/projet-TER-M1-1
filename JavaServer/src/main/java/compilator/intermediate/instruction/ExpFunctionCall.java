package compilator.intermediate.instruction;

import compilator.ast.Expression;
import compilator.intermediate.Instruction;

/**
 * Created by alistarle on 22/04/2016.
 */
public class ExpFunctionCall extends Instruction{

    private Expression expression;
    private FunctionCall functionCall;

    public ExpFunctionCall(Expression expression, FunctionCall functionCall) {
        this.expression = expression;
        this.functionCall = functionCall;
    }

    public Expression getExpression() {
        return expression;
    }

    public FunctionCall getFunctionCall() {
        return functionCall;
    }

    @Override
    public String toString() {
        return "\t"+expression + " := " + functionCall + "\n";
    }
}
