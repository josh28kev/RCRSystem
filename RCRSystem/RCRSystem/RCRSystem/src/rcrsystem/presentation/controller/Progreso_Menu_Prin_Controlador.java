package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaPrincEncargadoCompras;
import rcrsystem.presentation.view.VentanaPrincEncargadoVentas;
import rcrsystem.presentation.view.VentanaPrincipal;

public class Progreso_Menu_Prin_Controlador extends SwingWorker< Integer, String> {

    public Progreso_Menu_Prin_Controlador(VentanaCarga ventana, int tipoV) {
        this.a_ventana = ventana;
        this.tipoV = tipoV;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);
        if (obtener_tipoV() == 1) {
            new VentanaPrincipal().setVisible(true);
        } else if (obtener_tipoV() == 2) {
            new VentanaPrincEncargadoCompras().setVisible(true);
        } else if (obtener_tipoV() == 3) {
            new VentanaPrincEncargadoVentas().setVisible(true);
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

    public int obtener_tipoV() {
        return tipoV;
    }

    public void poner_TipoV(int tipoV) {
        this.tipoV = tipoV;
    }

    private VentanaCarga a_ventana;
    private int tipoV;
} // Fin de la clase Progreso_Menu_Prin_Controlador
