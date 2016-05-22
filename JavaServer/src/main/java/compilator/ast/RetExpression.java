package compilator.ast;

import compilator.intermediate.Intermediate;
import compilator.intermediate.instruction.Goto;
import compilator.intermediate.instruction.WriteReg;

import java.util.ArrayList;

/**
 * Created by thomas on 24/02/16.
 */
public class RetExpression extends Instruction {
    public Expression exp;

    public RetExpression(Position pos, Expression exp) {
        this.pos = pos;
        this.exp = exp;
    }

    public RetExpression(Position pos){
        this.pos = pos;
    }

    public boolean isNull(){
        return this.exp == null;
    }

    public String toString() {
        if(isNull()){
            return "return";
        }else {
            return "return " + exp.toString();
        }
    }

    public Type.EnumType getType() throws Exception{
        return exp.getType();
    }

    @Override
    public void verifSemantique() throws Exception {
        exp.verifSemantique();
    }

    @Override
    public ArrayList<compilator.intermediate.Instruction> genIntermediate() {
        ArrayList<compilator.intermediate.Instruction> iList = new ArrayList<>();
        if(!isNull()) iList.add(new WriteReg(Intermediate.return_reg, exp));
        iList.add(new Goto(Intermediate.return_label));
        return iList;
    }
}
