package compilator.ast;

/**
 * Created by thomas on 22/02/16.
 */
public class Type extends Ast{

    public enum EnumType {
        INTVAL("INT", false),
        INTREF("INT", true),
        CHARVAL("CHAR", false),
        CHARREF("CHAR", true),
        BOOLVAL("BOOL", false),
        BOOLREF("BOOL", true),
        VOIDVAL("VOID", false),
        VOIDREF("VOID", true);

        private final String type;
        private final boolean ref;

        EnumType(String type, boolean ref) {
            this.type = type;
            this.ref = ref;
        }

        public String TYPE() {
            return type;
        }

        public boolean isRef() {
            return ref;
        }

        @Override
        public String toString() {
            return (ref) ? "&"+type.toLowerCase() : type.toLowerCase();
        }
    }

    private EnumType t;

    public Type(Position pos, EnumType t){
        this.pos = pos;
        this.t = t;
    }

    public EnumType getType() {
        return t;
    }

    public String toString() {
        return t.toString();
    }

    @Override
    public void verifSemantique() throws Exception {

    }
}

