package rcrsystem.presentation.model;

import Modelo.Reporte_Venta_Materiales;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ReporteVentaMaterial_Modelo extends java.util.Observable {

    public void inicializar() {
        List<Reporte_Venta_Materiales> rows = new ArrayList<>();
        colocar_materiales(rows, 0);
        a_material_actual = new Reporte_Venta_Materiales();
        a_filtro = new Reporte_Venta_Materiales();
        inicializar_2();
    }

    public Reporte_Venta_Materiales obtener_filtro() {
        return a_filtro;
    }

    public void setFiltro(Reporte_Venta_Materiales filtro) {
        this.a_filtro = filtro;
    }

    public ReporteVenta_Materiales_TablaModelo obtener_modelo_material() {
        return this.a_modelo_material;
    }

    public void colocar_modelo_material(ReporteVenta_Materiales_TablaModelo materialModel) {
        this.a_modelo_material = materialModel;
        setChanged();
        notifyObservers(ae_modelo_material);
    }

    public void colocar_materiales(List<Reporte_Venta_Materiales> rows, int n) {
        int[] cols = {ReporteVenta_Materiales_TablaModelo.ae_bulto,
            ReporteVenta_Materiales_TablaModelo.ae_material,
            ReporteVenta_Materiales_TablaModelo.ae_peso};
        colocar_modelo_material(new ReporteVenta_Materiales_TablaModelo(cols, rows));
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_modelo_material);
        setChanged();
        notifyObservers(ae_material_actual);
    }

    void inicializar_2() {
        a_errores = new Hashtable<String, String>();
        a_mensaje = "";
    }

    public Hashtable<String, String> obtener_errores() {
        return a_errores;
    }

    public void colocar_errores(Hashtable<String, String> errores) {
        this.a_errores = errores;
    }

    public String obtener_mensaje() {
        return a_mensaje;
    }

    public void colocar_mensaje(String mensaje) {
        this.a_mensaje = mensaje;
    }

    public int obtener_modo() {
        return a_modo;
    }

    public void colocar_modo(int modo) {
        this.a_modo = modo;
    }

    public void limpiar_errores() {
        colocar_errores(new Hashtable<String, String>());
        colocar_mensaje("");
    }

    public Reporte_Venta_Materiales obtener_material_actual() {
        return a_material_actual;
    }

    public void colocar_material_actual(Reporte_Venta_Materiales materialCurrent) {
        this.a_material_actual = materialCurrent;
        setChanged();
        notifyObservers(ae_material_actual);
    }

    private ReporteVenta_Materiales_TablaModelo a_modelo_material;
    private Reporte_Venta_Materiales a_material_actual;
    private Reporte_Venta_Materiales a_filtro;
    public static Integer ae_modelo_material = 0;
    public static Integer ae_material_actual = 1;
    private Hashtable<String, String> a_errores;
    private String a_mensaje;
    private int a_modo;
}
