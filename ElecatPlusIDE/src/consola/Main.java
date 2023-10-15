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
    private static final int TIPO_DATO = 0;
    // Tabla de símbolos para realizar un seguimiento de las variables declaradas
    final Map<String, Token> variablesDeclaradas = new HashMap<>();
    Stack<Token> salida = new Stack<>();
    Stack<Token> pilaOperadores = new Stack<>();
    boolean isDeclaration = false;
    Token t;
    final Set<String> booleanos = new HashSet<>(Arrays.asList("v","f","verdadero","falso","booleano","and","or"));
    final Set<String> algebraicos = new HashSet<>(Arrays.asList("decimal","entero"));

    //#region CódigoSemántico
    void imprimirPila(){
        Stack<Token> x = (Stack<Token>) salida.clone();
        String xd = "";
        while (!x.empty()) {
            xd+=x.pop().getText()+" ";
        }
        System.out.println(xd);
    }

    boolean resolverPila(String tipo){
        invertirSalida(); 
        //Para leer de izquierda a derecha y no de derecha a izquierda (como llega)
        if (salida.size()==1){
            Token t = salida.pop();
            String tipoP;
            if (booleanos.contains(t.getText())){
                tipoP="booleano";
            } else {
                tipoP = t.getType() == NUMERO ? t.getText().contains(".") ? "decimal" : "entero" : t.getText();
            }
            return tipo.equals(tipoP);
        }
        Stack<Token> t = new Stack<>();
        while (!salida.empty()) {
            if (salida.peek().getType()!=OP_BIN){
                t.push(salida.pop());
            } else {
                try {
                    t.push(validarOperacion(t.pop(),t.pop(),salida.pop()));
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return t.pop().equals(tipo);
    }

    private Token validarOperacion(Token operando1,Token operando2 , Token operador) throws Exception {
        if (booleanos.contains(operador.getText())){
            if(!booleanos.contains(operando1.getText()) || !booleanos.contains(operando2.getText()))
                throw new Exception();
            return new CommonToken(TIPO_DATO,"booleano");
        } else {
            String t1 = operando1.getType()==NUMERO ? operando1.getText().contains(".") ? "decimal" : "entero" : operando1.getText();
            String t2 = operando2.getType()==NUMERO ? operando2.getText().contains(".") ? "decimal" : "entero" : operando2.getText();
            if(!algebraicos.contains(t1) || !algebraicos.contains(t2))
                throw new Exception();
            return (t1.equals("decimal") || t2.equals("decimal")) ? new CommonToken(TIPO_DATO,"decimal") : new CommonToken(TIPO_DATO,"entero"); 
        }
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

    // Funcion para añadir a la pila semántica
    void añadirAPila(Token t) {
        if (t == null)
            return;
        int type = t.getType();
        if (type == ID){
                salida.push(variablesDeclaradas.get(t.getText()));
        } else if (type == NUMERO || type == BOOLEANO){
            salida.push(t);
        } else if (type == OP_BIN){
            while (!pilaOperadores.empty() && precedencia(pilaOperadores.peek().getText()) >= precedencia(t.getText())) {
                salida.push(pilaOperadores.pop());
            }
            pilaOperadores.push(t);
        } else if (type == PAR_ABRIR){
            pilaOperadores.push(t);
        } else if (type == PAR_CERRAR){
            while (!pilaOperadores.empty() && pilaOperadores.peek().getType()!=PAR_ABRIR) {
                salida.push(pilaOperadores.pop());                
            }
            if (!pilaOperadores.empty() && pilaOperadores.peek().getType()==PAR_ABRIR)
                pilaOperadores.pop();
        }
    }

    private int precedencia(String text) {
        switch (text) {
            case "+":
            case "-":
            case "or":
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
		variablesDeclaradas.put(nombreVariable,tipoVariable);
		return false;
	}

    // Predicado semántico para verificar si una variable se ha declarado al
    // intentar declararla
    boolean usarVariable(Token variable) {
        return variablesDeclaradas.containsKey(variable.getText());
    }
    //#endregion

    public static void main(String[] args) {
        try {
            // antlr4 -no-listener -no-visitor -Dlanguage=Java -package=Analizadores Simple.g4
            CharStream input = CharStreams.fromFileName(new File("ElecatPlusIDE/src/Prueba.ecp").getAbsolutePath());
            // Leer código fuente

            SimpleLexer lexer = new SimpleLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);
            System.out.println("");
            ParseTree tree = parser.programa(); // Comienza el análisis desde la regla expr
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