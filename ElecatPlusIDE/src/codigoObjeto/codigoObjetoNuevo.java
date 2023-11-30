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

    private void transformarArduino() {
        // Elimina los ## a las variables
        cambiarVariables();

        // Cambia a void setup(){ la funcion que coincide con la declarada
        // como programa #ID#{
        // #ID#(){
        cambiarSetup_Loop();

        // Almacena las variables y su valor en un mapa
        // almacenarTiposDeDato();

        // Cambia las palabras de ElecatPlus a sus equivalentes en Arduino
        cambiarPalabras();

        // Cambia el delay
        cambiarDelay();

        // Cambia la declaracion de componentes a declaraciones de pines y
        // tipos de OUTPUT o INPUT según sea el caso
        // cambiarComponentes();

        // Cambia a las acciones por sus equivalentes en Arduino
        cambiarAcciones();

        // Cambiar asignaciones
        cambiarAsignaciones();

    }

    private void cambiarAsignaciones() {
        String lineas[] = codigo.split("\n");
        codigo = codigo.replace("(", "");
        codigo = codigo.replace(" )", "");
        codigo = codigo.replace(")", "");
        String aux = "";
        // Ciclo para recorrer cada línea
        for (int i = 0; i < lineas.length; i++) {
            String[] palabras = lineas[i].split(",");

            if (palabras.length != 5) {
                aux += lineas[i] + "\n";
                continue;
            }

            if (palabras[1].equals("int")
                    || palabras[1].equals("float")
                    || palabras[1].equals("boolean")
                    || palabras[1].equals("String")){
                String[] palabrasAsignacion = lineas[i + 1].split(",");
                if (palabrasAsignacion[1].equals("=")){
                    lineas[i] = palabras[1] + " " + palabras[2] + "=" + palabrasAsignacion[3] + ";";
                    lineas[i+1] = "";
                }
            }
            aux += lineas[i] + "\n";
        }
        codigo = aux;
}

    private void cambiarAcciones() {
        String lineas[] = codigo.split("\n");
        codigo = codigo.replace("(", "");
        codigo = codigo.replace(" )", "");
        codigo = codigo.replace(")", "");
        String aux = "";
        // Ciclo para recorrer cada línea
        for (int i = 0; i < lineas.length; i++) {
            // Dividir cada línea en palabras
            String[] palabras = lineas[i].split(",");

            if (palabras.length != 5) {
                aux += lineas[i] + "\n";
                continue;
            }

            // Para el led
            if (palabras[1].equals("encender"))
                lineas[i] = "digitalWrite(" + palabras[2] + ",HIGH);";
            if (palabras[1].equals("apagar"))
                lineas[i] = "digitalWrite(" + palabras[2] + ",LOW);";

            // Para el buzzer
            if (palabras[1].equals("sonar"))
                lineas[i] = "tone(" + palabras[2] + "," + palabras[3] + "," + palabras[4] + ");";

            // Para el display
            if (palabras[1].equals("escribir")) {
                String cadenaComilla = palabras[3];
                int tamCadena = cadenaComilla.length();
                if (tamCadena > 16) {
                    String renglon1 = cadenaComilla.substring(0, 16);
                    String renglon2 = cadenaComilla.substring(16, tamCadena);
                    lineas[i] = palabras[2] + ".clear(); \n" +
                            palabras[2] + ".print(" + renglon1 + ");\n" +
                            palabras[2] + ".setCursor(0, 1);\n" +
                            palabras[2] + ".print(" + renglon2 + ");";
                } else
                    lineas[i] = palabras[2] + ".clear(); \n" +
                            palabras[2] + ".print(" + cadenaComilla + ");";
            }

            // Para el servo
            if (palabras[1].equals("girar"))
                lineas[i] = palabras[2] + ".write(" + palabras[3] + ");";

            // Para el sensor distancia
            if (palabras[1].equals("detectar"))
                lineas[i] = palabras[2] + "=digitalRead(" + palabras[3] + ");";

            aux += lineas[i] + "\n";
        }
        codigo = aux;
    }

    private void cambiarDelay() {
        String lineas[] = codigo.split("\n");
        codigo = codigo.replace("(", "");
        codigo = codigo.replace(" )", "");
        codigo = codigo.replace(")", "");
        String aux = "";
        // Ciclo para recorrer cada línea
        for (int i = 0; i < lineas.length; i++) {
            // Dividir cada línea en palabras
            String[] palabras = lineas[i].split(",");

            if (palabras.length != 5) {
                aux += lineas[i] + "\n";
                continue;
            }

            // Si la segunda palabra es setup directamente hace la
            // transformacion
            if (palabras[1].equals("delay")) {
                lineas[i] = "delay(" + palabras[2] + ");";
            }

            aux += lineas[i] + "\n";
        }
        codigo = aux;
    }

    private void cambiarSetup_Loop() {
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
            if (palabras[1].equals("setup")) {
                lineas[i] = "void setup{";
                nombreSetup = palabras[2];
            }

            if (palabras[1].equals("}") && palabras[2].equals(nombreSetup))
                lineas[i] = "}";

            if (palabras[1].equals("loop"))
                lineas[i] = "void loop(){";

            if (palabras[2].equals("ejecutar") && palabras[1].equals("}"))
                lineas[i] = "}";

            if (palabras[1].equals("funcion")) {
                String[] palabrasFuncion = lineas[i + 1].split(",");
                if (palabrasFuncion[1].equals("entero")){
                    lineas[i] = "void " + palabras[2] + "(" + "int " + palabrasFuncion[2] + "){";
                    
                }
            }

            if (palabras[1].equals("}"))
                lineas[i] = "}";

            aux += lineas[i] + "\n";
        }
        codigo = aux;
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
                if (palabras[j].equals("entero"))
                    palabras[j] = "int";
                // Entero
                if (palabras[j].equals("booleano"))
                    palabras[j] = "boolean";
                // cadena
                if (palabras[j].equals("cadena"))
                    palabras[j] = "String";
                // decimal
                if (palabras[j].equals("decimal"))
                    palabras[j] = "float";
                // mientras
                if (palabras[j].equals("mientras"))
                    palabras[j] = "while";
                // mientras
                if (palabras[j].equals("para"))
                    palabras[j] = "for";
                // and
                if (palabras[j].equals("and"))
                    palabras[j] = "&&";
                // or
                if (palabras[j].equals("or"))
                    palabras[j] = "||";
                // si
                if (palabras[j].equals("si"))
                    palabras[j] = "if";
                // sino
                if (palabras[j].equals("sino"))
                    palabras[j] = "else";
                // esperar
                if (palabras[j].equals("esperar"))
                    palabras[j] = "delay";
                // elegir
                if (palabras[j].equals("elegir"))
                    palabras[j] = "switch";
                // romper
                if (palabras[j].equals("romper"))
                    palabras[j] = "break";
            }
            // Construir la nueva línea con las palabras modificadas
            lineas[i] = String.join(",", palabras) + "\n";
            aux += lineas[i];
        }
        codigo = aux;
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
            int respuesta = JOptionPane.showConfirmDialog(null, "El codigo máquina ha sido generado correctamente",
                    "¿Deseas abrir el código para anlizarlo?", JOptionPane.YES_NO_OPTION);
            // Si la respuesta es sí, verifica el archivo más no ejecuta automaticamente
            if (respuesta == 0) {
                d.open(archivo);
            } else {
                // Compilar
                ProcessBuilder compiladoBuilder = new ProcessBuilder("arduino-cli", "compile", "-b", "arduino:avr:uno",
                        rutaArchivo);
                compiladoBuilder.redirectErrorStream(true);
                Process procesoCompilado = compiladoBuilder.start();
                procesoCompilado.waitFor();
                // Subir
                ProcessBuilder subidoBuilder = new ProcessBuilder("arduino-cli", "upload", "-p", "COM6", "--fqbn",
                        "arduino:avr:uno", rutaArchivo);
                subidoBuilder.redirectErrorStream(true);
                Process procesoSubido = subidoBuilder.start();
                int exitCodeSubido = procesoSubido.waitFor();
                // Continuar con el resto del código después de que ambos procesos hayan
                // terminado
                System.out.println("\nSe ha cargado el codigo al arduino...");
            }
        } catch (IOException | InterruptedException ioe) {
            System.out.println(ioe);
        }
    }
}
