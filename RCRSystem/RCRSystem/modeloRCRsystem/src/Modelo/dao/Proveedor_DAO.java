package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Base_De_Datos;
import Modelo.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class Proveedor_DAO {

    public static int grabar(Proveedor cnt) {
        String sql = "INSERT INTO Proveedor (codigo,nombre,telFijo,telCelular,lugar,personaContacto) Values ('"
                + cnt.obtener_codigo() + "','"
                + cnt.obtener_nombre() + "',"
                + cnt.obtener_telFijo() + ","
                + cnt.obtener_telCelular() + ",'"
                + cnt.obtener_lugar() + "','"
                + cnt.obtener_personaContacto()
                + "')";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static int actualizar(Proveedor cnt) {
        String sql = "UPDATE Proveedor set nombre='" + cnt.obtener_nombre()
                + "',telFijo=" + cnt.obtener_telFijo()
                + ",telCelular=" + cnt.obtener_telCelular()
                + ",lugar='" + cnt.obtener_lugar()
                + "',personaContacto='" + cnt.obtener_personaContacto()
                + "' where codigo='" + cnt.obtener_codigo() + "'";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static Collection<Proveedor> obtener_proveedores_2() {
        Base_De_Datos baseDatos = new Base_De_Datos(null, null, null);
        ResultSet proveedor = baseDatos.ejecutar_consulta(Proveedor_DAO.ae_seleccionar_todo);
        java.util.Hashtable<String, Proveedor> proveedores = new java.util.Hashtable<String, Proveedor>();

        try {
            while (proveedor.next()) {
                Proveedor cnt = new Proveedor();
                cnt.poner_codigo(proveedor.getString(1));
                cnt.poner_nombre(proveedor.getString(2));
                cnt.poner_telFijo(proveedor.getInt(3));
                cnt.poner_telCelular(proveedor.getInt(4));
                cnt.poner_lugar(proveedor.getString(5));
                cnt.poner_personaContacto(proveedor.getString(6));
                proveedores.put(cnt.obtener_codigo(), cnt);
            }
            proveedor.close();
            baseDatos.ae_cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Vector(proveedores.values());
    }

    public static List<Proveedor> obtener_proveedores() {
        List<Proveedor> resultado;
        resultado = new ArrayList<Proveedor>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Proveedor_DAO.ae_seleccionar_todo);
                while (rs.next()) {
                    Proveedor cnt = new Proveedor();
                    cnt.poner_codigo(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    cnt.poner_telFijo(rs.getInt(3));
                    cnt.poner_telCelular(rs.getInt(4));
                    cnt.poner_lugar(rs.getString(5));
                    cnt.poner_personaContacto(rs.getString(6));
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

    public static Proveedor obtener_proveedor(String codigo) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Proveedor cnt = new Proveedor();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Proveedor_DAO.ae_seleccionar_todo + " where codigo=  '" + codigo + "'");
                if (rs.next()) {
                    cnt = new Proveedor();
                    cnt.poner_codigo(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    cnt.poner_telFijo(rs.getInt(3));
                    cnt.poner_telCelular(rs.getInt(4));
                    cnt.poner_lugar(rs.getString(5));
                    cnt.poner_personaContacto(rs.getString(6));
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

    public static List<Proveedor> obtener_proveedores(String id) {
        List<Proveedor> ListaProveedores = new ArrayList();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = Conexion.obtener_registros(Proveedor_DAO.ae_seleccionar_todo + " where identificacion != '" + id + "'");

            try {
                while (rs.next()) {
                    Proveedor cnt = new Proveedor();
                    cnt.poner_codigo(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    cnt.poner_telFijo(rs.getInt(3));
                    cnt.poner_telCelular(rs.getInt(4));
                    cnt.poner_lugar(rs.getString(5));
                    cnt.poner_personaContacto(rs.getString(6));
                    ListaProveedores.add(cnt);
                }
                rs.close();
                Conexion.ae_con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            ListaProveedores = null;
        }
        return ListaProveedores;
    }

    static public int eliminar(Proveedor cnt) {
        String sql = "delete from Proveedor"
                + " where codigo= '" + cnt.obtener_codigo() + "'";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static Proveedor obtener_Proveedor_Bulto(String codigo) {
        Proveedor cnt = new Proveedor();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros("select Proveedor.codigo, Proveedor.nombre, Proveedor.telFijo, Proveedor.telCelular, Proveedor.lugar, Proveedor.personaContacto from Bulto, RegCompra_U_Bulto, ReporteCompra, Proveedor where\n"
                        + "Bulto.codigobulto = RegCompra_U_Bulto.bulto and\n"
                        + "ReporteCompra.regCompra = RegCompra_U_Bulto.regCompra and\n"
                        + "Proveedor.codigo = ReporteCompra.proveedor and\n"
                        + "Bulto.codigobulto= '" + codigo + "'");
                if (rs.next()) {
                    cnt = new Proveedor();
                    cnt.poner_codigo(rs.getString(1));
                    cnt.poner_nombre(rs.getString(2));
                    cnt.poner_telFijo(rs.getInt(3));
                    cnt.poner_telCelular(rs.getInt(4));
                    cnt.poner_lugar(rs.getString(5));
                    cnt.poner_personaContacto(rs.getString(6));
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

    public static String ae_nombre_tabla = "PROVEEDOR";
    public static String ae_seleccionar_todo = "select * from " + Proveedor_DAO.ae_nombre_tabla;
    static Base_De_Datos ae_bd = new Base_De_Datos(null, null, null);
}//Fin de la clase Proveedor_DAO
