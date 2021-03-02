package compi1_proyecto1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author José Alvarado
 */
public class Conjunto {
    public String nombre;
    public List<String> elementos = new ArrayList<>();

    public Conjunto(String nombre, String[] rango) {
        this.nombre = nombre;
        int inicio  = rango[0].toCharArray()[0];        
        int fin = rango[1].toCharArray()[0];
        for (int i = inicio; i <= fin; i++) {
            elementos.add(Character.toString(((char)i)));
        }
    }
    
    public Conjunto(String nombre, List<String> elementos) {
        this.nombre = nombre;
        for (String elemento : elementos) {
            this.elementos.add(elemento);
        }
    }
    
}
