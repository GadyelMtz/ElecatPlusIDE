parser grammar SimpleParser;
options { tokenVocab = SimpleLexer; }
@parser::header {
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
}
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
        
        Stack<Token> invertido = new Stack<>();
        while (!salida.empty()) {
            invertido.push(salida.pop());
        }
        salida = invertido;
    }
    boolean operando(int tipo){
        return booleanos.contains(tipo) || enteros.contains(tipo) || decimales.contains(tipo);
    }
    
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
    
    
    boolean variableDeclarada(Token variable, Token tipoVariable) {
        if (!isDeclaration)
            return false;
        String nombreVariable = variable.getText();
        if (variablesDeclaradas.containsKey(nombreVariable))
            return true;
        variablesDeclaradas.put(nombreVariable, tipoVariable);
        return false;
    }
    
    
    boolean usarVariable(Token variable) {
        return variablesDeclaradas.containsKey(variable.getText());
    }
    
}
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
esperar:
	ESPERAR PAR_ABRIR numero (COMA TIEMPO)? PAR_CERRAR FIN_LINEA;
girar: GIRAR PAR_ABRIR numero COMA DIRECCION PAR_CERRAR;
escribir: ESCRIBIR PAR_ABRIR (ID | CADENA | numero) PAR_CERRAR;
termino:
	(t = BOOLEANO | t = ID { usarVariable($t) }?) { añadirAPila($t); }
	| numero {añadirAPila(t);}
	| t = PAR_ABRIR { añadirAPila($t); } expresion t = PAR_CERRAR { añadirAPila($t); };
expresion: termino operacionBinaria*;
operacionBinaria: (op_bin { añadirAPila(t); } termino);
numero: (t = DECIMAL | t = ENTERO) {t=$t;};
op_bin: (t = OP_LOGICO | t = OP_COMPARADOR | t = OP_ARITMETICO) {t=$t;};
tipo_dato:
	(t = TD_DECIMAL
	| t = TD_ENTERO
	| t = TD_CARACTER
	| t = TD_CADENA
	| t = TD_BOOLEANO) {t=$t;};