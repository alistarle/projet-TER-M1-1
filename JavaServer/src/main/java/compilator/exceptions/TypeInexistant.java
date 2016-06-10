package compilator.exceptions;

import compilator.ast.Position;

/**
 * Created by max2 on 25/03/2016.
 */
public class TypeInexistant extends Exception {
    public TypeInexistant(String t, Position pos){
        System.err.println(t + " n'est pas un type !" +" at "+pos.toString());
    }
}
