package compilator.exceptions;

import compilator.ast.Position;

/**
 * Created by alistarle on 01/04/2016.
 */
public class CompilationException extends Exception{

    private Position pos;

    public CompilationException(Position pos, String message) {
        super(message);
        this.pos = pos;
    }

    public CompilationException(Position pos, String message, Throwable cause) {
        super(message, cause);
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }
}
