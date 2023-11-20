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
    String nombre;
    String codigo;
    String ruta;
    // Definir la estructura de datos
    Map<String, String> componentes = new HashMap<>();
    Map<String, String> cadenas = new HashMap<>();
    Map<String, String> enteros = new HashMap<>();

    public codigoObjeto(String codigo, String ruta) {
        this.codigo = codigo;
        this.ruta = ruta;
    }

    public void crearCodigoObjeto(String nombre) {
        eliminarEspacios();
        transformarArduino();
        crearArchivo(nombre);
    }

    public void cargarArduino(String nombre) {
        // Nombre del archivo .ino
        String nombreArchivo = nombre.split(".ecp - ElecatPlus IDE 1.0")[0];
        String rutaArchivo = System.getProperty("user.dir") + "\\ElecatPlusIDE\\" + "CodigoObjeto\\" + nombreArchivo
                + "\\" + nombreArchivo + ".ino";

        File archivo = new File(rutaArchivo);
        Desktop d = Desktop.getDesktop();
        try {
            d.open(archivo);
            String compilado = "arduino-cli compile -b arduino:avr:uno " + rutaArchivo;
            Runtime.getRuntime().exec(compilado);

            String subido = "arduino-cli upload -p COM6 --fqbn arduino:avr:uno " + rutaArchivo;
            Runtime.getRuntime().exec(subido);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void eliminarEspacios() {
        // Eliminar bloques de código
        // Patrón de expresión regular para encontrar comentarios /* ... */
        String patronComentarios = "/\\*(.|\\n)*?\\*/|//.*?$";
        // Agregar el modificador para que coincida con saltos de línea
        Pattern pattern = Pattern.compile(patronComentarios, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(codigo);
        codigo = matcher.replaceAll("");

        // Eliminar lineas comentadas

        // Eliminar cadenas vacias antes y despues de cada linea
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
        almacenarTiposDeDato();

        cambiarPalabras();
        cambiarComponentes();
        cambiarAcciones();
    }

    private void almacenarTiposDeDato() {
        String[] lineasCodigo = codigo.split("\n");

        for (int i = 0; i < lineasCodigo.length; i++) {
            // Cadena ID = "texto";
            if (lineasCodigo[i].contains("cadena ") && lineasCodigo[i].contains("=")) {
                String id = lineasCodigo[i].split(" ")[1].trim();
                // Patrón de expresión regular para encontrar texto entre comillas
                Pattern patronComillas = Pattern.compile("\"([^\"]*)\"");
                // Utilizar el patrón para buscar coincidencias
                Matcher matcher = patronComillas.matcher(lineasCodigo[i]);
                if (matcher.find()) {
                    String cadena = matcher.group(1);
                    cadenas.put(id, cadena);
                }
            }
            // Entero ID = ENTERO;
            if (lineasCodigo[i].contains("entero ") && lineasCodigo[i].contains("=")) {
                String id = lineasCodigo[i].split(" ")[1].trim();
                String entero = lineasCodigo[i].split("=")[1].trim().replaceAll("[^0-9]", "").trim();
                // Patrón de expresión regular para encontrar el entero despues del =
                enteros.put(id, entero);
            }
        }
    }

    private void cambiarComponentes() {
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

            // Agregar un led
            if (lineasCodigo[i].contains("led ")) {
                String id = lineasCodigo[i].split("=")[0].split(" ")[1];
                String pin = lineasCodigo[i].split("=")[1].replaceAll("\\D", "");
                String componente = lineasCodigo[i].split(" ")[0];
                componentes.put(id, componente);

                // Esta parte del codigo pone hasta arriba el nombre del componente
                // y su respectiva asignacion ledIzquierda = 1;
                declaracionPinComponente = "int " + id + "=" + pin + ";\n";
                codigoAuxiliar = codigoNuevo;

                codigoNuevo = declaracionPinComponente;
                codigoNuevo += codigoAuxiliar;
                lineasCodigo[i] = "pinMode(" + id + ",OUTPUT);";
            }

            // Agregar un buzzer
            if (lineasCodigo[i].contains("buzzer ")) {
                String id = lineasCodigo[i].split("=")[0].split(" ")[1];
                String pin = lineasCodigo[i].split("=")[1].replaceAll("\\D", "");
                String componente = lineasCodigo[i].split(" ")[0];
                componentes.put(id, componente);

                // Esta parte del codigo pone hasta arriba el nombre del componente
                // y su respectiva asignacion ledIzquierda = 1;
                declaracionPinComponente = "int " + id + "=" + pin + ";\n";
                codigoAuxiliar = codigoNuevo;

                codigoNuevo = declaracionPinComponente;
                codigoNuevo += codigoAuxiliar;
                lineasCodigo[i] = "pinMode(" + id + ",OUTPUT);";
            }

            // Agregar un sensor_distancia
            // Sensor_ultrasonico ID = 10;
            if (lineasCodigo[i].contains("sensor_ultrasonico ")) {
                String id = lineasCodigo[i].split("=")[0].split(" ")[1];
                String pin = lineasCodigo[i].split("=")[1].replaceAll("\\D", "");
                String componente = lineasCodigo[i].split(" ")[0];
                componentes.put(id, componente);

                // Esta parte del codigo pone hasta arriba el nombre del componente
                // y su respectiva asignacion sensorInfrarrojo = 1;
                declaracionPinComponente = "int " + id + "=" + pin + ";\n";
                codigoAuxiliar = codigoNuevo;
                codigoNuevo = declaracionPinComponente;
                codigoNuevo += codigoAuxiliar;
                lineasCodigo[i] = "pinMode(" + id + ",INPUT);";
            }

            // Agregar un boton
            // Boton ID = 3;
            if (lineasCodigo[i].contains("boton ")) {
                String id = lineasCodigo[i].split("=")[0].split(" ")[1];
                String pin = lineasCodigo[i].split("=")[1].replaceAll("\\D", "");
                String componente = lineasCodigo[i].split(" ")[0];
                componentes.put(id, componente);

                // Esta parte del codigo pone hasta arriba el nombre del componente
                // y su respectiva asignacion sensorInfrarrojo = 1;
                declaracionPinComponente = "int " + id + "=" + pin + ";\n";
                codigoAuxiliar = codigoNuevo;
                codigoNuevo = declaracionPinComponente;
                codigoNuevo += codigoAuxiliar;
                lineasCodigo[i] = "pinMode(" + id + ",INPUT);";
            }

            // Agergar un display_lcd
            // Declarar el Liquid.Crystal
            if (lineasCodigo[i].contains("display_lcd ")) {
                String id = lineasCodigo[i].split(" ")[1].trim().replaceAll("[^a-zA-Z]", "");
                String componente = lineasCodigo[i].split(" ")[0].trim();
                componentes.put(id, componente);

                // Esta parte del codigo pone hasta arriba el nombre del componente
                // y su respectiva asignacion ledIzquierda = 1;
                // LiquidCrystal_I2C ID(0x27, 16, 2);
                declaracionPinComponente = "#include <LiquidCrystal_I2C.h>\n" +
                        "LiquidCrystal_I2C " + id + "(0x27, 16, 2);\n";
                codigoAuxiliar = codigoNuevo;

                codigoNuevo = declaracionPinComponente;
                codigoNuevo += codigoAuxiliar;
                lineasCodigo[i] = id + ".init();\n"
                        + id + ".backlight();";
            }
            codigoNuevo += lineasCodigo[i] + "\n";
        }

        codigo = codigoNuevo;
    }

    private void cambiarAcciones() {
        String codigoNuevo = "";
        String id;
        String accion;
        String[] lineasCodigo = codigo.split("\n");
        for (int i = 0; i < lineasCodigo.length; i++) {
            if (lineasCodigo[i].contains("accion")) {
                id = lineasCodigo[i].split("accion\\(")[1].trim().split(",")[0];
                accion = lineasCodigo[i].split("accion\\(")[1].trim().split(",")[1].replace(");", "").trim();
                String componente = componentes.get(id);

                // Para los leds
                // accion '(' ID ',' 'encender' '(' ')' ')' ';'
                if (componente.equals("led") && accion.equals("encender()"))
                    lineasCodigo[i] = "digitalWrite(" + id + ",HIGH);";
                if (componente.equals("led") && accion.equals("apagar()"))
                    lineasCodigo[i] = "digitalWrite(" + id + ",LOW);";

                // Para los buzzer
                // 'accion' '(' ID ',' 'sonar' '(' TONO ',' TIEMPO ')' ')' ';'
                // Tono y tiempo deben ser enteros
                if (componente.equals("buzzer")) {
                    String tono = lineasCodigo[i].split(",")[1].trim().replaceAll("\\D", "");
                    String tiempo = lineasCodigo[i].split(",")[2].trim().replaceAll("\\D", "");
                    lineasCodigo[i] = "tone(" + id + "," + tono + "," + tiempo + ");";
                }

                // Para los display
                // 'accion' '(' ID ',' 'escribir' '(' CUALQUIERA | ID ')' ')' ';'
                if (componente.equals("display_lcd")) {
                    id = lineasCodigo[i].split("accion\\(")[1].trim().split(",")[0];

                    // Verificar si usa ID o usa una cadena
                    // En caso de que sea cadena
                    if (lineasCodigo[i].contains("\"")) {
                        String cadenaComilla = lineasCodigo[i].split("\"")[1].trim();
                        int tamCadena = cadenaComilla.length();
                        if (tamCadena > 16) {
                            String renglon1 = cadenaComilla.substring(0, 16);
                            String renglon2 = cadenaComilla.substring(16, tamCadena);
                            lineasCodigo[i] = id + ".clear(); \n" +
                                    id + ".print(\"" + renglon1 + "\");\n" +
                                    id + ".setCursor(0, 1);\n" +
                                    id + ".print(\"" + renglon2 + "\");";
                        } else
                            lineasCodigo[i] = id + ".clear(); \n" +
                                    id + ".print(\"" + cadenaComilla + "\");";
                    }
                    // Tratamiento de cadena mediante ID
                    else {
                        String ID = lineasCodigo[i].split(",")[1].trim().split("\\(")[1]
                                .replaceAll("[^a-zA-Z0-9]", "");

                        // Ver si es una cadena o un numero
                        String cadenaID = cadenas.get(ID);
                        String enteroID = enteros.get(ID);

                        // Si se encuentra declarado como cadena pero no como numero
                        if (cadenaID != null && enteroID == null) {
                            int tamCadena = cadenaID.length();
                            if (tamCadena > 16) {
                                String renglon1 = cadenaID.substring(0, 16);
                                String renglon2 = cadenaID.substring(16, tamCadena);
                                lineasCodigo[i] = id + ".clear(); \n" +
                                        id + ".print(\"" + renglon1 + "\");\n" +
                                        id + ".setCursor(0, 1);\n" +
                                        id + ".print(\"" + renglon2 + "\");\n";
                            } else
                                lineasCodigo[i] = id + ".clear(); \n" +
                                        id + ".print(\"" + cadenaID + "\");\n";
                        } else if (cadenaID == null && enteroID != null) {
                            int tamCadena = cadenaID.length();
                            if (tamCadena > 16) {
                                String renglon1 = enteroID.substring(0, 16);
                                String renglon2 = enteroID.substring(16, tamCadena);
                                lineasCodigo[i] = id + ".clear(); \n" +
                                        id + ".print(\"" + renglon1 + "\");\n" +
                                        id + ".setCursor(0, 1);\n" +
                                        id + ".print(\"" + renglon2 + "\");\n";
                            } else
                                lineasCodigo[i] = id + ".clear(); \n" +
                                        id + ".print(\"" + enteroID + "\");\n";
                        }
                        // Se usa un identifcador directamente
                        else {
                            lineasCodigo[i] = id + ".clear(); \n" +
                                    id + ".print(" + ID + ");\n";
                        }
                    }
                }

                // Para los sensor infrarrojo
                // 'accion' '(' ID ',' 'detectar' '(' ID ')' ')' ';'
                // Tono y tiempo deben ser enteros
                if (componente.equals("sensor_ultrasonico")) {
                    String variableLectura = lineasCodigo[i].split(",")[1].trim().split("\\(")[1]
                            .replaceAll("[^a-zA-Z0-9]", "");
                    lineasCodigo[i] = variableLectura + "=digitalRead(" + id + ");";
                }

                // Para los sensor botones
                // Se usará temporalmente la accion DETECTAR
                // 'accion' '(' ID ',' 'detectar' '(' ID ')' ')' ';'
                // Tono y tiempo deben ser enteros
                if (componente.equals("boton")) {
                    String variableLectura = lineasCodigo[i].split(",")[1].trim().split("\\(")[1]
                            .replaceAll("[^a-zA-Z0-9]", "");
                    lineasCodigo[i] = variableLectura + "=analogRead(" + id + ");";
                }

            }

            codigoNuevo += lineasCodigo[i] + "\n";
        }
        codigo = codigoNuevo;
    }

    private void cambiarPalabras() {
        // esperar - delay
        codigo = codigo.replaceAll("esperar\\((\\d+)\\);", "delay($1);");
        // entero - int
        codigo = codigo.replaceAll("entero", "int");
        // cadena - string
        codigo = codigo.replaceAll("cadena", "String");
        // cadena - string
        codigo = codigo.replaceAll("booleano", "boolean");
        // cadena - string
        codigo = codigo.replaceAll("decimal", "float");
        // si - if
        codigo = codigo.replaceAll("si\\(", "if(");
        // mientras - while
        codigo = codigo.replaceAll("repetir mientras\\(", "while(");
        // para - for
        codigo = codigo.replaceAll("repetir para\\(", "for(");
        // sino - else
        codigo = codigo.replace("sino", "else");
        // continuar - continue
        codigo = codigo.replaceAll("continuar\\;", "continue;");
        // romper - break
        codigo = codigo.replaceAll("romper\\;", "break;");
        // funcion - void
        codigo = codigo.replaceAll("funcion ", "void ");
        // and - &&
        codigo = codigo.replaceAll(" and ", "&&");
        // or - ||
        codigo = codigo.replaceAll(" or ", "||");
        // verdadero - true
        codigo = codigo.replace("verdadero", "true");
        // falso - false
        codigo = codigo.replace("falso", "false");
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
        for (int i = 1; i < lineasCodigo.length - 1; i++) {
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
        String rutaDirectorio = System.getProperty("user.dir") + "\\ElecatPlusIDE\\" + "CodigoObjeto\\"
                + nombreArchivo;
        String rutaArchivo = System.getProperty("user.dir") + "\\ElecatPlusIDE\\" + "CodigoObjeto\\" + nombreArchivo
                + "\\" + nombreArchivo + ".ino";

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
            cargarArduino(nombre);
        } catch (Exception ex) {
            System.out.println("\n Hubo un error al generar el codigo objeto " + ex);
        }
    }

}
