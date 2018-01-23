package rcrsystem.presentation.model;

import Modelo.Venta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Bultos_Vent_Modelo_Tabla extends AbstractTableModel {

    public Bultos_Vent_Modelo_Tabla(int[] cols, List<Venta> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        initColNames();
    }
    
    public Venta obtener_fila_a(int row) {
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
        Venta v = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_numero:
                return v.obtener_numero();
            case ae_codigo:
                return v.obtener_bulto().obtener_codigo();
            case ae_tipo:
                if (v.obtener_bulto().obtener_tipo() == 1) {
                    return "Paca";
                }
                return "Saca";
            case ae_peso:
                return v.obtener_bulto().obtener_peso();
       
            case ae_nombre_material:
                return v.obtener_bulto().obtener_material().obtener_t_material().obtener_nombre();
            default:
                return "";
        }
    }
    
    private void initColNames() {
        a_nombres_columnas[ae_numero] = "Número";
        a_nombres_columnas[ae_codigo] = "Código";
        a_nombres_columnas[ae_tipo] = "Tipo";
        a_nombres_columnas[ae_nombre_material] = "Material";
        a_nombres_columnas[ae_peso] = "Peso Kg";
    }

    public static final int ae_numero = 0;
    public static final int ae_codigo = 1;
    public static final int ae_tipo = 2;
    public static final int ae_nombre_material = 3;
    public static final int ae_peso = 4;
    private List<Venta> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[6];
} // Fin de la clase Bultos_Vent_Modelo_Tabla