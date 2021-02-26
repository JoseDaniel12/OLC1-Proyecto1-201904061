/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compi1_proyecto1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Alvarado
 */
public class Conjunto {
    public String nombre;
    public String notacion;
    public List<String> elementos = new ArrayList<>();

    public Conjunto(String nombre, String notacion) {
        this.nombre = nombre;
        this.notacion = notacion;
        if (this.notacion.indexOf(",") != -1) {
            String[] elementos = this.notacion.split(",");
            for (int i = 0; i < elementos.length; i++) {
                this.elementos.add(elementos[i]);
            }
        } else {
            int asciiInicial = (int)(char)this.notacion.split("-")[0].charAt(0);
            int asciiFinal = (int)(char)this.notacion.split("-")[1].charAt(0);
            for (int i = 0; i < asciiInicial; i++) {
                this.elementos.add(Character.toString((char) i));
            }
        }
        
        
    }
    
}
