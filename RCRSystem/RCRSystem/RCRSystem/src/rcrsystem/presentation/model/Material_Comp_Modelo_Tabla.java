package rcrsystem.presentation.model;

import Modelo.Bulto;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Material_Comp_Modelo_Tabla extends AbstractTableModel {

    public Material_Comp_Modelo_Tabla(int[] cols, List<Bulto> rows) {
        this.a_columnas = cols;
        this.a_filas = rows;
        inicializar_nombres_columnas();
    }
    
    public Bulto obtener_fila_a(int row) {
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
        Bulto bultoc = a_filas.get(row);
        switch (a_columnas[col]) {
            case ae_bulto:
                return bultoc.obtener_codigo();
            case ae_material:
                return bultoc.obtener_material().obtener_t_material().obtener_nombre();
            case ae_tipo:
                if (bultoc.obtener_tipo() == 1) {
                    return "Paca";
                }
                return "Saca";
            case ae_peso:
                String numero = String.valueOf(bultoc.obtener_peso());
                BigDecimal b = new BigDecimal(numero);
                return b;
            default:
                return "";
        }
    }
    
    private void inicializar_nombres_columnas() {
        a_nombres_columnas[ae_bulto] = "Bulto";
        a_nombres_columnas[ae_material] = "Material";
        a_nombres_columnas[ae_tipo] = "Tipo";
        a_nombres_columnas[ae_peso] = "Peso Kg";
    }

    public static final int ae_bulto = 0;
    public static final int ae_material = 1;
    public static final int ae_tipo = 2;
    public static final int ae_peso = 3;
    private List<Bulto> a_filas;
    private int[] a_columnas;
    private String[] a_nombres_columnas = new String[5];
} // Fin de la clase Material_Comp_Modelo_Tabla