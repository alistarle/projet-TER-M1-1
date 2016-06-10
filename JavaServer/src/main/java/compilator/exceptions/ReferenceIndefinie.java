package compilator.exceptions;

import compilator.ast.Position;

/**
 * Created by max2 on 25/03/2016.
 */
public class ReferenceIndefinie extends Exception {
    public ReferenceIndefinie(String varname, Position pos){
        System.err.println("Référence indéfinie : " + varname + " introuvable !"+" at "+pos.toString());
    }
}
