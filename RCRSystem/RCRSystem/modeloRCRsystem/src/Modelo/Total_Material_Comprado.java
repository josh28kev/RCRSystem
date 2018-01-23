package Modelo;

import java.math.BigDecimal;

public class Total_Material_Comprado {

    public Total_Material_Comprado() {

    }

    public Total_Material_Comprado(Material materialComprado, Registro_Compra regComp, BigDecimal pesoTotalC, int a_cantidad_bultos_c) {
        this.a_material_comprado = materialComprado;
        this.a_registro_compra = regComp;
        this.a_peso_total_c = pesoTotalC;
        this.a_cantidad_bultos_c = a_cantidad_bultos_c;
    }

    public Material obtener_material_comprado() {
        return a_material_comprado;
    }

    public void poner_material_comprado(Material materialComprado) {
        this.a_material_comprado = materialComprado;
    }

    public Registro_Compra obtener_registro_compra() {
        return a_registro_compra;
    }

    public void poner_registro_compra(Registro_Compra regComp) {
        this.a_registro_compra = regComp;
    }

    public BigDecimal obtener_peso_total_c() {
        return a_peso_total_c;
    }

    public void poner_peso_total_c(BigDecimal pesoTotalC) {
        this.a_peso_total_c = pesoTotalC;
    }

    public int obtener_cantidad_bultos_c() {
        return a_cantidad_bultos_c;
    }

    public void poner_cantidad_bultos_c(int a_cantidad_bultos_c) {
        this.a_cantidad_bultos_c = a_cantidad_bultos_c;
    }

    private Material a_material_comprado;
    private Registro_Compra a_registro_compra;
    private BigDecimal a_peso_total_c;
    private int a_cantidad_bultos_c;
}//Fin de la clase Total_Material_Comprado
