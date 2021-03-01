package compi1_proyecto1;

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
    int contadorEstados = 0;
    List<FilaTransiciones> filas = new ArrayList<>();

    public TablaTransiciones(Nodo raiz, HashMap<String, String> terminales, TabladeSiguientes tablaSiguientes) {
        this.tablaSiguientes = tablaSiguientes;
        this.terminales = terminales;
        FilaTransiciones filaInicio = new FilaTransiciones();
        filaInicio.elemntosEstado = raiz.firsts;
        filaInicio.estdoId = "S" + (contadorEstados++);
        filaInicio.transiciones = new HashMap<>();
        this.filas.add(filaInicio);
        crearTabladeTransiciones(filaInicio.elemntosEstado);
    }

    public void crearTabladeTransiciones(List<String> elemInicio) {
        if (elemInicio.contains((terminales.size()) + "")) {
            return;
        }
        
        List<String> estado = new ArrayList<>();
        HashMap<String, String> trans = new  HashMap<>();
        // se recoren los Terminales existentes
        for (int i = 0; i < this.terminales.size(); i++) {
            String terminal = this.terminales.get(i + ""); //a ,b, c
            // se recorren los elemenots del estado actual
            for (String elemento: elemInicio) { // 1, 2, 3
                // se verifica que el terminal coincida con el elemento del estado actual
                if (this.terminales.get(elemento).equals(terminal)) { // a,b,c == a,b,c
                    // busco que el id y terminal coincida en la tabla de siguientes
                    for (FilaSiguientes fila : this.tablaSiguientes.filas) {
                        if (fila.id.equals(elemento) && fila.hojaValor.equals(terminal) ) {
                            estado.addAll(fila.siguientes);
                            HashSet hs = new HashSet(); 
                            hs.addAll(estado); 
                            estado.clear(); 
                            estado.addAll(hs);
                        }
                    }
                }
            }
            
            int indice = contiene(estado);
            if (indice != -1) {
                this.filas.get(indice).transiciones.put(terminal, this.filas.get(indice).estdoId);
            } else {
                FilaTransiciones fila = new FilaTransiciones();
                fila.estdoId = "S" + (contadorEstados++);
                fila.elemntosEstado = estado;
                this.filas.add(fila);
            }
        }
        
        crearTabladeTransiciones(this.filas.get(this.filas.size()-1).elemntosEstado);
    }
    
    public int contiene(List<String> elemEstado) {
        int contador  = 0;
        for (FilaTransiciones f : this.filas) {
            if (f.elemntosEstado == elemEstado) {
                return contador;
            }
            contador++;
        }
        return -1;
    }

}
