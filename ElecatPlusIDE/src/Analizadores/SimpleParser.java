package Analizadores;
// Generated from SimpleParser.g4 by ANTLR 4.13.1
 	import static Analizadores.SimpleSemantic.*; 	import java.util.HashMap; 
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
		DETECTAR=29, MOSTRAR=30, ENCENDER=31, DETENER=32, APAGAR=33, CADENA=34, 
		COMPONENTE=35, AND=36, OR=37, IGUAL=38, DIFERENTE=39, MENOR_QUE=40, MENOR_IGUAL=41, 
		MAYOR_QUE=42, MAYOR_IGUAL=43, DIVIDIR=44, MULTIPLICAR=45, RESTAR=46, SUMAR=47, 
		TD_DECIMAL=48, TD_ENTERO=49, TD_CADENA=50, TD_BOOLEANO=51, BOOLEANO=52, 
		DOSPUNTOS=53, WS=54, IDENTIFIER=55, COMENTARIO_LINEA=56, COMENTARIO_BLOQUE=57, 
		ID=58, DECIMAL=59, ENTERO=60;
	public static final int
		RULE_programa = 0, RULE_cuerpoPrograma = 1, RULE_miembros = 2, RULE_setup = 3, 
		RULE_ejecucion = 4, RULE_funcion = 5, RULE_declaracionAtributo = 6, RULE_tipo = 7, 
		RULE_declaraciones = 8, RULE_parametrosFormales = 9, RULE_parametroFormal = 10, 
		RULE_bloque = 11, RULE_sentencia = 12, RULE_llamadaAFuncion = 13, RULE_argumentos = 14, 
		RULE_controlFor = 15, RULE_iniciadorFor = 16, RULE_listaExpresiones = 17, 
		RULE_parExpresion = 18, RULE_sentenciaSwitch = 19, RULE_etiquetaSwitch = 20, 
		RULE_declaracionLocal = 21, RULE_declaracionDeVariable = 22, RULE_accion = 23, 
		RULE_esperar = 24, RULE_expresion = 25, RULE_primaria = 26, RULE_literal = 27, 
		RULE_tipo_dato = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "cuerpoPrograma", "miembros", "setup", "ejecucion", "funcion", 
			"declaracionAtributo", "tipo", "declaraciones", "parametrosFormales", 
			"parametroFormal", "bloque", "sentencia", "llamadaAFuncion", "argumentos", 
			"controlFor", "iniciadorFor", "listaExpresiones", "parExpresion", "sentenciaSwitch", 
			"etiquetaSwitch", "declaracionLocal", "declaracionDeVariable", "accion", 
			"esperar", "expresion", "primaria", "literal", "tipo_dato"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'('", "')'", "'{'", "'}'", "';'", "','", "'='", 
			"'si'", "'repetir'", "'esperar'", "'mientras'", "'remoto'", "'accion'", 
			"'ejecutar'", "'funcion'", "'continuar'", "'romper'", "'devolver'", "'elegir'", 
			"'para'", "'sino'", "'caso'", "'escribir'", "'predeterminado'", "'sonar'", 
			"'girar'", "'avanzar'", "'detectar'", "'mostrar'", "'encender'", "'detener'", 
			"'apagar'", null, null, "'and'", "'or'", "'=='", "'!='", "'<'", "'<='", 
			"'>'", "'>='", "'/'", "'*'", "'-'", "'+'", "'decimal'", "'entero'", "'cadena'", 
			"'booleano'", null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PROGRAMA", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", 
			"ASSIGN", "SI", "REPETIR", "ESPERAR", "MIENTRAS", "REMOTO", "ACCION", 
			"EJECUTAR", "FUNCION", "CONTINUAR", "ROMPER", "DEVOLVER", "ELEGIR", "PARA", 
			"SINO", "CASO", "ESCRIBIR", "PRED", "SONAR", "GIRAR", "AVANZAR", "DETECTAR", 
			"MOSTRAR", "ENCENDER", "DETENER", "APAGAR", "CADENA", "COMPONENTE", "AND", 
			"OR", "IGUAL", "DIFERENTE", "MENOR_QUE", "MENOR_IGUAL", "MAYOR_QUE", 
			"MAYOR_IGUAL", "DIVIDIR", "MULTIPLICAR", "RESTAR", "SUMAR", "TD_DECIMAL", 
			"TD_ENTERO", "TD_CADENA", "TD_BOOLEANO", "BOOLEANO", "DOSPUNTOS", "WS", 
			"IDENTIFIER", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "ID", "DECIMAL", 
			"ENTERO"
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
			new SimpleSemantic();
			setState(59);
			match(PROGRAMA);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REMOTO) {
				{
				setState(60);
				match(REMOTO);
				}
			}

			setState(63);
			match(ID);
			setState(64);
			cuerpoPrograma();
			setState(65);
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
			setState(67);
			match(LBRACE);
			setState(68);
			miembros();
			setState(69);
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
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 292452535162208256L) != 0)) {
				{
				setState(77);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(71);
					setup();
					}
					break;
				case EJECUTAR:
					{
					setState(72);
					ejecucion();
					}
					break;
				case COMPONENTE:
				case TD_DECIMAL:
				case TD_ENTERO:
				case TD_CADENA:
				case TD_BOOLEANO:
					{
					setState(73);
					declaracionAtributo();
					setState(74);
					match(SEMI);
					}
					break;
				case FUNCION:
					{
					setState(76);
					funcion();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(81);
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
			setState(82);
			match(ID);
			setState(83);
			match(LPAREN);
			setState(84);
			match(RPAREN);
			setState(85);
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
			setState(87);
			match(EJECUTAR);
			setState(88);
			match(LPAREN);
			setState(89);
			match(RPAREN);
			setState(90);
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
			nuevaFuncion();
			setState(93);
			match(FUNCION);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4222124650659840L) != 0)) {
				{
				setState(94);
				tipo_dato();
				 retornoFuncion = t.getType(); 
				}
			}

			setState(99);
			((FuncionContext)_localctx).ID = match(ID);
			setState(100);
			match(LPAREN);
			setState(101);
			parametrosFormales();
			setState(102);
			match(RPAREN);
			 funcionDeclarada(((FuncionContext)_localctx).ID, listaParametros); 
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(104);
				bloque();
				}
				break;
			case SEMI:
				{
				setState(105);
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
			setState(108);
			tipo();
			td_variable=t.getType();
			setState(110);
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
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TD_DECIMAL:
			case TD_ENTERO:
			case TD_CADENA:
			case TD_BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				tipo_dato();
				}
				break;
			case COMPONENTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
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
			setState(117);
			declaracionDeVariable();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(118);
				match(COMMA);
				setState(119);
				declaracionDeVariable();
				}
				}
				setState(124);
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
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4222124650659840L) != 0)) {
				{
				setState(125);
				parametroFormal();
				}
			}

			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(128);
				match(COMMA);
				setState(129);
				parametroFormal();
				}
				}
				setState(134);
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
			setState(135);
			tipo_dato();
			setState(136);
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
			setState(140);
			match(LBRACE);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2026338408881606228L) != 0)) {
				{
				{
				setState(141);
				sentencia();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147);
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
		public Token t;
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
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				declaracionLocal();
				setState(150);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				expresion(0);
				setState(153);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(155);
				((SentenciaContext)_localctx).expresionSentencia = expresion(0);
				setState(156);
				match(SEMI);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				match(SEMI);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(159);
				match(CONTINUAR);
				setState(160);
				match(SEMI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(161);
				match(ROMPER);
				setState(162);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(163);
				((SentenciaContext)_localctx).t = match(DEVOLVER);
				comprobarPadre(((SentenciaContext)_localctx).t, _ctx);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2022116249869221892L) != 0)) {
					{
					comprobarRetorno(((SentenciaContext)_localctx).t);
					nuevaExpresion();
					setState(167);
					expresion(0);
					resolverExpresion(t -> retorno(retornoFuncion).test(t), s.getSymbolicName(retornoFuncion));
					}
				}

				setState(172);
				match(SEMI);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(173);
				match(ELEGIR);
				nuevoSwitch();
				setState(175);
				parExpresion();
				resolverSwitch(t);
				if(banderaSwitch)td_switch = salida.peek().getType();
				setState(178);
				match(LBRACE);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASO || _la==PRED) {
					{
					{
					setState(179);
					sentenciaSwitch();
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(185);
				match(RBRACE);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(187);
				match(REPETIR);
				setState(188);
				match(MIENTRAS);
				nuevaExpresion();
				setState(190);
				parExpresion();
				resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
				setState(192);
				sentencia();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(194);
				match(REPETIR);
				setState(195);
				match(PARA);
				setState(196);
				match(LPAREN);
				setState(197);
				controlFor();
				setState(198);
				match(RPAREN);
				setState(199);
				sentencia();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(201);
				match(SI);
				nuevaExpresion();
				setState(203);
				parExpresion();
				resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
				setState(205);
				sentencia();
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(206);
						match(SINO);
						setState(207);
						sentencia();
						}
						} 
					}
					setState(212);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(213);
				((SentenciaContext)_localctx).bloqueDeSentencias = bloque();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(214);
				llamadaAFuncion();
				setState(215);
				match(SEMI);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(217);
				accion();
				setState(218);
				match(SEMI);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(220);
				esperar();
				setState(221);
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
			setState(225);
			match(ID);
			nuevaExpresion();
			setState(227);
			argumentos();
			/*TODO:*/
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
			setState(230);
			match(LPAREN);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2022116249869221892L) != 0)) {
				{
				setState(231);
				listaExpresiones();
				}
			}

			setState(234);
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
			setState(236);
			iniciadorFor();
			setState(237);
			match(SEMI);
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2022116249869221892L) != 0)) {
				{
				nuevaExpresion();
				setState(239);
				expresion(0);
				resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
				}
			}

			setState(244);
			match(SEMI);
			setState(245);
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
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMPONENTE:
			case TD_DECIMAL:
			case TD_ENTERO:
			case TD_CADENA:
			case TD_BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
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
				setState(248);
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
			setState(251);
			expresion(0);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(252);
				match(COMMA);
				setState(253);
				expresion(0);
				}
				}
				setState(258);
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
		public Token OP;
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(SimpleParser.LPAREN, 0); }
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
			setState(259);
			((ParExpresionContext)_localctx).OP = match(LPAREN);
			 añadirAPila(((ParExpresionContext)_localctx).OP);
			setState(261);
			expresion(0);
			setState(262);
			((ParExpresionContext)_localctx).OP = match(RPAREN);
			 añadirAPila(((ParExpresionContext)_localctx).OP);
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
			setState(266); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(265);
				etiquetaSwitch();
				}
				}
				setState(268); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASO || _la==PRED );
			setState(271); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(270);
				sentencia();
				}
				}
				setState(273); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2026338408881606228L) != 0) );
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
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASO:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				match(CASO);
				{
				nuevaExpresion();
				setState(277);
				expresion(0);
				if (banderaSwitch)resolverExpresion(t -> retorno(td_switch).test(t) , "igual a la evaluación: "+s.getSymbolicName(td_switch));
				}
				setState(280);
				match(DOSPUNTOS);
				}
				break;
			case PRED:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				match(PRED);
				setState(283);
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
			setState(286);
			tipo();
			td_variable = t.getType();
			setState(288);
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
			setState(290);
			((DeclaracionDeVariableContext)_localctx).ID = match(ID);
			 if(declararVariable(((DeclaracionDeVariableContext)_localctx).ID,t))nuevaExpresion(); 
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(292);
				match(ASSIGN);
				setState(293);
				expresion(0);
				resolverAsignacion(((DeclaracionDeVariableContext)_localctx).ID);
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
		public TerminalNode DETENER() { return getToken(SimpleParser.DETENER, 0); }
		public TerminalNode ENCENDER() { return getToken(SimpleParser.ENCENDER, 0); }
		public TerminalNode APAGAR() { return getToken(SimpleParser.APAGAR, 0); }
		public TerminalNode MOSTRAR() { return getToken(SimpleParser.MOSTRAR, 0); }
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
			setState(298);
			match(ACCION);
			setState(299);
			match(LPAREN);
			setState(300);
			((AccionContext)_localctx).ID = match(ID);
			iniciarAccion(((AccionContext)_localctx).ID);
			setState(302);
			match(COMMA);
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SONAR:
				{
				setState(303);
				match(SONAR);
				 comprobarComponente(((AccionContext)_localctx).ID, "buzzer"); 
				setState(305);
				argumentos();
				/*TODO:*/
				}
				break;
			case ESCRIBIR:
				{
				setState(308);
				match(ESCRIBIR);
				comprobarComponente(((AccionContext)_localctx).ID, "display_lcd");
				setState(310);
				parExpresion();
				resolverExpresion(t -> t==CADENA | t == TD_CADENA, "TD_CADENA o CADENA");
				}
				break;
			case GIRAR:
				{
				setState(313);
				match(GIRAR);
				comprobarComponente(((AccionContext)_localctx).ID, "servo");
				setState(315);
				parExpresion();
				resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
				}
				break;
			case AVANZAR:
				{
				setState(318);
				match(AVANZAR);
				comprobarComponente(((AccionContext)_localctx).ID, "motor");
				setState(320);
				parExpresion();
				resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
				}
				break;
			case DETECTAR:
				{
				setState(323);
				match(DETECTAR);
				comprobarComponente(((AccionContext)_localctx).ID, "sensor_distancia");
				setState(325);
				parExpresion();
				resolverDetectar(t);
				}
				break;
			case DETENER:
				{
				setState(328);
				match(DETENER);
				comprobarComponente(((AccionContext)_localctx).ID, "motor");
				setState(330);
				parExpresion();
				resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
				}
				break;
			case ENCENDER:
				{
				setState(333);
				match(ENCENDER);
				comprobarComponente(((AccionContext)_localctx).ID, "led");
				setState(335);
				match(LPAREN);
				setState(336);
				match(RPAREN);
				}
				break;
			case APAGAR:
				{
				setState(337);
				match(APAGAR);
				comprobarComponente(((AccionContext)_localctx).ID, "led");
				setState(339);
				match(LPAREN);
				setState(340);
				match(RPAREN);
				}
				break;
			case MOSTRAR:
				{
				setState(341);
				match(MOSTRAR);
				comprobarComponente(((AccionContext)_localctx).ID, "siete_segmentos");
				setState(343);
				parExpresion();
				resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(348);
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
			setState(350);
			match(ESPERAR);
			nuevaExpresion();
			setState(352);
			parExpresion();
			resolverExpresion(t -> t == TD_ENTERO | t == ENTERO, "ENTERO o TD_ENTERO");
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
		public ExpresionContext expresionBinaria;
		public Token ID;
		public Token OP;
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
		public TerminalNode AND() { return getToken(SimpleParser.AND, 0); }
		public TerminalNode OR() { return getToken(SimpleParser.OR, 0); }
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
			setState(363);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(356);
				primaria();
				}
				break;
			case 2:
				{
				setState(357);
				((ExpresionContext)_localctx).ID = match(ID);
				setState(358);
				match(ASSIGN);
				nuevaExpresion();
				setState(360);
				expresion(1);
				if(usarVariable(((ExpresionContext)_localctx).ID))resolverAsignacion(((ExpresionContext)_localctx).ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(378);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpresionContext(_parentctx, _parentState);
					_localctx.expresionBinaria = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expresion);
					setState(365);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(372);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case DIVIDIR:
					case MULTIPLICAR:
						{
						setState(366);
						((ExpresionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==DIVIDIR || _la==MULTIPLICAR) ) {
							((ExpresionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case RESTAR:
					case SUMAR:
						{
						setState(367);
						((ExpresionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==RESTAR || _la==SUMAR) ) {
							((ExpresionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case MENOR_QUE:
					case MENOR_IGUAL:
					case MAYOR_QUE:
					case MAYOR_IGUAL:
						{
						setState(368);
						((ExpresionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16492674416640L) != 0)) ) {
							((ExpresionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case IGUAL:
					case DIFERENTE:
						{
						setState(369);
						((ExpresionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==IGUAL || _la==DIFERENTE) ) {
							((ExpresionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case AND:
						{
						setState(370);
						((ExpresionContext)_localctx).OP = match(AND);
						}
						break;
					case OR:
						{
						setState(371);
						((ExpresionContext)_localctx).OP = match(OR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					añadirAPila(((ExpresionContext)_localctx).OP);
					setState(375);
					expresion(3);
					}
					} 
				}
				setState(380);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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
			setState(385);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CADENA:
			case BOOLEANO:
			case DECIMAL:
			case ENTERO:
				enterOuterAlt(_localctx, 1);
				{
				setState(381);
				literal();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(382);
				parExpresion();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(383);
				((PrimariaContext)_localctx).ID = match(ID);
				if(usarVariable(((PrimariaContext)_localctx).ID))añadirAPila(((PrimariaContext)_localctx).ID);
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
		public Token t;
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
			setState(387);
			((LiteralContext)_localctx).t = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1733885873717510144L) != 0)) ) {
				((LiteralContext)_localctx).t = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			añadirAPila(((LiteralContext)_localctx).t);
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
		enterRule(_localctx, 56, RULE_tipo_dato);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			((Tipo_datoContext)_localctx).t = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4222124650659840L) != 0)) ) {
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001<\u018a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		">\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002N\b\u0002\n\u0002\f\u0002"+
		"Q\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005b\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005k\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007t\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0005\by\b\b\n\b\f\b|\t\b\u0001\t\u0003\t\u007f"+
		"\b\t\u0001\t\u0001\t\u0005\t\u0083\b\t\n\t\f\t\u0086\t\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000b\u008f\b"+
		"\u000b\n\u000b\f\u000b\u0092\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00ab\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0005\f\u00b5\b\f\n\f\f\f\u00b8\t\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f\u00d1\b\f\n\f\f\f\u00d4\t\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00e0\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00e9\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u00f3\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u00fa\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u00ff\b\u0011\n\u0011\f\u0011\u0102\t\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0004\u0013\u010b"+
		"\b\u0013\u000b\u0013\f\u0013\u010c\u0001\u0013\u0004\u0013\u0110\b\u0013"+
		"\u000b\u0013\f\u0013\u0111\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u011d\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0129\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u015b\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u016c\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0175\b\u0019\u0001\u0019"+
		"\u0001\u0019\u0005\u0019\u0179\b\u0019\n\u0019\f\u0019\u017c\t\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0182\b\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0000\u00012\u001d\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468\u0000\u0006\u0001\u0000"+
		",-\u0001\u0000./\u0001\u0000(+\u0001\u0000&\'\u0003\u0000\"\"44;<\u0001"+
		"\u000003\u01a2\u0000:\u0001\u0000\u0000\u0000\u0002C\u0001\u0000\u0000"+
		"\u0000\u0004O\u0001\u0000\u0000\u0000\u0006R\u0001\u0000\u0000\u0000\b"+
		"W\u0001\u0000\u0000\u0000\n\\\u0001\u0000\u0000\u0000\fl\u0001\u0000\u0000"+
		"\u0000\u000es\u0001\u0000\u0000\u0000\u0010u\u0001\u0000\u0000\u0000\u0012"+
		"~\u0001\u0000\u0000\u0000\u0014\u0087\u0001\u0000\u0000\u0000\u0016\u008c"+
		"\u0001\u0000\u0000\u0000\u0018\u00df\u0001\u0000\u0000\u0000\u001a\u00e1"+
		"\u0001\u0000\u0000\u0000\u001c\u00e6\u0001\u0000\u0000\u0000\u001e\u00ec"+
		"\u0001\u0000\u0000\u0000 \u00f9\u0001\u0000\u0000\u0000\"\u00fb\u0001"+
		"\u0000\u0000\u0000$\u0103\u0001\u0000\u0000\u0000&\u010a\u0001\u0000\u0000"+
		"\u0000(\u011c\u0001\u0000\u0000\u0000*\u011e\u0001\u0000\u0000\u0000,"+
		"\u0122\u0001\u0000\u0000\u0000.\u012a\u0001\u0000\u0000\u00000\u015e\u0001"+
		"\u0000\u0000\u00002\u016b\u0001\u0000\u0000\u00004\u0181\u0001\u0000\u0000"+
		"\u00006\u0183\u0001\u0000\u0000\u00008\u0186\u0001\u0000\u0000\u0000:"+
		";\u0006\u0000\uffff\uffff\u0000;=\u0005\u0001\u0000\u0000<>\u0005\r\u0000"+
		"\u0000=<\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0001\u0000"+
		"\u0000\u0000?@\u0005:\u0000\u0000@A\u0003\u0002\u0001\u0000AB\u0005\u0000"+
		"\u0000\u0001B\u0001\u0001\u0000\u0000\u0000CD\u0005\u0004\u0000\u0000"+
		"DE\u0003\u0004\u0002\u0000EF\u0005\u0005\u0000\u0000F\u0003\u0001\u0000"+
		"\u0000\u0000GN\u0003\u0006\u0003\u0000HN\u0003\b\u0004\u0000IJ\u0003\f"+
		"\u0006\u0000JK\u0005\u0006\u0000\u0000KN\u0001\u0000\u0000\u0000LN\u0003"+
		"\n\u0005\u0000MG\u0001\u0000\u0000\u0000MH\u0001\u0000\u0000\u0000MI\u0001"+
		"\u0000\u0000\u0000ML\u0001\u0000\u0000\u0000NQ\u0001\u0000\u0000\u0000"+
		"OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000P\u0005\u0001\u0000"+
		"\u0000\u0000QO\u0001\u0000\u0000\u0000RS\u0005:\u0000\u0000ST\u0005\u0002"+
		"\u0000\u0000TU\u0005\u0003\u0000\u0000UV\u0003\u0016\u000b\u0000V\u0007"+
		"\u0001\u0000\u0000\u0000WX\u0005\u000f\u0000\u0000XY\u0005\u0002\u0000"+
		"\u0000YZ\u0005\u0003\u0000\u0000Z[\u0003\u0016\u000b\u0000[\t\u0001\u0000"+
		"\u0000\u0000\\]\u0006\u0005\uffff\uffff\u0000]a\u0005\u0010\u0000\u0000"+
		"^_\u00038\u001c\u0000_`\u0006\u0005\uffff\uffff\u0000`b\u0001\u0000\u0000"+
		"\u0000a^\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0001\u0000"+
		"\u0000\u0000cd\u0005:\u0000\u0000de\u0005\u0002\u0000\u0000ef\u0003\u0012"+
		"\t\u0000fg\u0005\u0003\u0000\u0000gj\u0006\u0005\uffff\uffff\u0000hk\u0003"+
		"\u0016\u000b\u0000ik\u0005\u0006\u0000\u0000jh\u0001\u0000\u0000\u0000"+
		"ji\u0001\u0000\u0000\u0000k\u000b\u0001\u0000\u0000\u0000lm\u0003\u000e"+
		"\u0007\u0000mn\u0006\u0006\uffff\uffff\u0000no\u0003\u0010\b\u0000o\r"+
		"\u0001\u0000\u0000\u0000pt\u00038\u001c\u0000qr\u0005#\u0000\u0000rt\u0006"+
		"\u0007\uffff\uffff\u0000sp\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000"+
		"\u0000t\u000f\u0001\u0000\u0000\u0000uz\u0003,\u0016\u0000vw\u0005\u0007"+
		"\u0000\u0000wy\u0003,\u0016\u0000xv\u0001\u0000\u0000\u0000y|\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{\u0011"+
		"\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}\u007f\u0003\u0014"+
		"\n\u0000~}\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f"+
		"\u0084\u0001\u0000\u0000\u0000\u0080\u0081\u0005\u0007\u0000\u0000\u0081"+
		"\u0083\u0003\u0014\n\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0086"+
		"\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0001\u0000\u0000\u0000\u0085\u0013\u0001\u0000\u0000\u0000\u0086\u0084"+
		"\u0001\u0000\u0000\u0000\u0087\u0088\u00038\u001c\u0000\u0088\u0089\u0005"+
		":\u0000\u0000\u0089\u008a\u0006\n\uffff\uffff\u0000\u008a\u008b\u0006"+
		"\n\uffff\uffff\u0000\u008b\u0015\u0001\u0000\u0000\u0000\u008c\u0090\u0005"+
		"\u0004\u0000\u0000\u008d\u008f\u0003\u0018\f\u0000\u008e\u008d\u0001\u0000"+
		"\u0000\u0000\u008f\u0092\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000"+
		"\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0093\u0001\u0000"+
		"\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0094\u0005\u0005"+
		"\u0000\u0000\u0094\u0017\u0001\u0000\u0000\u0000\u0095\u0096\u0003*\u0015"+
		"\u0000\u0096\u0097\u0005\u0006\u0000\u0000\u0097\u00e0\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u00032\u0019\u0000\u0099\u009a\u0005\u0006\u0000\u0000"+
		"\u009a\u00e0\u0001\u0000\u0000\u0000\u009b\u009c\u00032\u0019\u0000\u009c"+
		"\u009d\u0005\u0006\u0000\u0000\u009d\u00e0\u0001\u0000\u0000\u0000\u009e"+
		"\u00e0\u0005\u0006\u0000\u0000\u009f\u00a0\u0005\u0011\u0000\u0000\u00a0"+
		"\u00e0\u0005\u0006\u0000\u0000\u00a1\u00a2\u0005\u0012\u0000\u0000\u00a2"+
		"\u00e0\u0005\u0006\u0000\u0000\u00a3\u00a4\u0005\u0013\u0000\u0000\u00a4"+
		"\u00aa\u0006\f\uffff\uffff\u0000\u00a5\u00a6\u0006\f\uffff\uffff\u0000"+
		"\u00a6\u00a7\u0006\f\uffff\uffff\u0000\u00a7\u00a8\u00032\u0019\u0000"+
		"\u00a8\u00a9\u0006\f\uffff\uffff\u0000\u00a9\u00ab\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a5\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00e0\u0005\u0006\u0000\u0000"+
		"\u00ad\u00ae\u0005\u0014\u0000\u0000\u00ae\u00af\u0006\f\uffff\uffff\u0000"+
		"\u00af\u00b0\u0003$\u0012\u0000\u00b0\u00b1\u0006\f\uffff\uffff\u0000"+
		"\u00b1\u00b2\u0006\f\uffff\uffff\u0000\u00b2\u00b6\u0005\u0004\u0000\u0000"+
		"\u00b3\u00b5\u0003&\u0013\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b9\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b6\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005\u0005\u0000\u0000\u00ba"+
		"\u00e0\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\n\u0000\u0000\u00bc\u00bd"+
		"\u0005\f\u0000\u0000\u00bd\u00be\u0006\f\uffff\uffff\u0000\u00be\u00bf"+
		"\u0003$\u0012\u0000\u00bf\u00c0\u0006\f\uffff\uffff\u0000\u00c0\u00c1"+
		"\u0003\u0018\f\u0000\u00c1\u00e0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005"+
		"\n\u0000\u0000\u00c3\u00c4\u0005\u0015\u0000\u0000\u00c4\u00c5\u0005\u0002"+
		"\u0000\u0000\u00c5\u00c6\u0003\u001e\u000f\u0000\u00c6\u00c7\u0005\u0003"+
		"\u0000\u0000\u00c7\u00c8\u0003\u0018\f\u0000\u00c8\u00e0\u0001\u0000\u0000"+
		"\u0000\u00c9\u00ca\u0005\t\u0000\u0000\u00ca\u00cb\u0006\f\uffff\uffff"+
		"\u0000\u00cb\u00cc\u0003$\u0012\u0000\u00cc\u00cd\u0006\f\uffff\uffff"+
		"\u0000\u00cd\u00d2\u0003\u0018\f\u0000\u00ce\u00cf\u0005\u0016\u0000\u0000"+
		"\u00cf\u00d1\u0003\u0018\f\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d2"+
		"\u00d3\u0001\u0000\u0000\u0000\u00d3\u00e0\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d5\u00e0\u0003\u0016\u000b\u0000\u00d6"+
		"\u00d7\u0003\u001a\r\u0000\u00d7\u00d8\u0005\u0006\u0000\u0000\u00d8\u00e0"+
		"\u0001\u0000\u0000\u0000\u00d9\u00da\u0003.\u0017\u0000\u00da\u00db\u0005"+
		"\u0006\u0000\u0000\u00db\u00e0\u0001\u0000\u0000\u0000\u00dc\u00dd\u0003"+
		"0\u0018\u0000\u00dd\u00de\u0005\u0006\u0000\u0000\u00de\u00e0\u0001\u0000"+
		"\u0000\u0000\u00df\u0095\u0001\u0000\u0000\u0000\u00df\u0098\u0001\u0000"+
		"\u0000\u0000\u00df\u009b\u0001\u0000\u0000\u0000\u00df\u009e\u0001\u0000"+
		"\u0000\u0000\u00df\u009f\u0001\u0000\u0000\u0000\u00df\u00a1\u0001\u0000"+
		"\u0000\u0000\u00df\u00a3\u0001\u0000\u0000\u0000\u00df\u00ad\u0001\u0000"+
		"\u0000\u0000\u00df\u00bb\u0001\u0000\u0000\u0000\u00df\u00c2\u0001\u0000"+
		"\u0000\u0000\u00df\u00c9\u0001\u0000\u0000\u0000\u00df\u00d5\u0001\u0000"+
		"\u0000\u0000\u00df\u00d6\u0001\u0000\u0000\u0000\u00df\u00d9\u0001\u0000"+
		"\u0000\u0000\u00df\u00dc\u0001\u0000\u0000\u0000\u00e0\u0019\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0005:\u0000\u0000\u00e2\u00e3\u0006\r\uffff"+
		"\uffff\u0000\u00e3\u00e4\u0003\u001c\u000e\u0000\u00e4\u00e5\u0006\r\uffff"+
		"\uffff\u0000\u00e5\u001b\u0001\u0000\u0000\u0000\u00e6\u00e8\u0005\u0002"+
		"\u0000\u0000\u00e7\u00e9\u0003\"\u0011\u0000\u00e8\u00e7\u0001\u0000\u0000"+
		"\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000"+
		"\u0000\u00ea\u00eb\u0005\u0003\u0000\u0000\u00eb\u001d\u0001\u0000\u0000"+
		"\u0000\u00ec\u00ed\u0003 \u0010\u0000\u00ed\u00f2\u0005\u0006\u0000\u0000"+
		"\u00ee\u00ef\u0006\u000f\uffff\uffff\u0000\u00ef\u00f0\u00032\u0019\u0000"+
		"\u00f0\u00f1\u0006\u000f\uffff\uffff\u0000\u00f1\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f2\u00ee\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005\u0006\u0000"+
		"\u0000\u00f5\u00f6\u0003\"\u0011\u0000\u00f6\u001f\u0001\u0000\u0000\u0000"+
		"\u00f7\u00fa\u0003*\u0015\u0000\u00f8\u00fa\u0003\"\u0011\u0000\u00f9"+
		"\u00f7\u0001\u0000\u0000\u0000\u00f9\u00f8\u0001\u0000\u0000\u0000\u00fa"+
		"!\u0001\u0000\u0000\u0000\u00fb\u0100\u00032\u0019\u0000\u00fc\u00fd\u0005"+
		"\u0007\u0000\u0000\u00fd\u00ff\u00032\u0019\u0000\u00fe\u00fc\u0001\u0000"+
		"\u0000\u0000\u00ff\u0102\u0001\u0000\u0000\u0000\u0100\u00fe\u0001\u0000"+
		"\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101#\u0001\u0000\u0000"+
		"\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0103\u0104\u0005\u0002\u0000"+
		"\u0000\u0104\u0105\u0006\u0012\uffff\uffff\u0000\u0105\u0106\u00032\u0019"+
		"\u0000\u0106\u0107\u0005\u0003\u0000\u0000\u0107\u0108\u0006\u0012\uffff"+
		"\uffff\u0000\u0108%\u0001\u0000\u0000\u0000\u0109\u010b\u0003(\u0014\u0000"+
		"\u010a\u0109\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000"+
		"\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000"+
		"\u010d\u010f\u0001\u0000\u0000\u0000\u010e\u0110\u0003\u0018\f\u0000\u010f"+
		"\u010e\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000\u0000\u0000\u0111"+
		"\u010f\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112"+
		"\'\u0001\u0000\u0000\u0000\u0113\u0114\u0005\u0017\u0000\u0000\u0114\u0115"+
		"\u0006\u0014\uffff\uffff\u0000\u0115\u0116\u00032\u0019\u0000\u0116\u0117"+
		"\u0006\u0014\uffff\uffff\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118"+
		"\u0119\u00055\u0000\u0000\u0119\u011d\u0001\u0000\u0000\u0000\u011a\u011b"+
		"\u0005\u0019\u0000\u0000\u011b\u011d\u00055\u0000\u0000\u011c\u0113\u0001"+
		"\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d)\u0001\u0000"+
		"\u0000\u0000\u011e\u011f\u0003\u000e\u0007\u0000\u011f\u0120\u0006\u0015"+
		"\uffff\uffff\u0000\u0120\u0121\u0003,\u0016\u0000\u0121+\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0005:\u0000\u0000\u0123\u0128\u0006\u0016\uffff\uffff"+
		"\u0000\u0124\u0125\u0005\b\u0000\u0000\u0125\u0126\u00032\u0019\u0000"+
		"\u0126\u0127\u0006\u0016\uffff\uffff\u0000\u0127\u0129\u0001\u0000\u0000"+
		"\u0000\u0128\u0124\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000"+
		"\u0000\u0129-\u0001\u0000\u0000\u0000\u012a\u012b\u0005\u000e\u0000\u0000"+
		"\u012b\u012c\u0005\u0002\u0000\u0000\u012c\u012d\u0005:\u0000\u0000\u012d"+
		"\u012e\u0006\u0017\uffff\uffff\u0000\u012e\u015a\u0005\u0007\u0000\u0000"+
		"\u012f\u0130\u0005\u001a\u0000\u0000\u0130\u0131\u0006\u0017\uffff\uffff"+
		"\u0000\u0131\u0132\u0003\u001c\u000e\u0000\u0132\u0133\u0006\u0017\uffff"+
		"\uffff\u0000\u0133\u015b\u0001\u0000\u0000\u0000\u0134\u0135\u0005\u0018"+
		"\u0000\u0000\u0135\u0136\u0006\u0017\uffff\uffff\u0000\u0136\u0137\u0003"+
		"$\u0012\u0000\u0137\u0138\u0006\u0017\uffff\uffff\u0000\u0138\u015b\u0001"+
		"\u0000\u0000\u0000\u0139\u013a\u0005\u001b\u0000\u0000\u013a\u013b\u0006"+
		"\u0017\uffff\uffff\u0000\u013b\u013c\u0003$\u0012\u0000\u013c\u013d\u0006"+
		"\u0017\uffff\uffff\u0000\u013d\u015b\u0001\u0000\u0000\u0000\u013e\u013f"+
		"\u0005\u001c\u0000\u0000\u013f\u0140\u0006\u0017\uffff\uffff\u0000\u0140"+
		"\u0141\u0003$\u0012\u0000\u0141\u0142\u0006\u0017\uffff\uffff\u0000\u0142"+
		"\u015b\u0001\u0000\u0000\u0000\u0143\u0144\u0005\u001d\u0000\u0000\u0144"+
		"\u0145\u0006\u0017\uffff\uffff\u0000\u0145\u0146\u0003$\u0012\u0000\u0146"+
		"\u0147\u0006\u0017\uffff\uffff\u0000\u0147\u015b\u0001\u0000\u0000\u0000"+
		"\u0148\u0149\u0005 \u0000\u0000\u0149\u014a\u0006\u0017\uffff\uffff\u0000"+
		"\u014a\u014b\u0003$\u0012\u0000\u014b\u014c\u0006\u0017\uffff\uffff\u0000"+
		"\u014c\u015b\u0001\u0000\u0000\u0000\u014d\u014e\u0005\u001f\u0000\u0000"+
		"\u014e\u014f\u0006\u0017\uffff\uffff\u0000\u014f\u0150\u0005\u0002\u0000"+
		"\u0000\u0150\u015b\u0005\u0003\u0000\u0000\u0151\u0152\u0005!\u0000\u0000"+
		"\u0152\u0153\u0006\u0017\uffff\uffff\u0000\u0153\u0154\u0005\u0002\u0000"+
		"\u0000\u0154\u015b\u0005\u0003\u0000\u0000\u0155\u0156\u0005\u001e\u0000"+
		"\u0000\u0156\u0157\u0006\u0017\uffff\uffff\u0000\u0157\u0158\u0003$\u0012"+
		"\u0000\u0158\u0159\u0006\u0017\uffff\uffff\u0000\u0159\u015b\u0001\u0000"+
		"\u0000\u0000\u015a\u012f\u0001\u0000\u0000\u0000\u015a\u0134\u0001\u0000"+
		"\u0000\u0000\u015a\u0139\u0001\u0000\u0000\u0000\u015a\u013e\u0001\u0000"+
		"\u0000\u0000\u015a\u0143\u0001\u0000\u0000\u0000\u015a\u0148\u0001\u0000"+
		"\u0000\u0000\u015a\u014d\u0001\u0000\u0000\u0000\u015a\u0151\u0001\u0000"+
		"\u0000\u0000\u015a\u0155\u0001\u0000\u0000\u0000\u015b\u015c\u0001\u0000"+
		"\u0000\u0000\u015c\u015d\u0005\u0003\u0000\u0000\u015d/\u0001\u0000\u0000"+
		"\u0000\u015e\u015f\u0005\u000b\u0000\u0000\u015f\u0160\u0006\u0018\uffff"+
		"\uffff\u0000\u0160\u0161\u0003$\u0012\u0000\u0161\u0162\u0006\u0018\uffff"+
		"\uffff\u0000\u01621\u0001\u0000\u0000\u0000\u0163\u0164\u0006\u0019\uffff"+
		"\uffff\u0000\u0164\u016c\u00034\u001a\u0000\u0165\u0166\u0005:\u0000\u0000"+
		"\u0166\u0167\u0005\b\u0000\u0000\u0167\u0168\u0006\u0019\uffff\uffff\u0000"+
		"\u0168\u0169\u00032\u0019\u0001\u0169\u016a\u0006\u0019\uffff\uffff\u0000"+
		"\u016a\u016c\u0001\u0000\u0000\u0000\u016b\u0163\u0001\u0000\u0000\u0000"+
		"\u016b\u0165\u0001\u0000\u0000\u0000\u016c\u017a\u0001\u0000\u0000\u0000"+
		"\u016d\u0174\n\u0002\u0000\u0000\u016e\u0175\u0007\u0000\u0000\u0000\u016f"+
		"\u0175\u0007\u0001\u0000\u0000\u0170\u0175\u0007\u0002\u0000\u0000\u0171"+
		"\u0175\u0007\u0003\u0000\u0000\u0172\u0175\u0005$\u0000\u0000\u0173\u0175"+
		"\u0005%\u0000\u0000\u0174\u016e\u0001\u0000\u0000\u0000\u0174\u016f\u0001"+
		"\u0000\u0000\u0000\u0174\u0170\u0001\u0000\u0000\u0000\u0174\u0171\u0001"+
		"\u0000\u0000\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0174\u0173\u0001"+
		"\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u0177\u0006"+
		"\u0019\uffff\uffff\u0000\u0177\u0179\u00032\u0019\u0003\u0178\u016d\u0001"+
		"\u0000\u0000\u0000\u0179\u017c\u0001\u0000\u0000\u0000\u017a\u0178\u0001"+
		"\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b3\u0001\u0000"+
		"\u0000\u0000\u017c\u017a\u0001\u0000\u0000\u0000\u017d\u0182\u00036\u001b"+
		"\u0000\u017e\u0182\u0003$\u0012\u0000\u017f\u0180\u0005:\u0000\u0000\u0180"+
		"\u0182\u0006\u001a\uffff\uffff\u0000\u0181\u017d\u0001\u0000\u0000\u0000"+
		"\u0181\u017e\u0001\u0000\u0000\u0000\u0181\u017f\u0001\u0000\u0000\u0000"+
		"\u01825\u0001\u0000\u0000\u0000\u0183\u0184\u0007\u0004\u0000\u0000\u0184"+
		"\u0185\u0006\u001b\uffff\uffff\u0000\u01857\u0001\u0000\u0000\u0000\u0186"+
		"\u0187\u0007\u0005\u0000\u0000\u0187\u0188\u0006\u001c\uffff\uffff\u0000"+
		"\u01889\u0001\u0000\u0000\u0000\u001b=MOajsz~\u0084\u0090\u00aa\u00b6"+
		"\u00d2\u00df\u00e8\u00f2\u00f9\u0100\u010c\u0111\u011c\u0128\u015a\u016b"+
		"\u0174\u017a\u0181";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}