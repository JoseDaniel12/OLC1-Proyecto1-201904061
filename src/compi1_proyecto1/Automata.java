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
    String nombre = "";
    List<String> alfabeto = new ArrayList<>();
    String inicio = "S0";
    List<String> estados = new ArrayList<>();
    List<String> estadosAceptacion = new ArrayList<>();
    List<List<String>> transiciones = new ArrayList<>();

    public Automata(TablaTransiciones tabla, HashMap<String, String> terminales) {
        this.nombre = tabla.nombre;
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

    public void graficar() throws InterruptedException {
        String nombre = this.nombre;
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
            Process proc = rt.exec("dot -Tjpg " + nombre + ".dot -o ./AFD_201904061/" + nombre + ".jpg");
            int exitVal = proc.waitFor();
            File f = new File(nombre + ".dot");
            f.delete();
        } catch (IOException e) {
            System.out.println("error, no se realizo el archivo");
        }
    }

    public ArrayList<ArrayList<String>> findWays(String valorActual, String estadoActual, List<List<String>> transiciones, String cadena) {
        ArrayList<ArrayList<String>> ways = new ArrayList<>();
        String transicionApuntadora = estadoActual;
        for (List<String> transicion : transiciones) {
            if (transicionApuntadora.equals(transicion.get(0))) {
                boolean bandera = true;
                for (Conjunto conj : AppState.conjuntos) {
                    if (transicion.get(1).contains(conj.nombre)) {
                        bandera = false;
                        for (String elemento : conj.elementos) {
                            String nuevoEstadoAnterior = estadoActual;
                            String nuevoValorActual = valorActual + "" + elemento;
                            String nuevoEstadoActual = transicion.get(2);
                            ArrayList<String> lista = new ArrayList<String>();
                            lista.add(nuevoEstadoAnterior);
                            lista.add(nuevoValorActual);
                            lista.add(nuevoEstadoActual);
                            if (cadena.contains(nuevoValorActual)) {
                                ways.add(lista);
                            }
                        }
                    }
                }

                if (bandera) {
                    String nuevoEstadoAnterior = estadoActual;
                    String nuevoValorActual = valorActual + "" + transicion.get(1);
                    String nuevoEstadoActual = transicion.get(2);
                    ArrayList<String> lista = new ArrayList<String>();
                    lista.add(nuevoEstadoAnterior);
                    lista.add(nuevoValorActual);
                    lista.add(nuevoEstadoActual);
                    if (cadena.contains(nuevoValorActual)) {
                        ways.add(lista);
                    }
                }
            }
        }
        return ways;
    }

    public boolean validarCadena(String cadena) {
        String estadoAnterior = this.inicio;
        String valorActual = "";
        String estadoActual = this.inicio;
        ArrayList<ArrayList<ArrayList<String>>> caminos = new ArrayList<ArrayList<ArrayList<String>>>();
        ArrayList<String> lista1 = new ArrayList<>();
        lista1.add(estadoAnterior);
        lista1.add(valorActual);
        lista1.add(estadoActual);
        ArrayList<ArrayList<String>> lista2 = new ArrayList<>();
        lista2.add(lista1);
        caminos.add(lista2);
        for (int nProfundiad = 0; nProfundiad < cadena.length(); nProfundiad++) {
            ArrayList<ArrayList<String>> nivel = new ArrayList<>();
            ArrayList<ArrayList<String>> ultimoNivel = caminos.get(caminos.size() - 1);
            for (ArrayList<String> nodo : ultimoNivel) {
                valorActual = nodo.get(1);
                estadoActual = nodo.get(2);
                nivel.addAll(this.findWays(valorActual, estadoActual, this.transiciones, cadena));
            }
            if (nivel.size() != 0) {
                caminos.add(nivel);
            }
        }
        for (ArrayList<String> nodo : caminos.get(caminos.size() - 1)) {
            if (cadena.length() > 0) {
                if (cadena.equals(nodo.get(1)) && this.estadosAceptacion.contains(nodo.get(2))) {
                    return true;
                }
            }
        }
        return false;
    }
}
