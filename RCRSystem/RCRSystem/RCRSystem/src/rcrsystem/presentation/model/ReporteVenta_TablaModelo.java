package rcrsystem.presentation.model;

import Modelo.Lista_Empaque;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReporteVenta_TablaModelo extends AbstractTableModel {

    public ReporteVenta_TablaModelo(int[] cols, List<Lista_Empaque> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }

    public Lista_Empaque obtener_fila_a(int row) {
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
        Lista_Empaque admin = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_venta:
                return admin.obtener_codigo_l();
            case ae_fecha:
                return admin.obtener_fecha();
            case ae_transporte:
                if(admin.obtener_medio_transporte() == 1)
                    return "Marítimo";
                else
                    return "Terrestre";
            case ae_cliente:
                return admin.obtener_cliente().obtener_nombre_c();
            case ae_destino:
                return admin.obtener_destino();
            case ae_conductor:
                return admin.obtener_conductor().obtener_nombre();
            case ae_bulto:
                return admin.obtener_total_bultos();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_venta] = "No. Venta";
        a_nombres_columnas[ae_fecha] = "Fecha";
        a_nombres_columnas[ae_transporte] = "Transporte";
        a_nombres_columnas[ae_cliente] = "Cliente";
        a_nombres_columnas[ae_destino] = "Destino";
        a_nombres_columnas[ae_conductor] = "Conductor";
        a_nombres_columnas[ae_bulto] = "Cantidad de Bultos";
    }

    public static final int ae_venta = 0;
    public static final int ae_fecha = 1;
    public static final int ae_transporte = 2;
    public static final int ae_cliente = 3;
    public static final int ae_destino = 4;
    public static final int ae_conductor = 5;
    public static final int ae_bulto = 6;
    private List<Lista_Empaque> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[7];
}
