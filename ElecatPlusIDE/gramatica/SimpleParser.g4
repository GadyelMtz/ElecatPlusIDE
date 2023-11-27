parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
@header { 	import static Analizadores.SimpleSemantic.*; 	import java.util.HashMap; import static Analizadores.SimpleCode.*;
	}
programa:
	{new SimpleSemantic();} 'programa' 'remoto'? ID {nombrePrograma=$ID.getText();} cuerpoPrograma
		EOF;
cuerpoPrograma: '{' miembros '}';
miembros: (setup | ejecucion | declaracionAtributo ';' | funcion)*;
setup:
	ID {if(!nombrePrograma.equals($ID.getText()))semanticError($ID, "el método principal debe llamarse igual que el programa.");
		} '(' ')' {new Quintupla(new CommonToken(-1,"setup"),$ID,null,null);} bloque;
ejecucion:
	O = 'ejecutar' '(' ')' {new Quintupla(new CommonToken(-1,"loop"),$O,null,null);} bloque;
funcion:
	{nuevaFuncion();} 'funcion' (
		tipo_dato { retornoFuncion = t.getType(); }
	)? ID '(' parametrosFormales ')' { funcionDeclarada($ID, listaParametros); } (
		bloque
		| ';'
	);
declaracionAtributo:
	tipo {td_variable=t.getType();} declaraciones;
tipo: tipo_dato | t = COMPONENTE { t=$t; };
declaraciones:
	declaracionDeVariable (',' declaracionDeVariable)*;
parametrosFormales: parametroFormal? (',' parametroFormal)*;
parametroFormal:
	tipo_dato ID { listaParametros.add(t); } { parametrosDeclarados($ID); };
bloque: '{' sentencia* '}';
sentencia:
	declaracionLocal ';'
	| expresion ';'
	| expresionSentencia = expresion ';'
	| ';'
	| 'continuar' ';'
	| 'romper' ';'
	| t = 'devolver' {comprobarRetorno($t, _ctx);} (
		{comprobarRetorno($t);} {nuevaExpresion();} expresion {resolverExpresion(t -> retorno(retornoFuncion).test(t), s.getSymbolicName(retornoFuncion));
			}
	)? ';'
	| 'elegir' {nuevoSwitch();} parExpresion {resolverSwitch(t);} {if(banderaSwitch)td_switch = salida.peek().getType();
		} '{' sentenciaSwitch* '}'
	| 'repetir' 'mientras' {nuevaExpresion();} parExpresion {resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
		} sentencia
	| 'repetir' 'para' '(' controlFor ')' sentencia
	| 'si' {nuevaExpresion();} parExpresion {resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
		} sentencia ('sino' sentencia)*
	| bloqueDeSentencias = bloque
	| llamadaAFuncion ';'
	| accion ';'
	| esperar ';';
llamadaAFuncion: ID {nuevaExpresion();} argumentos {/*TODO:*/};
argumentos:
	'(' ({pilasArgumento = new ArrayList<>();} listaExpresiones)? T = ')' {validarListaExpresiones(_ctx, $T);
		};
controlFor:
	iniciadorFor ';' (
		{nuevaExpresion();} expresion {resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
			}
	)? ';' listaExpresiones;
iniciadorFor: declaracionLocal | listaExpresiones;
listaExpresiones:
	{nuevaExpresion();} expresion {validarArgumento(_ctx);} (
		{nuevaExpresion();} ',' expresion {validarArgumento(_ctx);}
	)*;
parExpresion:
	OP = '(' { añadirAPila($OP);} expresion OP = ')' { añadirAPila($OP);};
sentenciaSwitch: etiquetaSwitch+ sentencia+;
etiquetaSwitch:
	'caso' (
		{nuevaExpresion();} expresion {if (banderaSwitch)resolverExpresion(t -> retorno(td_switch).test(t) , "igual a la evaluación: "+s.getSymbolicName(td_switch));
			}
	) ':'
	| 'predeterminado' ':';
declaracionLocal:
	tipo {td_variable = t.getType();} declaracionDeVariable (
		',' declaracionDeVariable
	)*;
declaracionDeVariable:
	ID { if(declararVariable($ID,t))nuevaExpresion(); } {new Quintupla(t,((DeclaracionDeVariableContext)_localctx).ID,null,null);
		} (
		OP = '=' {añadirAPila($ID);añadirAPila($OP);} expresion {resolverAsignacion($ID,_ctx);}
	)?;
accion:
	'accion' '(' ID {iniciarAccion($ID);} ',' (
		'sonar' { comprobarComponente($ID, "buzzer"); } argumentos {/*TODO:*/}
		| 'escribir' {comprobarComponente($ID, "display_lcd");} parExpresion {resolverExpresion(t -> t==CADENA | t == TD_CADENA, "TD_CADENA o CADENA");
			}
		| G = 'girar' {comprobarComponente($ID, "servo");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			} {new Quintupla($G,$ID,salida.peek(),null);}
		| 'avanzar' {comprobarComponente($ID, "motor");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			}
		| 'detectar' {comprobarComponente($ID, "sensor_distancia");} parExpresion {resolverDetectar(t);
			}
		| 'detener' {comprobarComponente($ID, "motor");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			}
		| 'encender' {comprobarComponente($ID, "led");} '(' ')'
		| 'apagar' {comprobarComponente($ID, "led");} '(' ')'
		| 'mostrar' {comprobarComponente($ID, "siete_segmentos");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			}
	) ')';
esperar:
	'esperar' {nuevaExpresion();} parExpresion {resolverExpresion(t -> t == TD_ENTERO | t == ENTERO, "ENTERO o TD_ENTERO");
		};
expresion:
	primaria
	| expresionBinaria = expresion (
		OP = ('*' | '/')
		| OP = ('+' | '-')
		| OP = ('<=' | '>=' | '>' | '<')
		| OP = ('==' | '!=')
		| OP = 'and'
		| OP = 'or'
	) {añadirAPila($OP);} expresion
	| <assoc = right> ID OP = '=' {nuevaExpresion();} {añadirAPila($ID);añadirAPila($OP);} expresion
		{if(usarVariable($ID))resolverAsignacion($ID,_ctx);};
primaria:
	literal
	| parExpresion
	| ID {if(usarVariable($ID))añadirAPila($ID);};
literal:
	t = (DECIMAL | CADENA | BOOLEANO | ENTERO) {añadirAPila($t);};
tipo_dato:
	t = (TD_DECIMAL | TD_ENTERO | TD_CADENA | TD_BOOLEANO) {t=$t;};