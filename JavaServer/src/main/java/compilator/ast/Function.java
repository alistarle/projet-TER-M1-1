package compilator.ast;

import compilator.exceptions.TypeIncoherent;
import compilator.exceptions.VarExistante;
import compilator.intermediate.Intermediate;
import compilator.miniLI.StringOffseter;
import compilator.table.FunctionIdentificateur;
import compilator.table.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 22/02/16.
 */
public class Function extends Ast {

    public RetExpression ret;
    public List<Instruction> ins;
    public ArrayList<DeclareVar> params;
    //public HashMap<Type.EnumType, String> params;
    public String id;
    public Type.EnumType type;

    public Function(Position pos, RetExpression ret, List<Instruction> ins, ArrayList<DeclareVar> params, String id, Type.EnumType type) {
        this.pos = pos;
        this.ret = ret;
        this.ins = ins;
        this.params = params;
        this.id = id;
        this.type = type;
    }

    public String toString() {
        StringOffseter s = new StringOffseter(type.toString() + " " + id.toString() +"(");
        for(DeclareVar entry : params){
            s.appendNoOffset(entry.toString() + ",");
        }
        //remove the last ","
        if(params.size() > 0) s.getBuilder().deleteCharAt(s.getBuilder().length()-1);
        s.appendNoOffset("){\n");

        StringOffseter.offset++;
        for(Instruction i: ins){
            String semicolon = (i instanceof Control)? "" : ";";
            s.append(i.toString() + semicolon + "\n");
        }
        StringOffseter.offset--;

        s.append("}");

        return s.toString();
    }

    public void insertIntoTable() throws Exception{
        Type.EnumType t = Table.getInstance().lookUp(id,true);
        if(t == null) {
            FunctionIdentificateur fId = new FunctionIdentificateur(type, id);

            for(DeclareVar entry : params){
                entry.setIndex(Intermediate.fresh_reg());
                if(entry.getT().isRef())
                    fId.addRef(entry.getT(), entry.getVar());
                else
                    fId.addVal(entry.getT(), entry.getVar());
            }

            Table.getInstance().addTopBlock(fId, true);
        }else {
            throw new VarExistante(id,pos);
        }
    }

    @Override
    public void verifSemantique() throws Exception {
        insertIntoTable();
        Type.EnumType t = Table.getInstance().lookUp(id,true);
        if(type != ret.getType()) {
            throw new TypeIncoherent(type.toString(), ret.getType().toString(),pos);
        }

        for(Instruction i:ins){
            i.verifSemantique();
        }
        System.out.println("=========== Affichage de la compilator.table des symbole ===========");
        System.out.println(Table.getInstance().toString());
        Table.getInstance().popBlock();
    }
}
