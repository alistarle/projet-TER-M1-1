/**
 * 
 */
package compilator.ast;

import java.util.ArrayList;

public abstract class Instruction extends Ast {
    public abstract ArrayList<compilator.intermediate.Instruction> genIntermediate();
}
