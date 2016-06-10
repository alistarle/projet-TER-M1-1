package compilator.intermediate.instruction;

import compilator.intermediate.Instruction;
import compilator.intermediate.Intermediate;

/**
 * Created by alistarle on 22/04/2016.
 */
public class Label extends Instruction{

    private int index;

    public Label() {
        this.index = Intermediate.fresh_lbl();
    }

    public Label(int index)
    {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return index + " :" + "\n";
    }
}
