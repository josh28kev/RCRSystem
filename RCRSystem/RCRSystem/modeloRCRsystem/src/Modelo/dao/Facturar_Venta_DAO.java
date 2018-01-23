package Modelo.dao;

import Modelo.Facturar_Venta;
import java.util.ArrayList;
import java.util.List;

public class Facturar_Venta_DAO {

    public Facturar_Venta_DAO(List<Facturar_Venta> listas) {
        this.ae_listas = listas;
    }

    public Facturar_Venta_DAO() {
        this.ae_listas = new ArrayList();
    }

    public List<Facturar_Venta> obtener_listas() {
        return ae_listas;
    }

    public void poner_listas(List<Facturar_Venta> listas) {
        this.ae_listas = listas;
    }

    public static void agregar_factura_venta(Facturar_Venta fv) throws Exception {
        ae_listas.add(fv);
    }
    
    private static List<Facturar_Venta> ae_listas;
}//Fin de la clase Facturar_Venta_DAO
