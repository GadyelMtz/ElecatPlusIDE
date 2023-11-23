package Analizadores;

import static Analizadores.SimpleParser.*;

import java.awt.Component;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.SemanticContext.PrecedencePredicate;

class Funcion {
    String nombre;
    ArrayList<Token> parametros;

    public Funcion(String nombre, ArrayList<Token> parametros) {
        this.nombre = nombre;
        this.parametros = parametros;
    }
}

public class SimpleSemantic {
    public SimpleSemantic() {
        variablesDeclaradas = new HashMap<>();
        funcionesDeclaradas = new ArrayList<>();
        listaParametros = new ArrayList<>();
        nombreParametros = new HashSet<>();
        salida = new Stack<>();
        pilaOperadores = new Stack<>();
        pilas = new ArrayList<>();
    }

    public static final ArrayList<Predicate<Integer>> retornoExpresion = new ArrayList<>();
    static {
        retornoExpresion.add(t -> t == BOOLEANO | t == TD_BOOLEANO);
        retornoExpresion.add(t -> t == CADENA | t == TD_CADENA);
        retornoExpresion.add(t -> t == ENTERO | t == TD_ENTERO);
        retornoExpresion.add(t -> t == DECIMAL | t == TD_DECIMAL);
    }

    public static Predicate<Integer> retorno(int tipo) {
        for (Predicate<Integer> p : retornoExpresion) {
            if (p.test(tipo))
                return p;
        }
        return null;
    }

    public static Map<String, Token> variablesDeclaradas = new HashMap<>();
    public static ArrayList<Funcion> funcionesDeclaradas = new ArrayList<>();
    public static ArrayList<Token> listaParametros = new ArrayList<>();
    public static ArrayList<String> pilas = new ArrayList<>();
    public static Set<String> nombreParametros = new HashSet<>();
    public static Stack<Token> salida = new Stack<>();
    public static Stack<Token> pilaOperadores = new Stack<>();
    public static boolean banderaRetorno = false;
    public static boolean banderaSwitch = false;

