package Analizadores;
// Generated from Simple.g4 by ANTLR 4.13.1

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
		PAR_ABRIR=32, PAR_CERRAR=33, ES=34, MAYOR=35, MENOR=36, IGUAL=37, QUE=38, 
		DIFERENTE=39, ESPERAR=40, COMA=41, WS=42;
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
			"PAR_ABRIR", "PAR_CERRAR", "ES", "MAYOR", "MENOR", "IGUAL", "QUE", "DIFERENTE", 
			"ESPERAR", "COMA", "WS"
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


	    final Map<String, Token> variablesDeclaradas = new HashMap<>();
	    Stack<Token> salida = new Stack<>();
	    Stack<Token> pilaOperadores = new Stack<>();
	    boolean isDeclaration = false;
	    Token t;
	    final Set<String> booleanos = new HashSet<>(Arrays.asList("v","f","verdadero","falso","booleano","and","or"));
	    final Set<String> algebraicos = new HashSet<>(Arrays.asList("decimal","entero"));

	    //#region CódigoSemántico
	    void imprimirPila(){
	        Stack<Token> x = (Stack<Token>) salida.clone();
	        String xd = "";
	        while (!x.empty()) {
	            xd+=x.pop().getText()+" ";
	        }
	        System.out.println(xd);
	    }

	    boolean resolverPila(String tipo){
	        // while (!pilaOperadores.empty()) {
	        //     salida.push(pilaOperadores.pop());
	        // }
	        // Sacar el resto de operadores de la pila
	        //invertirSalida(); 
	        //Para leer de izquierda a derecha y no de derecha a izquierda (como llega)
	        if (salida.size()==1){
	            Token t = salida.pop();
	            String tipoP;
	            if (booleanos.contains(t.getText())){
	                tipoP="booleano";
	            } else {
	                tipoP = t.getType() == NUMERO ? t.getText().contains(".") ? "decimal" : "entero" : t.getText();
	            }
	            return tipo.equals(tipoP);
	        }
	        Stack<Token> t = new Stack<>();
	        while (!salida.empty()) {
	            if (salida.peek().getType()!=OP_BIN){
	                t.push(salida.pop());
	            } else {
	                try {
	                    t.push(validarOperacion(t.pop(),t.pop(),salida.pop()));
	                } catch (Exception e) {
	                    return false;
	                }
	            }
	        }
	        return t.pop().equals(tipo);
	    }

	    private Token validarOperacion(Token operando1,Token operando2 , Token operador) throws Exception {
	        if (booleanos.contains(operador.getText())){
	            if(!booleanos.contains(operando1.getText()) || !booleanos.contains(operando2.getText()))
	                throw new Exception();
	            return new CommonToken(TIPO_DATO,"booleano");
	        } else {
	            String t1 = operando1.getType()==NUMERO ? operando1.getText().contains(".") ? "decimal" : "entero" : operando1.getText();
	            String t2 = operando2.getType()==NUMERO ? operando2.getText().contains(".") ? "decimal" : "entero" : operando2.getText();
	            if(!algebraicos.contains(t1) || !algebraicos.contains(t2))
	                throw new Exception();
	            return (t1.equals("decimal") || t2.equals("decimal")) ? new CommonToken(TIPO_DATO,"decimal") : new CommonToken(TIPO_DATO,"entero"); 
	        }
	    }

	    private void invertirSalida() {
			while (!pilaOperadores.empty()) {
				salida.push(pilaOperadores.pop());
			}
			// Sacar el resto de operadores de la pila
	        Stack<Token> invertido = new Stack<>();
	        while (!salida.empty()) {
	            invertido.push(salida.pop());
	        }
	        salida = invertido;
	    }

	    // Funcion para añadir a la pila semántica
	    void añadirAPila(Token t) {
	        if (t == null)
	            return;
	        int type = t.getType();
	        if (type == ID){
	                salida.push(variablesDeclaradas.get(t.getText()));
	        } else if (type == NUMERO || type == BOOLEANO){
	            salida.push(t);
	        } else if (type == OP_BIN){
	            while (!pilaOperadores.empty() && precedencia(pilaOperadores.peek().getText()) >= precedencia(t.getText())) {
	                salida.push(pilaOperadores.pop());
	            }
	            pilaOperadores.push(t);
	        } else if (type == PAR_ABRIR){
	            pilaOperadores.push(t);
	        } else if (type == PAR_CERRAR){
	            while (!pilaOperadores.empty() && pilaOperadores.peek().getType()!=PAR_ABRIR) {
	                salida.push(pilaOperadores.pop());                
	            }
	            if (!pilaOperadores.empty() && pilaOperadores.peek().getType()==PAR_ABRIR)
	                pilaOperadores.pop();
	        }
	    }

	    private int precedencia(String text) {
	        switch (text) {
	            case "+":
	            case "-":
	            case "or":
	                return 1;
	            case "*":
	            case "/":
	            case "and":
	                return 2;
	            default:
	                return -1;
	        }
	    }

	    // Predicado semántico para verificar si una variable se ha declarado al
	    // intentar declararla
	    boolean variableDeclarada(Token variable, Token tipoVariable) {
			if (!isDeclaration)
				return false;
			String nombreVariable = variable.getText();
	        if (variablesDeclaradas.containsKey(nombreVariable))
				return true;
			variablesDeclaradas.put(nombreVariable,tipoVariable);
			return false;
		}

	    // Predicado semántico para verificar si una variable se ha declarado al
	    // intentar declararla
	    boolean usarVariable(Token variable) {
	        return variablesDeclaradas.containsKey(variable.getText());
	    }
	    //#endregion

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
				if (!( !variableDeclarada(((DeclaracionContext)_localctx).ID,((DeclaracionContext)_localctx).t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,$t) ");
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
			if (!( !variableDeclarada(((AsignacionContext)_localctx).ID,t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,t) ");
			setState(59);
			if (!( usarVariable(((AsignacionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
			setState(60);
			match(SIGNO_IGUAL);
			setState(61);
			expresion();
			 
			            invertirSalida();
			            imprimirPila();
					
			setState(63);
			if (!(resolverPila(variablesDeclaradas.get((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)).getText()))) throw new FailedPredicateException(this, "resolverPila(variablesDeclaradas.get($ID.text).getText())");
			setState(64);
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
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
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
	}

	public final CondicionalifContext condicionalif() throws RecognitionException {
		CondicionalifContext _localctx = new CondicionalifContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_condicionalif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(SI);
			setState(67);
			match(PAR_ABRIR);
			setState(68);
			expresion();
			setState(69);
			match(PAR_CERRAR);
			setState(70);
			match(BRACKET_ABRIR);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099583465480L) != 0)) {
				{
				{
				setState(71);
				sentencia();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(BRACKET_CERRAR);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SI_NO) {
				{
				setState(78);
				match(SI_NO);
				setState(79);
				match(BRACKET_ABRIR);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099583465480L) != 0)) {
					{
					{
					setState(80);
					sentencia();
					}
					}
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(86);
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
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
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
	}

	public final CicloWhileContext cicloWhile() throws RecognitionException {
		CicloWhileContext _localctx = new CicloWhileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cicloWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(REPETIR);
			setState(90);
			match(MIENTRAS);
			setState(91);
			match(PAR_ABRIR);
			setState(92);
			expresion();
			setState(93);
			match(PAR_CERRAR);
			setState(94);
			match(BRACKET_ABRIR);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099583465480L) != 0)) {
				{
				{
				setState(95);
				sentencia();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
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
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
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
	}

	public final AccionContext accion() throws RecognitionException {
		AccionContext _localctx = new AccionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_accion);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESPERAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				esperar();
				}
				break;
			case ACCION:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				match(ACCION);
				setState(105);
				match(PAR_ABRIR);
				setState(106);
				((AccionContext)_localctx).ID = match(ID);
				setState(107);
				if (!( usarVariable(((AccionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
				setState(108);
				match(COMA);
				setState(111);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIRAR:
					{
					setState(109);
					girar();
					}
					break;
				case ESCRIBIR:
					{
					setState(110);
					escribir();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(113);
				match(PAR_CERRAR);
				setState(114);
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
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode TIEMPO() { return getToken(SimpleParser.TIEMPO, 0); }
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
		public TerminalNode FIN_LINEA() { return getToken(SimpleParser.FIN_LINEA, 0); }
		public EsperarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_esperar; }
	}

	public final EsperarContext esperar() throws RecognitionException {
		EsperarContext _localctx = new EsperarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_esperar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(ESPERAR);
			setState(119);
			match(PAR_ABRIR);
			setState(120);
			match(NUMERO);
			setState(121);
			match(COMA);
			setState(122);
			match(TIEMPO);
			setState(123);
			match(PAR_CERRAR);
			setState(124);
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
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode DIRECCION() { return getToken(SimpleParser.DIRECCION, 0); }
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
		public GirarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_girar; }
	}

	public final GirarContext girar() throws RecognitionException {
		GirarContext _localctx = new GirarContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_girar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(GIRAR);
			setState(127);
			match(PAR_ABRIR);
			setState(128);
			match(NUMERO);
			setState(129);
			match(COMA);
			setState(130);
			match(DIRECCION);
			setState(131);
			match(PAR_CERRAR);
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
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode CADENA() { return getToken(SimpleParser.CADENA, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public EscribirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escribir; }
	}

	public final EscribirContext escribir() throws RecognitionException {
		EscribirContext _localctx = new EscribirContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_escribir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(ESCRIBIR);
			setState(134);
			match(PAR_ABRIR);
			setState(135);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(136);
			match(PAR_CERRAR);
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
		public Token t;
		public TerminalNode BOOLEANO() { return getToken(SimpleParser.BOOLEANO, 0); }
		public TerminalNode NUMERO() { return getToken(SimpleParser.NUMERO, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
		public TerminoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termino; }
	}

	public final TerminoContext termino() throws RecognitionException {
		TerminoContext _localctx = new TerminoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_termino);
		try {
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMERO:
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOLEANO:
					{
					setState(138);
					((TerminoContext)_localctx).t = match(BOOLEANO);
					}
					break;
				case NUMERO:
					{
					setState(139);
					((TerminoContext)_localctx).t = match(NUMERO);
					}
					break;
				case ID:
					{
					setState(140);
					((TerminoContext)_localctx).t = match(ID);
					setState(141);
					if (!( usarVariable(((TerminoContext)_localctx).t) )) throw new FailedPredicateException(this, " usarVariable($t) ");
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 añadirAPila(((TerminoContext)_localctx).t); 
				}
				break;
			case PAR_ABRIR:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				((TerminoContext)_localctx).t = match(PAR_ABRIR);
				 añadirAPila(((TerminoContext)_localctx).t); 
				setState(147);
				expresion();
				setState(148);
				((TerminoContext)_localctx).t = match(PAR_CERRAR);
				 añadirAPila(((TerminoContext)_localctx).t); 
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
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expresion);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			termino();
			setState(157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(154);
					operacionBinaria();
					}
					} 
				}
				setState(159);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		public Token OP_BIN;
		public TerminalNode OP_BIN() { return getToken(SimpleParser.OP_BIN, 0); }
		public TerminoContext termino() {
			return getRuleContext(TerminoContext.class,0);
		}
		public OperacionBinariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacionBinaria; }
	}

	public final OperacionBinariaContext operacionBinaria() throws RecognitionException {
		OperacionBinariaContext _localctx = new OperacionBinariaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operacionBinaria);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(160);
			((OperacionBinariaContext)_localctx).OP_BIN = match(OP_BIN);
			 añadirAPila(((OperacionBinariaContext)_localctx).OP_BIN); 
			setState(162);
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
			return  !variableDeclarada(((DeclaracionContext)_localctx).ID,((DeclaracionContext)_localctx).t) ;
		}
		return true;
	}
	private boolean asignacion_sempred(AsignacionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return  !variableDeclarada(((AsignacionContext)_localctx).ID,t) ;
		case 2:
			return  usarVariable(((AsignacionContext)_localctx).ID) ;
		case 3:
			return resolverPila(variablesDeclaradas.get((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)).getText());
		}
		return true;
	}
	private boolean accion_sempred(AccionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return  usarVariable(((AccionContext)_localctx).ID) ;
		}
		return true;
	}
	private boolean termino_sempred(TerminoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return  usarVariable(((TerminoContext)_localctx).t) ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001*\u00a5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000"+
		"\u001f\b\u0000\u000b\u0000\f\u0000 \u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001*\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002/\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00026\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"I\b\u0004\n\u0004\f\u0004L\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004R\b\u0004\n\u0004\f\u0004U\t\u0004\u0001\u0004"+
		"\u0003\u0004X\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005a\b\u0005\n\u0005\f\u0005"+
		"d\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"p\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006u\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u008f\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u0098\b\n\u0001\u000b\u0001\u000b\u0005\u000b\u009c\b\u000b"+
		"\n\u000b\f\u000b\u009f\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0000\u0000\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u0000\u0001\u0001\u0000\u0003\u0005\u00a8\u0000\u001a\u0001\u0000"+
		"\u0000\u0000\u0002)\u0001\u0000\u0000\u0000\u0004+\u0001\u0000\u0000\u0000"+
		"\u00069\u0001\u0000\u0000\u0000\bB\u0001\u0000\u0000\u0000\nY\u0001\u0000"+
		"\u0000\u0000\ft\u0001\u0000\u0000\u0000\u000ev\u0001\u0000\u0000\u0000"+
		"\u0010~\u0001\u0000\u0000\u0000\u0012\u0085\u0001\u0000\u0000\u0000\u0014"+
		"\u0097\u0001\u0000\u0000\u0000\u0016\u0099\u0001\u0000\u0000\u0000\u0018"+
		"\u00a0\u0001\u0000\u0000\u0000\u001a\u001b\u0005\n\u0000\u0000\u001b\u001c"+
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
		"\u000b\u0000>?\u0006\u0003\uffff\uffff\u0000?@\u0004\u0003\u0003\u0001"+
		"@A\u0005\u001b\u0000\u0000A\u0007\u0001\u0000\u0000\u0000BC\u0005\u0013"+
		"\u0000\u0000CD\u0005 \u0000\u0000DE\u0003\u0016\u000b\u0000EF\u0005!\u0000"+
		"\u0000FJ\u0005\u001e\u0000\u0000GI\u0003\u0002\u0001\u0000HG\u0001\u0000"+
		"\u0000\u0000IL\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000JK\u0001"+
		"\u0000\u0000\u0000KM\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000"+
		"MW\u0005\u001f\u0000\u0000NO\u0005\u0014\u0000\u0000OS\u0005\u001e\u0000"+
		"\u0000PR\u0003\u0002\u0001\u0000QP\u0001\u0000\u0000\u0000RU\u0001\u0000"+
		"\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TV\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VX\u0005\u001f\u0000\u0000"+
		"WN\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000X\t\u0001\u0000\u0000"+
		"\u0000YZ\u0005\u0016\u0000\u0000Z[\u0005\u0017\u0000\u0000[\\\u0005 \u0000"+
		"\u0000\\]\u0003\u0016\u000b\u0000]^\u0005!\u0000\u0000^b\u0005\u001e\u0000"+
		"\u0000_a\u0003\u0002\u0001\u0000`_\u0001\u0000\u0000\u0000ad\u0001\u0000"+
		"\u0000\u0000b`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000ce\u0001"+
		"\u0000\u0000\u0000db\u0001\u0000\u0000\u0000ef\u0005\u001f\u0000\u0000"+
		"f\u000b\u0001\u0000\u0000\u0000gu\u0003\u000e\u0007\u0000hi\u0005\u000b"+
		"\u0000\u0000ij\u0005 \u0000\u0000jk\u0005\u0003\u0000\u0000kl\u0004\u0006"+
		"\u0004\u0001lo\u0005)\u0000\u0000mp\u0003\u0010\b\u0000np\u0003\u0012"+
		"\t\u0000om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000\u0000pq\u0001\u0000"+
		"\u0000\u0000qr\u0005!\u0000\u0000rs\u0005\u001b\u0000\u0000su\u0001\u0000"+
		"\u0000\u0000tg\u0001\u0000\u0000\u0000th\u0001\u0000\u0000\u0000u\r\u0001"+
		"\u0000\u0000\u0000vw\u0005(\u0000\u0000wx\u0005 \u0000\u0000xy\u0005\u0004"+
		"\u0000\u0000yz\u0005)\u0000\u0000z{\u0005\u0011\u0000\u0000{|\u0005!\u0000"+
		"\u0000|}\u0005\u001b\u0000\u0000}\u000f\u0001\u0000\u0000\u0000~\u007f"+
		"\u0005\u0007\u0000\u0000\u007f\u0080\u0005 \u0000\u0000\u0080\u0081\u0005"+
		"\u0004\u0000\u0000\u0081\u0082\u0005)\u0000\u0000\u0082\u0083\u0005\u000e"+
		"\u0000\u0000\u0083\u0084\u0005!\u0000\u0000\u0084\u0011\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0005\u0006\u0000\u0000\u0086\u0087\u0005 \u0000\u0000"+
		"\u0087\u0088\u0007\u0000\u0000\u0000\u0088\u0089\u0005!\u0000\u0000\u0089"+
		"\u0013\u0001\u0000\u0000\u0000\u008a\u008f\u0005\u001d\u0000\u0000\u008b"+
		"\u008f\u0005\u0004\u0000\u0000\u008c\u008d\u0005\u0003\u0000\u0000\u008d"+
		"\u008f\u0004\n\u0005\u0001\u008e\u008a\u0001\u0000\u0000\u0000\u008e\u008b"+
		"\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0001\u0000\u0000\u0000\u0090\u0098\u0006\n\uffff\uffff\u0000\u0091\u0092"+
		"\u0005 \u0000\u0000\u0092\u0093\u0006\n\uffff\uffff\u0000\u0093\u0094"+
		"\u0003\u0016\u000b\u0000\u0094\u0095\u0005!\u0000\u0000\u0095\u0096\u0006"+
		"\n\uffff\uffff\u0000\u0096\u0098\u0001\u0000\u0000\u0000\u0097\u008e\u0001"+
		"\u0000\u0000\u0000\u0097\u0091\u0001\u0000\u0000\u0000\u0098\u0015\u0001"+
		"\u0000\u0000\u0000\u0099\u009d\u0003\u0014\n\u0000\u009a\u009c\u0003\u0018"+
		"\f\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000"+
		"\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000"+
		"\u0000\u009e\u0017\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000"+
		"\u0000\u00a0\u00a1\u0005\u000f\u0000\u0000\u00a1\u00a2\u0006\f\uffff\uffff"+
		"\u0000\u00a2\u00a3\u0003\u0014\n\u0000\u00a3\u0019\u0001\u0000\u0000\u0000"+
		"\r ).5JSWbot\u008e\u0097\u009d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}