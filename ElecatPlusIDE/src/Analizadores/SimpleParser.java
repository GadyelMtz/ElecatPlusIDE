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
		COMENTARIO_LINEA=1, COMENTARIO_BLOQUE=2, ID=3, DECIMAL=4, ENTERO=5, CADENA=6, 
		ESCRIBIR=7, GIRAR=8, PROGRAMA=9, ACCION=10, COMPONENTE=11, DIRECCION=12, 
		OP_LOGICO=13, OP_COMPARADOR=14, OP_ARITMETICO=15, NOT=16, TIEMPO=17, SI=18, 
		SI_NO=19, REPETIR=20, MIENTRAS=21, TIPO_DATO=22, TD_DECIMAL=23, TD_ENTERO=24, 
		TD_CARACTER=25, TD_CADENA=26, TD_BOOLEANO=27, FIN_LINEA=28, SIGNO_IGUAL=29, 
		BOOLEANO=30, BRACKET_ABRIR=31, BRACKET_CERRAR=32, PAR_ABRIR=33, PAR_CERRAR=34, 
		ESPERAR=35, COMA=36, WS=37;
	public static final int
		RULE_programa = 0, RULE_sentencia = 1, RULE_declaracion = 2, RULE_asignacion = 3, 
		RULE_condicionalif = 4, RULE_cicloWhile = 5, RULE_accion = 6, RULE_esperar = 7, 
		RULE_girar = 8, RULE_escribir = 9, RULE_termino = 10, RULE_expresion = 11, 
		RULE_operacionBinaria = 12, RULE_numero = 13, RULE_op_bin = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "sentencia", "declaracion", "asignacion", "condicionalif", 
			"cicloWhile", "accion", "esperar", "girar", "escribir", "termino", "expresion", 
			"operacionBinaria", "numero", "op_bin"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'escribir'", "'gira'", "'programa'", 
			"'accion'", null, null, null, null, null, "'not'", null, "'si'", "'si_no'", 
			"'repetir'", "'mientras'", null, "'decimal'", "'entero'", "'caracter'", 
			"'cadena'", "'booleano'", "';'", "'='", null, "'{'", "'}'", "'('", "')'", 
			"'esperar'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "ID", "DECIMAL", "ENTERO", 
			"CADENA", "ESCRIBIR", "GIRAR", "PROGRAMA", "ACCION", "COMPONENTE", "DIRECCION", 
			"OP_LOGICO", "OP_COMPARADOR", "OP_ARITMETICO", "NOT", "TIEMPO", "SI", 
			"SI_NO", "REPETIR", "MIENTRAS", "TIPO_DATO", "TD_DECIMAL", "TD_ENTERO", 
			"TD_CARACTER", "TD_CADENA", "TD_BOOLEANO", "FIN_LINEA", "SIGNO_IGUAL", 
			"BOOLEANO", "BRACKET_ABRIR", "BRACKET_CERRAR", "PAR_ABRIR", "PAR_CERRAR", 
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
	    final Set<Integer> booleanos = new HashSet<Integer>(Arrays.asList(TD_BOOLEANO, BOOLEANO));
	    final Set<Integer> enteros = new HashSet<>(Arrays.asList(TD_ENTERO, ENTERO));
	    final Set<Integer> decimales = new HashSet<>(Arrays.asList(DECIMAL, TD_DECIMAL));
	    final Set<Integer> operadores = new HashSet<>(Arrays.asList(OP_ARITMETICO, OP_COMPARADOR, OP_LOGICO));

	    // #region CódigoSemántico
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
	        // Para leer de izquierda a derecha y no de derecha a izquierda (como llega)
	        if (salida.size() == 1) {
	            Token t = salida.pop();
	            return tipos.contains(t.getType());
	        }
	        Stack<Token> t = new Stack<>();
	        while (!salida.empty()) {
	            if (salida.peek().getType() >= OP_LOGICO && salida.peek().getType() <= OP_ARITMETICO) {
	                t.push(salida.pop());
	            } else {
	                try {
	                    t.push(validarOperacion(t.pop(), t.pop(), salida.pop()));
	                } catch (Exception e) {
	                    return false;
	                }
	            }
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
	        // Sacar el resto de operadores de la pila
	        Stack<Token> invertido = new Stack<>();
	        while (!salida.empty()) {
	            invertido.push(salida.pop());
	        }
	        salida = invertido;
	    }

		boolean operando(int tipo){
			return booleanos.contains(tipo) || enteros.contains(tipo) || decimales.contains(tipo);
		}

	    // Funcion para añadir a la pila semántica
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

	    // Predicado semántico para verificar si una variable se ha declarado al
	    // intentar declararla
	    boolean variableDeclarada(Token variable, Token tipoVariable) {
	        if (!isDeclaration)
	            return false;
	        String nombreVariable = variable.getText();
	        if (variablesDeclaradas.containsKey(nombreVariable))
	            return true;
	        variablesDeclaradas.put(nombreVariable, tipoVariable);
	        return false;
	    }

	    // Predicado semántico para verificar si una variable se ha declarado al
	    // intentar declararla
	    boolean usarVariable(Token variable) {
	        return variablesDeclaradas.containsKey(variable.getText());
	    }
	    // #endregion

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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(PROGRAMA);
			setState(31);
			match(ID);
			setState(32);
			match(BRACKET_ABRIR);
			setState(34); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(33);
					sentencia();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(36); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				sentencia();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 34365246472L) != 0) );
			setState(43);
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
			setState(50);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMPONENTE:
			case TIPO_DATO:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				declaracion();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				asignacion();
				}
				break;
			case SI:
				enterOuterAlt(_localctx, 3);
				{
				setState(47);
				condicionalif();
				}
				break;
			case REPETIR:
				enterOuterAlt(_localctx, 4);
				{
				setState(48);
				cicloWhile();
				}
				break;
			case ACCION:
			case ESPERAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(49);
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
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TIPO_DATO:
				{
				setState(53);
				((DeclaracionContext)_localctx).t = match(TIPO_DATO);
				}
				break;
			case COMPONENTE:
				{
				setState(54);
				((DeclaracionContext)_localctx).t = match(COMPONENTE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			t = ((DeclaracionContext)_localctx).t;
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				{
				setState(58);
				((DeclaracionContext)_localctx).ID = match(ID);
				setState(59);
				if (!( !variableDeclarada(((DeclaracionContext)_localctx).ID,((DeclaracionContext)_localctx).t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,$t) ");
				setState(60);
				match(FIN_LINEA);
				}
				}
				break;
			case 2:
				{
				setState(61);
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
			setState(66);
			((AsignacionContext)_localctx).ID = match(ID);
			setState(67);
			if (!( !variableDeclarada(((AsignacionContext)_localctx).ID,t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,t) ");
			setState(68);
			if (!( usarVariable(((AsignacionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
			setState(69);
			match(SIGNO_IGUAL);
			setState(70);
			expresion();
			setState(71);
			if (!(resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)).getType())))
					)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get($ID.text).getType())))\r\n\t\t");
			setState(72);
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
	}

	public final CondicionalifContext condicionalif() throws RecognitionException {
		CondicionalifContext _localctx = new CondicionalifContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_condicionalif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(SI);
			setState(75);
			match(PAR_ABRIR);
			setState(76);
			expresion();
			setState(77);
			if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO))))) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))");
			setState(78);
			match(PAR_CERRAR);
			setState(79);
			match(BRACKET_ABRIR);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34365246472L) != 0)) {
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
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SI_NO) {
				{
				setState(87);
				match(SI_NO);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SI) {
					{
					setState(88);
					match(SI);
					setState(89);
					match(PAR_ABRIR);
					setState(90);
					expresion();
					setState(91);
					if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
									)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))\r\n\t\t\t\t");
					setState(92);
					match(PAR_CERRAR);
					}
				}

				setState(96);
				match(BRACKET_ABRIR);
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34365246472L) != 0)) {
					{
					{
					setState(97);
					sentencia();
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(103);
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
			setState(106);
			match(REPETIR);
			setState(107);
			match(MIENTRAS);
			setState(108);
			match(PAR_ABRIR);
			setState(109);
			expresion();
			setState(110);
			if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
					)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))\r\n\t\t");
			setState(111);
			match(PAR_CERRAR);
			setState(112);
			match(BRACKET_ABRIR);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34365246472L) != 0)) {
				{
				{
				setState(113);
				sentencia();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
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
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESPERAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				esperar();
				}
				break;
			case ACCION:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				match(ACCION);
				setState(123);
				match(PAR_ABRIR);
				setState(124);
				((AccionContext)_localctx).ID = match(ID);
				setState(125);
				if (!( usarVariable(((AccionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
				setState(126);
				match(COMA);
				setState(129);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIRAR:
					{
					setState(127);
					girar();
					}
					break;
				case ESCRIBIR:
					{
					setState(128);
					escribir();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(131);
				match(PAR_CERRAR);
				setState(132);
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
	}

	public final EsperarContext esperar() throws RecognitionException {
		EsperarContext _localctx = new EsperarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_esperar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(ESPERAR);
			setState(137);
			match(PAR_ABRIR);
			setState(138);
			numero();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMA) {
				{
				setState(139);
				match(COMA);
				setState(140);
				match(TIEMPO);
				}
			}

			setState(143);
			match(PAR_CERRAR);
			setState(144);
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
	}

	public final GirarContext girar() throws RecognitionException {
		GirarContext _localctx = new GirarContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_girar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(GIRAR);
			setState(147);
			match(PAR_ABRIR);
			setState(148);
			numero();
			setState(149);
			match(COMA);
			setState(150);
			match(DIRECCION);
			setState(151);
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
	}

	public final EscribirContext escribir() throws RecognitionException {
		EscribirContext _localctx = new EscribirContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_escribir);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(ESCRIBIR);
			setState(154);
			match(PAR_ABRIR);
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(155);
				match(ID);
				}
				break;
			case CADENA:
				{
				setState(156);
				match(CADENA);
				}
				break;
			case DECIMAL:
			case ENTERO:
				{
				setState(157);
				numero();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(160);
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
	}

	public final TerminoContext termino() throws RecognitionException {
		TerminoContext _localctx = new TerminoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_termino);
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOLEANO:
					{
					setState(162);
					((TerminoContext)_localctx).t = match(BOOLEANO);
					}
					break;
				case ID:
					{
					setState(163);
					((TerminoContext)_localctx).t = match(ID);
					setState(164);
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
				setState(168);
				numero();
				añadirAPila(t);
				}
				break;
			case PAR_ABRIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				((TerminoContext)_localctx).t = match(PAR_ABRIR);
				 añadirAPila(((TerminoContext)_localctx).t); 
				setState(173);
				expresion();
				setState(174);
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
			setState(179);
			termino();
			setState(183);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(180);
					operacionBinaria();
					}
					} 
				}
				setState(185);
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
	}

	public final OperacionBinariaContext operacionBinaria() throws RecognitionException {
		OperacionBinariaContext _localctx = new OperacionBinariaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operacionBinaria);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(186);
			op_bin();
			 añadirAPila(t); 
			setState(188);
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
	}

	public final NumeroContext numero() throws RecognitionException {
		NumeroContext _localctx = new NumeroContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_numero);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
				{
				setState(190);
				((NumeroContext)_localctx).t = match(DECIMAL);
				}
				break;
			case ENTERO:
				{
				setState(191);
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
	}

	public final Op_binContext op_bin() throws RecognitionException {
		Op_binContext _localctx = new Op_binContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_op_bin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP_LOGICO:
				{
				setState(196);
				((Op_binContext)_localctx).t = match(OP_LOGICO);
				}
				break;
			case OP_COMPARADOR:
				{
				setState(197);
				((Op_binContext)_localctx).t = match(OP_COMPARADOR);
				}
				break;
			case OP_ARITMETICO:
				{
				setState(198);
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
		"\u0004\u0001%\u00cc\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0004\u0000#\b\u0000\u000b\u0000\f\u0000$\u0001"+
		"\u0000\u0004\u0000(\b\u0000\u000b\u0000\f\u0000)\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"3\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00028\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002?\b"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004R\b\u0004\n\u0004\f\u0004U\t\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004_\b\u0004\u0001\u0004\u0001\u0004\u0005\u0004c\b\u0004\n\u0004\f"+
		"\u0004f\t\u0004\u0001\u0004\u0003\u0004i\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005s\b\u0005\n\u0005\f\u0005v\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\u0082\b\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u0087\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u008e\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u009f\b\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u00a6\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b2\b\n\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u00b6\b\u000b\n\u000b\f\u000b\u00b9\t\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0003\r\u00c1\b\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00c8\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000\u0000\u00d5"+
		"\u0000\u001e\u0001\u0000\u0000\u0000\u00022\u0001\u0000\u0000\u0000\u0004"+
		"4\u0001\u0000\u0000\u0000\u0006B\u0001\u0000\u0000\u0000\bJ\u0001\u0000"+
		"\u0000\u0000\nj\u0001\u0000\u0000\u0000\f\u0086\u0001\u0000\u0000\u0000"+
		"\u000e\u0088\u0001\u0000\u0000\u0000\u0010\u0092\u0001\u0000\u0000\u0000"+
		"\u0012\u0099\u0001\u0000\u0000\u0000\u0014\u00b1\u0001\u0000\u0000\u0000"+
		"\u0016\u00b3\u0001\u0000\u0000\u0000\u0018\u00ba\u0001\u0000\u0000\u0000"+
		"\u001a\u00c0\u0001\u0000\u0000\u0000\u001c\u00c7\u0001\u0000\u0000\u0000"+
		"\u001e\u001f\u0005\t\u0000\u0000\u001f \u0005\u0003\u0000\u0000 \"\u0005"+
		"\u001f\u0000\u0000!#\u0003\u0002\u0001\u0000\"!\u0001\u0000\u0000\u0000"+
		"#$\u0001\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000"+
		"\u0000%\'\u0001\u0000\u0000\u0000&(\u0003\u0002\u0001\u0000\'&\u0001\u0000"+
		"\u0000\u0000()\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0005 \u0000\u0000,\u0001"+
		"\u0001\u0000\u0000\u0000-3\u0003\u0004\u0002\u0000.3\u0003\u0006\u0003"+
		"\u0000/3\u0003\b\u0004\u000003\u0003\n\u0005\u000013\u0003\f\u0006\u0000"+
		"2-\u0001\u0000\u0000\u00002.\u0001\u0000\u0000\u00002/\u0001\u0000\u0000"+
		"\u000020\u0001\u0000\u0000\u000021\u0001\u0000\u0000\u00003\u0003\u0001"+
		"\u0000\u0000\u000047\u0006\u0002\uffff\uffff\u000058\u0005\u0016\u0000"+
		"\u000068\u0005\u000b\u0000\u000075\u0001\u0000\u0000\u000076\u0001\u0000"+
		"\u0000\u000089\u0001\u0000\u0000\u00009>\u0006\u0002\uffff\uffff\u0000"+
		":;\u0005\u0003\u0000\u0000;<\u0004\u0002\u0000\u0001<?\u0005\u001c\u0000"+
		"\u0000=?\u0003\u0006\u0003\u0000>:\u0001\u0000\u0000\u0000>=\u0001\u0000"+
		"\u0000\u0000?@\u0001\u0000\u0000\u0000@A\u0006\u0002\uffff\uffff\u0000"+
		"A\u0005\u0001\u0000\u0000\u0000BC\u0005\u0003\u0000\u0000CD\u0004\u0003"+
		"\u0001\u0001DE\u0004\u0003\u0002\u0001EF\u0005\u001d\u0000\u0000FG\u0003"+
		"\u0016\u000b\u0000GH\u0004\u0003\u0003\u0001HI\u0005\u001c\u0000\u0000"+
		"I\u0007\u0001\u0000\u0000\u0000JK\u0005\u0012\u0000\u0000KL\u0005!\u0000"+
		"\u0000LM\u0003\u0016\u000b\u0000MN\u0004\u0004\u0004\u0000NO\u0005\"\u0000"+
		"\u0000OS\u0005\u001f\u0000\u0000PR\u0003\u0002\u0001\u0000QP\u0001\u0000"+
		"\u0000\u0000RU\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000TV\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000"+
		"Vh\u0005 \u0000\u0000W^\u0005\u0013\u0000\u0000XY\u0005\u0012\u0000\u0000"+
		"YZ\u0005!\u0000\u0000Z[\u0003\u0016\u000b\u0000[\\\u0004\u0004\u0005\u0000"+
		"\\]\u0005\"\u0000\u0000]_\u0001\u0000\u0000\u0000^X\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`d\u0005\u001f"+
		"\u0000\u0000ac\u0003\u0002\u0001\u0000ba\u0001\u0000\u0000\u0000cf\u0001"+
		"\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000"+
		"eg\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000gi\u0005 \u0000\u0000"+
		"hW\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000i\t\u0001\u0000\u0000"+
		"\u0000jk\u0005\u0014\u0000\u0000kl\u0005\u0015\u0000\u0000lm\u0005!\u0000"+
		"\u0000mn\u0003\u0016\u000b\u0000no\u0004\u0005\u0006\u0000op\u0005\"\u0000"+
		"\u0000pt\u0005\u001f\u0000\u0000qs\u0003\u0002\u0001\u0000rq\u0001\u0000"+
		"\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001"+
		"\u0000\u0000\u0000uw\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000"+
		"wx\u0005 \u0000\u0000x\u000b\u0001\u0000\u0000\u0000y\u0087\u0003\u000e"+
		"\u0007\u0000z{\u0005\n\u0000\u0000{|\u0005!\u0000\u0000|}\u0005\u0003"+
		"\u0000\u0000}~\u0004\u0006\u0007\u0001~\u0081\u0005$\u0000\u0000\u007f"+
		"\u0082\u0003\u0010\b\u0000\u0080\u0082\u0003\u0012\t\u0000\u0081\u007f"+
		"\u0001\u0000\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0001\u0000\u0000\u0000\u0083\u0084\u0005\"\u0000\u0000\u0084\u0085\u0005"+
		"\u001c\u0000\u0000\u0085\u0087\u0001\u0000\u0000\u0000\u0086y\u0001\u0000"+
		"\u0000\u0000\u0086z\u0001\u0000\u0000\u0000\u0087\r\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0005#\u0000\u0000\u0089\u008a\u0005!\u0000\u0000\u008a"+
		"\u008d\u0003\u001a\r\u0000\u008b\u008c\u0005$\u0000\u0000\u008c\u008e"+
		"\u0005\u0011\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0005\"\u0000\u0000\u0090\u0091\u0005\u001c\u0000\u0000\u0091\u000f\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0005\b\u0000\u0000\u0093\u0094\u0005!"+
		"\u0000\u0000\u0094\u0095\u0003\u001a\r\u0000\u0095\u0096\u0005$\u0000"+
		"\u0000\u0096\u0097\u0005\f\u0000\u0000\u0097\u0098\u0005\"\u0000\u0000"+
		"\u0098\u0011\u0001\u0000\u0000\u0000\u0099\u009a\u0005\u0007\u0000\u0000"+
		"\u009a\u009e\u0005!\u0000\u0000\u009b\u009f\u0005\u0003\u0000\u0000\u009c"+
		"\u009f\u0005\u0006\u0000\u0000\u009d\u009f\u0003\u001a\r\u0000\u009e\u009b"+
		"\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009d"+
		"\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0005\"\u0000\u0000\u00a1\u0013\u0001\u0000\u0000\u0000\u00a2\u00a6\u0005"+
		"\u001e\u0000\u0000\u00a3\u00a4\u0005\u0003\u0000\u0000\u00a4\u00a6\u0004"+
		"\n\b\u0001\u00a5\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00b2\u0006\n\uffff"+
		"\uffff\u0000\u00a8\u00a9\u0003\u001a\r\u0000\u00a9\u00aa\u0006\n\uffff"+
		"\uffff\u0000\u00aa\u00b2\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005!\u0000"+
		"\u0000\u00ac\u00ad\u0006\n\uffff\uffff\u0000\u00ad\u00ae\u0003\u0016\u000b"+
		"\u0000\u00ae\u00af\u0005\"\u0000\u0000\u00af\u00b0\u0006\n\uffff\uffff"+
		"\u0000\u00b0\u00b2\u0001\u0000\u0000\u0000\u00b1\u00a5\u0001\u0000\u0000"+
		"\u0000\u00b1\u00a8\u0001\u0000\u0000\u0000\u00b1\u00ab\u0001\u0000\u0000"+
		"\u0000\u00b2\u0015\u0001\u0000\u0000\u0000\u00b3\u00b7\u0003\u0014\n\u0000"+
		"\u00b4\u00b6\u0003\u0018\f\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b9\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b8\u0017\u0001\u0000\u0000\u0000\u00b9"+
		"\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bb\u0003\u001c\u000e\u0000\u00bb"+
		"\u00bc\u0006\f\uffff\uffff\u0000\u00bc\u00bd\u0003\u0014\n\u0000\u00bd"+
		"\u0019\u0001\u0000\u0000\u0000\u00be\u00c1\u0005\u0004\u0000\u0000\u00bf"+
		"\u00c1\u0005\u0005\u0000\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c0"+
		"\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c3\u0006\r\uffff\uffff\u0000\u00c3\u001b\u0001\u0000\u0000\u0000\u00c4"+
		"\u00c8\u0005\r\u0000\u0000\u00c5\u00c8\u0005\u000e\u0000\u0000\u00c6\u00c8"+
		"\u0005\u000f\u0000\u0000\u00c7\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9"+
		"\u0001\u0000\u0000\u0000\u00c9\u00ca\u0006\u000e\uffff\uffff\u0000\u00ca"+
		"\u001d\u0001\u0000\u0000\u0000\u0013$)27>S^dht\u0081\u0086\u008d\u009e"+
		"\u00a5\u00b1\u00b7\u00c0\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}