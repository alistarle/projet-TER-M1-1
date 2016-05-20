package interpretor.instructions;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Assign extends Instruction {

    private int index;
    private Expression expression;

    public Assign(int index, Expression expression) {
        this.index = index;
        this.expression = expression;
    }

    public int getIndex() {
        return index;
    }

    public Expression getExpression() {
        return expression;
    }
}
