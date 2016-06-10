package compilator.ast;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpErlangExit;
import compilator.exceptions.TypeIncoherent;

import java.io.IOException;
import java.util.List;

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
	public int evaluate(List<Integer> stack) throws InterruptedException, OtpErlangExit, OtpAuthException, IOException {
		switch (op)
		{
			case ADD:
				return left.evaluate(stack) + right.evaluate(stack);
			case MUL:
				return left.evaluate(stack) * right.evaluate(stack);
			case SUB:
				return left.evaluate(stack) - right.evaluate(stack);
			case DIV:
				return left.evaluate(stack) / right.evaluate(stack);
			case AND:
				return (left.evaluate(stack) != 1 && right.evaluate(stack) != 1) ? 1 : 0;
			case EQ:
				return (left.evaluate(stack) == right.evaluate(stack)) ? 1 : 0;
			case GT:
				return (left.evaluate(stack) > right.evaluate(stack)) ? 1 : 0;
			case GTE:
				return (left.evaluate(stack) >= right.evaluate(stack)) ? 1 : 0;
			case LT:
				return (left.evaluate(stack) < right.evaluate(stack)) ? 1 : 0;
			case LTE:
				return (left.evaluate(stack) <= right.evaluate(stack)) ? 1 : 0;
			case NEQ:
				return (left.evaluate(stack) != right.evaluate(stack)) ? 1 : 0;
			case NOT:
				return (left.evaluate(stack) > 0) ? 0 : 1;
			case OR:
				return (left.evaluate(stack) > 0 || right.evaluate(stack) > 0) ? 1 : 0;
		}
		return 0;
	}

	@Override
	public void verifSemantique() throws Exception {
		if(left.getType() != right.getType()){
			throw new TypeIncoherent(left.getType().toString(),right.getType().toString(),pos);
		}
	}
}
