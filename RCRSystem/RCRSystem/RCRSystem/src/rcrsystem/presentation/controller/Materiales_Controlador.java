package rcrsystem.presentation.controller;

import Modelo.Administrar_Material;
import Modelo.dao.Administrar_Material_DAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Material_Modelo;
import rcrsystem.presentation.view.VentanaAdminMaterial;
import rcrsystem.presentation.view.VentanaMaterial;

public class Materiales_Controlador {

    public Materiales_Controlador(VentanaMaterial vista, Material_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_materiales = new ArrayList();
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        this.colocar_materiales(Administrar_Material_DAO.obtener_materiales());
        a_modelo.colocar_materiales(a_materiales, 0);
    }
    
    public void editar(int row, VentanaAdminMaterial ventana) {
        try {
            a_modelo.limpiar_errores();
            Administrar_Material seleccionada = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_editar);
            a_modelo.colocar_material_actual(seleccionada);
            ventana.jTextField_codigo.setEnabled(false);
            ventana.setVisible(true);
           
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ningún elemento!");
        }
    }

    public List<Administrar_Material> obtener_materiales() {
        return a_materiales;
    }

    public void colocar_materiales (List<Administrar_Material> materiales) {
        this.a_materiales = materiales;
    }

    public void buscar() {
        a_modelo.setFiltro(new Administrar_Material());
        a_modelo.obtener_filtro().obtener_material().poner_codigo(a_vista.jTextField_filtro.getText());
        List<Administrar_Material> rows = new ArrayList();
        for (Administrar_Material p : this.obtener_materiales()) {
            if (p.obtener_material().obtener_codigo().contains(a_modelo.obtener_filtro().obtener_material().obtener_codigo())) {
                rows.add(p);
            }
        }
        a_modelo.colocar_materiales(rows, 0);
    }
    
    public void reiniciar_filtro(){
        a_vista.jTextField_filtro.setText("");
    }
     
    public void pre_agregar() {
        a_modelo.limpiar_errores();
        a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_agregar);
        Administrar_Material material = new Administrar_Material();
        a_modelo.colocar_material_actual(material);
    }
    
    private VentanaMaterial a_vista;
    private Material_Modelo a_modelo;
    private List<Administrar_Material> a_materiales;
} // Fin de la clase Materiales_Controlador