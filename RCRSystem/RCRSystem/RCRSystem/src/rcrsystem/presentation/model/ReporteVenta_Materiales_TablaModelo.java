package rcrsystem.presentation.model;

import Modelo.Reporte_Venta_Materiales;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReporteVenta_Materiales_TablaModelo extends AbstractTableModel {

    public ReporteVenta_Materiales_TablaModelo(int[] cols, List<Reporte_Venta_Materiales> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }

    public Reporte_Venta_Materiales obtener_fila_a(int row) {
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
        Reporte_Venta_Materiales admin = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_bulto:
                return admin.obtener_bulto();
            case ae_material:
                return admin.obtener_material();
            case ae_peso:
                return admin.obtener_peso();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_bulto] = "Bulto";
        a_nombres_columnas[ae_material] = "Material";
        a_nombres_columnas[ae_peso] = "Peso Kg";
    }

    public static final int ae_bulto = 0;
    public static final int ae_material = 1;
    public static final int ae_peso = 2;

    private List<Reporte_Venta_Materiales> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[3];
}
