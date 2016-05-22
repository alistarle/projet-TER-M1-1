package compilator.ast;

import compilator.exceptions.ReferenceIndefinie;
import compilator.intermediate.instruction.Label;
import compilator.table.FunctionIdentificateur;
import compilator.table.Table;

import java.util.ArrayList;

/**
 * Created by thomas on 22/02/16.
 */
public class FunctionCall extends Instruction {

    public String id;
    public ArrayList<Expression> param;

    public FunctionCall(Position pos,String id, ArrayList<Expression> param) {
        this.pos = pos;
        this.id = id;
        this.param = param;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(id +"(");

        for(Expression i: param){
            s.append(i.toString() + ",");
        }

        //remove last ","
        s.deleteCharAt(s.length()-1);
        s.append(")");

        return s.toString();
    }

    @Override
    public void verifSemantique() throws Exception {
        if( !(id.equals("in") || id.equals("out"))){

            FunctionIdentificateur f = Table.getInstance().getFunc(id);
            //probleme parametres
            if (f == null) {
                throw new ReferenceIndefinie(id,pos);
            }
        }
    }

    @Override
    public ArrayList<compilator.intermediate.Instruction> genIntermediate() {
        ArrayList<compilator.intermediate.Instruction> iList = new ArrayList<>();
         iList.add(new compilator.intermediate.instruction.FunctionCall(new Label(Table.getInstance().getFunc(id).getIndex()), param));
        return iList;
    }
}
