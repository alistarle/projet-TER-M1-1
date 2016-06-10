package compilator.intermediate;

import compilator.intermediate.instruction.Label;

import java.util.ArrayList;

/**
 * Created by alistarle on 22/04/2016.
 */
public class Frame {

    private Label entry_label;
    private Label return_label;

    private ArrayList<Integer> args;
    private int result;

    private int stack;

    private ArrayList<Instruction> instructions;

    public Frame(Label entry_label, Label return_label, ArrayList<Integer> args, int result) {
        this.instructions = new ArrayList<>();
        this.entry_label = entry_label;
        this.return_label = return_label;
        this.args = args;
        this.result = result;
        this.stack = 0;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public Label getEntry() {
        return entry_label;
    }

    public Label getReturn() {
        return return_label;
    }

    public ArrayList<Integer> getArgs() {
        return args;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        //TODO Rework the toString of arrayList to prevent [ ] to be appended
        StringBuilder str = new StringBuilder("frame(" + entry_label.getIndex() + ") : {entry : " + entry_label.getIndex() + "; return : " + return_label.getIndex() + "; args : " + args + "; result : reg"+result + "; stack : "+stack +"}" + "\n");
        for(Instruction instruction : instructions)
            str.append(instruction.toString());
        return str.toString();
    }
}
