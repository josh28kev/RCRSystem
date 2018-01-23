package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Base_De_Datos;
import Modelo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario_DAO {

    public static int grabar(Usuario cnt) {
        String sql = "INSERT INTO Usuarios (identificacion,nombre,numTelefono,puesto,contraseña) values ('"
                + cnt.obtener_identificacion() + "' ,'"
                + cnt.obtener_nombre() + "',"
                + cnt.obtener_numero_telefono() + ", "
                + cnt.obtener_puesto() + ", '"
                + cnt.obtener_contraseña()
                + "')";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    static public int actualizar(Usuario cnt) {
        String sql = "UPDATE Usuarios set nombre='"
                + cnt.obtener_nombre() + "',numTelefono="
                + cnt.obtener_numero_telefono() + ",puesto="
                + cnt.obtener_puesto() + ", contraseña= '"
                + cnt.obtener_contraseña() + "' where identificacion= '"
                + cnt.obtener_identificacion() + "'";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static List<Usuario> obtener_usuarios() {
        List<Usuario> ListaUsuarios = new ArrayList();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = Conexion.obtener_registros(Usuario_DAO.ae_seleccionar_todo);

            try {
                while (rs.next()) {
                    Usuario cnt = new Usuario();
                    cnt.colocar_identificacion(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    cnt.poner_numero_telefono(rs.getInt(3));
                    cnt.poner_puesto(rs.getInt(4));
                    cnt.poner_contraseña(rs.getString(5));
                    ListaUsuarios.add(cnt);
                }
                rs.close();
                Conexion.ae_con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            ListaUsuarios = null;
        }
        return ListaUsuarios;
    }

    public static Usuario obtener_usuario(String identificacion, String pass) {
        Usuario cnt = new Usuario();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Usuario_DAO.ae_seleccionar_todo + " where identificacion=  '" + identificacion + "' and contraseña = '" + pass + "'");
                if (rs.next()) {
                    cnt = new Usuario();
                    cnt.colocar_identificacion(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    cnt.poner_numero_telefono(rs.getInt(3));
                    cnt.poner_puesto(rs.getInt(4));
                    cnt.poner_contraseña(rs.getString(5));
                }
                rs.close();
                Conexion.ae_con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            cnt = null;
        }

        return cnt;
    }

    static public int reiniciar_contraseñas() {
        String sql = "UPDATE Usuarios set contraseña = '123456' where puesto = 1";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    static public int eliminar(Usuario cnt) {
        String sql = "delete from Usuarios"
                + " where identificacion= '" + cnt.obtener_identificacion() + "'";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static String ae_nombre_tabla = "Usuarios";
    public static String ae_seleccionar_todo = "select * from " + Usuario_DAO.ae_nombre_tabla;
    static Base_De_Datos ae_bd = new Base_De_Datos(null, null, null);
}//Fin de la clase Usuario_DAO
