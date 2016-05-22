package compilator.ast;

import compilator.exceptions.ReferenceIndefinie;
import compilator.exceptions.TypeIncoherent;
import compilator.intermediate.Instruction;
import compilator.table.Table;

import java.util.ArrayList;

/**
 * Created by thomas on 29/02/16.
 */
public class AssignTabExp extends Assign {

    public String nameTab;
    public Expression expParam;
    public int reg_index;
    public Expression exp;

    public AssignTabExp(Position pos, String nameTab, Expression expParam, Expression exp) {
        this.pos = pos;
        this.nameTab = nameTab;
        this.expParam = expParam;
        this.exp = exp;
    }

    public String toString(){
        return nameTab + "[" + expParam.toString() + "] = " + exp.toString();
    }

    @Override
    public void verifSemantique() throws Exception{
        Type.EnumType t = Table.getInstance().lookUp(nameTab,false);
        if(t==null){
            throw new ReferenceIndefinie(nameTab,pos);
        }else{
            if(expParam.getType() != Type.EnumType.INTVAL){
                throw new TypeIncoherent(expParam.getType().toString(), "int",pos);
            }else {
                if(exp.getType() != t){
                    throw new TypeIncoherent(exp.getType().toString(),t.toString(),pos);
                }
            }
        }
        this.reg_index = Table.getInstance().lookUpIndex(nameTab);
    }

    @Override
    public ArrayList<Instruction> genIntermediate() {
        //TODO Gen assignation of array
        return null;
    }
}
