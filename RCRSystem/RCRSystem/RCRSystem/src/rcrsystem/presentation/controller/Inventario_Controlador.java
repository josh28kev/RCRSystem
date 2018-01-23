package rcrsystem.presentation.controller;

import Modelo.Inventario;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Inventario_Table_Model;
import rcrsystem.presentation.view.VentanaInventario;
import rcrsystem.presentation.model.Modelo;
import rcrsystem.presentation.view.VentanaCarga;

public class Inventario_Controlador {

    public Inventario_Controlador(VentanaInventario vista, Modelo modelo, VentanaCarga a_carga) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        cargar(a_carga);
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar(VentanaCarga a_carga) {
        List<Inventario> inv = rcrsystem.Aplicacion.ae_modelo.obtener_inventario();
        //  a_modelo.colocar_inventario(inv, 0);
        int[] cols = {Inventario_Table_Model.ae_codigo, Inventario_Table_Model.ae_material, Inventario_Table_Model.ae_peso};
        a_carga.setVisible(false);
        a_modelo.revisar_inventario(inv);

        a_modelo.colocar_modelo_inventarios(new Inventario_Table_Model(cols, inv));
    }

    public void cargar2(VentanaCarga a_carga) {
        List<Inventario> inv = rcrsystem.Aplicacion.ae_modelo.obtener_inventario();
        //  a_modelo.colocar_inventario(inv, 0);
        int[] cols = {Inventario_Table_Model.ae_codigo, Inventario_Table_Model.ae_material, Inventario_Table_Model.ae_peso};
        a_carga.setVisible(false);

        a_modelo.colocar_modelo_inventarios(new Inventario_Table_Model(cols, inv));
    }

    public void abrir(int row) {
        try {
            Inventario seleccionada = a_modelo.obtener_modelo_inventarios().obtener_fila_a(row);
            a_modelo.colocar_inventario_actual(seleccionada);
            rcrsystem.presentation.view.VentanaPrincipal.vistaEdicion.setVisible(true);
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ningún material!");
        }
    }

    public void buscar(VentanaCarga a_carga, String bulto) {
        List<Inventario> inv = rcrsystem.Aplicacion.ae_modelo.obtener_inventarioXBulto(bulto);
        //  a_modelo.colocar_inventario(inv, 0);
        int[] cols = {Inventario_Table_Model.ae_codigo, Inventario_Table_Model.ae_material, Inventario_Table_Model.ae_peso};
        a_carga.setVisible(false);

        a_modelo.colocar_modelo_inventarios(new Inventario_Table_Model(cols, inv));
    }

    private final VentanaInventario a_vista;
    private final Modelo a_modelo;
} // Fin de la clase Inventario_Controlador
