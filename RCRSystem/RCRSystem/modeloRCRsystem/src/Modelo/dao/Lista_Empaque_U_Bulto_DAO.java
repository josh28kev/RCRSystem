package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Bulto;
import Modelo.Lista_Empaque_U_Bulto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Lista_Empaque_U_Bulto_DAO {

    public static int grabar(Lista_Empaque_U_Bulto cnt) {
        String sql = "INSERT INTO ListEmpaque_U_Bulto (listEmpaque,bultoVendido) Values ("
                + cnt.obtener_lista_empaque().obtener_codigo_l() + ",'"
                + cnt.obtener_bulto_vendido().obtener_codigo()
                + "')";
        return Conexion.guardar_registro(sql);
    }

    public static List<Lista_Empaque_U_Bulto> obtener_bultos_por_lista(int codLis) {
        ResultSet res = Conexion.obtener_registros("Select * from ListEmpaque_U_Bulto where listEmpaque = " + codLis);
        List<Lista_Empaque_U_Bulto> list = new ArrayList();

        try {
            while (res.next()) {
                Lista_Empaque_U_Bulto cnt = new Lista_Empaque_U_Bulto();
                cnt.poner_bulto_vendido(Bulto_DAO.obtener_bulto(res.getString(2)));
                list.add(cnt);
            }
            res.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static String ae_nombre_tabla = "ListEmpaque_U_Bulto";
    public static String ae_seleccionar_todo = "select * from " + Lista_Empaque_U_Bulto_DAO.ae_nombre_tabla;
}//Fin de la clase Lista_Empaque_U_Bulto_DAO
