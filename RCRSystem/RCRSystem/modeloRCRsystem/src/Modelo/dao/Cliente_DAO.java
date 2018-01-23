package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Base_De_Datos;
import Modelo.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class Cliente_DAO {

    public static int grabar(Cliente cnt) {
        String sql = "INSERT INTO Cliente (codigoC,nombreC,telefonoC,direccionC,contactoC) Values ('"
                + cnt.obtener_codigo_c() + "','"
                + cnt.obtener_nombre_c() + "','"
                + cnt.obtener_telefono_c() + "','"
                + cnt.obtener_direccion_c() + "','"
                + cnt.obtener_contacto_c() + "')";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static int actualizar(Cliente cnt) {
        String sql = "UPDATE Cliente SET nombreC='" + cnt.obtener_nombre_c() + "',"
                + "telefonoC='" + cnt.obtener_telefono_c() + "',"
                + "direccionC='" + cnt.obtener_direccion_c() + "',"
                + "contactoC='" + cnt.obtener_contacto_c()
                + "' where codigoC='" + cnt.obtener_codigo_c() + "'";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static Collection<Cliente> obtener_cliente_2() {
        Base_De_Datos baseDatos = new Base_De_Datos(null, null, null);
        ResultSet cliente = baseDatos.ejecutar_consulta(Cliente_DAO.ae_seleccionar_todo);
        java.util.Hashtable<String, Cliente> clientes = new java.util.Hashtable<String, Cliente>();

        try {
            while (cliente.next()) {
                Cliente cnt = new Cliente();
                cnt.poner_codigo_c(cliente.getString(1));
                cnt.poner_nombre_c(cliente.getString(2));
                cnt.poner_telefono_c(cliente.getString(3));
                cnt.poner_direccion_c(cliente.getString(4));
                cnt.poner_contacto_c(cliente.getString(5));
                clientes.put(cnt.obtener_codigo_c(), cnt);
            }
            cliente.close();
            baseDatos.ae_cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Vector(clientes.values());
    }

    public static List<Cliente> obtener_clientes() {
        List<Cliente> resultado;
        resultado = new ArrayList<Cliente>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Cliente_DAO.ae_seleccionar_todo);
                while (rs.next()) {
                    Cliente cnt = new Cliente();
                    cnt.poner_codigo_c(rs.getString(1));
                    cnt.poner_nombre_c(rs.getString(2));
                    cnt.poner_telefono_c(rs.getString(3));
                    cnt.poner_direccion_c(rs.getString(4));
                    cnt.poner_contacto_c(rs.getString(5));
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

    public static Cliente obtener_cliente(String codigo) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Cliente cnt = new Cliente();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Cliente_DAO.ae_seleccionar_todo + " where codigoC=  '" + codigo + "'");
                if (rs.next()) {
                    cnt = new Cliente();
                    cnt.poner_codigo_c(rs.getString(1));
                    cnt.poner_nombre_c(rs.getString(2));
                    cnt.poner_telefono_c(rs.getString(3));
                    cnt.poner_direccion_c(rs.getString(4));
                    cnt.poner_contacto_c(rs.getString(5));
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

    public static List<Cliente> obtener_clientes(String id) {
        List<Cliente> ListaClientes = new ArrayList();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = Conexion.obtener_registros(Cliente_DAO.ae_seleccionar_todo + " where identificacion != '" + id + "'");

            try {
                while (rs.next()) {
                    Cliente cnt = new Cliente();
                    cnt.poner_codigo_c(rs.getString(1));
                    cnt.poner_nombre_c(rs.getString(2));
                    cnt.poner_telefono_c(rs.getString(3));
                    cnt.poner_direccion_c(rs.getString(4));
                    cnt.poner_contacto_c(rs.getString(5));
                    ListaClientes.add(cnt);
                }
                rs.close();
                Conexion.ae_con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            ListaClientes = null;
        }
        return ListaClientes;
    }

    static public int eliminar(Cliente cnt) {
        String sql = "delete from Cliente"
                + " where codigoC= '" + cnt.obtener_codigo_c() + "'";
        return ae_bd.ejecutar_actualizacion(sql);
    }

    public static String ae_nombre_tabla = "Cliente";
    public static String ae_seleccionar_todo = "select * from " + Cliente_DAO.ae_nombre_tabla;
    public static Base_De_Datos ae_bd = new Base_De_Datos(null, null, null);
}//Fin de la clase Cliente_DAO
