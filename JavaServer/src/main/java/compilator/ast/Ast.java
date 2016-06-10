package compilator.ast;

public abstract class Ast {
	public Position pos;

	abstract public void verifSemantique() throws Exception;
}


