package compilator.ast;

import java.util.List;

public class ExpInt extends Expression {
	public int value;
	public ExpInt(Position pos, int value){
		this.pos = pos;
		this.value = value;
	}
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public Type.EnumType getType() throws Exception {
		return Type.EnumType.INTVAL;
	}

	@Override
	public int evaluate(List<Integer> stack) {
		return value;
	}

	@Override
	public void verifSemantique() throws Exception {

	}
}
