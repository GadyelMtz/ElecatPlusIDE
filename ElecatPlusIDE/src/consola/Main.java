package consola;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import Analizadores.SimpleLexer;
import Analizadores.SimpleParser;

public class Main {
    public static void main(String[] args) {
        try {
            //java -jar antlr-4.13.1-complete.jar -Dlanguage=Java Simple.g4
            CharStream input = CharStreams.fromFileName("C:\\Users\\angel\\OneDrive\\Escritorio\\Prueba.ecp");
            //Leer código fuente

            SimpleLexer lexer = new SimpleLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);
            System.out.println("");
            ParseTree tree = parser.programa(); // Comienza el análisis desde la regla expr
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}