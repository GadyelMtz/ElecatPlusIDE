/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elecatpluside;

import javax.swing.table.DefaultTableModel;
import static  elecatpluside.IDE.modelo;
/**
 *
 * @author zapat
 */
public class TablaTokens extends javax.swing.JFrame {
    DefaultTableModel m;
    /**
     * Creates new form TablaTokens
     */
    public TablaTokens() {
        initComponents();
        tablaTokens.setModel(modelo);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPaneIDE = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextPane();
        lblSalida = new javax.swing.JLabel();
        lblCaretPosition = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblGuardar = new javax.swing.JLabel();
        lblGuardarComo = new javax.swing.JLabel();
        lblAbrir = new javax.swing.JLabel();
        lblAcercar = new javax.swing.JLabel();
        lblAlejar = new javax.swing.JLabel();
        lblToken = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAutomata = new javax.swing.JLabel();
        lblArbol = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblLogo1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaTokens = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ELECAT.png"))); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txtPaneIDE.setBackground(new java.awt.Color(0, 0, 0));
        txtPaneIDE.setBorder(null);
        txtPaneIDE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtPaneIDE.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPaneIDEactualizarLabel(evt);
            }
        });
        jScrollPane1.setViewportView(txtPaneIDE);

        txtOutput.setEditable(false);
        txtOutput.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtOutput.setText("En este espacio se mostrará la salida del compilador.");
        txtOutput.setToolTipText("");
        jScrollPane2.setViewportView(txtOutput);

        lblSalida.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        lblSalida.setForeground(new java.awt.Color(255, 255, 102));
        lblSalida.setText("Salida");

        lblCaretPosition.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblCaretPosition.setForeground(new java.awt.Color(255, 255, 255));
        lblCaretPosition.setText("Posición del cursor 1:0");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compilar.png"))); // NOI18N
        jLabel1.setToolTipText("Compilar");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        lblGuardar.setToolTipText("Guardar");
        lblGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGuardarMouseClicked(evt);
            }
        });

        lblGuardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardarcomo.png"))); // NOI18N
        lblGuardarComo.setToolTipText("Guardar como");
        lblGuardarComo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblGuardarComo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGuardarComoMouseClicked(evt);
            }
        });

        lblAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/abrir.png"))); // NOI18N
        lblAbrir.setToolTipText("Abrir");
        lblAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAbrirMouseClicked(evt);
            }
        });

        lblAcercar.setFont(new java.awt.Font("sansserif", 0, 48)); // NOI18N
        lblAcercar.setForeground(new java.awt.Color(255, 255, 255));
        lblAcercar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcercar.setText("+");
        lblAcercar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAcercar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAcercarMouseClicked(evt);
            }
        });

        lblAlejar.setFont(new java.awt.Font("sansserif", 0, 48)); // NOI18N
        lblAlejar.setForeground(new java.awt.Color(255, 255, 255));
        lblAlejar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlejar.setText("-");
        lblAlejar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAlejar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlejarMouseClicked(evt);
            }
        });

        lblToken.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/token.png"))); // NOI18N
        lblToken.setToolTipText("Tokens");
        lblToken.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblToken.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTokenMouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stack.png"))); // NOI18N
        jLabel5.setToolTipText("Pila");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblAutomata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/transicion.png"))); // NOI18N
        lblAutomata.setToolTipText("Diagrama de transicion");
        lblAutomata.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAutomata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAutomataMouseClicked(evt);
            }
        });

        lblArbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/arboles.png"))); // NOI18N
        lblArbol.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblArbol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArbolMouseClicked(evt);
            }
        });

        jLabel2.setText("Abrir archivo");

        jLabel6.setText("Guardar como");

        jLabel7.setText("Guardar");

        jLabel8.setText("Compilar");

        jLabel9.setText("Ver automata");

        jLabel10.setText("Ver arbol");

        jLabel11.setText("Ver tokens");

        jLabel12.setText("Ver pila");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(364, 364, 364)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblAutomata)
                                    .addComponent(jLabel9))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(lblArbol))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblToken)
                                    .addComponent(jLabel11))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel5)))
                                .addContainerGap(233, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAlejar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAcercar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblAbrir)
                                .addGap(18, 18, 18)
                                .addComponent(lblGuardarComo)
                                .addGap(18, 18, 18)
                                .addComponent(lblGuardar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8))
                            .addComponent(jLabel1))
                        .addGap(80, 80, 80))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblSalida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCaretPosition)
                .addGap(65, 65, 65))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblAcercar)
                                .addComponent(lblAlejar))
                            .addComponent(lblAbrir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGuardar)
                            .addComponent(lblGuardarComo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSalida)
                            .addComponent(lblCaretPosition))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 49, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblToken)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblArbol)
                                    .addComponent(lblAutomata))))
                        .addGap(121, 121, 121))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        lblLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ELECAT.png"))); // NOI18N

        tablaTokens.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tablaTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Descripcion"
            }
        ));
        tablaTokens.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaTokens.setFocusable(false);
        tablaTokens.setGridColor(new java.awt.Color(51, 153, 255));
        tablaTokens.setSelectionBackground(new java.awt.Color(255, 255, 0));
        jScrollPane3.setViewportView(tablaTokens);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tabla de tokens");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblLogo1)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)))
                .addGap(99, 99, 99))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPaneIDEactualizarLabel(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPaneIDEactualizarLabel

    }//GEN-LAST:event_txtPaneIDEactualizarLabel

    private void lblGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGuardarMouseClicked

    }//GEN-LAST:event_lblGuardarMouseClicked

    private void lblGuardarComoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGuardarComoMouseClicked

    }//GEN-LAST:event_lblGuardarComoMouseClicked

    private void lblAbrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAbrirMouseClicked

    }//GEN-LAST:event_lblAbrirMouseClicked

    private void lblAcercarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAcercarMouseClicked

    }//GEN-LAST:event_lblAcercarMouseClicked

    private void lblAlejarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlejarMouseClicked

    }//GEN-LAST:event_lblAlejarMouseClicked

    private void lblTokenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTokenMouseClicked
    }//GEN-LAST:event_lblTokenMouseClicked

    private void lblAutomataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAutomataMouseClicked

    }//GEN-LAST:event_lblAutomataMouseClicked

    private void lblArbolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArbolMouseClicked

    }//GEN-LAST:event_lblArbolMouseClicked

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
            java.util.logging.Logger.getLogger(TablaTokens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaTokens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaTokens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaTokens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablaTokens().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAbrir;
    private javax.swing.JLabel lblAcercar;
    private javax.swing.JLabel lblAlejar;
    private javax.swing.JLabel lblArbol;
    private javax.swing.JLabel lblAutomata;
    private javax.swing.JLabel lblCaretPosition;
    private javax.swing.JLabel lblGuardar;
    private javax.swing.JLabel lblGuardarComo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblSalida;
    private javax.swing.JLabel lblToken;
    private javax.swing.JTable tablaTokens;
    private javax.swing.JTextPane txtOutput;
    private javax.swing.JTextPane txtPaneIDE;
    // End of variables declaration//GEN-END:variables
}
