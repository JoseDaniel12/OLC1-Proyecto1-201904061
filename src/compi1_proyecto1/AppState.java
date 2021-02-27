/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compi1_proyecto1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Alvarado
 */
public class AppState {

    public static File filePath = null;
    public static String texto = "";
    public static List<Nodo> arboles = new ArrayList<>();
    public static List<Conjunto> conjuntos = new ArrayList<>();
    
    public static void graficarArboles() {
        FileWriter fw;
        PrintWriter pw;
        for (int i = 0; i < arboles.size(); i++) {
            Nodo arbol = arboles.get(i);
            try {
                String nombre = "arbol_" + i;
                fw = new FileWriter("./" + nombre + ".dot");
                pw = new PrintWriter(fw);
                pw.println("digraph G{");
                pw.println("rankdir=UD");
                pw.println("node[shape=record]");
                pw.println("concentrate=true");
                pw.println(arbol.getDotText());
                pw.println("}");
                fw.close();
                vaciarCarpeta(new File("./arboles"));
                String cmd = "dot -Ttiff " + nombre + ".dot -o ./arboles/" + nombre + ".jpg";
                Runtime.getRuntime().exec(cmd);
            } catch (Exception e) {
                System.out.println("error, no se realizo el archivo");
            }
        }
    }
    
    public static void vaciarCarpeta(File ruta) {
        if (!ruta.exists()){return;}
        if(ruta.isDirectory()) {
            for (File f : ruta.listFiles()) {
                vaciarCarpeta(f);
            }
        }
    }
}
