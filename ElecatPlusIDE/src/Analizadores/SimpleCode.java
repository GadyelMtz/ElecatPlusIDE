package Analizadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

import static Analizadores.SimpleParser.BOOLEANO;
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
    static HashMap<String,Object> temp = new HashMap<>();
    static HashMap<String,Boolean> castBooleano = new HashMap<>();
    static{
        castBooleano.put("falso",false);
        castBooleano.put("f",false);
        castBooleano.put("v",true);
        castBooleano.put("verdadero",true);
    }

        static void optimizarExpresiones() {
            Predicate<Integer> tipoDato = (t -> t == TD_ENTERO || t == TD_DECIMAL || t == TD_BOOLEANO);
            quintuplas.stream().filter(t -> (t.tokens[4] != null && operadores.contains(t.tokens[1].getText()))).forEach(
                    t -> {
                        if (tipoDato.test(t.tokens[2].getType()) || tipoDato.test(t.tokens[3].getType()))
                            ;
                        else
                            switch (t.tokens[4].getType()) {
                                case BOOLEANO:
                                    Boolean o1 = castBooleano.get(t.tokens[2].getText()), o2 = castBooleano.get(t.tokens[3].getText()), res;
                                    o1 = (o1 = castBooleano.get(t.tokens[2].getText())) != null ? o1 : (Boolean) temp.get(t.tokens[2].getText());
                                    switch (t.tokens[1].getText()) {
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
                                case ENTERO:
                                    try{

                                    }catch(Exception e){

                                    }
                                    int o1 = Integer.parseInt t.tokens[2], o2 = t.tokens[3], res;
                                    break;
                                case DECIMAL:
                                    float o1 = t.tokens[2], o2 = t.tokens[3], res;
                                    break;
                            }
                    });
        }
}
