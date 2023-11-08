lexer grammar SimpleLexer;

COMENTARIO_LINEA: '//' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;
ID:
	'#' [a-zA-Z] ([a-zA-Z0-9]* ('_' [a-zA-Z0-9]+)?)? [a-zA-Z0-9]* '#';
DECIMAL: ([0-9]+ '.' [0-9]+ | '.' [0-9]+);
ENTERO: [0-9]+;
CADENA: '"' .*? '"';
ESCRIBIR: 'escribir';
GIRAR: 'gira';
PROGRAMA: 'programa';
ACCION: 'accion';
COMPONENTE:
	'display_lcd'
	| 'servo'
	| 'sensor_ultrasonico'
	| 'motor'
	| 'foto_resistencia'
	| 'buzzer'
	| 'siete_segmentos'
	| 'boton'
	| 'fuente'
	| 'registro'
	| 'pin';
DIRECCION: 'horario' | 'antihorario';
OP_LOGICO: 'and' | 'or';
OP_COMPARADOR: '==' | '!=' | '<' | '<=' | '>' | '>=';
OP_ARITMETICO: '+' | '-' | '*' | '/';
NOT: 'not';
TIEMPO: 'milisegundo' | 'segundo' | 'minuto';
SI: 'si';
SI_NO: 'si_no';
REPETIR: 'repetir';
MIENTRAS: 'mientras';
TD_DECIMAL: 'decimal';
TD_ENTERO: 'entero';
TD_CARACTER: 'caracter';
TD_CADENA: 'cadena';
TD_BOOLEANO: 'booleano';
FIN_LINEA: ';';
SIGNO_IGUAL: '=';
BOOLEANO: 'v' | 'f' | 'verdadero' | 'falso';
BRACKET_ABRIR: '{';
BRACKET_CERRAR: '}';
PAR_ABRIR: '(';
PAR_CERRAR: ')';
ESPERAR: 'esperar';
COMA: ',';
WS: [ \t\r\n]+ -> skip;
// ERROR: ~([0-9]|'/'|'#'|'"'|'e'|'g'|'p'|'a'|'d'|'f'|'b'|'s'|'r'|'h'|'o'|'n'|'m'|'c'|'v').+?WS;

IDENTIFIER: Letter LetterOrDigit* { System.err.println("No se reconoce el identificador "); };
fragment LetterOrDigit: Letter | [0-9];

fragment Letter:
	[a-zA-Z$_] // these are the "java letters" below 0x7F
	| ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
	| [\uD800-\uDBFF] [\uDC00-\uDFFF]
		; // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF