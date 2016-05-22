package compilator.table;

import compilator.ast.Type;

import java.util.ArrayList;

/**
 * Created by max2 on 10/03/2016.
 */
public class Block {
    private ArrayList<AbstractIdentificateur> identificateurs;

    Block(){
        identificateurs = new ArrayList<AbstractIdentificateur>();
    }

    public boolean addIdentificateur(AbstractIdentificateur a){

        boolean exists = false;
        for(AbstractIdentificateur ai : identificateurs){
            if(ai.nom == a.nom){
                exists = true;
                break;
            }
        }
        if(!exists){
            identificateurs.add(a);
        }
        return !exists;
    }

    public Type.EnumType exists(String n, boolean isFunction){
        Type.EnumType t = null;
        for(AbstractIdentificateur ident : identificateurs){
            if(ident.nom.equals(n)){
                if(isFunction && ident instanceof FunctionIdentificateur) {
                    t = ident.t;
                    break;
                }else if(!isFunction && ident instanceof VarIdentificateur) {
                    t = ident.t;
                    break;
                }
            }
        }
        return t;
    }

    public int exists(String n){
        for(AbstractIdentificateur ident : identificateurs){
            if(ident.nom.equals(n)){
                return ident.getIndex();
            }
        }
        return 0;
    }

    public FunctionIdentificateur getFunc(String n){
        for(AbstractIdentificateur ident : identificateurs) {
            if (ident.nom.equals(n)) {
                if (ident instanceof FunctionIdentificateur) {
                    return (FunctionIdentificateur)ident;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String st = "Bloc :";
        for(AbstractIdentificateur ai : identificateurs)
            st+= "\n\t\t"+ai;
        return st;
    }
}
