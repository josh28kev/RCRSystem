package Modelo.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Base_De_Datos {

    public Base_De_Datos(String servidorArg, String usuarioArg, String claveArg) {
        if (servidorArg != null) {
            ae_cnx = this.obtener_conexion(servidorArg, usuarioArg, claveArg);

        } else {
            ae_cnx = this.obtener_conexion(null, null, null);
        }
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

    public static int ejecutar_actualizacion(String statement) {
        try {
            Statement stm = ae_cnx.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException ex) {
            // Logger.getLogger(Base_De_Datos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static ResultSet ejecutar_consulta_2(String statement) {
        try {
            Statement stm = ae_cnx.createStatement();
            return stm.executeQuery(statement);
        } catch (SQLException ex) {
            Logger.getLogger(Base_De_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet ejecutar_consulta(String sql) {
        Base_De_Datos.obtener_conexion(null, null, null);
        try {
            Statement st = Base_De_Datos.ae_cnx.createStatement();
            ResultSet eq = st.executeQuery(sql);
            return eq;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Connection ae_cnx;
    private static final String ae_manejador_db = "org.postgresql.Driver";
    private static final String ae_protocolo = "jdbc:postgresql:";
    private static final String ae_servidor = "localhost";
    private static final String ae_puerto = "5432";
    private static final String ae_usuario = "postgres";
    private static final String ae_clave = "root";
    private static final String ae_base_de_datos = "ReciclaCR";
}//Fin de la clase Base_De_Datos
