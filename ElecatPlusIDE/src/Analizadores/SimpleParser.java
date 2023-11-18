package Analizadores;
// Generated from SimpleParser.g4 by ANTLR 4.13.1

	import static Analizadores.SimpleSemantic.*;
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
		PROGRAMA=1, LPAREN=2, RPAREN=3, LBRACE=4, RBRACE=5, SEMI=6, COMMA=7, ASSIGN=8, 
		SI=9, REPETIR=10, ESPERAR=11, MIENTRAS=12, REMOTO=13, ACCION=14, EJECUTAR=15, 
		FUNCION=16, CONTINUAR=17, ROMPER=18, DEVOLVER=19, ELEGIR=20, PARA=21, 
		SINO=22, CASO=23, ESCRIBIR=24, PRED=25, SONAR=26, GIRAR=27, AVANZAR=28, 
		DETECTAR=29, ENCENDER=30, APAGAR=31, CADENA=32, COMPONENTE=33, AND=34, 
		OR=35, IGUAL=36, DIFERENTE=37, MENOR_QUE=38, MENOR_IGUAL=39, MAYOR_QUE=40, 
		MAYOR_IGUAL=41, DIVIDIR=42, MULTIPLICAR=43, RESTAR=44, SUMAR=45, TD_DECIMAL=46, 
		TD_ENTERO=47, TD_CADENA=48, TD_BOOLEANO=49, BOOLEANO=50, DOSPUNTOS=51, 
		WS=52, IDENTIFIER=53, COMENTARIO_LINEA=54, COMENTARIO_BLOQUE=55, ID=56, 
		DECIMAL=57, ENTERO=58;
	public static final int
		RULE_programa = 0, RULE_cuerpoPrograma = 1, RULE_miembros = 2, RULE_setup = 3, 
		RULE_ejecucion = 4, RULE_funcion = 5, RULE_declaracionAtributo = 6, RULE_tipo = 7, 
		RULE_declaraciones = 8, RULE_parametrosFormales = 9, RULE_parametroFormal = 10, 
		RULE_bloque = 11, RULE_sentencia = 12, RULE_llamadaAFuncion = 13, RULE_argumentos = 14, 
		RULE_controlFor = 15, RULE_iniciadorFor = 16, RULE_listaExpresiones = 17, 
		RULE_parExpresion = 18, RULE_sentenciaSwitch = 19, RULE_etiquetaSwitch = 20, 
		RULE_declaracionLocal = 21, RULE_declaracionDeVariable = 22, RULE_accion = 23, 
		RULE_esperar = 24, RULE_expresion = 25, RULE_primaria = 26, RULE_literal = 27, 
		RULE_numero = 28, RULE_tipo_dato = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "cuerpoPrograma", "miembros", "setup", "ejecucion", "funcion", 
			"declaracionAtributo", "tipo", "declaraciones", "parametrosFormales", 
			"parametroFormal", "bloque", "sentencia", "llamadaAFuncion", "argumentos", 
			"controlFor", "iniciadorFor", "listaExpresiones", "parExpresion", "sentenciaSwitch", 
			"etiquetaSwitch", "declaracionLocal", "declaracionDeVariable", "accion", 
			"esperar", "expresion", "primaria", "literal", "numero", "tipo_dato"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'('", "')'", "'{'", "'}'", "';'", "','", "'='", 
			"'si'", "'repetir'", "'esperar'", "'mientras'", "'remoto'", "'accion'", 
			"'ejecutar'", "'funcion'", "'continuar'", "'romper'", "'devolver'", "'elegir'", 
			"'para'", "'sino'", "'caso'", "'escribir'", "'predeterminado'", "'sonar'", 
			"'girar'", "'avanzar'", "'detectar'", "'encender'", "'apagar'", null, 
			null, "'and'", "'or'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", 
			"'/'", "'*'", "'-'", "'+'", "'decimal'", "'entero'", "'cadena'", "'booleano'", 
			null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PROGRAMA", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", 
			"ASSIGN", "SI", "REPETIR", "ESPERAR", "MIENTRAS", "REMOTO", "ACCION", 
			"EJECUTAR", "FUNCION", "CONTINUAR", "ROMPER", "DEVOLVER", "ELEGIR", "PARA", 
			"SINO", "CASO", "ESCRIBIR", "PRED", "SONAR", "GIRAR", "AVANZAR", "DETECTAR", 
			"ENCENDER", "APAGAR", "CADENA", "COMPONENTE", "AND", "OR", "IGUAL", "DIFERENTE", 
			"MENOR_QUE", "MENOR_IGUAL", "MAYOR_QUE", "MAYOR_IGUAL", "DIVIDIR", "MULTIPLICAR", 
			"RESTAR", "SUMAR", "TD_DECIMAL", "TD_ENTERO", "TD_CADENA", "TD_BOOLEANO", 
			"BOOLEANO", "DOSPUNTOS", "WS", "IDENTIFIER", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", 
			"ID", "DECIMAL", "ENTERO"
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
	public String getGrammarFileName() { return "SimpleParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode PROGRAMA() { return getToken(SimpleParser.PROGRAMA, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public CuerpoProgramaContext cuerpoPrograma() {
			return getRuleContext(CuerpoProgramaContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SimpleParser.EOF, 0); }
		public TerminalNode REMOTO() { return getToken(SimpleParser.REMOTO, 0); }
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			variablesDeclaradas = new HashMap<>(); funcionesDeclaradas = new HashMap<>();
			setState(61);
			match(PROGRAMA);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REMOTO) {
				{
				setState(62);
				match(REMOTO);
				}
			}

			setState(65);
			match(ID);
			setState(66);
			cuerpoPrograma();
			setState(67);
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
	public static class CuerpoProgramaContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(SimpleParser.LBRACE, 0); }
		public MiembrosContext miembros() {
			return getRuleContext(MiembrosContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(SimpleParser.RBRACE, 0); }
		public CuerpoProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cuerpoPrograma; }
	}

	public final CuerpoProgramaContext cuerpoPrograma() throws RecognitionException {
		CuerpoProgramaContext _localctx = new CuerpoProgramaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cuerpoPrograma);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(LBRACE);
			setState(70);
			miembros();
			setState(71);
			match(RBRACE);
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
	public static class MiembrosContext extends ParserRuleContext {
		public List<SetupContext> setup() {
			return getRuleContexts(SetupContext.class);
		}
		public SetupContext setup(int i) {
			return getRuleContext(SetupContext.class,i);
		}
		public List<EjecucionContext> ejecucion() {
			return getRuleContexts(EjecucionContext.class);
		}
		public EjecucionContext ejecucion(int i) {
			return getRuleContext(EjecucionContext.class,i);
		}
		public List<DeclaracionAtributoContext> declaracionAtributo() {
			return getRuleContexts(DeclaracionAtributoContext.class);
		}
		public DeclaracionAtributoContext declaracionAtributo(int i) {
			return getRuleContext(DeclaracionAtributoContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(SimpleParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(SimpleParser.SEMI, i);
		}
		public List<FuncionContext> funcion() {
			return getRuleContexts(FuncionContext.class);
		}
		public FuncionContext funcion(int i) {
			return getRuleContext(FuncionContext.class,i);
		}
		public MiembrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_miembros; }
	}

	public final MiembrosContext miembros() throws RecognitionException {
		MiembrosContext _localctx = new MiembrosContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_miembros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 73113133790625792L) != 0)) {
				{
				setState(79);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(73);
					setup();
					}
					break;
				case EJECUTAR:
					{
					setState(74);
					ejecucion();
					}
					break;
				case COMPONENTE:
				case TD_DECIMAL:
				case TD_ENTERO:
				case TD_CADENA:
				case TD_BOOLEANO:
					{
					setState(75);
					declaracionAtributo();
					setState(76);
					match(SEMI);
					}
					break;
				case FUNCION:
					{
					setState(78);
					funcion();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(83);
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
	public static class SetupContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(SimpleParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SimpleParser.RPAREN, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public SetupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setup; }
	}

	public final SetupContext setup() throws RecognitionException {
		SetupContext _localctx = new SetupContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_setup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ID);
			setState(85);
			match(LPAREN);
			setState(86);
			match(RPAREN);
			setState(87);
			bloque();
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
	public static class EjecucionContext extends ParserRuleContext {
		public TerminalNode EJECUTAR() { return getToken(SimpleParser.EJECUTAR, 0); }
		public TerminalNode LPAREN() { return getToken(SimpleParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SimpleParser.RPAREN, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public EjecucionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ejecucion; }
	}

	public final EjecucionContext ejecucion() throws RecognitionException {
		EjecucionContext _localctx = new EjecucionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ejecucion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(EJECUTAR);
			setState(90);
			match(LPAREN);
			setState(91);
			match(RPAREN);
			setState(92);
			bloque();
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
	public static class FuncionContext extends ParserRuleContext {
		public Token ID;
		public TerminalNode FUNCION() { return getToken(SimpleParser.FUNCION, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(SimpleParser.LPAREN, 0); }
		public ParametrosFormalesContext parametrosFormales() {
			return getRuleContext(ParametrosFormalesContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SimpleParser.RPAREN, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SimpleParser.SEMI, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public FuncionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcion; }
	}

	public final FuncionContext funcion() throws RecognitionException {
		FuncionContext _localctx = new FuncionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funcion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 listaParametros = new ArrayList<>(); nombreParametros.clear(); 
			setState(95);
			match(FUNCION);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1055531162664960L) != 0)) {
				{
				setState(96);
				tipo_dato();
				 listaParametros.add(t); 
				}
			}

			setState(101);
			((FuncionContext)_localctx).ID = match(ID);
			setState(102);
			match(LPAREN);
			setState(103);
			parametrosFormales();
			setState(104);
			match(RPAREN);
			 funcionDeclarada(((FuncionContext)_localctx).ID, listaParametros); 
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(106);
				bloque();
				}
				break;
			case SEMI:
				{
				setState(107);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class DeclaracionAtributoContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public DeclaracionesContext declaraciones() {
			return getRuleContext(DeclaracionesContext.class,0);
		}
		public DeclaracionAtributoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionAtributo; }
	}

	public final DeclaracionAtributoContext declaracionAtributo() throws RecognitionException {
		DeclaracionAtributoContext _localctx = new DeclaracionAtributoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declaracionAtributo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			tipo();
			setState(111);
			declaraciones();
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
	public static class TipoContext extends ParserRuleContext {
		public Token t;
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode COMPONENTE() { return getToken(SimpleParser.COMPONENTE, 0); }
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tipo);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TD_DECIMAL:
			case TD_ENTERO:
			case TD_CADENA:
			case TD_BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				tipo_dato();
				}
				break;
			case COMPONENTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				((TipoContext)_localctx).t = match(COMPONENTE);
				 t=((TipoContext)_localctx).t; 
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
	public static class DeclaracionesContext extends ParserRuleContext {
		public List<DeclaracionDeVariableContext> declaracionDeVariable() {
			return getRuleContexts(DeclaracionDeVariableContext.class);
		}
		public DeclaracionDeVariableContext declaracionDeVariable(int i) {
			return getRuleContext(DeclaracionDeVariableContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleParser.COMMA, i);
		}
		public DeclaracionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaraciones; }
	}

	public final DeclaracionesContext declaraciones() throws RecognitionException {
		DeclaracionesContext _localctx = new DeclaracionesContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaraciones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			declaracionDeVariable();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(119);
				match(COMMA);
				setState(120);
				declaracionDeVariable();
				}
				}
				setState(125);
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
	public static class ParametrosFormalesContext extends ParserRuleContext {
		public List<ParametroFormalContext> parametroFormal() {
			return getRuleContexts(ParametroFormalContext.class);
		}
		public ParametroFormalContext parametroFormal(int i) {
			return getRuleContext(ParametroFormalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleParser.COMMA, i);
		}
		public ParametrosFormalesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametrosFormales; }
	}

	public final ParametrosFormalesContext parametrosFormales() throws RecognitionException {
		ParametrosFormalesContext _localctx = new ParametrosFormalesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parametrosFormales);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1055531162664960L) != 0)) {
				{
				setState(126);
				parametroFormal();
				}
			}

			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(129);
				match(COMMA);
				setState(130);
				parametroFormal();
				}
				}
				setState(135);
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
	public static class ParametroFormalContext extends ParserRuleContext {
		public Token ID;
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public ParametroFormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametroFormal; }
	}

	public final ParametroFormalContext parametroFormal() throws RecognitionException {
		ParametroFormalContext _localctx = new ParametroFormalContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parametroFormal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			tipo_dato();
			setState(137);
			((ParametroFormalContext)_localctx).ID = match(ID);
			 listaParametros.add(t); 
			 parametrosDeclarados(((ParametroFormalContext)_localctx).ID); 
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
	public static class BloqueContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(SimpleParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SimpleParser.RBRACE, 0); }
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public BloqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque; }
	}

	public final BloqueContext bloque() throws RecognitionException {
		BloqueContext _localctx = new BloqueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bloque);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(LBRACE);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 506584602221891156L) != 0)) {
				{
				{
				setState(142);
				sentencia();
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148);
			match(RBRACE);
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
		public ExpresionContext expresionSentencia;
		public BloqueContext bloqueDeSentencias;
		public DeclaracionLocalContext declaracionLocal() {
			return getRuleContext(DeclaracionLocalContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SimpleParser.SEMI, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CONTINUAR() { return getToken(SimpleParser.CONTINUAR, 0); }
		public TerminalNode ROMPER() { return getToken(SimpleParser.ROMPER, 0); }
		public TerminalNode DEVOLVER() { return getToken(SimpleParser.DEVOLVER, 0); }
		public TerminalNode ELEGIR() { return getToken(SimpleParser.ELEGIR, 0); }
		public ParExpresionContext parExpresion() {
			return getRuleContext(ParExpresionContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(SimpleParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SimpleParser.RBRACE, 0); }
		public List<SentenciaSwitchContext> sentenciaSwitch() {
			return getRuleContexts(SentenciaSwitchContext.class);
		}
		public SentenciaSwitchContext sentenciaSwitch(int i) {
			return getRuleContext(SentenciaSwitchContext.class,i);
		}
		public TerminalNode REPETIR() { return getToken(SimpleParser.REPETIR, 0); }
		public TerminalNode MIENTRAS() { return getToken(SimpleParser.MIENTRAS, 0); }
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public TerminalNode PARA() { return getToken(SimpleParser.PARA, 0); }
		public TerminalNode LPAREN() { return getToken(SimpleParser.LPAREN, 0); }
		public ControlForContext controlFor() {
			return getRuleContext(ControlForContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SimpleParser.RPAREN, 0); }
		public TerminalNode SI() { return getToken(SimpleParser.SI, 0); }
		public List<TerminalNode> SINO() { return getTokens(SimpleParser.SINO); }
		public TerminalNode SINO(int i) {
			return getToken(SimpleParser.SINO, i);
		}
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public LlamadaAFuncionContext llamadaAFuncion() {
			return getRuleContext(LlamadaAFuncionContext.class,0);
		}
		public AccionContext accion() {
			return getRuleContext(AccionContext.class,0);
		}
		public EsperarContext esperar() {
			return getRuleContext(EsperarContext.class,0);
		}
		public SentenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencia; }
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sentencia);
		int _la;
		try {
			int _alt;
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				declaracionLocal();
				setState(151);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				expresion(0);
				setState(154);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				((SentenciaContext)_localctx).expresionSentencia = expresion(0);
				setState(157);
				match(SEMI);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(159);
				match(SEMI);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(160);
				match(CONTINUAR);
				setState(161);
				match(SEMI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(162);
				match(ROMPER);
				setState(163);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(164);
				match(DEVOLVER);
				setState(165);
				expresion(0);
				setState(166);
				match(SEMI);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(168);
				match(ELEGIR);
				setState(169);
				parExpresion();
				setState(170);
				match(LBRACE);
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASO || _la==PRED) {
					{
					{
					setState(171);
					sentenciaSwitch();
					}
					}
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(177);
				match(RBRACE);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(179);
				match(REPETIR);
				setState(180);
				match(MIENTRAS);
				setState(181);
				parExpresion();
				setState(182);
				sentencia();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(184);
				match(REPETIR);
				setState(185);
				match(PARA);
				setState(186);
				match(LPAREN);
				setState(187);
				controlFor();
				setState(188);
				match(RPAREN);
				setState(189);
				sentencia();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(191);
				match(SI);
				setState(192);
				parExpresion();
				setState(193);
				sentencia();
				setState(198);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(194);
						match(SINO);
						setState(195);
						sentencia();
						}
						} 
					}
					setState(200);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(201);
				((SentenciaContext)_localctx).bloqueDeSentencias = bloque();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(202);
				llamadaAFuncion();
				setState(203);
				match(SEMI);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(205);
				accion();
				setState(206);
				match(SEMI);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(208);
				esperar();
				setState(209);
				match(SEMI);
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
	public static class LlamadaAFuncionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public LlamadaAFuncionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llamadaAFuncion; }
	}

	public final LlamadaAFuncionContext llamadaAFuncion() throws RecognitionException {
		LlamadaAFuncionContext _localctx = new LlamadaAFuncionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_llamadaAFuncion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(ID);
			setState(214);
			argumentos();
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
	public static class ArgumentosContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(SimpleParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SimpleParser.RPAREN, 0); }
		public ListaExpresionesContext listaExpresiones() {
			return getRuleContext(ListaExpresionesContext.class,0);
		}
		public ArgumentosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentos; }
	}

	public final ArgumentosContext argumentos() throws RecognitionException {
		ArgumentosContext _localctx = new ArgumentosContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_argumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(LPAREN);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 505529062467305476L) != 0)) {
				{
				setState(217);
				listaExpresiones();
				}
			}

			setState(220);
			match(RPAREN);
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
	public static class ControlForContext extends ParserRuleContext {
		public IniciadorForContext iniciadorFor() {
			return getRuleContext(IniciadorForContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(SimpleParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(SimpleParser.SEMI, i);
		}
		public ListaExpresionesContext listaExpresiones() {
			return getRuleContext(ListaExpresionesContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ControlForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controlFor; }
	}

	public final ControlForContext controlFor() throws RecognitionException {
		ControlForContext _localctx = new ControlForContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_controlFor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			iniciadorFor();
			setState(223);
			match(SEMI);
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 505529062467305476L) != 0)) {
				{
				setState(224);
				expresion(0);
				}
			}

			setState(227);
			match(SEMI);
			setState(228);
			listaExpresiones();
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
	public static class IniciadorForContext extends ParserRuleContext {
		public DeclaracionLocalContext declaracionLocal() {
			return getRuleContext(DeclaracionLocalContext.class,0);
		}
		public ListaExpresionesContext listaExpresiones() {
			return getRuleContext(ListaExpresionesContext.class,0);
		}
		public IniciadorForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iniciadorFor; }
	}

	public final IniciadorForContext iniciadorFor() throws RecognitionException {
		IniciadorForContext _localctx = new IniciadorForContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_iniciadorFor);
		try {
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMPONENTE:
			case TD_DECIMAL:
			case TD_ENTERO:
			case TD_CADENA:
			case TD_BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				declaracionLocal();
				}
				break;
			case LPAREN:
			case CADENA:
			case BOOLEANO:
			case ID:
			case DECIMAL:
			case ENTERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				listaExpresiones();
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
	public static class ListaExpresionesContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleParser.COMMA, i);
		}
		public ListaExpresionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaExpresiones; }
	}

	public final ListaExpresionesContext listaExpresiones() throws RecognitionException {
		ListaExpresionesContext _localctx = new ListaExpresionesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_listaExpresiones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			expresion(0);
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(235);
				match(COMMA);
				setState(236);
				expresion(0);
				}
				}
				setState(241);
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
	public static class ParExpresionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(SimpleParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SimpleParser.RPAREN, 0); }
		public ParExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpresion; }
	}

	public final ParExpresionContext parExpresion() throws RecognitionException {
		ParExpresionContext _localctx = new ParExpresionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_parExpresion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(LPAREN);
			setState(243);
			expresion(0);
			setState(244);
			match(RPAREN);
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
	public static class SentenciaSwitchContext extends ParserRuleContext {
		public List<EtiquetaSwitchContext> etiquetaSwitch() {
			return getRuleContexts(EtiquetaSwitchContext.class);
		}
		public EtiquetaSwitchContext etiquetaSwitch(int i) {
			return getRuleContext(EtiquetaSwitchContext.class,i);
		}
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public SentenciaSwitchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentenciaSwitch; }
	}

	public final SentenciaSwitchContext sentenciaSwitch() throws RecognitionException {
		SentenciaSwitchContext _localctx = new SentenciaSwitchContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_sentenciaSwitch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(246);
				etiquetaSwitch();
				}
				}
				setState(249); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASO || _la==PRED );
			setState(252); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(251);
				sentencia();
				}
				}
				setState(254); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 506584602221891156L) != 0) );
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
	public static class EtiquetaSwitchContext extends ParserRuleContext {
		public ExpresionContext expresionConstante;
		public TerminalNode CASO() { return getToken(SimpleParser.CASO, 0); }
		public TerminalNode DOSPUNTOS() { return getToken(SimpleParser.DOSPUNTOS, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PRED() { return getToken(SimpleParser.PRED, 0); }
		public EtiquetaSwitchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_etiquetaSwitch; }
	}

	public final EtiquetaSwitchContext etiquetaSwitch() throws RecognitionException {
		EtiquetaSwitchContext _localctx = new EtiquetaSwitchContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_etiquetaSwitch);
		try {
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASO:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(CASO);
				{
				setState(257);
				((EtiquetaSwitchContext)_localctx).expresionConstante = expresion(0);
				}
				setState(258);
				match(DOSPUNTOS);
				}
				break;
			case PRED:
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				match(PRED);
				setState(261);
				match(DOSPUNTOS);
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
	public static class DeclaracionLocalContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public DeclaracionDeVariableContext declaracionDeVariable() {
			return getRuleContext(DeclaracionDeVariableContext.class,0);
		}
		public DeclaracionLocalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionLocal; }
	}

	public final DeclaracionLocalContext declaracionLocal() throws RecognitionException {
		DeclaracionLocalContext _localctx = new DeclaracionLocalContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_declaracionLocal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			tipo();
			setState(265);
			declaracionDeVariable();
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
	public static class DeclaracionDeVariableContext extends ParserRuleContext {
		public Token ID;
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(SimpleParser.ASSIGN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public DeclaracionDeVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionDeVariable; }
	}

	public final DeclaracionDeVariableContext declaracionDeVariable() throws RecognitionException {
		DeclaracionDeVariableContext _localctx = new DeclaracionDeVariableContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_declaracionDeVariable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			((DeclaracionDeVariableContext)_localctx).ID = match(ID);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(268);
				match(ASSIGN);
				setState(269);
				expresion(0);
				}
			}

			 variableDeclarada(((DeclaracionDeVariableContext)_localctx).ID,t); 
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
		public TerminalNode ACCION() { return getToken(SimpleParser.ACCION, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(SimpleParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(SimpleParser.LPAREN, i);
		}
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode COMMA() { return getToken(SimpleParser.COMMA, 0); }
		public List<TerminalNode> RPAREN() { return getTokens(SimpleParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(SimpleParser.RPAREN, i);
		}
		public TerminalNode SONAR() { return getToken(SimpleParser.SONAR, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public TerminalNode ESCRIBIR() { return getToken(SimpleParser.ESCRIBIR, 0); }
		public ParExpresionContext parExpresion() {
			return getRuleContext(ParExpresionContext.class,0);
		}
		public TerminalNode GIRAR() { return getToken(SimpleParser.GIRAR, 0); }
		public TerminalNode AVANZAR() { return getToken(SimpleParser.AVANZAR, 0); }
		public TerminalNode DETECTAR() { return getToken(SimpleParser.DETECTAR, 0); }
		public TerminalNode ENCENDER() { return getToken(SimpleParser.ENCENDER, 0); }
		public TerminalNode APAGAR() { return getToken(SimpleParser.APAGAR, 0); }
		public AccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accion; }
	}

	public final AccionContext accion() throws RecognitionException {
		AccionContext _localctx = new AccionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_accion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(ACCION);
			setState(275);
			match(LPAREN);
			setState(276);
			((AccionContext)_localctx).ID = match(ID);
			setState(277);
			match(COMMA);
			setState(294);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SONAR:
				{
				setState(278);
				match(SONAR);
				setState(279);
				argumentos();
				}
				break;
			case ESCRIBIR:
				{
				setState(280);
				match(ESCRIBIR);
				setState(281);
				parExpresion();
				}
				break;
			case GIRAR:
				{
				setState(282);
				match(GIRAR);
				setState(283);
				parExpresion();
				}
				break;
			case AVANZAR:
				{
				setState(284);
				match(AVANZAR);
				setState(285);
				parExpresion();
				}
				break;
			case DETECTAR:
				{
				setState(286);
				match(DETECTAR);
				setState(287);
				parExpresion();
				}
				break;
			case ENCENDER:
				{
				setState(288);
				match(ENCENDER);
				setState(289);
				match(LPAREN);
				setState(290);
				match(RPAREN);
				}
				break;
			case APAGAR:
				{
				setState(291);
				match(APAGAR);
				setState(292);
				match(LPAREN);
				setState(293);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(296);
			match(RPAREN);
			 usarVariable(((AccionContext)_localctx).ID); 
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
		public ParExpresionContext parExpresion() {
			return getRuleContext(ParExpresionContext.class,0);
		}
		public EsperarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_esperar; }
	}

	public final EsperarContext esperar() throws RecognitionException {
		EsperarContext _localctx = new EsperarContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_esperar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(ESPERAR);
			setState(300);
			parExpresion();
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
		public Token ID;
		public PrimariaContext primaria() {
			return getRuleContext(PrimariaContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(SimpleParser.ASSIGN, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MULTIPLICAR() { return getToken(SimpleParser.MULTIPLICAR, 0); }
		public TerminalNode DIVIDIR() { return getToken(SimpleParser.DIVIDIR, 0); }
		public TerminalNode SUMAR() { return getToken(SimpleParser.SUMAR, 0); }
		public TerminalNode RESTAR() { return getToken(SimpleParser.RESTAR, 0); }
		public TerminalNode MENOR_IGUAL() { return getToken(SimpleParser.MENOR_IGUAL, 0); }
		public TerminalNode MAYOR_IGUAL() { return getToken(SimpleParser.MAYOR_IGUAL, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(SimpleParser.MAYOR_QUE, 0); }
		public TerminalNode MENOR_QUE() { return getToken(SimpleParser.MENOR_QUE, 0); }
		public TerminalNode IGUAL() { return getToken(SimpleParser.IGUAL, 0); }
		public TerminalNode DIFERENTE() { return getToken(SimpleParser.DIFERENTE, 0); }
		public TerminalNode AND() { return getToken(SimpleParser.AND, 0); }
		public TerminalNode OR() { return getToken(SimpleParser.OR, 0); }
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
	}

	public final ExpresionContext expresion() throws RecognitionException {
		return expresion(0);
	}

	private ExpresionContext expresion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpresionContext _localctx = new ExpresionContext(_ctx, _parentState);
		ExpresionContext _prevctx = _localctx;
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_expresion, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(303);
				primaria();
				}
				break;
			case 2:
				{
				setState(304);
				((ExpresionContext)_localctx).ID = match(ID);
				setState(305);
				match(ASSIGN);
				setState(306);
				expresion(1);
				usarVariable(((ExpresionContext)_localctx).ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(331);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(329);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new ExpresionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(311);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(312);
						_la = _input.LA(1);
						if ( !(_la==DIVIDIR || _la==MULTIPLICAR) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(313);
						expresion(8);
						}
						break;
					case 2:
						{
						_localctx = new ExpresionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(314);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(315);
						_la = _input.LA(1);
						if ( !(_la==RESTAR || _la==SUMAR) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(316);
						expresion(7);
						}
						break;
					case 3:
						{
						_localctx = new ExpresionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(317);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(318);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4123168604160L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(319);
						expresion(6);
						}
						break;
					case 4:
						{
						_localctx = new ExpresionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(320);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(321);
						_la = _input.LA(1);
						if ( !(_la==IGUAL || _la==DIFERENTE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(322);
						expresion(5);
						}
						break;
					case 5:
						{
						_localctx = new ExpresionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(323);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(324);
						match(AND);
						setState(325);
						expresion(4);
						}
						break;
					case 6:
						{
						_localctx = new ExpresionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(326);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(327);
						match(OR);
						setState(328);
						expresion(3);
						}
						break;
					}
					} 
				}
				setState(333);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
	public static class PrimariaContext extends ParserRuleContext {
		public Token ID;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ParExpresionContext parExpresion() {
			return getRuleContext(ParExpresionContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public PrimariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaria; }
	}

	public final PrimariaContext primaria() throws RecognitionException {
		PrimariaContext _localctx = new PrimariaContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_primaria);
		try {
			setState(338);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CADENA:
			case BOOLEANO:
			case DECIMAL:
			case ENTERO:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				literal();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
				parExpresion();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(336);
				((PrimariaContext)_localctx).ID = match(ID);
				usarVariable(((PrimariaContext)_localctx).ID);
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
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(SimpleParser.DECIMAL, 0); }
		public TerminalNode CADENA() { return getToken(SimpleParser.CADENA, 0); }
		public TerminalNode BOOLEANO() { return getToken(SimpleParser.BOOLEANO, 0); }
		public TerminalNode ENTERO() { return getToken(SimpleParser.ENTERO, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 433471468429377536L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumeroContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(SimpleParser.DECIMAL, 0); }
		public TerminalNode ENTERO() { return getToken(SimpleParser.ENTERO, 0); }
		public NumeroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numero; }
	}

	public final NumeroContext numero() throws RecognitionException {
		NumeroContext _localctx = new NumeroContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_numero);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			_la = _input.LA(1);
			if ( !(_la==DECIMAL || _la==ENTERO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Tipo_datoContext extends ParserRuleContext {
		public Token t;
		public TerminalNode TD_DECIMAL() { return getToken(SimpleParser.TD_DECIMAL, 0); }
		public TerminalNode TD_ENTERO() { return getToken(SimpleParser.TD_ENTERO, 0); }
		public TerminalNode TD_CADENA() { return getToken(SimpleParser.TD_CADENA, 0); }
		public TerminalNode TD_BOOLEANO() { return getToken(SimpleParser.TD_BOOLEANO, 0); }
		public Tipo_datoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_dato; }
	}

	public final Tipo_datoContext tipo_dato() throws RecognitionException {
		Tipo_datoContext _localctx = new Tipo_datoContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_tipo_dato);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			((Tipo_datoContext)_localctx).t = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1055531162664960L) != 0)) ) {
				((Tipo_datoContext)_localctx).t = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			t=((Tipo_datoContext)_localctx).t;
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
		case 25:
			return expresion_sempred((ExpresionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expresion_sempred(ExpresionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001:\u015c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000@\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002"+
		"P\b\u0002\n\u0002\f\u0002S\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005d\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005m\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"u\b\u0007\u0001\b\u0001\b\u0001\b\u0005\bz\b\b\n\b\f\b}\t\b\u0001\t\u0003"+
		"\t\u0080\b\t\u0001\t\u0001\t\u0005\t\u0084\b\t\n\t\f\t\u0087\t\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u0090\b\u000b\n\u000b\f\u000b\u0093\t\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f\u00ad\b\f\n\f\f\f\u00b0\t\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0005\f\u00c5\b\f\n\f\f\f\u00c8\t\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00d4\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00db\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00e2"+
		"\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u00e9\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00ee"+
		"\b\u0011\n\u0011\f\u0011\u00f1\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0004\u0013\u00f8\b\u0013\u000b\u0013\f\u0013"+
		"\u00f9\u0001\u0013\u0004\u0013\u00fd\b\u0013\u000b\u0013\f\u0013\u00fe"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u0107\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u010f\b\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0003\u0017\u0127\b\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u0136\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0005\u0019\u014a\b\u0019\n\u0019\f\u0019\u014d\t\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0153\b\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0000\u00012\u001e\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:\u0000"+
		"\u0007\u0001\u0000*+\u0001\u0000,-\u0001\u0000&)\u0001\u0000$%\u0003\u0000"+
		"  229:\u0001\u00009:\u0001\u0000.1\u0170\u0000<\u0001\u0000\u0000\u0000"+
		"\u0002E\u0001\u0000\u0000\u0000\u0004Q\u0001\u0000\u0000\u0000\u0006T"+
		"\u0001\u0000\u0000\u0000\bY\u0001\u0000\u0000\u0000\n^\u0001\u0000\u0000"+
		"\u0000\fn\u0001\u0000\u0000\u0000\u000et\u0001\u0000\u0000\u0000\u0010"+
		"v\u0001\u0000\u0000\u0000\u0012\u007f\u0001\u0000\u0000\u0000\u0014\u0088"+
		"\u0001\u0000\u0000\u0000\u0016\u008d\u0001\u0000\u0000\u0000\u0018\u00d3"+
		"\u0001\u0000\u0000\u0000\u001a\u00d5\u0001\u0000\u0000\u0000\u001c\u00d8"+
		"\u0001\u0000\u0000\u0000\u001e\u00de\u0001\u0000\u0000\u0000 \u00e8\u0001"+
		"\u0000\u0000\u0000\"\u00ea\u0001\u0000\u0000\u0000$\u00f2\u0001\u0000"+
		"\u0000\u0000&\u00f7\u0001\u0000\u0000\u0000(\u0106\u0001\u0000\u0000\u0000"+
		"*\u0108\u0001\u0000\u0000\u0000,\u010b\u0001\u0000\u0000\u0000.\u0112"+
		"\u0001\u0000\u0000\u00000\u012b\u0001\u0000\u0000\u00002\u0135\u0001\u0000"+
		"\u0000\u00004\u0152\u0001\u0000\u0000\u00006\u0154\u0001\u0000\u0000\u0000"+
		"8\u0156\u0001\u0000\u0000\u0000:\u0158\u0001\u0000\u0000\u0000<=\u0006"+
		"\u0000\uffff\uffff\u0000=?\u0005\u0001\u0000\u0000>@\u0005\r\u0000\u0000"+
		"?>\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000"+
		"\u0000AB\u00058\u0000\u0000BC\u0003\u0002\u0001\u0000CD\u0005\u0000\u0000"+
		"\u0001D\u0001\u0001\u0000\u0000\u0000EF\u0005\u0004\u0000\u0000FG\u0003"+
		"\u0004\u0002\u0000GH\u0005\u0005\u0000\u0000H\u0003\u0001\u0000\u0000"+
		"\u0000IP\u0003\u0006\u0003\u0000JP\u0003\b\u0004\u0000KL\u0003\f\u0006"+
		"\u0000LM\u0005\u0006\u0000\u0000MP\u0001\u0000\u0000\u0000NP\u0003\n\u0005"+
		"\u0000OI\u0001\u0000\u0000\u0000OJ\u0001\u0000\u0000\u0000OK\u0001\u0000"+
		"\u0000\u0000ON\u0001\u0000\u0000\u0000PS\u0001\u0000\u0000\u0000QO\u0001"+
		"\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000R\u0005\u0001\u0000\u0000"+
		"\u0000SQ\u0001\u0000\u0000\u0000TU\u00058\u0000\u0000UV\u0005\u0002\u0000"+
		"\u0000VW\u0005\u0003\u0000\u0000WX\u0003\u0016\u000b\u0000X\u0007\u0001"+
		"\u0000\u0000\u0000YZ\u0005\u000f\u0000\u0000Z[\u0005\u0002\u0000\u0000"+
		"[\\\u0005\u0003\u0000\u0000\\]\u0003\u0016\u000b\u0000]\t\u0001\u0000"+
		"\u0000\u0000^_\u0006\u0005\uffff\uffff\u0000_c\u0005\u0010\u0000\u0000"+
		"`a\u0003:\u001d\u0000ab\u0006\u0005\uffff\uffff\u0000bd\u0001\u0000\u0000"+
		"\u0000c`\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0001\u0000"+
		"\u0000\u0000ef\u00058\u0000\u0000fg\u0005\u0002\u0000\u0000gh\u0003\u0012"+
		"\t\u0000hi\u0005\u0003\u0000\u0000il\u0006\u0005\uffff\uffff\u0000jm\u0003"+
		"\u0016\u000b\u0000km\u0005\u0006\u0000\u0000lj\u0001\u0000\u0000\u0000"+
		"lk\u0001\u0000\u0000\u0000m\u000b\u0001\u0000\u0000\u0000no\u0003\u000e"+
		"\u0007\u0000op\u0003\u0010\b\u0000p\r\u0001\u0000\u0000\u0000qu\u0003"+
		":\u001d\u0000rs\u0005!\u0000\u0000su\u0006\u0007\uffff\uffff\u0000tq\u0001"+
		"\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000u\u000f\u0001\u0000\u0000"+
		"\u0000v{\u0003,\u0016\u0000wx\u0005\u0007\u0000\u0000xz\u0003,\u0016\u0000"+
		"yw\u0001\u0000\u0000\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000"+
		"\u0000{|\u0001\u0000\u0000\u0000|\u0011\u0001\u0000\u0000\u0000}{\u0001"+
		"\u0000\u0000\u0000~\u0080\u0003\u0014\n\u0000\u007f~\u0001\u0000\u0000"+
		"\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0085\u0001\u0000\u0000"+
		"\u0000\u0081\u0082\u0005\u0007\u0000\u0000\u0082\u0084\u0003\u0014\n\u0000"+
		"\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0087\u0001\u0000\u0000\u0000"+
		"\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000"+
		"\u0086\u0013\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0003:\u001d\u0000\u0089\u008a\u00058\u0000\u0000\u008a\u008b"+
		"\u0006\n\uffff\uffff\u0000\u008b\u008c\u0006\n\uffff\uffff\u0000\u008c"+
		"\u0015\u0001\u0000\u0000\u0000\u008d\u0091\u0005\u0004\u0000\u0000\u008e"+
		"\u0090\u0003\u0018\f\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0093"+
		"\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0001\u0000\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093\u0091"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0005\u0005\u0000\u0000\u0095\u0017"+
		"\u0001\u0000\u0000\u0000\u0096\u0097\u0003*\u0015\u0000\u0097\u0098\u0005"+
		"\u0006\u0000\u0000\u0098\u00d4\u0001\u0000\u0000\u0000\u0099\u009a\u0003"+
		"2\u0019\u0000\u009a\u009b\u0005\u0006\u0000\u0000\u009b\u00d4\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u00032\u0019\u0000\u009d\u009e\u0005\u0006\u0000"+
		"\u0000\u009e\u00d4\u0001\u0000\u0000\u0000\u009f\u00d4\u0005\u0006\u0000"+
		"\u0000\u00a0\u00a1\u0005\u0011\u0000\u0000\u00a1\u00d4\u0005\u0006\u0000"+
		"\u0000\u00a2\u00a3\u0005\u0012\u0000\u0000\u00a3\u00d4\u0005\u0006\u0000"+
		"\u0000\u00a4\u00a5\u0005\u0013\u0000\u0000\u00a5\u00a6\u00032\u0019\u0000"+
		"\u00a6\u00a7\u0005\u0006\u0000\u0000\u00a7\u00d4\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0005\u0014\u0000\u0000\u00a9\u00aa\u0003$\u0012\u0000\u00aa"+
		"\u00ae\u0005\u0004\u0000\u0000\u00ab\u00ad\u0003&\u0013\u0000\u00ac\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000\u0000\u0000\u00ae\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b2"+
		"\u0005\u0005\u0000\u0000\u00b2\u00d4\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0005\n\u0000\u0000\u00b4\u00b5\u0005\f\u0000\u0000\u00b5\u00b6\u0003"+
		"$\u0012\u0000\u00b6\u00b7\u0003\u0018\f\u0000\u00b7\u00d4\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u0005\n\u0000\u0000\u00b9\u00ba\u0005\u0015\u0000"+
		"\u0000\u00ba\u00bb\u0005\u0002\u0000\u0000\u00bb\u00bc\u0003\u001e\u000f"+
		"\u0000\u00bc\u00bd\u0005\u0003\u0000\u0000\u00bd\u00be\u0003\u0018\f\u0000"+
		"\u00be\u00d4\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005\t\u0000\u0000\u00c0"+
		"\u00c1\u0003$\u0012\u0000\u00c1\u00c6\u0003\u0018\f\u0000\u00c2\u00c3"+
		"\u0005\u0016\u0000\u0000\u00c3\u00c5\u0003\u0018\f\u0000\u00c4\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00d4\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00d4\u0003"+
		"\u0016\u000b\u0000\u00ca\u00cb\u0003\u001a\r\u0000\u00cb\u00cc\u0005\u0006"+
		"\u0000\u0000\u00cc\u00d4\u0001\u0000\u0000\u0000\u00cd\u00ce\u0003.\u0017"+
		"\u0000\u00ce\u00cf\u0005\u0006\u0000\u0000\u00cf\u00d4\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d1\u00030\u0018\u0000\u00d1\u00d2\u0005\u0006\u0000\u0000"+
		"\u00d2\u00d4\u0001\u0000\u0000\u0000\u00d3\u0096\u0001\u0000\u0000\u0000"+
		"\u00d3\u0099\u0001\u0000\u0000\u0000\u00d3\u009c\u0001\u0000\u0000\u0000"+
		"\u00d3\u009f\u0001\u0000\u0000\u0000\u00d3\u00a0\u0001\u0000\u0000\u0000"+
		"\u00d3\u00a2\u0001\u0000\u0000\u0000\u00d3\u00a4\u0001\u0000\u0000\u0000"+
		"\u00d3\u00a8\u0001\u0000\u0000\u0000\u00d3\u00b3\u0001\u0000\u0000\u0000"+
		"\u00d3\u00b8\u0001\u0000\u0000\u0000\u00d3\u00bf\u0001\u0000\u0000\u0000"+
		"\u00d3\u00c9\u0001\u0000\u0000\u0000\u00d3\u00ca\u0001\u0000\u0000\u0000"+
		"\u00d3\u00cd\u0001\u0000\u0000\u0000\u00d3\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d4\u0019\u0001\u0000\u0000\u0000\u00d5\u00d6\u00058\u0000\u0000\u00d6"+
		"\u00d7\u0003\u001c\u000e\u0000\u00d7\u001b\u0001\u0000\u0000\u0000\u00d8"+
		"\u00da\u0005\u0002\u0000\u0000\u00d9\u00db\u0003\"\u0011\u0000\u00da\u00d9"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005\u0003\u0000\u0000\u00dd\u001d"+
		"\u0001\u0000\u0000\u0000\u00de\u00df\u0003 \u0010\u0000\u00df\u00e1\u0005"+
		"\u0006\u0000\u0000\u00e0\u00e2\u00032\u0019\u0000\u00e1\u00e0\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e4\u0005\u0006\u0000\u0000\u00e4\u00e5\u0003\"\u0011"+
		"\u0000\u00e5\u001f\u0001\u0000\u0000\u0000\u00e6\u00e9\u0003*\u0015\u0000"+
		"\u00e7\u00e9\u0003\"\u0011\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8"+
		"\u00e7\u0001\u0000\u0000\u0000\u00e9!\u0001\u0000\u0000\u0000\u00ea\u00ef"+
		"\u00032\u0019\u0000\u00eb\u00ec\u0005\u0007\u0000\u0000\u00ec\u00ee\u0003"+
		"2\u0019\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ee\u00f1\u0001\u0000"+
		"\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000"+
		"\u0000\u0000\u00f0#\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f3\u0005\u0002\u0000\u0000\u00f3\u00f4\u00032\u0019\u0000"+
		"\u00f4\u00f5\u0005\u0003\u0000\u0000\u00f5%\u0001\u0000\u0000\u0000\u00f6"+
		"\u00f8\u0003(\u0014\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f8\u00f9"+
		"\u0001\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fc\u0001\u0000\u0000\u0000\u00fb\u00fd"+
		"\u0003\u0018\f\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001"+
		"\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001"+
		"\u0000\u0000\u0000\u00ff\'\u0001\u0000\u0000\u0000\u0100\u0101\u0005\u0017"+
		"\u0000\u0000\u0101\u0102\u00032\u0019\u0000\u0102\u0103\u00053\u0000\u0000"+
		"\u0103\u0107\u0001\u0000\u0000\u0000\u0104\u0105\u0005\u0019\u0000\u0000"+
		"\u0105\u0107\u00053\u0000\u0000\u0106\u0100\u0001\u0000\u0000\u0000\u0106"+
		"\u0104\u0001\u0000\u0000\u0000\u0107)\u0001\u0000\u0000\u0000\u0108\u0109"+
		"\u0003\u000e\u0007\u0000\u0109\u010a\u0003,\u0016\u0000\u010a+\u0001\u0000"+
		"\u0000\u0000\u010b\u010e\u00058\u0000\u0000\u010c\u010d\u0005\b\u0000"+
		"\u0000\u010d\u010f\u00032\u0019\u0000\u010e\u010c\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000"+
		"\u0110\u0111\u0006\u0016\uffff\uffff\u0000\u0111-\u0001\u0000\u0000\u0000"+
		"\u0112\u0113\u0005\u000e\u0000\u0000\u0113\u0114\u0005\u0002\u0000\u0000"+
		"\u0114\u0115\u00058\u0000\u0000\u0115\u0126\u0005\u0007\u0000\u0000\u0116"+
		"\u0117\u0005\u001a\u0000\u0000\u0117\u0127\u0003\u001c\u000e\u0000\u0118"+
		"\u0119\u0005\u0018\u0000\u0000\u0119\u0127\u0003$\u0012\u0000\u011a\u011b"+
		"\u0005\u001b\u0000\u0000\u011b\u0127\u0003$\u0012\u0000\u011c\u011d\u0005"+
		"\u001c\u0000\u0000\u011d\u0127\u0003$\u0012\u0000\u011e\u011f\u0005\u001d"+
		"\u0000\u0000\u011f\u0127\u0003$\u0012\u0000\u0120\u0121\u0005\u001e\u0000"+
		"\u0000\u0121\u0122\u0005\u0002\u0000\u0000\u0122\u0127\u0005\u0003\u0000"+
		"\u0000\u0123\u0124\u0005\u001f\u0000\u0000\u0124\u0125\u0005\u0002\u0000"+
		"\u0000\u0125\u0127\u0005\u0003\u0000\u0000\u0126\u0116\u0001\u0000\u0000"+
		"\u0000\u0126\u0118\u0001\u0000\u0000\u0000\u0126\u011a\u0001\u0000\u0000"+
		"\u0000\u0126\u011c\u0001\u0000\u0000\u0000\u0126\u011e\u0001\u0000\u0000"+
		"\u0000\u0126\u0120\u0001\u0000\u0000\u0000\u0126\u0123\u0001\u0000\u0000"+
		"\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129\u0005\u0003\u0000"+
		"\u0000\u0129\u012a\u0006\u0017\uffff\uffff\u0000\u012a/\u0001\u0000\u0000"+
		"\u0000\u012b\u012c\u0005\u000b\u0000\u0000\u012c\u012d\u0003$\u0012\u0000"+
		"\u012d1\u0001\u0000\u0000\u0000\u012e\u012f\u0006\u0019\uffff\uffff\u0000"+
		"\u012f\u0136\u00034\u001a\u0000\u0130\u0131\u00058\u0000\u0000\u0131\u0132"+
		"\u0005\b\u0000\u0000\u0132\u0133\u00032\u0019\u0001\u0133\u0134\u0006"+
		"\u0019\uffff\uffff\u0000\u0134\u0136\u0001\u0000\u0000\u0000\u0135\u012e"+
		"\u0001\u0000\u0000\u0000\u0135\u0130\u0001\u0000\u0000\u0000\u0136\u014b"+
		"\u0001\u0000\u0000\u0000\u0137\u0138\n\u0007\u0000\u0000\u0138\u0139\u0007"+
		"\u0000\u0000\u0000\u0139\u014a\u00032\u0019\b\u013a\u013b\n\u0006\u0000"+
		"\u0000\u013b\u013c\u0007\u0001\u0000\u0000\u013c\u014a\u00032\u0019\u0007"+
		"\u013d\u013e\n\u0005\u0000\u0000\u013e\u013f\u0007\u0002\u0000\u0000\u013f"+
		"\u014a\u00032\u0019\u0006\u0140\u0141\n\u0004\u0000\u0000\u0141\u0142"+
		"\u0007\u0003\u0000\u0000\u0142\u014a\u00032\u0019\u0005\u0143\u0144\n"+
		"\u0003\u0000\u0000\u0144\u0145\u0005\"\u0000\u0000\u0145\u014a\u00032"+
		"\u0019\u0004\u0146\u0147\n\u0002\u0000\u0000\u0147\u0148\u0005#\u0000"+
		"\u0000\u0148\u014a\u00032\u0019\u0003\u0149\u0137\u0001\u0000\u0000\u0000"+
		"\u0149\u013a\u0001\u0000\u0000\u0000\u0149\u013d\u0001\u0000\u0000\u0000"+
		"\u0149\u0140\u0001\u0000\u0000\u0000\u0149\u0143\u0001\u0000\u0000\u0000"+
		"\u0149\u0146\u0001\u0000\u0000\u0000\u014a\u014d\u0001\u0000\u0000\u0000"+
		"\u014b\u0149\u0001\u0000\u0000\u0000\u014b\u014c\u0001\u0000\u0000\u0000"+
		"\u014c3\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014e"+
		"\u0153\u00036\u001b\u0000\u014f\u0153\u0003$\u0012\u0000\u0150\u0151\u0005"+
		"8\u0000\u0000\u0151\u0153\u0006\u001a\uffff\uffff\u0000\u0152\u014e\u0001"+
		"\u0000\u0000\u0000\u0152\u014f\u0001\u0000\u0000\u0000\u0152\u0150\u0001"+
		"\u0000\u0000\u0000\u01535\u0001\u0000\u0000\u0000\u0154\u0155\u0007\u0004"+
		"\u0000\u0000\u01557\u0001\u0000\u0000\u0000\u0156\u0157\u0007\u0005\u0000"+
		"\u0000\u01579\u0001\u0000\u0000\u0000\u0158\u0159\u0007\u0006\u0000\u0000"+
		"\u0159\u015a\u0006\u001d\uffff\uffff\u0000\u015a;\u0001\u0000\u0000\u0000"+
		"\u001a?OQclt{\u007f\u0085\u0091\u00ae\u00c6\u00d3\u00da\u00e1\u00e8\u00ef"+
		"\u00f9\u00fe\u0106\u010e\u0126\u0135\u0149\u014b\u0152";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}