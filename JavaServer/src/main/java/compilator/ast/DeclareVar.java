package compilator.ast;

import compilator.exceptions.VarExistante;
import compilator.intermediate.Instruction;
import compilator.intermediate.Intermediate;
import compilator.intermediate.instruction.WriteReg;
import compilator.table.Table;
import compilator.table.VarIdentificateur;

import java.util.ArrayList;

/**
 * Created by thomas on 29/02/16.
 */
public class DeclareVar extends Assign{

    public Type.EnumType t;
    public int reg_index;
    public String var;

    public DeclareVar(Position pos, Type.EnumType t, String var) {
        this.pos = pos;
        this.t = t;
        this.var = var;
    }

    public Type.EnumType getT() {
        return t;
    }

    public String getVar() {
        return var;
    }

    public String toString(){
        return t.toString() + " " + var;
    }

    public void insertIntoTable() throws Exception{
        Type.EnumType ty = Table.getInstance().lookUp(var,false);
        if(ty == null) {
            this.reg_index = Intermediate.fresh_reg();
            VarIdentificateur varId = new VarIdentificateur(t, var, reg_index);
            Table.getInstance().addTopBlock(varId, isGlobal);
        }else{
            throw new VarExistante(var,pos);
        }
    }

    public int getIndex() {
        return reg_index;
    }

    public void setIndex(int reg_index) {
        this.reg_index = reg_index;
    }

    @Override
    public void verifSemantique() throws Exception {
        insertIntoTable();
    }

    @Override
    public ArrayList<Instruction> genIntermediate() {
        ArrayList<Instruction> iList = new ArrayList<>();
        iList.add(new WriteReg(reg_index, new ExpInt(null, 0)));
        return iList;
    }
}
