package compilator.table;
import compilator.ast.Type;

import java.util.HashMap;

/**
 * Created by max2 on 10/03/2016.
 */
public class FunctionIdentificateur extends AbstractIdentificateur{
    private HashMap<Type.EnumType ,String> paramVal;
    private HashMap<Type.EnumType ,String> paramRef;

    public FunctionIdentificateur(Type.EnumType r, String n){
        paramRef = new HashMap<Type.EnumType ,String>();
        paramVal = new HashMap<Type.EnumType ,String>();
        this.t = r;
        this.nom = n;
    }

    public void addRef(Type.EnumType t, String n){
        paramRef.put(t,n);
    }

    public void addVal(Type.EnumType t, String n){
        paramVal.put(t,n);
    }
}
