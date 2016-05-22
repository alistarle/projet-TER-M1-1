package compilator.ast;

/**
 * Created by thomas on 22/02/16.
 */
public class ExpBool extends Expression {
    public Bool value;
    public ExpBool(Position pos, Bool value){
        this.pos = pos;
        this.value = value;
    }
    public String toString() {
        return value.toString();
    }

    @Override
    public Type.EnumType getType() {
        return Type.EnumType.BOOLVAL;
    }

    @Override
    public void verifSemantique() throws Exception {

    }
}
