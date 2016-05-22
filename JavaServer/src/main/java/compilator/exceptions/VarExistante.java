package compilator.exceptions;

import compilator.ast.Position;

/**
 * Created by max2 on 30/03/2016.
 */
public class VarExistante extends Exception {
    public VarExistante(String v, Position pos){
        System.err.println(v + " existe déjà !"+" at "+pos.toString());
    }
}
