package Analizadores;

import static Analizadores.SimpleParser.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import static javax.swing.JOptionPane.showMessageDialog;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class SimpleSemantic {
    public static Map<String, Token> variablesDeclaradas = new HashMap<>();
    public static Map<String, ArrayList<Token>> funcionesDeclaradas = new HashMap<>();
    public static ArrayList<Token> listaParametros = new ArrayList<>();
    public static Set<String> nombreParametros = new HashSet<>();
    private static final int PAR_ABRIR = 0;
    private static final int PAR_CERRAR = 0;
    private static final int OP_ARITMETICO = 0;
    private static final int OP_COMPARADOR = 0;
    private static final int OP_LOGICO = 0;
    Stack<Token> salida = new Stack<>();
    Stack<Token> pilaOperadores = new Stack<>();
    boolean isDeclaration = false;
    public static Token t;
    private static ANTLRErrorListener listener = new ConsoleErrorListener() {
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            System.err.println("Linea " + line + ":" + charPositionInLine + "; Error semantico: " + msg + ";");
        }
    };
    final Set<Integer> booleanos = new HashSet<Integer>(Arrays.asList(TD_BOOLEANO, BOOLEANO));
    final Set<Integer> enteros = new HashSet<>(Arrays.asList(TD_ENTERO, ENTERO));
    final Set<Integer> decimales = new HashSet<>(Arrays.asList(DECIMAL, TD_DECIMAL));
    final Set<Integer> operadores = new HashSet<>(Arrays.asList(OP_ARITMETICO, OP_COMPARADOR, OP_LOGICO));

    public static void usarFuncion(Token funcion, ArrayList<Token> lista) {
        try {
            ArrayList<Token> parametros = funcionesDeclaradas.get(funcion.getText());
            if(parametros==null)
                throw new Exception();
            for (int i = 0; i < parametros.size(); i++) {
                if(!parametros.get(i).getText().equals(lista.get(i).getText()))
                    throw new Exception();
            }
        } catch (Exception e) {
            errorFuncion(funcion, lista, " no ha sido declarada");
        }
    }

    static void parametrosDeclarados(Token parametro) {
        try {
            String nombreParametro = parametro.getText();
            if (nombreParametros.contains(nombreParametro))
                throw new Exception();
            nombreParametros.add(parametro.getText());
        } catch (Exception e) {
            String msg = "el parametro: '" + parametro.getText() + "' se encuentra duplicado";
            semanticError(parametro, msg);
        }
    }

    static void funcionDeclarada(Token funcion, ArrayList<Token> lista) {
        try {
            if (funcionesDeclaradas.containsKey(funcion.getText())) {
                ArrayList<Token> listaExistente = funcionesDeclaradas.get(funcion.getText());
                if (listaExistente.size() == lista.size()) {
                    if (lista.size() != 0)
                        for (int i = 0; i < lista.size(); i++) {
                            if (!lista.get(i).getText().equals(listaExistente.get(i).getText()))
                                return;
                        }
                    // HashSet<String> tiposDato = new HashSet<>();
                    // for (Token[] tok : lista) {
                    // tiposDato.add(tok[0].getText());
                    // }
                    // int cuentaLN[] = new int[tiposDato.size()];
                    // int cuentaLE[] = new int[tiposDato.size()];
                    // for (int i = 0; i < tiposDato.size(); i++) {
                    // for(Token[] tok : lista){
                    // cuentaLE[i] += tok[0].equals(tiposDato.toArray()[i]) ? 1 : 0;
                    // }
                    // for(Token[] tok : listaExistente){
                    // cuentaLE[i] += tok[0].equals(tiposDato.toArray()[i]) ? 1 : 0;
                    // }
                    // if (cuentaLE[i]!=cuentaLN[i])
                    // return;
                    // }
                    throw new Exception();
                }
            }
            funcionesDeclaradas.put(funcion.getText(), lista);
        } catch (Exception e) {
            errorFuncion(funcion, lista, " ya ha sido declarada");
        }
    }

    private static void errorFuncion(Token funcion, ArrayList<Token> lista, String predicado) {
        String params = "(";
        boolean b = false;
        boolean c = false;
        for (Token tokens : lista) {
            if (c) {
                params += tokens.getText() + ",";
                if (!b)
                    b = true;
            } else {
                c = true;
            }
        }
        if (b)
            params = params.substring(0, params.length() - 1) + ")";
        else
            params += ")";
        semanticError(funcion, "la funcion "+ lista.get(0).getText() + funcion.getText() + params + predicado);
    }

    private static void semanticError(Token variable, String msg) {
        listener.syntaxError(null, null, variable.getLine(), variable.getCharPositionInLine(), msg, null);
    }

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
        if (tipos.size() == 1)
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
                        return new CommonToken(operador.getType() == OP_ARITMETICO
                                ? (operador.getText().equals("/")) ? DECIMAL : ENTERO
                                : BOOLEANO, "valor");
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

    boolean operando(int tipo) {
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

    static void variableDeclarada(Token variable, Token tipoVariable) {
        try {
            String nombreVariable = variable.getText();
            if (variablesDeclaradas.containsKey(nombreVariable))
                throw new Exception();
            variablesDeclaradas.put(nombreVariable, tipoVariable);
        } catch (Exception e) {
            String text = variable.getText();
            Token token = variablesDeclaradas.get(variable.getText());
            String msg = "la variable: '" + text + "' ya ha sido declarada en linea: " + token.getLine();
            semanticError(variable, msg);
        }
    }

    public static void usarVariable(Token variable) {
        try {
            if (!variablesDeclaradas.containsKey(variable.getText()))
                throw new Exception();
        } catch (Exception e) {
            semanticError(variable, "la variable " + variable.getText() + " no ha sido declarada");
        }
    }
    

}