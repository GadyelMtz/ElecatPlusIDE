package codigoObjeto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Desktop;

import javax.swing.JOptionPane;

import Analizadores.SimpleCode;

public class codigoObjetoNuevo {
        String nombre;
    String codigo;
    String nombreArchivo;
    String rutaArchivo;
    String rutaDirectorio;
    // Definir la estructura de datos
    Map<String, String> componentes = new HashMap<>();
    Map<String, String> cadenas = new HashMap<>();
    Map<String, String> enteros = new HashMap<>();


    public codigoObjetoNuevo(String codigo, String nombre) {
    
        // Definir las rutas de trabajo
        this.codigo = codigo;
        this.nombreArchivo = nombre.split(".ecp - ElecatPlus IDE 1.0")[0];
        this.rutaArchivo = System.getProperty("user.dir") + "\\ElecatPlusIDE\\" + "CodigoObjeto\\" + nombreArchivo
                + "\\" + nombreArchivo + ".ino";
        this.rutaDirectorio = System.getProperty("user.dir") + "\\ElecatPlusIDE\\" + "CodigoObjeto\\"
        + nombreArchivo;
    }

    public void crearCodigoObjeto() {
        transformarArduino();
        crearArchivo();
        cargarArduino();
    }

    private void transformarArduino(){
         // Elimina los ## a las variables
         cambiarVariables();
         // Cambia a void setup(){ la funcion que coincide con la declarada
         // como programa #ID#{
         // #ID#(){
         cambiarSetup();
         // Cambia a void loop() la funcion ejecutar(){
         //cambiarLoop();
         // Almacena las variables y su valor en un mapa
         //almacenarTiposDeDato();
         // Cambia las palabras de ElecatPlus a sus equivalentes en Arduino
         cambiarPalabras();
         // Cambia la declaracion de componentes a declaraciones de pines y 
         // tipos de OUTPUT o INPUT según sea el caso
         //cambiarComponentes();
         // Cambia a las acciones por sus equivalentes en Arduino
         //cambiarAcciones();
    }
    
private void cambiarSetup() {
    String lineas[] = codigo.split("\n");
        codigo = codigo.replace("(", "");
        codigo = codigo.replace(" )", "");
        codigo = codigo.replace(")", "");
        String aux = "";
        String nombreSetup = "";
        // Ciclo para recorrer cada línea
       for (int i = 0; i < lineas.length; i++) {
        // Dividir cada línea en palabras
        String[] palabras = lineas[i].split(",");
        
        // Si la segunda palabra es setup directamente hace la
        // transformacion
        if(palabras[1].equals("setup")) {
            lineas[i] = "setup{";
            nombreSetup = palabras[2];
        }    
        

        if(palabras[1].equals("}") && palabras[2].equals(nombreSetup))
            lineas[i] = "}";

        aux += lineas[i]+"\n";
        codigo = aux;
       }
}


private void cambiarVariables() {
        Pattern patron = Pattern.compile("#(.*?)#");
        // Crear un objeto Matcher
        Matcher matcher = patron.matcher(codigo);
        StringBuffer resultado = new StringBuffer();
        while (matcher.find()) {
            // Extraer el texto entre '#'
            String textoExtraido = matcher.group(1);
            matcher.appendReplacement(resultado, textoExtraido);
        }
        matcher.appendTail(resultado);
        codigo = resultado.toString();
    }

    private void cambiarPalabras() {
        String lineas[] = codigo.split("\n");
        codigo = codigo.replace("(", "");
        codigo = codigo.replace(" )", "");
        codigo = codigo.replace(")", "");
        String aux = "";
        // Ciclo para recorrer cada línea
       for (int i = 0; i < lineas.length; i++) {
        // Dividir cada línea en palabras
        String[] palabras = lineas[i].split(",");
        // Ciclo para recorrer cada palabra
        for (int j = 1; j < palabras.length; j++) {
            // Reemplazar la palabra si coincide con la palabra original
            // Entero
            if (palabras[j].equals("entero")) palabras[j] = "int";
            // cadena
            if (palabras[j].equals("cadena")) palabras[j] = "String";
            // decimal
            if (palabras[j].equals("decimal")) palabras[j] = "float";
            // mientras
            if (palabras[j].equals("mientras")) palabras[j] = "while";
            // and
            if (palabras[j].equals("and")) palabras[j] = "&&";
            // or
            if (palabras[j].equals("or")) palabras[j] = "||";
            // si
            if (palabras[j].equals("si")) palabras[j] = "if";
            // sino
            if (palabras[j].equals("sino")) palabras[j] = "else";
            // esperar
            if (palabras[j].equals("esperar")) palabras[j] = "delay";
             // elegir
            if (palabras[j].equals("elegir")) palabras[j] = "switch";
             // romper
            if (palabras[j].equals("romper")) palabras[j] = "break";
        }
        // Construir la nueva línea con las palabras modificadas
        lineas[i] =  String.join(",", palabras) + "\n";
        aux += lineas[i];
        codigo = aux;
    }

       
        
    }

    private void crearArchivo() {
        File archivo = new File(rutaArchivo);
        File directorio = new File(rutaDirectorio);

        try {
            if (!archivo.exists() && !directorio.exists()) {
                // Si no existe, intentar crear el archivo
                directorio.mkdirs();
                archivo.createNewFile();
            }
            FileWriter fw = new FileWriter(rutaArchivo);
            BufferedWriter bw = new BufferedWriter(fw);
            // Lectura del fichero
            bw.write(codigo);
            bw.close();
        } catch (Exception ex) {
            System.out.println("\nHubo un error al generar el codigo objeto " + ex);
        }
    }

    public void cargarArduino() {
        File archivo = new File(rutaArchivo);
        Desktop d = Desktop.getDesktop();
        try {
                int respuesta = JOptionPane.showConfirmDialog(null, "El codigo máquina ha sido generado correctamente", "¿Deseas abrir el código para anlizarlo?", JOptionPane.YES_NO_OPTION);
                // Si la respuesta es sí, verifica el archivo más no ejecuta automaticamente
                if(respuesta == 0){
                    d.open(archivo);
                }
                else{                        
                    // Compilar
                    ProcessBuilder compiladoBuilder = new ProcessBuilder("arduino-cli", "compile", "-b", "arduino:avr:uno", rutaArchivo);
                    compiladoBuilder.redirectErrorStream(true);
                    Process procesoCompilado = compiladoBuilder.start();
                    procesoCompilado.waitFor();
                    // Subir
                    ProcessBuilder subidoBuilder = new ProcessBuilder("arduino-cli", "upload", "-p", "COM6", "--fqbn", "arduino:avr:uno", rutaArchivo);
                    subidoBuilder.redirectErrorStream(true);
                    Process procesoSubido = subidoBuilder.start();
                    int exitCodeSubido = procesoSubido.waitFor();
                    // Continuar con el resto del código después de que ambos procesos hayan terminado
                    System.out.println("\nSe ha cargado el codigo al arduino...");
                }
        } catch (IOException | InterruptedException ioe) {
            System.out.println(ioe);
        }
    }
}
