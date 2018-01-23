package Modelo;

import java.math.BigDecimal;

public class Reporte_Compra_Materiales {

    public Reporte_Compra_Materiales() {
    }

    public String obtener_nombre() {
        return a_nombre;
    }

    public void poner_nombre(String nombre) {
        this.a_nombre = nombre;
    }

    public BigDecimal obtener_precio() {
        return a_precio;
    }

    public void poner_precio(BigDecimal precio) {
        this.a_precio = precio;
    }
    
    private String a_nombre;
    private BigDecimal a_precio;
}//Fin de la clase Reporte_Compra_Materiales
