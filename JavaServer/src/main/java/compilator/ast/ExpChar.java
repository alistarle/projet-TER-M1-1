package compilator.ast;

import java.util.List;

/**
 * Created by thomas on 22/02/16.
 */
public class ExpChar extends Expression {
    public String value;
    public ExpChar(Position pos, String value){
        this.pos = pos;
        this.value = value;
    }
    public String toString() {
        return value.toString();
    }

    @Override
    public Type.EnumType getType() {
        return Type.EnumType.CHARVAL;
    }

    @Override
    public int evaluate(List<Integer> stack) {
        return (value.equals("0")) ? 0 : 1;
    }

    @Override
    public void verifSemantique() throws Exception {

    }
}
