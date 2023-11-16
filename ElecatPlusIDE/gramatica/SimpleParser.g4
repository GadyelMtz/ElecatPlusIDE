parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
@header{
	import static Analizadores.SimpleSemantic.*;
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
tipo: tipo_dato | t=COMPONENTE { t=$t; };
declaraciones:
	declaracionDeVariable (',' declaracionDeVariable)*;
parametrosFormales: parametroFormal? (',' parametroFormal)*;
parametroFormal: tipo_dato ID;
bloque: '{' sentencia* '}';
//Duda: SEMI
sentencia:
	declaracionLocal ';'
	| expresion ';'
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
declaracionDeVariable: ID ('=' expresion)? { variableDeclarada($ID,t); };
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
expresion: 
	primaria
	| expresion ('*'|'/') expresion
	| expresion ('+'|'-') expresion
	| expresion ('<=' | '>=' | '>' | '<') expresion
	| expresion ('=='|'!=') expresion
	| expresion 'and' expresion
	| expresion 'or' expresion	
	| ID '=' expresion;
primaria: 
	literal
	|parExpresion
	|ID;
literal: 
	DECIMAL
	|CADENA
	|BOOLEANO
	|ENTERO;
numero: (DECIMAL | ENTERO);
tipo_dato: t=(
		TD_DECIMAL
		| TD_ENTERO
		| TD_CADENA
		| TD_BOOLEANO
	) {t=$t;};