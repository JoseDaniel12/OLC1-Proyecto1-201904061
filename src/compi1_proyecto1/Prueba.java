/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


/**
 *
 * @author José Alvarado
 */
public class Prueba extends javax.swing.JFrame {
    public Prueba() {
        initComponents();
        CmbxArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    procesarOpcionDeArchivo(CmbxArchivo.getSelectedItem().toString());
                } catch (IOException ex) {
                    Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")

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
                BufferedWriter bw = new BufferedWriter(new FileWriter(AppState.filePath));
                bw.write(AppState.texto);
                bw.close();
                break;
            case "Guardar como":
                fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(this);
                File nombre =fileChooser.getSelectedFile();
                if (nombre != null) {
                    FileWriter archivo = new FileWriter(nombre + ".olc");
                    archivo.write(Texto.getText());
                    archivo.close();
                    File fichero = new File(AppState.filePath.toString());
                    fichero.delete();
                    AppState.filePath = Paths.get(nombre.getPath() + ".olc").toFile();
                }

                break;
            case "Generar XML de salida":
                System.out.println("Generar XML de salida");
                break;
        }
        CmbxArchivo.setSelectedItem("Archivo");

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CmbxArchivo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Consola = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Texto = new javax.swing.JTextArea();
        BtnAnalizarEntradas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CmbxImagenes = new javax.swing.JComboBox();
        BtnAnterior = new javax.swing.JButton();
        Display = new javax.swing.JLabel();
        BtnSIguiente = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TreeCarpetas = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CmbxArchivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Archivo", "Abrir", "Guardar", "Guardar como", "Generar XML de salida" }));
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

        CmbxImagenes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ver Imagenes", "Siguientes", "Transiciones", "Automatas" }));

        BtnAnterior.setText("Anterior");
        BtnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnteriorActionPerformed(evt);
            }
        });

        BtnSIguiente.setText("Siguiente");
        BtnSIguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSIguienteActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(TreeCarpetas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(1040, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CmbxArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                            .addComponent(BtnAnalizarEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnSIguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(CmbxImagenes, javax.swing.GroupLayout.Alignment.LEADING, 0, 500, Short.MAX_VALUE)
                                    .addComponent(Display, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(CmbxArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnAnalizarEntradas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CmbxImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Display, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnAnterior)
                            .addComponent(BtnSIguiente))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAnteriorActionPerformed

    private void BtnSIguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSIguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSIguienteActionPerformed

    private void CmbxArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbxArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbxArchivoActionPerformed

    private void BtnAnalizarEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnalizarEntradasActionPerformed
        try {
            parser sintactico = new parser(new lexico(new StringReader(Texto.getText())));
            sintactico.parse();
            AppState.graficarArboles();
            AppState.crearTablasdeSiguientes();
            AppState.graficarTablasdeSiguientes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_BtnAnalizarEntradasActionPerformed

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
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnalizarEntradas;
    private javax.swing.JButton BtnAnterior;
    private javax.swing.JButton BtnSIguiente;
    private javax.swing.JComboBox CmbxArchivo;
    private javax.swing.JComboBox CmbxImagenes;
    private javax.swing.JTextArea Consola;
    private javax.swing.JLabel Display;
    private javax.swing.JTextArea Texto;
    private javax.swing.JTree TreeCarpetas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
