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
        while (!pilaOperadores.empty()) {
            salida.push(pilaOperadores.pop());
        }
        // Sacar el resto de operadores de la pila
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
}

// // Excepción personalizada para errores semánticos class SemanticErrorException extends
// RuntimeException { public SemanticErrorException(String message) { super(message); } }

// @parser::members { boolean declarando; Map<String, Object> tablaSimbolos = new HashMap<String,
// Object>(); }

// ESTRUCTURAS DEL PROGRAMA.
programa: PROGRAMA ID BRACKET_ABRIR sentencia+ BRACKET_CERRAR;

sentencia:
	declaracion
	| asignacion
	| condicionalif
	| cicloWhile
	| accion;

declaracion:
	{ isDeclaration = true; } (t = TIPO_DATO | t = COMPONENTE) {t = $t;} (
		(ID { !variableDeclarada($ID,$t) }? FIN_LINEA)
		| asignacion
	) { isDeclaration = false;};

asignacion:
	ID { !variableDeclarada($ID,t) }? { usarVariable($ID) }? SIGNO_IGUAL expresion { 
            invertirSalida();
            imprimirPila();
		} {resolverPila(variablesDeclaradas.get($ID.text).getText())}? FIN_LINEA;

condicionalif:
	SI PAR_ABRIR expresion PAR_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR (
		SI_NO BRACKET_ABRIR sentencia* BRACKET_CERRAR
	)?;

cicloWhile:
	REPETIR MIENTRAS PAR_ABRIR expresion PAR_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR;

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
	ESPERAR PAR_ABRIR NUMERO COMA TIEMPO PAR_CERRAR FIN_LINEA;
// ESPERAR(213, SEGUNDOS);
girar: GIRAR PAR_ABRIR NUMERO COMA DIRECCION PAR_CERRAR;
// GIRA(grados, direccion)
escribir: ESCRIBIR PAR_ABRIR (ID | CADENA | NUMERO) PAR_CERRAR;
// ESCRIBIR(ID|Cadena) ACCION(#sieteseg#, ESCRIBIR(#micadena#));

termino:
	(t = BOOLEANO | t = NUMERO | t = ID { usarVariable($t) }?) { añadirAPila($t); }
	| t = PAR_ABRIR { añadirAPila($t); } expresion t = PAR_CERRAR { añadirAPila($t); };

expresion: termino operacionBinaria*;
operacionBinaria: (OP_BIN { añadirAPila($OP_BIN); } termino);

// Regla léxica para comentarios de línea
COMENTARIO_LINEA: '//' ~[\r\n]* -> skip;

// Regla léxica para comentarios de bloque
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;

//-------------------------------------------------------------

// EXPRESIONES REGULARES - Análisis Léxico
// #[a-zA-Z](([a-zA-Z]|[0-9])*(_([a-zA-Z]|[0-9]))?)*([a-zA-Z]|[0-9])*#
ID:
	'#' [a-zA-Z] ([a-zA-Z0-9]* ('_' [a-zA-Z0-9]+)?)? [a-zA-Z0-9]* '#';
NUMERO: ([0-9]+ | [0-9]+ '.' [0-9]+ | '.' [0-9]+);
CADENA: '"' [a-zA-Z0-9]+ '"';

ESCRIBIR: 'escribir';
GIRAR: 'gira';
//PENDIENTE
ACCIONES: 'sonar' | 'encender' | 'apagar';
LEE: 'leer';
PROGRAMA: 'programa';
ACCION: 'accion';
ESTADO: 'encendido' | 'apagado' | 'reversa';
COMPONENTE:
	'led'
	| 'display_lcd'
	| 'servo'
	| 'sensor_ultrasonico'
	| 'motor'
	| 'foto_resistencia'
	| 'buzzer'
	| 'siete_segmentos' 'boton'
	| 'fuente';

DIRECCION: 'horario' | 'antihorario';

// Operadores binarios
OP_BIN: 'and' | 'or' | '+' | '-' | '*' | '/';
NOT: 'not';
TIEMPO: 'milisegundo' | 'segundo' | 'minuto';
UNIDAD: 'voltio' | 'amperio' | 'ohm' | 'watt';
SI: 'si';
SI_NO: 'sino';
CONDICION: 'elegir' | 'opcion' | 'por defecto';
REPETIR: 'repetir';
MIENTRAS: 'mientras';
CICLO: 'hasta' | 'por' | 'para';
VAR: 'var';
TIPO_DATO:
	'decimal'
	| 'entero'
	| 'caracter'
	| 'cadena'
	| 'booleano'
	| 'tabla';
FIN_LINEA: ';';
SIGNO_IGUAL: '=';
BOOLEANO: 'v' | 'f' | 'verdadero' | 'falso';
BRACKET_ABRIR: '{';
BRACKET_CERRAR: '}';
PAR_ABRIR: '(';
PAR_CERRAR: ')';
ES: 'es';
MAYOR: 'mayor';
MENOR: 'menor';
IGUAL: 'igual';
QUE: 'que';
DIFERENTE: 'diferente';
ESPERAR: 'esperar';
COMA: ',';

WS: [ \t\r\n]+ -> skip;