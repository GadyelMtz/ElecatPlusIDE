package Analizadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

import static Analizadores.SimpleSemantic.*;
import static Analizadores.SimpleParser.*;

public class SimpleCode {
    public static class Quintupla {
        public static int linea = 0;
        Token[] tokens = new Token[5];

        // (num,operador,operando1,operando2,almacen)
        public Quintupla(Token... t) {
            if (t.length != 4)
                System.err.println("ErrorQuintupla:");
            tokens[0] = new CommonToken(0, ++linea + "");
            tokens[1] = t[0];
            tokens[2] = t[1];
            tokens[3] = t[2];
            tokens[4] = t[3];
            quintuplas.add(this);
            quintuplasSinOptimizar.add(this);
        }

        @Override
        public String toString() {
            String xd = "(";
            for (Token t : tokens) {
                xd += (t != null) ? t.getText() + "," : " ,";
            }
            xd = xd.substring(0, xd.length() - 1) + ")";
            return xd;
        }
    }

    public static ArrayList<Quintupla> quintuplas = new ArrayList<>();
    public static ArrayList<Quintupla> quintuplasSinOptimizar = new ArrayList<>();

    static HashMap<String, Object> temp = new HashMap<>();
    static HashMap<String, Boolean> castBooleano = new HashMap<>();
    static {
        castBooleano.put("falso", false);
        castBooleano.put("f", false);
        castBooleano.put("v", true);
        castBooleano.put("verdadero", true);
    }

    public static void optimizarExpresiones() {
        ArrayList<Quintupla> aux = new ArrayList<>();
        Predicate<Integer> tiposDeDato = (td ->td == TD_CADENA || td == TD_ENTERO || td == TD_DECIMAL || td == TD_BOOLEANO);
        Predicate<String> noOptimizable = (td ->{
            switch (td) {
                case "setup":
                case "}":
                case "loop":
                case "encender":
                case "apagar":
                return true;
            }
            return false;});
        Predicate<Integer> tipoDato = (td -> td == TD_ENTERO || td == TD_DECIMAL || td == TD_BOOLEANO);
        // 1. Filtrar las quintuplas a optimizar
        quintuplas.stream()
                .filter(qin -> !tiposDeDato.test(qin.tokens[1].getType()) && !noOptimizable.test(qin.tokens[1].getText()))
                .forEach(
                        quintupla -> {
                            // 2. Dentro de las quíntuplas, filtrar la optimización
                            if (!operadores.contains(quintupla.tokens[1].getText())){
                                for (int index = 2; index < 5; index++) {
                                    if (quintupla.tokens[index]!=null){
                                        if (quintupla.tokens[index].getText().startsWith("t")) {
                                            resolverLiteral(quintupla, quintupla.tokens[index]);
                                            temp = new HashMap<>();
                                        }
                                    }
                                }
                            }else if (quintupla.tokens[1].getText().equals("=")) {
                                // 3. Si es una asignación de temporal, coloar el valor literal de t
                                if (quintupla.tokens[3].getText().startsWith("t")) {
                                    resolverLiteral(quintupla,quintupla.tokens[3]);
                                    temp = new HashMap<>();
                                }
                            } else if (tipoDato.test(quintupla.tokens[2].getType())
                                    || tipoDato.test(quintupla.tokens[3].getType()))
                                // 4. Si las operaciones son con variables, ignorar.
                                ;
                            else
                                // 5. Verificar el tipo de retorno de la expresión
                                switch (quintupla.tokens[4].getType()) {
                                    case BOOLEANO:
                                        if (quintupla.tokens[2].getType() == BOOLEANO
                                                && quintupla.tokens[3].getType() == BOOLEANO) {
                                            // 6. Si es booleano, verificar que los operandos sean Booleanos
                                            Boolean o1, o2;
                                            o1 = (o1 = castBooleano.get(quintupla.tokens[2].getText())) != null ? o1
                                                    : (Boolean) temp.get(quintupla.tokens[2].getText());
                                            o2 = (o2 = castBooleano.get(quintupla.tokens[3].getText())) != null ? o2
                                                    : (Boolean) temp.get(quintupla.tokens[3].getText());
                                            // 7. Si alguno es nulo, se tiene un temporal que depende de una operación
                                            // con variables
                                            boolean ban = o1 == null || o2 == null;
                                            if (!ban) {
                                                // 8. Resolver
                                                temp.put(quintupla.tokens[4].getText(),
                                                        resolverBooleanos(quintupla, o1, o2));
                                                aux.add(quintupla);
                                            }
                                        } else {
                                            // 9. Si son dos números compararlos como Double (xd)
                                            Double o1, o2;
                                            try {
                                                o1 = Double.parseDouble(quintupla.tokens[2].getText());
                                            } catch (Exception e) {
                                                o1 = (Double) temp.get(quintupla.tokens[2].getText());
                                            }
                                            try {
                                                o2 = Double.parseDouble(quintupla.tokens[3].getText());
                                            } catch (Exception e) {
                                                o2 = (Double) temp.get(quintupla.tokens[3].getText());
                                            }
                                            // 10. Resolver y guardar el resultado
                                            temp.put(quintupla.tokens[4].getText(),
                                                    resolverComparacionNumeros(quintupla, o1, o2));
                                            aux.add(quintupla);
                                        }
                                        break;
                                    case ENTERO:
                                        // 11. Obtener los datos
                                        Integer o1, o2;
                                        try {
                                            // Literal
                                            o1 = Integer.parseInt(quintupla.tokens[2].getText());
                                        } catch (Exception e) {
                                            o1 = (Integer) temp.get(quintupla.tokens[2].getText());
                                        }
                                        try {
                                            // Literal
                                            o2 = Integer.parseInt(quintupla.tokens[3].getText());
                                        } catch (Exception e) {
                                            o2 = (Integer) temp.get(quintupla.tokens[3].getText());
                                        }
                                        // 7.
                                        boolean banI = o1 == null || o2 == null;
                                        // 12. Operar y guardar
                                        if (!banI) {
                                            temp.put(quintupla.tokens[4].getText(), resolverEnteros(quintupla, o1, o2));
                                            aux.add(quintupla);
                                        }
                                        break;
                                    case DECIMAL:
                                        Double o5, o6;
                                        try {
                                            // Literal
                                            o5 = Double.parseDouble(quintupla.tokens[2].getText());
                                        } catch (Exception e) {
                                            o5 = (Double) temp.get(quintupla.tokens[2].getText());
                                        }
                                        try {
                                            // Literal
                                            o6 = Double.parseDouble(quintupla.tokens[3].getText());
                                        } catch (Exception e) {
                                            o6 = (Double) temp.get(quintupla.tokens[3].getText());
                                        }
                                        boolean banD = o5 == null || o6 == null;
                                        if (!banD) {
                                            temp.put(quintupla.tokens[4].getText(), resolverDouble(quintupla, o5, o6));
                                            aux.add(quintupla);
                                        }
                                        break;
                                }
                        });

        aux.forEach(quintupla -> quintuplas.remove(quintupla));
        variablesDeclaradas.forEach((nombre, u) -> {
            // Suponiendo que Quintupla es una clase con un constructor y métodos
            // apropiados.
            List<Quintupla> elementosARemover = quintuplas.stream()
                    .filter(t -> t.tokens[1].getText().equals(nombre) ||
                            (t.tokens[2] == null ? "" : t.tokens[2].getText()).equals(nombre) ||
                            (t.tokens[3] == null ? "" : t.tokens[3].getText()).equals(nombre))
                    .collect(Collectors.toList());

            if (elementosARemover.size() == 1) {
                quintuplas.removeIf(elementosARemover::contains);
            }
        });
    }

