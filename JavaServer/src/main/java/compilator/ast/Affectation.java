package compilator.ast;

/**
 * Created by thomas on 22/02/16.
 */
public class Affectation extends Ast {
    public Assign assign;

    public Affectation(Position pos, Assign assign) {
        this.pos = pos;
        this.assign = assign;
    }

    public String toString() {
        return assign.toString() + ";";
    }

    @Override
    public void verifSemantique() throws Exception{
        assign.verifSemantique();
    }
}
