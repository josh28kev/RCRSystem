package rcrsystem;

import Modelo.Usuario;
import Modelo.dao.Bulto_DAO;
import Modelo.dao.Facturar_Venta_DAO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Modelo.dao.Inventario_DAO;
import Modelo.dao.Lista_Empaque_DAO;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import rcrsystem.presentation.controller.Usuario_Controlador;
import rcrsystem.presentation.view.VentanaInicio;
import rcrsystem.presentation.view.VentanaPrincipal;

public class Aplicacion {

    public void mostrar_interfaz() {
        //  ae_vista_principal = new VentanaInicio();
        ae_vista_principal.setController(a_control_usuario);
        //controlU.se
        ae_vista_principal.init();
    }

    public static VentanaPrincipal obtener_menu_principal() {
        return ae_menu_principal;
    }

    public static void colocar_menu_principal(VentanaPrincipal menuPrincipal) {
        Aplicacion.ae_menu_principal = menuPrincipal;
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            System.err.println(e.getMessage());
        }

        Aplicacion aplicacion = new Aplicacion();
        aplicacion.mostrar_interfaz();
    }
    
    public Usuario_Controlador a_control_usuario = new Usuario_Controlador();
    public static Inventario_DAO ae_modelo = new Inventario_DAO();
    public static Lista_Empaque_DAO ae_modelo_lista_empaquetado = new Lista_Empaque_DAO();
    public static Bulto_DAO ae_modelo_bulto_dao = new Bulto_DAO();
    public static Usuario ae_usuario = new Usuario();
    public static boolean ae_estado=false;
    public static Border ae_borde_error = BorderFactory.createLineBorder(Color.red);
    public static Border ae_no_borde = BorderFactory.createLineBorder(Color.red);
    public static VentanaInicio ae_vista_principal = new VentanaInicio();
    public static Facturar_Venta_DAO ae_modelo_factura = new Facturar_Venta_DAO();
    public static VentanaPrincipal ae_menu_principal;
    public static final int ae_modo_agregar = 0;
    public static final int ae_modo_editar = 1;
} // Fin de la clase Aplicacion
