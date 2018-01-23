package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Modelo_Tabla_Inventario extends java.util.Observable {

    public Modelo_Tabla_Inventario() {
        this.inicializar();
    }

    public Tabla_Inventario obtener_inventarios_modelo() {
        return a_inventario_model;
    }

    public void poner_inventarios_modelo(Tabla_Inventario inventarioModel) {
        this.a_inventario_model = inventarioModel;
        setChanged();
        notifyObservers(ae_inventario_model);
    }

    public void poner_inventario(List<Inventario> rows) {
        int[] cols = {Tabla_Inventario.ae_codigo, Tabla_Inventario.ae_material, Tabla_Inventario.ae_cantidad};
        poner_inventarios_modelo(new Tabla_Inventario(cols, rows));
    }

    void inicializar() {
        List<Inventario> rows = new ArrayList<Inventario>();
        poner_inventario(rows);
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_inventario_model);
    }
    
    private Tabla_Inventario a_inventario_model;
    public static Integer ae_inventario_model = 0;
}//Fin de la clase Modelo_Tabla_Inventario