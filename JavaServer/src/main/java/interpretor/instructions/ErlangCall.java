package interpretor.instructions;

import java.util.ArrayList;

/**
 * Created by alistarle on 20/05/2016.
 */
public class ErlangCall extends Instruction {

    private String name;
    private ArrayList<String> params;

    public ErlangCall(ArrayList<String> params, String name) {
        this.params = params;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getParams() {
        return params;
    }
}
