package rcrsystem.presentation.model;

import Modelo.Reporte_Compra;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReporteCompra_TablaModelo extends AbstractTableModel {

    public ReporteCompra_TablaModelo(int[] cols, List<Reporte_Compra> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }

    public Reporte_Compra obtener_fila_a(int row) {
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
        Reporte_Compra admin = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_compra:
                return admin.obtener_registro_compra().obtener_numero_compra();
            case ae_fecha:
                return admin.obtener_fecha();
            case ae_proveedor:
                return admin.obtener_proveedor();
            case ae_chofer:
                return admin.obtener_chofer();
            case ae_placa:
                return admin.obtener_placa_vehiculo();
            case ae_bultos:
                return admin.obtener_registro_compra().obtemer_bultos_total();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_compra] = "No. Reporte";
        a_nombres_columnas[ae_fecha] = "Fecha";
        a_nombres_columnas[ae_proveedor] = "Proveedor";
        a_nombres_columnas[ae_chofer] = "Chofer";
        a_nombres_columnas[ae_placa] = "Placa Vehiculo";
        a_nombres_columnas[ae_bultos] = "Total de Bultos";
    }

    public static final int ae_compra = 0;
    public static final int ae_fecha = 1;
    public static final int ae_proveedor = 2;
    public static final int ae_chofer = 3;
    public static final int ae_bultos = 4;
    public static final int ae_placa = 5;
    private List<Reporte_Compra> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[6];
}
