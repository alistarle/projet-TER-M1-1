package compilator.ast;

public class Position {
	public int line;
	public int column;

	public Position(int line, int column) {
		super();
		this.line = line;
		this.column = column;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[line=" + line + ", column=" + column + "]";
	}
	
	
}
