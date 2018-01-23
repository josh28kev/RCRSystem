package rcrsystem.presentation.model;

import Modelo.Reporte_Compra_Materiales;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReporteCompra_Materiales_TablaModelo extends AbstractTableModel {

    public ReporteCompra_Materiales_TablaModelo(int[] cols, List<Reporte_Compra_Materiales> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }

    public Reporte_Compra_Materiales obtener_fila_a(int row) {
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
        Reporte_Compra_Materiales admin = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_nombre:
                return admin.obtener_nombre();
            case ae_precio:
                return admin.obtener_precio();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_nombre] = "Material";
        a_nombres_columnas[ae_precio] = "Precio";
    }

    public static final int ae_nombre = 0;
    public static final int ae_precio = 1;

    private List<Reporte_Compra_Materiales> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[2];
}
