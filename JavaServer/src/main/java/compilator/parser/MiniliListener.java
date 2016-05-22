package compilator.parser;// Generated from /Users/alistarle/Documents/Master/Compilation/src/Minili.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniliParser}.
 */
public interface MiniliListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Program}
	 * labeled alternative in {@link MiniliParser#minili}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MiniliParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link MiniliParser#minili}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MiniliParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniliParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MiniliParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniliParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MiniliParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniliParser#global}.
	 * @param ctx the parse tree
	 */
	void enterGlobal(MiniliParser.GlobalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniliParser#global}.
	 * @param ctx the parse tree
	 */
	void exitGlobal(MiniliParser.GlobalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclareTab}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void enterDeclareTab(MiniliParser.DeclareTabContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclareTab}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void exitDeclareTab(MiniliParser.DeclareTabContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignTabExp}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void enterAssignTabExp(MiniliParser.AssignTabExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignTabExp}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void exitAssignTabExp(MiniliParser.AssignTabExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignExp}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void enterAssignExp(MiniliParser.AssignExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignExp}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void exitAssignExp(MiniliParser.AssignExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclareVar}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void enterDeclareVar(MiniliParser.DeclareVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclareVar}
	 * labeled alternative in {@link MiniliParser#affectation}.
	 * @param ctx the parse tree
	 */
	void exitDeclareVar(MiniliParser.DeclareVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniliParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MiniliParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniliParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MiniliParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniliParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(MiniliParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniliParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(MiniliParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniliParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(MiniliParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniliParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(MiniliParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnExp}
	 * labeled alternative in {@link MiniliParser#ret}.
	 * @param ctx the parse tree
	 */
	void enterReturnExp(MiniliParser.ReturnExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnExp}
	 * labeled alternative in {@link MiniliParser#ret}.
	 * @param ctx the parse tree
	 */
	void exitReturnExp(MiniliParser.ReturnExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnOnly}
	 * labeled alternative in {@link MiniliParser#ret}.
	 * @param ctx the parse tree
	 */
	void enterReturnOnly(MiniliParser.ReturnOnlyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnOnly}
	 * labeled alternative in {@link MiniliParser#ret}.
	 * @param ctx the parse tree
	 */
	void exitReturnOnly(MiniliParser.ReturnOnlyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniliParser#instructionList}.
	 * @param ctx the parse tree
	 */
	void enterInstructionList(MiniliParser.InstructionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniliParser#instructionList}.
	 * @param ctx the parse tree
	 */
	void exitInstructionList(MiniliParser.InstructionListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ControleIf}
	 * labeled alternative in {@link MiniliParser#controle}.
	 * @param ctx the parse tree
	 */
	void enterControleIf(MiniliParser.ControleIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ControleIf}
	 * labeled alternative in {@link MiniliParser#controle}.
	 * @param ctx the parse tree
	 */
	void exitControleIf(MiniliParser.ControleIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ControleWhile}
	 * labeled alternative in {@link MiniliParser#controle}.
	 * @param ctx the parse tree
	 */
	void enterControleWhile(MiniliParser.ControleWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ControleWhile}
	 * labeled alternative in {@link MiniliParser#controle}.
	 * @param ctx the parse tree
	 */
	void exitControleWhile(MiniliParser.ControleWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Par}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPar(MiniliParser.ParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Par}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPar(MiniliParser.ParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(MiniliParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(MiniliParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(MiniliParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(MiniliParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpFunctionCall}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpFunctionCall(MiniliParser.ExpFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpFunctionCall}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpFunctionCall(MiniliParser.ExpFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdArray}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdArray(MiniliParser.IdArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdArray}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdArray(MiniliParser.IdArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInt(MiniliParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInt(MiniliParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Comp}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComp(MiniliParser.CompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Comp}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComp(MiniliParser.CompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicNot}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicNot(MiniliParser.LogicNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicNot}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicNot(MiniliParser.LogicNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqual(MiniliParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqual(MiniliParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Char}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterChar(MiniliParser.CharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Char}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitChar(MiniliParser.CharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterId(MiniliParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitId(MiniliParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(MiniliParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(MiniliParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogic(MiniliParser.LogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link MiniliParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogic(MiniliParser.LogicContext ctx);
}