package rcrsystem.presentation.controller;

import Modelo.Modelo_Usuario;
import Modelo.dao.Usuario_DAO;
import javax.swing.JOptionPane;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaInicio;

public class Usuario_Controlador {

    public Usuario_Controlador() {
        a_modelo_usuario = new Modelo_Usuario();
    }

    public Usuario_Controlador(Modelo_Usuario model, VentanaInicio view) {
        this.a_modelo_usuario = model;
        this.a_vista = view;
        view.setController(this);
        view.setModel(model);
    }

    public void colocar_usuario() {
        rcrsystem.Aplicacion.ae_usuario = a_modelo_usuario.obtener_usuario_actual();
    }

    public String validar_ingreso(String identificacion, String pass) {
        return a_modelo_usuario.validar_ingreso(identificacion, pass);
    }

    public void reiniciar_contraseñas(VentanaCarga ventana) {
        try {
            rcrsystem.Aplicacion.ae_vista_principal.setVisible(false);
            ventana.setVisible(true);
            Usuario_DAO.reiniciar_contraseñas();
            ventana.setVisible(false);
            rcrsystem.Aplicacion.ae_vista_principal.setVisible(true);
            JOptionPane.showMessageDialog(null, "¡Contraseñas reiniciadas correctamente!", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            ventana.setVisible(false);
            a_vista.setVisible(true);
            JOptionPane.showMessageDialog(null, "¡Hubo un error!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private VentanaInicio a_vista;
    private Modelo_Usuario a_modelo_usuario;
} // Fin de la clase Usuario_Controlador
