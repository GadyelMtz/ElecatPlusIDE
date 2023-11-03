package consola;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.io.File;
import java.io.IOException;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import Analizadores.SimpleLexer;
import Analizadores.SimpleParser;

public class Main {
    private static final int NUMERO = 0;
    private static final int BOOLEANO = 0;
    private static final int ID = 0;
    private static final int OP_BIN = 0;
    private static final int PAR_ABRIR = 0;
    private static final int PAR_CERRAR = 0;
    private static final int TD_BOOLEANO = 0;
    private static final int TD_ENTERO = 0;
    private static final int ENTERO = 0;
    private static final int TD_DECIMAL = 0;
    private static final int DECIMAL = 0;
    private static final int OP_ARITMETICO = 0;
    private static final int OP_LOGICO = 0;
    private static final int OP_COMPARADOR = 0;
    // Tabla de símbolos para realizar un seguimiento de las variables declaradas
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
            if (salida.peek().getType() >= OP_LOGICO && salida.peek().getType() <= OP_ARITMETICO) {
                t.push(salida.pop());
            } else {
                try {
                    t.push(validarOperacion(t.pop(), t.pop(), salida.pop()));
                } catch (Exception e) {
                    return false;
                }
            }
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
    static int solicitarPin() throws Exception {
        for (int i = 0; i < pines.length; i++) {
            if (!pines[i]) {
                pines[i] = true;
                return i + 1;
            }
        }
        throw new Exception("No hay más pines disponibles");
    }

    static boolean pines[] = new boolean[13];

    public static void main(String[] args) {
        try {
            // antlr4 -no-listener -no-visitor -Dlanguage=Java -package=
            // Simple.g4
            CharStream input = CharStreams.fromFileName(new File("ElecatPlusIDE/src/Prueba.ecp").getAbsolutePath());
            // Leer código fuente

            SimpleLexer lexer = new SimpleLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);
            System.out.println("");
            ParseTree tree = parser.programa(); // Comienza el análisis desde la regla expr

            // Prueba de PINES
            try {
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
                solicitarPin();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            if (e instanceof IOException)
                System.out.println("El archivo no fué encontrado" + e);
        }
    }
}

// 0) #gBUz7HrH8D# = ( f ) ;
// 0) #z_tVGyD1zNx9# = f ;
// 0) #F1R# = 1492517506 * #iQoIuNaNr# ;
// 0) #JsRZm3eF# = #sxU4z_wiy# ;
// 0) #w# = v or ( ( 8.8909702 ) or #cmM7RUy# ) ;
// 0) #E54km0b# = #I2rcABOVu0q# and .7 ;