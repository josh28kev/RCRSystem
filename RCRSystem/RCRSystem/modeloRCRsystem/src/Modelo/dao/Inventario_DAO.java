package Modelo.dao;

import Modelo.BD.Base_De_Datos;
import Modelo.BD.Conexion;
import Modelo.Bulto;
import Modelo.Inventario;
import Modelo.Material;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Inventario_DAO {

    public static List<Inventario> obtener_inventario() {
        List<Inventario> resultado;
        resultado = new ArrayList<Inventario>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros(Inventario_DAO.ae_seleccionar_todo);
                while (rs.next()) {
                    Inventario cnt = new Inventario();
                    cnt.poner_material(Material_DAO.obtener_material(rs.getString(1)));
                    cnt.poner_cantidad(new BigDecimal(rs.getString(2)));
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

    //solo de prueba
    public void imprimir(List<Inventario> i) {
        for (Inventario invent : i) {
            System.out.println(invent.obtener_material().obtener_nombre());
        }
    }

    public int actualizar(Inventario inv) {
        String sql = "UPDATE inventario SET cantidadT= " + inv.obtener_cantidad()
                + " where material='" + inv.obtener_material().obtener_codigo() + "'";
        return Conexion.guardar_registro(sql);
    }

    public static int actualizar(Bulto bulto) {
        String sql = "UPDATE Inventario SET cantidadT= cantidadT- " + bulto.obtener_peso()
                + " from Materiales,Bulto,TipoMaterial "
                + " where Inventario.material = TipoMaterial.Tmaterial"
                + " and TipoMaterial.codigoTM = '" + bulto.obtener_material().obtener_codigo() + "'";
        return Conexion.guardar_registro(sql);
    }

    public static List<Material> obtener_inventario_fecha() {
        List<Material> resultado;
        resultado = new ArrayList<Material>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros("select material from Inventario where cantidadT >= 20000");
                while (rs.next()) {
                    Material material = new Material();
                    material = (Material_DAO.obtener_material(rs.getString(1)));
                    resultado.add(material);
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

    public static Inventario obtener_inventario(String material) {
        Inventario inv = new Inventario();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros("select * from Inventario where material=" + "'" + material + "'");
                if (rs.next()) {
                    Inventario cnt = new Inventario();
                    cnt.poner_material(Material_DAO.obtener_material(rs.getString(1)));
                    cnt.poner_cantidad(new BigDecimal(rs.getString(2)));
                    inv = cnt;
                }
                rs.close();
                Conexion.ae_con.close();
                return inv;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            inv = null;
        }
        return inv;
    }

    public static List<Inventario> obtener_inventarioXBulto(String Bulto) {
        List<Inventario> resultado;
        ArrayList<String> anterior = new ArrayList<>();
        resultado = new ArrayList<Inventario>();
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros("select Inventario.material, Inventario.cantidadt from Inventario,Bulto where ('S' || material = materialbulto or 'P' || material = materialbulto) and codigobulto like '%" + Bulto + "%' and estado = 1");
                while (rs.next()) {
                    Inventario cnt = new Inventario();
                    cnt.poner_material(Material_DAO.obtener_material(rs.getString(1)));
                    cnt.poner_cantidad(new BigDecimal(rs.getString(2)));
                    if (!anterior.contains(rs.getString(1))) {
                        resultado.add(cnt);
                    }
                    anterior.add(rs.getString(1));
                }
                rs.close();
                Conexion.ae_con.close();
                return resultado;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            resultado = null;
        }
        return resultado;
    }

    public static int actualizar_peso(Bulto cnt) {
        String sql = "UPDATE Inventario SET  cantidadT= cantidadT-" + cnt.obtener_peso()
                + " where material='" + cnt.obtener_material().obtener_codigo() + "'";
        return Conexion.guardar_registro(sql);
    }

    public static int actualizar_peso2(Bulto cnt) {
        String sql = "UPDATE Inventario SET cantidadT = cantidadT -" + cnt.obtener_peso()
                + " from Materiales,Bulto,TipoMaterial\n"
                + "where Inventario.material = TipoMaterial.Tmaterial \n"
                + "and TipoMaterial.codigoTM = '" + cnt.obtener_material().obtener_codigo() + "'";
        return Conexion.guardar_registro(sql);
    }

    public static int grabar(Material cnt) {
        Base_De_Datos db = new Base_De_Datos(null, null, null);
        String sql = "INSERT INTO Inventario (material,cantidadT) Values ('"
                + cnt.obtener_codigo() + "', 0.0 ,0.0)";
        return db.ejecutar_actualizacion(sql);
    }

    public static String ae_nombre_tabla = "Inventario";
    public static String ae_seleccionar_todo = "select * from " + Inventario_DAO.ae_nombre_tabla;
}//Fin de la clase Inventario_DAO
