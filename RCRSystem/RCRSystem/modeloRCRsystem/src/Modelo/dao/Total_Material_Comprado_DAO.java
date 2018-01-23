package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Total_Material_Comprado;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Total_Material_Comprado_DAO {

    public static List<Total_Material_Comprado> obtener_i_total_materiales_comprados() {
        List<Total_Material_Comprado> resultado;
        resultado = new ArrayList<>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Total_Material_Comprado_DAO.ae_seleccionar_todo);
                while (rs.next()) {
                    Total_Material_Comprado cnt = new Total_Material_Comprado();
                    cnt.poner_material_comprado(Material_DAO.obtener_material(rs.getString(1)));
                    cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(rs.getString(2)));
                    cnt.poner_peso_total_c(new BigDecimal(rs.getString(3)));
                    cnt.poner_cantidad_bultos_c(rs.getInt(4));
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

    public int grabar(Total_Material_Comprado cnt) {
        String sql = "INSERT INTO TotalMaterialComprado (materialComprado,regComp,pesoTotalC,cantBultosC) Values ("
                + cnt.obtener_material_comprado().obtener_codigo() + ","
                + cnt.obtener_registro_compra().obtener_numero_compra() + ","
                + cnt.obtener_peso_total_c() + ","
                + cnt.obtener_cantidad_bultos_c()
                + ")";
        return Conexion.guardar_registro(sql);
    }

    public int actualizar(Total_Material_Comprado cnt) {
        String sql = "UPDATE TotalMaterialComprado SET pesoTotalC=" + cnt.obtener_peso_total_c() + ", cantBultosC= "
                + cnt.obtener_cantidad_bultos_c()
                + " where materialComprado='" + cnt.obtener_material_comprado().obtener_codigo()
                + "'and regComp='" + cnt.obtener_registro_compra().obtener_numero_compra() + "'";
        return Conexion.guardar_registro(sql);
    }

    public int borrar(Total_Material_Comprado cnt) {
        String sql = "DELETE  FROM TotalMaterialComprado where materialComprado=" + cnt.obtener_material_comprado().obtener_codigo()
                + "and regComp=" + cnt.obtener_registro_compra().obtener_numero_compra();
        return Conexion.guardar_registro(sql);
    }

    public static Total_Material_Comprado obtener_total_material_comprado(String codigoMaterial, String regC) {
        ResultSet rs = Conexion.obtener_registros(ae_seleccionar_todo + " where materialComprado = '" + codigoMaterial + "' "
                + "and regComp= '" + regC + "'");
        Total_Material_Comprado cnt = null;
        try {
            if (rs.next()) {
                cnt = new Total_Material_Comprado();
                cnt.poner_material_comprado(Material_DAO.obtener_material(rs.getString(1)));
                cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(rs.getString(2)));
                cnt.poner_peso_total_c(new BigDecimal(rs.getString(3)));
                cnt.poner_cantidad_bultos_c(rs.getInt(4));
            }
            rs.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static String procedimiento_ingresar_total_material_comprado(String tipoBultoComprado, int regCompra, BigDecimal peso) {
        String res = "";
        ResultSet rs = Conexion.obtener_registros("SELECT ingresarTotalMaterialComprado('" + tipoBultoComprado + "'," + regCompra + "," + peso + ")");
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

    public static String ae_nombre_tabla = "TotalMaterialComprado";
    public static String ae_seleccionar_todo = "select * from " + Total_Material_Comprado_DAO.ae_nombre_tabla;
}//Fin de la clase Total_Material_Comprado_DAO
