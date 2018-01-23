/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcrsystem.presentation.controller;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaInventario;

/**
 *
 * @author KevinJ
 */
public class Progreso_Invent_Controlador extends SwingWorker< Integer, String> {

    private VentanaCarga ventana;
    private JProgressBar jpb;
    private int tipioV;

    public Progreso_Invent_Controlador(VentanaCarga ventana, int tipoV) {
        this.ventana = ventana;
        this.jpb = ventana.jProgressBar1;
        this.tipioV = tipoV;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        getVentana().setVisible(true);
        getJpb().setIndeterminate(true);
        rcrsystem.presentation.model.Modelo modelo = new rcrsystem.presentation.model.Modelo();

        VentanaInventario vista = new VentanaInventario();
        vista.tipoVentana = tipioV;
        Inventario_Controlador controlador = new Inventario_Controlador(vista, modelo, this.ventana);
        vista.setVisible(true);

        getVentana().setVisible(false);
        getJpb().setIndeterminate(false);
        return 0;
    }

    /**
     * @return the ventana
     */
    public VentanaCarga getVentana() {
        return ventana;
    }

    /**
     * @param ventana the ventana to set
     */
    public void setVentana(VentanaCarga ventana) {
        this.ventana = ventana;
    }

    /**
     * @return the jpb
     */
    public JProgressBar getJpb() {
        return jpb;
    }

    /**
     * @param jpb the jpb to set
     */
    public void setJpb(JProgressBar jpb) {
        this.jpb = jpb;
    }

}
