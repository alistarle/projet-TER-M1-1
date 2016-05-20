package interpretor.instructions;

import java.util.List;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Expression {

    private List<Integer> varList;
    private List<ErlangCall> erlangCalls;

    private String rawExpression;

    public Expression(String raw) {
        this.rawExpression = raw;
    }

    public void evaluate() {

    }
}
