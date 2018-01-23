package Modelo;

import Modelo.dao.Usuario_DAO;
import java.util.List;
import java.util.Observer;

public class Modelo_Usuario extends java.util.Observable {

    public Modelo_Usuario() {
        a_cnl = new Usuario_DAO();
    }

    public Usuario obtener_usuario(int indice) {
        return a_usuarios.get(indice);
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_cliente_actual);
        setChanged();

    }

    private void initUsuario() {

        poner_usuario_actual(new Usuario());
        //clearErrors();
    }

    public Usuario obtener_usuario_actual() {
        return a_usuario_actual;
    }

    public void poner_usuario_actual(Usuario usuarioCurrent) {
        this.a_usuario_actual = usuarioCurrent;
      //  setChanged();
       // notifyObservers(CLIENTE_CURRENT);
    }

    public String validar_ingreso(String identificacion, String pass) {
        Usuario miUsuario = Usuario_DAO.obtener_usuario(identificacion, pass);
        
        if (!identificacion.equals("") || !pass.equals("")) {
            if (miUsuario != null) {
                if (pass.equals(miUsuario.obtener_contraseña())) {
                    this.poner_usuario_actual(miUsuario);
                    return Integer.toString(miUsuario.obtener_puesto());
                } else {
                    return "invalido";//si el usuario o contraseña son incorrectos 
                }

            } else {
                return "desconectado";//si no hay conexion a la base
            }
        }
        return "errores";
    }

    private List<Usuario> a_usuarios;
    public Usuario_DAO a_cnl;
    public final int a_seleccion = 0;
    public static Integer ae_cliente_actual = 1;
    public Usuario a_usuario_actual;
}//Fin de la clase Modelo_Usuario
