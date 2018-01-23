package rcrsystem.presentation.controller;

import Modelo.Administrar_Material;
import Modelo.dao.Administrar_Material_DAO;
import Modelo.dao.Inventario_DAO;
import java.util.List;
import rcrsystem.presentation.model.Material_Modelo;
import rcrsystem.presentation.view.VentanaAdminMaterial;

public class Admin_Material_Controlador {

    public Admin_Material_Controlador(VentanaAdminMaterial vista, Material_Modelo modelo, Materiales_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }

    public void agregar() {
        Administrar_Material material = new Administrar_Material();
        a_modelo.limpiar_errores();

        material.obtener_material().poner_codigo(a_vista.jTextField_codigo.getText());
        if (a_vista.jTextField_codigo.getText().length() == 0) {
            a_modelo.obtener_errores().put("codigo", "Debe digitar el código del material");
        }

        material.obtener_material().poner_nombre(a_vista.jTextField_descripcion.getText());
        if (a_vista.jTextField_descripcion.getText().length() == 0) {
            a_modelo.obtener_errores().put("descripcion", "Debe digitar la descripción del material");
        }
        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                        if (Administrar_Material_DAO.grabar(material) == 0) {
                            a_modelo.obtener_errores().put("id", "¡Material ya existe!");
                            a_modelo.colocar_mensaje("¡El material ya está registrado!");
                            a_modelo.colocar_material_actual(material);
                        } else {
                            Administrar_Material_DAO.grabar_tipo(material);
                            Inventario_DAO.grabar(material.obtener_material());
                            a_modelo.setFiltro(new Administrar_Material());

                            a_modelo.colocar_mensaje("¡Material Agregado!");
                            a_modelo.colocar_material_actual(new Administrar_Material());
                            a_modelo.colocar_mensaje("");
                            a_controlador.reiniciar_filtro();
                            List<Administrar_Material> materiales = Administrar_Material_DAO.obtener_materiales();
                            a_modelo.colocar_materiales(materiales, 0);
                            a_modelo.limpiar_errores();
                            a_vista.setVisible(false);
                        }
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:
                        Administrar_Material_DAO.actualizar(material);
                        a_modelo.colocar_mensaje("¡Material modificado!");

                        a_modelo.colocar_material_actual(new Administrar_Material());
                        a_modelo.colocar_mensaje("");
                        a_modelo.setFiltro(new Administrar_Material());
                        a_controlador.reiniciar_filtro();

                        List<Administrar_Material> materiales2 = Administrar_Material_DAO.obtener_materiales();
                        a_modelo.colocar_materiales(materiales2, 0);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                a_modelo.obtener_errores().put("id", "¡Material ya existe!");
                a_modelo.colocar_mensaje("¡El material ya está registrado!");
                a_modelo.colocar_material_actual(material);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_material_actual(material);
        }
    }

    private VentanaAdminMaterial a_vista;
    private Material_Modelo a_modelo;
    private Materiales_Controlador a_controlador;
} // Fin de la clase Admin_Material_Controlador

