package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Lista_Empaque;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lista_Empaque_DAO {

    public static int grabar(Lista_Empaque cnt) {
        String sql = "INSERT INTO ListaEmpaque (codigoListEm,fechaLE,medioTransporte,clienteLE,destino,pesoBruto,pesoNeto,conductor,placa,marca,chasis,furgon,totalBultos,numMarchamo,transportista,codigoTransportista, numeroContenedor) Values ("
                + cnt.obtener_codigo_l() + ","
                + "current_date,'"
                + cnt.obtener_medio_transporte() + "','"
                + cnt.obtener_cliente().obtener_codigo_c() + "','"
                + cnt.obtener_destino() + "',"
                + cnt.obtener_peso_bruto() + ","
                + cnt.obtener_peso_neto() + ",'"
                + cnt.obtener_conductor().obtener_id() + "','"
                + cnt.obtener_placa() + "','"
                + cnt.obtener_marca() + "','"
                + cnt.obtener_chasis() + "','"
                + cnt.obtener_furgon() + "',"
                + cnt.obtener_total_bultos() + ",'"
                + cnt.obtener_marchamo() + "','"
                + cnt.obtener_transportista() + "','"
                + cnt.obtener_codigoTransportista() + "','"
                + cnt.obtener_numero_contenedor()
                + "')";
        return Conexion.guardar_registro(sql);
    }

    public static List<Lista_Empaque> obtener_lista_empaque_fecha(String Fecha1, String Fecha2) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente\n"
                + "	where ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor\n"
                + "	and ListaEmpaque.fechaLE >= '" + Fecha1 + "'\n"
                + "	and ListaEmpaque.fechaLE <= '" + Fecha2 + "';");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_cliente(String cliente) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente\n"
                + "	where Cliente.codigoC like '%" + cliente + "%'\n"
                + "	and ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor;");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_cliente_nombre(String cliente) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente\n"
                + "	where Cliente.nombreC Ilike '%" + cliente + "%'\n"
                + "	and ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor;");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_contenedor(String contenedor) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente\n"
                + "	where \n"
                + "	ListaEmpaque.furgon Ilike '%" + contenedor + "%'\n"
                + "	and ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor;");

        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_numero_venta(String numero) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente\n"
                + "	where \n"
                + "	ListaEmpaque.codigoListEm = " + numero + " \n"
                + "	and ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor;");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_numero_factura(String numero) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente,Factura\n"
                + "     where ListaEmpaque.codigoListEm = Factura.listempaque \n"
                + "	and Factura.numerof Ilike '%" + numero + "%'\n"
                + "	and ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor;");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_transporte(int medio) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente\n"
                + "	where ListaEmpaque.medioTransporte = " + medio + "\n"
                + "	and ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor;");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_bulto(String bulto) {
        ResultSet listaEmpaque = Conexion.obtener_registros("select  ListaEmpaque.codigolistem,\n"
                + "	ListaEmpaque.fechaLE,\n"
                + "	ListaEmpaque.medioTransporte,\n"
                + "	Cliente.codigoC,\n"
                + "	ListaEmpaque.destino,\n"
                + "	Conductor.identificacionConductor,\n"
                + "	ListaEmpaque.totalbultos\n"
                + "	from ListaEmpaque,Conductor,Cliente,Bulto,ListEmpaque_U_Bulto\n"
                + "	where ListaEmpaque.clienteLE = Cliente.codigoC\n"
                + "	and ListaEmpaque.conductor = Conductor.identificacionConductor\n"
                + "	and ListaEmpaque.codigolistem = ListEmpaque_U_Bulto.listempaque\n"
                + "	and Bulto.codigoBulto Ilike '" + bulto + "'\n"
                + "	and Bulto.codigoBulto = ListEmpaque_U_Bulto.bultoVendido;");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(6)));
                cnt.poner_total_bultos(listaEmpaque.getInt(7));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque() {
        ResultSet listaEmpaque = Conexion.obtener_registros(Lista_Empaque_DAO.ae_seleccionar_todo);
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_peso_bruto(new BigDecimal(listaEmpaque.getString(6)));
                cnt.poner_peso_neto(new BigDecimal(listaEmpaque.getString(7)));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(8)));
                cnt.poner_placa(listaEmpaque.getString(9));
                cnt.poner_marca(listaEmpaque.getString(10));
                cnt.poner_chasis(listaEmpaque.getString(11));
                cnt.poner_furgon(listaEmpaque.getString(12));
                cnt.poner_total_bultos(listaEmpaque.getInt(13));
                cnt.poner_marchamo(listaEmpaque.getString(14));
                cnt.poner_transportista(listaEmpaque.getString(15));
                cnt.poner_codigoTransportista(listaEmpaque.getString(16));
                cnt.poner_numero_contenedor(listaEmpaque.getString(17));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static List<Lista_Empaque> obtener_lista_empaque_pen() {
        ResultSet listaEmpaque = Conexion.obtener_registros("select * from ListaEmpaque where estado = 0");
        List<Lista_Empaque> Lista = new ArrayList();

        try {
            while (listaEmpaque.next()) {
                Lista_Empaque cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_peso_bruto(new BigDecimal(listaEmpaque.getString(6)));
                cnt.poner_peso_neto(new BigDecimal(listaEmpaque.getString(7)));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(8)));
                cnt.poner_placa(listaEmpaque.getString(9));
                cnt.poner_marca(listaEmpaque.getString(10));
                cnt.poner_chasis(listaEmpaque.getString(11));
                cnt.poner_furgon(listaEmpaque.getString(12));
                cnt.poner_total_bultos(listaEmpaque.getInt(13));
                cnt.poner_marchamo(listaEmpaque.getString(14));
                cnt.poner_transportista(listaEmpaque.getString(15));
                cnt.poner_codigoTransportista(listaEmpaque.getString(16));
                cnt.poner_numero_contenedor(listaEmpaque.getString(17));
                Lista.add(cnt);
            }

            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lista;
    }

    public static Lista_Empaque obtener_lista_empaque(int co) {
        ResultSet listaEmpaque = Conexion.obtener_registros(Lista_Empaque_DAO.ae_seleccionar_todo + " where codigoListEm = " + co);
        Lista_Empaque cnt = null;
        try {
            if (listaEmpaque.next()) {
                cnt = new Lista_Empaque();
                cnt.poner_codigo_l(listaEmpaque.getInt(1));
                cnt.poner_fecha(listaEmpaque.getString(2));
                cnt.poner_medio_transporte(listaEmpaque.getInt(3));
                cnt.poner_cliente(Cliente_DAO.obtener_cliente(listaEmpaque.getString(4)));
                cnt.poner_destino(listaEmpaque.getString(5));
                cnt.poner_peso_bruto(new BigDecimal(listaEmpaque.getString(6)));
                cnt.poner_peso_neto(new BigDecimal(listaEmpaque.getString(7)));
                cnt.poner_conductor(Conductor_DAO.obtener_conductor(listaEmpaque.getString(8)));
                cnt.poner_placa(listaEmpaque.getString(9));
                cnt.poner_marca(listaEmpaque.getString(10));
                cnt.poner_chasis(listaEmpaque.getString(11));
                cnt.poner_furgon(listaEmpaque.getString(12));
                cnt.poner_total_bultos(listaEmpaque.getInt(13));
                cnt.poner_marchamo(listaEmpaque.getString(14));
                cnt.poner_transportista(listaEmpaque.getString(15));
                cnt.poner_codigoTransportista(listaEmpaque.getString(16));
                cnt.poner_numero_contenedor(listaEmpaque.getString(17));
            }
            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cnt;
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

    public static int obtener_maximo() {
        ResultSet listaEmpaque = Conexion.obtener_registros("select max(codigoListEm) from ListaEmpaque");
        int num = 0;
        try {
            if (listaEmpaque.next()) {
                num = listaEmpaque.getInt(1);
            }
            listaEmpaque.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return num;
    }

    public static int actualizar(Lista_Empaque cnt) {
        String sql = "UPDATE ListaEmpaque SET estado= 1 "
                + " where codigoListEm='" + cnt.obtener_codigo_l() + "'";
        return Conexion.guardar_registro(sql);
    }

    public static String obtener_secuencia_codListEmpaque() {
        String secCodListEmp = "0";
        if (Conexion.conectar() != 0) {//si no  hay conexion a la base
            ResultSet rs = null;
            try {
                rs = Conexion.obtener_registros("select nextval('sec_codListEmpaque');");
                if (rs.next()) {
                    secCodListEmp = rs.getString(1);
                }
                rs.close();
                Conexion.ae_con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            secCodListEmp = "-1";
        }
        return secCodListEmp;
    }

    public static String ae_nombre_tabla = "ListaEmpaque";
    public static String ae_seleccionar_todo = "select * from " + Lista_Empaque_DAO.ae_nombre_tabla;
}//Fin de la clase Lista_Empaque_DAO
