package interpretor.instructions;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Jump extends Instruction {

    private Expression expression;
    private Label isTrue;
    private Label isFalse;

    public Jump(Expression expression, Label isTrue, Label isFalse) {
        this.expression = expression;
        this.isTrue = isTrue;
        this.isFalse = isFalse;
    }

    public Expression getExpression() {
        return expression;
    }

    public Label getIsTrue() {
        return isTrue;
    }

    public Label getIsFalse() {
        return isFalse;
    }
}
