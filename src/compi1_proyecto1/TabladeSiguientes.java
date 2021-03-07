package compi1_proyecto1;

import static compi1_proyecto1.AppState.hojas;
import static compi1_proyecto1.AppState.vaciarCarpeta;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author José Alvarado
 */
class FilaSiguientes {

    String hojaValor = "";
    String id = "";
    List<String> siguientes = new ArrayList<>();

    public FilaSiguientes() {
    }

    public FilaSiguientes(String hojaValor, String id, List<String> siguientes) {
        this.hojaValor = hojaValor;
        this.id = id;
        this.siguientes = siguientes;
    }

}

public class TabladeSiguientes {

    String nombre = "";
    public List<FilaSiguientes> filas = new ArrayList<>();

    public TabladeSiguientes(Nodo arbol, HashMap<String, String> hojas) {
        this.nombre = arbol.nombre;
        crearTabladeSiguientes(arbol, hojas);
        List<FilaSiguientes> ordenado = new ArrayList<>();
        FilaSiguientes temp;
        for (int a = 0; a < this.filas.size(); a++) {
            for (int i = 0; i < this.filas.size() - 1; i++) {
                if (Integer.parseInt(this.filas.get(i).id) > Integer.parseInt(this.filas.get(i + 1).id)) {
                    temp = this.filas.get(i);
                    this.filas.set(i, this.filas.get(i + 1));
                    this.filas.set(i+1, temp);
                }
            }
        }
    }

    public void crearTabladeSiguientes(Nodo raiz, HashMap<String, String> hojas) {
        Nodo c1 = null;
        Nodo c2 = null;
        if (raiz == null) {
            return;
        } else if (".".equals(raiz.valor)) {
            c1 = raiz.hizq;
            c2 = raiz.hder;
        } else if ("*".equals(raiz.valor) || "+".equals(raiz.valor)) {
            c1 = raiz.hizq;
            c2 = raiz.hizq;
        }

        if (c1 != null && c2 != null) {
            for (String last : c1.lasts) {
                for (String first : c2.firsts) {
                    boolean hojaIngresada = false;
                    for (FilaSiguientes fila : this.filas) {
                        if (fila.id.equals(last)) {
                            hojaIngresada = true;
                            fila.siguientes.add(first);
                            fila.siguientes.sort(null);
                            break;
                        }
                    }
                    if (!hojaIngresada) {
                        FilaSiguientes fila = new FilaSiguientes();
                        fila.hojaValor = hojas.get(last);
                        fila.id = last;
                        fila.siguientes.add(first);
                        fila.siguientes.sort(null);
                        this.filas.add(fila);
                    }

                }
            }
        }

        crearTabladeSiguientes(raiz.hizq, hojas);
        crearTabladeSiguientes(raiz.hder, hojas);
    }

    public void graficar() throws InterruptedException {
        String nombre = this.nombre;
        filas.add(new FilaSiguientes("#", (filas.size() + 1) + "", Arrays.asList("--")));
        FileWriter fw;
        PrintWriter pw;
        try {
            fw = new FileWriter("./" + nombre + ".dot");
            pw = new PrintWriter(fw);
            pw.println("digraph G{");
            pw.println("graph[pad=0.5, nodesep=0.5, ranksep=2]");
            pw.println("node[shape=plain]");
            pw.println("Foo [label=<");
            pw.println("<table border=\"0\" cellborder=\"1\" cellspacing=\"0\" cellpadding=\"3\">");
            pw.println("<tr>");
            pw.println("<td>");
            pw.println("CARACTER");
            pw.println("</td>");
            pw.println("<td>");
            pw.println("ID");
            pw.println("</td>");
            pw.println("<td>");
            pw.println("SIGUIENTES");
            pw.println("</td>");
            pw.println("</tr>");
            for (FilaSiguientes fila : filas) {
                pw.println("<tr>");
                pw.println("<td>");
                pw.println(fila.hojaValor);
                pw.println("</td>");
                pw.println("<td>");
                pw.println(fila.id);
                pw.println("</td>");
                pw.println("<td>");
                pw.println(fila.siguientes.toString().substring(1, fila.siguientes.toString().length() - 1));
                pw.println("</td>");
                pw.println("</tr>");
            }
            pw.println("</table>>;");
            pw.println("];");
            pw.println("}");
            fw.close();
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("dot -Tjpg " + nombre + ".dot -o ./SIGUIENTES_201904061/" + nombre + ".jpg");
            int exitVal = proc.waitFor();
            File f = new File(nombre + ".dot");
            f.delete();
        } catch (IOException e) {
            System.out.println("error, no se realizo el archivo");
        }
    }

}
