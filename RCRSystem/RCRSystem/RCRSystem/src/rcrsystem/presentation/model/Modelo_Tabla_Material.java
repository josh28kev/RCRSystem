package rcrsystem.presentation.model;

import Modelo.Administrar_Material;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Tabla_Material extends AbstractTableModel {
    
    public Modelo_Tabla_Material(int[] cols, List<Administrar_Material> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Administrar_Material obtener_fila_a(int row) {
        return a_filas.get(row);
    }
    
    @Override
    public int getRowCount() {
        return a_filas.size();
    }

    @Override
    public int getColumnCount() {
        return a_columnas.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return a_nombres_columnas[a_columnas[col]];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Administrar_Material admin = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return admin.obtener_material().obtener_codigo();
            case ae_descripcion:
                return admin.obtener_material().obtener_nombre();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_descripcion] = "Descripción";
    }
    
    public static final int ae_codigo = 0;
    public static final int ae_descripcion = 1;
    private List<Administrar_Material> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[2];
} // Fin de la clase Modelo_Tabla_Material