package rcrsystem.presentation.controller;

import Modelo.Proveedor;
import Modelo.dao.Proveedor_DAO;
import java.util.List;
import rcrsystem.presentation.model.Proveedor_Modelo;
import rcrsystem.presentation.view.VentanaProveedor;

public class Ag_Ed_Proveedor_Controlador {

    public Ag_Ed_Proveedor_Controlador(VentanaProveedor vista, Proveedor_Modelo modelo, Admin_Proveedores_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }

    public void agregar() {
        Proveedor proveedor = new Proveedor();
        a_modelo.limpiar_errores();

        if (a_vista.jTextField_nombre.getText().length() == 0) {
            a_modelo.obtener_errores().put("nombre", "Debe ingresar el nombre del proveedor");
        } else {
            proveedor.poner_nombre(a_vista.jTextField_nombre.getText());
        }
        if (a_vista.jTextField_id.getText().length() == 0) {
            a_modelo.obtener_errores().put("id", "Debe ingresar la cédula del proveedor");
        } else {
            proveedor.poner_codigo(a_vista.jTextField_id.getText());
        }
        if (a_vista.jTextField_lugar.getText().length() == 0) {
            a_modelo.obtener_errores().put("lugar", "Debe ingresar un lugar");
        } else {
            proveedor.poner_lugar(a_vista.jTextField_lugar.getText());
        }
        if (a_vista.jTextField_Contacto.getText().length() == 0) {
            a_modelo.obtener_errores().put("contacto", "Debe ingresar una persona contacto");
        } else {
            proveedor.poner_personaContacto(a_vista.jTextField_Contacto.getText());
        }
        if (a_vista.jTextField_telCelular.getText().length() == 0) {
            a_modelo.obtener_errores().put("celular", "Debe ingresar un teléfono celular");
        } else {
            proveedor.poner_telCelular(Integer.parseInt(a_vista.jTextField_telCelular.getText()));
        }
        if (a_vista.jTextField_telFijo.getText().length() == 0) {
            a_modelo.obtener_errores().put("fijo", "Debe ingresar un teléfono fijo");
        } else {
            proveedor.poner_telFijo(Integer.parseInt(a_vista.jTextField_telFijo.getText()));
        }

        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                        if (Proveedor_DAO.grabar(proveedor) == 0) {
                            a_modelo.obtener_errores().put("id", "¡Proveedor ya existe!");
                            a_modelo.colocar_mensaje("¡El proveedor ya está registrado!");
                            a_modelo.colocar_proveedor_actual(proveedor);
                        } else {
                            a_modelo.colocar_mensaje("¡Proveedor Agregado!");
                            a_modelo.colocar_proveedor_actual(new Proveedor());
                            a_modelo.colocar_mensaje("");
                            a_modelo.colocar_filtro(new Proveedor());
                            a_controlador.reiniciar_filtro();

                            List<Proveedor> proveedors = Proveedor_DAO.obtener_proveedores();
                            a_modelo.colocar_proveedores(proveedors, 0);
                            a_controlador.colocar_proveedoress(proveedors);
                            a_modelo.limpiar_errores();
                            a_vista.setVisible(false);
                        }
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:

                        Proveedor_DAO.actualizar(proveedor);
                        a_modelo.colocar_mensaje("¡Proveedor modificado!");
                        a_modelo.colocar_proveedor_actual(new Proveedor());
                        a_modelo.colocar_mensaje("");

                        a_modelo.colocar_filtro(new Proveedor());
                        a_controlador.reiniciar_filtro();
                        List<Proveedor> proveedors2 = Proveedor_DAO.obtener_proveedores();
                        a_modelo.colocar_proveedores(proveedors2, 0);
                        a_controlador.colocar_proveedoress(proveedors2);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                a_modelo.colocar_mensaje("¡El proveedor no se puede eliminar!");
                a_modelo.colocar_proveedor_actual(proveedor);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_proveedor_actual(proveedor);
        }
    }
    private VentanaProveedor a_vista;
    private Proveedor_Modelo a_modelo;
    private Admin_Proveedores_Controlador a_controlador;
} // Fin de la clase Ag_Ed_Proveedor_Controlador
