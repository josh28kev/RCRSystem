package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Facturar_Venta {

    public Facturar_Venta(Lista_Empaque le, List<Venta> venta) {
        this.a_le = le;
        this.a_venta = venta;
    }

    public Facturar_Venta() {
        this.a_le = new Lista_Empaque();
        this.a_venta = new ArrayList();
    }

    public Lista_Empaque obtener_lista_empaque() {
        return a_le;
    }

    public void poner_lista_empaque(Lista_Empaque le) {
        this.a_le = le;
    }

    public List<Venta> obtener_venta() {
        return a_venta;
    }

    public void poner_venta(List<Venta> venta) {
        this.a_venta = venta;
    }

    private Lista_Empaque a_le;
    private List<Venta> a_venta;
}
//Fin de la clase Facturar_Venta