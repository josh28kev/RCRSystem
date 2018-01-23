package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentConsultaMatInv;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaInventario;

public class Progreso_Mat_Controlador extends SwingWorker< Integer, String> {

    private VentanaCarga ventana;
    private VentanaInventario ventanaInv;

    public Progreso_Mat_Controlador(VentanaCarga ventana, VentanaInventario ventanaInv) {
        this.ventana = ventana;
        this.ventanaInv = ventanaInv;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        getVentana().setVisible(true);
        ventana.jProgressBar1.setIndeterminate(true);
        rcrsystem.presentation.model.Modelo_B_C modelo = new rcrsystem.presentation.model.Modelo_B_C();
        //int row = Integer.valueOf((String) this.tablaInventario.getValueAt(this.tablaInventario.getSelectedRow(), 0));

        //  rcrsystem.presentation.model.ModeloBC modelo = new rcrsystem.presentation.model.ModeloBC();
        ///VentConsultaMatInv vista = new VentConsultaMatInv();
        //BultosCControlador control = new BultosCControlador(vista, modelo,
        //      (String) this.tablaInventario.getValueAt(this.tablaInventario.getSelectedRow(), 0));
        //rcrsystem.presentation.model.Modelo_B_C modelo = new rcrsystem.presentation.model.Modelo_B_C();
        VentConsultaMatInv vista = new VentConsultaMatInv();
        Bultos_C_Controlador control = new Bultos_C_Controlador(vista, modelo,
                (String) ventanaInv.tablaInventario.getValueAt(ventanaInv.tablaInventario.getSelectedRow(), 0), (String) ventanaInv.tablaInventario.getValueAt(ventanaInv.tablaInventario.getSelectedRow(), 1), ventanaInv, ventanaInv.tablaInventario.getSelectedRow());
        vista.setVisible(true);
        getVentana().setVisible(false);
        ventana.jProgressBar1.setIndeterminate(false);
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

}