    static int buscarTexto(String pattern, String texto) {
        return (int) Pattern.compile(pattern).matcher(texto).results().count();
    }

    private static Double resolverDouble(Quintupla quintupla, Double o1, Double o2) {
        Double resD = 0d;
        switch (quintupla.tokens[1].getText()) {
            case "+":
                resD = o1 + o2;
                break;
            case "-":
                resD = o1 - o2;
                break;
            case "/":
                resD = (o1 / o2);
                break;
            case "*":
                resD = o1 * o2;
                break;
        }
        return resD;
    }

    private static Integer resolverEnteros(Quintupla quintupla, Integer o1, Integer o2) {
        int res = 0;
        switch (quintupla.tokens[1].getText()) {
            case "+":
                res = o1 + o2;
                break;
            case "-":
                res = o1 - o2;
                break;
            case "/":
                res = (int) (o1 / o2);
                break;
            case "*":
                res = o1 * o2;
                break;
        }
        return res;
    }

    private static Boolean resolverComparacionNumeros(Quintupla quintupla, Double o1, Double o2) {
        Boolean res = false;
        switch (quintupla.tokens[1].getText()) {
            case "<":
                res = o1 < o2;
                break;
            case ">":
                res = o1 > o2;
                break;
            case ">=":
                res = o1 >= o2;
                break;
            case "<=":
                res = o1 <= o2;
                break;
            case "==":
                res = o1 == o2;
                break;
            case "!=":
                res = o1 != o2;
                break;
        }
        return res;
    }

    private static Boolean resolverBooleanos(Quintupla quintupla, Boolean o1, Boolean o2) {
        Boolean res = false;
        switch (quintupla.tokens[1].getText()) {
            case "or":
                res = o1 || o2;
                break;
            case "and":
                res = o1 && o2;
                break;
            case "==":
                res = o1 == o2;
                break;
            case "!=":
                res = o1 != o2;
                break;
        }
        return res;
    }

    private static void resolverLiteral(Quintupla quintupla,Token token) {
        Object object = temp.get(token.getText());
        if (object != null)
            switch (token.getType()) {
                case BOOLEANO:
                    Boolean x = (Boolean) object;
                    ((CommonToken) token).setText(x ? "v" : "f");
                    break;
                case ENTERO:
                    Integer i = (Integer) object;
                    ((CommonToken) token).setText(i + "");
                    break;
                case DECIMAL:
                    Double d = (Double) object;
                    ((CommonToken) token).setText(d + "");
                    break;
            }
    };
}