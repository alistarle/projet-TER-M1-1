package compilator.ast;

import java.util.List;

/**
 * Created by thomas on 22/02/16.
 */
public class ExpVoid extends Expression {

    @Override
    public Type.EnumType getType() throws Exception {
        return Type.EnumType.VOIDVAL;
    }

    @Override
    public int evaluate(List<Integer> stack) {
        return 0;
    }

    @Override
    public void verifSemantique() throws Exception {

    }
}
