package compilator.ast;

import compilator.exceptions.ReferenceIndefinie;
import compilator.table.Table;

/**
 * Created by thomas on 24/02/16.
 */
public class ExpFunctionCall extends Expression {
    public FunctionCall fc;

    public ExpFunctionCall(Position pos, FunctionCall fc) {
        this.pos = pos;
        this.fc = fc;
    }

    public String toString() {
        return fc.toString();
    }

    @Override
    public Type.EnumType getType() throws Exception{
        //Table.getInstance().
        Type.EnumType t = Table.getInstance().lookUp(fc.id,true);
        if(t!=null){
            return t;
        }else{
            throw new ReferenceIndefinie(fc.id,pos);
        }
    }

    @Override
    public void verifSemantique() throws Exception {
        fc.verifSemantique();
    }
}
