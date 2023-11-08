package Analizadores;
// Generated from ./SimpleParser.g4 by ANTLR 4.13.1

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
		COMENTARIO_LINEA=1, COMENTARIO_BLOQUE=2, ID=3, DECIMAL=4, ENTERO=5, CADENA=6, 
		ESCRIBIR=7, GIRAR=8, PROGRAMA=9, ACCION=10, COMPONENTE=11, DIRECCION=12, 
		OP_LOGICO=13, OP_COMPARADOR=14, OP_ARITMETICO=15, NOT=16, TIEMPO=17, SI=18, 
		SI_NO=19, REPETIR=20, MIENTRAS=21, TD_DECIMAL=22, TD_ENTERO=23, TD_CARACTER=24, 
		TD_CADENA=25, TD_BOOLEANO=26, FIN_LINEA=27, SIGNO_IGUAL=28, BOOLEANO=29, 
		BRACKET_ABRIR=30, BRACKET_CERRAR=31, PAR_ABRIR=32, PAR_CERRAR=33, ESPERAR=34, 
		COMA=35, WS=36, IDENTIFIER=37;
	public static final int
		RULE_programa = 0, RULE_sentencia = 1, RULE_declaracion = 2, RULE_asignacion = 3, 
		RULE_condicionalif = 4, RULE_cicloWhile = 5, RULE_accion = 6, RULE_esperar = 7, 
		RULE_girar = 8, RULE_escribir = 9, RULE_termino = 10, RULE_expresion = 11, 
		RULE_operacionBinaria = 12, RULE_numero = 13, RULE_op_bin = 14, RULE_tipo_dato = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "sentencia", "declaracion", "asignacion", "condicionalif", 
			"cicloWhile", "accion", "esperar", "girar", "escribir", "termino", "expresion", 
			"operacionBinaria", "numero", "op_bin", "tipo_dato"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'escribir'", "'gira'", "'programa'", 
			"'accion'", null, null, null, null, null, "'not'", null, "'si'", "'si_no'", 
			"'repetir'", "'mientras'", "'decimal'", "'entero'", "'caracter'", "'cadena'", 
			"'booleano'", "';'", "'='", null, "'{'", "'}'", "'('", "')'", "'esperar'", 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "ID", "DECIMAL", "ENTERO", 
			"CADENA", "ESCRIBIR", "GIRAR", "PROGRAMA", "ACCION", "COMPONENTE", "DIRECCION", 
			"OP_LOGICO", "OP_COMPARADOR", "OP_ARITMETICO", "NOT", "TIEMPO", "SI", 
			"SI_NO", "REPETIR", "MIENTRAS", "TD_DECIMAL", "TD_ENTERO", "TD_CARACTER", 
			"TD_CADENA", "TD_BOOLEANO", "FIN_LINEA", "SIGNO_IGUAL", "BOOLEANO", "BRACKET_ABRIR", 
			"BRACKET_CERRAR", "PAR_ABRIR", "PAR_CERRAR", "ESPERAR", "COMA", "WS", 
			"IDENTIFIER"
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


	    final Map<String, Token> variablesDeclaradas = new HashMap<>();
	    Stack<Token> salida = new Stack<>();
	    Stack<Token> pilaOperadores = new Stack<>();
	    boolean isDeclaration = false;
	    Token t;
	    final Set<Integer> booleanos = new HashSet<Integer>(Arrays.asList(TD_BOOLEANO, BOOLEANO));
	    final Set<Integer> enteros = new HashSet<>(Arrays.asList(TD_ENTERO, ENTERO));
	    final Set<Integer> decimales = new HashSet<>(Arrays.asList(DECIMAL, TD_DECIMAL));
	    final Set<Integer> operadores = new HashSet<>(Arrays.asList(OP_ARITMETICO, OP_COMPARADOR, OP_LOGICO));
	    
	    void imprimirPila(Stack<Token> xdd) {
	        Stack<Token> x = (Stack<Token>) xdd.clone();
	        String xd = "";
	        while (!x.empty()) {
	            xd += x.pop().getText() + " ";
	        }
	        System.out.println(xd);
	    }
	    boolean resolverPila(HashSet<Integer> tipos) {
	        invertirSalida();
	        imprimirPila(salida);
	        
	        if (salida.size() == 1) {
	            Token t = salida.pop();
	            return tipos.contains(t.getType());
	        }
	        Stack<Token> t = new Stack<>();
	        while (!salida.empty()) {
	            if (!(salida.peek().getType() >= OP_LOGICO && salida.peek().getType() <= OP_ARITMETICO)) {
	                t.push(salida.pop());
	                imprimirPila(t);
	            } else {
	                try {
	                    t.push(validarOperacion(t.pop(), t.pop(), salida.pop()));
	                    imprimirPila(t);
	                } catch (Exception e) {
	                    return false;
	                }
	            }
	        }
	        if (tipos.size()==1)
	        switch (t.pop().getType()) {
	            case DECIMAL:
	                return decimales.contains(tipos.toArray()[0]);
	            case ENTERO:
	                return enteros.contains(tipos.toArray()[0]);
	            case BOOLEANO:
	                return booleanos.contains(tipos.toArray()[0]);
	            default:
	                break;
	        }
	        return tipos.contains(t.pop().getType());
	    }
	    private Token validarOperacion(Token operando1, Token operando2, Token operador) throws Exception {
	        try {
	            if (operador.getType() == OP_LOGICO) {
	                if (!booleanos.contains(operando1.getType()) || !booleanos.contains(operando2.getType())) {
	                    throw new Exception();
	                }
	                return new CommonToken(BOOLEANO, "booleano");
	            } else if (operador.getType() == OP_ARITMETICO || operador.getType() == OP_COMPARADOR) {
	                byte d = 0;
	                d += decimales.contains(operando1.getType()) ? 1 : 0;
	                d += decimales.contains(operando2.getType()) ? 2 : 0;
	                switch (d) {
	                    case 0:
	                        if (!enteros.contains(operando1.getType()) && !enteros.contains(operando2.getType()))
	                            throw new Exception();
	                        return new CommonToken(operador.getType() == OP_ARITMETICO ? (operador.getText().equals("/"))? DECIMAL : ENTERO : BOOLEANO, "valor");
	                    case 1:
	                        if (!enteros.contains(operando2.getType()))
	                            throw new Exception();
	                        break;
	                    case 2:
	                        if (!enteros.contains(operando1.getType()))
	                            throw new Exception();
	                        break;
	                    default:
	                        break;
	                }
	                return new CommonToken(operador.getType() == OP_ARITMETICO ? DECIMAL : BOOLEANO, "valor");
	            }
	        } catch (Exception e) {
	            String err = "Operación inválida: '" + operando1.getText() + " " + operador.getText() + " "
	                    + operando2.getText() + "'";
	            err += "\nTipos de dato incompatibles: '" + operando1.getType() + " " + operador.getText() + " "
	                    + operando2.getType() + "'";
	            err += "\nLínea: " + operador.getLine() + ", " + operador.getCharPositionInLine();
	            System.out.println(err);
	            throw e;
	        }
	        return null;
	    }
	    private void invertirSalida() {
	        while (!pilaOperadores.empty()) {
	            salida.push(pilaOperadores.pop());
	        }
	        
	        Stack<Token> invertido = new Stack<>();
	        while (!salida.empty()) {
	            invertido.push(salida.pop());
	        }
	        salida = invertido;
	    }
	    boolean operando(int tipo){
	        return booleanos.contains(tipo) || enteros.contains(tipo) || decimales.contains(tipo);
	    }
	    
	    void añadirAPila(Token t) {
	        if (t == null)
	            return;
	        int type = t.getType();
	        if (type == ID) {
	            salida.push(variablesDeclaradas.get(t.getText()));
	        } else if (operando(type)) {
	            salida.push(t);
	        } else if (operadores.contains(type)) {
	            while (!pilaOperadores.empty()
	                    && precedencia(pilaOperadores.peek().getText()) >= precedencia(t.getText())) {
	                salida.push(pilaOperadores.pop());
	            }
	            pilaOperadores.push(t);
	        } else if (type == PAR_ABRIR) {
	            pilaOperadores.push(t);
	        } else if (type == PAR_CERRAR) {
	            while (!pilaOperadores.empty() && pilaOperadores.peek().getType() != PAR_ABRIR) {
	                salida.push(pilaOperadores.pop());
	            }
	            if (!pilaOperadores.empty() && pilaOperadores.peek().getType() == PAR_ABRIR)
	                pilaOperadores.pop();
	        }
	    }
	    private int precedencia(String text) {
	        switch (text) {
	            case "+":
	            case "-":
	            case "or":
	            case "==":
	            case "!=":
	            case "<":
	            case "<=":
	            case ">":
	            case ">=":
	                return 1;
	            case "*":
	            case "/":
	            case "and":
	                return 2;
	            default:
	                return -1;
	        }
	    }
	    
	    
	    boolean variableDeclarada(Token variable, Token tipoVariable) {
	        if (!isDeclaration)
	            return false;
	        String nombreVariable = variable.getText();
	        if (variablesDeclaradas.containsKey(nombreVariable))
	            return true;
	        variablesDeclaradas.put(nombreVariable, tipoVariable);
	        return false;
	    }
	    
	    
	    boolean usarVariable(Token variable) {
	        return variablesDeclaradas.containsKey(variable.getText());
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
		public TerminalNode EOF() { return getToken(SimpleParser.EOF, 0); }
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
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(PROGRAMA);
			setState(33);
			match(ID);
			setState(34);
			match(BRACKET_ABRIR);
			setState(36); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(35);
					sentencia();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(38); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				sentencia();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0) );
			setState(45);
			match(BRACKET_CERRAR);
			setState(46);
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
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterSentencia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitSentencia(this);
		}
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentencia);
		try {
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMPONENTE:
			case TD_DECIMAL:
			case TD_ENTERO:
			case TD_CARACTER:
			case TD_CADENA:
			case TD_BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				declaracion();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				asignacion();
				}
				break;
			case SI:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				condicionalif();
				}
				break;
			case REPETIR:
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				cicloWhile();
				}
				break;
			case ACCION:
			case ESPERAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
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
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public TerminalNode COMPONENTE() { return getToken(SimpleParser.COMPONENTE, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode FIN_LINEA() { return getToken(SimpleParser.FIN_LINEA, 0); }
		public DeclaracionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterDeclaracion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitDeclaracion(this);
		}
	}

	public final DeclaracionContext declaracion() throws RecognitionException {
		DeclaracionContext _localctx = new DeclaracionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 isDeclaration = true; 
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TD_DECIMAL:
			case TD_ENTERO:
			case TD_CARACTER:
			case TD_CADENA:
			case TD_BOOLEANO:
				{
				setState(56);
				tipo_dato();
				}
				break;
			case COMPONENTE:
				{
				setState(57);
				((DeclaracionContext)_localctx).t = match(COMPONENTE);
				t = ((DeclaracionContext)_localctx).t;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				{
				setState(61);
				((DeclaracionContext)_localctx).ID = match(ID);
				setState(62);
				if (!( !variableDeclarada(((DeclaracionContext)_localctx).ID,t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,t) ");
				setState(63);
				match(FIN_LINEA);
				}
				}
				break;
			case 2:
				{
				setState(64);
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
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitAsignacion(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_asignacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			((AsignacionContext)_localctx).ID = match(ID);
			setState(70);
			if (!( !variableDeclarada(((AsignacionContext)_localctx).ID,t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,t) ");
			setState(71);
			if (!( usarVariable(((AsignacionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
			setState(72);
			match(SIGNO_IGUAL);
			setState(73);
			expresion();
			setState(74);
			if (!(resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)).getType())))
					)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get($ID.text).getType())))\r\n\t\t");
			setState(75);
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
		public List<TerminalNode> SI() { return getTokens(SimpleParser.SI); }
		public TerminalNode SI(int i) {
			return getToken(SimpleParser.SI, i);
		}
		public List<TerminalNode> PAR_ABRIR() { return getTokens(SimpleParser.PAR_ABRIR); }
		public TerminalNode PAR_ABRIR(int i) {
			return getToken(SimpleParser.PAR_ABRIR, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> PAR_CERRAR() { return getTokens(SimpleParser.PAR_CERRAR); }
		public TerminalNode PAR_CERRAR(int i) {
			return getToken(SimpleParser.PAR_CERRAR, i);
		}
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
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterCondicionalif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitCondicionalif(this);
		}
	}

	public final CondicionalifContext condicionalif() throws RecognitionException {
		CondicionalifContext _localctx = new CondicionalifContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_condicionalif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(SI);
			setState(78);
			match(PAR_ABRIR);
			setState(79);
			expresion();
			setState(80);
			if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO))))) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))");
			setState(81);
			match(PAR_CERRAR);
			setState(82);
			match(BRACKET_ABRIR);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0)) {
				{
				{
				setState(83);
				sentencia();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(BRACKET_CERRAR);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SI_NO) {
				{
				setState(90);
				match(SI_NO);
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SI) {
					{
					setState(91);
					match(SI);
					setState(92);
					match(PAR_ABRIR);
					setState(93);
					expresion();
					setState(94);
					if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
									)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))\r\n\t\t\t\t");
					setState(95);
					match(PAR_CERRAR);
					}
				}

				setState(99);
				match(BRACKET_ABRIR);
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0)) {
					{
					{
					setState(100);
					sentencia();
					}
					}
					setState(105);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(106);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterCicloWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitCicloWhile(this);
		}
	}

	public final CicloWhileContext cicloWhile() throws RecognitionException {
		CicloWhileContext _localctx = new CicloWhileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cicloWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(REPETIR);
			setState(110);
			match(MIENTRAS);
			setState(111);
			match(PAR_ABRIR);
			setState(112);
			expresion();
			setState(113);
			if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
					)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))\r\n\t\t");
			setState(114);
			match(PAR_CERRAR);
			setState(115);
			match(BRACKET_ABRIR);
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0)) {
				{
				{
				setState(116);
				sentencia();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterAccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitAccion(this);
		}
	}

	public final AccionContext accion() throws RecognitionException {
		AccionContext _localctx = new AccionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_accion);
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESPERAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				esperar();
				}
				break;
			case ACCION:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				match(ACCION);
				setState(126);
				match(PAR_ABRIR);
				setState(127);
				((AccionContext)_localctx).ID = match(ID);
				setState(128);
				if (!( usarVariable(((AccionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
				setState(129);
				match(COMA);
				setState(132);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIRAR:
					{
					setState(130);
					girar();
					}
					break;
				case ESCRIBIR:
					{
					setState(131);
					escribir();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(134);
				match(PAR_CERRAR);
				setState(135);
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
		public NumeroContext numero() {
			return getRuleContext(NumeroContext.class,0);
		}
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
		public TerminalNode FIN_LINEA() { return getToken(SimpleParser.FIN_LINEA, 0); }
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode TIEMPO() { return getToken(SimpleParser.TIEMPO, 0); }
		public EsperarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_esperar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterEsperar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitEsperar(this);
		}
	}

	public final EsperarContext esperar() throws RecognitionException {
		EsperarContext _localctx = new EsperarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_esperar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(ESPERAR);
			setState(140);
			match(PAR_ABRIR);
			setState(141);
			numero();
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMA) {
				{
				setState(142);
				match(COMA);
				setState(143);
				match(TIEMPO);
				}
			}

			setState(146);
			match(PAR_CERRAR);
			setState(147);
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
		public NumeroContext numero() {
			return getRuleContext(NumeroContext.class,0);
		}
		public TerminalNode COMA() { return getToken(SimpleParser.COMA, 0); }
		public TerminalNode DIRECCION() { return getToken(SimpleParser.DIRECCION, 0); }
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
		public GirarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_girar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterGirar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitGirar(this);
		}
	}

	public final GirarContext girar() throws RecognitionException {
		GirarContext _localctx = new GirarContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_girar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(GIRAR);
			setState(150);
			match(PAR_ABRIR);
			setState(151);
			numero();
			setState(152);
			match(COMA);
			setState(153);
			match(DIRECCION);
			setState(154);
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
		public NumeroContext numero() {
			return getRuleContext(NumeroContext.class,0);
		}
		public EscribirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escribir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterEscribir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitEscribir(this);
		}
	}

	public final EscribirContext escribir() throws RecognitionException {
		EscribirContext _localctx = new EscribirContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_escribir);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(ESCRIBIR);
			setState(157);
			match(PAR_ABRIR);
			setState(161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(158);
				match(ID);
				}
				break;
			case CADENA:
				{
				setState(159);
				match(CADENA);
				}
				break;
			case DECIMAL:
			case ENTERO:
				{
				setState(160);
				numero();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(163);
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
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public NumeroContext numero() {
			return getRuleContext(NumeroContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_ABRIR() { return getToken(SimpleParser.PAR_ABRIR, 0); }
		public TerminalNode PAR_CERRAR() { return getToken(SimpleParser.PAR_CERRAR, 0); }
		public TerminoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterTermino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitTermino(this);
		}
	}

	public final TerminoContext termino() throws RecognitionException {
		TerminoContext _localctx = new TerminoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_termino);
		try {
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOLEANO:
					{
					setState(165);
					((TerminoContext)_localctx).t = match(BOOLEANO);
					}
					break;
				case ID:
					{
					setState(166);
					((TerminoContext)_localctx).t = match(ID);
					setState(167);
					if (!( usarVariable(((TerminoContext)_localctx).t) )) throw new FailedPredicateException(this, " usarVariable($t) ");
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 añadirAPila(((TerminoContext)_localctx).t); 
				}
				break;
			case DECIMAL:
			case ENTERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				numero();
				añadirAPila(t);
				}
				break;
			case PAR_ABRIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(174);
				((TerminoContext)_localctx).t = match(PAR_ABRIR);
				 añadirAPila(((TerminoContext)_localctx).t); 
				setState(176);
				expresion();
				setState(177);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitExpresion(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expresion);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			termino();
			setState(186);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(183);
					operacionBinaria();
					}
					} 
				}
				setState(188);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		public Op_binContext op_bin() {
			return getRuleContext(Op_binContext.class,0);
		}
		public TerminoContext termino() {
			return getRuleContext(TerminoContext.class,0);
		}
		public OperacionBinariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacionBinaria; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterOperacionBinaria(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitOperacionBinaria(this);
		}
	}

	public final OperacionBinariaContext operacionBinaria() throws RecognitionException {
		OperacionBinariaContext _localctx = new OperacionBinariaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operacionBinaria);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(189);
			op_bin();
			 añadirAPila(t); 
			setState(191);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumeroContext extends ParserRuleContext {
		public Token t;
		public TerminalNode DECIMAL() { return getToken(SimpleParser.DECIMAL, 0); }
		public TerminalNode ENTERO() { return getToken(SimpleParser.ENTERO, 0); }
		public NumeroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numero; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterNumero(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitNumero(this);
		}
	}

	public final NumeroContext numero() throws RecognitionException {
		NumeroContext _localctx = new NumeroContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_numero);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
				{
				setState(193);
				((NumeroContext)_localctx).t = match(DECIMAL);
				}
				break;
			case ENTERO:
				{
				setState(194);
				((NumeroContext)_localctx).t = match(ENTERO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			t=((NumeroContext)_localctx).t;
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
	public static class Op_binContext extends ParserRuleContext {
		public Token t;
		public TerminalNode OP_LOGICO() { return getToken(SimpleParser.OP_LOGICO, 0); }
		public TerminalNode OP_COMPARADOR() { return getToken(SimpleParser.OP_COMPARADOR, 0); }
		public TerminalNode OP_ARITMETICO() { return getToken(SimpleParser.OP_ARITMETICO, 0); }
		public Op_binContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_bin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterOp_bin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitOp_bin(this);
		}
	}

	public final Op_binContext op_bin() throws RecognitionException {
		Op_binContext _localctx = new Op_binContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_op_bin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP_LOGICO:
				{
				setState(199);
				((Op_binContext)_localctx).t = match(OP_LOGICO);
				}
				break;
			case OP_COMPARADOR:
				{
				setState(200);
				((Op_binContext)_localctx).t = match(OP_COMPARADOR);
				}
				break;
			case OP_ARITMETICO:
				{
				setState(201);
				((Op_binContext)_localctx).t = match(OP_ARITMETICO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			t=((Op_binContext)_localctx).t;
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
		public TerminalNode TD_CARACTER() { return getToken(SimpleParser.TD_CARACTER, 0); }
		public TerminalNode TD_CADENA() { return getToken(SimpleParser.TD_CADENA, 0); }
		public TerminalNode TD_BOOLEANO() { return getToken(SimpleParser.TD_BOOLEANO, 0); }
		public Tipo_datoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_dato; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).enterTipo_dato(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleParserListener ) ((SimpleParserListener)listener).exitTipo_dato(this);
		}
	}

	public final Tipo_datoContext tipo_dato() throws RecognitionException {
		Tipo_datoContext _localctx = new Tipo_datoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tipo_dato);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TD_DECIMAL:
				{
				setState(206);
				((Tipo_datoContext)_localctx).t = match(TD_DECIMAL);
				}
				break;
			case TD_ENTERO:
				{
				setState(207);
				((Tipo_datoContext)_localctx).t = match(TD_ENTERO);
				}
				break;
			case TD_CARACTER:
				{
				setState(208);
				((Tipo_datoContext)_localctx).t = match(TD_CARACTER);
				}
				break;
			case TD_CADENA:
				{
				setState(209);
				((Tipo_datoContext)_localctx).t = match(TD_CADENA);
				}
				break;
			case TD_BOOLEANO:
				{
				setState(210);
				((Tipo_datoContext)_localctx).t = match(TD_BOOLEANO);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		case 2:
			return declaracion_sempred((DeclaracionContext)_localctx, predIndex);
		case 3:
			return asignacion_sempred((AsignacionContext)_localctx, predIndex);
		case 4:
			return condicionalif_sempred((CondicionalifContext)_localctx, predIndex);
		case 5:
			return cicloWhile_sempred((CicloWhileContext)_localctx, predIndex);
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
			return  !variableDeclarada(((DeclaracionContext)_localctx).ID,t) ;
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
			return resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)).getType())))
				;
		}
		return true;
	}
	private boolean condicionalif_sempred(CondicionalifContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)));
		case 5:
			return resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
						;
		}
		return true;
	}
	private boolean cicloWhile_sempred(CicloWhileContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
				;
		}
		return true;
	}
	private boolean accion_sempred(AccionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return  usarVariable(((AccionContext)_localctx).ID) ;
		}
		return true;
	}
	private boolean termino_sempred(TerminoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return  usarVariable(((TerminoContext)_localctx).t) ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u00d8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000%\b\u0000"+
		"\u000b\u0000\f\u0000&\u0001\u0000\u0004\u0000*\b\u0000\u000b\u0000\f\u0000"+
		"+\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u00016\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002<\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002B\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004U\b\u0004\n\u0004\f\u0004"+
		"X\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004b\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004f\b\u0004\n\u0004\f\u0004i\t\u0004\u0001\u0004"+
		"\u0003\u0004l\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005v\b\u0005"+
		"\n\u0005\f\u0005y\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006\u0085\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u008a\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u0091\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u00a2\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00a9\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b5\b\n\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u00b9\b\u000b\n\u000b\f\u000b\u00bc\t\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0003\r\u00c4\b\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u00cb\b\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u00d4\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0000\u0000\u0010\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e\u0000\u0000\u00e4\u0000 \u0001\u0000\u0000\u0000\u00025\u0001\u0000"+
		"\u0000\u0000\u00047\u0001\u0000\u0000\u0000\u0006E\u0001\u0000\u0000\u0000"+
		"\bM\u0001\u0000\u0000\u0000\nm\u0001\u0000\u0000\u0000\f\u0089\u0001\u0000"+
		"\u0000\u0000\u000e\u008b\u0001\u0000\u0000\u0000\u0010\u0095\u0001\u0000"+
		"\u0000\u0000\u0012\u009c\u0001\u0000\u0000\u0000\u0014\u00b4\u0001\u0000"+
		"\u0000\u0000\u0016\u00b6\u0001\u0000\u0000\u0000\u0018\u00bd\u0001\u0000"+
		"\u0000\u0000\u001a\u00c3\u0001\u0000\u0000\u0000\u001c\u00ca\u0001\u0000"+
		"\u0000\u0000\u001e\u00d3\u0001\u0000\u0000\u0000 !\u0005\t\u0000\u0000"+
		"!\"\u0005\u0003\u0000\u0000\"$\u0005\u001e\u0000\u0000#%\u0003\u0002\u0001"+
		"\u0000$#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&$\u0001\u0000"+
		"\u0000\u0000&\'\u0001\u0000\u0000\u0000\')\u0001\u0000\u0000\u0000(*\u0003"+
		"\u0002\u0001\u0000)(\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000"+
		"+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000"+
		"\u0000-.\u0005\u001f\u0000\u0000./\u0005\u0000\u0000\u0001/\u0001\u0001"+
		"\u0000\u0000\u000006\u0003\u0004\u0002\u000016\u0003\u0006\u0003\u0000"+
		"26\u0003\b\u0004\u000036\u0003\n\u0005\u000046\u0003\f\u0006\u000050\u0001"+
		"\u0000\u0000\u000051\u0001\u0000\u0000\u000052\u0001\u0000\u0000\u0000"+
		"53\u0001\u0000\u0000\u000054\u0001\u0000\u0000\u00006\u0003\u0001\u0000"+
		"\u0000\u00007;\u0006\u0002\uffff\uffff\u00008<\u0003\u001e\u000f\u0000"+
		"9:\u0005\u000b\u0000\u0000:<\u0006\u0002\uffff\uffff\u0000;8\u0001\u0000"+
		"\u0000\u0000;9\u0001\u0000\u0000\u0000<A\u0001\u0000\u0000\u0000=>\u0005"+
		"\u0003\u0000\u0000>?\u0004\u0002\u0000\u0001?B\u0005\u001b\u0000\u0000"+
		"@B\u0003\u0006\u0003\u0000A=\u0001\u0000\u0000\u0000A@\u0001\u0000\u0000"+
		"\u0000BC\u0001\u0000\u0000\u0000CD\u0006\u0002\uffff\uffff\u0000D\u0005"+
		"\u0001\u0000\u0000\u0000EF\u0005\u0003\u0000\u0000FG\u0004\u0003\u0001"+
		"\u0001GH\u0004\u0003\u0002\u0001HI\u0005\u001c\u0000\u0000IJ\u0003\u0016"+
		"\u000b\u0000JK\u0004\u0003\u0003\u0001KL\u0005\u001b\u0000\u0000L\u0007"+
		"\u0001\u0000\u0000\u0000MN\u0005\u0012\u0000\u0000NO\u0005 \u0000\u0000"+
		"OP\u0003\u0016\u000b\u0000PQ\u0004\u0004\u0004\u0000QR\u0005!\u0000\u0000"+
		"RV\u0005\u001e\u0000\u0000SU\u0003\u0002\u0001\u0000TS\u0001\u0000\u0000"+
		"\u0000UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000"+
		"\u0000\u0000WY\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000Yk\u0005"+
		"\u001f\u0000\u0000Za\u0005\u0013\u0000\u0000[\\\u0005\u0012\u0000\u0000"+
		"\\]\u0005 \u0000\u0000]^\u0003\u0016\u000b\u0000^_\u0004\u0004\u0005\u0000"+
		"_`\u0005!\u0000\u0000`b\u0001\u0000\u0000\u0000a[\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cg\u0005\u001e\u0000"+
		"\u0000df\u0003\u0002\u0001\u0000ed\u0001\u0000\u0000\u0000fi\u0001\u0000"+
		"\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hj\u0001"+
		"\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000jl\u0005\u001f\u0000\u0000"+
		"kZ\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000l\t\u0001\u0000\u0000"+
		"\u0000mn\u0005\u0014\u0000\u0000no\u0005\u0015\u0000\u0000op\u0005 \u0000"+
		"\u0000pq\u0003\u0016\u000b\u0000qr\u0004\u0005\u0006\u0000rs\u0005!\u0000"+
		"\u0000sw\u0005\u001e\u0000\u0000tv\u0003\u0002\u0001\u0000ut\u0001\u0000"+
		"\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000"+
		"z{\u0005\u001f\u0000\u0000{\u000b\u0001\u0000\u0000\u0000|\u008a\u0003"+
		"\u000e\u0007\u0000}~\u0005\n\u0000\u0000~\u007f\u0005 \u0000\u0000\u007f"+
		"\u0080\u0005\u0003\u0000\u0000\u0080\u0081\u0004\u0006\u0007\u0001\u0081"+
		"\u0084\u0005#\u0000\u0000\u0082\u0085\u0003\u0010\b\u0000\u0083\u0085"+
		"\u0003\u0012\t\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0083\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0005"+
		"!\u0000\u0000\u0087\u0088\u0005\u001b\u0000\u0000\u0088\u008a\u0001\u0000"+
		"\u0000\u0000\u0089|\u0001\u0000\u0000\u0000\u0089}\u0001\u0000\u0000\u0000"+
		"\u008a\r\u0001\u0000\u0000\u0000\u008b\u008c\u0005\"\u0000\u0000\u008c"+
		"\u008d\u0005 \u0000\u0000\u008d\u0090\u0003\u001a\r\u0000\u008e\u008f"+
		"\u0005#\u0000\u0000\u008f\u0091\u0005\u0011\u0000\u0000\u0090\u008e\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0092\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0005!\u0000\u0000\u0093\u0094\u0005\u001b"+
		"\u0000\u0000\u0094\u000f\u0001\u0000\u0000\u0000\u0095\u0096\u0005\b\u0000"+
		"\u0000\u0096\u0097\u0005 \u0000\u0000\u0097\u0098\u0003\u001a\r\u0000"+
		"\u0098\u0099\u0005#\u0000\u0000\u0099\u009a\u0005\f\u0000\u0000\u009a"+
		"\u009b\u0005!\u0000\u0000\u009b\u0011\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0005\u0007\u0000\u0000\u009d\u00a1\u0005 \u0000\u0000\u009e\u00a2\u0005"+
		"\u0003\u0000\u0000\u009f\u00a2\u0005\u0006\u0000\u0000\u00a0\u00a2\u0003"+
		"\u001a\r\u0000\u00a1\u009e\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a4\u0005!\u0000\u0000\u00a4\u0013\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a9\u0005\u001d\u0000\u0000\u00a6\u00a7\u0005\u0003\u0000"+
		"\u0000\u00a7\u00a9\u0004\n\b\u0001\u00a8\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000"+
		"\u00aa\u00b5\u0006\n\uffff\uffff\u0000\u00ab\u00ac\u0003\u001a\r\u0000"+
		"\u00ac\u00ad\u0006\n\uffff\uffff\u0000\u00ad\u00b5\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\u0005 \u0000\u0000\u00af\u00b0\u0006\n\uffff\uffff\u0000"+
		"\u00b0\u00b1\u0003\u0016\u000b\u0000\u00b1\u00b2\u0005!\u0000\u0000\u00b2"+
		"\u00b3\u0006\n\uffff\uffff\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4"+
		"\u00a8\u0001\u0000\u0000\u0000\u00b4\u00ab\u0001\u0000\u0000\u0000\u00b4"+
		"\u00ae\u0001\u0000\u0000\u0000\u00b5\u0015\u0001\u0000\u0000\u0000\u00b6"+
		"\u00ba\u0003\u0014\n\u0000\u00b7\u00b9\u0003\u0018\f\u0000\u00b8\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b9\u00bc\u0001\u0000\u0000\u0000\u00ba\u00b8"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u0017"+
		"\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd\u00be"+
		"\u0003\u001c\u000e\u0000\u00be\u00bf\u0006\f\uffff\uffff\u0000\u00bf\u00c0"+
		"\u0003\u0014\n\u0000\u00c0\u0019\u0001\u0000\u0000\u0000\u00c1\u00c4\u0005"+
		"\u0004\u0000\u0000\u00c2\u00c4\u0005\u0005\u0000\u0000\u00c3\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c6\u0006\r\uffff\uffff\u0000\u00c6\u001b\u0001"+
		"\u0000\u0000\u0000\u00c7\u00cb\u0005\r\u0000\u0000\u00c8\u00cb\u0005\u000e"+
		"\u0000\u0000\u00c9\u00cb\u0005\u000f\u0000\u0000\u00ca\u00c7\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00c9\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00cd\u0006\u000e"+
		"\uffff\uffff\u0000\u00cd\u001d\u0001\u0000\u0000\u0000\u00ce\u00d4\u0005"+
		"\u0016\u0000\u0000\u00cf\u00d4\u0005\u0017\u0000\u0000\u00d0\u00d4\u0005"+
		"\u0018\u0000\u0000\u00d1\u00d4\u0005\u0019\u0000\u0000\u00d2\u00d4\u0005"+
		"\u001a\u0000\u0000\u00d3\u00ce\u0001\u0000\u0000\u0000\u00d3\u00cf\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d6\u0006\u000f\uffff\uffff\u0000\u00d6\u001f"+
		"\u0001\u0000\u0000\u0000\u0014&+5;AVagkw\u0084\u0089\u0090\u00a1\u00a8"+
		"\u00b4\u00ba\u00c3\u00ca\u00d3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}