package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.TotalMaterialVendido;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Total_Material_Vendido_DAO {

    public static List<TotalMaterialVendido> obtener_i_total_materiales_vendidos(int listE) {
        List<TotalMaterialVendido> resultado;
        resultado = new ArrayList<>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = rs = Conexion.obtener_registros(ae_seleccionar_todo + " where listEmpaque = " + listE);
                while (rs.next()) {
                    TotalMaterialVendido cnt = new TotalMaterialVendido();
                    cnt.poner_material_vendido(Material_DAO.obtener_material(rs.getString(1)));
                    cnt.poner_lista_empaque(Lista_Empaque_DAO.obtener_lista_empaque(rs.getInt(2)));
                    cnt.poner_peso_total_v(new BigDecimal(rs.getString(4)));
                    cnt.poner_cantidad_bultos_v(rs.getInt(3));
                    cnt.poner_precio_unid(new BigDecimal(rs.getString(5)));
                    cnt.poner_importe(new BigDecimal(rs.getString(6)));
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

    public static List<TotalMaterialVendido> obtener_i_total_materiales_vendidos() {

        List<TotalMaterialVendido> resultado;
        resultado = new ArrayList<>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Total_Material_Vendido_DAO.ae_seleccionar_todo);
                while (rs.next()) {
                    TotalMaterialVendido cnt = new TotalMaterialVendido();
                    cnt.poner_material_vendido(Material_DAO.obtener_material(rs.getString(1)));
                    cnt.poner_lista_empaque(Lista_Empaque_DAO.obtener_lista_empaque(rs.getInt(2)));
                    cnt.poner_peso_total_v(new BigDecimal(rs.getString(4)));
                    cnt.poner_cantidad_bultos_v(rs.getInt(3));
                    cnt.poner_precio_unid(new BigDecimal(rs.getString(5)));
                    cnt.poner_importe(new BigDecimal(rs.getString(6)));
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

    public int grabar(TotalMaterialVendido cnt) {
        String sql = "INSERT INTO TotalMaterialVendido (materialVendido,listEmpaque,cantBultosV,pesoTotalV,precioUnid,importe) Values ("
                + cnt.obtener_material_vendido().obtener_codigo() + ","
                + cnt.obtener_lista_empaque().obtener_codigo_l() + ","
                + cnt.obtener_cantidad_bultos_v() + ","
                + cnt.obtener_peso_total_v() + ","
                + cnt.obtener_precio_unid() + ","
                + cnt.obtener_importe()
                + ")";
        return Conexion.guardar_registro(sql);
    }

    public static TotalMaterialVendido obtener_total_material_vendido(String codigoMaterial, int listE) {
        ResultSet rs = Conexion.obtener_registros(ae_seleccionar_todo + " where materialVendido = '" + codigoMaterial + "' "
                + "and listEmpaque=" + listE);
        TotalMaterialVendido cnt = null;
        try {
            if (rs.next()) {
                cnt = new TotalMaterialVendido();
                cnt.poner_material_vendido(Material_DAO.obtener_material(rs.getString(1)));
                cnt.poner_lista_empaque(Lista_Empaque_DAO.obtener_lista_empaque(rs.getInt(2)));
                cnt.poner_peso_total_v(new BigDecimal(rs.getString(4)));
                cnt.poner_cantidad_bultos_v(rs.getInt(3));
                cnt.poner_precio_unid(new BigDecimal(rs.getString(5)));
                cnt.poner_importe(new BigDecimal(rs.getString(6)));
            }
            rs.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static String procedimiento_ingresar_total_material_vendido(String tipoBultoVendido, int listEmpaque, BigDecimal peso) {
        String res = "";
        ResultSet rs = Conexion.obtener_registros("SELECT ingresarTotalMaterialVendido('" + tipoBultoVendido + "'," + listEmpaque + "," + peso + ");");
        try {

            if (rs.next()) {
                res = rs.getString(1);
            }
            rs.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public int actualizar(TotalMaterialVendido cnt) {
        String sql = "UPDATE TotalMaterialVendido SET precioUnid=" + cnt.obtener_precio_unid()
                + ", importe =" + cnt.obtener_importe()
                + " where listEmpaque=" + cnt.obtener_lista_empaque().obtener_codigo_l()
                + "and materialVendido= '" + cnt.obtener_material_vendido().obtener_codigo() + "'";
        return Conexion.guardar_registro(sql);
    }

    public static String ae_nombre_tabla = "TotalMaterialVendido";
    public static String ae_seleccionar_todo = "select * from " + Total_Material_Vendido_DAO.ae_nombre_tabla;
}//Fin de la clase Total_Material_Vendido_DAO
