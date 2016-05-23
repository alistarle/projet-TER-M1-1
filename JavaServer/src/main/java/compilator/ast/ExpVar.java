package compilator.ast;

import compilator.exceptions.ReferenceIndefinie;
import compilator.table.Table;

import java.util.List;

public class ExpVar extends Expression {

	//Flag use to tweak the toString function from name to regIndex
	public static boolean FLAG_REG = false;

	public String name;
	public int reg_index;
	public boolean sub;
	public ExpVar(Position pos, String name, boolean sub){
		this.pos = pos;
		this.name = name;
		this.sub = sub;
	}

	public String toString() {
		if(FLAG_REG)
			return (sub) ? "-reg"+reg_index : "reg"+reg_index;
		else
			return (sub) ? Binop.SUB.toString()+name.toString() : name.toString();
	}
	@Override
	public Type.EnumType getType() throws Exception {
		Type.EnumType t = Table.getInstance().lookUp(name,false);
		if(t!=null){
			this.reg_index = Table.getInstance().lookUpIndex(name);
			return t;
		}else{
			throw new ReferenceIndefinie(name,pos);
		}
	}

	@Override
	public int evaluate(List<Integer> stack) {
		return stack.get(reg_index);
	}

	public int getIndex() {
		return reg_index;
	}

	@Override
	public void verifSemantique() throws Exception {
		getType();
	}
}
