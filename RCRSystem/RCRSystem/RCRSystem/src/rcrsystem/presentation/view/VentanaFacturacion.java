package rcrsystem.presentation.view;

import Modelo.Usuario;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JOptionPane;
import rcrsystem.presentation.controller.Facturar_Controlador;
import rcrsystem.presentation.controller.Usuario_Controlador;
import rcrsystem.presentation.controller.Progreso_Menu_Prin_Controlador;
import rcrsystem.presentation.model.Facturar_Modelo;

public class VentanaFacturacion extends javax.swing.JFrame implements java.util.Observer {

    Facturar_Controlador controller;
    Facturar_Modelo model;
    public static VentanaFacturaExportacion vistaFacturaExpo;
    public static VentanaFacturaNacional vistaFacturaNac;
    public VentanaCarga ventana;
    public VentanaVenta vv;
    public VentanaFacturaExportacion ve;
    public VentanaFacturaNacional vn;

    public Facturar_Controlador getController() {
        return controller;
    }

    public void setController(Facturar_Controlador controller) {
        this.controller = controller;
    }

    public Facturar_Modelo getModel() {
        return model;
    }

    public void setModel(Facturar_Modelo model) {
        this.model = model;
        model.addObserver(this);
    }

    public VentanaFacturacion() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png")).getImage());
        setLocationRelativeTo(null);
        addWindowListener(exitListener);
        this.setResizable(false);
        vistaFacturaExpo = new VentanaFacturaExportacion(this, true);
        ventana = new VentanaCarga();
    }
    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            if (rcrsystem.Aplicacion.ae_usuario.obtener_puesto() == 1) {
                setVisible(false);
                Progreso_Menu_Prin_Controlador v = new Progreso_Menu_Prin_Controlador(ventana, 1);
                v.execute();
            } else if (rcrsystem.Aplicacion.ae_usuario.obtener_puesto() == 3) {
                setVisible(false);
                Progreso_Menu_Prin_Controlador v = new Progreso_Menu_Prin_Controlador(ventana, 3);
                v.execute();
            }
        }
    };

    public VentanaFacturacion(VentanaVenta vv) {
        initComponents();
        //   vistaEdicion = new VentanaEdicionInventario(this, true);

        this.vv = vv;
        setIconImage(new ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png")).getImage());
        setLocationRelativeTo(null);
        this.setResizable(false);
        vistaFacturaExpo = new VentanaFacturaExportacion(this, true);
        ventana = new VentanaCarga();
    }

    public VentanaFacturacion(VentanaFacturaExportacion ve) {
        initComponents();
        //   vistaEdicion = new VentanaEdicionInventario(this, true);

        this.ve = ve;
        setIconImage(new ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png")).getImage());
        setLocationRelativeTo(null);
        addWindowListener(exitListener);
        this.setResizable(false);
        vistaFacturaExpo = new VentanaFacturaExportacion(this, true);
        ventana = new VentanaCarga();
    }

    public VentanaFacturacion(VentanaFacturaNacional vn) {
        initComponents();
        //   vistaEdicion = new VentanaEdicionInventario(this, true);

        this.vn = vn;
        setIconImage(new ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/logoRCR.png")).getImage());
        setLocationRelativeTo(null);
        this.setResizable(false);
        vistaFacturaNac = new VentanaFacturaNacional(this, true);
        ventana = new VentanaCarga();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        JButton_generarFactura = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jRadioNacional = new javax.swing.JRadioButton();
        jRadioExportacion = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableListEmpaques = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btn_cerrar_sesion = new javax.swing.JMenuItem();
        btn_salir = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btn_calculadora = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Facturar");
        setBackground(new java.awt.Color(27, 82, 156));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(27, 82, 156));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Listas de empaque");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JButton_generarFactura.setText("Generar Factura");
        JButton_generarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton_generarFacturaActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo:");

        buttonGroup1.add(jRadioNacional);
        jRadioNacional.setText("Nacional");
        jRadioNacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioNacionalActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioExportacion);
        jRadioExportacion.setSelected(true);
        jRadioExportacion.setText("Exportación");

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JButton_generarFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioNacional)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioExportacion)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioNacional)
                    .addComponent(jRadioExportacion))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JButton_generarFactura)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableListEmpaques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListEmpaques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListEmpaquesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableListEmpaques);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/folder.png"))); // NOI18N
        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        btn_cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/disconnect.png"))); // NOI18N
        btn_cerrar_sesion.setText("Cerrar Sesión");
        btn_cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrar_sesionActionPerformed(evt);
            }
        });
        jMenu1.add(btn_cerrar_sesion);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/door_out.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jMenu1.add(btn_salir);

        jMenuBar1.add(jMenu1);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/cog.png"))); // NOI18N
        jMenu6.setText("Herramientas");

        btn_calculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcrsystem/presentation/view/iconos/calculator.png"))); // NOI18N
        btn_calculadora.setText("Calculadora");
        btn_calculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculadoraActionPerformed(evt);
            }
        });
        jMenu6.add(btn_calculadora);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JButton_generarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButton_generarFacturaActionPerformed

        int row = this.jTableListEmpaques.getSelectedRow();
        Facturar_Controlador controlador = new Facturar_Controlador(this, model);
        if (this.jRadioExportacion.isSelected()) {
            controller.abrir(row, 0);
        } else if (this.jRadioNacional.isSelected()) {
            controller.abrir(row, 1);
        }
    }//GEN-LAST:event_JButton_generarFacturaActionPerformed

    private void jRadioNacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioNacionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioNacionalActionPerformed

    private void jTableListEmpaquesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListEmpaquesMouseClicked

    }//GEN-LAST:event_jTableListEmpaquesMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (rcrsystem.Aplicacion.ae_usuario.obtener_puesto() == 1) {
            setVisible(false);
            Progreso_Menu_Prin_Controlador v = new Progreso_Menu_Prin_Controlador(ventana, 1);
            v.execute();
        } else if (rcrsystem.Aplicacion.ae_usuario.obtener_puesto() == 3) {
            setVisible(false);
            Progreso_Menu_Prin_Controlador v = new Progreso_Menu_Prin_Controlador(ventana, 3);
            v.execute();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrar_sesionActionPerformed
        Toolkit.getDefaultToolkit().beep();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            rcrsystem.Aplicacion.ae_usuario = new Usuario();
            rcrsystem.Aplicacion.ae_vista_principal = new VentanaInicio();
            rcrsystem.Aplicacion.ae_vista_principal.setController(new Usuario_Controlador());
            rcrsystem.Aplicacion.ae_vista_principal.setVisible(true);
        }
    }//GEN-LAST:event_btn_cerrar_sesionActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar el programa?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_salirActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void btn_calculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculadoraActionPerformed
        Process runtimeProcess;
        try {

            System.out.println(System.getProperty("os.name"));
            String comandoCalculadora = System.getProperty("os.name").startsWith("Windows") ? "calc" : "gcalctool";
            runtimeProcess = Runtime.getRuntime().exec(comandoCalculadora);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_calculadoraActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaFacturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton JButton_generarFactura;
    private javax.swing.JMenuItem btn_calculadora;
    private javax.swing.JMenuItem btn_cerrar_sesion;
    private javax.swing.JMenuItem btn_salir;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioExportacion;
    private javax.swing.JRadioButton jRadioNacional;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableListEmpaques;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(java.util.Observable updatedModel, Object parametros) {
        if (parametros != Facturar_Modelo.ae_modelo_factura) {
            return;
        }
        this.jTableListEmpaques.setModel(model.obtener_modelo_factura());
        this.revalidate();
    }
}
