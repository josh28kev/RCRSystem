package rcrsystem.presentation.controller;

import Modelo.Material;
import Modelo.dao.Material_DAO;
import rcrsystem.presentation.view.VentConsultaMatInv;
import rcrsystem.presentation.model.Modelo_B_C;
import rcrsystem.presentation.view.VentanaInventario;

public class Bultos_C_Controlador {

    public Bultos_C_Controlador(VentConsultaMatInv vista, Modelo_B_C modelo, String id, String nombre, VentanaInventario a_inventario, int row) {
        this.a_vista = vista;
        this.a_modelo_b_c = modelo;
        cargar_bultos(id);
        vista.setController(this);
        vista.setModel(modelo);
        this.nombre = "";
        this.a_inventario = a_inventario;
    }

    public void colocar_nombre(String nombre) {
        this.nombre = nombre;
    }

    public String obtener_nombre() {
        return this.nombre;
    }

    public void cerrar_ventana() {
        this.a_vista.setVisible(false);
        this.a_inventario.setVisible(true);
    }

    public void cargar_bultos(String id) {
        Material material = Material_DAO.obtener_material(id);
        a_modelo_b_c.colocar_bulto_c(rcrsystem.Aplicacion.ae_modelo_bulto_dao.obtener_bultos_comprados_por_material(id));
        a_vista.letrero_jLabel.setText("Bultos disponibles del material " + material.obtener_nombre());
    }

    public Modelo_B_C getA_modelo_b_c() {
        return a_modelo_b_c;
    }

    public void setA_modelo_b_c(Modelo_B_C a_modelo_b_c) {
        this.a_modelo_b_c = a_modelo_b_c;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    private VentConsultaMatInv a_vista;
    private Modelo_B_C a_modelo_b_c;
    private String nombre;
    private VentanaInventario a_inventario;
    private int row;
} // Fin de la clase Bultos_C_Controlador
