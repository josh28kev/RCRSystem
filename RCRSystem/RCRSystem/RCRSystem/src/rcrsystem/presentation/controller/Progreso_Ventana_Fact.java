
package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.model.Facturar_Modelo;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaFacturacion;
import static rcrsystem.presentation.view.VentanaPrincipal.vistaFacturacion;


public class Progreso_Ventana_Fact extends SwingWorker< Integer, String>{
    
    public Progreso_Ventana_Fact(VentanaCarga ventana) {
        this.a_ventana = ventana;
    }

    @Override
    protected Integer doInBackground() throws Exception {
    obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);

        vistaFacturacion = new VentanaFacturacion();
        Facturar_Modelo ventasModelo = new Facturar_Modelo();
        Facturar_Controlador controladorVentas = new Facturar_Controlador(vistaFacturacion, ventasModelo);
        vistaFacturacion.jButton1.setEnabled(true);
        
        vistaFacturacion.setVisible(true);
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
}//Fin de la clase
