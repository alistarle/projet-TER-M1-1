package compilator.table;
import compilator.ast.Type;

/**
 * Created by max2 on 10/03/2016.
 */
public class VarIdentificateur extends AbstractIdentificateur{


    public VarIdentificateur(Type.EnumType t, String n, int regIndex){
        this.t = t;
        this.nom = n;
        this.reg_index = regIndex;
    }
}
