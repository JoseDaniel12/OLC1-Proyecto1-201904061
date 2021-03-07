package compi1_proyecto1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author José Alvarado
 */
public class Thomson {

    public static int contador = 0;
    public static String texto = "";

    public static void graficar(Nodo raiz) throws IOException, InterruptedException {
        texto += "digraph g { \n";
        texto += "rankdir=LR; \n";
        codigoInterno(raiz);
        texto += "}";
        FileWriter fw = new FileWriter("./" + raiz.nombre + ".dot");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(texto);
        fw.close();
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec("dot -Tjpg " + raiz.nombre + ".dot -o ./thomson/" + raiz.nombre + ".jpg");
        int exitVal = proc.waitFor();
        File f = new File(raiz.nombre + ".dot");
        f.delete();
        contador = 0;
        texto = "";
    }

    public static int[] codigoInterno(Nodo raiz) {
        int[] res = new int[2];
        if (raiz != null) {
            if (raiz.valor.equals(".") && raiz.hizq != null) {
                return punto(codigoInterno(raiz.hizq), codigoInterno(raiz.hder));
            } else if (raiz.valor.equals("|") && raiz.hizq != null) {
                return or(codigoInterno(raiz.hizq), codigoInterno(raiz.hder));
            } else if (raiz.valor.equals("*") && raiz.hizq != null) {
                return asterisco(codigoInterno(raiz.hizq));
            } else if (raiz.valor.equals("+") && raiz.hizq != null) {
                return mas(codigoInterno(raiz.hizq));
            } else if (raiz.valor.equals("?") && raiz.hizq != null) {
                return interrogacion(codigoInterno(raiz.hizq));
            } else {
                contador++;
                String valorText = raiz.valor;
                valorText = ("|".equals(raiz.valor)) ? "\\|" : valorText;
                valorText = ("\\n".equals(raiz.valor)) ? "\\\\n" : valorText;
                valorText = ("\\\"".equals(raiz.valor)) ? "\\\\\"" : valorText;
                if ("#".equals(raiz.valor)) {
                    texto += "S" + (contador + 1) + " [shape = doublecircle]";
                    texto += "S" + (contador) + " -> " + "S" + (contador + 1) + "[ label = \"" + valorText + "\" ]\n";
                } else {
                    texto += "S" + (contador) + " -> " + "S" + (contador + 1) + "[ label = \"" + valorText + "\" ]\n";
                }

                res[0] = contador;
                res[1] = contador + 1;
                contador++;
                return res;
            }
        }
        return res;
    }

    public static int[] asterisco(int[] p1) {
        int[] res = new int[2];
        contador++;
        texto += "S" + (contador) + " -> " + "S" + (p1[0]) + "[ label = \"ε\" ]\n";
        texto += "S" + (p1[1]) + " -> " + "S" + (p1[0]) + "[ label = \"ε\" ]\n";
        texto += "S" + (p1[1]) + " -> " + "S" + (contador + 1) + "[ label = \"ε\" ]\n";
        texto += "S" + (contador) + " -> " + "S" + (contador + 1) + "[ label = \"ε\" ]\n";
        contador += 1;
        res[0] = contador - 1;
        res[1] = contador;
        return res;
    }

    public static int[] mas(int[] p1) {
        int[] res = new int[2];
        contador++;
        texto += "S" + (contador) + " -> " + "S" + (p1[0]) + "[ label = \"ε\" ]\n";
        texto += "S" + (p1[1]) + " -> " + "S" + (p1[0]) + "[ label = \"ε\" ]\n";
        texto += "S" + (p1[1]) + " -> " + "S" + (contador + 1) + "[ label = \"ε\" ]\n";
        contador += 1;
        res[0] = contador - 1;
        res[1] = contador;
        return res;
    }

    public static int[] interrogacion(int[] p1) {
        int[] res = new int[2];
        contador++;
        texto += "S" + (contador) + " -> " + "S" + (p1[0]) + "[ label = \"ε\" ]\n";
        texto += "S" + (p1[1]) + " -> " + "S" + (contador + 1) + "[ label = \"ε\" ]\n";
        texto += "S" + (contador) + " -> " + "S" + (contador + 1) + "[ label = \"ε\" ]\n";
        contador += 1;
        res[0] = contador - 1;
        res[1] = contador;
        return res;
    }

    public static int[] punto(int[] p1, int[] p2) {
        int[] res = new int[2];
        texto += "S" + (p1[1]) + " -> " + "S" + (p2[0]) + "[ label = \"ε\" ]\n";
        res[0] = p1[0];
        res[1] = p2[1];
        return res;
    }

    public static int[] or(int[] p1, int[] p2) {
        int[] res = new int[2];
        contador++;
        texto += "S" + (contador) + " -> " + "S" + (p1[0]) + "[ label = \"ε\" ]\n";
        texto += "S" + (contador) + " -> " + "S" + (p2[0]) + "[ label = \"ε\" ]\n";
        texto += "S" + (p1[1]) + " -> " + "S" + (contador + 1) + "[ label = \"ε\" ]\n";
        texto += "S" + (p2[1]) + " -> " + "S" + (contador + 1) + "[ label = \"ε\" ]\n";
        res[0] = contador;
        res[1] = contador += 1;
        contador++;
        return res;
    }
}
