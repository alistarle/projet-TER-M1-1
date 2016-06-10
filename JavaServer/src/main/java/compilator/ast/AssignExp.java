package compilator.ast;

import compilator.exceptions.ReferenceIndefinie;
import compilator.exceptions.TypeIncoherent;
import compilator.exceptions.VarExistante;
import compilator.intermediate.Instruction;
import compilator.intermediate.Intermediate;
import compilator.intermediate.instruction.WriteReg;
import compilator.table.Table;
import compilator.table.VarIdentificateur;

import java.util.ArrayList;

/**
 * Created by thomas on 22/02/16.
 */
public class AssignExp extends Assign {
    public Type.EnumType t;
    public String var;
    public int reg_index;
    public Expression exp;

    public AssignExp(Position pos, Type.EnumType t, String var, Expression exp) {
        this.t = t;
        this.pos = pos;
        this.var = var;
        this.exp = exp;
    }

    public boolean isNull(){
        return this.t == null;
    }

    public String toString() {
        if (isNull()){
            return var + " = " + exp.toString();
        }
        else {
            return t.toString() + " " + var + " = " + exp.toString();
        }
    }

    public void insertIntoTable() throws Exception{
        //declaration
        if(!isNull()){
            Type.EnumType ty = Table.getInstance().lookUp(var,false);
            if(ty == null) {
                this.reg_index = Intermediate.fresh_reg();
                VarIdentificateur varId;
                varId = new VarIdentificateur(t, var, reg_index);
                Table.getInstance().addTopBlock(varId, isGlobal);
            }else{
                throw new VarExistante(var,pos);
            }
        }
    }

    @Override
    public void verifSemantique() throws Exception{
        insertIntoTable();
        Type.EnumType type = Table.getInstance().lookUp(var, false);
        if(type == null){
            throw new ReferenceIndefinie(var,pos);
        }else if(type != exp.getType()){
            throw new TypeIncoherent(type.toString(),exp.getType().toString(),pos);
        }
        this.reg_index = Table.getInstance().lookUpIndex(var);
    }

    @Override
    public ArrayList<Instruction> genIntermediate() {
        ArrayList<Instruction> iList = new ArrayList<Instruction>();
        iList.add(new WriteReg(reg_index, exp));
        return iList;
    }
}
