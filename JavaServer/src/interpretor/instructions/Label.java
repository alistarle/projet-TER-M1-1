package interpretor.instructions;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Label extends Instruction {

    private int index;

    public Label(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
