package compilator.exceptions;

import compilator.ast.Position;

/**
 * Created by max2 on 25/03/2016.
 */
public class TypeIncoherent extends Exception {
    public TypeIncoherent(String t1, String t2, Position pos){
        System.err.println("type : " + t1 + " n'est pas equivalent à : " + t2+" at "+pos.toString());
    }
}
