package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Reporte_Compra;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reporte_Compra_DAO {

    public static int grabar(Reporte_Compra cnt) {
        String sql = "INSERT INTO ReporteCompra (regCompra,fecha,proveedor,chofer,placaVehiculo,tiposBultos) Values ("
                + cnt.obtener_registro_compra().obtener_numero_compra() + ","
                + "current_date,'"
                + cnt.obtener_proveedor().obtener_codigo() + "','"
                + cnt.obtener_chofer() + "','"
                + cnt.obtener_placa_vehiculo() + "',"
                + cnt.obtener_tipos_bultos()
                + ")";
        return Conexion.guardar_registro(sql);
    }

    public int actualizar(Reporte_Compra cnt) {
        String sql = "UPDATE ReporteCompra SET fecha='" + cnt.obtener_fecha()
                + "',proveedor='" + cnt.obtener_proveedor().obtener_codigo()
                + "',chofer='" + cnt.obtener_chofer()
                + "',placaVehiculo='" + cnt.obtener_placa_vehiculo()
                + "',tiposBultos=" + cnt.obtener_placa_vehiculo()
                + " where regCompra=" + cnt.obtener_registro_compra().obtener_numero_compra();
        return Conexion.guardar_registro(sql);
    }

    public int borrar(Reporte_Compra cnt) {
        String sql = "DELETE  FROM ReporteCompra WHERE numero=" + cnt.obtener_registro_compra().obtener_numero_compra();
        return Conexion.guardar_registro(sql);
    }

    public static List<Reporte_Compra> obtener_reportes_compras_fecha(String Fecha1, String Fecha2) {
        ResultSet reporteCompra
                = Conexion.obtener_registros("select ReporteCompra.regCompra,\n"
                        + "	ReporteCompra.fecha,\n"
                        + "	Proveedor.codigo,\n"
                        + "	ReporteCompra.chofer,\n"
                        + "	ReporteCompra.placaVehiculo\n"
                        + "	from ReporteCompra,Proveedor,RegistroCompra\n"
                        + "	where ReporteCompra.proveedor = Proveedor.codigo\n"
                        + "	and ReporteCompra.regCompra = RegistroCompra.numCompra\n"
                        + "	and ReporteCompra.fecha >= '" + Fecha1 + "'\n"
                        + "	and ReporteCompra.fecha <= '" + Fecha2 + "';");
        List<Reporte_Compra> ListaBultos = new ArrayList();

        try {
            while (reporteCompra.next()) {
                Reporte_Compra cnt = new Reporte_Compra();
                cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(reporteCompra.getString(1)));
                cnt.poner_fecha(reporteCompra.getDate(2));
                cnt.poner_proveedor(Proveedor_DAO.obtener_proveedor(reporteCompra.getString(3)));
                cnt.poner_chofer(reporteCompra.getString(4));
                cnt.poner_placa_vehiculo(reporteCompra.getString(5));
                ListaBultos.add(cnt);
            }

            reporteCompra.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static List<Reporte_Compra> obtener_reportes_compras_proveedor_nombre(String proveedor) {
        ResultSet reporteCompra
                = Conexion.obtener_registros("select ReporteCompra.regCompra,\n"
                        + "	ReporteCompra.fecha,\n"
                        + "	Proveedor.codigo,\n"
                        + "	ReporteCompra.chofer,\n"
                        + "	ReporteCompra.placaVehiculo\n"
                        + "	from ReporteCompra,Proveedor,RegistroCompra\n"
                        + "     where Proveedor.nombre Ilike '%" + proveedor + "%' \n"
                        + "	and ReporteCompra.proveedor = Proveedor.codigo\n"
                        + "	and ReporteCompra.regCompra = RegistroCompra.numCompra;");
        List<Reporte_Compra> ListaBultos = new ArrayList();

        try {
            while (reporteCompra.next()) {
                Reporte_Compra cnt = new Reporte_Compra();
                cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(reporteCompra.getString(1)));
                cnt.poner_fecha(reporteCompra.getDate(2));
                cnt.poner_proveedor(Proveedor_DAO.obtener_proveedor(reporteCompra.getString(3)));
                cnt.poner_chofer(reporteCompra.getString(4));
                cnt.poner_placa_vehiculo(reporteCompra.getString(5));
                ListaBultos.add(cnt);
            }

            reporteCompra.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static List<Reporte_Compra> obtener_reportes_compras_proveedor(String proveedor) {
        ResultSet reporteCompra
                = Conexion.obtener_registros("select ReporteCompra.regCompra,\n"
                        + "	ReporteCompra.fecha,\n"
                        + "	Proveedor.codigo,\n"
                        + "	ReporteCompra.chofer,\n"
                        + "	ReporteCompra.placaVehiculo\n"
                        + "	from ReporteCompra,Proveedor,RegistroCompra\n"
                        + "     where Proveedor.codigo like '%" + proveedor + "%' \n"
                        + "	and ReporteCompra.proveedor = Proveedor.codigo\n"
                        + "	and ReporteCompra.regCompra = RegistroCompra.numCompra;");
        List<Reporte_Compra> ListaBultos = new ArrayList();

        try {
            while (reporteCompra.next()) {
                Reporte_Compra cnt = new Reporte_Compra();
                cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(reporteCompra.getString(1)));
                cnt.poner_fecha(reporteCompra.getDate(2));
                cnt.poner_proveedor(Proveedor_DAO.obtener_proveedor(reporteCompra.getString(3)));
                cnt.poner_chofer(reporteCompra.getString(4));
                cnt.poner_placa_vehiculo(reporteCompra.getString(5));
                ListaBultos.add(cnt);
            }

            reporteCompra.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static List<Reporte_Compra> obtener_reportes_compras_numero_compra(String numCompra) {
        ResultSet reporteCompra
                = Conexion.obtener_registros("select ReporteCompra.regCompra,\n"
                        + "	ReporteCompra.fecha,\n"
                        + "	Proveedor.codigo,\n"
                        + "	ReporteCompra.chofer,\n"
                        + "	ReporteCompra.placaVehiculo\n"
                        + "	from ReporteCompra,Proveedor,RegistroCompra\n"
                        + "	where ReporteCompra.proveedor = Proveedor.codigo\n"
                        + "	and ReporteCompra.regCompra = RegistroCompra.numCompra\n"
                        + "	and ReporteCompra.regCompra = " + numCompra + ";");
        List<Reporte_Compra> ListaBultos = new ArrayList();

        try {
            while (reporteCompra.next()) {
                Reporte_Compra cnt = new Reporte_Compra();
                cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(reporteCompra.getString(1)));
                cnt.poner_fecha(reporteCompra.getDate(2));
                cnt.poner_proveedor(Proveedor_DAO.obtener_proveedor(reporteCompra.getString(3)));
                cnt.poner_chofer(reporteCompra.getString(4));
                cnt.poner_placa_vehiculo(reporteCompra.getString(5));
                ListaBultos.add(cnt);
            }

            reporteCompra.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static List<Reporte_Compra> obtener_reportes_compras_bulto(String codBulto) {
        ResultSet reporteCompra
                = Conexion.obtener_registros("select ReporteCompra.regCompra,\n"
                        + "	ReporteCompra.fecha,\n"
                        + "	Proveedor.codigo,\n"
                        + "	ReporteCompra.chofer,\n"
                        + "	ReporteCompra.placaVehiculo\n"
                        + "	from ReporteCompra,Proveedor,RegistroCompra,RegCompra_U_Bulto,Bulto\n"
                        + "	where ReporteCompra.proveedor = Proveedor.codigo\n"
                        + "	and Bulto.codigoBulto Ilike '" + codBulto + "'\n"
                        + "	and RegistroCompra.numCompra = RegCompra_U_Bulto.regcompra\n"
                        + "	and Bulto.codigoBulto = RegCompra_U_Bulto.bulto\n"
                        + "	and ReporteCompra.regCompra = RegistroCompra.numCompra;");
        List<Reporte_Compra> ListaBultos = new ArrayList();

        try {
            while (reporteCompra.next()) {
                Reporte_Compra cnt = new Reporte_Compra();
                cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(reporteCompra.getString(1)));
                cnt.poner_fecha(reporteCompra.getDate(2));
                cnt.poner_proveedor(Proveedor_DAO.obtener_proveedor(reporteCompra.getString(3)));
                cnt.poner_chofer(reporteCompra.getString(4));
                cnt.poner_placa_vehiculo(reporteCompra.getString(5));
                ListaBultos.add(cnt);
            }

            reporteCompra.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }

    public static void liberar_conexiones() {
        try {
            Conexion.ejecutar("	SELECT pg_terminate_backend(pid) \n"
                    + "	FROM pg_stat_activity \n"
                    + "	WHERE datname='ReciclaCR'\n"
                    + "	and application_name='';");
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Compra_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Reporte_Compra obtener_reporte_compra(String numero) {
        ResultSet reporteC = Conexion.obtener_registros(Reporte_Compra_DAO.ae_seleccionar_todo + " where numero = '" + numero + "'");
        Reporte_Compra cnt = null;
        try {
            if (reporteC.next()) {
                cnt = new Reporte_Compra();
                cnt.poner_registro_compra(Registro_Compra_DAO.obtener_registro_compra(reporteC.getString(1)));
                cnt.poner_fecha(reporteC.getDate(2));
                cnt.poner_proveedor(Proveedor_DAO.obtener_proveedor(reporteC.getString(3)));
                cnt.poner_chofer(reporteC.getString(4));
                cnt.poner_placa_vehiculo(reporteC.getString(5));
                cnt.poner_codigo_bulto(reporteC.getString(6));
                cnt.poner_total(new BigDecimal(reporteC.getString(7)));
                cnt.poner_tipos_bultos(reporteC.getInt(8));
            }
            reporteC.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static String ae_nombre_tabla = "ReporteCompra";
    public static String ae_seleccionar_todo = "select * from " + Reporte_Compra_DAO.ae_nombre_tabla;
}//Fin de la clase Reporte_Compra_DAO
