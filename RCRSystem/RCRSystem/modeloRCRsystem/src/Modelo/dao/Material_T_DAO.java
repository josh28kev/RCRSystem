package Modelo.dao;

import Modelo.Administrar_Material;
import Modelo.BD.Conexion;
import Modelo.BD.Base_De_Datos;
import Modelo.Material_T;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Material_T_DAO {

    public int grabar(Material_T cnt) {
        String sql = "INSERT INTO TipoMaterial (codigoTM,Tmaterial) Values ("
                + cnt.obtener_codigo() + ",'"
                + cnt.obtener_t_material()
                + ")";
        return Base_De_Datos.ejecutar_actualizacion(sql);
    }

    public int actualizar(Material_T cnt) {
        String sql = "UPDATE TipoMaterial SET Tmaterial='" + cnt.obtener_t_material()
                + " where codigoTM=" + cnt.obtener_codigo();
        return Base_De_Datos.ejecutar_actualizacion(sql);
    }

    public int borrar(Material_T cnt) {
        String sql = "DELETE  FROM TipoMaterial WHERE codigoTM=" + cnt.obtener_codigo();
        return Base_De_Datos.ejecutar_actualizacion(sql);
    }

    public List<Material_T> obtener_materiales_tipo() {
        ResultSet material = Base_De_Datos.ejecutar_consulta(Material_T_DAO.ae_seleccionar_todo);
        List<Material_T> ListaMaterialesTipo = new ArrayList();

        try {
            while (material.next()) {
                Material_T cnt = new Material_T();
                cnt.poner_codigo(material.getString(1));
                cnt.poner_t_material(Material_DAO.obtener_material(material.getString(2)));
                ListaMaterialesTipo.add(cnt);
            }
            material.close();
            Base_De_Datos.ae_cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaMaterialesTipo;
    }

    public static Material_T obtener_material_tipo(String codigoMaterial) {
        ResultSet rs = Conexion.obtener_registros(Material_T_DAO.ae_seleccionar_todo + " where codigoTM = '" + codigoMaterial + "'");
        Material_T cnt = null;
        try {
            if (rs.next()) {
                cnt = new Material_T();
                cnt.poner_codigo(rs.getString(1));
                cnt.poner_t_material(Material_DAO.obtener_material(rs.getString(2)));
            }
            rs.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }
    
   /*public static Administrar_Material obtener_precios(Administrar_Material material) {
        ResultSet rs = Conexion.obtener_registros("select precio from TipoMaterial" + " where tmaterial = '" + material.obtener_material().obtener_codigo() + "'");
      //  int cnt = null;
        try {
            if (rs.next()) {
                material.poner_precio_paca(rs.getFloat(1));
              if(rs.next()){
                material.poner_precio_saca(rs.getFloat(1));
              }
            }
            rs.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return material;
    }*/
   
   public static String ae_nombre_tabla = "TipoMaterial";
   public static String ae_seleccionar_todo = "select * from " + Material_T_DAO.ae_nombre_tabla;
}//Fin de la clase Material_T_DAO
