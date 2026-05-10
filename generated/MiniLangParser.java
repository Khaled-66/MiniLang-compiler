// Generated from MiniLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MiniLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, PRINT=24, 
		IF=25, ELSE=26, WHILE=27, FOR=28, SWITCH=29, CASE=30, DEFAULT=31, BREAK=32, 
		FUNCTION=33, RETURN=34, INPUT=35, BOOL=36, ID=37, NUMBER=38, STRING=39, 
		LINE_COMMENT=40, BLOCK_COMMENT=41, WS=42;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_assignmentStmt = 2, RULE_arrayAssignStmt = 3, 
		RULE_printStmt = 4, RULE_inputStmt = 5, RULE_ifStmt = 6, RULE_whileStmt = 7, 
		RULE_forStmt = 8, RULE_forInit = 9, RULE_forUpdate = 10, RULE_switchStmt = 11, 
		RULE_caseClause = 12, RULE_defaultClause = 13, RULE_functionDecl = 14, 
		RULE_paramList = 15, RULE_returnStmt = 16, RULE_breakStmt = 17, RULE_exprStmt = 18, 
		RULE_block = 19, RULE_expr = 20, RULE_atom = 21, RULE_argList = 22, RULE_literal = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "assignmentStmt", "arrayAssignStmt", "printStmt", 
			"inputStmt", "ifStmt", "whileStmt", "forStmt", "forInit", "forUpdate", 
			"switchStmt", "caseClause", "defaultClause", "functionDecl", "paramList", 
			"returnStmt", "breakStmt", "exprStmt", "block", "expr", "atom", "argList", 
			"literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "';'", "'['", "']'", "'('", "')'", "'{'", "'}'", "':'", 
			"','", "'*'", "'/'", "'+'", "'-'", "'>'", "'<'", "'>='", "'<='", "'=='", 
			"'!='", "'&&'", "'||'", "'!'", "'print'", "'if'", "'else'", "'while'", 
			"'for'", "'switch'", "'case'", "'default'", "'break'", "'function'", 
			"'return'", "'input'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"PRINT", "IF", "ELSE", "WHILE", "FOR", "SWITCH", "CASE", "DEFAULT", "BREAK", 
			"FUNCTION", "RETURN", "INPUT", "BOOL", "ID", "NUMBER", "STRING", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "MiniLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MiniLangParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1061855182888L) != 0)) {
				{
				{
				setState(48);
				statement();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AssignmentStmtContext assignmentStmt() {
			return getRuleContext(AssignmentStmtContext.class,0);
		}
		public ArrayAssignStmtContext arrayAssignStmt() {
			return getRuleContext(ArrayAssignStmtContext.class,0);
		}
		public InputStmtContext inputStmt() {
			return getRuleContext(InputStmtContext.class,0);
		}
		public PrintStmtContext printStmt() {
			return getRuleContext(PrintStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public SwitchStmtContext switchStmt() {
			return getRuleContext(SwitchStmtContext.class,0);
		}
		public FunctionDeclContext functionDecl() {
			return getRuleContext(FunctionDeclContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				assignmentStmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				arrayAssignStmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				inputStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				printStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				ifStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				whileStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(62);
				forStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(63);
				switchStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(64);
				functionDecl();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(65);
				returnStmt();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(66);
				breakStmt();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(67);
				exprStmt();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStmtContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterAssignmentStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitAssignmentStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitAssignmentStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStmtContext assignmentStmt() throws RecognitionException {
		AssignmentStmtContext _localctx = new AssignmentStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignmentStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(ID);
			setState(71);
			match(T__0);
			setState(72);
			expr(0);
			setState(73);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAssignStmtContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayAssignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayAssignStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterArrayAssignStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitArrayAssignStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitArrayAssignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayAssignStmtContext arrayAssignStmt() throws RecognitionException {
		ArrayAssignStmtContext _localctx = new ArrayAssignStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arrayAssignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(ID);
			setState(76);
			match(T__2);
			setState(77);
			expr(0);
			setState(78);
			match(T__3);
			setState(79);
			match(T__0);
			setState(80);
			expr(0);
			setState(81);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrintStmtContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(MiniLangParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterPrintStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitPrintStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitPrintStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStmtContext printStmt() throws RecognitionException {
		PrintStmtContext _localctx = new PrintStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_printStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(PRINT);
			setState(84);
			expr(0);
			setState(85);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class InputStmtContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public TerminalNode INPUT() { return getToken(MiniLangParser.INPUT, 0); }
		public TerminalNode STRING() { return getToken(MiniLangParser.STRING, 0); }
		public InputStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterInputStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitInputStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitInputStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputStmtContext inputStmt() throws RecognitionException {
		InputStmtContext _localctx = new InputStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_inputStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ID);
			setState(88);
			match(T__0);
			setState(89);
			match(INPUT);
			setState(90);
			match(T__4);
			setState(91);
			match(STRING);
			setState(92);
			match(T__5);
			setState(93);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MiniLangParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MiniLangParser.ELSE, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(IF);
			setState(96);
			match(T__4);
			setState(97);
			expr(0);
			setState(98);
			match(T__5);
			setState(99);
			block();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(100);
				match(ELSE);
				setState(101);
				block();
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MiniLangParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(WHILE);
			setState(105);
			match(T__4);
			setState(106);
			expr(0);
			setState(107);
			match(T__5);
			setState(108);
			block();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MiniLangParser.FOR, 0); }
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(FOR);
			setState(111);
			match(T__4);
			setState(112);
			forInit();
			setState(113);
			expr(0);
			setState(114);
			match(T__1);
			setState(115);
			forUpdate();
			setState(116);
			match(T__5);
			setState(117);
			block();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ForInitContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_forInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(ID);
			setState(120);
			match(T__0);
			setState(121);
			expr(0);
			setState(122);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ForUpdateContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitForUpdate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitForUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(ID);
			setState(125);
			match(T__0);
			setState(126);
			expr(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStmtContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(MiniLangParser.SWITCH, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CaseClauseContext> caseClause() {
			return getRuleContexts(CaseClauseContext.class);
		}
		public CaseClauseContext caseClause(int i) {
			return getRuleContext(CaseClauseContext.class,i);
		}
		public DefaultClauseContext defaultClause() {
			return getRuleContext(DefaultClauseContext.class,0);
		}
		public SwitchStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitSwitchStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitSwitchStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStmtContext switchStmt() throws RecognitionException {
		SwitchStmtContext _localctx = new SwitchStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_switchStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(SWITCH);
			setState(129);
			match(T__4);
			setState(130);
			expr(0);
			setState(131);
			match(T__5);
			setState(132);
			match(T__6);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(133);
				caseClause();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(139);
				defaultClause();
				}
			}

			setState(142);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CaseClauseContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(MiniLangParser.CASE, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public CaseClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitCaseClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitCaseClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseClauseContext caseClause() throws RecognitionException {
		CaseClauseContext _localctx = new CaseClauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_caseClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(CASE);
			setState(145);
			literal();
			setState(146);
			match(T__8);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1061855182888L) != 0)) {
				{
				{
				setState(147);
				statement();
				}
				}
				setState(152);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultClauseContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(MiniLangParser.DEFAULT, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public DefaultClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterDefaultClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitDefaultClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitDefaultClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultClauseContext defaultClause() throws RecognitionException {
		DefaultClauseContext _localctx = new DefaultClauseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_defaultClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(DEFAULT);
			setState(154);
			match(T__8);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1061855182888L) != 0)) {
				{
				{
				setState(155);
				statement();
				}
				}
				setState(160);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(MiniLangParser.FUNCTION, 0); }
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FunctionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitFunctionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitFunctionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclContext functionDecl() throws RecognitionException {
		FunctionDeclContext _localctx = new FunctionDeclContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_functionDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(FUNCTION);
			setState(162);
			match(ID);
			setState(163);
			match(T__4);
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(164);
				paramList();
				}
			}

			setState(167);
			match(T__5);
			setState(168);
			block();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(MiniLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MiniLangParser.ID, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(ID);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(171);
				match(T__9);
				setState(172);
				match(ID);
				}
				}
				setState(177);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MiniLangParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_returnStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(RETURN);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1030800556072L) != 0)) {
				{
				setState(179);
				expr(0);
				}
			}

			setState(182);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStmtContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(MiniLangParser.BREAK, 0); }
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(BREAK);
			setState(185);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprStmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprStmtContext exprStmt() throws RecognitionException {
		ExprStmtContext _localctx = new ExprStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			expr(0);
			setState(188);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(T__6);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1061855182888L) != 0)) {
				{
				{
				setState(191);
				statement();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulDivExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterMulDivExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitMulDivExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitMulDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualityExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelationalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitRelationalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomExprContext extends ExprContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterAddSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitAddSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryMinusExprContext extends ExprContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public UnaryMinusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterUnaryMinusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitUnaryMinusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitUnaryMinusExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__22:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(200);
				match(T__22);
				setState(201);
				expr(3);
				}
				break;
			case T__13:
				{
				_localctx = new UnaryMinusExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(202);
				match(T__13);
				setState(203);
				atom();
				}
				break;
			case T__2:
			case T__4:
			case BOOL:
			case ID:
			case NUMBER:
			case STRING:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(204);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(227);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(225);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(207);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(208);
						((MulDivExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__10 || _la==T__11) ) {
							((MulDivExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(209);
						expr(10);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(210);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(211);
						((AddSubExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
							((AddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(212);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(213);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(214);
						((RelationalExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 491520L) != 0)) ) {
							((RelationalExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(215);
						expr(8);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(217);
						((EqualityExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__18 || _la==T__19) ) {
							((EqualityExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(218);
						expr(7);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(219);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(220);
						match(T__20);
						setState(221);
						expr(6);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(222);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(223);
						match(T__21);
						setState(224);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(229);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallAtomContext extends AtomContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public FunctionCallAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterFunctionCallAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitFunctionCallAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitFunctionCallAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VariableAtomContext extends AtomContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public VariableAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterVariableAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitVariableAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitVariableAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenAtomContext extends AtomContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterParenAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitParenAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitParenAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralAtomContext extends AtomContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterLiteralAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitLiteralAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitLiteralAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessAtomContext extends AtomContext {
		public TerminalNode ID() { return getToken(MiniLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayAccessAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterArrayAccessAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitArrayAccessAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitArrayAccessAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralAtomContext extends AtomContext {
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public ArrayLiteralAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterArrayLiteralAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitArrayLiteralAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitArrayLiteralAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_atom);
		int _la;
		try {
			setState(252);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new ParenAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				match(T__4);
				setState(231);
				expr(0);
				setState(232);
				match(T__5);
				}
				break;
			case 2:
				_localctx = new FunctionCallAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				match(ID);
				setState(235);
				match(T__4);
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1030800556072L) != 0)) {
					{
					setState(236);
					argList();
					}
				}

				setState(239);
				match(T__5);
				}
				break;
			case 3:
				_localctx = new ArrayAccessAtomContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(240);
				match(ID);
				setState(241);
				match(T__2);
				setState(242);
				expr(0);
				setState(243);
				match(T__3);
				}
				break;
			case 4:
				_localctx = new ArrayLiteralAtomContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(245);
				match(T__2);
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1030800556072L) != 0)) {
					{
					setState(246);
					argList();
					}
				}

				setState(249);
				match(T__3);
				}
				break;
			case 5:
				_localctx = new LiteralAtomContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(250);
				literal();
				}
				break;
			case 6:
				_localctx = new VariableAtomContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(251);
				match(ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			expr(0);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(255);
				match(T__9);
				setState(256);
				expr(0);
				}
				}
				setState(261);
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

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode STRING() { return getToken(MiniLangParser.STRING, 0); }
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolLiteralContext extends LiteralContext {
		public TerminalNode BOOL() { return getToken(MiniLangParser.BOOL, 0); }
		public BoolLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberLiteralContext extends LiteralContext {
		public TerminalNode NUMBER() { return getToken(MiniLangParser.NUMBER, 0); }
		public NumberLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniLangListener ) ((MiniLangListener)listener).exitNumberLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniLangVisitor ) return ((MiniLangVisitor<? extends T>)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_literal);
		try {
			setState(265);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				_localctx = new NumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(262);
				match(NUMBER);
				}
				break;
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(STRING);
				}
				break;
			case BOOL:
				_localctx = new BoolLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(264);
				match(BOOL);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001*\u010c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0005\u0000"+
		"2\b\u0000\n\u0000\f\u00005\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"E\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006g\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u0087\b\u000b\n\u000b\f\u000b\u008a\t\u000b\u0001\u000b\u0003\u000b\u008d"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u0095\b\f\n\f\f\f\u0098\t\f\u0001\r\u0001\r\u0001\r\u0005\r\u009d\b"+
		"\r\n\r\f\r\u00a0\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u00a6\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u00ae\b\u000f\n\u000f\f\u000f\u00b1\t\u000f"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00b5\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u00c1\b\u0013\n\u0013\f\u0013\u00c4"+
		"\t\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00ce\b\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00e2"+
		"\b\u0014\n\u0014\f\u0014\u00e5\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00ee\b\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u00f8\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u00fd\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0005\u0016\u0102\b\u0016\n\u0016\f\u0016\u0105\t\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u010a\b\u0017\u0001\u0017\u0000\u0001("+
		"\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.\u0000\u0004\u0001\u0000\u000b\f\u0001\u0000"+
		"\r\u000e\u0001\u0000\u000f\u0012\u0001\u0000\u0013\u0014\u011a\u00003"+
		"\u0001\u0000\u0000\u0000\u0002D\u0001\u0000\u0000\u0000\u0004F\u0001\u0000"+
		"\u0000\u0000\u0006K\u0001\u0000\u0000\u0000\bS\u0001\u0000\u0000\u0000"+
		"\nW\u0001\u0000\u0000\u0000\f_\u0001\u0000\u0000\u0000\u000eh\u0001\u0000"+
		"\u0000\u0000\u0010n\u0001\u0000\u0000\u0000\u0012w\u0001\u0000\u0000\u0000"+
		"\u0014|\u0001\u0000\u0000\u0000\u0016\u0080\u0001\u0000\u0000\u0000\u0018"+
		"\u0090\u0001\u0000\u0000\u0000\u001a\u0099\u0001\u0000\u0000\u0000\u001c"+
		"\u00a1\u0001\u0000\u0000\u0000\u001e\u00aa\u0001\u0000\u0000\u0000 \u00b2"+
		"\u0001\u0000\u0000\u0000\"\u00b8\u0001\u0000\u0000\u0000$\u00bb\u0001"+
		"\u0000\u0000\u0000&\u00be\u0001\u0000\u0000\u0000(\u00cd\u0001\u0000\u0000"+
		"\u0000*\u00fc\u0001\u0000\u0000\u0000,\u00fe\u0001\u0000\u0000\u0000."+
		"\u0109\u0001\u0000\u0000\u000002\u0003\u0002\u0001\u000010\u0001\u0000"+
		"\u0000\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001"+
		"\u0000\u0000\u000046\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u0000"+
		"67\u0005\u0000\u0000\u00017\u0001\u0001\u0000\u0000\u00008E\u0003\u0004"+
		"\u0002\u00009E\u0003\u0006\u0003\u0000:E\u0003\n\u0005\u0000;E\u0003\b"+
		"\u0004\u0000<E\u0003\f\u0006\u0000=E\u0003\u000e\u0007\u0000>E\u0003\u0010"+
		"\b\u0000?E\u0003\u0016\u000b\u0000@E\u0003\u001c\u000e\u0000AE\u0003 "+
		"\u0010\u0000BE\u0003\"\u0011\u0000CE\u0003$\u0012\u0000D8\u0001\u0000"+
		"\u0000\u0000D9\u0001\u0000\u0000\u0000D:\u0001\u0000\u0000\u0000D;\u0001"+
		"\u0000\u0000\u0000D<\u0001\u0000\u0000\u0000D=\u0001\u0000\u0000\u0000"+
		"D>\u0001\u0000\u0000\u0000D?\u0001\u0000\u0000\u0000D@\u0001\u0000\u0000"+
		"\u0000DA\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DC\u0001\u0000"+
		"\u0000\u0000E\u0003\u0001\u0000\u0000\u0000FG\u0005%\u0000\u0000GH\u0005"+
		"\u0001\u0000\u0000HI\u0003(\u0014\u0000IJ\u0005\u0002\u0000\u0000J\u0005"+
		"\u0001\u0000\u0000\u0000KL\u0005%\u0000\u0000LM\u0005\u0003\u0000\u0000"+
		"MN\u0003(\u0014\u0000NO\u0005\u0004\u0000\u0000OP\u0005\u0001\u0000\u0000"+
		"PQ\u0003(\u0014\u0000QR\u0005\u0002\u0000\u0000R\u0007\u0001\u0000\u0000"+
		"\u0000ST\u0005\u0018\u0000\u0000TU\u0003(\u0014\u0000UV\u0005\u0002\u0000"+
		"\u0000V\t\u0001\u0000\u0000\u0000WX\u0005%\u0000\u0000XY\u0005\u0001\u0000"+
		"\u0000YZ\u0005#\u0000\u0000Z[\u0005\u0005\u0000\u0000[\\\u0005\'\u0000"+
		"\u0000\\]\u0005\u0006\u0000\u0000]^\u0005\u0002\u0000\u0000^\u000b\u0001"+
		"\u0000\u0000\u0000_`\u0005\u0019\u0000\u0000`a\u0005\u0005\u0000\u0000"+
		"ab\u0003(\u0014\u0000bc\u0005\u0006\u0000\u0000cf\u0003&\u0013\u0000d"+
		"e\u0005\u001a\u0000\u0000eg\u0003&\u0013\u0000fd\u0001\u0000\u0000\u0000"+
		"fg\u0001\u0000\u0000\u0000g\r\u0001\u0000\u0000\u0000hi\u0005\u001b\u0000"+
		"\u0000ij\u0005\u0005\u0000\u0000jk\u0003(\u0014\u0000kl\u0005\u0006\u0000"+
		"\u0000lm\u0003&\u0013\u0000m\u000f\u0001\u0000\u0000\u0000no\u0005\u001c"+
		"\u0000\u0000op\u0005\u0005\u0000\u0000pq\u0003\u0012\t\u0000qr\u0003("+
		"\u0014\u0000rs\u0005\u0002\u0000\u0000st\u0003\u0014\n\u0000tu\u0005\u0006"+
		"\u0000\u0000uv\u0003&\u0013\u0000v\u0011\u0001\u0000\u0000\u0000wx\u0005"+
		"%\u0000\u0000xy\u0005\u0001\u0000\u0000yz\u0003(\u0014\u0000z{\u0005\u0002"+
		"\u0000\u0000{\u0013\u0001\u0000\u0000\u0000|}\u0005%\u0000\u0000}~\u0005"+
		"\u0001\u0000\u0000~\u007f\u0003(\u0014\u0000\u007f\u0015\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0005\u001d\u0000\u0000\u0081\u0082\u0005\u0005\u0000"+
		"\u0000\u0082\u0083\u0003(\u0014\u0000\u0083\u0084\u0005\u0006\u0000\u0000"+
		"\u0084\u0088\u0005\u0007\u0000\u0000\u0085\u0087\u0003\u0018\f\u0000\u0086"+
		"\u0085\u0001\u0000\u0000\u0000\u0087\u008a\u0001\u0000\u0000\u0000\u0088"+
		"\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089"+
		"\u008c\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008b"+
		"\u008d\u0003\u001a\r\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0005\b\u0000\u0000\u008f\u0017\u0001\u0000\u0000\u0000\u0090\u0091\u0005"+
		"\u001e\u0000\u0000\u0091\u0092\u0003.\u0017\u0000\u0092\u0096\u0005\t"+
		"\u0000\u0000\u0093\u0095\u0003\u0002\u0001\u0000\u0094\u0093\u0001\u0000"+
		"\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000"+
		"\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0019\u0001\u0000"+
		"\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009a\u0005\u001f"+
		"\u0000\u0000\u009a\u009e\u0005\t\u0000\u0000\u009b\u009d\u0003\u0002\u0001"+
		"\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000\u0000"+
		"\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000"+
		"\u0000\u009f\u001b\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0005!\u0000\u0000\u00a2\u00a3\u0005%\u0000\u0000\u00a3"+
		"\u00a5\u0005\u0005\u0000\u0000\u00a4\u00a6\u0003\u001e\u000f\u0000\u00a5"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005\u0006\u0000\u0000\u00a8"+
		"\u00a9\u0003&\u0013\u0000\u00a9\u001d\u0001\u0000\u0000\u0000\u00aa\u00af"+
		"\u0005%\u0000\u0000\u00ab\u00ac\u0005\n\u0000\u0000\u00ac\u00ae\u0005"+
		"%\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000"+
		"\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000"+
		"\u0000\u0000\u00b0\u001f\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b4\u0005\"\u0000\u0000\u00b3\u00b5\u0003(\u0014"+
		"\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005\u0002\u0000"+
		"\u0000\u00b7!\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005 \u0000\u0000\u00b9"+
		"\u00ba\u0005\u0002\u0000\u0000\u00ba#\u0001\u0000\u0000\u0000\u00bb\u00bc"+
		"\u0003(\u0014\u0000\u00bc\u00bd\u0005\u0002\u0000\u0000\u00bd%\u0001\u0000"+
		"\u0000\u0000\u00be\u00c2\u0005\u0007\u0000\u0000\u00bf\u00c1\u0003\u0002"+
		"\u0001\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c5\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c6\u0005\b\u0000\u0000\u00c6\'\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0006\u0014\uffff\uffff\u0000\u00c8\u00c9\u0005\u0017"+
		"\u0000\u0000\u00c9\u00ce\u0003(\u0014\u0003\u00ca\u00cb\u0005\u000e\u0000"+
		"\u0000\u00cb\u00ce\u0003*\u0015\u0000\u00cc\u00ce\u0003*\u0015\u0000\u00cd"+
		"\u00c7\u0001\u0000\u0000\u0000\u00cd\u00ca\u0001\u0000\u0000\u0000\u00cd"+
		"\u00cc\u0001\u0000\u0000\u0000\u00ce\u00e3\u0001\u0000\u0000\u0000\u00cf"+
		"\u00d0\n\t\u0000\u0000\u00d0\u00d1\u0007\u0000\u0000\u0000\u00d1\u00e2"+
		"\u0003(\u0014\n\u00d2\u00d3\n\b\u0000\u0000\u00d3\u00d4\u0007\u0001\u0000"+
		"\u0000\u00d4\u00e2\u0003(\u0014\t\u00d5\u00d6\n\u0007\u0000\u0000\u00d6"+
		"\u00d7\u0007\u0002\u0000\u0000\u00d7\u00e2\u0003(\u0014\b\u00d8\u00d9"+
		"\n\u0006\u0000\u0000\u00d9\u00da\u0007\u0003\u0000\u0000\u00da\u00e2\u0003"+
		"(\u0014\u0007\u00db\u00dc\n\u0005\u0000\u0000\u00dc\u00dd\u0005\u0015"+
		"\u0000\u0000\u00dd\u00e2\u0003(\u0014\u0006\u00de\u00df\n\u0004\u0000"+
		"\u0000\u00df\u00e0\u0005\u0016\u0000\u0000\u00e0\u00e2\u0003(\u0014\u0005"+
		"\u00e1\u00cf\u0001\u0000\u0000\u0000\u00e1\u00d2\u0001\u0000\u0000\u0000"+
		"\u00e1\u00d5\u0001\u0000\u0000\u0000\u00e1\u00d8\u0001\u0000\u0000\u0000"+
		"\u00e1\u00db\u0001\u0000\u0000\u0000\u00e1\u00de\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4)\u0001\u0000\u0000\u0000\u00e5"+
		"\u00e3\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005\u0005\u0000\u0000\u00e7"+
		"\u00e8\u0003(\u0014\u0000\u00e8\u00e9\u0005\u0006\u0000\u0000\u00e9\u00fd"+
		"\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005%\u0000\u0000\u00eb\u00ed\u0005"+
		"\u0005\u0000\u0000\u00ec\u00ee\u0003,\u0016\u0000\u00ed\u00ec\u0001\u0000"+
		"\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000"+
		"\u0000\u0000\u00ef\u00fd\u0005\u0006\u0000\u0000\u00f0\u00f1\u0005%\u0000"+
		"\u0000\u00f1\u00f2\u0005\u0003\u0000\u0000\u00f2\u00f3\u0003(\u0014\u0000"+
		"\u00f3\u00f4\u0005\u0004\u0000\u0000\u00f4\u00fd\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f7\u0005\u0003\u0000\u0000\u00f6\u00f8\u0003,\u0016\u0000\u00f7"+
		"\u00f6\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8"+
		"\u00f9\u0001\u0000\u0000\u0000\u00f9\u00fd\u0005\u0004\u0000\u0000\u00fa"+
		"\u00fd\u0003.\u0017\u0000\u00fb\u00fd\u0005%\u0000\u0000\u00fc\u00e6\u0001"+
		"\u0000\u0000\u0000\u00fc\u00ea\u0001\u0000\u0000\u0000\u00fc\u00f0\u0001"+
		"\u0000\u0000\u0000\u00fc\u00f5\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fd+\u0001\u0000"+
		"\u0000\u0000\u00fe\u0103\u0003(\u0014\u0000\u00ff\u0100\u0005\n\u0000"+
		"\u0000\u0100\u0102\u0003(\u0014\u0000\u0101\u00ff\u0001\u0000\u0000\u0000"+
		"\u0102\u0105\u0001\u0000\u0000\u0000\u0103\u0101\u0001\u0000\u0000\u0000"+
		"\u0103\u0104\u0001\u0000\u0000\u0000\u0104-\u0001\u0000\u0000\u0000\u0105"+
		"\u0103\u0001\u0000\u0000\u0000\u0106\u010a\u0005&\u0000\u0000\u0107\u010a"+
		"\u0005\'\u0000\u0000\u0108\u010a\u0005$\u0000\u0000\u0109\u0106\u0001"+
		"\u0000\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u0109\u0108\u0001"+
		"\u0000\u0000\u0000\u010a/\u0001\u0000\u0000\u0000\u00133Df\u0088\u008c"+
		"\u0096\u009e\u00a5\u00af\u00b4\u00c2\u00cd\u00e1\u00e3\u00ed\u00f7\u00fc"+
		"\u0103\u0109";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}