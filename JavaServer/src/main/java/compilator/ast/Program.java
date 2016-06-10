package compilator.ast;

import compilator.miniLI.StringOffseter;

import java.util.List;

public class Program extends Ast {

	public List<Global> global;
	public List<Function> function;

	public Program(Position pos, List<Global> global, List<Function> function){
		this.function = function;
		this.pos = pos;
		this.global = global;
	}



	public String toString() {
		StringOffseter s = new StringOffseter();

		for(Global g:global){
			s.append(g.toString()+"\n");
		}

		for(Function f:function){
			s.append(f.toString()+"\n");
		}

		return s.toString();
	}

	@Override
	public void verifSemantique() throws Exception {
		for(Global g:global){
			g.verifSemantique();
		}

		for(Function f:function){
			f.verifSemantique();
		}
	}
}
