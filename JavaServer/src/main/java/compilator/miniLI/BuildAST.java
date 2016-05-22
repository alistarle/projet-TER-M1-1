package compilator.miniLI;

import compilator.ast.*;
import compilator.exceptions.CompilationException;
import compilator.parser.MiniliBaseVisitor;
import compilator.parser.MiniliLexer;
import compilator.parser.MiniliParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 24/02/16.
 */
public class BuildAST extends MiniliBaseVisitor<Ast> {

    public static Position position(ParserRuleContext ctx){
        Position pos = new Position(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        return pos;
    }


    @Override
    public Ast visitControleWhile(MiniliParser.ControleWhileContext ctx) {
        Expression e = (Expression) visit(ctx.expression());
        List<Instruction> ins =  new ArrayList<>();

        for(ParseTree child : ctx.instruction()){
            Ast i = visit(child);
            if(i != null && i instanceof Instruction){
                ins.add((Instruction) i);
            }
        }
        return new ControlWhile(position(ctx), e, ins);
    }

    @Override
    public Ast visitControleIf(MiniliParser.ControleIfContext ctx) {
        Expression e = (Expression) visit(ctx.expression());
        List<Instruction> lif = new ArrayList<>();
        List<Instruction> lelse = new ArrayList<>();

        for(ParseTree child : ctx.instructionList(0).instruction()){
            Ast i = visit(child);
            if(i != null && i instanceof Instruction){
                lif.add((Instruction) i);
            }
        }
        if(ctx.instructionList(1) != null) {
            for (ParseTree child : ctx.instructionList(1).instruction()) {
                Ast i = visit(child);
                if (i != null && i instanceof Instruction) {
                    lelse.add((Instruction) i);
                }
            }
        }
        return new ControlIf(position(ctx), e, lif, lelse );
    }

    @Override
    public Ast visitReturnOnly(MiniliParser.ReturnOnlyContext ctx){
        return new RetExpression(position(ctx), null);
    }

    @Override
    public Ast visitReturnExp(MiniliParser.ReturnExpContext ctx) {
        return new RetExpression(position(ctx), (Expression) visit(ctx.expression()));
    }

    @Override
    public Ast visitInstruction(MiniliParser.InstructionContext ctx) {
        if(ctx.getChild(0) == null)
            ErrorHandling.handleError(new CompilationException(position(ctx),"Erreur dans l'instruction"));
        return visit(ctx.getChild(0));
    }

    @Override
    public Ast visitDeclareVar(MiniliParser.DeclareVarContext ctx){
        boolean ref;
        if(ctx.children.indexOf(ctx.type()) > 0) {
            ParseTree term = ctx.children.get(ctx.children.indexOf(ctx.type())-1);
            ref = term instanceof TerminalNodeImpl && term.toString().equals("&");
        } else {
            ref = false;
        }
        return new DeclareVar(position(ctx), ((Type) visitType(ctx.type(),ref)).getType(), ctx.Identifiant().toString());
    }

    @Override
    public Ast visitAssignExp(MiniliParser.AssignExpContext ctx) {
        boolean ref;
        if(ctx.children.indexOf(ctx.type()) > 0) {
            ParseTree term = ctx.children.get(ctx.children.indexOf(ctx.type())-1);
            ref = term instanceof TerminalNodeImpl && term.toString().equals("&");
        } else {
            ref = false;
        }
        Type.EnumType type = (ctx.type() != null) ? ((Type) visitType(ctx.type(),ref)).getType() : null;
        Expression expression = (Expression) visit(ctx.expression());
        return new AssignExp(position(ctx), type, ctx.Identifiant().toString(), expression);
    }

    @Override
    public Ast visitAssignTabExp(MiniliParser.AssignTabExpContext ctx) {
        return new AssignTabExp(position(ctx), ctx.Identifiant().toString() ,(Expression)visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)));
    }

    @Override
    public Ast visitDeclareTab(MiniliParser.DeclareTabContext ctx) {
        Integer constante = (ctx.Constante() != null) ? Integer.parseInt(ctx.Constante().toString()) : null;
        String idParam = (ctx.Identifiant(1) != null) ? ctx.Identifiant(1).toString() : null;
        boolean ref;
        if(ctx.children.indexOf(ctx.type()) > 0) {
            ParseTree term = ctx.children.get(ctx.children.indexOf(ctx.type())-1);
            ref = term instanceof TerminalNodeImpl && term.toString().equals("&");
        } else {
            ref = false;
        }
        return new DeclareTab(position(ctx), ((Type) visitType(ctx.type(),ref)).getType(),  constante , idParam, ctx.Identifiant(0).toString());
    }

    @Override
    public Ast visitMulDiv(MiniliParser.MulDivContext ctx) {
        Expression e0 = (Expression) visit(ctx.expression(0));
        Expression e1 = (Expression) visit(ctx.expression(1));
        if ( ctx.op.getType() == MiniliLexer.MUL)
            return new ExpBinop(position(ctx),e0, Binop.MUL,e1);
        else
            return new ExpBinop(position(ctx),e0, Binop.DIV,e1);
    }

    @Override public Ast visitPar(MiniliParser.ParContext ctx) {
        return visit(ctx.expression());
    }

    @Override public Ast visitAddSub(MiniliParser.AddSubContext ctx) {
        Expression e0 = (Expression) visit(ctx.expression(0));
        Expression e1 = (Expression) visit(ctx.expression(1));
        if ( ctx.op.getType() == MiniliLexer.ADD)
            return new ExpBinop(position(ctx),e0, Binop.ADD,e1);
        else
            return new ExpBinop(position(ctx),e0, Binop.SUB,e1);
    }

    @Override public Ast visitLogic(MiniliParser.LogicContext ctx) {
        Expression e0 = (Expression) visit(ctx.expression(0));
        Expression e1 = (Expression) visit(ctx.expression(1));
        switch (ctx.op.getType()){
            case MiniliLexer.AND:
                return new ExpBinop(position(ctx),e0, Binop.AND,e1);
            case MiniliLexer.OR:
                return new ExpBinop(position(ctx),e0, Binop.OR,e1);
        }
        return null;
    }

    @Override public Ast visitLogicNot(MiniliParser.LogicNotContext ctx) {
        Expression e = (Expression) visit(ctx.expression());
        return new ExpUnop(position(ctx), e, Binop.NOT);
    }

    @Override public Ast visitComp(MiniliParser.CompContext ctx) {
        Expression e0 = (Expression) visit(ctx.expression(0));
        Expression e1 = (Expression) visit(ctx.expression(1));
        switch (ctx.op.getType()){
            case MiniliLexer.GT:
                return new ExpBinop(position(ctx),e0, Binop.GT,e1);
            case MiniliLexer.GTE:
                return new ExpBinop(position(ctx),e0, Binop.GTE,e1);
            case MiniliLexer.LT:
                return new ExpBinop(position(ctx),e0, Binop.LT,e1);
            case MiniliLexer.LTE:
                return new ExpBinop(position(ctx),e0, Binop.LTE,e1);
        }
        return null;
    }

    @Override public Ast visitEqual(MiniliParser.EqualContext ctx) {
        Expression e0 = (Expression) visit(ctx.expression(0));
        Expression e1 = (Expression) visit(ctx.expression(1));
        switch (ctx.op.getType()) {
            case MiniliLexer.EQ:
                return new ExpBinop(position(ctx), e0, Binop.EQ, e1);
            case MiniliLexer.NEQ:
                return new ExpBinop(position(ctx), e0, Binop.NEQ, e1);
        }
        return null;
    }

    @Override public Ast visitChar(MiniliParser.CharContext ctx) {
        return new ExpChar(position(ctx), ctx.CHAR().toString());
    }

    @Override public Ast visitBoolean(MiniliParser.BooleanContext ctx) {
        return new ExpBool(position(ctx), Bool.valueOf(ctx.BOOLEAN().toString().toUpperCase()));
    }
    @Override public Ast visitInt(MiniliParser.IntContext ctx) {
        Integer integer = Integer.parseInt(ctx.Constante().toString());
        if(ctx.SUB() != null) integer = -integer;
        return new ExpInt(position(ctx), integer);
    }

    public Ast visitType(MiniliParser.TypeContext ctx, boolean ref) {
        if(ctx.Int() != null)
            return new Type(position(ctx), (ref) ? Type.EnumType.INTREF : Type.EnumType.INTVAL);
        else if(ctx.Boolean() != null)
            return new Type(position(ctx), (ref) ? Type.EnumType.BOOLREF : Type.EnumType.BOOLVAL);
        else if(ctx.Char() != null)
            return new Type(position(ctx), (ref) ? Type.EnumType.CHARREF : Type.EnumType.CHARVAL);
        else if(ctx.Void() != null)
            return new Type(position(ctx), (ref) ? Type.EnumType.VOIDREF : Type.EnumType.VOIDVAL);
        return null;
    }

    @Override public Ast visitId(MiniliParser.IdContext ctx) {
        return new ExpVar(position(ctx), ctx.Identifiant().toString(), ctx.SUB() != null);
    }

    @Override public Ast visitIdArray(MiniliParser.IdArrayContext ctx) {
        return new ExpIdArray(position(ctx), ctx.Identifiant().toString(), (Expression) visit(ctx.expression()));
    }

    @Override public Ast visitProgram(MiniliParser.ProgramContext ctx) {
        List<Global> g = new ArrayList<>();
        List<Function> f = new ArrayList<>();

        for(ParseTree child : ctx.children){
            Ast i = visit(child);
            if(i == null) {
                System.out.println("EOF");
            }
            if(i != null && i instanceof Global){
                g.add((Global)i);
            }else if(i != null && i instanceof Function){
                f.add((Function)i);
            }
        }
        return new Program(position(ctx),g,f);
    }

    @Override public Ast visitGlobal(MiniliParser.GlobalContext ctx) {
        return new Global(position(ctx), (Affectation) visit(ctx.affectation()));
    }


    @Override public Ast visitFunction(MiniliParser.FunctionContext ctx) {
        List<Instruction> ins =  new ArrayList<>();
        ArrayList<DeclareVar> param = new ArrayList<>();

        for(MiniliParser.InstructionContext child : ctx.instruction()){
            ins.add((Instruction) visitInstruction(child));
        }

        for(int i = 1; i<ctx.type().size() ; i++) {
            boolean ref;
            if(ctx.children.indexOf(ctx.type()) > 0) {
                ParseTree term = ctx.children.get(ctx.children.indexOf(ctx.type(i))-1);
                ref = term instanceof TerminalNodeImpl && term.toString().equals("&");
            } else {
                ref = false;
            }
            Type type = (Type) this.visitType(ctx.type(i),ref);
            param.add(new DeclareVar(null,type.getType(),ctx.Identifiant(i).toString()));
        }

        Type type = (Type) this.visitType(ctx.type(0),false);

        RetExpression retExpression = null;
        try {
            retExpression = (RetExpression) ins.get(ins.size() - 1);
        } catch(ClassCastException e) {
            ErrorHandling.handleError(new CompilationException(position(ctx),"Erreur dans l'instruction return", e));
        }
        return new Function(position(ctx), retExpression,ins,param,ctx.Identifiant(0).toString(), type.getType());

    }

    @Override public Ast visitFunctionCall(MiniliParser.FunctionCallContext ctx) {
            ArrayList<Expression> param = new ArrayList<>();
            for(ParseTree child : ctx.expression()){
                Ast i = visit(child);
                if(i != null && i instanceof Expression){
                    param.add((Expression) i);
                }
            }
            return new FunctionCall(position(ctx),ctx.Identifiant(0).toString(),param);
        }


    @Override public Ast visitExpFunctionCall(MiniliParser.ExpFunctionCallContext ctx) {
        ArrayList<Expression> param = new ArrayList<>();
        for(ParseTree child : ctx.functionCall().expression()){
            Ast i = visit(child);
            if(i != null && i instanceof Expression){
                param.add((Expression) i);
            }
        }
        FunctionCall fc = new FunctionCall(position(ctx),ctx.functionCall().Identifiant(0).toString(),param);
        return new ExpFunctionCall(position(ctx), fc);
    }
}
