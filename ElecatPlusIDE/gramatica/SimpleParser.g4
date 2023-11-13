grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
programa:
	'programa' 'remoto'? ID cuerpoPrograma EOF;
cuerpoPrograma: '{' miembros '}';
miembros: (setup | ejecucion | declaracionAtributo ';' | funcion)*;
setup: ID '(' ')' bloque;
ejecucion: 'ejecutar' '(' ')' bloque;
funcion:
	'funcion' tipo_dato? ID '(' parametrosFormales ')' (bloque | ';');
declaracionAtributo: tipo declaraciones;
tipo: tipo_dato | COMPONENTE;
declaraciones:
	declaracionDeVariable (',' declaracionDeVariable)*;
parametrosFormales: parametroFormal? (',' parametroFormal)*;
parametroFormal: tipo_dato ID;
bloque: '{' sentencia* '}';
//Duda: SEMI
sentencia:
	declaracionLocal ';'
	| asignacion ';'
	| expresionSentencia = expresion ';'
	| ';'
	| 'continuar' ';'
	| 'romper' ';'
	| 'devolver' expresion ';'
	| 'elegir' parExpresion '{' sentenciaSwitch* '}'
	| 'repetir' 'mientras' parExpresion sentencia
	| 'repetir' 'para' '(' controlFor ')' sentencia
	| 'si' parExpresion sentencia ('sino' sentencia)*
	| bloqueDeSentencias = bloque
	| llamadaAFuncion ';'
	| accion ';'
	| esperar ';';
llamadaAFuncion: ID argumentos;
argumentos: '(' listaExpresiones? ')';
controlFor: iniciadorFor ';' expresion? ';' listaExpresiones;
iniciadorFor: declaracionLocal | listaExpresiones;
listaExpresiones: expresion (',' expresion)*;
parExpresion: '(' expresion ')';
sentenciaSwitch: etiquetaSwitch+ sentencia+;
etiquetaSwitch:
	'caso' (
		expresionConstante = expresion
		| identificadorConstante = ID
	) ':'
	| 'predeterminado' ':';
declaracionLocal: tipo declaracionDeVariable;
declaracionDeVariable: ID ('=' expresion)?;
asignacion: ID '=' expresion;
accion:
	'accion' '(' ID ',' (
		'sonar' argumentos
		| 'escribir' parExpresion
		| 'girar' parExpresion
		| 'avanzar' parExpresion
		| 'detectar' parExpresion
		| 'encender' '(' ')'
		| 'apagar' '(' ')'
	) ')';
// // esperar | ACCION PAR_ABRIR ID COMA (girar | escribir) PAR_CERRAR FIN_LINEA;
esperar: 'esperar' parExpresion;
termino: BOOLEANO | numero | parExpresion | CADENA | ID;
expresion: termino operacionBinaria*;
operacionBinaria: (op_bin termino);
numero: (DECIMAL | ENTERO);
op_bin: (OP_LOGICO | OP_COMPARADOR | OP_ARITMETICO);
tipo_dato: (
		TD_DECIMAL
		| TD_ENTERO
		| TD_CADENA
		| TD_BOOLEANO
	);