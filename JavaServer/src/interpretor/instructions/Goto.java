package interpretor.instructions;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Goto extends Instruction {

    private int label;

    public Goto(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }
}
