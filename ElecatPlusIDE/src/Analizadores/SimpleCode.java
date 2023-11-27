package Analizadores;

import java.util.ArrayList;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;


public class SimpleCode {
    public static class Quintupla {
        public static int linea=0;
        Token[] tokens = new Token[5];
        //(num,operador,operando1,operando2,almacen)
        public Quintupla(Token... t){
            if(t.length!=4)
                System.err.println("ErrorQuintupla:");
            tokens[0] = new CommonToken(0, ++linea+"");
            tokens[1] = t[0];
            tokens[2] = t[1];
            tokens[3] = t[2];
            tokens[4] = t[3];
            quintuplas.add(this);
        }

        @Override
        public String toString() {
            String xd="(";
            for(Token t: tokens){
                xd+=(t!=null)?t.getText()+",":",";
            }
            xd=xd.substring(0,xd.length()-1)+")";
            return xd;
        }
    }
    public static ArrayList<Quintupla> quintuplas = new ArrayList<>();
}
