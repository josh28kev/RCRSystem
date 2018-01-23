package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Modelo_Tabla_Bultos_Comprados extends java.util.Observable {

    public Modelo_Tabla_Bultos_Comprados() {
        this.inicializar();
    }

    public Tabla_Bultos_Comprados obtener_inventarios_modelo() {
        return a_bultos_compra_modelo;
    }

    public void poner_i_bultos_comprados_model(Tabla_Bultos_Comprados inventarioModel) {
        this.a_bultos_compra_modelo = inventarioModel;
        setChanged();
        notifyObservers(ae_bultos_comprados_modelo);
    }

    public void poner_bultos_comprados(List<Bulto> rows) {
        int[] cols = {Tabla_Inventario.ae_codigo, Tabla_Inventario.ae_material, Tabla_Inventario.ae_cantidad};
        poner_i_bultos_comprados_model(new Tabla_Bultos_Comprados(cols, rows));
    }

    void inicializar() {
        List<Bulto> rows = new ArrayList<Bulto>();
        poner_bultos_comprados(rows);
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_bultos_comprados_modelo);
    }
    
    private Tabla_Bultos_Comprados a_bultos_compra_modelo;
    public static Integer ae_bultos_comprados_modelo = 0;
}//Fin de la clase Modelo_Tabla_Bultos_Comprados
