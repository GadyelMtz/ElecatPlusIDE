grammar Simple;

@parser::header {
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
}

// Acciones semánticas

// // Excepción personalizada para errores semánticos class SemanticErrorException extends
// RuntimeException { public SemanticErrorException(String message) { super(message); } }

// @parser::members { boolean declarando; Map<String, Object> tablaSimbolos = new HashMap<String,
// Object>(); }

// ESTRUCTURAS DEL PROGRAMA.
programa:
	PROGRAMA ID BRACKET_ABRIR sentencia+ sentencia+ BRACKET_CERRAR EOF;

sentencia:
	declaracion
	| asignacion
	| condicionalif
	| cicloWhile
	| accion;

declaracion:
	{ isDeclaration = true; } (tipo_dato | t = COMPONENTE {t = $t;}) (
		(ID { !variableDeclarada($ID,t) }? FIN_LINEA)
		| asignacion
	) { isDeclaration = false;};

asignacion:
	ID { !variableDeclarada($ID,t) }? { usarVariable($ID) }? SIGNO_IGUAL expresion {resolverPila(new HashSet<>(Arrays.asList(variablesDeclaradas.get($ID.text).getType())))
		}? FIN_LINEA;

condicionalif:
	SI PAR_ABRIR expresion {resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))}?
		PAR_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR (
		SI_NO (
			SI PAR_ABRIR expresion {resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
				}? PAR_CERRAR
		)? BRACKET_ABRIR sentencia* BRACKET_CERRAR
	)?;

cicloWhile:
	REPETIR MIENTRAS PAR_ABRIR expresion {resolverPila(new HashSet<>(Arrays.asList(BOOLEANO,TD_BOOLEANO)))
		}? PAR_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR;

accion:
	esperar
	| ACCION PAR_ABRIR ID { usarVariable($ID) }? COMA (
		girar
		| escribir
	) PAR_CERRAR FIN_LINEA;

// (ACCION ( PARENTESIS_ABRIR ID COMA (ESTADO|ACCIONES) PARENTESIS_CERRAR | ) | esperar | girar )
// FIN_LINEA; ACCION (#idComponente#, ESTADO | ACCIONES | GIRAR | ESCRIBIR); ACCION (#idLED#,
// ENCENDER); ACCION (#idServo#, GIRA(180, antihorario)); ACCION (#LCD#, ESCRIBIR(Hola Angel));
// ESPERAR(13, SEGUNDOS);
esperar:
	ESPERAR PAR_ABRIR numero (COMA TIEMPO)? PAR_CERRAR FIN_LINEA;
// ESPERAR(213, SEGUNDOS);
girar: GIRAR PAR_ABRIR numero COMA DIRECCION PAR_CERRAR;
// GIRA(grados, direccion)
escribir: ESCRIBIR PAR_ABRIR (ID | CADENA | numero) PAR_CERRAR;
// ESCRIBIR(ID|Cadena) ACCION(#sieteseg#, ESCRIBIR(#micadena#));

termino:
	(t = BOOLEANO | t = ID { usarVariable($t) }?) { añadirAPila($t); }
	| numero {añadirAPila(t);}
	| t = PAR_ABRIR { añadirAPila($t); } expresion t = PAR_CERRAR { añadirAPila($t); };

expresion: termino operacionBinaria*;
operacionBinaria: (op_bin { añadirAPila(t); } termino);

// Regla léxica para comentarios de línea
COMENTARIO_LINEA: '//' ~[\r\n]* -> skip;

// Regla léxica para comentarios de bloque
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;

//-------------------------------------------------------------

// EXPRESIONES REGULARES - Análisis Léxico
// #[a-zA-Z](([a-zA-Z]|[0-9])*(_([a-zA-Z]|[0-9]))?)*([a-zA-Z]|[0-9])*#
ID:
	'#' [a-zA-Z] ([a-zA-Z0-9]* ('_' [a-zA-Z0-9]+)?)? [a-zA-Z0-9]* '#';
numero: (t = DECIMAL | t = ENTERO) {t=$t;};
DECIMAL: ([0-9]+ '.' [0-9]+ | '.' [0-9]+);
ENTERO: [0-9]+;
CADENA: '"' [a-zA-Z0-9]+ '"';

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

// Operadores binarios
op_bin: (t = OP_LOGICO | t = OP_COMPARADOR | t = OP_ARITMETICO) {t=$t;};
OP_LOGICO: 'and' | 'or';
OP_COMPARADOR: '==' | '!=' | '<' | '<=' | '>' | '>=';
OP_ARITMETICO: '+' | '-' | '*' | '/';
NOT: 'not';
TIEMPO: 'milisegundo' | 'segundo' | 'minuto';
SI: 'si';
SI_NO: 'si_no';
REPETIR: 'repetir';
MIENTRAS: 'mientras';
tipo_dato:
	(t = TD_DECIMAL
	| t = TD_ENTERO
	| t = TD_CARACTER
	| t = TD_CADENA
	| t = TD_BOOLEANO) {t=$t;};

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