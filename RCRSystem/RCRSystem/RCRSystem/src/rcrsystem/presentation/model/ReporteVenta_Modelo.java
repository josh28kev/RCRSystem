package rcrsystem.presentation.model;

import Modelo.Lista_Empaque;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ReporteVenta_Modelo extends java.util.Observable {

    public void inicializar() {
        List<Lista_Empaque> rows = new ArrayList<>();
        colocar_materiales(rows, 0);
        a_material_actual = new Lista_Empaque();
        a_filtro = new Lista_Empaque();
        inicializar_2();
    }

    public Lista_Empaque obtener_filtro() {
        return a_filtro;
    }

    public void setFiltro(Lista_Empaque filtro) {
        this.a_filtro = filtro;
    }

    public ReporteVenta_TablaModelo obtener_modelo_material() {
        return this.a_modelo_material;
    }

    public void colocar_modelo_material(ReporteVenta_TablaModelo materialModel) {
        this.a_modelo_material = materialModel;
        setChanged();
        notifyObservers(ae_modelo_material);
    }

    public void colocar_materiales(List<Lista_Empaque> rows, int n) {
        int[] cols = {ReporteVenta_TablaModelo.ae_venta,
            ReporteVenta_TablaModelo.ae_fecha,
            ReporteVenta_TablaModelo.ae_transporte,
            ReporteVenta_TablaModelo.ae_cliente,
            ReporteVenta_TablaModelo.ae_destino,
            ReporteVenta_TablaModelo.ae_conductor,
            ReporteVenta_TablaModelo.ae_bulto};
        colocar_modelo_material(new ReporteVenta_TablaModelo(cols, rows));
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

    public Lista_Empaque obtener_material_actual() {
        return a_material_actual;
    }

    public void colocar_material_actual(Lista_Empaque materialCurrent) {
        this.a_material_actual = materialCurrent;
        setChanged();
        notifyObservers(ae_material_actual);
    }

    private ReporteVenta_TablaModelo a_modelo_material;
    private Lista_Empaque a_material_actual;
    private Lista_Empaque a_filtro;
    public static Integer ae_modelo_material = 0;
    public static Integer ae_material_actual = 1;
    private Hashtable<String, String> a_errores;
    private String a_mensaje;
    private int a_modo;
}
