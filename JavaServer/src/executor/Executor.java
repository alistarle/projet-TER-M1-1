package executor;

import interpretor.instructions.Goto;
import interpretor.instructions.Instruction;
import interpretor.instructions.Jump;
import interpretor.instructions.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Executor {

    /**
     * Variables réelles
     */
    private int a0, a1, a2, a3;

    /**
     * Variables temporaires
     */
    private int t0, t1, t2, t3, t4, t5, t6, t7, t8, t9;

    /**
     * Variables spéciales
     */
    private int ra, v0, fp;

    /**
     * Hashmap who link label to line number ( line number are artificial, it's only an index in a list )
     */
    private HashMap<Label, Integer> labelLink;

    /**
     * Erlang Node instance to trigger event
     */
    private Erlang erlang;

    /**
     * List of instruction
     */
    private List<Instruction> instructions;

    /**
     * Temp var used to process the list
     */
    private int pointer = 0;
    private Instruction current;
    private Instruction next;

    public Executor(ArrayList<Instruction> instructions)
    {
        this.instructions = instructions;
        this.labelLink = new HashMap<>();
        this.erlang = new Erlang();
        genLabelLink();
    }

    public void execute()
    {
        next = instructions.get(pointer);
        while(next != null)
        {
            if(next instanceof Goto) {
                Goto g = (Goto) next;
                next = instructions.get(labelLink.get(new Label(g.getLabel())));
            } else if(next instanceof Jump) {
                Jump j = (Jump) next;

            }
        }
    }

    private void genLabelLink()
    {
        for(Instruction i : instructions)
        {
            if(i instanceof Label)
            {
                labelLink.put((Label) i, instructions.indexOf(i));
            }
        }
    }
}
