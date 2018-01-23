package rcrsystem.presentation.model;

import Modelo.TotalMaterialVendido;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Total_Material_Lista_Empaque_Modelo_Tabla extends AbstractTableModel {

    public Total_Material_Lista_Empaque_Modelo_Tabla(int[] cols, List<TotalMaterialVendido> rows, int moneda) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas(moneda);
    }

    public TotalMaterialVendido obtener_fila_a(int row) {
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
        TotalMaterialVendido totalMV = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_material:
                return totalMV.obtener_material_vendido().obtener_codigo();
            case ae_cantidad:
                return totalMV.obtener_peso_total_v();
            case ae_descripcion:
                return totalMV.obtener_material_vendido().obtener_nombre();
            case ae_cantidad_bultos:
                return totalMV.obtener_cantidad_bultos_v();
            case ae_precio_unitario:
                return totalMV.obtener_precio_unid();
            case ae_importe:
                return totalMV.obtener_importe();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas(int moneda) {
        a_nombres_columnas[ae_material] = "Material";
        a_nombres_columnas[ae_cantidad] = "Cantidad despachada (Kg)";
        a_nombres_columnas[ae_descripcion] = "Descripción";
        a_nombres_columnas[ae_cantidad_bultos] = "Cantidad bultos";
        if (moneda == 1) {
            a_nombres_columnas[ae_precio_unitario] = "Precio uni. (CRC)";
        } else {
            a_nombres_columnas[ae_precio_unitario] = "Precio uni. (USD)";
        }
        a_nombres_columnas[ae_importe] = "Importe";
    }

    public static final int ae_material = 0;
    public static final int ae_cantidad = 1;
    public static final int ae_descripcion = 2;
    public static final int ae_cantidad_bultos = 3;
    public static final int ae_precio_unitario = 4;
    public static final int ae_importe = 5;
    private List<TotalMaterialVendido> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[7];
}
