package executor;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpErlangExit;
import compilator.ast.Expression;
import compilator.ast.Reserver;
import compilator.intermediate.Frame;
import compilator.intermediate.Instruction;
import compilator.intermediate.instruction.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Executor {

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
    public static Erlang erlang;

    /**
     * List of instruction
     */
    private List<Instruction> instructions;

    /**
     * List of frames
     */
    private List<Frame> frames;

    /**
     * Stack du programme
     */
    private ArrayList<Integer> stack;

    /**
     * Temp var used to process the list
     */
    private int pointer;

    private Instruction curIns;
    private Frame curFrame;

    private int mainFrame;

    public Executor(ArrayList<Frame> frames, int mainFrame)
    {
        this.frames = frames;
        this.mainFrame = mainFrame-Reserver.Function.values().length;
        this.instructions = new ArrayList<Instruction>();
        this.stack = new ArrayList<Integer>();
        this.labelLink = new HashMap<>();
        this.erlang = new Erlang();
        this.curFrame = frames.get(this.mainFrame);

        for(Frame f : frames)
        {
            instructions.addAll(f.getInstructions());
        }
        genLabelLink();
        this.pointer = labelLink.get(frames.get(this.mainFrame).getEntry());
        this.fp = this.mainFrame;
        this.ra = labelLink.get(curFrame.getReturn());
    }

    public void execute() throws IOException, OtpErlangExit, InterruptedException, OtpAuthException {
        curIns = instructions.get(pointer);
        System.out.println("End at :" + labelLink.get(frames.get(mainFrame).getReturn()));
        while(pointer != labelLink.get(frames.get(mainFrame).getReturn())+1)
        {
            System.out.println(curIns+" at "+pointer);
            if(curIns instanceof Goto) {
                Goto g = (Goto) curIns;
                if(g.getLabel().getIndex() == curFrame.getReturn().getIndex()) {
                    pointer = ra;
                    curIns = instructions.get(pointer);
                    curFrame = frames.get(fp);
                } else
                    jumpAtLabel(g.getLabel());
            } else if(curIns instanceof Jump) {
                Jump j = (Jump) curIns;
                jumpAtLabel((j.getExpression().evaluate(stack) == 1) ? j.getLabelTrue() : j.getLabelFalse());
            } else if(curIns instanceof WriteReg) {
                WriteReg writeReg = (WriteReg) curIns;
                stack.add(writeReg.getReg(), writeReg.getExpression().evaluate(stack));
                pointer++;
                curIns = instructions.get(pointer);
            } else if(curIns instanceof FunctionCall) {
                FunctionCall fc = (FunctionCall) curIns;
                if(fc.getLabel().getIndex() < Reserver.Function.values().length) { //Alors c'est une fonction reservée
                    ArrayList<Integer> params = new ArrayList<Integer>();
                    for (Expression e : fc.getExpressions()) {
                        params.add(e.evaluate(stack));
                    }
                    erlang.execute(Reserver.Function.values()[fc.getLabel().getIndex()], params);
                    pointer++;
                    curIns = instructions.get(pointer);
                } else {
                    fp = frames.indexOf(curFrame);
                    curFrame = frames.get(fc.getLabel().getIndex());
                    ra = pointer++;
                    for (Expression e : fc.getExpressions()) {
                        stack.add(curFrame.getArgs().get(fc.getExpressions().indexOf(e)), e.evaluate(stack));
                    }
                    jumpAtLabel(curFrame.getEntry());
                }
            } else if (curIns instanceof Label) {
                if(pointer == labelLink.get(frames.get(mainFrame).getReturn())) break;
                pointer++;
                curIns = instructions.get(pointer);
            }
        }
        erlang.disconnect();
    }

    public void jumpAtLabel(Label label)
    {
        pointer = labelLink.get(label);
        curIns = instructions.get(pointer);
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
