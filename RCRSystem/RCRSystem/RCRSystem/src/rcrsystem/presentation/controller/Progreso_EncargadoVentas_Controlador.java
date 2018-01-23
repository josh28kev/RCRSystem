package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaPrincEncargadoVentas;

public class Progreso_EncargadoVentas_Controlador extends SwingWorker< Integer, String> {

    public Progreso_EncargadoVentas_Controlador(VentanaCarga ventana, VentanaPrincEncargadoVentas a_principal) {
        this.a_ventana = ventana;
        this.a_principal = a_principal;

    }

    @Override
    protected Integer doInBackground() throws Exception {
        obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

        this.a_principal.cargar_ReporteVentas();

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
    private VentanaPrincEncargadoVentas a_principal;

}
