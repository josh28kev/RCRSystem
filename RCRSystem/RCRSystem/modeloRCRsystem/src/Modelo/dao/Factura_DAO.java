package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Factura;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Factura_DAO {

    public static int grabar(Factura cnt) {
        String sql = "INSERT INTO Factura (numeroF,listEmpaque,tipoFactura,cliente,enviadoA,fecha,paisOrigen,fechaEmbarque,puertoEmbarque,fechaExportacion,metodoTransporte,puertoExportacion,puertoDestino"
                + ",PO,Icoterm,fechaDespacho,totalBultos,pesoNet,pesoBrut,flete,subTotal,totalF) Values ('"
                + cnt.obtener_numero_f() + "',"
                + cnt.obtener_lista_empaque().obtener_codigo_l() + ","
                + cnt.obtener_tipo_factura() + ",'"
                + cnt.obtener_cliente().obtener_codigo_c() + "','"
                + cnt.obtener_enviado_a() + "','"
                + cnt.obtener_fecha() + "','"
                + cnt.obtener_pais_origen() + "','"
                + cnt.obtener_fecha_embarque() + "','"
                + cnt.obtener_puerto_embarque() + "','"
                + cnt.obtener_fecha_exportacion() + "',"
                + cnt.obtener_metodo_transporte() + ",'"
                + cnt.obtener_puerto_exportacion() + "','"
                + cnt.obtener_puerto_destino() + "','"
                + cnt.obtener_po() + "','"
                + cnt.obtener_icoterm() + "','"
                + cnt.obtener_fecha_despacho() + "',"
                + cnt.obtener_total_bultos() + ","
                + cnt.obtener_peso_neto() + ","
                + cnt.obtener_peso_bruto() + ","
                + cnt.obtener_flete() + ","
                + cnt.obtener_subtotal() + ","
                + cnt.obtener_total_f()
                + ")";
        return Conexion.guardar_registro(sql);
    }

    public static List<Factura> obtener_facturas() {
        ResultSet factura = Conexion.obtener_registros(Factura_DAO.ae_seleccionar_todo);
        List<Factura> ListaFacturas = new ArrayList();

        try {
            while (factura.next()) {
                Factura cnt = new Factura();
                cnt.poner_numero_f(factura.getString(1));
                cnt.poner_lista_empaque(Lista_Empaque_DAO.obtener_lista_empaque(factura.getInt(2)));
                cnt.poner_tipo_factura(factura.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(factura.getString(4)));
                cnt.poner_enviado_a(factura.getString(5));
                cnt.poner_fecha(factura.getString(6));
                cnt.poner_pais_origen(factura.getString(7));
                cnt.poner_fecha_embarque(factura.getString(8));
                cnt.poner_puerto_embarque(factura.getString(9));
                cnt.poner_fecha_exportacion(factura.getString(10));
                cnt.poner_metodo_transporte(factura.getInt(11));
                cnt.poner_puerto_exportacion(factura.getString(12));
                cnt.poner_puerto_destino(factura.getString(13));
                cnt.poner_po(factura.getString(14));
                cnt.poner_icoterm(factura.getString(15));
                cnt.poner_fecha_despacho(factura.getString(16));
                cnt.poner_total_bultos(factura.getInt(17));
                cnt.poner_peso_neto(new BigDecimal(factura.getString(18)));
                cnt.poner_peso_bruto(new BigDecimal(factura.getString(19)));
                cnt.poner_subtotal(new BigDecimal(factura.getString(20)));
                cnt.poner_total_f(new BigDecimal(factura.getString(21)));
                ListaFacturas.add(cnt);
            }
            factura.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaFacturas;
    }

    public static Factura obtener_factura(String numFactura) {
        ResultSet factura = Conexion.obtener_registros(Factura_DAO.ae_seleccionar_todo + " where numeroF = '" + numFactura + "'");
        Factura cnt = null;
        try {
            if (factura.next()) {
                cnt = new Factura();
                cnt.poner_numero_f(factura.getString(1));
                cnt.poner_lista_empaque(Lista_Empaque_DAO.obtener_lista_empaque(factura.getInt(2)));
                cnt.poner_tipo_factura(factura.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(factura.getString(4)));
                cnt.poner_enviado_a(factura.getString(5));
                cnt.poner_fecha(factura.getString(6));
                cnt.poner_pais_origen(factura.getString(7));
                cnt.poner_fecha_embarque(factura.getString(8));
                cnt.poner_puerto_embarque(factura.getString(9));
                cnt.poner_fecha_exportacion(factura.getString(10));
                cnt.poner_metodo_transporte(factura.getInt(11));
                cnt.poner_puerto_exportacion(factura.getString(12));
                cnt.poner_puerto_destino(factura.getString(13));
                cnt.poner_po(factura.getString(14));
                cnt.poner_icoterm(factura.getString(15));
                cnt.poner_fecha_despacho(factura.getString(16));
                cnt.poner_total_bultos(factura.getInt(17));
                cnt.poner_peso_neto(new BigDecimal(factura.getString(18)));
                cnt.poner_peso_bruto(new BigDecimal(factura.getString(19)));
                cnt.poner_subtotal(new BigDecimal(factura.getString(20)));
                cnt.poner_total_f(new BigDecimal(factura.getString(21)));
            }
            factura.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static Factura obtener_facturaXLE(int numListaE) {
        ResultSet factura = Conexion.obtener_registros("select * from Factura where listempaque = " + numListaE);
        Factura cnt = null;
        try {
            if (factura.next()) {
                cnt = new Factura();
                cnt.poner_numero_f(factura.getString(1));
                cnt.poner_lista_empaque(Lista_Empaque_DAO.obtener_lista_empaque(factura.getInt(2)));
                cnt.poner_tipo_factura(factura.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(factura.getString(4)));
                cnt.poner_enviado_a(factura.getString(5));
                cnt.poner_fecha(factura.getString(6));
                cnt.poner_pais_origen(factura.getString(7));
                cnt.poner_fecha_embarque(factura.getString(8));
                cnt.poner_puerto_embarque(factura.getString(9));
                cnt.poner_fecha_exportacion(factura.getString(10));
                cnt.poner_metodo_transporte(factura.getInt(11));
                cnt.poner_puerto_exportacion(factura.getString(12));
                cnt.poner_puerto_destino(factura.getString(13));
                cnt.poner_po(factura.getString(14));
                cnt.poner_icoterm(factura.getString(15));
                cnt.poner_fecha_despacho(factura.getString(16));
                cnt.poner_total_bultos(factura.getInt(17));
                cnt.poner_peso_neto(new BigDecimal(factura.getString(18)));
                cnt.poner_peso_bruto(new BigDecimal(factura.getString(19)));
                cnt.poner_subtotal(new BigDecimal(factura.getString(20)));
                cnt.poner_total_f(new BigDecimal(factura.getString(21)));
            }
            factura.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
    }

    public static String ae_nombre_tabla = "Bulto";
    public static String ae_seleccionar_todo = "select * from " + Factura_DAO.ae_nombre_tabla;
}//Fin de la clase Factura_DAO
