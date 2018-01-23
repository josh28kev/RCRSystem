package Modelo;

import java.math.BigDecimal;

public class Inventario {

    public Inventario(Material material, BigDecimal cantidad) {
        this.a_material = material;
        this.a_cantidad = cantidad;
    }

    public Inventario() {
        this.a_material = new Material();
        this.a_cantidad = new BigDecimal("0");
    }

    public Material obtener_material() {
        return a_material;
    }

    public void poner_material(Material material) {
        this.a_material = material;
    }

    public BigDecimal obtener_cantidad() {
        return a_cantidad;
    }

    public void poner_cantidad(BigDecimal cantidad) {
        this.a_cantidad = cantidad;
    }

    private Material a_material;
    private BigDecimal a_cantidad;
}//Fin de la clase Inventario