
package rcrsystem.presentation.controller;

import javax.swing.SwingWorker;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaConsultaCompras;


public class Progreso_Consulta_Compra  extends SwingWorker<Integer, String> {

    public Progreso_Consulta_Compra (VentanaCarga ventana, VentanaConsultaCompras a_compras) {
        this.a_ventana = ventana;
        this.a_compras = a_compras;
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
        
        a_compras.setEnabled(false);
        if (a_tipo == 1) {
            obtener_ventana().setVisible(true);
        obtener_ventana().jProgressBar1.setIndeterminate(true);
            this.a_compras.busqueda(this.obtener_ventana());
             a_compras.setEnabled(true);
        }
       
        if (a_tipo == 2) {
        obtener_ventana().jProgressBar1.setIndeterminate(true);
            this.a_compras.generar_reporte(this.obtener_ventana());
            a_compras.setEnabled(true);
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
    private VentanaConsultaCompras a_compras;
    private int a_tipo = -1;
}