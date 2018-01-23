package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Base_De_Datos;
import Modelo.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Material_DAO {

    public int grabar(Material cnt) {
        String sql = "INSERT INTO Materiales (codigoMaterial,nombre) Values ("
                + cnt.obtener_codigo() + ",'"
                + cnt.obtener_nombre()
                + ")";
        return Base_De_Datos.ejecutar_actualizacion(sql);
    }

    public int actualizar(Material cnt) {
        String sql = "UPDATE Materiales SET nombre='" + cnt.obtener_nombre()
                + " where codigoMaterial=" + cnt.obtener_codigo();
        return Base_De_Datos.ejecutar_actualizacion(sql);
    }

    public int borrar(Material cnt) {
        String sql = "DELETE  FROM Materiales WHERE codigoMaterial=" + cnt.obtener_codigo();
        return Base_De_Datos.ejecutar_actualizacion(sql);
    }

    public static List<Material> obtener_materiales() {
        List<Material> resultado;
        resultado = new ArrayList<Material>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Material_DAO.ae_seleccionar_todo);
                while (rs.next()) {
                    Material cnt = new Material();
                    cnt.poner_codigo(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    resultado.add(cnt);
                }
                rs.close();
                Conexion.ae_con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            resultado = null;
        }
        return resultado;
    }

    public static Material obtener_material(String codigoMaterial) {
        ResultSet rs = Conexion.obtener_registros(Material_DAO.ae_seleccionar_todo + " where codigoMaterial = '" + codigoMaterial + "'");
        Material cnt = null;
        try {
            if (rs.next()) {
                cnt = new Material();
                cnt.poner_codigo(rs.getString(1));
                cnt.poner_nombre(rs.getString(2));
            }
            rs.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static String ae_nombre_tabla = "Materiales";
    public static String ae_seleccionar_todo = "select * from " + Material_DAO.ae_nombre_tabla;
}//Fin de la clase Material_DAO
