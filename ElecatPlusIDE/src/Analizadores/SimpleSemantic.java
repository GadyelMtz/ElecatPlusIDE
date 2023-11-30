package Analizadores;

import static Analizadores.SimpleParser.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import static Analizadores.SimpleCode.Quintupla;
import static Analizadores.SimpleCode.quintuplas;

@FunctionalInterface
interface SymbolicName {
    String getSymbolicName(int tokenType);
}

class Funcion {
    String nombre;
    int retorno;
    ArrayList<Token> parametros;

    public Funcion(String nombre, int retorno, ArrayList<Token> parametros) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.retorno = retorno;
    }
}

public class SimpleSemantic {
    public static String nombrePrograma;
    public static final ArrayList<Predicate<Integer>> retornoExpresion = new ArrayList<>();
    static {
        retornoExpresion.add(t -> t == BOOLEANO | t == TD_BOOLEANO);
        retornoExpresion.add(t -> t == CADENA | t == TD_CADENA);
        retornoExpresion.add(t -> t == ENTERO | t == TD_ENTERO);
        retornoExpresion.add(t -> t == DECIMAL | t == TD_DECIMAL);
    }
    public static Map<String, Token> variablesDeclaradas = new HashMap<>();
    public static ArrayList<Funcion> funcionesDeclaradas = new ArrayList<>();
    public static ArrayList<Token> listaParametros = new ArrayList<>();
    public static ArrayList<String> pilas = new ArrayList<>();
    public static Stack<Token> pilasArgumento = new Stack<>();
    public static Set<String> nombreParametros = new HashSet<>();
    public static Stack<Token> salida = new Stack<>();
    public static Stack<Token> pilaOperadores = new Stack<>();
    public static boolean banderaRetorno = false;
    public static boolean banderaSwitch = false;
    private static boolean puedeResolverPila = true;
    public static Token t;
    public static Token td;
    private static ANTLRErrorListener listener = new ConsoleErrorListener() {
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            System.err.println("Linea " + line + ":" + charPositionInLine + "; Error semantico: " + msg + ";");
        }
    };
    public static SymbolicName s = SimpleParser.VOCABULARY::getSymbolicName;
    static final Set<String> operadores = new HashSet<>(
            Arrays.asList("=","and", "or", "==", "!=", "<", "<=", ">", ">=", "/", "*", "-", "+"));
    static final Set<String> operadoresAritmeticos = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
    static final Set<String> operadoresComparadores = new HashSet<>(Arrays.asList("<", ">", "<=", ">="));
    static final Set<String> operadoresBooleanoNumericos = new HashSet<>(
            Arrays.asList("==", "!="));
    static final Set<String> operadoresBooleanos = new HashSet<>(
            Arrays.asList("and", "or"));
    static final Set<Integer> literales = new HashSet<>(Arrays.asList(DECIMAL, CADENA, BOOLEANO, ENTERO));
    static final Set<Integer> tdVariables = new HashSet<>(Arrays.asList(TD_DECIMAL, TD_ENTERO, TD_CADENA, TD_BOOLEANO));
    static Integer retornoFuncion = -1;
    static Integer regresarTipoDato;
    static Integer td_switch;
    static Integer td_variable;
    

    public SimpleSemantic() {
        // TODO:new Quintupla($G,$ID,salida.peek(),null);
        variablesDeclaradas = new HashMap<>();
        funcionesDeclaradas = new ArrayList<>();
        listaParametros = new ArrayList<>();
        nombreParametros = new HashSet<>();
        salida = new Stack<>();
        pilaOperadores = new Stack<>();
        pilas = new ArrayList<>();
        quintuplas = new ArrayList<>();
        Quintupla.linea=0;
    }

    public static void comprobarComponente(Token ID, String componentName) {
        String tipo;
        if (!(tipo = variablesDeclaradas.get(ID.getText()).getText()).equals(componentName)) {
            puedeResolverPila = false;
            semanticError(ID, "acción no válida para variables de tipo " + tipo + "; se esperaba: " + componentName);
        }
    }

    public static void iniciarAccion(Token ID) {
        if (usarVariable(ID)) {
            nuevaExpresion();
            añadirAPila(ID);
            if (resolverExpresion(t -> COMPONENTE == t, "COMPONENTE")) {
                nuevaExpresion();
                puedeResolverPila = true;
            } else {
                puedeResolverPila = false;
            }
        }
    }

    /**
     * La función "retorno" devuelve un Predicado<Integer> basado en el tipo dado.
     * 
     * @param tipo El parámetro "tipo" es un valor entero que representa un tipo.
     * @return El método `retorno` devuelve un objeto `Predicado<Integer>`.
     */
    public static Predicate<Integer> retorno(int tipo) {
        for (Predicate<Integer> p : retornoExpresion) {
            if (p.test(tipo))
                return p;
        }
        return null;
    }

    /**
     * La función "resolverExpresion" toma un predicado y un mensaje como parámetros
     * e intenta resolver
     * una expresión basada en el predicado, mientras maneja las excepciones que
     * puedan ocurrir.
     * 
     * @param predicate El predicado es una interfaz funcional que toma un número
     *                  entero como entrada y
     *                  devuelve un valor booleano. Se utiliza para verificar una
     *                  condición en el número entero de
     *                  entrada.
     * @param message   El parámetro "mensaje" es una cadena que representa un
     *                  mensaje de error.
     */
    public static boolean resolverExpresion(Predicate<Integer> predicate, String message) {
        try {
            if (!resolverPila(predicate)) {
                errorPila(message, salida.peek());
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * La función `resolverAsignacion` comprueba si se puede asignar un valor a una
     * variable y arroja
     * un error semántico si los tipos no coinciden.
     * 
     * @param _localctx El parámetro `_localctx` es de tipo `ExpresionContext`.
     */
    public static void resolverAsignacion(Token ID, ParserRuleContext c) {
        try {
            int tipo_dato = variablesDeclaradas.get(ID.getText()).getType();
            byte reg = 0;
            reg += tipo_dato == COMPONENTE ? 0b01 : 0;
            reg += c.getRuleIndex() == RULE_declaracionDeVariable ? 0b10 : 0;
            switch (reg) {
                case 0b11:
                    switch (variablesDeclaradas.get(ID.getText()).getText()) {
                        case "display_lcd":
                            semanticError(ID, "al display_lcd no se le pueden asignar pines");
                            return;
                        case "motor":
                            semanticError(ID, "al motor no se le pueden asignar pines");
                            return;
                        case "siete_segmentos":
                            semanticError(ID, "al siete_segmentos no se le pueden asignar pines");
                        default:
                            break;
                    }
                    if (!resolverPila(t -> t == ENTERO)) {
                        semanticError(ID, "los componentes deben ser inicializados con el número de PIN en ENTERO, no "
                                + s.getSymbolicName(salida.peek().getType()));
                    }
                    return;
                case 0b01:
                    semanticError(ID, "los pines asignados al componente no deben cambiar");
                    return;
            }
            if (!resolverPila(
                    t -> {
                        return retorno(tipo_dato).test(t);
                    }))
                semanticError(salida.peek(), String.format("no se puede asignar %s a la variable %s; se esperaba %s",
                        s.getSymbolicName(salida.peek().getType()), ID.getText(),
                        s.getSymbolicName(td_variable)));
        } catch (Exception e) {
        }
    }

    /**
     * La función comprueba si solo hay un identificador en la pila y si es de un
     * tipo válido, y
     * establece una bandera si se cumplen las condiciones.
     * 
     * @param t El parámetro "t" es de tipo Token.
     */
    static void resolverSwitch(Token t) {
        if (cantidadEnPila() != 1) {
            semanticError(t, "se esperaba sólo un identificador");
            return;
        }
        if (!tdVariables.contains(salida.peek().getType())) {
            semanticError(t, "se esperaba sólo un identificador");
            return;
        }
        banderaSwitch = true;
    }

    /**
     * La función "errorPila" se utiliza para generar un mensaje de error semántico
     * indicando que una
     * expresión debería ser de un determinado tipo, pero no lo es.
     * 
     * @param esperado El parámetro "esperado" es una cadena que representa el tipo
     *                 de expresión esperada.
     * @param t        El parámetro "t" representa el token que almacena el tipo de
     *                 expresión que es.
     */
    public static void errorPila(String esperado, Token t) {
        semanticError(t, String.format("la expresión debe ser %s, no %s", esperado,
                s.getSymbolicName(t.getType())));
    }

    public static void resolverDetectar(Token t) {
        if (cantidadEnPila() != 1) {
            semanticError(t, "se esperaba sólo un identificador TD_ENTERO");
            return;
        }
        if (salida.peek().getType() != TD_ENTERO) {
            semanticError(t, "se esperaba un identificador TD_ENTERO y no "
                    + s.getSymbolicName(t.getType()) + "");
        }
    }

    public static void validarListaExpresiones(ParserRuleContext c, Token t) {
        if (c.parent.getRuleIndex() == RULE_accion) {
            if (pilasArgumento.size() != 2) {
                semanticError(t, "la acción 'sonar' requiere 2 parámetros (ENTERO o TD_ENTERO,ENTERO o TD_ENTERO)");
                return;
            }
            if (!pilasArgumento.stream().allMatch(t2 -> t2.getType() == ENTERO || t2.getType() == TD_ENTERO))
                semanticError(t, "la acción 'sonar' requiere 2 parámetros (ENTERO o TD_ENTERO,ENTERO o TD_ENTERO)");
        }
    }

    public static void validarArgumento(ParserRuleContext c) {
        if (c.parent.getRuleIndex() == RULE_argumentos) {
            try {
                resolverPila(t -> true);
            } catch (Exception e) {
            }
            pilasArgumento.push(salida.peek());
        }
    }

    private static int cantidadEnPila() {
        return salida.size() + pilaOperadores.size();
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

    static void nuevaExpresion() {
        pilaOperadores = new Stack<>();
        salida = new Stack<>();
        puedeResolverPila = true;
        varTemp=0;
    };

    /**
     * La función comprueba si hay una devolución inesperada de datos.
     *
     * @param t El parámetro "t" es de tipo Token.
     */
    public static void comprobarRetorno(Token t) {
        if (retornoFuncion == -1)
            semanticError(t, "no se esperaba retorno de datos");
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
    public static void comprobarRetorno(Token t, ParserRuleContext c) {
        if (!comprobarPadre(c, RULE_funcion, t))
            semanticError(t, "devolver no se encuentra en una función");
        RuleContext p = c;
        while ((p = p.parent) != null) {
            if (p.getRuleIndex() == RULE_funcion)
                return;
        }
    }

    public static boolean comprobarPadre(ParserRuleContext c, int rule, Token t) {
        RuleContext p = c;
        while ((p = p.parent) != null) {
            if (p.getRuleIndex() == rule)
                return true;
        }
        return false;
    }

    static void imprimirPila(Stack<Token> xdd, Token inicial) {
        StringBuilder xd = new StringBuilder();
        xd.append("Linea " + inicial.getLine() + ":" + inicial.getCharPositionInLine() + "[");
        xdd.forEach(t -> xd.append("[" + t.getText() + "]"));
        xd.append("]");
        pilas.add(xd.toString());
    }

    static boolean resolverPila(Predicate<Integer> esperado) throws Exception {
        if (!puedeResolverPila)
            throw new Exception();
        while (!pilaOperadores.empty()) {
            salida.push(pilaOperadores.pop());
        }
        imprimirPila(salida, salida.peek());
        invertirPila();
        if (salida.size() == 1) {
            return esperado.test(salida.peek().getType());
        }
        Stack<Token> t = new Stack<>();
        while (!salida.empty()) {
            if ((!operadores.contains(salida.peek().getText()))) {
                t.push(salida.pop());
            } else {
                try {
                    t.push(salida.peek());
                    imprimirPila(t,t.peek());
                    t.pop();
                    t.push(validarOperacion(t.pop(), t.pop(), salida.pop()));
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        imprimirPila(t, t.peek());
        salida.push(SimpleSemantic.t = t.pop());
        return esperado.test(salida.peek().getType());
    }

    private static void invertirPila() {
        Stack<Token> aux = new Stack<>();
        while (!salida.empty()) {
            aux.push(salida.pop());
        }
        salida = aux;
        t.getText().equals("");
    }

    private static Token resultadoPila(int tipo_dato, String texto, Token operador) {
        CommonToken x = new CommonToken(tipo_dato, texto);
        x.setLine(operador.getLine());
        x.setCharPositionInLine(operador.getCharPositionInLine());
        return x;
    }

    static int varTemp=0; 
    public static String variableTemporal() {
        return "t"+(++varTemp);
    }
 
    private static Token validarOperacion(Token operando2, Token operando1, Token operador) throws Exception {
        if (operador.getText().equals("=")){
            new Quintupla(operador,operando1,operando2,null);
            return resultadoPila(operando2.getType(), operando2.getText(), operador);
        }
        // 1. No hay operaciones con cadenas
        Predicate<Integer> cadenas = retornoExpresion.get(1);
        if (cadenas.test(operando1.getType()) || cadenas.test(operando2.getType())) {
            semanticError(operador, "el operador " + operador.getText() + " no admite cadenas");
            throw new Exception();
        }
        // 2. Operandos Booleanos
        Predicate<Integer> booleanos = retornoExpresion.get(0);
        byte regOperandos = 0;
        byte regOperador = 0;
        regOperandos += booleanos.test(operando1.getType()) ? 0b01 : 0;
        regOperandos += booleanos.test(operando2.getType()) ? 0b10 : 0;
        regOperador += operadoresBooleanos.contains(operador.getText()) ? 0b01 : 0;
        regOperador += operadoresBooleanoNumericos.contains(operador.getText()) ? 0b10 : 0;
        switch (regOperandos) {
            case 0b11:
                if (regOperador != 0){
                    Token resultadoPila = resultadoPila(BOOLEANO, variableTemporal(), operador);
                    new Quintupla(operador,operando1,operando2,resultadoPila);
                    return resultadoPila;
                }
                else {
                    semanticError(operador, "operador: '" + operador.getText()
                            + "'; las expresiones izquierda y derecha deben ser de tipo Booleano");
                    throw new Exception();
                }
            case 0b10:
            case 0b01:
                semanticError(operador,
                        "operador: '" + operador.getText() + "'; se esperaba que ambos operandos fueran booleanos");
            default:
                break;
        }
        // 3. Operandos numéricos
        Predicate<Integer> enteros = retornoExpresion.get(2);
        regOperandos = 0;
        regOperandos += enteros.test(operando1.getType()) ? 0b01 : 0;
        regOperandos += enteros.test(operando2.getType()) ? 0b10 : 0;
        regOperador = 0;
        regOperador += operadoresBooleanoNumericos.contains(operador.getText()) ? 0b001 : 0;
        regOperador += operadoresComparadores.contains(operador.getText()) ? 0b010 : 0;
        regOperador += operadoresAritmeticos.contains(operador.getText()) ? 0b100 : 0;
        switch (regOperador) {
            case 0:
                semanticError(operador, "operador: '" + operador.getText()
                        + "'; se esperaba que ambos operandos fueran booleanos");
                throw new Exception();
            case 0b001:
            case 0b010:
                Token resultadoPila = resultadoPila(BOOLEANO, variableTemporal(), operador);
                new Quintupla(operador,operando1,operando2,resultadoPila);
                return resultadoPila;
        }
        switch (regOperandos) {
            case 0b11:
                Token resultadoPila = resultadoPila(ENTERO, variableTemporal(), operador);
                new Quintupla(operador,operando1,operando2,resultadoPila);
                return resultadoPila;
            case 0b10:
            case 0b01:
            case 0b00:
                Token resultadoPila2 = resultadoPila(DECIMAL, variableTemporal(), operador);
                new Quintupla(operador,operando1,operando2,resultadoPila2);
                return resultadoPila2;
        }
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
            funcionesDeclaradas.add(new Funcion(funcion.getText(), retornoFuncion, lista));
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
    public static void semanticError(Token variable, String msg) {
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
                CommonToken x = (CommonToken) new CommonToken(variablesDeclaradas.get(token.getText()));
                x.setLine(token.getLine());
                x.setCharPositionInLine(token.getCharPositionInLine());
                x.setText(token.getText());
                salida.push(x);
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
            case "=":
                return 0;
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
    public static boolean declararVariable(Token variable, Token tipoVariable) {
        if (!variablesDeclaradas.containsKey(variable.getText())) {
            variablesDeclaradas.put(variable.getText(), tipoVariable);
            return true;
        }
        String text = variable.getText();
        Token declarada = variablesDeclaradas.get(variable.getText());
        semanticError(variable, "la variable: '" + text + "' ya ha sido declarada en linea: " + declarada.getLine());
        return puedeResolverPila = false;
    }

    /**
     * La función comprueba si se ha declarado una variable y arroja un error
     * semántico si no es así.
     *
     * @param variable El parámetro "variable" es de tipo "Token".
     */
    public static boolean usarVariable(Token variable) {
        if (!variablesDeclaradas.containsKey(variable.getText())) {
            semanticError(variable, "la variable " + variable.getText() + " no ha sido declarada");
            return puedeResolverPila = false;
        }
        return true;
    }
}