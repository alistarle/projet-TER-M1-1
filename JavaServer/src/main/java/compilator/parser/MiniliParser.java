package compilator.parser;// Generated from /Users/alistarle/Documents/Master/Compilation/src/Minili.g4 by ANTLR 4.5.1

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniliParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, Int=11, Boolean=12, Char=13, Void=14, BOOLEAN=15, ADD=16, SUB=17, 
		MUL=18, DIV=19, GT=20, GTE=21, LT=22, LTE=23, EQ=24, NEQ=25, AND=26, OR=27, 
		NOT=28, IF=29, ELSE=30, WHILE=31, GLOBAL=32, RETURN=33, Constante=34, 
		Identifiant=35, CHAR=36, WS=37;
	public static final int
		RULE_minili = 0, RULE_type = 1, RULE_global = 2, RULE_affectation = 3, 
		RULE_function = 4, RULE_functionCall = 5, RULE_instruction = 6, RULE_ret = 7, 
		RULE_instructionList = 8, RULE_controle = 9, RULE_expression = 10;
	public static final String[] ruleNames = {
		"minili", "type", "global", "affectation", "function", "functionCall", 
		"instruction", "ret", "instructionList", "controle", "expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'['", "']'", "'='", "'('", "'&'", "','", "')'", "'{'", "'}'", 
		"'int'", "'boolean'", "'char'", "'void'", null, "'+'", "'-'", "'*'", "'/'", 
		"'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'if'", 
		"'else'", "'while'", "'global'", "'return'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "Int", 
		"Boolean", "Char", "Void", "BOOLEAN", "ADD", "SUB", "MUL", "DIV", "GT", 
		"GTE", "LT", "LTE", "EQ", "NEQ", "AND", "OR", "NOT", "IF", "ELSE", "WHILE", 
		"GLOBAL", "RETURN", "Constante", "Identifiant", "CHAR", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Minili.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniliParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MiniliContext extends ParserRuleContext {
		public MiniliContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minili; }
	 
		public MiniliContext() { }
		public void copyFrom(MiniliContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProgramContext extends MiniliContext {
		public TerminalNode EOF() { return getToken(MiniliParser.EOF, 0); }
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public List<GlobalContext> global() {
			return getRuleContexts(GlobalContext.class);
		}
		public GlobalContext global(int i) {
			return getRuleContext(GlobalContext.class,i);
		}
		public ProgramContext(MiniliContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MiniliContext minili() throws RecognitionException {
		MiniliContext _localctx = new MiniliContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_minili);
		int _la;
		try {
			_localctx = new ProgramContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void) | (1L << GLOBAL))) != 0)) {
				{
				setState(24);
				switch (_input.LA(1)) {
				case Int:
				case Boolean:
				case Char:
				case Void:
					{
					setState(22);
					function();
					}
					break;
				case GLOBAL:
					{
					setState(23);
					global();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MiniliParser.Int, 0); }
		public TerminalNode Boolean() { return getToken(MiniliParser.Boolean, 0); }
		public TerminalNode Char() { return getToken(MiniliParser.Char, 0); }
		public TerminalNode Void() { return getToken(MiniliParser.Void, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GlobalContext extends ParserRuleContext {
		public TerminalNode GLOBAL() { return getToken(MiniliParser.GLOBAL, 0); }
		public AffectationContext affectation() {
			return getRuleContext(AffectationContext.class,0);
		}
		public GlobalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterGlobal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitGlobal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitGlobal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalContext global() throws RecognitionException {
		GlobalContext _localctx = new GlobalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_global);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(GLOBAL);
			setState(34);
			affectation();
			setState(35);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AffectationContext extends ParserRuleContext {
		public AffectationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_affectation; }
	 
		public AffectationContext() { }
		public void copyFrom(AffectationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignExpContext extends AffectationContext {
		public TerminalNode Identifiant() { return getToken(MiniliParser.Identifiant, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AssignExpContext(AffectationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterAssignExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitAssignExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitAssignExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclareTabContext extends AffectationContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> Identifiant() { return getTokens(MiniliParser.Identifiant); }
		public TerminalNode Identifiant(int i) {
			return getToken(MiniliParser.Identifiant, i);
		}
		public TerminalNode Constante() { return getToken(MiniliParser.Constante, 0); }
		public DeclareTabContext(AffectationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterDeclareTab(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitDeclareTab(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitDeclareTab(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignTabExpContext extends AffectationContext {
		public TerminalNode Identifiant() { return getToken(MiniliParser.Identifiant, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignTabExpContext(AffectationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterAssignTabExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitAssignTabExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitAssignTabExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclareVarContext extends AffectationContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifiant() { return getToken(MiniliParser.Identifiant, 0); }
		public DeclareVarContext(AffectationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterDeclareVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitDeclareVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitDeclareVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AffectationContext affectation() throws RecognitionException {
		AffectationContext _localctx = new AffectationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_affectation);
		int _la;
		try {
			setState(59);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new DeclareTabContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				type();
				setState(38);
				match(Identifiant);
				setState(39);
				match(T__1);
				setState(40);
				_la = _input.LA(1);
				if ( !(_la==Constante || _la==Identifiant) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(41);
				match(T__2);
				}
				break;
			case 2:
				_localctx = new AssignTabExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				match(Identifiant);
				setState(44);
				match(T__1);
				setState(45);
				expression(0);
				setState(46);
				match(T__2);
				setState(47);
				match(T__3);
				setState(48);
				expression(0);
				}
				break;
			case 3:
				_localctx = new AssignExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(51);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void))) != 0)) {
					{
					setState(50);
					type();
					}
				}

				setState(53);
				match(Identifiant);
				setState(54);
				match(T__3);
				setState(55);
				expression(0);
				}
				break;
			case 4:
				_localctx = new DeclareVarContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				type();
				setState(57);
				match(Identifiant);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> Identifiant() { return getTokens(MiniliParser.Identifiant); }
		public TerminalNode Identifiant(int i) {
			return getToken(MiniliParser.Identifiant, i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public RetContext ret() {
			return getRuleContext(RetContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			type();
			setState(62);
			match(Identifiant);
			setState(63);
			match(T__4);
			setState(70);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void))) != 0)) {
				{
				setState(65);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(64);
					match(T__5);
					}
				}

				setState(67);
				type();
				setState(68);
				match(Identifiant);
				}
			}

			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(72);
					match(T__6);
					}
					}
					setState(75); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__6 );
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(78);
					_la = _input.LA(1);
					if (_la==T__5) {
						{
						setState(77);
						match(T__5);
						}
					}

					setState(80);
					type();
					setState(81);
					match(Identifiant);
					}
					}
					setState(85); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void))) != 0) );
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			match(T__7);
			setState(93);
			match(T__8);
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(94);
					instruction();
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(101);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(100);
				ret();
				}
			}

			setState(103);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public List<TerminalNode> Identifiant() { return getTokens(MiniliParser.Identifiant); }
		public TerminalNode Identifiant(int i) {
			return getToken(MiniliParser.Identifiant, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AffectationContext affectation() {
			return getRuleContext(AffectationContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(107);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(105);
					affectation();
					}
					break;
				case 2:
					{
					setState(106);
					match(Identifiant);
					}
					break;
				}
				setState(109);
				match(T__3);
				}
				break;
			}
			setState(112);
			match(Identifiant);
			setState(113);
			match(T__4);
			setState(118);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void) | (1L << BOOLEAN) | (1L << SUB) | (1L << NOT) | (1L << Constante) | (1L << Identifiant) | (1L << CHAR))) != 0)) {
				{
				setState(115);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(114);
					match(T__5);
					}
				}

				setState(117);
				expression(0);
				}
			}

			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120);
					match(T__6);
					}
					}
					setState(123); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__6 );
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(126);
					_la = _input.LA(1);
					if (_la==T__5) {
						{
						setState(125);
						match(T__5);
						}
					}

					setState(128);
					expression(0);
					}
					}
					setState(131); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void) | (1L << BOOLEAN) | (1L << SUB) | (1L << NOT) | (1L << Constante) | (1L << Identifiant) | (1L << CHAR))) != 0) );
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(138);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public AffectationContext affectation() {
			return getRuleContext(AffectationContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ControleContext controle() {
			return getRuleContext(ControleContext.class,0);
		}
		public RetContext ret() {
			return getRuleContext(RetContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_instruction);
		try {
			setState(148);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				affectation();
				setState(141);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				functionCall();
				setState(144);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				controle();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				ret();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RetContext extends ParserRuleContext {
		public RetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ret; }
	 
		public RetContext() { }
		public void copyFrom(RetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnExpContext extends RetContext {
		public TerminalNode RETURN() { return getToken(MiniliParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnExpContext(RetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterReturnExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitReturnExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitReturnExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnOnlyContext extends RetContext {
		public TerminalNode RETURN() { return getToken(MiniliParser.RETURN, 0); }
		public ReturnOnlyContext(RetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterReturnOnly(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitReturnOnly(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitReturnOnly(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetContext ret() throws RecognitionException {
		RetContext _localctx = new RetContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ret);
		try {
			setState(156);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new ReturnExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(RETURN);
				setState(151);
				expression(0);
				setState(152);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new ReturnOnlyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(RETURN);
				setState(155);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionListContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public InstructionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterInstructionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitInstructionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitInstructionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionListContext instructionList() throws RecognitionException {
		InstructionListContext _localctx = new InstructionListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_instructionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void) | (1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << Identifiant))) != 0)) {
				{
				{
				setState(158);
				instruction();
				}
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ControleContext extends ParserRuleContext {
		public ControleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controle; }
	 
		public ControleContext() { }
		public void copyFrom(ControleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ControleIfContext extends ControleContext {
		public TerminalNode IF() { return getToken(MiniliParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<InstructionListContext> instructionList() {
			return getRuleContexts(InstructionListContext.class);
		}
		public InstructionListContext instructionList(int i) {
			return getRuleContext(InstructionListContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MiniliParser.ELSE, 0); }
		public ControleIfContext(ControleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterControleIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitControleIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitControleIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ControleWhileContext extends ControleContext {
		public TerminalNode WHILE() { return getToken(MiniliParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public ControleWhileContext(ControleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterControleWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitControleWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitControleWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ControleContext controle() throws RecognitionException {
		ControleContext _localctx = new ControleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_controle);
		int _la;
		try {
			setState(191);
			switch (_input.LA(1)) {
			case IF:
				_localctx = new ControleIfContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(IF);
				setState(165);
				match(T__4);
				setState(166);
				expression(0);
				setState(167);
				match(T__7);
				setState(168);
				match(T__8);
				setState(169);
				instructionList();
				setState(170);
				match(T__9);
				setState(176);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(171);
					match(ELSE);
					setState(172);
					match(T__8);
					setState(173);
					instructionList();
					setState(174);
					match(T__9);
					}
				}

				}
				break;
			case WHILE:
				_localctx = new ControleWhileContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				match(WHILE);
				setState(179);
				match(T__4);
				setState(180);
				expression(0);
				setState(181);
				match(T__7);
				setState(182);
				match(T__8);
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Boolean) | (1L << Char) | (1L << Void) | (1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << Identifiant))) != 0)) {
					{
					{
					setState(183);
					instruction();
					}
					}
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(189);
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterPar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitPar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitPar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(MiniliParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MiniliParser.DIV, 0); }
		public MulDivContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(MiniliParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MiniliParser.SUB, 0); }
		public AddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpFunctionCallContext extends ExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ExpFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterExpFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitExpFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitExpFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdArrayContext extends ExpressionContext {
		public TerminalNode Identifiant() { return getToken(MiniliParser.Identifiant, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterIdArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitIdArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitIdArray(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ExpressionContext {
		public TerminalNode Constante() { return getToken(MiniliParser.Constante, 0); }
		public TerminalNode SUB() { return getToken(MiniliParser.SUB, 0); }
		public IntContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GT() { return getToken(MiniliParser.GT, 0); }
		public TerminalNode GTE() { return getToken(MiniliParser.GTE, 0); }
		public TerminalNode LT() { return getToken(MiniliParser.LT, 0); }
		public TerminalNode LTE() { return getToken(MiniliParser.LTE, 0); }
		public CompContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicNotContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(MiniliParser.NOT, 0); }
		public LogicNotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterLogicNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitLogicNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitLogicNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQ() { return getToken(MiniliParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(MiniliParser.NEQ, 0); }
		public EqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharContext extends ExpressionContext {
		public TerminalNode CHAR() { return getToken(MiniliParser.CHAR, 0); }
		public CharContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitChar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdContext extends ExpressionContext {
		public TerminalNode Identifiant() { return getToken(MiniliParser.Identifiant, 0); }
		public TerminalNode SUB() { return getToken(MiniliParser.SUB, 0); }
		public IdContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanContext extends ExpressionContext {
		public TerminalNode BOOLEAN() { return getToken(MiniliParser.BOOLEAN, 0); }
		public BooleanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(MiniliParser.AND, 0); }
		public TerminalNode OR() { return getToken(MiniliParser.OR, 0); }
		public LogicContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).enterLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniliListener) ((MiniliListener)listener).exitLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniliVisitor) return ((MiniliVisitor<? extends T>)visitor).visitLogic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				_localctx = new LogicNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(194);
				((LogicNotContext)_localctx).op = match(NOT);
				setState(195);
				expression(8);
				}
				break;
			case 2:
				{
				_localctx = new IdArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(196);
				match(Identifiant);
				setState(197);
				match(T__1);
				setState(198);
				expression(0);
				setState(199);
				match(T__2);
				}
				break;
			case 3:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(201);
				match(BOOLEAN);
				}
				break;
			case 4:
				{
				_localctx = new CharContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(202);
				match(CHAR);
				}
				break;
			case 5:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(204);
				_la = _input.LA(1);
				if (_la==SUB) {
					{
					setState(203);
					match(SUB);
					}
				}

				setState(206);
				match(Constante);
				}
				break;
			case 6:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(208);
				_la = _input.LA(1);
				if (_la==SUB) {
					{
					setState(207);
					match(SUB);
					}
				}

				setState(210);
				match(Identifiant);
				}
				break;
			case 7:
				{
				_localctx = new ParContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(211);
				match(T__4);
				setState(212);
				expression(0);
				setState(213);
				match(T__7);
				}
				break;
			case 8:
				{
				_localctx = new ExpFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(215);
				functionCall();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(233);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(218);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(219);
						((MulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(220);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(221);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(222);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(223);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new CompContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(224);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(225);
						((CompContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GTE) | (1L << LT) | (1L << LTE))) != 0)) ) {
							((CompContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(226);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new EqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(227);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(228);
						((EqualContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((EqualContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(229);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new LogicContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(230);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(231);
						((LogicContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((LogicContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(232);
						expression(10);
						}
						break;
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u00f1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\7\2\33\n\2\f\2\16\2\36\13\2\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5"+
		"\66\n\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\3\6\3\6\5\6D\n\6\3\6"+
		"\3\6\3\6\5\6I\n\6\3\6\6\6L\n\6\r\6\16\6M\3\6\5\6Q\n\6\3\6\3\6\3\6\6\6"+
		"V\n\6\r\6\16\6W\7\6Z\n\6\f\6\16\6]\13\6\3\6\3\6\3\6\7\6b\n\6\f\6\16\6"+
		"e\13\6\3\6\5\6h\n\6\3\6\3\6\3\7\3\7\5\7n\n\7\3\7\5\7q\n\7\3\7\3\7\3\7"+
		"\5\7v\n\7\3\7\5\7y\n\7\3\7\6\7|\n\7\r\7\16\7}\3\7\5\7\u0081\n\7\3\7\6"+
		"\7\u0084\n\7\r\7\16\7\u0085\7\7\u0088\n\7\f\7\16\7\u008b\13\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0097\n\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\5\t\u009f\n\t\3\n\7\n\u00a2\n\n\f\n\16\n\u00a5\13\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b3\n\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\7\13\u00bb\n\13\f\13\16\13\u00be\13\13\3\13\3\13"+
		"\5\13\u00c2\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00cf"+
		"\n\f\3\f\3\f\5\f\u00d3\n\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00db\n\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00ec\n\f\f"+
		"\f\16\f\u00ef\13\f\3\f\2\3\26\r\2\4\6\b\n\f\16\20\22\24\26\2\t\3\2\r\20"+
		"\3\2$%\3\2\24\25\3\2\22\23\3\2\26\31\3\2\32\33\3\2\34\35\u0111\2\34\3"+
		"\2\2\2\4!\3\2\2\2\6#\3\2\2\2\b=\3\2\2\2\n?\3\2\2\2\fp\3\2\2\2\16\u0096"+
		"\3\2\2\2\20\u009e\3\2\2\2\22\u00a3\3\2\2\2\24\u00c1\3\2\2\2\26\u00da\3"+
		"\2\2\2\30\33\5\n\6\2\31\33\5\6\4\2\32\30\3\2\2\2\32\31\3\2\2\2\33\36\3"+
		"\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\34\3\2\2\2\37 \7\2"+
		"\2\3 \3\3\2\2\2!\"\t\2\2\2\"\5\3\2\2\2#$\7\"\2\2$%\5\b\5\2%&\7\3\2\2&"+
		"\7\3\2\2\2\'(\5\4\3\2()\7%\2\2)*\7\4\2\2*+\t\3\2\2+,\7\5\2\2,>\3\2\2\2"+
		"-.\7%\2\2./\7\4\2\2/\60\5\26\f\2\60\61\7\5\2\2\61\62\7\6\2\2\62\63\5\26"+
		"\f\2\63>\3\2\2\2\64\66\5\4\3\2\65\64\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2"+
		"\2\678\7%\2\289\7\6\2\29>\5\26\f\2:;\5\4\3\2;<\7%\2\2<>\3\2\2\2=\'\3\2"+
		"\2\2=-\3\2\2\2=\65\3\2\2\2=:\3\2\2\2>\t\3\2\2\2?@\5\4\3\2@A\7%\2\2AH\7"+
		"\7\2\2BD\7\b\2\2CB\3\2\2\2CD\3\2\2\2DE\3\2\2\2EF\5\4\3\2FG\7%\2\2GI\3"+
		"\2\2\2HC\3\2\2\2HI\3\2\2\2I[\3\2\2\2JL\7\t\2\2KJ\3\2\2\2LM\3\2\2\2MK\3"+
		"\2\2\2MN\3\2\2\2NU\3\2\2\2OQ\7\b\2\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\5"+
		"\4\3\2ST\7%\2\2TV\3\2\2\2UP\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XZ\3"+
		"\2\2\2YK\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^"+
		"_\7\n\2\2_c\7\13\2\2`b\5\16\b\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2"+
		"\2dg\3\2\2\2ec\3\2\2\2fh\5\20\t\2gf\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\7\f"+
		"\2\2j\13\3\2\2\2kn\5\b\5\2ln\7%\2\2mk\3\2\2\2ml\3\2\2\2no\3\2\2\2oq\7"+
		"\6\2\2pm\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\7%\2\2sx\7\7\2\2tv\7\b\2\2ut\3"+
		"\2\2\2uv\3\2\2\2vw\3\2\2\2wy\5\26\f\2xu\3\2\2\2xy\3\2\2\2y\u0089\3\2\2"+
		"\2z|\7\t\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0083\3\2\2\2\177"+
		"\u0081\7\b\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2"+
		"\2\u0082\u0084\5\26\f\2\u0083\u0080\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087{\3\2\2\2"+
		"\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c"+
		"\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\7\n\2\2\u008d\r\3\2\2\2\u008e"+
		"\u008f\5\b\5\2\u008f\u0090\7\3\2\2\u0090\u0097\3\2\2\2\u0091\u0092\5\f"+
		"\7\2\u0092\u0093\7\3\2\2\u0093\u0097\3\2\2\2\u0094\u0097\5\24\13\2\u0095"+
		"\u0097\5\20\t\2\u0096\u008e\3\2\2\2\u0096\u0091\3\2\2\2\u0096\u0094\3"+
		"\2\2\2\u0096\u0095\3\2\2\2\u0097\17\3\2\2\2\u0098\u0099\7#\2\2\u0099\u009a"+
		"\5\26\f\2\u009a\u009b\7\3\2\2\u009b\u009f\3\2\2\2\u009c\u009d\7#\2\2\u009d"+
		"\u009f\7\3\2\2\u009e\u0098\3\2\2\2\u009e\u009c\3\2\2\2\u009f\21\3\2\2"+
		"\2\u00a0\u00a2\5\16\b\2\u00a1\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\23\3\2\2\2\u00a5\u00a3\3\2\2"+
		"\2\u00a6\u00a7\7\37\2\2\u00a7\u00a8\7\7\2\2\u00a8\u00a9\5\26\f\2\u00a9"+
		"\u00aa\7\n\2\2\u00aa\u00ab\7\13\2\2\u00ab\u00ac\5\22\n\2\u00ac\u00b2\7"+
		"\f\2\2\u00ad\u00ae\7 \2\2\u00ae\u00af\7\13\2\2\u00af\u00b0\5\22\n\2\u00b0"+
		"\u00b1\7\f\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00ad\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\u00c2\3\2\2\2\u00b4\u00b5\7!\2\2\u00b5\u00b6\7\7\2\2\u00b6"+
		"\u00b7\5\26\f\2\u00b7\u00b8\7\n\2\2\u00b8\u00bc\7\13\2\2\u00b9\u00bb\5"+
		"\16\b\2\u00ba\u00b9\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7\f"+
		"\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00a6\3\2\2\2\u00c1\u00b4\3\2\2\2\u00c2"+
		"\25\3\2\2\2\u00c3\u00c4\b\f\1\2\u00c4\u00c5\7\36\2\2\u00c5\u00db\5\26"+
		"\f\n\u00c6\u00c7\7%\2\2\u00c7\u00c8\7\4\2\2\u00c8\u00c9\5\26\f\2\u00c9"+
		"\u00ca\7\5\2\2\u00ca\u00db\3\2\2\2\u00cb\u00db\7\21\2\2\u00cc\u00db\7"+
		"&\2\2\u00cd\u00cf\7\23\2\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00db\7$\2\2\u00d1\u00d3\7\23\2\2\u00d2\u00d1\3\2"+
		"\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00db\7%\2\2\u00d5"+
		"\u00d6\7\7\2\2\u00d6\u00d7\5\26\f\2\u00d7\u00d8\7\n\2\2\u00d8\u00db\3"+
		"\2\2\2\u00d9\u00db\5\f\7\2\u00da\u00c3\3\2\2\2\u00da\u00c6\3\2\2\2\u00da"+
		"\u00cb\3\2\2\2\u00da\u00cc\3\2\2\2\u00da\u00ce\3\2\2\2\u00da\u00d2\3\2"+
		"\2\2\u00da\u00d5\3\2\2\2\u00da\u00d9\3\2\2\2\u00db\u00ed\3\2\2\2\u00dc"+
		"\u00dd\f\17\2\2\u00dd\u00de\t\4\2\2\u00de\u00ec\5\26\f\20\u00df\u00e0"+
		"\f\16\2\2\u00e0\u00e1\t\5\2\2\u00e1\u00ec\5\26\f\17\u00e2\u00e3\f\r\2"+
		"\2\u00e3\u00e4\t\6\2\2\u00e4\u00ec\5\26\f\16\u00e5\u00e6\f\f\2\2\u00e6"+
		"\u00e7\t\7\2\2\u00e7\u00ec\5\26\f\r\u00e8\u00e9\f\13\2\2\u00e9\u00ea\t"+
		"\b\2\2\u00ea\u00ec\5\26\f\f\u00eb\u00dc\3\2\2\2\u00eb\u00df\3\2\2\2\u00eb"+
		"\u00e2\3\2\2\2\u00eb\u00e5\3\2\2\2\u00eb\u00e8\3\2\2\2\u00ec\u00ef\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\27\3\2\2\2\u00ef\u00ed"+
		"\3\2\2\2!\32\34\65=CHMPW[cgmpux}\u0080\u0085\u0089\u0096\u009e\u00a3\u00b2"+
		"\u00bc\u00c1\u00ce\u00d2\u00da\u00eb\u00ed";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}