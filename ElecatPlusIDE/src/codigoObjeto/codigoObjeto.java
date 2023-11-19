package codigoObjeto;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import elecatpluside.IDE;

public class codigoObjeto {

    String codigo;
    // Definir la estructura de datos
    Map<String, String> componentes = new HashMap<>();


    public codigoObjeto(String codigo) {
        this.codigo = codigo;
    }

    public void crearCodigoObjeto(String nombre) {
        eliminarEspacios();
        transformarArduino();
        crearArchivo(nombre);
    }

    private void eliminarEspacios() {
        // Eliminar bloques de código
        // Patrón de expresión regular para encontrar comentarios /* ... */
        String patronComentarios = "/\\*(.|\\n)*?\\*/|//.*?$";
        // Agregar el modificador para que coincida con saltos de línea
        Pattern pattern = Pattern.compile(patronComentarios, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(codigo);
        codigo = matcher.replaceAll("");
        
        //Eliminar lineas comentadas
        
        //Eliminar cadenas vacias antes y despues de cada linea
        String codigoNuevo = "";
        String[] lineasCodigo = codigo.split("\n");
        for (int i = 0; i < lineasCodigo.length; i++)
            codigoNuevo += lineasCodigo[i].trim() + "\n";
        codigo = codigoNuevo;
    }

    private void transformarArduino() {
        cambiarVariables();
        cambiarSetup();
        cambiarLoop();
        cambiarPalabras();
        cambiarComponentes();
        cambiarAcciones();
    }

    private void cambiarComponentes(){
        String codigoNuevo = "";
        // El codigo auxiliar es una forma de tener una copia del codigo
        // para colocar la asignación de variables globales a los componentes
        // en las primeras lineas de código
        String codigoAuxiliar = "";
        String[] lineasCodigo = codigo.split("\n");
        
        for (int i = 0; i < lineasCodigo.length; i++) {
            // Agrega al hashmap el componente y su pin
            // Siempre y cuando se cumpla la siguiente gramatica
            // COMPONENTE ID '=' PIN ';'
            String declaracionPinComponente = "";
            if (lineasCodigo[i].contains("led ")){                
                String id = lineasCodigo[i].split("=")[0].split(" ")[1];
                String pin = lineasCodigo[i].split("=")[1].replaceAll("\\D","");
                String componente = lineasCodigo[i].split(" ")[0];
                componentes.put(id, componente);

                // Esta parte del codigo pone hasta arriba el nombre del componente
                // y su respectiva asignacion ledIzquierda = 1;
                declaracionPinComponente = "int "+id+"="+pin+";\n";
                codigoAuxiliar = codigoNuevo;
                
                codigoNuevo = declaracionPinComponente;
                codigoNuevo += codigoAuxiliar;
                lineasCodigo[i] = "pinMode("+id+",OUTPUT);";
            }

            if (lineasCodigo[i].contains("buzzer ")){                
                String id = lineasCodigo[i].split("=")[0].split(" ")[1];
                String pin = lineasCodigo[i].split("=")[1].replaceAll("\\D","");
                String componente = lineasCodigo[i].split(" ")[0];
                componentes.put(id, componente);

                // Esta parte del codigo pone hasta arriba el nombre del componente
                // y su respectiva asignacion ledIzquierda = 1;
                declaracionPinComponente = "int "+id+"="+pin+";\n";
                codigoAuxiliar = codigoNuevo;
                
                codigoNuevo = declaracionPinComponente;
                codigoNuevo += codigoAuxiliar;
                lineasCodigo[i] = "pinMode("+id+",OUTPUT);";
            }

            
            codigoNuevo += lineasCodigo[i] + "\n";
        }
        codigo = codigoNuevo;
    }

    private void cambiarAcciones(){
        String codigoNuevo="";
        String id;
        String accion;
        String[] lineasCodigo = codigo.split("\n");
            for (int i = 0; i < lineasCodigo.length; i++) {
                if(lineasCodigo[i].contains("accion")){
                    id = lineasCodigo[i].split("accion\\(")[1].split(",")[0];
                    accion = lineasCodigo[i].split("accion\\(")[1].split(",")[1].replace(");","").trim();
                    String componente = componentes.get(id);
                    if(componente.equals("led") && accion.equals("encender()"))
                            lineasCodigo[i] = "digitalWrite("+id+",HIGH);";
                    if(componente.equals("led") && accion.equals("apagar()"))
                            lineasCodigo[i] = "digitalWrite("+id+",LOW);";
                }
                
                codigoNuevo += lineasCodigo[i] + "\n";
            }
        codigo = codigoNuevo;
    }

    private void cambiarPalabras(){
        // esperar - delay
        codigo = codigo.replaceAll("esperar\\((\\d+)\\);", "delay($1);");
        // entero - int
        codigo = codigo.replaceAll("entero", "int");
        // cadena - string
        codigo = codigo.replaceAll("cadena", "string");
        // si - if
        codigo = codigo.replaceAll("si\\(", "if(");
        // mientras - while
        codigo = codigo.replaceAll("repetir mientras\\(", "while(");
        // para - for
        codigo = codigo.replaceAll("repetir para\\(", "for(");    
        // sino - else
        codigo = codigo.replaceAll("sino\\{", "else{");
        // continuar - continue
        codigo = codigo.replaceAll("continuar\\;", "continue;");
        // romper - break
        codigo = codigo.replaceAll("romper\\;", "break;");
        // funcion - void
        codigo = codigo.replaceAll("funcion ", "void ");
    }

    private void cambiarSetup() {
        String setup = "";
        // Patrón de expresión regular para encontrar el texto entre "programa" y las
        // llaves
        Pattern patron = Pattern.compile("programa\\s*(.*?)\\s*\\{");
        // Crear un objeto Matcher
        Matcher matcher = patron.matcher(codigo);
        // Verificar si se encuentra la coincidencia

        if (matcher.find()) {
            // Obtener el texto entre "programa" y las llaves
            String codigoNuevo = "";
            setup = matcher.group(1).trim();
            String[] lineasCodigo = codigo.split("\n");
            for (int i = 0; i < lineasCodigo.length; i++) {
                // Cambia el ejecutar por el void loop
                if (lineasCodigo[i].equals(setup + "(){"))
                    lineasCodigo[i] = "void setup(){";
                codigoNuevo += lineasCodigo[i] + "\n";
            }
            codigo = codigoNuevo;
        }
        
    }

    private void cambiarLoop() {
        String codigoNuevo = "";
        String[] lineasCodigo = codigo.split("\n");
        // Se inicia desde la linea 1 para eliminar programa ID {
        // Se termina una linea antes para eliminar la llave de cierre
        for (int i = 1; i < lineasCodigo.length-1; i++) {
            // Cambia el ejecutar por el void loop
            if (lineasCodigo[i].equals("ejecutar(){"))
                lineasCodigo[i] = "void loop(){";

            codigoNuevo += lineasCodigo[i] + "\n";
        }
        codigo = codigoNuevo;
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

    private void crearArchivo(String nombre) {
        // Nombre del archivo .ino
        String nombreArchivo = nombre.split(".ecp - ElecatPlus IDE 1.0")[0];
        String rutaDirectorio = System.getProperty("user.dir") + "\\ElecatPlusIDE\\" + "Codigo objeto\\"
                + nombreArchivo;
        String rutaArchivo = System.getProperty("user.dir") + "\\ElecatPlusIDE\\" + "Codigo objeto\\" + nombreArchivo
                + "\\" + nombreArchivo + ".ino";

        File archivo = new File(rutaArchivo);
        File directorio = new File(rutaDirectorio);
        Desktop d = Desktop.getDesktop();
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
            d.open(archivo);
        } catch (Exception ex) {
            System.out.println("\n Hubo un error al generar el codigo objeto " + ex);
        }
    }

}
