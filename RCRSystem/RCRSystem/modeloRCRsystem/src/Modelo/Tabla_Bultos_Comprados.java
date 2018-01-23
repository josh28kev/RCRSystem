package Modelo;

import Modelo.dao.Bulto_DAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Tabla_Bultos_Comprados extends AbstractTableModel {

    public Tabla_Bultos_Comprados(List<Bulto> inventario) {
        a_cnl = new Bulto_DAO();
        this.a_filas = inventario;
    }

    public Tabla_Bultos_Comprados() {
        a_cnl = new Bulto_DAO();
        a_filas = a_cnl.obtener_bultos_comprados();
    }

    public Tabla_Bultos_Comprados(int[] cols, List<Bulto> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Bulto getRowAt(int row) {
        return a_filas.get(row);
    }

    public String getColumnName(int col) {
        return colNames[a_columnas[col]];
    }

    public int getRowCount() {
        return a_filas.size();
    }

    public int getColumnCount() {
        return a_columnas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Bulto bulto = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return bulto.obtener_codigo();
            case ae_tipo:
                return bulto.obtener_tipo();
            case ae_peso:
                return bulto.obtener_peso();
            case ae_material:
                return bulto.obtener_material().obtener_t_material().obtener_nombre();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        colNames[ae_codigo] = "Código Bulto";
        colNames[ae_tipo] = "Tipo";
        colNames[ae_peso] = "Peso (Kg)";
        colNames[ae_material] = "Material";
    }

    private List<Bulto> a_filas;
    private int[] a_columnas;
    private Bulto_DAO a_cnl;
    private String[] colNames = new String[5];
    public static final int ae_codigo = 0;
    public static final int ae_tipo = 1;
    public static final int ae_peso = 2;
    public static final int ae_material = 3;
}//Fin de la clase Tabla_Bultos_Comprados
