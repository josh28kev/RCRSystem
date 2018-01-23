package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaFacturaExportacion;

public class Progreso_Confirmar_FacturaExpo extends SwingWorker<Integer, String> {

    public Progreso_Confirmar_FacturaExpo(VentanaCarga ventana, VentanaFacturaExportacion ventFacExpo) {
        this.a_ventana = ventana;
        this.a_FacturaExpo = ventFacExpo;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

        this.a_FacturaExpo.confirmar(a_ventana);

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
    private VentanaFacturaExportacion a_FacturaExpo;

}
