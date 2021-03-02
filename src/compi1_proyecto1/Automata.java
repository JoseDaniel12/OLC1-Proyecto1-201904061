package compi1_proyecto1;

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
       for(FilaTransiciones fila : tabla.filas) {
            if (!this.estados.contains(fila.estdoId))
                this.estados.add(fila.estdoId);
            fila.transiciones.forEach((terminal, estado) -> {
                if (!this.alfabeto.contains(terminal) && terminal != "#") 
                    this.alfabeto.add(terminal);
                    List<String> transicion = new ArrayList<>();
                    transicion.add(fila.estdoId);
                    transicion.add(terminal);
                    transicion.add(estado);
                    this.transiciones.add(transicion);
            });
            for (String elemento : fila.elemntosEstado) {
                if (elemento.equals(terminales.size() + "")) {
                    this.estadosAceptacion.add(fila.estdoId);
                }
            }
       }
    }

}
