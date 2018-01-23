package rcrsystem.presentation.controller;

import rcrsystem.presentation.view.VentanaCarga;
import javax.swing.SwingWorker;

public class Progreso_Cambio_Contr_Controlador extends SwingWorker< Integer, String> {

    public Progreso_Cambio_Contr_Controlador(VentanaCarga ventana) {
        this.a_ventana = ventana;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        //obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

        rcrsystem.Aplicacion.ae_vista_principal.reiniciar_contraseñas(a_ventana);
        rcrsystem.Aplicacion.ae_vista_principal.btnLogin.setEnabled(true);
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
} //Fin de la clase Progreso_Cambio_Contr_Controlador
