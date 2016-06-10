package compilator.intermediate.instruction;

import compilator.intermediate.Instruction;

/**
 * Created by alistarle on 22/04/2016.
 */
public class Goto extends Instruction{

    private Label label;

    public Goto(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "\tgoto " + label.getIndex() + "\n";
    }
}
