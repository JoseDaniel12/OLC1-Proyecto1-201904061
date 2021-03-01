package compi1_proyecto1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author José Alvarado
 */
class FilaTransiciones {

    String estdoId = "";
    List<String> elemntosEstado = new ArrayList<>();
    HashMap<String, String> transiciones = new HashMap<>();

    public FilaTransiciones() {
    }
}

public class TablaTransiciones {

    HashMap<String, String> terminales = new HashMap<>();
    TabladeSiguientes tablaSiguientes = null;
    int contadorEstados = 1;
    int cont = 0;
    List<FilaTransiciones> filas = new ArrayList<>();

    public TablaTransiciones(Nodo raiz, HashMap<String, String> terminales, TabladeSiguientes tablaSiguientes) {
        this.tablaSiguientes = tablaSiguientes;
        this.terminales = terminales;
        this.contadorEstados = 1;
        this.cont = 0;
        FilaTransiciones filaInicio = new FilaTransiciones();
        filaInicio.estdoId = "S0";
        filaInicio.elemntosEstado = raiz.firsts;
        filaInicio.transiciones = new HashMap<>();
        this.filas.add(filaInicio);

        crearTabladeTransiciones(filaInicio.elemntosEstado);
        this.filas.remove(this.filas.size() - 1);
    }

    public void crearTabladeTransiciones(List<String> elemInicio) {
        // se recoren los Terminales existentes
        for (int i = 1; i <= this.terminales.size(); i++) {
            List<String> estado = new ArrayList<>();
            String terminal = this.terminales.get(i + ""); //a ,b, c
            // se recorren los elemenots del estado actual
            for (String elemento : elemInicio) { // 1, 2, 3
                // se verifica que el terminal coincida con el elemento del estado actual
                if (this.terminales.get(elemento).equals(terminal)) { // a,b,c == a,b,c
                    // busco que el id y terminal coincida en la tabla de siguientes
                    for (FilaSiguientes fila : this.tablaSiguientes.filas) {
                        if (fila.id.equals(elemento) && fila.hojaValor.equals(terminal)) {
                            estado.addAll(fila.siguientes);
                            HashSet hs = new HashSet();
                            hs.addAll(estado);
                            estado.clear();
                            estado.addAll(hs);
                        }
                    }
                }
            }
            //_________________________________________________
            if (estado.size() != 0) {
                int indice = contiene(estado);
                if (indice != -1) { // SI ya existen los elementos del estado
                    this.filas.get(cont).transiciones.put(terminal, this.filas.get(indice).estdoId);
                } else { // no existen los elemenos del estado
                    this.filas.get(cont).transiciones.put(terminal, "S" + contadorEstados);
                    FilaTransiciones f = new FilaTransiciones();
                    f.estdoId = "S" + (contadorEstados);
                    f.elemntosEstado.addAll(estado);
                    f.transiciones = new HashMap<>();
                    this.filas.add(f);
                    contadorEstados++;
                }
            }
            //______________________________________
        }
        cont++;
        if (!elemInicio.contains((terminales.size()) + "")) {
            crearTabladeTransiciones(this.filas.get(cont).elemntosEstado);
        }
    }

    public int contiene(List<String> elemEstado) {
        int contador = 0;
        for (FilaTransiciones f : this.filas) {
            if (f.elemntosEstado.equals(elemEstado)) {
                return contador;
            }
            contador++;
        }
        return -1;
    }

    public void graficar(String nombre) throws InterruptedException {
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
            pw.println("ESTADO");
            pw.println("</td>");
            List<String> sinRepetir = new ArrayList<>();
            for (int i = 1; i <= this.terminales.size(); i++) {
                if (!"#".equals(this.terminales.get(i + ""))) {
                    sinRepetir.add(this.terminales.get(i + ""));
                }
            }
            HashSet<String> hs = new HashSet<String>(sinRepetir);
            sinRepetir.clear();
            sinRepetir.addAll(hs);

            for (String terminal : sinRepetir) {
                pw.println("<td>");
                pw.println(terminal);
                pw.println("</td>");
            }
            pw.println("</tr>");
            for (FilaTransiciones fila : this.filas) {
                pw.println("<tr>");
                pw.println("<td>");
                pw.println(fila.estdoId + " = " + fila.elemntosEstado.toString());
                pw.println("</td>");
                for (String terminal : sinRepetir) {
                    if (fila.transiciones.get(terminal) != null) {
                        pw.println("<td>");
                        pw.println(fila.transiciones.get(terminal));
                        pw.println("</td>");
                    } else {
                        pw.println("<td>");
                        pw.println("---");
                        pw.println("</td>");
                    }
                }
                pw.println("</tr>");
            }
            pw.println("</table>>;");
            pw.println("];");
            pw.println("}");
            fw.close();
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("dot -Tjpg " + nombre + ".dot -o ./tabladeTransiciones/" + nombre + ".jpg");
            int exitVal = proc.waitFor();
            File f = new File(nombre + ".dot");
            f.delete();
        } catch (IOException e) {
            System.out.println("error, no se realizo el archivo");
        }
    }
}
