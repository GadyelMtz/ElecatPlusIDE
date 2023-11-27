// antlr4-parse SimpleParser.g4 SimpleLexer.g4 programa -tokens
// C:\Users\angel\OneDrive\Documentos\ElecatPlusIDE\ElecatPlusIDE\src\Prueba.ecp 
// antlr4 SimpleLexer.g4 -no-listener -no-visitor antlr4 SimpleParser.g4 -no-listener -no-visitor

package consola;

import java.io.File;
import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import Analizadores.SimpleLexer;
import Analizadores.SimpleParser;
import Analizadores.SimpleSemantic;

public class Main {
    public static void main(String[] args) {
        try {
            CharStream input = CharStreams.fromFileName(new File("ElecatPlusIDE/src/Prueba.ecp").getAbsolutePath());
            // Leer código fuente
            int x=0;
            SimpleLexer lexer = new SimpleLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);
            System.out.println("");
            parser.programa(); // Comienza el análisis desde la regla expr
            System.out.println("PILAS");
            // SimpleSemantic.pilas.forEach(t -> System.out.println(t));
            // Prueba de PINES = FUNCIONÓ
        } catch (Exception e) {
            if (e instanceof IOException)
                System.out.println("El archivo no fué encontrado" + e);
        }
    }
}