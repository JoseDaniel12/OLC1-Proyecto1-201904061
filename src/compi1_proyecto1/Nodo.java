package compi1_proyecto1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author José Alvarado
 */
public class Nodo {
    String nombre = "";
    public String id;
    public String valor;
    public String anunabilidad;
    public List<String> firsts;
    public List<String> lasts;
    public Nodo hizq;
    public Nodo hder;

    public Nodo(String nombre, String id, String valor, String anunabilidad, List<String> firsts, List<String> lasts, Nodo hizq, Nodo hder) {
        this.nombre = nombre;
        this.id = id;
        this.valor = valor;
        this.anunabilidad = anunabilidad;
        this.firsts = firsts;
        this.lasts = lasts;
        this.hizq = hizq;
        this.hder = hder;
    }

    public Nodo(String nombre, String id, String valor, Nodo hizq, Nodo hder) {
        this.nombre = nombre;
        this.id = id;
        this.valor = valor;
        this.anunabilidad = "";
        this.firsts = new ArrayList<>();
        this.lasts = new ArrayList<>();
        this.hizq = hizq;
        this.hder = hder;
        
        switch (valor) {
            case "|":
                this.anunabilidad = ("A".equals(hizq.anunabilidad) || "A".equals(hder.anunabilidad)) ? "A" : "N";
                this.firsts.addAll(hizq.firsts);
                this.firsts.addAll(hder.firsts);
                this.lasts.addAll(hizq.lasts);
                this.lasts.addAll(hder.lasts);
                break;
            case ".":
                this.anunabilidad = ("A".equals(hizq.anunabilidad) && "A".equals(hder.anunabilidad)) ? "A" : "N";
                if ("A".equals(hizq.anunabilidad)) {
                    this.firsts.addAll(hizq.firsts);
                    this.firsts.addAll(hder.firsts);
                } else {
                    this.firsts.addAll(hizq.firsts);
                }

                if ("A".equals(hder.anunabilidad)) {
                    this.lasts.addAll(hizq.lasts);
                    this.lasts.addAll(hder.lasts);
                } else {
                    this.lasts.addAll(hder.lasts);
                }
                break;
            case "*":
                this.anunabilidad = "A";
                this.firsts.addAll(hizq.firsts);
                this.lasts.addAll(hizq.lasts);
                break;
            case "+":
                this.anunabilidad = ("A".equals(hizq.anunabilidad)) ? "A" : "N";
                this.firsts.addAll(hizq.firsts);
                this.lasts.addAll(hizq.lasts);
                break;
            case "?":
                this.anunabilidad = "A";
                this.firsts.addAll(hizq.firsts);
                this.lasts.addAll(hizq.lasts);
                break;
            default:
                break;
        }
    }

    public String getDotText() {
        String etiqueta = "";
        if (valor != null) {
            String valorText = ("|".equals(valor))? "\\|": valor;
            String firstsText = String.join(",", firsts.toString());
            String lastsText = String.join(",", lasts.toString());
            if (hizq == null && hder == null) {
                etiqueta = "nodo" + id + "[label= \"" 
                            + firstsText.substring(1, firstsText.length()-1)
                            + "|{" + anunabilidad + "|" + valorText  + "|" + "id: " + id + "}|"
                            + lastsText.substring(1, lastsText.length()-1)
                            + "\"];\n";
            } else {
                etiqueta = "nodo" + id + "[label= \"" 
                    + firstsText.substring(1, firstsText.length()-1)
                    + "|{" + anunabilidad + "|" + valorText + "}|"
                    + lastsText.substring(1, lastsText.length()-1)
                    + "\"];\n";
            }
            if (hizq != null) {
                etiqueta += hizq.getDotText() + "nodo" + id + "->nodo" + hizq.id + ";\n";
            }
            if (hder != null) {
                etiqueta += hder.getDotText() + "nodo" + id + "->nodo" + hder.id + ";\n";
            }
        }
        return etiqueta;
    }

}
