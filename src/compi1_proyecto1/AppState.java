package compi1_proyecto1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author José Alvarado
 */
public class AppState {
    public static File filePath = null;
    public static String texto = "";
    public static List<HashMap<String, String>> hojas = new ArrayList<>();
    public static List<Nodo> arboles = new ArrayList<>();
    public static List<Conjunto> conjuntos = new ArrayList<>();
    public static ArrayList<TabladeSiguientes> tablasdeSiguientes = new ArrayList<>();
    public static ArrayList<TablaTransiciones> tablasdeTransiciones = new ArrayList<>();


    public static void graficarArboles() {
        vaciarCarpeta(new File("./arboles"));
        FileWriter fw;
        PrintWriter pw;
        for (int i = 0; i < arboles.size(); i++) {
            Nodo arbol = arboles.get(i);
            String nombre = "arbol_" + i;
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
                Process proc = rt.exec("dot -Ttiff " + nombre + ".dot -o ./arboles/" + nombre + ".jpg");
                int exitVal = proc.waitFor();
                File f = new File(nombre + ".dot");
                f.delete();
            } catch (IOException | InterruptedException e) {
                System.out.println("error, no se realizo el archivo");
            }
        }
    }
    
    public static void crearTablasdeSiguientes() {
        int contador  = 0;
        for (Nodo arbol : arboles) {
            tablasdeSiguientes.add(new TabladeSiguientes(arbol, hojas.get(contador)));
            contador++;
        }
    }
    
    public static void graficarTablasdeSiguientes() throws InterruptedException {
        for (int i = 0; i < tablasdeSiguientes.size(); i++) {
            String nombre = "tablaSiguientes_" + i;
            tablasdeSiguientes.get(i).graficar(nombre);
        }
    }
    
    public static void crearTablasdeTransiciones() {
        for (int i = 0; i < tablasdeSiguientes.size(); i++) {
            Nodo arbol = arboles.get(i);
            tablasdeTransiciones.add(new TablaTransiciones(arbol, hojas.get(i), tablasdeSiguientes.get(i)));
        }
        
        for (TablaTransiciones t : tablasdeTransiciones) {
            for (FilaTransiciones fila : t.filas) {
                System.out.println(fila.estdoId);
            }
            System.out.println("------------");
        }
    }

    public static void vaciarCarpeta(File ruta) {
        if (ruta.isDirectory()) {
            for (File f : ruta.listFiles()) {
                f.delete();
            }
        }
    }
}
