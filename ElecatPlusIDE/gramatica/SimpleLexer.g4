lexer grammar SimpleLexer;
PROGRAMA: 'programa';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
SEMI: ';';
COMMA: ',';
ASSIGN: '=';
SI: 'si';
REPETIR: 'repetir';
ESPERAR: 'esperar';
MIENTRAS: 'mientras';
REMOTO: 'remoto';
ACCION: 'accion';
EJECUTAR: 'ejecutar';
FUNCION: 'funcion';
CONTINUAR: 'continuar';
ROMPER: 'romper';
DEVOLVER: 'devolver';
ELEGIR: 'elegir';
PARA: 'para';
SINO: 'sino';
CASO: 'caso';
ESCRIBIR: 'escribir';
PRED: 'predeterminado';
SONAR: 'sonar';
GIRAR: 'girar';
AVANZAR: 'avanzar';
DETECTAR: 'detectar';
ENCENDER: 'encender';
DETENER: 'detener';
APAGAR: 'apagar';
CADENA: '"' .*? '"';
COMPONENTE:
	'display_lcd'
	| 'servo'
	| 'sensor_ultrasonico'
	| 'motor'
	| 'led'
	| 'buzzer'
	| 'siete_segmentos'
	| 'boton'
	| 'fuente'
	| 'registro'
	| 'pin';
AND: 'and';
OR: 'or';
IGUAL: '==';
DIFERENTE: '!=';
MENOR_QUE: '<';
MENOR_IGUAL: '<=';
MAYOR_QUE: '>';
MAYOR_IGUAL: '>=';
DIVIDIR: '/';
MULTIPLICAR: '*';
RESTAR: '-';
SUMAR: '+';
TD_DECIMAL: 'decimal';
TD_ENTERO: 'entero';
TD_CADENA: 'cadena';
TD_BOOLEANO: 'booleano';
BOOLEANO: 'v' | 'f' | 'verdadero' | 'falso';
DOSPUNTOS: ':';
WS: [ \t\r\n]+ -> skip;
IDENTIFIER: Letter LetterOrDigit* { notifyListeners(new LexerNoViableAltException(null,null , actionIndex, null)); } -> skip;
fragment LetterOrDigit: Letter | [0-9];

fragment Letter:
	[a-zA-Z$_] // these are the "java letters" below 0x7F
	| ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
	| [\uD800-\uDBFF] [\uDC00-\uDFFF];
	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
COMENTARIO_LINEA: '//' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;
ID:
	'#' [a-zA-Z] ([a-zA-Z0-9]* ('_' [a-zA-Z0-9]+)?)? [a-zA-Z0-9]* '#';
DECIMAL: ([0-9]+ '.' [0-9]+ | '.' [0-9]+);
ENTERO: [0-9]+;