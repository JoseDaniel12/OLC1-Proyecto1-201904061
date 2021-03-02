package compi1_proyecto1;

import static compi1_proyecto1.AppState.vaciarCarpeta;
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
public class Automata {

    List<String> alfabeto = new ArrayList<>();
    String inicio = "S0";
    List<String> estados = new ArrayList<>();
    List<String> estadosAceptacion = new ArrayList<>();
    List<List<String>> transiciones = new ArrayList<>();

    public Automata(TablaTransiciones tabla, HashMap<String, String> terminales) {
        for (FilaTransiciones fila : tabla.filas) {
            if (!this.estados.contains(fila.estdoId)) {
                this.estados.add(fila.estdoId);
            }
            fila.transiciones.forEach((terminal, estado) -> {
                if (terminal != "#") {
                    if (!this.alfabeto.contains(terminal)) {
                        this.alfabeto.add(terminal);
                    }
                    List<String> transicion = new ArrayList<>();
                    transicion.add(fila.estdoId);
                    transicion.add(terminal);
                    transicion.add(estado);
                    this.transiciones.add(transicion);
                }
            });
            for (String elemento : fila.elemntosEstado) {
                if (elemento.equals(terminales.size() + "")) {
                    this.estadosAceptacion.add(fila.estdoId);
                }
            }
        }
    }

    public void graficar(String nombre) throws InterruptedException {
        FileWriter fw;
        PrintWriter pw;
        try {
            fw = new FileWriter("./" + nombre + ".dot");
            pw = new PrintWriter(fw);
            pw.println("digraph G{");
            pw.println("rankdir=LR");
            pw.println("size=\"8,5\"");
            String texto = "node [shape = doublecircle]; ";
            for (String estadoAcep : this.estadosAceptacion) {
                texto += estadoAcep + " ";
            }
            texto += ";";
            pw.println(texto);
            pw.println("node[shape=circle];");
            for (List<String> transicion : this.transiciones) {
                pw.println(transicion.get(0) + " -> " + transicion.get(2) + "[ label = \"" + transicion.get(1).replace("\"", "") + "\" ];");
            }
            pw.println("}");
            fw.close();
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("dot -Tjpg " + nombre + ".dot -o ./automatas/" + nombre + ".jpg");
            int exitVal = proc.waitFor();
            File f = new File(nombre + ".dot");
            f.delete();
        } catch (IOException e) {
            System.out.println("error, no se realizo el archivo");
        }
    }
}
