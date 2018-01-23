package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaPrincEncargadoCompras;

public class Progreso_EncargadoCompras_Controlador extends SwingWorker< Integer, String> {

    public Progreso_EncargadoCompras_Controlador(VentanaCarga ventana, VentanaPrincEncargadoCompras a_principal) {
        this.a_ventana = ventana;
        this.a_principal = a_principal;

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
        obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

        if (a_tipo == 5) {
            this.a_principal.cargar_ReporteCompras();
        }

        if (a_tipo == 6) {
            this.a_principal.cargar_ReporteVentas();
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
    private VentanaPrincEncargadoCompras a_principal;
    private int a_tipo = -1;
}
