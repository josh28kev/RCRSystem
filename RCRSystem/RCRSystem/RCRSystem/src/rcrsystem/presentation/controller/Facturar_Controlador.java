package rcrsystem.presentation.controller;

import Modelo.Lista_Empaque;
import Modelo.dao.Lista_Empaque_DAO;
import Modelo.dao.Total_Material_Vendido_DAO;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Facturar_Modelo;
import rcrsystem.presentation.view.VentanaFacturaExportacion;
import rcrsystem.presentation.view.VentanaFacturaNacional;
import rcrsystem.presentation.view.VentanaFacturacion;

public class Facturar_Controlador {

    public Facturar_Controlador(VentanaFacturacion vista, Facturar_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        a_modelo.colocar_listas_empaques(Lista_Empaque_DAO.obtener_lista_empaque_pen(), 0);
    }

    public void abrir(int row, int tipo) {
        try {
            this.a_lista_empaque = a_modelo.obtener_modelo_factura().obtener_fila_a(row);
            a_modelo.colocar_lista_empaque_actual(a_lista_empaque);

            if (tipo == 0) {
                VentanaFacturaExportacion v = new VentanaFacturaExportacion(a_vista, true);
                a_modelo.colocar_total_material_vendido_lista_empaque(Total_Material_Vendido_DAO.obtener_i_total_materiales_vendidos(a_lista_empaque.obtener_codigo_l()), 0);
                Factura_Exportacion_Controlador fc = new Factura_Exportacion_Controlador(v, a_modelo);
                a_vista.setVisible(false);
                v.setVisible(true);
            } else {
                VentanaFacturaNacional v = new VentanaFacturaNacional(a_vista, true);
                a_modelo.colocar_total_material_vendido_lista_empaque(Total_Material_Vendido_DAO.obtener_i_total_materiales_vendidos(a_lista_empaque.obtener_codigo_l()), 1);
                Factura_Nacional_Controlador fc = new Factura_Nacional_Controlador(v, a_modelo);
                a_vista.setVisible(false);
                v.setVisible(true);
            }

        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(a_vista, "¡No ha seleccionado ninguna lista de empaque!");
        }
    }
    private VentanaFacturacion a_vista;
    private Facturar_Modelo a_modelo;
    private Lista_Empaque a_lista_empaque;
} // Fin de la clase Facturar_Controlador
