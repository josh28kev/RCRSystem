package rcrsystem.presentation.model;

import Modelo.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Cliente_Modelo_Tabla extends AbstractTableModel {

    public Cliente_Modelo_Tabla(int[] cols, List<Cliente> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }

    public Cliente obtener_fila_a(int row) {
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
        Cliente cliente = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_id:
                return cliente.obtener_codigo_c();
            case ae_nombre:
                return cliente.obtener_nombre_c();
            case ae_telefono:
                return cliente.obtener_telefono_c();
            case ae_direccion:
                return cliente.obtener_direccion_c();
            case ae_contacto:
                return cliente.obtener_contacto_c();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_id] = "Identificación";
        a_nombres_columnas[ae_nombre] = "Nombre";
        a_nombres_columnas[ae_telefono] = "Teléfono";
        a_nombres_columnas[ae_direccion] = "Dirección";
        a_nombres_columnas[ae_contacto] = "Contacto";
    }

    public static final int ae_id = 0;
    public static final int ae_nombre = 1;
    public static final int ae_telefono = 2;
    public static final int ae_direccion = 3;
    public static final int ae_contacto = 4;
    public List<Cliente> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[5];
} // Fin de la clase Cliente_Modelo_Tabla
