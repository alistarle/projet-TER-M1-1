package compilator.intermediate.instruction;

import compilator.ast.Expression;
import compilator.intermediate.Instruction;

import java.util.ArrayList;

/**
 * Created by alistarle on 22/04/2016.
 */
public class FunctionCall extends Instruction{

    private Label lbl;
    private ArrayList<Expression> expressions;

    public FunctionCall(Label lbl, ArrayList<Expression> expressions) {
        this.lbl = lbl;
        this.expressions = expressions;
    }

    public Label getLabel() {
        return lbl;
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public String toString() {
        return "\tcall " + lbl.getIndex() + " ("+ expressions + ")" + "\n";
    }
}
