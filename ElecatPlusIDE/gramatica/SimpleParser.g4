parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
@header {
import static Analizadores.SimpleSemantic.*;
import java.util.HashMap;
import static Analizadores.SimpleCode.*;
import java.util.Stack;
}
programa:
	{new SimpleSemantic();} 'programa' 'remoto'? ID {nombrePrograma=$ID.getText();} cuerpoPrograma
		EOF;
cuerpoPrograma: '{' miembros '}';
miembros: (setup | ejecucion | declaracionAtributo ';' | funcion)*;
setup:
	ID {if(!nombrePrograma.equals($ID.getText()))semanticError($ID, "el método principal debe llamarse igual que el programa.");
		} '(' ')' {new Quintupla(new CommonToken(-1,"setup"),$ID,new CommonToken(-1,"{"),null);}
		bloque {new Quintupla(new CommonToken(-1,"}"),$ID,null,null);};
ejecucion:
	O = 'ejecutar' '(' ')' {new Quintupla(new CommonToken(-1,"loop"),$O,new CommonToken(-1,"{"),null);
		} bloque {new Quintupla(new CommonToken(-1,"}"),$O,null,null);};
funcion:
	{nuevaFuncion();} 'funcion' (
		tipo_dato { retornoFuncion = t.getType(); }
	)? ID '(' parametrosFormales ')' { funcionDeclarada($ID, listaParametros); } (
		bloque
		| ';'
	);
declaracionAtributo:
	tipo {td_variable=t.getType();} {td=t;} declaraciones;
tipo: tipo_dato | t = COMPONENTE { t=$t; };
declaraciones:
	declaracionDeVariable (',' declaracionDeVariable)*;
parametrosFormales: parametroFormal? (',' parametroFormal)*;
parametroFormal:
	tipo_dato ID { listaParametros.add(t); } { parametrosDeclarados($ID); };
bloque: '{' sentencia* '}';
sentencia:
	declaracionLocal ';'
	| expresionSentencia = expresion ';'
	| ';'
	| t = 'continuar' ';' {{new Quintupla($t,null,null,null);}}
	| t = 'romper' ';' {new Quintupla($t,null,null,null);}
	| t = 'devolver' {comprobarRetorno($t, _ctx);} (
		{comprobarRetorno($t);} {nuevaExpresion();} expresion {resolverExpresion(t -> retorno(retornoFuncion).test(t), s.getSymbolicName(retornoFuncion));
			} {new Quintupla(t,salida.peek(),null,null);}
	)? ';' {new Quintupla(t,null,null,null);}
	| t = 'elegir' {nuevoSwitch();} parExpresion {resolverSwitch(t);} {if(banderaSwitch)td_switch = salida.peek().getType();
		} '{' {new Quintupla($t,salida.peek(),new CommonToken(-1,"{"),null);
		} sentenciaSwitch* {new Quintupla(new CommonToken(-1,"}"),$t,null,null);
		} '}'
	| 'repetir' t = 'mientras' {nuevaExpresion();} parExpresion {resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
		} {new Quintupla($t,salida.peek(),new CommonToken(-1,"{"),null);
		} sentencia {new Quintupla(new CommonToken(-1,"}"),$t,null,null);
		}
	| 'repetir' t = 'para' '(' controlFor ')' {new Quintupla($t,salida.peek(),new CommonToken(-1,"{"),null);
		} sentencia {new Quintupla(new CommonToken(-1,"}"),$t,null,null);
		}
	| t = 'si' {nuevaExpresion();} parExpresion {resolverExpresion(t -> t==BOOLEANO | t==TD_BOOLEANO , "TD_BOOLEANO o BOOLEANO");
		} {new Quintupla(t,salida.peek(),new CommonToken(-1,"{"),null);} sentencia {new Quintupla(new CommonToken(-1,"}"),$t,null,null);
		} (
		t = 'sino' {new Quintupla(t,null,new CommonToken(-1,"{"),null);} sentencia {new Quintupla(new CommonToken(-1,"}"),$t,null,null);
			}
	)*
	| bloqueDeSentencias = bloque
	| llamadaAFuncion ';'
	| accion ';'
	| esperar ';';
llamadaAFuncion: ID {nuevaExpresion();} argumentos {/*TODO:*/};
argumentos:
	'(' ({pilasArgumento = new Stack<>();} listaExpresiones)? T = ')' {validarListaExpresiones(_ctx, $T);
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
	ID { if(declararVariable($ID,t))nuevaExpresion(); } {new Quintupla(td,((DeclaracionDeVariableContext)_localctx).ID,null,null);
		} (
		OP = '=' {añadirAPila($ID);añadirAPila($OP);} expresion {resolverAsignacion($ID,_ctx);}
	)?;
accion:
	'accion' '(' ID {iniciarAccion($ID);} ',' (
		G = 'sonar' {comprobarComponente($ID, "buzzer");} argumentos {/*TODO:*/} {new Quintupla($G,$ID,pilasArgumento.pop(),pilasArgumento.pop());
			}
		| G = 'escribir' {comprobarComponente($ID, "display_lcd");} parExpresion {resolverExpresion(t -> t==CADENA | t == TD_CADENA, "TD_CADENA o CADENA");
			} {new Quintupla($G,$ID,salida.peek(),null);}
		| G = 'girar' {comprobarComponente($ID, "servo");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			} {new Quintupla($G,$ID,salida.peek(),null);}
		| G = 'avanzar' {comprobarComponente($ID, "motor");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			} {new Quintupla($G,$ID,salida.peek(),null);}
		| G = 'detectar' {comprobarComponente($ID, "sensor_distancia");} parExpresion {resolverDetectar(t);
			} {new Quintupla($G,$ID,salida.peek(),null);}
		| G = 'detener' {comprobarComponente($ID, "motor");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			} {new Quintupla($G,$ID,salida.peek(),null);}
		| G = 'encender' {comprobarComponente($ID, "led");} '(' ')' {new Quintupla($G,$ID,null,null);
			}
		| G = 'apagar' {comprobarComponente($ID, "led");} '(' ')' {new Quintupla($G,$ID,null,null);}
		| G = 'mostrar' {comprobarComponente($ID, "siete_segmentos");} parExpresion {resolverExpresion(t -> t==ENTERO | t == TD_ENTERO, "TD_ENTERO o ENTERO");
			} {new Quintupla($G,$ID,salida.peek(),null);}
	) ')';
esperar:
	E = 'esperar' {nuevaExpresion();} parExpresion {resolverExpresion(t -> t == TD_ENTERO | t == ENTERO,
	 "ENTERO o TD_ENTERO");} {new Quintupla($E,salida.peek(),null,null);};
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