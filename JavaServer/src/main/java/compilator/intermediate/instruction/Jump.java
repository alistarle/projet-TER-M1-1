package compilator.intermediate.instruction;

import compilator.ast.Expression;
import compilator.intermediate.Instruction;

/**
 * Created by alistarle on 22/04/2016.
 */
public class Jump extends Instruction{

    private Expression expression;
    private Label labelTrue;
    private Label labelFalse;

    public Jump(Expression expression, Label labelTrue, Label labelFalse) {
        this.expression = expression;
        this.labelFalse = labelFalse;
        this.labelTrue = labelTrue;
    }

    public Expression getExpression() {
        return expression;
    }

    public Label getLabelTrue() {
        return labelTrue;
    }

    public Label getLabelFalse() {
        return labelFalse;
    }

    @Override
    public String toString() {
        return "\tjump (" + expression + ") " + labelTrue.getIndex() + " " + labelFalse.getIndex() + "\n";
    }
}
