package compilator.ast;

import compilator.exceptions.TypeIncoherent;
import compilator.exceptions.VarExistante;
import compilator.exceptions.WrongIndex;
import compilator.intermediate.Instruction;
import compilator.intermediate.Intermediate;
import compilator.table.Table;
import compilator.table.VarIdentificateur;

import java.util.ArrayList;

/**
 * Created by thomas on 29/02/16.
 */
public class DeclareTab extends Assign {

    public Type.EnumType t;
    public String id;
    public Integer cst;
    public String idParam;

    public DeclareTab(Position pos, Type.EnumType t, Integer cst, String idParam, String id) {
        this.pos = pos;
        this.t = t;
        this.cst = cst;
        this.idParam = idParam;
        this.id = id;
    }

    public boolean cstIsNull(){
        return cst == null;
    }

    public boolean idParamIsNull(){
        return idParam == null;
    }

    public String toString(){
        if(cstIsNull()){
            return t.toString() + " " + id + "[" + idParam.toString() + "]";
        }
        else{
            return t.toString() + " " + id + "[" + cst.toString() + "]";
        }
    }

    public void insertIntoTable() throws Exception{
        Type.EnumType t = Table.getInstance().lookUp(id,false);
        if(t == null) {
            VarIdentificateur varIdentificateur = new VarIdentificateur(this.t, id, Intermediate.fresh_reg());
            Table.getInstance().addTopBlock(varIdentificateur, isGlobal);
        }else{
            throw new VarExistante(id,pos);
        }
    }

    @Override
    public void verifSemantique() throws Exception {
        insertIntoTable();
        if(cstIsNull()){
            Type.EnumType t = Table.getInstance().lookUp(idParam,false);
            if(t != Type.EnumType.INTVAL){
                throw new TypeIncoherent(t.toString(), "int",pos);
            }
        }else if(cst <= 0){
            throw new WrongIndex(cst,pos);
        }
    }

    @Override
    public ArrayList<Instruction> genIntermediate() {
        //TODO Gen declaration of array
        return null;
    }
}
