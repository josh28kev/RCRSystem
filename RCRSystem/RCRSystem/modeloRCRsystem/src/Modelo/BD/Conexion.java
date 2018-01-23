package Modelo.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public static int conectar() {
        int i = 0;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            ae_con = DriverManager.getConnection(ae_url, ae_login, ae_contraseña);
            i = 1;
        } catch (Exception e) {
            System.out.println("ERROR DE CONEXION: " + e.getMessage());
        }
        return i;
    }

    public static Connection obtener_conexion(String servidorArg, String usuarioArg, String claveArg) {
        try {
            String servidor = (servidorArg == null ? ae_servidor : servidorArg);
            String usuario = (usuarioArg == null ? ae_usuario : usuarioArg);
            String clave = (claveArg == null ? ae_clave : claveArg);
            String URL_conexion = ae_protocolo + "//" + servidor + ":" + ae_puerto + "/" + ae_base_de_datos + "?user=" + usuario + "&password=" + clave;
            Class.forName(ae_manejador_db).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public static int guardar_registro(String sql) {
        Conexion.conectar();
        try {
            Statement st = Conexion.ae_con.createStatement();
            int eu = st.executeUpdate(sql);
            st.close();
            Conexion.ae_con.close();
            return eu;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static ResultSet obtener_registros(String sql) {
        Conexion.conectar();
        try {
            Statement st = Conexion.ae_con.createStatement();
            ResultSet eq = st.executeQuery(sql);
            return eq;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void ejecutar(String sql) {
        Conexion.conectar();
        try {
            Statement st = Conexion.ae_con.createStatement();
            st.executeQuery(sql);
        } catch (SQLException ex) {
            //System.err.println("Revisar");
        }
    }

    private static final String ae_manejador_db = "org.postgresql.Driver";
    private static final String ae_protocolo = "jdbc:postgresql:";
    private static final String ae_servidor = "localhost";
    private static final String ae_puerto = "5432";
    private static final String ae_usuario = "postgres";
    private static final String ae_clave = "root";
    private static final String ae_base_de_datos = "ReciclaCR";
    public static String ae_bd = "ReciclaCR";
    public static String ae_login = "postgres";
    public static String ae_contraseña = "root";
    public static String ae_url = "jdbc:postgresql://localhost:5432/" + Conexion.ae_bd;
    public static Connection ae_con;
    public Statement a_stmt;
    public ResultSet a_rs;
    public ResultSetMetaData a_rsMeta;
}//Fin de la clase Conexion
