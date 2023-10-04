grammar Simple;

@parser::header {
    import java.util.Map;
    import java.util.HashMap;
}

// Acciones semánticas
@parser::members {
  // Tabla de símbolos para realizar un seguimiento de las variables declaradas
  Map<String,String> variablesDeclaradas = new HashMap<>();
  boolean isDeclaration = false;
  Token t;

  // Predicado semántico para verificar si una variable se ha declarado al intentar declararla
  boolean variableDeclarada(String variableName, String variableType) {
	if (!isDeclaration)
		return false;
	if (variablesDeclaradas.containsKey(variableName))
		return true;
	variablesDeclaradas.put(variableName,variableType);
	return false;
  }

  // Predicado semántico para verificar si una variable se ha declarado al intentar declararla
  boolean usarVariable(String variableName) {
	return variablesDeclaradas.containsKey(variableName);
  }
}

// // Excepción personalizada para errores semánticos class SemanticErrorException extends
// RuntimeException { public SemanticErrorException(String message) { super(message); } }

// @parser::members { boolean declarando; Map<String, Object> tablaSimbolos = new HashMap<String,
// Object>(); }

// ESTRUCTURAS DEL PROGRAMA.
programa: PROGRAMA ID BRACKET_ABRIR sentencia+ BRACKET_CERRAR;

sentencia:
	declaracion
	| asignacion
	| condicionalif
	| cicloWhile
	| accion;
// booleano #xd# = v;
declaracion:
	{ isDeclaration = true; } (t=TIPO_DATO | t=COMPONENTE) {t = $t;} (
		(ID { !variableDeclarada($ID.text,$t.text) }? FIN_LINEA)
		| asignacion
	) { isDeclaration = false;};

asignacion:
	ID { !variableDeclarada($ID.text,t.getText()) }? { usarVariable($ID.text) }? SIGNO_IGUAL expresion FIN_LINEA
		;

condicionalif:
	SI PARENTESIS_ABRIR expresion PARENTESIS_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR (
		SI_NO BRACKET_ABRIR sentencia* BRACKET_CERRAR
	)?;
cicloWhile:
	REPETIR MIENTRAS PARENTESIS_ABRIR expresion PARENTESIS_CERRAR BRACKET_ABRIR sentencia*
		BRACKET_CERRAR;

accion:
	esperar
	| ACCION PARENTESIS_ABRIR ID { usarVariable($ID.text) }? COMA (
		girar
		| escribir
	) PARENTESIS_CERRAR FIN_LINEA;

// (ACCION ( PARENTESIS_ABRIR ID COMA (ESTADO|ACCIONES) PARENTESIS_CERRAR | ) | esperar | girar )
// FIN_LINEA; ACCION (#idComponente#, ESTADO | ACCIONES | GIRAR | ESCRIBIR); ACCION (#idLED#,
// ENCENDER); ACCION (#idServo#, GIRA(180, antihorario)); ACCION (#LCD#, ESCRIBIR(Hola Angel));
// ESPERAR(13, SEGUNDOS);
esperar:
	ESPERAR PARENTESIS_ABRIR NUMERO COMA TIEMPO PARENTESIS_CERRAR FIN_LINEA;
// ESPERAR(213, SEGUNDOS);
girar:
	GIRAR PARENTESIS_ABRIR NUMERO COMA DIRECCION PARENTESIS_CERRAR;
// GIRA(grados, direccion)
escribir:
	ESCRIBIR PARENTESIS_ABRIR (ID | CADENA | NUMERO) PARENTESIS_CERRAR;
// ESCRIBIR(ID|Cadena) ACCION(#sieteseg#, ESCRIBIR(#micadena#));

termino:
	BOOLEANO
	| NUMERO
	| ID { usarVariable($ID.text) }?
	| PARENTESIS_ABRIR expresion PARENTESIS_CERRAR;

expresion: termino operacionBinaria*;
operacionBinaria: (OP_BIN termino);

// Regla léxica para comentarios de línea
COMENTARIO_LINEA: '//' ~[\r\n]* -> skip;

// Regla léxica para comentarios de bloque
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;

//-------------------------------------------------------------

// EXPRESIONES REGULARES - Análisis Léxico
// #[a-zA-Z](([a-zA-Z]|[0-9])*(_([a-zA-Z]|[0-9]))?)*([a-zA-Z]|[0-9])*#
ID:
	'#' [a-zA-Z] ([a-zA-Z0-9]* ('_' [a-zA-Z0-9]+)?)? [a-zA-Z0-9]* '#';
NUMERO: ([0-9]+ | [0-9]+ '.' [0-9]+ | '.' [0-9]+);
CADENA: '"' [a-zA-Z0-9]+ '"';

ESCRIBIR: 'escribir';
GIRAR: 'gira';
//PENDIENTE
ACCIONES: 'sonar' | 'encender' | 'apagar';
LEE: 'leer';
PROGRAMA: 'programa';
ACCION: 'accion';
ESTADO: 'encendido' | 'apagado' | 'reversa';
COMPONENTE:
	'led'
	| 'display_lcd'
	| 'servo'
	| 'sensor_ultrasonico'
	| 'motor'
	| 'foto_resistencia'
	| 'buzzer'
	| 'siete_segmentos' 'boton'
	| 'fuente';

DIRECCION: 'horario' | 'antihorario';

// Operadores binarios
OP_BIN: 'and' | 'or' | '+' | '-' | '*' | '/';
NOT: 'not';
TIEMPO: 'milisegundo' | 'segundo' | 'minuto';
UNIDAD: 'voltio' | 'amperio' | 'ohm' | 'watt';
SI: 'si';
SI_NO: 'sino';
CONDICION: 'elegir' | 'opcion' | 'por defecto';
REPETIR: 'repetir';
MIENTRAS: 'mientras';
CICLO: 'hasta' | 'por' | 'para';
VAR: 'var';
TIPO_DATO:
	'decimal'
	| 'entero'
	| 'caracter'
	| 'cadena'
	| 'booleano'
	| 'tabla';
FIN_LINEA: ';';
SIGNO_IGUAL: '=';
BOOLEANO: 'v' | 'f' | 'verdadero' | 'falso';
BRACKET_ABRIR: '{';
BRACKET_CERRAR: '}';
PARENTESIS_ABRIR: '(';
PARENTESIS_CERRAR: ')';
ES: 'es';
MAYOR: 'mayor';
MENOR: 'menor';
IGUAL: 'igual';
QUE: 'que';
DIFERENTE: 'diferente';
ESPERAR: 'esperar';
COMA: ',';

WS: [ \t\r\n]+ -> skip;