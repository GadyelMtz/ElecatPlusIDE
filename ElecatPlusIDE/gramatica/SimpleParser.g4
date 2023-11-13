parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
//Statement
programa:
	'programa' MODIFICADOR = 'remoto'? ID cuerpoPrograma EOF;
cuerpoPrograma: '{' miembros* '}';
miembros: 
	setup
	| ejecucion
	| tipo declaraciones ';'
	| funcion;
funcion: 'funcion' tipo_dato? ID parametrosFormales (bloque|';');
parametrosFormales: parametroFormal (',' parametroFormal)*;
parametroFormal: tipo_dato ID;
declaraciones: declaracionDeVariable (',' declaracionDeVariable)*;
setup: ID '(' ')' bloque;
ejecucion: 'ejecutar' '(' ')' bloque;
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
	| 'elegir' parExpresion '{' sentenciaSwitch* etiquetaSwitch* '}'
	| 'repetir' 'mientras' parExpresion sentencia
	| 'repetir' 'para' '(' controlFor ')' sentencia
	| 'si' parExpresion sentencia ('sino' sentencia)*
	| accion
	| bloqueDeSentencias=bloque;
controlFor: iniciadorFor ';' expresion? ';' listaExpresiones;
iniciadorFor: declaracionLocal|listaExpresiones;
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
tipo: tipo_dato | COMPONENTE;
asignacion: ID '=' expresion;
cicloWhile:
	REPETIR MIENTRAS PAR_ABRIR expresion PAR_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR;
accion:
	esperar
	| ACCION PAR_ABRIR ID COMA (girar | escribir) PAR_CERRAR FIN_LINEA;
esperar:
	ESPERAR PAR_ABRIR numero (COMA TIEMPO)? PAR_CERRAR FIN_LINEA;
girar: GIRAR PAR_ABRIR numero COMA DIRECCION PAR_CERRAR;
escribir: ESCRIBIR PAR_ABRIR (ID | CADENA | numero) PAR_CERRAR;
termino: (BOOLEANO | ID)
	| numero
	| PAR_ABRIR expresion PAR_CERRAR;
expresion: termino operacionBinaria*;
operacionBinaria: (op_bin termino);
numero: (DECIMAL | ENTERO);
op_bin: (OP_LOGICO | OP_COMPARADOR | OP_ARITMETICO);
tipo_dato: (
		TD_DECIMAL
		| TD_ENTERO
		| TD_CARACTER
		| TD_CADENA
		| TD_BOOLEANO
	);