    public static Token t;
    private static ANTLRErrorListener listener = new ConsoleErrorListener() {
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            System.err.println("Linea " + line + ":" + charPositionInLine + "; Error semantico: " + msg + ";");
        }
    };
    final Set<Integer> booleanos = new HashSet<Integer>(Arrays.asList(TD_BOOLEANO, BOOLEANO));
    final Set<Integer> enteros = new HashSet<>(Arrays.asList(TD_ENTERO, ENTERO));
    static final Set<Integer> decimales = new HashSet<>(Arrays.asList(DECIMAL, TD_DECIMAL));
    static final Set<String> operadores = new HashSet<>(
            Arrays.asList("and", "or", "==", "!=", "<", "<=", ">", ">=", "/", "*", "-", "+"));
    static final Set<Integer> literales = new HashSet<>(Arrays.asList(DECIMAL, CADENA, BOOLEANO, ENTERO));
    static final Set<Integer> tdVariables = new HashSet<>(Arrays.asList(TD_DECIMAL, TD_ENTERO, TD_CADENA, TD_BOOLEANO));
    static Integer retornoFuncion = -1;
    static Integer regresarTipoDato;
    static Integer td_switch;
    static Integer td_variable;

    static void resolverDetectar(Token t){
        if(cantidadEnPila()!=1){
            semanticError(t, "se esperaba sólo un identificador ENTERO.");
        }
        if (salida.peek().getType()!=TD_ENTERO){
            semanticError(t, "se esperaba un identificador TD_ENTERO y no " + SimpleParser.VOCABULARY.getSymbolicName(t.getType()) +".");
        }
    }

    private static int cantidadEnPila() {
        return salida.size() + operadores.size();
    }

    public static void usarFuncion(Token funcion, ArrayList<Token> lista) {
        // try {
        // ArrayList<Token> parametros = funcionesDeclaradas.get(funcion.getText());
        // if (parametros == null)
        // throw new Exception();
        // for (int i = 0; i < parametros.size(); i++) {
        // if (!parametros.get(i).getText().equals(lista.get(i).getText()))
        // throw new Exception();
        // }
        // } catch (Exception e) {
        // errorFuncion(funcion, lista, " no ha sido declarada");
        // }
    }

    static void nuevaExpresion() {
        pilaOperadores = new Stack<>();
        salida = new Stack<>();
    };

    static void imprimirPila(Stack<Token> xdd, Token inicial) {
        StringBuilder xd = new StringBuilder();
        xd.append("Linea " + inicial.getLine() + ":" + inicial.getCharPositionInLine() + "[");
        xdd.forEach(t -> xd.append("[" + t.getText() + "]"));
        xd.append("]");
        pilas.add(xd.toString());
    }

    /**
     * La función "nuevaFuncion" restablece los parámetros, el valor de retorno y el
     * indicador de
     * retorno de una función.
     */
    public static void nuevaFuncion() {
        listaParametros = new ArrayList<>();
        nombreParametros.clear();
        retornoFuncion = -1;
        banderaRetorno = false;
    }

    public static void nuevoSwitch() {
        nuevaExpresion();
        banderaSwitch = false;
    }

    /**
     * La función comprueba si hay una devolución inesperada de datos.
     * 
     * @param t El parámetro "t" es de tipo Token.
     */
    public static void comprobarRetorno(Token t) {
        if (retornoFuncion == -1)
            semanticError(t, "no se esperaba retorno de datos.");
        banderaRetorno = true;
    }

    /**
     * La función comprueba si un token determinado está dentro de una función o no.
     * 
     * @param t El token t es el token que activó la verificación.
     * @param c El parámetro "c" es de tipo ParserRuleContext, que es una clase que
     *          representa un
     *          contexto de regla en un analizador. Se utiliza para representar el
     *          contexto de una regla
     *          específica en la gramática.
     */
    public static void comprobarPadre(Token t, ParserRuleContext c) {
        ParserRuleContext p = null;
        while ((p = c.getParent()) != null) {
            if (p.getRuleIndex() == RULE_funcion)
                return;
        }
        semanticError(t, "devolver no se encuentra en una función.");
    }

    int obtenerResultadoPila() {
        return salida.peek().getType();
    }

    // TODO: Ponerle la bandera de switch en true si se resuelve con éxito.
    static boolean resolverPila(Predicate<Integer> esperado) {
        // 1 devolver,Igual al que devuelve la función. V V
        resolverPila(t -> t == retornoFuncion);
        // 2 elegir ,Un tipo de dato, es decir, una variable. V V
        resolverPila(t -> tdVariables.contains(t));
        // 3 repetir mientras ,Booleano. V V
        resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO);
        // 4 si Booleano o TD_Booleano. V V
        resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO);
        // 5 controlFor Booleano o TD_Booleano. V V
        resolverPila(t -> t == BOOLEANO | t == TD_BOOLEANO);
        // 6 etiquetaSwitch El mismo tipo de dato que 'elegir'. V V
        resolverPila(t -> retorno(td_switch).test(t));
        // 7 declaracionDeVariable El mismo tipo de dato de la variable. V V
        resolverPila(t -> retorno(td_variable).test(t));
        // 8 escribir Cadena o TD_CADENA. V V
        resolverPila(t -> t == TD_CADENA | t == CADENA);
        // 9 girar ENTERO o TD_ENTERO. V V
        resolverPila(t -> t == TD_ENTERO | t == ENTERO);
        // 10 avanzar ENTERO o TD_ENTERO. V V
        resolverPila(t -> t == TD_ENTERO | t == ENTERO);
        // 11 detectar TD_ENTERO. V V
        resolverPila(t -> t == TD_ENTERO);
        // 12 detener TD_ENTERO o ENTERO. V V
        resolverPila(t -> t == TD_ENTERO | t == ENTERO);
        // 13 esperar TD_ENTERO o ENTERO. V V
        resolverPila(t -> t == ENTERO | t == TD_ENTERO);
        // 14 expresión El mismo tipo de dato de la variable
        resolverPila(t -> retorno(td_variable).test(t));

        // 0. Sacar el resto de operadores de la pila
        while (!pilaOperadores.empty()) {
            salida.push(pilaOperadores.pop());
        }
        imprimirPila(salida, salida.peek());
        // if (salida.size() == 1) {
        // Token t = salida.pop();
        // return tipos.contains(t.getType());
        // }
        // Stack<Token> t = new Stack<>();
        // while (!salida.empty()) {
        // if (!(salida.peek().getType() >= OP_LOGICO && salida.peek().getType() <=
        // OP_ARITMETICO)) {
        // t.push(salida.pop());
        // } else {
        // try {
        // t.push(validarOperacion(t.pop(), t.pop(), salida.pop()));
        // } catch (Exception e) {
        // return false;
        // }
        // }
        // }
        // if (tipos.size() == 1)
        // switch (t.pop().getType()) {
        // case DECIMAL:
        // return decimales.contains(tipos.toArray()[0]);
        // case ENTERO:
        // return enteros.contains(tipos.toArray()[0]);
        // case BOOLEANO:
        // return booleanos.contains(tipos.toArray()[0]);
        // default:
        // break;
        // }
        // return tipos.contains(t.pop().getType());
    }

    private Token validarOperacion(Token operando1, Token operando2, Token operador) throws Exception {
        // try {
        // if (operador.getType() == OP_LOGICO) {
        // if (!booleanos.contains(operando1.getType()) ||
        // !booleanos.contains(operando2.getType())) {
        // throw new Exception();
        // }
        // return new CommonToken(BOOLEANO, "booleano");
        // } else if (operador.getType() == OP_ARITMETICO || operador.getType() ==
        // OP_COMPARADOR) {
        // byte d = 0;
        // d += decimales.contains(operando1.getType()) ? 1 : 0;
        // d += decimales.contains(operando2.getType()) ? 2 : 0;
        // switch (d) {
        // case 0:
        // if (!enteros.contains(operando1.getType()) &&
        // !enteros.contains(operando2.getType()))
        // throw new Exception();
        // return new CommonToken(operador.getType() == OP_ARITMETICO
        // ? (operador.getText().equals("/")) ? DECIMAL : ENTERO
        // : BOOLEANO, "valor");
        // case 1:
        // if (!enteros.contains(operando2.getType()))
        // throw new Exception();
        // break;
        // case 2:
        // if (!enteros.contains(operando1.getType()))
        // throw new Exception();
        // break;
        // default:
        // break;
        // }
        // return new CommonToken(operador.getType() == OP_ARITMETICO ? DECIMAL :
        // BOOLEANO, "valor");
        // }
        // } catch (Exception e) {
        // String err = "Operación inválida: '" + operando1.getText() + " " +
        // operador.getText() + " "
        // + operando2.getText() + "'";
        // err += "\nTipos de dato incompatibles: '" + operando1.getType() + " " +
        // operador.getText() + " "
        // + operando2.getType() + "'";
        // err += "\nLínea: " + operador.getLine() + ", " +
        // operador.getCharPositionInLine();
        // System.out.println(err);
        // throw e;
        // }
        return null;
    }

    /**
     * La función comprueba si un parámetro ya ha sido declarado y arroja un error
     * semántico si es un
     * duplicado.
     * 
     * @param parametro El parámetro "parametro" es de tipo "Token".
     */
    public static void parametrosDeclarados(Token parametro) {
        String nombreParametro = parametro.getText();
        if (!nombreParametros.contains(nombreParametro)) {
            nombreParametros.add(parametro.getText());
            return;
        }
        String msg = "el parametro: '" + parametro.getText() + "' se encuentra duplicado";
        semanticError(parametro, msg);
    }

    /**
     * La función "parametrosIguales" comprueba si dos ArrayLists de Tokens tienen
     * los mismos elementos
     * en el mismo orden.
     * 
     * @param lista1 Un ArrayList de objetos Token llamado lista1.
     * @param lista2 El parámetro "lista2" es un ArrayList de objetos Token.
     * @return El método devuelve un valor booleano.
     */
    private static boolean parametrosIguales(ArrayList<Token> lista1, ArrayList<Token> lista2) {
        for (int i = 0; i < lista1.size(); i++) {
            if (!lista1.get(i).equals(lista2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * La función comprueba si ya se ha declarado una función con el mismo nombre y
     * parámetros y, en
     * caso contrario, declara la función.
     * 
     * @param funcion El parámetro "funcion" es de tipo Token, que representa un
     *                token en el código. Se
     *                utiliza para representar el nombre de la función que se
     *                declara.
     * @param lista   El parámetro "lista" es un ArrayList de objetos Token.
     */
    public static void funcionDeclarada(Token funcion, ArrayList<Token> lista) {
        // 1. Encontrar funciones iguales, mismo nombre, mismos parámetros en el mismo
        // orden
        boolean existeFuncion = funcionesDeclaradas.stream()
                .anyMatch(t -> t.nombre.equals(funcion.getText())
                        && t.parametros.size() == lista.size()
                        && parametrosIguales(t.parametros, lista));

        // 2. Si las hay, manejar el error
        if (existeFuncion) {
            errorFuncion(funcion, lista, " ya ha sido declarada");
        } else {
            // 3. Si no existe, declarar la función
            funcionesDeclaradas.add(new Funcion(funcion.getText(), lista));
        }
    }

    /**
     * La función `errorFuncion` genera un mensaje de error basado en el token de
     * función dado, la
     * lista de tokens y un predicado, y luego llama a la función `semanticError`
     * con el mensaje de
     * error generado.
     * 
     * @param funcion   Un Token que representa el nombre de la función.
     * @param lista     Una ArrayList de objetos Token que representan los
     *                  parámetros de la función.
     * @param predicado El parámetro "predicado" es una cadena que representa el
     *                  mensaje de error o
     *                  descripción del error ocurrido en la función.
     */
    private static void errorFuncion(Token funcion, ArrayList<Token> lista, String predicado) {
        String message = "la funcion ";
        switch (lista.size()) {
            case 0:
                message += String.format("%s() %s", funcion.getText(), predicado);
                break;
            case 1:
                message += String.format("%s %s() %s", lista.get(0).getText(), funcion.getText(), predicado);
            default:
                StringBuilder paramsBuilder = new StringBuilder();
                boolean[] ban = { false }; // Esto es una forma de simular una variable mutable
                lista.forEach(t -> {
                    if (ban[0]) {
                        paramsBuilder.append(t.getText()).append(",");
                    } else {
                        ban[0] = true;
                    }
                });
                String params = paramsBuilder.toString().substring(0, paramsBuilder.length() - 1);
                message += String.format("%s %s(%s) %s", funcion.getText(), lista.get(0).getText(), params, predicado);
                break;
        }
        semanticError(funcion, message);
    }

    /**
     * La función `semanticError` se utiliza para informar un error semántico con un
     * mensaje y una
     * ubicación específicos.
     * 
     * @param variable El parámetro variable es de tipo Token y representa el token
     *                 que provocó el
     *                 error semántico.
     * @param msg      El parámetro "msg" es una cadena que representa el mensaje de
     *                 error o la descripción
     *                 del error semántico. Se utiliza para proporcionar más
     *                 información sobre el error ocurrido.
     */
    private static void semanticError(Token variable, String msg) {
        listener.syntaxError(null, null, variable.getLine(), variable.getCharPositionInLine(), msg, null);
    }

    /**
     * La función "añadirAPila" agrega un token a una pila según su tipo y maneja
     * operadores y
     * paréntesis en consecuencia.
     * 
     * @param token El parámetro "token" es de tipo Token, que representa un token
     *              en un lenguaje de
     *              programación. Contiene información como el tipo de token y el
     *              texto.
     */
    public static void añadirAPila(Token token) {
        try {
            // Salir si el token es nulo
            if (token == null)
                return;
            int tipoToken = token.getType();
            // Agregar a la pila según el tipo del token
            if (tipoToken == ID) {
                salida.push(variablesDeclaradas.get(token.getText()));
            } else if (literal(tipoToken)) {
                salida.push(token);
            } else if (operador(token.getText())) {
                // Lógica para operadores
                manejarOperadores(token);
            } else if (tipoToken == LPAREN) {
                pilaOperadores.push(token);
            } else if (tipoToken == RPAREN) {
                manejarParentesis();
            }
        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error en añadirAPila: " + e.getMessage());
        }
    }

    /**
     * La función comprueba si un tipo de Token determinado es literal.
     * 
     * @param tipo El parámetro "tipo" es un número entero que representa un tipo.
     * @return El método devuelve un valor booleano.
     */
    private static boolean literal(int tipo) {
        return literales.contains(tipo);
    }

    /**
     * La función comprueba si un texto determinado es un operador.
     * 
     * @param text El parámetro "texto" es una cadena que representa un operador.
     * @return El método devuelve un valor booleano.
     */
    private static boolean operador(String text) {
        return operadores.contains(text);
    }

    /**
     * La función "manejarOperadores" maneja operadores comparando su precedencia y
     * empujándolos a la
     * pila apropiada.
     * 
     * @param token El parámetro "token" es un objeto de tipo "Token" que representa
     *              el operador.
     */
    private static void manejarOperadores(Token token) {
        while (!pilaOperadores.empty()
                && precedencia(pilaOperadores.peek().getText()) >= precedencia(token.getText())) {
            salida.push(pilaOperadores.pop());
        }
        pilaOperadores.push(token);
    }

    /**
     * La función "precedencia" devuelve el nivel de precedencia de un operador u
     * operador lógico
     * determinado.
     * 
     * @param text El parámetro "texto" es una cadena que representa un operador en
     *             una expresión.
     * @return El método devuelve un valor entero que representa la precedencia del
     *         texto dado.
     */
    private static int precedencia(String text) {
        switch (text) {
            case "or":
                return 1;
            case "and":
                return 2;
            case "==":
            case "!=":
                return 3;
            case "<":
            case ">":
            case "<=":
            case ">=":
                return 4;
            case "+":
            case "-":
                return 5;
            case "*":
            case "/":
                return 6;
        }
        return -1;
    }

    /**
     * La función "manejarParentesis" extrae operadores de una pila hasta que
     * encuentra un paréntesis
     * izquierdo y luego extrae el paréntesis izquierdo de la pila.
     */
    private static void manejarParentesis() {
        while (!pilaOperadores.empty() && pilaOperadores.peek().getType() != LPAREN) {
            salida.push(pilaOperadores.pop());
        }
        if (!pilaOperadores.empty() && pilaOperadores.peek().getType() == LPAREN) {
            pilaOperadores.pop();
        }
    }

    /**
     * La función comprueba si una variable ya ha sido declarada y arroja un error
     * semántico si es así.
     * 
     * @param variable     Un token que representa la variable que se declara.
     * @param tipoVariable El parámetro tipoVariable representa el tipo de variable
     *                     que se declara. Es
     *                     un objeto Token que contiene información sobre el tipo,
     *                     como su representación de texto y número
     *                     de línea.
     */
    public static void variableDeclarada(Token variable, Token tipoVariable) {
        if (!variablesDeclaradas.containsKey(variable.getText())) {
            variablesDeclaradas.put(variable.getText(), tipoVariable);
            return;
        }
        String text = variable.getText();
        Token declarada = variablesDeclaradas.get(variable.getText());
        semanticError(variable, "la variable: '" + text + "' ya ha sido declarada en linea: " + declarada.getLine());
    }

    /**
     * La función comprueba si se ha declarado una variable y arroja un error
     * semántico si no es así.
     * 
     * @param variable El parámetro "variable" es de tipo "Token".
     */
    public static void usarVariable(Token variable) {
        if (!variablesDeclaradas.containsKey(variable.getText()))
            semanticError(variable, "la variable " + variable.getText() + " no ha sido declarada");
    }
}