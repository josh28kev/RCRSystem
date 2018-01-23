package rcrsystem.presentation.model;

import Modelo.Proveedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Proveedor_Modelo_Tabla extends AbstractTableModel {

    public Proveedor_Modelo_Tabla(int[] cols, List<Proveedor> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }

    public Proveedor obtener_fila_a(int row) {
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
        Proveedor proveedor = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_id:
                return proveedor.obtener_codigo();
            case ae_nombre:
                return proveedor.obtener_nombre();
            case ae_telFijo:
                return proveedor.obtener_telFijo();
            case ae_telCelular:
                return proveedor.obtener_telCelular();
            case ae_lugar:
                return proveedor.obtener_lugar();
            case ae_contatcto:
                return proveedor.obtener_personaContacto();
            default:
                return "";
        }
    }

    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_id] = "Identificación";
        a_nombres_columnas[ae_nombre] = "Nombre";
        a_nombres_columnas[ae_telFijo] = "Teléfono Fijo";
        a_nombres_columnas[ae_telCelular] = "Teléfono Celular";
        a_nombres_columnas[ae_lugar] = "Lugar";
        a_nombres_columnas[ae_contatcto] = "Contacto";
    }

    public static final int ae_id = 0;
    public static final int ae_nombre = 1;
    public static final int ae_telFijo = 2;
    public static final int ae_telCelular = 3;
    public static final int ae_lugar = 4;
    public static final int ae_contatcto = 5;
    public List<Proveedor> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[6];
} // Fin de la clase Proveedor_Modelo_Tabla
