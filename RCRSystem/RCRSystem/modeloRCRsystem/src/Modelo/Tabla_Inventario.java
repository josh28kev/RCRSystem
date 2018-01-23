package Modelo;

import Modelo.dao.Inventario_DAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Tabla_Inventario extends AbstractTableModel {

    public Tabla_Inventario(List<Inventario> inventario) {
        a_cnl = new Inventario_DAO();
        this.a_filas = inventario;
    }

    public Tabla_Inventario() {
        a_cnl = new Inventario_DAO();
        a_filas = a_cnl.obtener_inventario();
    }

    public Tabla_Inventario(int[] cols, List<Inventario> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Inventario getRowAt(int row) {
        return a_filas.get(row);
    }

    public String getColumnName(int col) {
        return a_nombres_columnas[a_columnas[col]];
    }

    public int getRowCount() {
        return a_filas.size();
    }

    public int getColumnCount() {
        return a_columnas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Inventario inventario = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return inventario.obtener_material().obtener_codigo();
            case ae_material:
                return inventario.obtener_material().obtener_nombre();
            case ae_cantidad:
                return inventario.obtener_cantidad();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_material] = "Material";
        a_nombres_columnas[ae_cantidad] = "Cantidad (Kg)";
    }

    private List<Inventario> a_filas;
    private int[] a_columnas;
    private Inventario_DAO a_cnl;
    private String[] a_nombres_columnas = new String[3];
    public static final int ae_codigo = 0;
    public static final int ae_material = 1;
    public static final int ae_cantidad = 2;
}//Fin de la clase Tabla_Inventario
