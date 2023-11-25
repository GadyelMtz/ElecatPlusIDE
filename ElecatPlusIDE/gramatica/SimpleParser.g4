// antlr4-parse SimpleParser.g4 SimpleLexer.g4 programa -tokens C:\Users\angel\OneDrive\Documentos\ElecatPlusIDE\ElecatPlusIDE\src\Prueba.ecp
// antlr4 SimpleLexer.g4 -no-listener -no-visitor 
// antlr4 SimpleParser.g4 -no-listener -no-visitor
parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
@header {
	import static Analizadores.SimpleSemantic.*;
	import java.util.HashMap;
}

programa:
	{new SimpleSemantic();} 'programa' 'remoto'? ID cuerpoPrograma EOF;
cuerpoPrograma: '{' miembros '}';
miembros: (setup | ejecucion | declaracionAtributo ';' | funcion)*;
setup: ID '(' ')' bloque;
ejecucion: 'ejecutar' '(' ')' bloque;
funcion:
	{ listaParametros = new ArrayList<>(); nombreParametros.clear(); retornoFuncion = -1; }
		'funcion' (
		tipo_dato { listaParametros.add(t); retornoFuncion = t.getType(); }
	)? ID '(' parametrosFormales ')' { funcionDeclarada($ID, listaParametros); } (
		bloque
		| ';'
	);
declaracionAtributo: tipo {td_variable=t.getType();} declaraciones;
tipo: tipo_dato | t = COMPONENTE { t=$t; };
declaraciones:
	declaracionDeVariable (',' declaracionDeVariable)*;
parametrosFormales: parametroFormal? (',' parametroFormal)*;
parametroFormal:
	tipo_dato ID { listaParametros.add(t); } { parametrosDeclarados($ID); };
bloque: '{' sentencia* '}';
//Duda: SEMI
sentencia:
	declaracionLocal ';'
	| expresion ';'
	| expresionSentencia = expresion ';'
	| ';'
	| 'continuar' ';'
	| 'romper' ';'
	| t = 'devolver' {comprobarPadre($t, _ctx);} (
		{comprobarRetorno($t);} {nuevaExpresion();} expresion { if(banderaRetorno)if (!resolverPila(t -> t == retornoFuncion))errorPila(SimpleParser.VOCABULARY.getSymbolicName(retornoFuncion),salida.peek());
			}
	)? ';'
	| 'elegir' {nuevoSwitch();} parExpresion {resolverSwitch(t); if(banderaSwitch)td_switch = obtenerResultadoPila();
		} '{' sentenciaSwitch* '}'
	| 'repetir' 'mientras' {nuevaExpresion();} parExpresion {if(!resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO))errorPila("BOOLEANO o TD_BOOLEANO", salida.peek());
		} sentencia
	| 'repetir' 'para' '(' controlFor ')' sentencia
	| 'si' {nuevaExpresion();} parExpresion {if(!resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO))errorPila("BOOLEANO o TD_BOOLEANO", salida.peek());
		} sentencia ('sino' sentencia)*
	| bloqueDeSentencias = bloque
	| llamadaAFuncion ';'
	| accion ';'
	| esperar ';';
llamadaAFuncion:
	ID {nuevaExpresion();} argumentos {resolverPila(1);};
argumentos: '(' listaExpresiones? ')';
controlFor:
	iniciadorFor ';' (
		{nuevaExpresion();} expresion {if(!resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO))errorPila("BOOLEANO o TD_BOOLEANO", salida.peek());
			}
	)? ';' listaExpresiones;
iniciadorFor: declaracionLocal | listaExpresiones;
listaExpresiones: expresion (',' expresion)*;
parExpresion:
	OP = '(' { añadirAPila($OP);} expresion OP = ')' { añadirAPila($OP);};
sentenciaSwitch: etiquetaSwitch+ sentencia+;
etiquetaSwitch:
	'caso' (
		{nuevaExpresion();} expresionConstante = expresion {if(banderaSwitch)if(resolverPila(t -> retorno(td_switch).test(t)))errorPila("el mismo tipo de dato que se evalúa "+ SimpleParser.VOCABULARY.getSymbolicName(td_switch), salida.peek());
			}
	) ':'
	| 'predeterminado' ':';
declaracionLocal:
	tipo {td_variable = t.getType();} declaracionDeVariable;
declaracionDeVariable:
	ID { variableDeclarada($ID,t); } (
		{nuevaExpresion();} '=' expresion {if(resolverPila(t -> retorno(td_variable).test(t)))errorPila("el mismo tipo de dato que se declara "+ SimpleParser.VOCABULARY.getSymbolicName(td_variable), salida.peek());
			}
	)?;
accion:
	'accion' '(' ID ',' (
		'sonar' {nuevaExpresion();} argumentos {resolverPila(1);}
		| 'escribir' {nuevaExpresion();} parExpresion {if(resolverPila(t -> t == TD_CADENA | t == CADENA))errorPila("CADENA o TD_CADENA", t);
			}
		| 'girar' {nuevaExpresion();} parExpresion {if(resolverPila(t -> t == TD_ENTERO | t == ENTERO))errorPila("ENTERO o TD_ENTERO", t);
			}
		| 'avanzar' {nuevaExpresion();} parExpresion {if(resolverPila(t -> t == TD_ENTERO | t == ENTERO))errorPila("ENTERO o TD_ENTERO", t);
			}
		| 'detectar' {nuevaExpresion();} parExpresion {resolverDetectar(t);}
		| 'detener' {nuevaExpresion();} parExpresion {if(resolverPila(t -> t == TD_ENTERO | t == ENTERO))errorPila("ENTERO o TD_ENTERO", t);
			}
		| 'encender' '(' ')'
		| 'apagar' '(' ')'
	) ')' { usarVariable($ID); };
// // esperar | ACCION PAR_ABRIR ID COMA (girar | escribir) PAR_CERRAR FIN_LINEA;
esperar:
	'esperar' {nuevaExpresion();} parExpresion {if(resolverPila(t -> t == TD_ENTERO | t == ENTERO))errorPila("ENTERO o TD_ENTERO", t);
		};
expresion:
	primaria
	| expresion OP = ('*' | '/') { añadirAPila($OP);} expresion
	| expresion OP = ('+' | '-') { añadirAPila($OP);} expresion
	| expresion OP = ('<=' | '>=' | '>' | '<') { añadirAPila($OP);} expresion
	| expresion OP = ('==' | '!=') { añadirAPila($OP);} expresion
	| expresion OP = 'and' { añadirAPila($OP);} expresion
	| expresion OP = 'or' { añadirAPila($OP);} expresion
	| <assoc = right> ID '=' {nuevaExpresion();} expresion {usarVariable($ID);} {
		final ExpresionContext a = _localctx;
						if (!resolverPila(t -> retorno(
								variablesDeclaradas.get(((ExpresionContext) a).ID.getText()).getType())
								.test(t)))
							errorPila("el mismo tipo de dato que se declara "
									+ SimpleParser.VOCABULARY.getSymbolicName(td_variable), salida.peek());
	};
primaria:
	literal
	| parExpresion
	| ID {usarVariable($ID);} { añadirAPila($ID);};
literal:
	t = (DECIMAL | CADENA | BOOLEANO | ENTERO) {añadirAPila($t);};
tipo_dato:
	t = (TD_DECIMAL | TD_ENTERO | TD_CADENA | TD_BOOLEANO) {t=$t;};