package Analizadores;
// Generated from Simple.g4 by ANTLR 4.13.1

    import java.util.Map;
    import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SimpleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMENTARIO_LINEA=1, COMENTARIO_BLOQUE=2, ID=3, NUMERO=4, CADENA=5, ESCRIBIR=6, 
		GIRAR=7, ACCIONES=8, LEE=9, PROGRAMA=10, ACCION=11, ESTADO=12, COMPONENTE=13, 
		DIRECCION=14, OP_BIN=15, NOT=16, TIEMPO=17, UNIDAD=18, SI=19, SI_NO=20, 
		CONDICION=21, REPETIR=22, MIENTRAS=23, CICLO=24, VAR=25, TIPO_DATO=26, 
		FIN_LINEA=27, SIGNO_IGUAL=28, BOOLEANO=29, BRACKET_ABRIR=30, BRACKET_CERRAR=31, 
		PARENTESIS_ABRIR=32, PARENTESIS_CERRAR=33, ES=34, MAYOR=35, MENOR=36, 
		IGUAL=37, QUE=38, DIFERENTE=39, ESPERAR=40, COMA=41, WS=42;
	public static final int
		RULE_programa = 0, RULE_sentencia = 1, RULE_declaracion = 2, RULE_asignacion = 3, 
		RULE_condicionalif = 4, RULE_cicloWhile = 5, RULE_accion = 6, RULE_esperar = 7, 
		RULE_girar = 8, RULE_escribir = 9, RULE_termino = 10, RULE_expresion = 11, 
		RULE_operacionBinaria = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "sentencia", "declaracion", "asignacion", "condicionalif", 
			"cicloWhile", "accion", "esperar", "girar", "escribir", "termino", "expresion", 
			"operacionBinaria"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'escribir'", "'gira'", null, "'leer'", 
			"'programa'", "'accion'", null, null, null, null, "'not'", null, null, 
			"'si'", "'sino'", null, "'repetir'", "'mientras'", null, "'var'", null, 
			"';'", "'='", null, "'{'", "'}'", "'('", "')'", "'es'", "'mayor'", "'menor'", 
			"'igual'", "'que'", "'diferente'", "'esperar'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "ID", "NUMERO", "CADENA", 
			"ESCRIBIR", "GIRAR", "ACCIONES", "LEE", "PROGRAMA", "ACCION", "ESTADO", 
			"COMPONENTE", "DIRECCION", "OP_BIN", "NOT", "TIEMPO", "UNIDAD", "SI", 
			"SI_NO", "CONDICION", "REPETIR", "MIENTRAS", "CICLO", "VAR", "TIPO_DATO", 
			"FIN_LINEA", "SIGNO_IGUAL", "BOOLEANO", "BRACKET_ABRIR", "BRACKET_CERRAR", 
			"PARENTESIS_ABRIR", "PARENTESIS_CERRAR", "ES", "MAYOR", "MENOR", "IGUAL", 
			"QUE", "DIFERENTE", "ESPERAR", "COMA", "WS"
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
	public String getGrammarFileName() { return "Simple.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	  // Tabla de símbolos para realizar un seguimiento de las variables declaradas
	  Map<String,String> variablesDeclaradas = new HashMap<>();
	  boolean isDeclaration = false;
	  Token t;

	  // Predicado semántico para verificar si una variable se ha declarado al intentar declararla
	  boolean variableDeclarada(String variableName, String variableType) {
		if (!isDeclaration)
			return false;
		if (variablesDeclaradas.containsKey(variableName))
			return true;
		variablesDeclaradas.put(variableName,variableType);
		return false;
	  }

	  // Predicado semántico para verificar si una variable se ha declarado al intentar declararla
	  boolean usarVariable(String variableName) {
		return variablesDeclaradas.containsKey(variableName);
	  }

	public SimpleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode PROGRAMA() { return getToken(SimpleParser.PROGRAMA, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode BRACKET_ABRIR() { return getToken(SimpleParser.BRACKET_ABRIR, 0); }
		public TerminalNode BRACKET_CERRAR() { return getToken(SimpleParser.BRACKET_CERRAR, 0); }
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(PROGRAMA);
			setState(27);
			match(ID);
			setState(28);
			match(BRACKET_ABRIR);
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				sentencia();
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1099583465480L) != 0) );
			setState(34);
			match(BRACKET_CERRAR);
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
	public static class SentenciaContext extends ParserRuleContext {
		public DeclaracionContext declaracion() {
			return getRuleContext(DeclaracionContext.class,0);
		}
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public CondicionalifContext condicionalif() {
			return getRuleContext(CondicionalifContext.class,0);
		}
		public CicloWhileContext cicloWhile() {
			return getRuleContext(CicloWhileContext.class,0);
		}
		public AccionContext accion() {
			return getRuleContext(AccionContext.class,0);
		}
		public SentenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterSentencia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitSentencia(this);
		}
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentencia);
		try {
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMPONENTE:
			case TIPO_DATO:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				declaracion();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				asignacion();
				}
				break;
			case SI:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				condicionalif();
				}
				break;
			case REPETIR:
				enterOuterAlt(_localctx, 4);
				{
				setState(39);
				cicloWhile();
				}
				break;
			case ACCION:
			case ESPERAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(40);
				accion();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionContext extends ParserRuleContext {
		public Token t;
		public Token ID;
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public TerminalNode TIPO_DATO() { return getToken(SimpleParser.TIPO_DATO, 0); }
		public TerminalNode COMPONENTE() { return getToken(SimpleParser.COMPONENTE, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode FIN_LINEA() { return getToken(SimpleParser.FIN_LINEA, 0); }
		public DeclaracionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterDeclaracion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitDeclaracion(this);
		}
	}

	public final DeclaracionContext declaracion() throws RecognitionException {
		DeclaracionContext _localctx = new DeclaracionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 isDeclaration = true; 
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TIPO_DATO:
				{
				setState(44);
				((DeclaracionContext)_localctx).t = match(TIPO_DATO);
				}
				break;
			case COMPONENTE:
				{
				setState(45);
				((DeclaracionContext)_localctx).t = match(COMPONENTE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			t = ((DeclaracionContext)_localctx).t;
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				{
				setState(49);
				((DeclaracionContext)_localctx).ID = match(ID);
				setState(50);
				if (!( !variableDeclarada((((DeclaracionContext)_localctx).ID!=null?((DeclaracionContext)_localctx).ID.getText():null),(((DeclaracionContext)_localctx).t!=null?((DeclaracionContext)_localctx).t.getText():null)) )) throw new FailedPredicateException(this, " !variableDeclarada($ID.text,$t.text) ");
				setState(51);
				match(FIN_LINEA);
				}
				}
				break;
			case 2:
				{
				setState(52);
				asignacion();
				}
				break;
			}
			 isDeclaration = false;
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
	public static class AsignacionContext extends ParserRuleContext {
		public Token ID;
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode SIGNO_IGUAL() { return getToken(SimpleParser.SIGNO_IGUAL, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode FIN_LINEA() { return getToken(SimpleParser.FIN_LINEA, 0); }
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitAsignacion(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_asignacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			((AsignacionContext)_localctx).ID = match(ID);
			setState(58);
			if (!( !variableDeclarada((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null),t.getText()) )) throw new FailedPredicateException(this, " !variableDeclarada($ID.text,t.getText()) ");
			setState(59);
			if (!( usarVariable((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)) )) throw new FailedPredicateException(this, " usarVariable($ID.text) ");
			setState(60);
			match(SIGNO_IGUAL);
			setState(61);
			expresion();
			setState(62);
			match(FIN_LINEA);
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
	public static class CondicionalifContext extends ParserRuleContext {
		public TerminalNode SI() { return getToken(SimpleParser.SI, 0); }
		public TerminalNode PARENTESIS_ABRIR() { return getToken(SimpleParser.PARENTESIS_ABRIR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_CERRAR() { return getToken(SimpleParser.PARENTESIS_CERRAR, 0); }
		public List<TerminalNode> BRACKET_ABRIR() { return getTokens(SimpleParser.BRACKET_ABRIR); }
		public TerminalNode BRACKET_ABRIR(int i) {
			return getToken(SimpleParser.BRACKET_ABRIR, i);
		}
		public List<TerminalNode> BRACKET_CERRAR() { return getTokens(SimpleParser.BRACKET_CERRAR); }
		public TerminalNode BRACKET_CERRAR(int i) {
			return getToken(SimpleParser.BRACKET_CERRAR, i);
		}
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public TerminalNode SI_NO() { return getToken(SimpleParser.SI_NO, 0); }
		public CondicionalifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicionalif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterCondicionalif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitCondicionalif(this);
		}
	}

	public final CondicionalifContext condicionalif() throws RecognitionException {
		CondicionalifContext _localctx = new CondicionalifContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_condicionalif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(SI);
			setState(65);
			match(PARENTESIS_ABRIR);
			setState(66);
			expresion();
			setState(67);
			match(PARENTESIS_CERRAR);
			setState(68);
			match(BRACKET_ABRIR);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099583465480L) != 0)) {
				{
				{
				setState(69);
				sentencia();
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			match(BRACKET_CERRAR);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SI_NO) {
				{
				setState(76);
				match(SI_NO);
				setState(77);
				match(BRACKET_ABRIR);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099583465480L) != 0)) {
					{
					{
					setState(78);
					sentencia();
					}
					}
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(84);
				match(BRACKET_CERRAR);
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
	public static class CicloWhileContext extends ParserRuleContext {
		public TerminalNode REPETIR() { return getToken(SimpleParser.REPETIR, 0); }
		public TerminalNode MIENTRAS() { return getToken(SimpleParser.MIENTRAS, 0); }
		public TerminalNode PARENTESIS_ABRIR() { return getToken(SimpleParser.PARENTESIS_ABRIR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_CERRAR() { return getToken(SimpleParser.PARENTESIS_CERRAR, 0); }
		public TerminalNode BRACKET_ABRIR() { return getToken(SimpleParser.BRACKET_ABRIR, 0); }
		public TerminalNode BRACKET_CERRAR() { return getToken(SimpleParser.BRACKET_CERRAR, 0); }
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public CicloWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cicloWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterCicloWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitCicloWhile(this);
		}
	}

	public final CicloWhileContext cicloWhile() throws RecognitionException {
		CicloWhileContext _localctx = new CicloWhileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cicloWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(REPETIR);
			setState(88);
			match(MIENTRAS);
			setState(89);
			match(PARENTESIS_ABRIR);
			setState(90);
			expresion();
			setState(91);
			match(PARENTESIS_CERRAR);
			setState(92);
			match(BRACKET_ABRIR);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099583465480L) != 0)) {
				{
				{
				setState(93);
				sentencia();
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(BRACKET_CERRAR);
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
	public static class AccionContext extends ParserRuleContext {
		public Token ID;
		public EsperarContext esperar() {
			return getRuleContext(EsperarContext.class,0);
		}
		public TerminalNode ACCION() { return getToken(SimpleParser.ACCION, 0); }
		public TerminalNode PARENTESIS_ABRIR() { return getToken(SimpleParser.PARENTESIS_ABRIR, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode PARENTESIS_CERRAR() { return getToken(SimpleParser.PARENTESIS_CERRAR, 0); }
		public TerminalNode FIN_LINEA() { return getToken(SimpleParser.FIN_LINEA, 0); }
		public GirarContext girar() {
			return getRuleContext(GirarContext.class,0);
		}
		public EscribirContext escribir() {
			return getRuleContext(EscribirContext.class,0);
		}
		public AccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterAccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitAccion(this);
		}
	}

	public final AccionContext accion() throws RecognitionException {
		AccionContext _localctx = new AccionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_accion);
		try {
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESPERAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				esperar();
				}
				break;
			case ACCION:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(ACCION);
				setState(103);
				match(PARENTESIS_ABRIR);
				setState(104);
				((AccionContext)_localctx).ID = match(ID);
				setState(105);
				if (!( usarVariable((((AccionContext)_localctx).ID!=null?((AccionContext)_localctx).ID.getText():null)) )) throw new FailedPredicateException(this, " usarVariable($ID.text) ");
				setState(106);
				match(COMA);
				setState(109);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIRAR:
					{
					setState(107);
					girar();
					}
					break;
				case ESCRIBIR:
					{
					setState(108);
					escribir();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(111);
				match(PARENTESIS_CERRAR);
				setState(112);
				match(FIN_LINEA);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EsperarContext extends ParserRuleContext {
		public TerminalNode ESPERAR() { return getToken(SimpleParser.ESPERAR, 0); }
		public TerminalNode PARENTESIS_ABRIR() { return getToken(SimpleParser.PARENTESIS_ABRIR, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode TIEMPO() { return getToken(SimpleParser.TIEMPO, 0); }
		public TerminalNode PARENTESIS_CERRAR() { return getToken(SimpleParser.PARENTESIS_CERRAR, 0); }
		public TerminalNode FIN_LINEA() { return getToken(SimpleParser.FIN_LINEA, 0); }
		public EsperarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_esperar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterEsperar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitEsperar(this);
		}
	}

	public final EsperarContext esperar() throws RecognitionException {
		EsperarContext _localctx = new EsperarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_esperar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(ESPERAR);
			setState(117);
			match(PARENTESIS_ABRIR);
			setState(118);
			match(NUMERO);
			setState(119);
			match(COMA);
			setState(120);
			match(TIEMPO);
			setState(121);
			match(PARENTESIS_CERRAR);
			setState(122);
			match(FIN_LINEA);
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
	public static class GirarContext extends ParserRuleContext {
		public TerminalNode GIRAR() { return getToken(SimpleParser.GIRAR, 0); }
		public TerminalNode PARENTESIS_ABRIR() { return getToken(SimpleParser.PARENTESIS_ABRIR, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode DIRECCION() { return getToken(SimpleParser.DIRECCION, 0); }
		public TerminalNode PARENTESIS_CERRAR() { return getToken(SimpleParser.PARENTESIS_CERRAR, 0); }
		public GirarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_girar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterGirar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitGirar(this);
		}
	}

	public final GirarContext girar() throws RecognitionException {
		GirarContext _localctx = new GirarContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_girar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(GIRAR);
			setState(125);
			match(PARENTESIS_ABRIR);
			setState(126);
			match(NUMERO);
			setState(127);
			match(COMA);
			setState(128);
			match(DIRECCION);
			setState(129);
			match(PARENTESIS_CERRAR);
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
	public static class EscribirContext extends ParserRuleContext {
		public TerminalNode ESCRIBIR() { return getToken(SimpleParser.ESCRIBIR, 0); }
		public TerminalNode PARENTESIS_ABRIR() { return getToken(SimpleParser.PARENTESIS_ABRIR, 0); }
		public TerminalNode PARENTESIS_CERRAR() { return getToken(SimpleParser.PARENTESIS_CERRAR, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode CADENA() { return getToken(SimpleParser.CADENA, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public EscribirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escribir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterEscribir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitEscribir(this);
		}
	}

	public final EscribirContext escribir() throws RecognitionException {
		EscribirContext _localctx = new EscribirContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_escribir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(ESCRIBIR);
			setState(132);
			match(PARENTESIS_ABRIR);
			setState(133);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(134);
			match(PARENTESIS_CERRAR);
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
	public static class TerminoContext extends ParserRuleContext {
		public Token ID;
		public TerminalNode BOOLEANO() { return getToken(SimpleParser.BOOLEANO, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode PARENTESIS_ABRIR() { return getToken(SimpleParser.PARENTESIS_ABRIR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_CERRAR() { return getToken(SimpleParser.PARENTESIS_CERRAR, 0); }
		public TerminoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterTermino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitTermino(this);
		}
	}

	public final TerminoContext termino() throws RecognitionException {
		TerminoContext _localctx = new TerminoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_termino);
		try {
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(BOOLEANO);
				}
				break;
			case NUMERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(NUMERO);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				((TerminoContext)_localctx).ID = match(ID);
				setState(139);
				if (!( usarVariable((((TerminoContext)_localctx).ID!=null?((TerminoContext)_localctx).ID.getText():null)) )) throw new FailedPredicateException(this, " usarVariable($ID.text) ");
				}
				break;
			case PARENTESIS_ABRIR:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				match(PARENTESIS_ABRIR);
				setState(141);
				expresion();
				setState(142);
				match(PARENTESIS_CERRAR);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionContext extends ParserRuleContext {
		public TerminoContext termino() {
			return getRuleContext(TerminoContext.class,0);
		}
		public List<OperacionBinariaContext> operacionBinaria() {
			return getRuleContexts(OperacionBinariaContext.class);
		}
		public OperacionBinariaContext operacionBinaria(int i) {
			return getRuleContext(OperacionBinariaContext.class,i);
		}
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitExpresion(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expresion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			termino();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_BIN) {
				{
				{
				setState(147);
				operacionBinaria();
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
	public static class OperacionBinariaContext extends ParserRuleContext {
		public TerminalNode OP_BIN() { return getToken(SimpleParser.OP_BIN, 0); }
		public TerminoContext termino() {
			return getRuleContext(TerminoContext.class,0);
		}
		public OperacionBinariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacionBinaria; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterOperacionBinaria(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitOperacionBinaria(this);
		}
	}

	public final OperacionBinariaContext operacionBinaria() throws RecognitionException {
		OperacionBinariaContext _localctx = new OperacionBinariaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operacionBinaria);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(153);
			match(OP_BIN);
			setState(154);
			termino();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return declaracion_sempred((DeclaracionContext)_localctx, predIndex);
		case 3:
			return asignacion_sempred((AsignacionContext)_localctx, predIndex);
		case 6:
			return accion_sempred((AccionContext)_localctx, predIndex);
		case 10:
			return termino_sempred((TerminoContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean declaracion_sempred(DeclaracionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  !variableDeclarada((((DeclaracionContext)_localctx).ID!=null?((DeclaracionContext)_localctx).ID.getText():null),(((DeclaracionContext)_localctx).t!=null?((DeclaracionContext)_localctx).t.getText():null)) ;
		}
		return true;
	}
	private boolean asignacion_sempred(AsignacionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return  !variableDeclarada((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null),t.getText()) ;
		case 2:
			return  usarVariable((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)) ;
		}
		return true;
	}
	private boolean accion_sempred(AccionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return  usarVariable((((AccionContext)_localctx).ID!=null?((AccionContext)_localctx).ID.getText():null)) ;
		}
		return true;
	}
	private boolean termino_sempred(TerminoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return  usarVariable((((TerminoContext)_localctx).ID!=null?((TerminoContext)_localctx).ID.getText():null)) ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001*\u009d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000"+
		"\u001f\b\u0000\u000b\u0000\f\u0000 \u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001*\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002/\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00026\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004G\b\u0004\n\u0004\f\u0004"+
		"J\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"P\b\u0004\n\u0004\f\u0004S\t\u0004\u0001\u0004\u0003\u0004V\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005_\b\u0005\n\u0005\f\u0005b\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006n\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006s\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\n\u0091\b\n\u0001\u000b\u0001\u000b\u0005\u000b\u0095"+
		"\b\u000b\n\u000b\f\u000b\u0098\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0000\u0000\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u0000\u0001\u0001\u0000\u0003\u0005\u00a0\u0000\u001a\u0001\u0000"+
		"\u0000\u0000\u0002)\u0001\u0000\u0000\u0000\u0004+\u0001\u0000\u0000\u0000"+
		"\u00069\u0001\u0000\u0000\u0000\b@\u0001\u0000\u0000\u0000\nW\u0001\u0000"+
		"\u0000\u0000\fr\u0001\u0000\u0000\u0000\u000et\u0001\u0000\u0000\u0000"+
		"\u0010|\u0001\u0000\u0000\u0000\u0012\u0083\u0001\u0000\u0000\u0000\u0014"+
		"\u0090\u0001\u0000\u0000\u0000\u0016\u0092\u0001\u0000\u0000\u0000\u0018"+
		"\u0099\u0001\u0000\u0000\u0000\u001a\u001b\u0005\n\u0000\u0000\u001b\u001c"+
		"\u0005\u0003\u0000\u0000\u001c\u001e\u0005\u001e\u0000\u0000\u001d\u001f"+
		"\u0003\u0002\u0001\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f \u0001"+
		"\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000"+
		"\u0000!\"\u0001\u0000\u0000\u0000\"#\u0005\u001f\u0000\u0000#\u0001\u0001"+
		"\u0000\u0000\u0000$*\u0003\u0004\u0002\u0000%*\u0003\u0006\u0003\u0000"+
		"&*\u0003\b\u0004\u0000\'*\u0003\n\u0005\u0000(*\u0003\f\u0006\u0000)$"+
		"\u0001\u0000\u0000\u0000)%\u0001\u0000\u0000\u0000)&\u0001\u0000\u0000"+
		"\u0000)\'\u0001\u0000\u0000\u0000)(\u0001\u0000\u0000\u0000*\u0003\u0001"+
		"\u0000\u0000\u0000+.\u0006\u0002\uffff\uffff\u0000,/\u0005\u001a\u0000"+
		"\u0000-/\u0005\r\u0000\u0000.,\u0001\u0000\u0000\u0000.-\u0001\u0000\u0000"+
		"\u0000/0\u0001\u0000\u0000\u000005\u0006\u0002\uffff\uffff\u000012\u0005"+
		"\u0003\u0000\u000023\u0004\u0002\u0000\u000136\u0005\u001b\u0000\u0000"+
		"46\u0003\u0006\u0003\u000051\u0001\u0000\u0000\u000054\u0001\u0000\u0000"+
		"\u000067\u0001\u0000\u0000\u000078\u0006\u0002\uffff\uffff\u00008\u0005"+
		"\u0001\u0000\u0000\u00009:\u0005\u0003\u0000\u0000:;\u0004\u0003\u0001"+
		"\u0001;<\u0004\u0003\u0002\u0001<=\u0005\u001c\u0000\u0000=>\u0003\u0016"+
		"\u000b\u0000>?\u0005\u001b\u0000\u0000?\u0007\u0001\u0000\u0000\u0000"+
		"@A\u0005\u0013\u0000\u0000AB\u0005 \u0000\u0000BC\u0003\u0016\u000b\u0000"+
		"CD\u0005!\u0000\u0000DH\u0005\u001e\u0000\u0000EG\u0003\u0002\u0001\u0000"+
		"FE\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000"+
		"\u0000HI\u0001\u0000\u0000\u0000IK\u0001\u0000\u0000\u0000JH\u0001\u0000"+
		"\u0000\u0000KU\u0005\u001f\u0000\u0000LM\u0005\u0014\u0000\u0000MQ\u0005"+
		"\u001e\u0000\u0000NP\u0003\u0002\u0001\u0000ON\u0001\u0000\u0000\u0000"+
		"PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000"+
		"\u0000RT\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000TV\u0005\u001f"+
		"\u0000\u0000UL\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\t\u0001"+
		"\u0000\u0000\u0000WX\u0005\u0016\u0000\u0000XY\u0005\u0017\u0000\u0000"+
		"YZ\u0005 \u0000\u0000Z[\u0003\u0016\u000b\u0000[\\\u0005!\u0000\u0000"+
		"\\`\u0005\u001e\u0000\u0000]_\u0003\u0002\u0001\u0000^]\u0001\u0000\u0000"+
		"\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000"+
		"\u0000\u0000ac\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000cd\u0005"+
		"\u001f\u0000\u0000d\u000b\u0001\u0000\u0000\u0000es\u0003\u000e\u0007"+
		"\u0000fg\u0005\u000b\u0000\u0000gh\u0005 \u0000\u0000hi\u0005\u0003\u0000"+
		"\u0000ij\u0004\u0006\u0003\u0001jm\u0005)\u0000\u0000kn\u0003\u0010\b"+
		"\u0000ln\u0003\u0012\t\u0000mk\u0001\u0000\u0000\u0000ml\u0001\u0000\u0000"+
		"\u0000no\u0001\u0000\u0000\u0000op\u0005!\u0000\u0000pq\u0005\u001b\u0000"+
		"\u0000qs\u0001\u0000\u0000\u0000re\u0001\u0000\u0000\u0000rf\u0001\u0000"+
		"\u0000\u0000s\r\u0001\u0000\u0000\u0000tu\u0005(\u0000\u0000uv\u0005 "+
		"\u0000\u0000vw\u0005\u0004\u0000\u0000wx\u0005)\u0000\u0000xy\u0005\u0011"+
		"\u0000\u0000yz\u0005!\u0000\u0000z{\u0005\u001b\u0000\u0000{\u000f\u0001"+
		"\u0000\u0000\u0000|}\u0005\u0007\u0000\u0000}~\u0005 \u0000\u0000~\u007f"+
		"\u0005\u0004\u0000\u0000\u007f\u0080\u0005)\u0000\u0000\u0080\u0081\u0005"+
		"\u000e\u0000\u0000\u0081\u0082\u0005!\u0000\u0000\u0082\u0011\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0005\u0006\u0000\u0000\u0084\u0085\u0005 \u0000"+
		"\u0000\u0085\u0086\u0007\u0000\u0000\u0000\u0086\u0087\u0005!\u0000\u0000"+
		"\u0087\u0013\u0001\u0000\u0000\u0000\u0088\u0091\u0005\u001d\u0000\u0000"+
		"\u0089\u0091\u0005\u0004\u0000\u0000\u008a\u008b\u0005\u0003\u0000\u0000"+
		"\u008b\u0091\u0004\n\u0004\u0001\u008c\u008d\u0005 \u0000\u0000\u008d"+
		"\u008e\u0003\u0016\u000b\u0000\u008e\u008f\u0005!\u0000\u0000\u008f\u0091"+
		"\u0001\u0000\u0000\u0000\u0090\u0088\u0001\u0000\u0000\u0000\u0090\u0089"+
		"\u0001\u0000\u0000\u0000\u0090\u008a\u0001\u0000\u0000\u0000\u0090\u008c"+
		"\u0001\u0000\u0000\u0000\u0091\u0015\u0001\u0000\u0000\u0000\u0092\u0096"+
		"\u0003\u0014\n\u0000\u0093\u0095\u0003\u0018\f\u0000\u0094\u0093\u0001"+
		"\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000\u0096\u0094\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0017\u0001"+
		"\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009a\u0005"+
		"\u000f\u0000\u0000\u009a\u009b\u0003\u0014\n\u0000\u009b\u0019\u0001\u0000"+
		"\u0000\u0000\f ).5HQU`mr\u0090\u0096";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}