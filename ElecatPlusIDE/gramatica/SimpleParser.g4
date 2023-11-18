//antlr4-parse SimpleParser.g4 SimpleLexer.g4 programa -tokens C:\Users\angel\OneDrive\Documentos\ElecatPlusIDE\ElecatPlusIDE\src\Prueba.ecp
//
parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
@header {
	import static Analizadores.SimpleSemantic.*;
	import java.util.HashMap;
}

programa: {variablesDeclaradas = new HashMap<>(); funcionesDeclaradas = new HashMap<>();} 'programa' 'remoto'? ID cuerpoPrograma EOF;
cuerpoPrograma: '{' miembros '}';
miembros: (setup | ejecucion | declaracionAtributo ';' | funcion)*;
setup: ID '(' ')' bloque;
ejecucion: 'ejecutar' '(' ')' bloque;
funcion: { listaParametros = new ArrayList<>(); nombreParametros.clear(); }
	'funcion' (tipo_dato { listaParametros.add(t); })?  ID '(' parametrosFormales ')' { funcionDeclarada($ID, listaParametros); } (
		bloque
		| ';'
	);
declaracionAtributo: tipo declaraciones;
tipo: tipo_dato | t = COMPONENTE { t=$t; };
declaraciones:
	declaracionDeVariable (',' declaracionDeVariable)*;
parametrosFormales: parametroFormal? (',' parametroFormal)*;
parametroFormal: tipo_dato ID { listaParametros.add(t); } { parametrosDeclarados($ID); };
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
	) ':'
	| 'predeterminado' ':';
declaracionLocal: tipo declaracionDeVariable;
declaracionDeVariable:
	ID ('=' expresion)? { variableDeclarada($ID,t); };
accion:
	'accion' '(' ID ',' (
		'sonar' argumentos
		| 'escribir' parExpresion
		| 'girar' parExpresion
		| 'avanzar' parExpresion
		| 'detectar' parExpresion
		| 'encender' '(' ')'
		| 'apagar' '(' ')'
	) ')' { usarVariable($ID); };
// // esperar | ACCION PAR_ABRIR ID COMA (girar | escribir) PAR_CERRAR FIN_LINEA;
esperar: 'esperar' parExpresion;
expresion:
	primaria
	| expresion ('*' | '/') expresion
	| expresion ('+' | '-') expresion
	| expresion ('<=' | '>=' | '>' | '<') expresion
	| expresion ('==' | '!=') expresion
	| expresion 'and' expresion
	| expresion 'or' expresion
	| ID '=' expresion {usarVariable($ID);};
primaria: 
	literal 
	| parExpresion 
	| ID {usarVariable($ID);};
literal: DECIMAL | CADENA | BOOLEANO | ENTERO;
numero: (DECIMAL | ENTERO);
tipo_dato:
	t = (TD_DECIMAL | TD_ENTERO | TD_CADENA | TD_BOOLEANO) {t=$t;};