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
import Analizadores.SimpleSemantic;

public class Main {
    public static void main(String[] args) {
        try {
            CharStream input = CharStreams.fromFileName(new File("ElecatPlusIDE/src/Prueba.ecp").getAbsolutePath());
            // Leer código fuente
            SimpleLexer lexer = new SimpleLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);
            System.out.println("");
            ParseTree tree = parser.programa(); // Comienza el análisis desde la regla expr
            System.out.println("PILAS");
            SimpleSemantic.pilas.forEach(t -> System.out.println(t));
            // Prueba de PINES = FUNCIONÓ
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