package Modelo;

import java.util.Observable;
import java.util.Observer;

public class Modelo extends Observable {

    //<editor-fold desc="constructores" defaultstate="collapsed">
    public Modelo() {
        a_tabla_inventario = new Tabla_Inventario();
    }
    //</editor-fold>

    //<editor-fold desc="MVC" defaultstate="collapsed">
    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        setChanged();
        notifyObservers();
    }
    //</editor-fold>

    //<editor-fold desc="get y set" defaultstate="collapsed">
    //Llama a todos get y set de la calse TableApartado
    public Tabla_Inventario obtener_tabla_modelo_inventario() {
        return a_tabla_inventario;
    }

    //</editor-fold>
    
    private Tabla_Inventario a_tabla_inventario;
}//Fin de la clase Modelo
