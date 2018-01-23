package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.BD.Base_De_Datos;
import Modelo.Registro_Compra;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Registro_Compra_DAO {

    public static int grabar(Registro_Compra cnt) {
        String sql = "INSERT INTO RegistroCompra (numCompra,pesoTotal,bultosTotal) Values ("
                + cnt.obtener_numero_compra() + ","
                + cnt.obtener_peso_total() + ","
                + cnt.obtemer_bultos_total()
                + ")";
        return Conexion.guardar_registro(sql);
    }

    public int actualizar(Registro_Compra cnt) {
        String sql = "UPDATE RegistroCompra SET pesoTotal=" + cnt.obtener_peso_total()
                + "and  bultosTotal= " + cnt.obtemer_bultos_total()
                + " where numCompra=" + cnt.obtener_numero_compra();
        return Base_De_Datos.ejecutar_actualizacion(sql);
    }

    public static List<Registro_Compra> obtener_registros_compras() {
        ResultSet usuario = Base_De_Datos.ejecutar_consulta(Registro_Compra_DAO.ae_seleccionar_todo);
        List<Registro_Compra> ListaRegistros = new ArrayList();

        try {
            while (usuario.next()) {
                Registro_Compra cnt = new Registro_Compra();
                cnt.poner_numero_compra(usuario.getInt(1));
                cnt.poner_peso_total(new BigDecimal(usuario.getString(2)));
                cnt.poner_bultos_total(usuario.getInt(3));
                ListaRegistros.add(cnt);
            }
            usuario.close();
            Base_De_Datos.ae_cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaRegistros;
    }

    public static int obtener_maximo() {
        ResultSet registroCompra = Conexion.obtener_registros("select max(numCompra) from RegistroCompra");
        int num = 0;
        try {
            if (registroCompra.next()) {
                num = registroCompra.getInt(1);
            }
            registroCompra.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return num;
    }

    public static String obtener_secuencia_regCompra() {
        String secRgC = "0";
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros("select nextval('sec_numeroRegCompra');");
                if (rs.next()) {
                    secRgC = rs.getString(1);
                }
                rs.close();
                Conexion.ae_con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            secRgC = "-1";
        }
        return secRgC;
    }

    public static Registro_Compra obtener_registro_compra(String numCompra) {
        //Connection connection = null;
        //Conexion miConexion = new Conexion();
        // connection = Conexion.getConnection();
        Registro_Compra cnt = new Registro_Compra();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Registro_Compra_DAO.ae_seleccionar_todo + " where numCompra=  '" + numCompra + "'");
                if (rs.next()) {
                    cnt = new Registro_Compra();
                    cnt.poner_numero_compra(rs.getInt(1));
                    cnt.poner_peso_total(new BigDecimal(rs.getString(2)));
                    cnt.poner_bultos_total(rs.getInt(3));
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

    public static String ae_nombre_tabla = "RegistroCompra";
    public static String ae_seleccionar_todo = "select * from " + Registro_Compra_DAO.ae_nombre_tabla;
}//Fin de la clase Registro_Compra_DAO
