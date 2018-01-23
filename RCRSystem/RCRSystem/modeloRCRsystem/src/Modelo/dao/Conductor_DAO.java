package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Conductor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conductor_DAO {

    public static int grabar(Conductor cnt) {
        String fecha;
        if(cnt.obtener_fecha_nacimiento() == ""){
            fecha = "2017-01-01";
        }else{
            fecha = cnt.obtener_fecha_nacimiento();
        }
        String sql = "INSERT INTO Conductor (identificacionConductor,nombreConductor,nacionalidadConductor,fechaNacimientoConductor) Values ('"
                + cnt.obtener_id() + "','"
                + cnt.obtener_nombre() + "','"
                + cnt.obtener_nacionalidad() + "','"
                + fecha + "')";
        return Conexion.guardar_registro(sql);
    }

    public static boolean existe_conductor(String id) {
        //Connection connection = null;
        boolean bandera = false;  //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Conductor cnt = new Conductor();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Conductor_DAO.ae_seleccionar_todo + " where identificacionConductor=  '" + id + "'");
                if (rs.next()) {
                    bandera = true;
                }
                rs.close();
                Conexion.ae_con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            bandera = false;
        }
        return bandera;
    }

    public static Conductor obtener_conductor(String id) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Conductor cnt = new Conductor();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Conductor_DAO.ae_seleccionar_todo + " where identificacionConductor=  '" + id + "'");
                if (rs.next()) {
                    cnt = new Conductor();
                    cnt.poner_id(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    cnt.poner_nacionalidad(rs.getString(3));
                    cnt.poner_fecha_nacimiento(rs.getString(4));
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

    public static String ae_nombre_tabla = "Conductor";
    public static String ae_seleccionar_todo = "select * from " + Conductor_DAO.ae_nombre_tabla;
}//Fin de la clase Conductor_DAO
