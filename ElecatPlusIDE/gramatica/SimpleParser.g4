//antlr4-parse SimpleParser.g4 SimpleLexer.g4 programa -tokens C:\Users\angel\OneDrive\Documentos\ElecatPlusIDE\ElecatPlusIDE\src\Prueba.ecp
//antlr4 SimpleLexer.g4 -no-listener -no-visitor
//antlr4 SimpleParser.g4 -no-listener -no-visitor
parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
@header {
	import static Analizadores.SimpleSemantic.*;
	import java.util.HashMap;
}

programa: {new SimpleSemantic();} 'programa' 'remoto'? ID cuerpoPrograma EOF;
cuerpoPrograma: '{' miembros '}';
miembros: (setup | ejecucion | declaracionAtributo ';' | funcion)*;
setup: ID '(' ')' bloque;
ejecucion: 'ejecutar' '(' ')' bloque;
funcion: { listaParametros = new ArrayList<>(); nombreParametros.clear(); retornoFuncion = -1; }
	'funcion' (tipo_dato { listaParametros.add(t); retornoFuncion = t.getType(); } )?  ID '(' parametrosFormales ')' { funcionDeclarada($ID, listaParametros); } (
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
	| t='devolver' {comprobarPadre($t, _ctx);} ({comprobarRetorno($t);} {nuevaExpresion();} expresion { if(banderaRetorno)resolverPila(t -> t == retornoFuncion);})? ';'
	| 'elegir' {nuevoSwitch();} parExpresion {banderaSwitch = resolverPila(t -> tdVariables.contains(t)); if(banderaSwitch)td_switch = obtenerResultadoPila();} '{' sentenciaSwitch* '}'
	| 'repetir' 'mientras' {nuevaExpresion();} parExpresion {resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO);} sentencia
	| 'repetir' 'para' '(' controlFor ')' sentencia
	| 'si' {nuevaExpresion();} parExpresion {resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO);} sentencia ('sino' sentencia)*
	| bloqueDeSentencias = bloque
	| llamadaAFuncion ';'
	| accion ';'
	| esperar ';';
llamadaAFuncion: ID {nuevaExpresion();} argumentos {resolverPila(1);};
argumentos: '(' listaExpresiones? ')' ;
controlFor: iniciadorFor ';' ({nuevaExpresion();} expresion {resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO);})? ';' listaExpresiones;
iniciadorFor: declaracionLocal | listaExpresiones;
listaExpresiones: expresion (',' expresion)*;
parExpresion: OP='(' { añadirAPila($OP);} expresion OP=')'{ añadirAPila($OP);};
sentenciaSwitch: etiquetaSwitch+ sentencia+;
etiquetaSwitch:
	'caso' (
		{nuevaExpresion();} expresionConstante = expresion {if(banderaSwitch)resolverPila(t -> retorno(td_switch).test(t));}
	) ':'
	| 'predeterminado' ':';
declaracionLocal: tipo {td_variable = $t.getType();} declaracionDeVariable;
declaracionDeVariable:
	ID { variableDeclarada($ID,t); } ({nuevaExpresion();} '=' expresion {resolverPila(t -> retorno(td_variable).test(t));})?;
accion:
	'accion' '(' ID ',' (
		'sonar' {nuevaExpresion();} argumentos {resolverPila(1);}
		| 'escribir' {nuevaExpresion();} parExpresion {resolverPila(t -> t == TD_CADENA | t == CADENA);}
		| 'girar' {nuevaExpresion();} parExpresion {resolverPila(t -> t == TD_ENTERO | t == ENTERO);}
		| 'avanzar' {nuevaExpresion();} parExpresion {resolverPila(t -> t == TD_ENTERO | t == ENTERO);}
		| 'detectar' {nuevaExpresion();} parExpresion {resolverDetectar(t);}
		| 'detener' {nuevaExpresion();} parExpresion {resolverPila(t -> t == TD_ENTERO | t == ENTERO);}
		| 'encender' '(' ')'
		| 'apagar' '(' ')'
	) ')' { usarVariable($ID); };
// // esperar | ACCION PAR_ABRIR ID COMA (girar | escribir) PAR_CERRAR FIN_LINEA;
esperar: 'esperar' {nuevaExpresion();} parExpresion {resolverPila(t -> t == ENTERO | t == TD_ENTERO);};
expresion:
	primaria
	| expresion OP=('*' | '/') { añadirAPila($OP);} expresion
	| expresion OP=('+' | '-') { añadirAPila($OP);}expresion
	| expresion OP=('<=' | '>=' | '>' | '<') { añadirAPila($OP);}expresion
	| expresion OP=('==' | '!=') { añadirAPila($OP);} expresion
	| expresion OP='and' { añadirAPila($OP);} expresion
	| expresion OP='or' { añadirAPila($OP);} expresion
	| <assoc=right> ID '=' {nuevaExpresion();} expresion {usarVariable($ID);} {resolverPila(t -> retorno(variablesDeclaradas.get($ID.getText()).getType();).test(t));};
primaria: 
	literal 
	| parExpresion
	| ID {usarVariable($ID);} { añadirAPila($ID);};
literal: t=(DECIMAL | CADENA | BOOLEANO | ENTERO) {añadirAPila($t);};
tipo_dato:
	t = (TD_DECIMAL | TD_ENTERO | TD_CADENA | TD_BOOLEANO) {t=$t;};