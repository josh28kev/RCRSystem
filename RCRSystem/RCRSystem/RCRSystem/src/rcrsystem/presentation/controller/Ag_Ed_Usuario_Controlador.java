package rcrsystem.presentation.controller;

import Modelo.Usuario;
import Modelo.dao.Usuario_DAO;
import java.util.List;
import rcrsystem.presentation.model.Usuario_Modelo;
import rcrsystem.presentation.view.VentanaUsuario;

public class Ag_Ed_Usuario_Controlador {

    public Ag_Ed_Usuario_Controlador(VentanaUsuario vista, Usuario_Modelo modelo, Admin_Usuario_Controlador controlador) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        this.a_controlador = controlador;
        vista.setControlador(this);
        vista.setModelo(modelo);
    }

    public void agregar() {
        Usuario usuario = new Usuario();
        a_modelo.limpiar_errores();

        if (a_vista.jTextField_nombre.getText().length() == 0) {
            a_modelo.obtener_errores().put("nombre", "Debe ingresar el nombre del usuario");
        } else {
            usuario.poner_nombre(a_vista.jTextField_nombre.getText());
        }
        if (a_vista.jTextField_id.getText().length() == 0) {
            a_modelo.obtener_errores().put("id", "Debe ingresar la identificación del usuario");
        } else {
            usuario.colocar_identificacion(a_vista.jTextField_id.getText());
        }
        if (a_vista.jTextField_telf.getText().length() == 0) {
            a_modelo.obtener_errores().put("telefono", "Debe ingresar el número de teléfono del usuario");
        } else {
            usuario.poner_numero_telefono(Integer.parseInt(a_vista.jTextField_telf.getText()));
        }
        usuario.poner_contraseña(a_vista.jPasswordField_normal.getText());
        if (a_vista.jPasswordField_normal.getText().length() < 6 || a_vista.jPasswordField_normal.getText().length() > 6) {
            a_modelo.obtener_errores().put("contraseña", "La contraseña debe ser de 6 caracteres");
        }
        if (a_vista.jPasswordField_confirmar.getText().length() == 0 && a_vista.jPasswordField_confirmar.getText().length() == 0) {

            a_modelo.obtener_errores().put("confirmar", "Debe repetir la contraseña ingresada");
        } else if (a_vista.jPasswordField_confirmar.getText().length() == 0) {
            a_modelo.obtener_errores().put("confirmar", "Debe repetir la contraseña ingresada");
            usuario.poner_contraseña(a_vista.jPasswordField_normal.getText());
        } else if (!a_vista.jPasswordField_normal.getText().equals(a_vista.jPasswordField_confirmar.getText())) {
            a_modelo.obtener_errores().put("coinciden", "Las contraseñas ingresadas no coinciden");
            usuario.poner_contraseña(a_vista.jPasswordField_normal.getText());
        } else {
            usuario.poner_contraseña(a_vista.jPasswordField_normal.getText());
        }

        if (a_vista.jRadioButton_admin.isSelected()) {
            usuario.poner_puesto(1);
        }
        if (a_vista.jRadioButton_compras.isSelected()) {
            usuario.poner_puesto(2);
        }
        if (a_vista.jRadioButton_venta.isSelected()) {
            usuario.poner_puesto(3);
        }

        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                switch (a_modelo.obtener_modo()) {
                    case rcrsystem.Aplicacion.ae_modo_agregar:
                        //Usuario_DAO.grabar(usuario);
                        if (Usuario_DAO.grabar(usuario) == 0) {
                            a_modelo.obtener_errores().put("id", "¡Usuario ya existe!");
                            a_modelo.colocar_mensaje("¡El usuario ya está registrado!");
                            a_modelo.colocar_usuario_actual(usuario);

                        } else {
                            a_modelo.colocar_mensaje("¡Usuario Agregado!");
                            a_modelo.colocar_usuario_actual(new Usuario());
                            a_modelo.colocar_mensaje("");
                            a_modelo.colocar_filtro(new Usuario());
                            a_controlador.reiniciar_filtro();
                            List<Usuario> usuarios = Usuario_DAO.obtener_usuarios();
                            a_modelo.colocar_usuarios(usuarios, 0);
                            a_controlador.colocar_usuarios(usuarios);
                            a_modelo.limpiar_errores();
                            a_vista.setVisible(false);
                        }
                        break;

                    case rcrsystem.Aplicacion.ae_modo_editar:

                        Usuario_DAO.actualizar(usuario);
                        a_modelo.colocar_mensaje("¡Usuario modificado!");
                        a_modelo.colocar_usuario_actual(new Usuario());
                        a_modelo.colocar_mensaje("");

                        a_modelo.colocar_filtro(new Usuario());
                        a_controlador.reiniciar_filtro();
                        List<Usuario> usuarios2 = Usuario_DAO.obtener_usuarios();
                        a_modelo.colocar_usuarios(usuarios2, 0);
                        a_controlador.colocar_usuarios(usuarios2);
                        a_modelo.limpiar_errores();
                        a_vista.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                a_modelo.colocar_mensaje("¡Ocurrió un error!");
                a_modelo.colocar_usuario_actual(usuario);
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_usuario_actual(usuario);
        }
    }
    private VentanaUsuario a_vista;
    private Usuario_Modelo a_modelo;
    private Admin_Usuario_Controlador a_controlador;
} // Fin de la clase Ag_Ed_Usuario_Controlador
