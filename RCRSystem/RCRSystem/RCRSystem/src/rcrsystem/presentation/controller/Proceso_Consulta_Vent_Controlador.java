package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaConsultaVentas;

public class Proceso_Consulta_Vent_Controlador extends SwingWorker<Integer, String> {

    public Proceso_Consulta_Vent_Controlador(VentanaCarga ventana, VentanaConsultaVentas a_ventas) {
        this.a_ventana = ventana;
        this.a_ventas = a_ventas;
        a_tipo = -1;
    }

    public int obtener_tipo() {
        return a_tipo;
    }

    public void colocar_tipo(int tipo) {
        this.a_tipo = tipo;
    }

    @Override
    protected Integer doInBackground() throws Exception {

        a_ventas.setEnabled(false);
        if (a_tipo == 1) {
            obtener_ventana().setVisible(true);
            obtener_ventana().jProgressBar1.setIndeterminate(true);
            this.a_ventas.busqueda(this.obtener_ventana());
            a_ventas.setEnabled(true);
        }

        if (a_tipo == 2) {
            obtener_ventana().jProgressBar1.setIndeterminate(true);
            this.a_ventas.generar_reporte(this.obtener_ventana());
            a_ventas.setEnabled(true);
        }

        if (a_tipo == 3) {
            obtener_ventana().jProgressBar1.setIndeterminate(true);
            this.a_ventas.generar_reporteFactura(this.obtener_ventana());
            a_ventas.setEnabled(true);
        }

        obtener_ventana().setVisible(false);
        obtener_ventana().jProgressBar1.setIndeterminate(false);
        return 0;
    }

    public VentanaCarga obtener_ventana() {
        return a_ventana;
    }

    public void colocar_ventana(VentanaCarga ventana) {
        this.a_ventana = ventana;
    }

    private VentanaCarga a_ventana;
    private VentanaConsultaVentas a_ventas;
    private int a_tipo = -1;
}
