package compi1_proyecto1;

import analizadores.parser;
import analizadores.lexico;
import static java.awt.SystemColor.info;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import analizadores.*;
import java.awt.Image;
import java.nio.file.FileSystems;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author José Alvarado
 */
public class Pantalla extends javax.swing.JFrame {

    public Pantalla() {
        initComponents();
        CmbxArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    procesarOpcionDeArchivo(CmbxArchivo.getSelectedItem().toString());
                } catch (IOException ex) {
                }
            }
        });

        CmbxReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarImagen();
            }
        });

        CmbxElemento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarImagen();
            }
        });

    }

    @SuppressWarnings("unchecked")

    private void mostrarImagen() {
        try {
            String ruta = "./" + CmbxReporte.getSelectedItem().toString() + "/" + CmbxElemento.getSelectedItem().toString() + ".jpg";
            //ImageIcon imagen = new ImageIcon(ruta);
            Image imagen = new ImageIcon(ruta).getImage();
            ImageIcon img = new ImageIcon(imagen.getScaledInstance(Display.getWidth(), Display.getHeight(), Image.SCALE_SMOOTH));
            Display.setIcon(img);
        } catch (Exception e) {

        }
    }

    private void procesarOpcionDeArchivo(String opcion) throws IOException {
        switch (opcion) {
            case "Abrir":
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Seleccioanar imagen", "olc", "txt");
                fileChooser.setFileFilter(extensionFilter);
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    AppState.filePath = fileChooser.getSelectedFile();
                    try {
                        Scanner scanner = new Scanner(AppState.filePath);
                        AppState.texto = "";
                        while (scanner.hasNextLine()) {
                            AppState.texto += scanner.nextLine();
                        }
                        Texto.setText(AppState.texto);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                break;
            case "Guardar":
                AppState.texto = Texto.getText();
                if (AppState.filePath != null) {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(AppState.filePath));
                    bw.write(AppState.texto);
                    bw.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Debes \"Guardar Como\" el archivo y luego abrirlo para guardar.");
                }
                break;
            case "Guardar como":
                fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(this);
                File nombre = fileChooser.getSelectedFile();
                if (nombre != null) {
                    FileWriter archivo = new FileWriter(nombre + ".olc");
                    archivo.write(Texto.getText());
                    archivo.close();
                    if (AppState.filePath != null) {
                        File fichero = new File(AppState.filePath.toString());
                        fichero.delete();
                        AppState.filePath = Paths.get(nombre.getPath() + ".olc").toFile();
                    } else {
                        AppState.texto = Texto.getText();
                        BufferedWriter bw = new BufferedWriter(new FileWriter(nombre.getPath()));
                        bw.write(AppState.texto);
                        bw.close();
                    }
                }

                break;
            case "Generar XML de salida":
                System.out.println("Generar XML de salida");
                break;
        }
        CmbxArchivo.setSelectedItem("Archivo");

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        CmbxArchivo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Consola = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Texto = new javax.swing.JTextArea();
        BtnAnalizarEntradas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CmbxElemento = new javax.swing.JComboBox();
        Display = new javax.swing.JLabel();
        LblNombre = new javax.swing.JLabel();
        CmbxReporte = new javax.swing.JComboBox();
        LblNombre1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CmbxArchivo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Archivo", "Abrir", "Guardar", "Guardar como"}));
        CmbxArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbxArchivoActionPerformed(evt);
            }
        });

        Consola.setColumns(20);
        Consola.setRows(5);
        jScrollPane1.setViewportView(Consola);

        Texto.setColumns(20);
        Texto.setRows(5);
        jScrollPane2.setViewportView(Texto);

        BtnAnalizarEntradas.setText("Analizar  Entradas");
        BtnAnalizarEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnalizarEntradasActionPerformed(evt);
            }
        });

        jLabel1.setText("Salida");

        Display.setBackground(new java.awt.Color(0, 0, 0));

        LblNombre.setText("Reporte:");

        CmbxReporte.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"arboles", "tablasdeSiguientes", "tabladeTransiciones", "automatas", "thomson"}));
        CmbxReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbxReporteActionPerformed(evt);
            }
        });

        LblNombre1.setText("Elemento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addContainerGap(1040, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                                                        .addComponent(BtnAnalizarEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(CmbxArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Display, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(LblNombre)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(CmbxReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(LblNombre1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(CmbxElemento, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(CmbxArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BtnAnalizarEntradas))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(CmbxElemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(CmbxReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(LblNombre)
                                                .addComponent(LblNombre1))
                                        .addGap(18, 18, 18)
                                        .addComponent(Display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void CmbxArchivoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void BtnAnalizarEntradasActionPerformed(java.awt.event.ActionEvent evt) {
        AppState.reiniciar();
        Consola.setText("");
        Display.setIcon(null);
        CmbxElemento.removeAllItems();
        try {
            parser sintactico = new parser(new lexico(new StringReader(Texto.getText())));
            sintactico.parse();
            AppState.reportar();
            AppState.graficarArboles();
            try {
                AppState.graficarThomson();
            } catch (IOException ex) {
                Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
            }
            AppState.crearTablasdeSiguientes();
            AppState.graficarTablasdeSiguientes();
            AppState.crearTablasdeTransiciones();
            AppState.graficarTablasdeTransiciones();
            AppState.crearAutomatas();
            AppState.graficarAutomatas();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                AppState.validarCadenas();
            } catch (IOException ex) {
                Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Nodo arbol : AppState.arboles) {
                CmbxElemento.addItem(arbol.nombre);
            }
        }
    }

    private void CmbxReporteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton BtnAnalizarEntradas;
    private javax.swing.JComboBox CmbxArchivo;
    public static javax.swing.JComboBox CmbxElemento;
    public static javax.swing.JComboBox CmbxReporte;
    public static javax.swing.JTextArea Consola;
    private javax.swing.JLabel Display;
    private javax.swing.JLabel LblNombre;
    private javax.swing.JLabel LblNombre1;
    private javax.swing.JTextArea Texto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration                   
}
