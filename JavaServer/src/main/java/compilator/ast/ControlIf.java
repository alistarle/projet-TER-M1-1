package compilator.ast;

import compilator.exceptions.TypeIncoherent;
import compilator.intermediate.Intermediate;
import compilator.intermediate.instruction.Goto;
import compilator.intermediate.instruction.Jump;
import compilator.intermediate.instruction.Label;
import compilator.miniLI.StringOffseter;
import compilator.table.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 22/02/16.
 */
public class ControlIf extends Control {

    public Expression exp;
    public List<Instruction> lif;
    public List<Instruction> lelse;

    public ControlIf(Position pos, Expression exp, List<Instruction> lif, List<Instruction> lesle) {
        this.pos = pos;
        this.exp = exp;
        this.lif = lif;
        this.lelse = lesle;
    }

    public String toString() {
        StringOffseter s = new StringOffseter("if("+exp.toString()+"){\n", false);
        StringOffseter.offset++;
        for(Instruction i:lif){
            String semicolon = (i instanceof Control)? "" : ";";
            s.append(i.toString() + semicolon + "\n");
        }
        StringOffseter.offset--;
        s.append("}");
        if(lelse.size() != 0){
            s.appendNoOffset("else{\n");
            StringOffseter.offset++;
            for(Instruction i:lelse){
                String semicolon = (i instanceof Control)? "" : ";";
                s.append(i.toString() + semicolon +"\n");
            }
            StringOffseter.offset--;
            s.append("}");
        }
        return s.toString();
    }

    @Override
    public void verifSemantique() throws Exception {
        if(exp.getType()!= Type.EnumType.BOOLVAL){
            throw new TypeIncoherent(exp.getType().toString(),"Boolean",pos);
        }

        Table.getInstance().newBlock();

        for(Instruction i:lif){
            i.verifSemantique();
        }

        for(Instruction i:lelse){
            i.verifSemantique();
        }
        Table.getInstance().popBlock();
    }

    @Override
    public ArrayList<compilator.intermediate.Instruction> genIntermediate() {
        ArrayList<compilator.intermediate.Instruction> iList = new ArrayList<>();
        Label isTrue = new Label(Intermediate.fresh_lbl());
        Label isFalse = new Label(Intermediate.fresh_lbl());
        Label join = new Label(Intermediate.fresh_lbl());

        iList.add(new Jump(exp, isTrue, isFalse));

        iList.add(isTrue);
        for(Instruction instruction : lif)
        {
            iList.addAll(instruction.genIntermediate());
        }
        //Le join, on rajoute un label pour pouvoir le supprimer si il est inutilisé ensuite
        iList.add(new Label(Intermediate.fresh_lbl()));
        iList.add(new Goto(join));

        iList.add(isFalse);
        for(Instruction instruction : lelse)
        {
            iList.addAll(instruction.genIntermediate());
        }
        //Le join, on rajoute un label pour pouvoir le supprimer si il est inutilisé ensuite
        iList.add(new Label(Intermediate.fresh_lbl()));
        iList.add(new Goto(join));

        iList.add(join);
        return iList;
    }
}
