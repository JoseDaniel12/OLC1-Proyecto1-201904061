package compi1_proyecto1;

import static compi1_proyecto1.AppState.vaciarCarpeta;
import java.io.File;

/**
 *
 * @author José Alvarado
 */
public class Compi1_Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        File directorio = new File("./ARBOLES_201904061");
        directorio.mkdirs();
        directorio = new File("./SIGUIENTES_201904061");
        directorio.mkdirs();
        directorio = new File("./TRANSICIONES_201904061");
        directorio.mkdirs();
        directorio = new File("./AFD_201904061");
        directorio.mkdirs();
        directorio = new File("./AFND_201904061");
        directorio.mkdirs();
        directorio = new File("./SALIDAS_201904061");
        directorio.mkdirs();
        directorio = new File("./ERR0RES_201904061");
        directorio.mkdirs();
        try {
            AppState.vaciarCarpeta(new File("./ARBOLES_201904061"));
            AppState.vaciarCarpeta(new File("./SIGUIENTES_201904061"));
            AppState.vaciarCarpeta(new File("./TRANSICIONES_201904061"));
            AppState.vaciarCarpeta(new File("./AFD_201904061"));
            AppState.vaciarCarpeta(new File("./AFND_201904061"));
            AppState.vaciarCarpeta(new File("./SALIDAS_201904061"));
            AppState.vaciarCarpeta(new File("./ERR0RES_201904061"));
        } catch(Exception e)  {
        }
        Pantalla ventana = new Pantalla();
        ventana.setVisible(true);
    }

}
