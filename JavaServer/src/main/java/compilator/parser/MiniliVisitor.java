package compilator.parser;// Generated from /Users/alistarle/Documents/Master/Compilation/src/Minili.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniliParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniliVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link MiniliParser#minili}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MiniliParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniliParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MiniliParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniliParser#global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal(MiniliParser.GlobalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclareTab}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareTab(MiniliParser.DeclareTabContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignTabExp}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignTabExp(MiniliParser.AssignTabExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignExp}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExp(MiniliParser.AssignExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclareVar}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareVar(MiniliParser.DeclareVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniliParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(MiniliParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniliParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(MiniliParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniliParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(MiniliParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnExp}
	 * labeled alternative in {@link MiniliParser#ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnExp(MiniliParser.ReturnExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnOnly}
	 * labeled alternative in {@link MiniliParser#ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnOnly(MiniliParser.ReturnOnlyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniliParser#instructionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionList(MiniliParser.InstructionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ControleIf}
	 * labeled alternative in {@link MiniliParser#controle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControleIf(MiniliParser.ControleIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ControleWhile}
	 * labeled alternative in {@link MiniliParser#controle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControleWhile(MiniliParser.ControleWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Par}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPar(MiniliParser.ParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(MiniliParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(MiniliParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpFunctionCall}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFunctionCall(MiniliParser.ExpFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdArray}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdArray(MiniliParser.IdArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(MiniliParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Comp}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp(MiniliParser.CompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicNot}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicNot(MiniliParser.LogicNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(MiniliParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Char}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar(MiniliParser.CharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(MiniliParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(MiniliParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic(MiniliParser.LogicContext ctx);
}