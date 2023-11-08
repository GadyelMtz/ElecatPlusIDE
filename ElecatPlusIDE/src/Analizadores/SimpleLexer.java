package Analizadores;

// Generated from SimpleLexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape" })
public class SimpleLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION);
	}

	/* TODO: */
	@Override
	public void notifyListeners(LexerNoViableAltException e) {
		String text = this._input.getText(Interval.of(this._tokenStartCharIndex, this._input.index()));
		String msg = "No se reconoce el token: '" + this.getErrorDisplay(text) + "'";
		ANTLRErrorListener listener = this.getErrorListenerDispatch();
		listener.syntaxError(this, (Object) null, this._tokenStartLine, this._tokenStartCharPositionInLine, msg, e);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int COMENTARIO_LINEA = 1, COMENTARIO_BLOQUE = 2, ID = 3, DECIMAL = 4, ENTERO = 5, CADENA = 6,
			ESCRIBIR = 7, GIRAR = 8, PROGRAMA = 9, ACCION = 10, COMPONENTE = 11, DIRECCION = 12,
			OP_LOGICO = 13, OP_COMPARADOR = 14, OP_ARITMETICO = 15, NOT = 16, TIEMPO = 17, SI = 18,
			SI_NO = 19, REPETIR = 20, MIENTRAS = 21, TD_DECIMAL = 22, TD_ENTERO = 23, TD_CARACTER = 24,
			TD_CADENA = 25, TD_BOOLEANO = 26, FIN_LINEA = 27, SIGNO_IGUAL = 28, BOOLEANO = 29,
			BRACKET_ABRIR = 30, BRACKET_CERRAR = 31, PAR_ABRIR = 32, PAR_CERRAR = 33, ESPERAR = 34,
			COMA = 35, WS = 36, IDENTIFIER = 37;
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
			"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
				"COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "ID", "DECIMAL", "ENTERO", "CADENA",
				"ESCRIBIR", "GIRAR", "PROGRAMA", "ACCION", "COMPONENTE", "DIRECCION",
				"OP_LOGICO", "OP_COMPARADOR", "OP_ARITMETICO", "NOT", "TIEMPO", "SI",
				"SI_NO", "REPETIR", "MIENTRAS", "TD_DECIMAL", "TD_ENTERO", "TD_CARACTER",
				"TD_CADENA", "TD_BOOLEANO", "FIN_LINEA", "SIGNO_IGUAL", "BOOLEANO", "BRACKET_ABRIR",
				"BRACKET_CERRAR", "PAR_ABRIR", "PAR_CERRAR", "ESPERAR", "COMA", "WS",
				"IDENTIFIER", "LetterOrDigit", "Letter"
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

	public SimpleLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "SimpleLexer.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
			case 36:
				IDENTIFIER_action((RuleContext) _localctx, actionIndex);
				break;
		}
	}

	private void IDENTIFIER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 0:
				System.err.println("No se reconoce el identificador ");
				break;
		}
	}

	public static final String _serializedATN = "\u0004\u0000%\u01ef\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"
			+
			"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004" +
			"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007" +
			"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b" +
			"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002" +
			"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002" +
			"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002" +
			"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002" +
			"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002" +
			"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002" +
			"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007" +
			"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007" +
			"&\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000T\b\u0000" +
			"\n\u0000\f\u0000W\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001" +
			"\u0001\u0001\u0001\u0001\u0005\u0001_\b\u0001\n\u0001\f\u0001b\t\u0001" +
			"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002" +
			"\u0001\u0002\u0001\u0002\u0005\u0002l\b\u0002\n\u0002\f\u0002o\t\u0002" +
			"\u0001\u0002\u0001\u0002\u0004\u0002s\b\u0002\u000b\u0002\f\u0002t\u0003" +
			"\u0002w\b\u0002\u0003\u0002y\b\u0002\u0001\u0002\u0005\u0002|\b\u0002" +
			"\n\u0002\f\u0002\u007f\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0004" +
			"\u0003\u0084\b\u0003\u000b\u0003\f\u0003\u0085\u0001\u0003\u0001\u0003" +
			"\u0004\u0003\u008a\b\u0003\u000b\u0003\f\u0003\u008b\u0001\u0003\u0001" +
			"\u0003\u0004\u0003\u0090\b\u0003\u000b\u0003\f\u0003\u0091\u0003\u0003" +
			"\u0094\b\u0003\u0001\u0004\u0004\u0004\u0097\b\u0004\u000b\u0004\f\u0004" +
			"\u0098\u0001\u0005\u0001\u0005\u0005\u0005\u009d\b\u0005\n\u0005\f\u0005" +
			"\u00a0\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006" +
			"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006" +
			"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001" +
			"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001" +
			"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
			"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0124\b\n\u0001\u000b\u0001" +
			"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001" +
			"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001" +
			"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0138" +
			"\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u013f\b\f\u0001" +
			"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001" +
			"\r\u0003\r\u014b\b\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001" +
			"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001" +
			"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001" +
			"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001" +
			"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001" +
			"\u0010\u0001\u0010\u0003\u0010\u016b\b\u0010\u0001\u0011\u0001\u0011\u0001" +
			"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001" +
			"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001" +
			"\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001" +
			"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001" +
			"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001" +
			"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001" +
			"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
			"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
			"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001" +
			"\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001" +
			"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001" +
			"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001" +
			"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001" +
			"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u01c2" +
			"\b\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001" +
			"\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!" +
			"\u0001!\u0001\"\u0001\"\u0001#\u0004#\u01d7\b#\u000b#\f#\u01d8\u0001#" +
			"\u0001#\u0001$\u0001$\u0005$\u01df\b$\n$\f$\u01e2\t$\u0001$\u0001$\u0001" +
			"%\u0001%\u0003%\u01e8\b%\u0001&\u0001&\u0001&\u0001&\u0003&\u01ee\b&\u0002" +
			"`\u009e\u0000\'\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005" +
			"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019" +
			"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015" +
			"+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f" +
			"? A!C\"E#G$I%K\u0000M\u0000\u0001\u0000\u000b\u0002\u0000\n\n\r\r\u0002" +
			"\u0000AZaz\u0003\u000009AZaz\u0001\u000009\u0003\u0000*+--//\u0002\u0000" +
			"ffvv\u0003\u0000\t\n\r\r  \u0004\u0000$$AZ__az\u0002\u0000\u0000\u007f" +
			"\u8000\ud800\u8000\udbff\u0001\u0000\u8000\ud800\u8000\udbff\u0001\u0000" +
			"\u8000\udc00\u8000\udfff\u0213\u0000\u0001\u0001\u0000\u0000\u0000\u0000" +
			"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000" +
			"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b" +
			"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001" +
			"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001" +
			"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001" +
			"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001" +
			"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001" +
			"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000" +
			"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000" +
			"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-" +
			"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000" +
			"\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000" +
			"\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;" +
			"\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000" +
			"\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000" +
			"\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I" +
			"\u0001\u0000\u0000\u0000\u0001O\u0001\u0000\u0000\u0000\u0003Z\u0001\u0000" +
			"\u0000\u0000\u0005h\u0001\u0000\u0000\u0000\u0007\u0093\u0001\u0000\u0000" +
			"\u0000\t\u0096\u0001\u0000\u0000\u0000\u000b\u009a\u0001\u0000\u0000\u0000" +
			"\r\u00a3\u0001\u0000\u0000\u0000\u000f\u00ac\u0001\u0000\u0000\u0000\u0011" +
			"\u00b1\u0001\u0000\u0000\u0000\u0013\u00ba\u0001\u0000\u0000\u0000\u0015" +
			"\u0123\u0001\u0000\u0000\u0000\u0017\u0137\u0001\u0000\u0000\u0000\u0019" +
			"\u013e\u0001\u0000\u0000\u0000\u001b\u014a\u0001\u0000\u0000\u0000\u001d" +
			"\u014c\u0001\u0000\u0000\u0000\u001f\u014e\u0001\u0000\u0000\u0000!\u016a" +
			"\u0001\u0000\u0000\u0000#\u016c\u0001\u0000\u0000\u0000%\u016f\u0001\u0000" +
			"\u0000\u0000\'\u0175\u0001\u0000\u0000\u0000)\u017d\u0001\u0000\u0000" +
			"\u0000+\u0186\u0001\u0000\u0000\u0000-\u018e\u0001\u0000\u0000\u0000/" +
			"\u0195\u0001\u0000\u0000\u00001\u019e\u0001\u0000\u0000\u00003\u01a5\u0001" +
			"\u0000\u0000\u00005\u01ae\u0001\u0000\u0000\u00007\u01b0\u0001\u0000\u0000" +
			"\u00009\u01c1\u0001\u0000\u0000\u0000;\u01c3\u0001\u0000\u0000\u0000=" +
			"\u01c5\u0001\u0000\u0000\u0000?\u01c7\u0001\u0000\u0000\u0000A\u01c9\u0001" +
			"\u0000\u0000\u0000C\u01cb\u0001\u0000\u0000\u0000E\u01d3\u0001\u0000\u0000" +
			"\u0000G\u01d6\u0001\u0000\u0000\u0000I\u01dc\u0001\u0000\u0000\u0000K" +
			"\u01e7\u0001\u0000\u0000\u0000M\u01ed\u0001\u0000\u0000\u0000OP\u0005" +
			"/\u0000\u0000PQ\u0005/\u0000\u0000QU\u0001\u0000\u0000\u0000RT\b\u0000" +
			"\u0000\u0000SR\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001" +
			"\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VX\u0001\u0000\u0000\u0000" +
			"WU\u0001\u0000\u0000\u0000XY\u0006\u0000\u0000\u0000Y\u0002\u0001\u0000" +
			"\u0000\u0000Z[\u0005/\u0000\u0000[\\\u0005*\u0000\u0000\\`\u0001\u0000" +
			"\u0000\u0000]_\t\u0000\u0000\u0000^]\u0001\u0000\u0000\u0000_b\u0001\u0000" +
			"\u0000\u0000`a\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000ac\u0001" +
			"\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000cd\u0005*\u0000\u0000de\u0005" +
			"/\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0006\u0001\u0000\u0000g\u0004" +
			"\u0001\u0000\u0000\u0000hi\u0005#\u0000\u0000ix\u0007\u0001\u0000\u0000" +
			"jl\u0007\u0002\u0000\u0000kj\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000" +
			"\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000nv\u0001\u0000" +
			"\u0000\u0000om\u0001\u0000\u0000\u0000pr\u0005_\u0000\u0000qs\u0007\u0002" +
			"\u0000\u0000rq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tr\u0001" +
			"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uw\u0001\u0000\u0000\u0000" +
			"vp\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wy\u0001\u0000\u0000" +
			"\u0000xm\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y}\u0001\u0000" +
			"\u0000\u0000z|\u0007\u0002\u0000\u0000{z\u0001\u0000\u0000\u0000|\u007f" +
			"\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000" +
			"\u0000~\u0080\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u0080" +
			"\u0081\u0005#\u0000\u0000\u0081\u0006\u0001\u0000\u0000\u0000\u0082\u0084" +
			"\u0007\u0003\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0085" +
			"\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086" +
			"\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0089" +
			"\u0005.\u0000\u0000\u0088\u008a\u0007\u0003\u0000\u0000\u0089\u0088\u0001" +
			"\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u0089\u0001" +
			"\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u0094\u0001" +
			"\u0000\u0000\u0000\u008d\u008f\u0005.\u0000\u0000\u008e\u0090\u0007\u0003" +
			"\u0000\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000" +
			"\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000" +
			"\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093\u0083\u0001\u0000" +
			"\u0000\u0000\u0093\u008d\u0001\u0000\u0000\u0000\u0094\b\u0001\u0000\u0000" +
			"\u0000\u0095\u0097\u0007\u0003\u0000\u0000\u0096\u0095\u0001\u0000\u0000" +
			"\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000" +
			"\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\n\u0001\u0000\u0000\u0000" +
			"\u009a\u009e\u0005\"\u0000\u0000\u009b\u009d\t\u0000\u0000\u0000\u009c" +
			"\u009b\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000\u0000\u0000\u009e" +
			"\u009f\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f" +
			"\u00a1\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1" +
			"\u00a2\u0005\"\u0000\u0000\u00a2\f\u0001\u0000\u0000\u0000\u00a3\u00a4" +
			"\u0005e\u0000\u0000\u00a4\u00a5\u0005s\u0000\u0000\u00a5\u00a6\u0005c" +
			"\u0000\u0000\u00a6\u00a7\u0005r\u0000\u0000\u00a7\u00a8\u0005i\u0000\u0000" +
			"\u00a8\u00a9\u0005b\u0000\u0000\u00a9\u00aa\u0005i\u0000\u0000\u00aa\u00ab" +
			"\u0005r\u0000\u0000\u00ab\u000e\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005" +
			"g\u0000\u0000\u00ad\u00ae\u0005i\u0000\u0000\u00ae\u00af\u0005r\u0000" +
			"\u0000\u00af\u00b0\u0005a\u0000\u0000\u00b0\u0010\u0001\u0000\u0000\u0000" +
			"\u00b1\u00b2\u0005p\u0000\u0000\u00b2\u00b3\u0005r\u0000\u0000\u00b3\u00b4" +
			"\u0005o\u0000\u0000\u00b4\u00b5\u0005g\u0000\u0000\u00b5\u00b6\u0005r" +
			"\u0000\u0000\u00b6\u00b7\u0005a\u0000\u0000\u00b7\u00b8\u0005m\u0000\u0000" +
			"\u00b8\u00b9\u0005a\u0000\u0000\u00b9\u0012\u0001\u0000\u0000\u0000\u00ba" +
			"\u00bb\u0005a\u0000\u0000\u00bb\u00bc\u0005c\u0000\u0000\u00bc\u00bd\u0005" +
			"c\u0000\u0000\u00bd\u00be\u0005i\u0000\u0000\u00be\u00bf\u0005o\u0000" +
			"\u0000\u00bf\u00c0\u0005n\u0000\u0000\u00c0\u0014\u0001\u0000\u0000\u0000" +
			"\u00c1\u00c2\u0005d\u0000\u0000\u00c2\u00c3\u0005i\u0000\u0000\u00c3\u00c4" +
			"\u0005s\u0000\u0000\u00c4\u00c5\u0005p\u0000\u0000\u00c5\u00c6\u0005l" +
			"\u0000\u0000\u00c6\u00c7\u0005a\u0000\u0000\u00c7\u00c8\u0005y\u0000\u0000" +
			"\u00c8\u00c9\u0005_\u0000\u0000\u00c9\u00ca\u0005l\u0000\u0000\u00ca\u00cb" +
			"\u0005c\u0000\u0000\u00cb\u0124\u0005d\u0000\u0000\u00cc\u00cd\u0005s" +
			"\u0000\u0000\u00cd\u00ce\u0005e\u0000\u0000\u00ce\u00cf\u0005r\u0000\u0000" +
			"\u00cf\u00d0\u0005v\u0000\u0000\u00d0\u0124\u0005o\u0000\u0000\u00d1\u00d2" +
			"\u0005s\u0000\u0000\u00d2\u00d3\u0005e\u0000\u0000\u00d3\u00d4\u0005n" +
			"\u0000\u0000\u00d4\u00d5\u0005s\u0000\u0000\u00d5\u00d6\u0005o\u0000\u0000" +
			"\u00d6\u00d7\u0005r\u0000\u0000\u00d7\u00d8\u0005_\u0000\u0000\u00d8\u00d9" +
			"\u0005u\u0000\u0000\u00d9\u00da\u0005l\u0000\u0000\u00da\u00db\u0005t" +
			"\u0000\u0000\u00db\u00dc\u0005r\u0000\u0000\u00dc\u00dd\u0005a\u0000\u0000" +
			"\u00dd\u00de\u0005s\u0000\u0000\u00de\u00df\u0005o\u0000\u0000\u00df\u00e0" +
			"\u0005n\u0000\u0000\u00e0\u00e1\u0005i\u0000\u0000\u00e1\u00e2\u0005c" +
			"\u0000\u0000\u00e2\u0124\u0005o\u0000\u0000\u00e3\u00e4\u0005m\u0000\u0000" +
			"\u00e4\u00e5\u0005o\u0000\u0000\u00e5\u00e6\u0005t\u0000\u0000\u00e6\u00e7" +
			"\u0005o\u0000\u0000\u00e7\u0124\u0005r\u0000\u0000\u00e8\u00e9\u0005f" +
			"\u0000\u0000\u00e9\u00ea\u0005o\u0000\u0000\u00ea\u00eb\u0005t\u0000\u0000" +
			"\u00eb\u00ec\u0005o\u0000\u0000\u00ec\u00ed\u0005_\u0000\u0000\u00ed\u00ee" +
			"\u0005r\u0000\u0000\u00ee\u00ef\u0005e\u0000\u0000\u00ef\u00f0\u0005s" +
			"\u0000\u0000\u00f0\u00f1\u0005i\u0000\u0000\u00f1\u00f2\u0005s\u0000\u0000" +
			"\u00f2\u00f3\u0005t\u0000\u0000\u00f3\u00f4\u0005e\u0000\u0000\u00f4\u00f5" +
			"\u0005n\u0000\u0000\u00f5\u00f6\u0005c\u0000\u0000\u00f6\u00f7\u0005i" +
			"\u0000\u0000\u00f7\u0124\u0005a\u0000\u0000\u00f8\u00f9\u0005b\u0000\u0000" +
			"\u00f9\u00fa\u0005u\u0000\u0000\u00fa\u00fb\u0005z\u0000\u0000\u00fb\u00fc" +
			"\u0005z\u0000\u0000\u00fc\u00fd\u0005e\u0000\u0000\u00fd\u0124\u0005r" +
			"\u0000\u0000\u00fe\u00ff\u0005s\u0000\u0000\u00ff\u0100\u0005i\u0000\u0000" +
			"\u0100\u0101\u0005e\u0000\u0000\u0101\u0102\u0005t\u0000\u0000\u0102\u0103" +
			"\u0005e\u0000\u0000\u0103\u0104\u0005_\u0000\u0000\u0104\u0105\u0005s" +
			"\u0000\u0000\u0105\u0106\u0005e\u0000\u0000\u0106\u0107\u0005g\u0000\u0000" +
			"\u0107\u0108\u0005m\u0000\u0000\u0108\u0109\u0005e\u0000\u0000\u0109\u010a" +
			"\u0005n\u0000\u0000\u010a\u010b\u0005t\u0000\u0000\u010b\u010c\u0005o" +
			"\u0000\u0000\u010c\u0124\u0005s\u0000\u0000\u010d\u010e\u0005b\u0000\u0000" +
			"\u010e\u010f\u0005o\u0000\u0000\u010f\u0110\u0005t\u0000\u0000\u0110\u0111" +
			"\u0005o\u0000\u0000\u0111\u0124\u0005n\u0000\u0000\u0112\u0113\u0005f" +
			"\u0000\u0000\u0113\u0114\u0005u\u0000\u0000\u0114\u0115\u0005e\u0000\u0000" +
			"\u0115\u0116\u0005n\u0000\u0000\u0116\u0117\u0005t\u0000\u0000\u0117\u0124" +
			"\u0005e\u0000\u0000\u0118\u0119\u0005r\u0000\u0000\u0119\u011a\u0005e" +
			"\u0000\u0000\u011a\u011b\u0005g\u0000\u0000\u011b\u011c\u0005i\u0000\u0000" +
			"\u011c\u011d\u0005s\u0000\u0000\u011d\u011e\u0005t\u0000\u0000\u011e\u011f" +
			"\u0005r\u0000\u0000\u011f\u0124\u0005o\u0000\u0000\u0120\u0121\u0005p" +
			"\u0000\u0000\u0121\u0122\u0005i\u0000\u0000\u0122\u0124\u0005n\u0000\u0000" +
			"\u0123\u00c1\u0001\u0000\u0000\u0000\u0123\u00cc\u0001\u0000\u0000\u0000" +
			"\u0123\u00d1\u0001\u0000\u0000\u0000\u0123\u00e3\u0001\u0000\u0000\u0000" +
			"\u0123\u00e8\u0001\u0000\u0000\u0000\u0123\u00f8\u0001\u0000\u0000\u0000" +
			"\u0123\u00fe\u0001\u0000\u0000\u0000\u0123\u010d\u0001\u0000\u0000\u0000" +
			"\u0123\u0112\u0001\u0000\u0000\u0000\u0123\u0118\u0001\u0000\u0000\u0000" +
			"\u0123\u0120\u0001\u0000\u0000\u0000\u0124\u0016\u0001\u0000\u0000\u0000" +
			"\u0125\u0126\u0005h\u0000\u0000\u0126\u0127\u0005o\u0000\u0000\u0127\u0128" +
			"\u0005r\u0000\u0000\u0128\u0129\u0005a\u0000\u0000\u0129\u012a\u0005r" +
			"\u0000\u0000\u012a\u012b\u0005i\u0000\u0000\u012b\u0138\u0005o\u0000\u0000" +
			"\u012c\u012d\u0005a\u0000\u0000\u012d\u012e\u0005n\u0000\u0000\u012e\u012f" +
			"\u0005t\u0000\u0000\u012f\u0130\u0005i\u0000\u0000\u0130\u0131\u0005h" +
			"\u0000\u0000\u0131\u0132\u0005o\u0000\u0000\u0132\u0133\u0005r\u0000\u0000" +
			"\u0133\u0134\u0005a\u0000\u0000\u0134\u0135\u0005r\u0000\u0000\u0135\u0136" +
			"\u0005i\u0000\u0000\u0136\u0138\u0005o\u0000\u0000\u0137\u0125\u0001\u0000" +
			"\u0000\u0000\u0137\u012c\u0001\u0000\u0000\u0000\u0138\u0018\u0001\u0000" +
			"\u0000\u0000\u0139\u013a\u0005a\u0000\u0000\u013a\u013b\u0005n\u0000\u0000" +
			"\u013b\u013f\u0005d\u0000\u0000\u013c\u013d\u0005o\u0000\u0000\u013d\u013f" +
			"\u0005r\u0000\u0000\u013e\u0139\u0001\u0000\u0000\u0000\u013e\u013c\u0001" +
			"\u0000\u0000\u0000\u013f\u001a\u0001\u0000\u0000\u0000\u0140\u0141\u0005" +
			"=\u0000\u0000\u0141\u014b\u0005=\u0000\u0000\u0142\u0143\u0005!\u0000" +
			"\u0000\u0143\u014b\u0005=\u0000\u0000\u0144\u014b\u0005<\u0000\u0000\u0145" +
			"\u0146\u0005<\u0000\u0000\u0146\u014b\u0005=\u0000\u0000\u0147\u014b\u0005" +
			">\u0000\u0000\u0148\u0149\u0005>\u0000\u0000\u0149\u014b\u0005=\u0000" +
			"\u0000\u014a\u0140\u0001\u0000\u0000\u0000\u014a\u0142\u0001\u0000\u0000" +
			"\u0000\u014a\u0144\u0001\u0000\u0000\u0000\u014a\u0145\u0001\u0000\u0000" +
			"\u0000\u014a\u0147\u0001\u0000\u0000\u0000\u014a\u0148\u0001\u0000\u0000" +
			"\u0000\u014b\u001c\u0001\u0000\u0000\u0000\u014c\u014d\u0007\u0004\u0000" +
			"\u0000\u014d\u001e\u0001\u0000\u0000\u0000\u014e\u014f\u0005n\u0000\u0000" +
			"\u014f\u0150\u0005o\u0000\u0000\u0150\u0151\u0005t\u0000\u0000\u0151 " +
			"\u0001\u0000\u0000\u0000\u0152\u0153\u0005m\u0000\u0000\u0153\u0154\u0005" +
			"i\u0000\u0000\u0154\u0155\u0005l\u0000\u0000\u0155\u0156\u0005i\u0000" +
			"\u0000\u0156\u0157\u0005s\u0000\u0000\u0157\u0158\u0005e\u0000\u0000\u0158" +
			"\u0159\u0005g\u0000\u0000\u0159\u015a\u0005u\u0000\u0000\u015a\u015b\u0005" +
			"n\u0000\u0000\u015b\u015c\u0005d\u0000\u0000\u015c\u016b\u0005o\u0000" +
			"\u0000\u015d\u015e\u0005s\u0000\u0000\u015e\u015f\u0005e\u0000\u0000\u015f" +
			"\u0160\u0005g\u0000\u0000\u0160\u0161\u0005u\u0000\u0000\u0161\u0162\u0005" +
			"n\u0000\u0000\u0162\u0163\u0005d\u0000\u0000\u0163\u016b\u0005o\u0000" +
			"\u0000\u0164\u0165\u0005m\u0000\u0000\u0165\u0166\u0005i\u0000\u0000\u0166" +
			"\u0167\u0005n\u0000\u0000\u0167\u0168\u0005u\u0000\u0000\u0168\u0169\u0005" +
			"t\u0000\u0000\u0169\u016b\u0005o\u0000\u0000\u016a\u0152\u0001\u0000\u0000" +
			"\u0000\u016a\u015d\u0001\u0000\u0000\u0000\u016a\u0164\u0001\u0000\u0000" +
			"\u0000\u016b\"\u0001\u0000\u0000\u0000\u016c\u016d\u0005s\u0000\u0000" +
			"\u016d\u016e\u0005i\u0000\u0000\u016e$\u0001\u0000\u0000\u0000\u016f\u0170" +
			"\u0005s\u0000\u0000\u0170\u0171\u0005i\u0000\u0000\u0171\u0172\u0005_" +
			"\u0000\u0000\u0172\u0173\u0005n\u0000\u0000\u0173\u0174\u0005o\u0000\u0000" +
			"\u0174&\u0001\u0000\u0000\u0000\u0175\u0176\u0005r\u0000\u0000\u0176\u0177" +
			"\u0005e\u0000\u0000\u0177\u0178\u0005p\u0000\u0000\u0178\u0179\u0005e" +
			"\u0000\u0000\u0179\u017a\u0005t\u0000\u0000\u017a\u017b\u0005i\u0000\u0000" +
			"\u017b\u017c\u0005r\u0000\u0000\u017c(\u0001\u0000\u0000\u0000\u017d\u017e" +
			"\u0005m\u0000\u0000\u017e\u017f\u0005i\u0000\u0000\u017f\u0180\u0005e" +
			"\u0000\u0000\u0180\u0181\u0005n\u0000\u0000\u0181\u0182\u0005t\u0000\u0000" +
			"\u0182\u0183\u0005r\u0000\u0000\u0183\u0184\u0005a\u0000\u0000\u0184\u0185" +
			"\u0005s\u0000\u0000\u0185*\u0001\u0000\u0000\u0000\u0186\u0187\u0005d" +
			"\u0000\u0000\u0187\u0188\u0005e\u0000\u0000\u0188\u0189\u0005c\u0000\u0000" +
			"\u0189\u018a\u0005i\u0000\u0000\u018a\u018b\u0005m\u0000\u0000\u018b\u018c" +
			"\u0005a\u0000\u0000\u018c\u018d\u0005l\u0000\u0000\u018d,\u0001\u0000" +
			"\u0000\u0000\u018e\u018f\u0005e\u0000\u0000\u018f\u0190\u0005n\u0000\u0000" +
			"\u0190\u0191\u0005t\u0000\u0000\u0191\u0192\u0005e\u0000\u0000\u0192\u0193" +
			"\u0005r\u0000\u0000\u0193\u0194\u0005o\u0000\u0000\u0194.\u0001\u0000" +
			"\u0000\u0000\u0195\u0196\u0005c\u0000\u0000\u0196\u0197\u0005a\u0000\u0000" +
			"\u0197\u0198\u0005r\u0000\u0000\u0198\u0199\u0005a\u0000\u0000\u0199\u019a" +
			"\u0005c\u0000\u0000\u019a\u019b\u0005t\u0000\u0000\u019b\u019c\u0005e" +
			"\u0000\u0000\u019c\u019d\u0005r\u0000\u0000\u019d0\u0001\u0000\u0000\u0000" +
			"\u019e\u019f\u0005c\u0000\u0000\u019f\u01a0\u0005a\u0000\u0000\u01a0\u01a1" +
			"\u0005d\u0000\u0000\u01a1\u01a2\u0005e\u0000\u0000\u01a2\u01a3\u0005n" +
			"\u0000\u0000\u01a3\u01a4\u0005a\u0000\u0000\u01a42\u0001\u0000\u0000\u0000" +
			"\u01a5\u01a6\u0005b\u0000\u0000\u01a6\u01a7\u0005o\u0000\u0000\u01a7\u01a8" +
			"\u0005o\u0000\u0000\u01a8\u01a9\u0005l\u0000\u0000\u01a9\u01aa\u0005e" +
			"\u0000\u0000\u01aa\u01ab\u0005a\u0000\u0000\u01ab\u01ac\u0005n\u0000\u0000" +
			"\u01ac\u01ad\u0005o\u0000\u0000\u01ad4\u0001\u0000\u0000\u0000\u01ae\u01af" +
			"\u0005;\u0000\u0000\u01af6\u0001\u0000\u0000\u0000\u01b0\u01b1\u0005=" +
			"\u0000\u0000\u01b18\u0001\u0000\u0000\u0000\u01b2\u01c2\u0007\u0005\u0000" +
			"\u0000\u01b3\u01b4\u0005v\u0000\u0000\u01b4\u01b5\u0005e\u0000\u0000\u01b5" +
			"\u01b6\u0005r\u0000\u0000\u01b6\u01b7\u0005d\u0000\u0000\u01b7\u01b8\u0005" +
			"a\u0000\u0000\u01b8\u01b9\u0005d\u0000\u0000\u01b9\u01ba\u0005e\u0000" +
			"\u0000\u01ba\u01bb\u0005r\u0000\u0000\u01bb\u01c2\u0005o\u0000\u0000\u01bc" +
			"\u01bd\u0005f\u0000\u0000\u01bd\u01be\u0005a\u0000\u0000\u01be\u01bf\u0005" +
			"l\u0000\u0000\u01bf\u01c0\u0005s\u0000\u0000\u01c0\u01c2\u0005o\u0000" +
			"\u0000\u01c1\u01b2\u0001\u0000\u0000\u0000\u01c1\u01b3\u0001\u0000\u0000" +
			"\u0000\u01c1\u01bc\u0001\u0000\u0000\u0000\u01c2:\u0001\u0000\u0000\u0000" +
			"\u01c3\u01c4\u0005{\u0000\u0000\u01c4<\u0001\u0000\u0000\u0000\u01c5\u01c6" +
			"\u0005}\u0000\u0000\u01c6>\u0001\u0000\u0000\u0000\u01c7\u01c8\u0005(" +
			"\u0000\u0000\u01c8@\u0001\u0000\u0000\u0000\u01c9\u01ca\u0005)\u0000\u0000" +
			"\u01caB\u0001\u0000\u0000\u0000\u01cb\u01cc\u0005e\u0000\u0000\u01cc\u01cd" +
			"\u0005s\u0000\u0000\u01cd\u01ce\u0005p\u0000\u0000\u01ce\u01cf\u0005e" +
			"\u0000\u0000\u01cf\u01d0\u0005r\u0000\u0000\u01d0\u01d1\u0005a\u0000\u0000" +
			"\u01d1\u01d2\u0005r\u0000\u0000\u01d2D\u0001\u0000\u0000\u0000\u01d3\u01d4" +
			"\u0005,\u0000\u0000\u01d4F\u0001\u0000\u0000\u0000\u01d5\u01d7\u0007\u0006" +
			"\u0000\u0000\u01d6\u01d5\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001\u0000" +
			"\u0000\u0000\u01d8\u01d6\u0001\u0000\u0000\u0000\u01d8\u01d9\u0001\u0000" +
			"\u0000\u0000\u01d9\u01da\u0001\u0000\u0000\u0000\u01da\u01db\u0006#\u0000" +
			"\u0000\u01dbH\u0001\u0000\u0000\u0000\u01dc\u01e0\u0003M&\u0000\u01dd" +
			"\u01df\u0003K%\u0000\u01de\u01dd\u0001\u0000\u0000\u0000\u01df\u01e2\u0001" +
			"\u0000\u0000\u0000\u01e0\u01de\u0001\u0000\u0000\u0000\u01e0\u01e1\u0001" +
			"\u0000\u0000\u0000\u01e1\u01e3\u0001\u0000\u0000\u0000\u01e2\u01e0\u0001" +
			"\u0000\u0000\u0000\u01e3\u01e4\u0006$\u0001\u0000\u01e4J\u0001\u0000\u0000" +
			"\u0000\u01e5\u01e8\u0003M&\u0000\u01e6\u01e8\u0007\u0003\u0000\u0000\u01e7" +
			"\u01e5\u0001\u0000\u0000\u0000\u01e7\u01e6\u0001\u0000\u0000\u0000\u01e8" +
			"L\u0001\u0000\u0000\u0000\u01e9\u01ee\u0007\u0007\u0000\u0000\u01ea\u01ee" +
			"\b\b\u0000\u0000\u01eb\u01ec\u0007\t\u0000\u0000\u01ec\u01ee\u0007\n\u0000" +
			"\u0000\u01ed\u01e9\u0001\u0000\u0000\u0000\u01ed\u01ea\u0001\u0000\u0000" +
			"\u0000\u01ed\u01eb\u0001\u0000\u0000\u0000\u01eeN\u0001\u0000\u0000\u0000" +
			"\u0018\u0000U`mtvx}\u0085\u008b\u0091\u0093\u0098\u009e\u0123\u0137\u013e" +
			"\u014a\u016a\u01c1\u01d8\u01e0\u01e7\u01ed\u0002\u0006\u0000\u0000\u0001" +
			"$\u0000";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}