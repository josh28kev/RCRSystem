package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Bulto;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bulto_DAO {

    public static int grabar(Bulto cnt) {
        String sql = "INSERT INTO Bulto (codigoBulto,tipoBulto,pesoBulto,materialBulto) Values ('"
                + cnt.obtener_codigo() + "',"
                + cnt.obtener_tipo() + ","
                + cnt.obtener_peso() + ",'"
                + cnt.obtener_material().obtener_codigo() + "'"
                + ")";
        return Conexion.guardar_registro(sql);
    }

    public static int actualizar(Bulto cnt) {
        String sql = "UPDATE Bulto SET estado=0 "
                + " where codigoBulto='" + cnt.obtener_codigo() + "'";
        return Conexion.guardar_registro(sql);
    }

    /* public static int borrar(Bulto cnt) {
        String sql = "DELETE  FROM Bulto WHERE codigoBulto=" + cnt.obtener_codigo();
        return Conexion.guardar_registro(sql);
    }*/
    public static int borrar2(Bulto cnt) {
        String sql = "update bulto set estado = 0 where codigobulto =" + "'" + cnt.obtener_codigo() + "'";
        return Conexion.guardar_registro(sql);
    }

    public static List<Bulto> obtener_bultos_comprados() {
        ResultSet bulto = Conexion.obtener_registros(Bulto_DAO.ae_seleccionar_todo);
        List<Bulto> ListaBultos = new ArrayList();

        try {
            while (bulto.next()) {
                Bulto cnt = new Bulto();
                cnt.poner_codigo(bulto.getString(1));
                cnt.poner_tipo(bulto.getInt(2));
                cnt.poner_peso(new BigDecimal(bulto.getString(3)));
                cnt.poner_material(Material_T_DAO.obtener_material_tipo(bulto.getString(4)));
                cnt.poner_estado(bulto.getInt(5));
                ListaBultos.add(cnt);
            }

            bulto.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static Bulto obtener_bulto(String codigoBulto) {
        ResultSet bulto = Conexion.obtener_registros(Bulto_DAO.ae_seleccionar_todo + " where codigoBulto = '" + codigoBulto + "'");
        Bulto cnt = null;
        try {
            if (bulto.next()) {
                cnt = new Bulto();
                cnt.poner_codigo(bulto.getString(1));
                cnt.poner_tipo(bulto.getInt(2));
                cnt.poner_peso(new BigDecimal(bulto.getString(3)));
                cnt.poner_material(Material_T_DAO.obtener_material_tipo(bulto.getString(4)));
                cnt.poner_estado(bulto.getInt(5));
            }
            bulto.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    //devuelve la lista de todos los bultos comprados que sean del tipo de materia codMaterial
    public static List<Bulto> obtener_bultos_comprados_por_material(String codMaterial) {
        ResultSet bulto = Conexion.obtener_registros("Select * from Bulto where (materialBulto ='" + ("P" + codMaterial)
                + "' or materialBulto ='" + ("S" + codMaterial) + "') and estado = 1");
        List<Bulto> ListaBultos = new ArrayList();
        try {
            while (bulto.next()) {
                Bulto cnt = new Bulto();
                cnt.poner_codigo(bulto.getString(1));
                cnt.poner_tipo(bulto.getInt(2));
                cnt.poner_peso(new BigDecimal(bulto.getString(3)));
                cnt.poner_material(Material_T_DAO.obtener_material_tipo(bulto.getString(4)));
                cnt.poner_estado(bulto.getInt(5));
                ListaBultos.add(cnt);
            }
            bulto.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static List<Bulto> getBultosVentaXMaterial(String codMaterial) {
        ResultSet bulto = Conexion.obtener_registros("Select * from Bulto where (materialBulto ='" + ("P" + codMaterial)
                + "' or materialBulto ='" + ("S" + codMaterial) + "') and estado = 0");
        List<Bulto> ListaBultos = new ArrayList();
        try {
            while (bulto.next()) {
                Bulto cnt = new Bulto();
                cnt.poner_codigo(bulto.getString(1));
                cnt.poner_tipo(bulto.getInt(2));
                cnt.poner_peso(new BigDecimal(bulto.getString(3)));
                cnt.poner_material(Material_T_DAO.obtener_material_tipo(bulto.getString(4)));
                cnt.poner_estado(bulto.getInt(5));
                ListaBultos.add(cnt);
            }
            bulto.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static String ae_nombre_tabla = "Bulto";
    public static String ae_seleccionar_todo = "select * from " + Bulto_DAO.ae_nombre_tabla;
}//Fin de la clase Bulto_DAO
