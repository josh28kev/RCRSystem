package Modelo;

import java.math.BigDecimal;

public class Reporte_Venta_Materiales {

    public Reporte_Venta_Materiales() {
    }

    public String obtener_bulto() {
        return a_bulto;
    }

    public void poner_bulto(String bulto) {
        this.a_bulto = bulto;
    }

    public String obtener_material() {
        return a_material;
    }

    public void poner_material(String material) {
        this.a_material = material;
    }

    public BigDecimal obtener_peso() {
        return a_peso;
    }

    public void poner_peso(BigDecimal peso) {
        this.a_peso = peso;
    }
    
    private String a_bulto;
    private String a_material;
    private BigDecimal a_peso;
}//Fin de la clase Reporte_Venta_Materiales
