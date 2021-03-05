package compi1_proyecto1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author José Alvarado
 */
public class AppState {

    public static File filePath = null;
    public static String texto = "";
    public static List<HashMap<String, String>> hojas = new ArrayList<>();
    public static List<Nodo> arboles = new ArrayList<>();
    public static List<Conjunto> conjuntos = new ArrayList<>();
    public static List<Evaluacion> evaluaciones = new ArrayList<>();
    public static ArrayList<TabladeSiguientes> tablasdeSiguientes = new ArrayList<>();
    public static ArrayList<TablaTransiciones> tablasdeTransiciones = new ArrayList<>();
    public static ArrayList<Automata> automatas = new ArrayList<>();
    public static ArrayList<Errorr> errores = new ArrayList<>();

    public static void graficarArboles() {
        vaciarCarpeta(new File("./arboles"));
        FileWriter fw;
        PrintWriter pw;
        for (int i = 0; i < arboles.size(); i++) {
            Nodo arbol = arboles.get(i);
            String nombre = arbol.nombre;
            try {
                fw = new FileWriter("./" + nombre + ".dot");
                pw = new PrintWriter(fw);
                pw.println("digraph G{");
                pw.println("rankdir=UD");
                pw.println("node[shape=record]");
                pw.println("concentrate=true");
                pw.println(arbol.getDotText());
                pw.println("}");
                fw.close();
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec("dot -Tjpg " + nombre + ".dot -o ./arboles/" + nombre + ".jpg");
                int exitVal = proc.waitFor();
                File f = new File(nombre + ".dot");
                f.delete();
            } catch (IOException | InterruptedException e) {
                System.out.println("error, no se realizo el archivo");
            }
        }
    }
    
    public static void graficarThomson() throws IOException, InterruptedException {
        vaciarCarpeta(new File("./thomson"));
        for (Nodo arbol : arboles) {
           Thomson.graficar(arbol);
        }
    }

    public static void crearTablasdeSiguientes() {
        vaciarCarpeta(new File("./tablasdeSiguientes"));
        int contador = 0;
        for (Nodo arbol : arboles) {
            tablasdeSiguientes.add(new TabladeSiguientes(arbol, hojas.get(contador)));
            contador++;
        }
    }

    public static void graficarTablasdeSiguientes() throws InterruptedException {
        vaciarCarpeta(new File("./tabladeTransiciones"));
        for (int i = 0; i < tablasdeSiguientes.size(); i++) {
            tablasdeSiguientes.get(i).graficar();
        }
    }

    public static void crearTablasdeTransiciones() {
        for (int i = 0; i < tablasdeSiguientes.size(); i++) {
            Nodo arbol = arboles.get(i);
            tablasdeTransiciones.add(new TablaTransiciones(arbol, hojas.get(i), tablasdeSiguientes.get(i)));
        }
    }

    public static void graficarTablasdeTransiciones() throws InterruptedException {
        for (int i = 0; i < tablasdeTransiciones.size(); i++) {
            tablasdeTransiciones.get(i).graficar();
        }
    }

    public static void crearAutomatas() {
        for (int i = 0; i < tablasdeTransiciones.size(); i++) {
            TablaTransiciones tabla = tablasdeTransiciones.get(i);
            HashMap<String, String> terminales = hojas.get(i);
            automatas.add(new Automata(tabla, terminales));
        }
    }

    public static void graficarAutomatas() throws InterruptedException {
        vaciarCarpeta(new File("./automatas"));
        for (int i = 0; i < automatas.size(); i++) {
            automatas.get(i).graficar();
        }
    }

    public static void vaciarCarpeta(File ruta) {
        if (ruta.isDirectory()) {
            for (File f : ruta.listFiles()) {
                f.delete();
            }
        }
    }

    public static void validarCadenas() throws IOException {
        String json = "[ \n";
        for (Evaluacion ev : evaluaciones) {
            json += "\t{\n";
            for (Automata atm : automatas) {
                if (ev.nombre.contains(atm.nombre) || atm.nombre.contains(ev.nombre) || ev.nombre.equalsIgnoreCase(atm.nombre)) {
                    json += "\t\t\"Valor\":" + "\"" + ev.lexema + "\",\n";
                    json += "\t\t\"ExpresionRegular\":" + "\"" + ev.nombre + "\",\n";

                    String texto = Prueba.Consola.getText() + "\n";
                    texto += "La expresion: " + ev.lexema + ", es ";
                    if (atm.validarCadena(ev.lexema)) {
                        texto += "valida con  la expresion regular: " + ev.nombre + ".\n";
                        json += "\t\t\"Resultado\":" + "\"Cadena Válida\",\n";
                    } else {
                        texto += "No valida con  la expresion regular: " + ev.nombre + ".\n";
                        json += "\t\t\"Resultado\":" + "\"Cadena No Válida\",\n";
                    }
                    Prueba.Consola.setText(texto);
                }
            }
            json += "\t},\n";
        }
        json += "]";
        FileWriter archivo = new FileWriter("./json.json");
        archivo.write(json);
        archivo.close();
    }
    
    public static void reportar() throws IOException {
        String texto = "<table>\n";
        texto += "\t<tr>\n";
        texto += "\t\t<td>#</td>\n";
        texto += "\t\t<td>Tipo de Error</td>\n";
        texto += "\t\t<td>Descripcion</td>\n";
        texto += "\t\t<td>Linea</td>\n";
        texto += "\t\t<td>Columna</td>\n";
        texto += "\t</td>";
        int contador  = 0;
        for (Errorr errorr : errores) {
            texto+= "\t<tr>\n";
            texto+= "\t\t<td>" + (contador++) +  "</td>\n";
            texto+= "\t\t<td>" + errorr.tipo +  "</td>\n";
            texto+= "\t\t<td>" + errorr.descripcion +  "</td>\n";
            texto+= "\t\t<td>" + errorr.linea +  "</td>\n";
            texto+= "\t\t<td>" + errorr.columna +  "</td>\n";
            texto+= "\t</td>";
        }
        texto += "</table>";
        FileWriter archivo = new FileWriter("./Reporte de Eerrores.html");
        archivo.write(texto);
        archivo.close();
    }

    public static void reiniciar() {
        filePath = null;
        texto = "";
        hojas = new ArrayList<>();
        arboles = new ArrayList<>();
        conjuntos = new ArrayList<>();
        evaluaciones = new ArrayList<>();
        tablasdeSiguientes = new ArrayList<>();
        tablasdeTransiciones = new ArrayList<>();
        automatas = new ArrayList<>();
        errores = new ArrayList<>();
    }
}
