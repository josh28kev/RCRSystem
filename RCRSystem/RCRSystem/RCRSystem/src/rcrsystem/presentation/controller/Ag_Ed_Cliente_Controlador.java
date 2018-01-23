package rcrsystem.presentation.controller;

import Modelo.Cliente;
import Modelo.dao.Cliente_DAO;
import java.util.List;
import rcrsystem.presentation.model.Cliente_Modelo;
import rcrsystem.presentation.view.VentanaCliente;

public class Ag_Ed_Cliente_Controlador {

    public Ag_Ed_Cliente_Controlador(VentanaCliente vista, Cliente_Modelo modelo, Admin_Cliente_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }

    public void agregar() {
        Cliente cliente = new Cliente();
        a_modelo.limpiar_errores();

        if (a_vista.jTextField_nombre.getText().length() == 0) {
            a_modelo.obtener_errores().put("nombre", "Debe ingresar el nombre del cliente");
        } else {
            cliente.poner_nombre_c(a_vista.jTextField_nombre.getText());
        }
        if (a_vista.jTextField_id.getText().length() == 0) {
            a_modelo.obtener_errores().put("id", "Debe ingresar la cédula del cliente");
        } else {
            cliente.poner_codigo_c(a_vista.jTextField_id.getText());
        }

        if (a_vista.jTextField_Tel.getText().length() == 0) {
            a_modelo.obtener_errores().put("telefono", "Debe ingresar el teléfono del cliente");
        } else {
            cliente.poner_telefono_c(a_vista.jTextField_Tel.getText());
        }

        if (a_vista.jTextField_Direccion.getText().length() == 0) {
            a_modelo.obtener_errores().put("direccion", "Debe ingresar la dirección del cliente");
        } else {
            cliente.poner_direccion_c(a_vista.jTextField_Direccion.getText());
        }

        if (a_vista.jTextField_Contacto.getText().length() == 0) {
            a_modelo.obtener_errores().put("contacto", "Debe ingresar el contacto del cliente");
        } else {
            cliente.poner_contacto_c(a_vista.jTextField_Contacto.getText());
        }

        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                        if (Cliente_DAO.grabar(cliente) == 0) {
                            a_modelo.obtener_errores().put("id", "¡Cliente ya existe!");
                            a_modelo.colocar_mensaje("¡El cliente ya está registrado!");
                            a_modelo.colocar_cliente_actual(cliente);
                        } else {

                            a_modelo.colocar_mensaje("¡Cliente Agregado!");
                            a_modelo.colocar_cliente_actual(new Cliente());
                            a_modelo.colocar_mensaje("");
                            a_modelo.colocar_filtro(new Cliente());
                            a_controlador.reiniciar_filtro();

                            List<Cliente> clientes = Cliente_DAO.obtener_clientes();
                            a_modelo.colocar_clientes(clientes, 0);
                            a_controlador.colocar_clientes(clientes);
                            a_modelo.limpiar_errores();
                            a_vista.setVisible(false);
                        }
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:

                        Cliente_DAO.actualizar(cliente);
                        a_modelo.colocar_mensaje("¡Cliente modificado!");
                        a_modelo.colocar_cliente_actual(new Cliente());
                        a_modelo.colocar_mensaje("");

                        a_modelo.colocar_filtro(new Cliente());
                        a_controlador.reiniciar_filtro();
                        List<Cliente> clientes2 = Cliente_DAO.obtener_clientes();
                        a_modelo.colocar_clientes(clientes2, 0);
                        a_controlador.colocar_clientes(clientes2);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {

                a_modelo.colocar_mensaje("¡El cliente no se puede eliminar!");
                a_modelo.colocar_cliente_actual(cliente);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_cliente_actual(cliente);
        }
    }
    private VentanaCliente a_vista;
    private Cliente_Modelo a_modelo;
    private Admin_Cliente_Controlador a_controlador;
} // Fin de la clase Ag_Ed_Cliente_Controlador
