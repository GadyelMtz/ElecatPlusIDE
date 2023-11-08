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
@parser::members {
    final Map<String, Token> variablesDeclaradas = new HashMap<>();
    Stack<Token> salida = new Stack<>();
    Stack<Token> pilaOperadores = new Stack<>();
    boolean isDeclaration = false;
    Token t;
    final Set<Integer> booleanos = new HashSet<Integer>(Arrays.asList(TD_BOOLEANO, BOOLEANO));
    final Set<Integer> enteros = new HashSet<>(Arrays.asList(TD_ENTERO, ENTERO));
    final Set<Integer> decimales = new HashSet<>(Arrays.asList(DECIMAL, TD_DECIMAL));
    final Set<Integer> operadores = new HashSet<>(Arrays.asList(OP_ARITMETICO, OP_COMPARADOR, OP_LOGICO));

    // #region CódigoSemántico
    void imprimirPila(Stack<Token> xdd) {
        Stack<Token> x = (Stack<Token>) xdd.clone();
        String xd = "";
        while (!x.empty()) {
            xd += x.pop().getText() + " ";
        }
        System.out.println(xd);
    }

    boolean resolverPila(HashSet<Integer> tipos) {
        invertirSalida();
        imprimirPila(salida);
        // Para leer de izquierda a derecha y no de derecha a izquierda (como llega)
        if (salida.size() == 1) {
            Token t = salida.pop();
            return tipos.contains(t.getType());
        }
        Stack<Token> t = new Stack<>();
        while (!salida.empty()) {
            if (!(salida.peek().getType() >= OP_LOGICO && salida.peek().getType() <= OP_ARITMETICO)) {
                t.push(salida.pop());
                imprimirPila(t);
            } else {
                try {
                    t.push(validarOperacion(t.pop(), t.pop(), salida.pop()));
                    imprimirPila(t);
                } catch (Exception e) {
                    return false;
                }
            }
        }
        if (tipos.size()==1)
        switch (t.pop().getType()) {
            case DECIMAL:
                return decimales.contains(tipos.toArray()[0]);
            case ENTERO:
                return enteros.contains(tipos.toArray()[0]);
            case BOOLEANO:
                return booleanos.contains(tipos.toArray()[0]);
            default:
                break;
        }
        return tipos.contains(t.pop().getType());
    }

    private Token validarOperacion(Token operando1, Token operando2, Token operador) throws Exception {
        try {
            if (operador.getType() == OP_LOGICO) {
                if (!booleanos.contains(operando1.getType()) || !booleanos.contains(operando2.getType())) {
                    throw new Exception();
                }
                return new CommonToken(BOOLEANO, "booleano");
            } else if (operador.getType() == OP_ARITMETICO || operador.getType() == OP_COMPARADOR) {
                byte d = 0;
                d += decimales.contains(operando1.getType()) ? 1 : 0;
                d += decimales.contains(operando2.getType()) ? 2 : 0;
                switch (d) {
                    case 0:
                        if (!enteros.contains(operando1.getType()) && !enteros.contains(operando2.getType()))
                            throw new Exception();
                        return new CommonToken(operador.getType() == OP_ARITMETICO ? (operador.getText().equals("/"))? DECIMAL : ENTERO : BOOLEANO, "valor");
                    case 1:
                        if (!enteros.contains(operando2.getType()))
                            throw new Exception();
                        break;
                    case 2:
                        if (!enteros.contains(operando1.getType()))
                            throw new Exception();
                        break;
                    default:
                        break;
                }
                return new CommonToken(operador.getType() == OP_ARITMETICO ? DECIMAL : BOOLEANO, "valor");
            }
        } catch (Exception e) {
            String err = "Operación inválida: '" + operando1.getText() + " " + operador.getText() + " "
                    + operando2.getText() + "'";
            err += "\nTipos de dato incompatibles: '" + operando1.getType() + " " + operador.getText() + " "
                    + operando2.getType() + "'";
            err += "\nLínea: " + operador.getLine() + ", " + operador.getCharPositionInLine();
            System.out.println(err);
            throw e;
        }
        return null;
    }

    private void invertirSalida() {
        while (!pilaOperadores.empty()) {
            salida.push(pilaOperadores.pop());
        }
        // Sacar el resto de operadores de la pila
        Stack<Token> invertido = new Stack<>();
        while (!salida.empty()) {
            invertido.push(salida.pop());
        }
        salida = invertido;
    }

    boolean operando(int tipo){
        return booleanos.contains(tipo) || enteros.contains(tipo) || decimales.contains(tipo);
    }

    // Funcion para añadir a la pila semántica
    void añadirAPila(Token t) {
        if (t == null)
            return;
        int type = t.getType();
        if (type == ID) {
            salida.push(variablesDeclaradas.get(t.getText()));
        } else if (operando(type)) {
            salida.push(t);
        } else if (operadores.contains(type)) {
            while (!pilaOperadores.empty()
                    && precedencia(pilaOperadores.peek().getText()) >= precedencia(t.getText())) {
                salida.push(pilaOperadores.pop());
            }
            pilaOperadores.push(t);
        } else if (type == PAR_ABRIR) {
            pilaOperadores.push(t);
        } else if (type == PAR_CERRAR) {
            while (!pilaOperadores.empty() && pilaOperadores.peek().getType() != PAR_ABRIR) {
                salida.push(pilaOperadores.pop());
            }
            if (!pilaOperadores.empty() && pilaOperadores.peek().getType() == PAR_ABRIR)
                pilaOperadores.pop();
        }
    }

    private int precedencia(String text) {
        switch (text) {
            case "+":
            case "-":
            case "or":
            case "==":
            case "!=":
            case "<":
            case "<=":
            case ">":
            case ">=":
                return 1;
            case "*":
            case "/":
            case "and":
                return 2;
            default:
                return -1;
        }
    }

    // Predicado semántico para verificar si una variable se ha declarado al
    // intentar declararla
    boolean variableDeclarada(Token variable, Token tipoVariable) {
        if (!isDeclaration)
            return false;
        String nombreVariable = variable.getText();
        if (variablesDeclaradas.containsKey(nombreVariable))
            return true;
        variablesDeclaradas.put(nombreVariable, tipoVariable);
        return false;
    }

    // Predicado semántico para verificar si una variable se ha declarado al
    // intentar declararla
    boolean usarVariable(Token variable) {
        return variablesDeclaradas.containsKey(variable.getText());
    }
    // #endregion
}

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