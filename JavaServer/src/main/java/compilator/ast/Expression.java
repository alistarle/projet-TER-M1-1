package compilator.ast;

public abstract class Expression extends Ast {
	abstract public Type.EnumType getType() throws Exception;
}
