package compilator.ast;

import compilator.exceptions.TypeIncoherent;

public class ExpUnop extends Expression {
    public Expression right;
    public Binop op;

    public ExpUnop(Position pos, Expression right, Binop op) {
        this.pos = pos;
        this.right = right;
        this.op = op;
    }
    public String toString() {
        return op.toString() + right.toString();
    }

    @Override
    public Type.EnumType getType() throws Exception {
        if(op.toString().toUpperCase() == "!"){
            return Type.EnumType.BOOLVAL;
        }else {
            return right.getType();
        }
    }

    @Override
    public void verifSemantique() throws Exception {
        //un seul binop : !
        if(getType() != Type.EnumType.BOOLVAL){
            throw new TypeIncoherent(getType().toString(),"boolean",pos);
        }
    }
}
