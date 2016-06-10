package compilator.ast;

public enum Binop {
    ADD, SUB, MUL, DIV, GT, GTE, LT, LTE, EQ, NEQ, AND, OR, NOT;

    @Override
    public String toString() {
        switch(this) {
            case ADD: return "+";
            case SUB: return "-";
            case MUL: return "*";
            case DIV: return "/";
            case GT: return ">";
            case GTE: return ">=";
            case LT: return "<";
            case LTE: return "<=";
            case EQ: return "==";
            case NEQ: return "!=";
            case AND: return "&&";
            case OR: return "||";
            case NOT: return "!";
        }
        return null;
    }
}
