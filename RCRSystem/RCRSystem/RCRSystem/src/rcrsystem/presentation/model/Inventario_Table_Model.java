package rcrsystem.presentation.model;

import Modelo.Inventario;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Inventario_Table_Model extends AbstractTableModel {

    public Inventario_Table_Model(int[] cols, List<Inventario> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Inventario obtener_fila_a(int row) {
        return a_filas.get(row);
    }

    @Override
    public String getColumnName(int col) {
        return a_nombres_columnas[a_columnas[col]];
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
    public Object getValueAt(int row, int col) {
        Inventario inventario = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_codigo:
                return inventario.obtener_material().obtener_codigo();
            case ae_material:
                return inventario.obtener_material().obtener_nombre();
            case ae_peso:
                return inventario.obtener_cantidad();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_material] = "Material";
        a_nombres_columnas[ae_peso] = "Cantidad Kg";
    }

    public static final int ae_codigo = 0;
    public static final int ae_material = 1;
    public static final int ae_peso = 2;
    private List<Inventario> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[3];
} // Fin de la clase Inventario_Table_Model