package compilator.exceptions;

import compilator.ast.Position;

/**
 * Created by max2 on 30/03/2016.
 */
public class WrongIndex extends Exception {
    public WrongIndex(Integer i, Position pos){
        System.err.println("Indice n�gatif ou �gal � 0 : "+i.toString()+" at "+pos.toString());
    }
}
