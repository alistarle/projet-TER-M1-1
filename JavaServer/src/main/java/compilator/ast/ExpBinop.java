package compilator.ast;

import compilator.exceptions.TypeIncoherent;

public class ExpBinop extends Expression {
	public Expression left;
	public Expression right;
	public Binop op;
	public ExpBinop(Position pos, Expression left, Binop op, Expression right){
		this.pos = pos;
		this.left = left;
		this.op = op;
		this.right = right;
	}
	public String toString() {
		return left.toString() + op.toString() + right.toString();
	}

	@Override
	public Type.EnumType getType() throws Exception {
		if(left.getType() == right.getType()){
			String o = op.toString().toUpperCase();
			if(o == ">" || o == ">=" ||o == "<" || o == "<=" || o == "==" || o == "!=" || o == "&&" || o == "||"){
				return Type.EnumType.BOOLVAL;
			}else{
				return left.getType();
			}

		}else{
			throw new TypeIncoherent(left.getType().toString(),right.getType().toString(),pos);
		}
	}

	@Override
	public void verifSemantique() throws Exception {
		if(left.getType() != right.getType()){
			throw new TypeIncoherent(left.getType().toString(),right.getType().toString(),pos);
		}
	}
}
