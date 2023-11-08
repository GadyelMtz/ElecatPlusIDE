package Analizadores;
// Generated from SimpleParser.g4 by ANTLR 4.13.1

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
	        
	        if (salida.size() == 1) {
	            Token t = salida.pop();
	            return tipos.contains(t.getType());
	        }
	        Stack<Token> t = new Stack<>();
	        while (!salida.empty()) {
	            if (!(salida.peek().getType() >= OP_LOGICO && salida.peek().getType() <= OP_ARITMETICO)) {
	                t.push(salida.pop());
	            } else {
	                try {
	                    t.push(validarOperacion(t.pop(), t.pop(), salida.pop()));
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
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
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
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				sentencia();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0) );
			setState(40);
			match(BRACKET_CERRAR);
			setState(41);
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
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentencia);
		try {
			setState(48);
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
				setState(43);
				declaracion();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				asignacion();
				}
				break;
			case SI:
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				condicionalif();
				}
				break;
			case REPETIR:
				enterOuterAlt(_localctx, 4);
				{
				setState(46);
				cicloWhile();
				}
				break;
			case ACCION:
			case ESPERAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(47);
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
	}

	public final DeclaracionContext declaracion() throws RecognitionException {
		DeclaracionContext _localctx = new DeclaracionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 isDeclaration = true; 
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TD_DECIMAL:
			case TD_ENTERO:
			case TD_CARACTER:
			case TD_CADENA:
			case TD_BOOLEANO:
				{
				setState(51);
				tipo_dato();
				}
				break;
			case COMPONENTE:
				{
				setState(52);
				((DeclaracionContext)_localctx).t = match(COMPONENTE);
				t = ((DeclaracionContext)_localctx).t;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				{
				setState(56);
				((DeclaracionContext)_localctx).ID = match(ID);
				setState(57);
				if (!( !variableDeclarada(((DeclaracionContext)_localctx).ID,t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,t) ");
				setState(58);
				match(FIN_LINEA);
				}
				}
				break;
			case 2:
				{
				setState(59);
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
			setState(64);
			((AsignacionContext)_localctx).ID = match(ID);
			setState(65);
			if (!( !variableDeclarada(((AsignacionContext)_localctx).ID,t) )) throw new FailedPredicateException(this, " !variableDeclarada($ID,t) ");
			setState(66);
			if (!( usarVariable(((AsignacionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
			setState(67);
			match(SIGNO_IGUAL);
			setState(68);
			expresion();
			setState(69);
			if (!(resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)).getType())))
					)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get($ID.text).getType())))\r\n\t\t");
			setState(70);
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
			setState(72);
			match(SI);
			setState(73);
			match(PAR_ABRIR);
			setState(74);
			expresion();
			setState(75);
			if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO))))) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))");
			setState(76);
			match(PAR_CERRAR);
			setState(77);
			match(BRACKET_ABRIR);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0)) {
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
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SI_NO) {
				{
				setState(85);
				match(SI_NO);
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SI) {
					{
					setState(86);
					match(SI);
					setState(87);
					match(PAR_ABRIR);
					setState(88);
					expresion();
					setState(89);
					if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
									)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))\r\n\t\t\t\t");
					setState(90);
					match(PAR_CERRAR);
					}
				}

				setState(94);
				match(BRACKET_ABRIR);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0)) {
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
			setState(104);
			match(REPETIR);
			setState(105);
			match(MIENTRAS);
			setState(106);
			match(PAR_ABRIR);
			setState(107);
			expresion();
			setState(108);
			if (!(resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
					)) throw new FailedPredicateException(this, "resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))\r\n\t\t");
			setState(109);
			match(PAR_CERRAR);
			setState(110);
			match(BRACKET_ABRIR);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17311206408L) != 0)) {
				{
				{
				setState(111);
				sentencia();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
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
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESPERAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				esperar();
				}
				break;
			case ACCION:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				match(ACCION);
				setState(121);
				match(PAR_ABRIR);
				setState(122);
				((AccionContext)_localctx).ID = match(ID);
				setState(123);
				if (!( usarVariable(((AccionContext)_localctx).ID) )) throw new FailedPredicateException(this, " usarVariable($ID) ");
				setState(124);
				match(COMA);
				setState(127);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIRAR:
					{
					setState(125);
					girar();
					}
					break;
				case ESCRIBIR:
					{
					setState(126);
					escribir();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(129);
				match(PAR_CERRAR);
				setState(130);
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
			setState(134);
			match(ESPERAR);
			setState(135);
			match(PAR_ABRIR);
			setState(136);
			numero();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMA) {
				{
				setState(137);
				match(COMA);
				setState(138);
				match(TIEMPO);
				}
			}

			setState(141);
			match(PAR_CERRAR);
			setState(142);
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
			setState(144);
			match(GIRAR);
			setState(145);
			match(PAR_ABRIR);
			setState(146);
			numero();
			setState(147);
			match(COMA);
			setState(148);
			match(DIRECCION);
			setState(149);
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
			setState(151);
			match(ESCRIBIR);
			setState(152);
			match(PAR_ABRIR);
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(153);
				match(ID);
				}
				break;
			case CADENA:
				{
				setState(154);
				match(CADENA);
				}
				break;
			case DECIMAL:
			case ENTERO:
				{
				setState(155);
				numero();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(158);
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
			setState(175);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOLEANO:
					{
					setState(160);
					((TerminoContext)_localctx).t = match(BOOLEANO);
					}
					break;
				case ID:
					{
					setState(161);
					((TerminoContext)_localctx).t = match(ID);
					setState(162);
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
				setState(166);
				numero();
				añadirAPila(t);
				}
				break;
			case PAR_ABRIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(169);
				((TerminoContext)_localctx).t = match(PAR_ABRIR);
				 añadirAPila(((TerminoContext)_localctx).t); 
				setState(171);
				expresion();
				setState(172);
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
			setState(177);
			termino();
			setState(181);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(178);
					operacionBinaria();
					}
					} 
				}
				setState(183);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
			setState(184);
			op_bin();
			 añadirAPila(t); 
			setState(186);
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
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
				{
				setState(188);
				((NumeroContext)_localctx).t = match(DECIMAL);
				}
				break;
			case ENTERO:
				{
				setState(189);
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
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP_LOGICO:
				{
				setState(194);
				((Op_binContext)_localctx).t = match(OP_LOGICO);
				}
				break;
			case OP_COMPARADOR:
				{
				setState(195);
				((Op_binContext)_localctx).t = match(OP_COMPARADOR);
				}
				break;
			case OP_ARITMETICO:
				{
				setState(196);
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
	}

	public final Tipo_datoContext tipo_dato() throws RecognitionException {
		Tipo_datoContext _localctx = new Tipo_datoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tipo_dato);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TD_DECIMAL:
				{
				setState(201);
				((Tipo_datoContext)_localctx).t = match(TD_DECIMAL);
				}
				break;
			case TD_ENTERO:
				{
				setState(202);
				((Tipo_datoContext)_localctx).t = match(TD_ENTERO);
				}
				break;
			case TD_CARACTER:
				{
				setState(203);
				((Tipo_datoContext)_localctx).t = match(TD_CARACTER);
				}
				break;
			case TD_CADENA:
				{
				setState(204);
				((Tipo_datoContext)_localctx).t = match(TD_CADENA);
				}
				break;
			case TD_BOOLEANO:
				{
				setState(205);
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
		"\u0004\u0001%\u00d3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000%\b\u0000"+
		"\u000b\u0000\f\u0000&\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u00011\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00027\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002=\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"P\b\u0004\n\u0004\f\u0004S\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"]\b\u0004\u0001\u0004\u0001\u0004\u0005\u0004a\b\u0004\n\u0004\f\u0004"+
		"d\t\u0004\u0001\u0004\u0003\u0004g\b\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005q\b\u0005\n\u0005\f\u0005t\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u0080\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u0085\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u008c\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u009d\b\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u00a4\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b0\b\n\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u00b4\b\u000b\n\u000b\f\u000b\u00b7\t\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0003\r\u00bf\b\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00c6\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u00cf\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0000"+
		"\u0000\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e\u0000\u0000\u00de\u0000 \u0001\u0000\u0000\u0000"+
		"\u00020\u0001\u0000\u0000\u0000\u00042\u0001\u0000\u0000\u0000\u0006@"+
		"\u0001\u0000\u0000\u0000\bH\u0001\u0000\u0000\u0000\nh\u0001\u0000\u0000"+
		"\u0000\f\u0084\u0001\u0000\u0000\u0000\u000e\u0086\u0001\u0000\u0000\u0000"+
		"\u0010\u0090\u0001\u0000\u0000\u0000\u0012\u0097\u0001\u0000\u0000\u0000"+
		"\u0014\u00af\u0001\u0000\u0000\u0000\u0016\u00b1\u0001\u0000\u0000\u0000"+
		"\u0018\u00b8\u0001\u0000\u0000\u0000\u001a\u00be\u0001\u0000\u0000\u0000"+
		"\u001c\u00c5\u0001\u0000\u0000\u0000\u001e\u00ce\u0001\u0000\u0000\u0000"+
		" !\u0005\t\u0000\u0000!\"\u0005\u0003\u0000\u0000\"$\u0005\u001e\u0000"+
		"\u0000#%\u0003\u0002\u0001\u0000$#\u0001\u0000\u0000\u0000%&\u0001\u0000"+
		"\u0000\u0000&$\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0001"+
		"\u0000\u0000\u0000()\u0005\u001f\u0000\u0000)*\u0005\u0000\u0000\u0001"+
		"*\u0001\u0001\u0000\u0000\u0000+1\u0003\u0004\u0002\u0000,1\u0003\u0006"+
		"\u0003\u0000-1\u0003\b\u0004\u0000.1\u0003\n\u0005\u0000/1\u0003\f\u0006"+
		"\u00000+\u0001\u0000\u0000\u00000,\u0001\u0000\u0000\u00000-\u0001\u0000"+
		"\u0000\u00000.\u0001\u0000\u0000\u00000/\u0001\u0000\u0000\u00001\u0003"+
		"\u0001\u0000\u0000\u000026\u0006\u0002\uffff\uffff\u000037\u0003\u001e"+
		"\u000f\u000045\u0005\u000b\u0000\u000057\u0006\u0002\uffff\uffff\u0000"+
		"63\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u00007<\u0001\u0000\u0000"+
		"\u000089\u0005\u0003\u0000\u00009:\u0004\u0002\u0000\u0001:=\u0005\u001b"+
		"\u0000\u0000;=\u0003\u0006\u0003\u0000<8\u0001\u0000\u0000\u0000<;\u0001"+
		"\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0006\u0002\uffff\uffff"+
		"\u0000?\u0005\u0001\u0000\u0000\u0000@A\u0005\u0003\u0000\u0000AB\u0004"+
		"\u0003\u0001\u0001BC\u0004\u0003\u0002\u0001CD\u0005\u001c\u0000\u0000"+
		"DE\u0003\u0016\u000b\u0000EF\u0004\u0003\u0003\u0001FG\u0005\u001b\u0000"+
		"\u0000G\u0007\u0001\u0000\u0000\u0000HI\u0005\u0012\u0000\u0000IJ\u0005"+
		" \u0000\u0000JK\u0003\u0016\u000b\u0000KL\u0004\u0004\u0004\u0000LM\u0005"+
		"!\u0000\u0000MQ\u0005\u001e\u0000\u0000NP\u0003\u0002\u0001\u0000ON\u0001"+
		"\u0000\u0000\u0000PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000RT\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000"+
		"\u0000Tf\u0005\u001f\u0000\u0000U\\\u0005\u0013\u0000\u0000VW\u0005\u0012"+
		"\u0000\u0000WX\u0005 \u0000\u0000XY\u0003\u0016\u000b\u0000YZ\u0004\u0004"+
		"\u0005\u0000Z[\u0005!\u0000\u0000[]\u0001\u0000\u0000\u0000\\V\u0001\u0000"+
		"\u0000\u0000\\]\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^b\u0005"+
		"\u001e\u0000\u0000_a\u0003\u0002\u0001\u0000`_\u0001\u0000\u0000\u0000"+
		"ad\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000"+
		"\u0000ce\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000eg\u0005\u001f"+
		"\u0000\u0000fU\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000g\t\u0001"+
		"\u0000\u0000\u0000hi\u0005\u0014\u0000\u0000ij\u0005\u0015\u0000\u0000"+
		"jk\u0005 \u0000\u0000kl\u0003\u0016\u000b\u0000lm\u0004\u0005\u0006\u0000"+
		"mn\u0005!\u0000\u0000nr\u0005\u001e\u0000\u0000oq\u0003\u0002\u0001\u0000"+
		"po\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000"+
		"\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000\u0000\u0000tr\u0001\u0000"+
		"\u0000\u0000uv\u0005\u001f\u0000\u0000v\u000b\u0001\u0000\u0000\u0000"+
		"w\u0085\u0003\u000e\u0007\u0000xy\u0005\n\u0000\u0000yz\u0005 \u0000\u0000"+
		"z{\u0005\u0003\u0000\u0000{|\u0004\u0006\u0007\u0001|\u007f\u0005#\u0000"+
		"\u0000}\u0080\u0003\u0010\b\u0000~\u0080\u0003\u0012\t\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000"+
		"\u0000\u0000\u0081\u0082\u0005!\u0000\u0000\u0082\u0083\u0005\u001b\u0000"+
		"\u0000\u0083\u0085\u0001\u0000\u0000\u0000\u0084w\u0001\u0000\u0000\u0000"+
		"\u0084x\u0001\u0000\u0000\u0000\u0085\r\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005\"\u0000\u0000\u0087\u0088\u0005 \u0000\u0000\u0088\u008b"+
		"\u0003\u001a\r\u0000\u0089\u008a\u0005#\u0000\u0000\u008a\u008c\u0005"+
		"\u0011\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008c\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008e\u0005"+
		"!\u0000\u0000\u008e\u008f\u0005\u001b\u0000\u0000\u008f\u000f\u0001\u0000"+
		"\u0000\u0000\u0090\u0091\u0005\b\u0000\u0000\u0091\u0092\u0005 \u0000"+
		"\u0000\u0092\u0093\u0003\u001a\r\u0000\u0093\u0094\u0005#\u0000\u0000"+
		"\u0094\u0095\u0005\f\u0000\u0000\u0095\u0096\u0005!\u0000\u0000\u0096"+
		"\u0011\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u0007\u0000\u0000\u0098"+
		"\u009c\u0005 \u0000\u0000\u0099\u009d\u0005\u0003\u0000\u0000\u009a\u009d"+
		"\u0005\u0006\u0000\u0000\u009b\u009d\u0003\u001a\r\u0000\u009c\u0099\u0001"+
		"\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009b\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005"+
		"!\u0000\u0000\u009f\u0013\u0001\u0000\u0000\u0000\u00a0\u00a4\u0005\u001d"+
		"\u0000\u0000\u00a1\u00a2\u0005\u0003\u0000\u0000\u00a2\u00a4\u0004\n\b"+
		"\u0001\u00a3\u00a0\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00b0\u0006\n\uffff\uffff"+
		"\u0000\u00a6\u00a7\u0003\u001a\r\u0000\u00a7\u00a8\u0006\n\uffff\uffff"+
		"\u0000\u00a8\u00b0\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005 \u0000\u0000"+
		"\u00aa\u00ab\u0006\n\uffff\uffff\u0000\u00ab\u00ac\u0003\u0016\u000b\u0000"+
		"\u00ac\u00ad\u0005!\u0000\u0000\u00ad\u00ae\u0006\n\uffff\uffff\u0000"+
		"\u00ae\u00b0\u0001\u0000\u0000\u0000\u00af\u00a3\u0001\u0000\u0000\u0000"+
		"\u00af\u00a6\u0001\u0000\u0000\u0000\u00af\u00a9\u0001\u0000\u0000\u0000"+
		"\u00b0\u0015\u0001\u0000\u0000\u0000\u00b1\u00b5\u0003\u0014\n\u0000\u00b2"+
		"\u00b4\u0003\u0018\f\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b6\u0017\u0001\u0000\u0000\u0000\u00b7\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0003\u001c\u000e\u0000\u00b9\u00ba"+
		"\u0006\f\uffff\uffff\u0000\u00ba\u00bb\u0003\u0014\n\u0000\u00bb\u0019"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bf\u0005\u0004\u0000\u0000\u00bd\u00bf"+
		"\u0005\u0005\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0006\r\uffff\uffff\u0000\u00c1\u001b\u0001\u0000\u0000\u0000\u00c2\u00c6"+
		"\u0005\r\u0000\u0000\u00c3\u00c6\u0005\u000e\u0000\u0000\u00c4\u00c6\u0005"+
		"\u000f\u0000\u0000\u00c5\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c8\u0006\u000e\uffff\uffff\u0000\u00c8\u001d"+
		"\u0001\u0000\u0000\u0000\u00c9\u00cf\u0005\u0016\u0000\u0000\u00ca\u00cf"+
		"\u0005\u0017\u0000\u0000\u00cb\u00cf\u0005\u0018\u0000\u0000\u00cc\u00cf"+
		"\u0005\u0019\u0000\u0000\u00cd\u00cf\u0005\u001a\u0000\u0000\u00ce\u00c9"+
		"\u0001\u0000\u0000\u0000\u00ce\u00ca\u0001\u0000\u0000\u0000\u00ce\u00cb"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00ce\u00cd"+
		"\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d1"+
		"\u0006\u000f\uffff\uffff\u0000\u00d1\u001f\u0001\u0000\u0000\u0000\u0013"+
		"&06<Q\\bfr\u007f\u0084\u008b\u009c\u00a3\u00af\u00b5\u00be\u00c5\u00ce";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}