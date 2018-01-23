package Modelo;

import java.math.BigDecimal;

public class TotalMaterialVendido {

    public TotalMaterialVendido() {
        a_material_vendido=new Material();
        a_lista_empaque=new Lista_Empaque();
        a_cantidad_bultos_v=0;
        a_peso_total_v = new BigDecimal("0");
        a_precio_unid = new BigDecimal("0");
        a_importe = new BigDecimal("0");
    }

    public TotalMaterialVendido(Material materialVendido, Lista_Empaque listEmp, int cantBultosV, BigDecimal pesoTotalV, BigDecimal precioUnid, BigDecimal importe) {
        this.a_material_vendido = materialVendido;
        this.a_lista_empaque = listEmp;
        this.a_cantidad_bultos_v = cantBultosV;
        this.a_peso_total_v = pesoTotalV;
        this.a_precio_unid = precioUnid;
        this.a_importe = importe;
    }

    public Material obtener_material_vendido() {
        return a_material_vendido;
    }

    public void poner_material_vendido(Material materialVendido) {
        this.a_material_vendido = materialVendido;
    }

    public Lista_Empaque obtener_lista_empaque() {
        return a_lista_empaque;
    }

    public void poner_lista_empaque(Lista_Empaque listEmp) {
        this.a_lista_empaque = listEmp;
    }

    public int obtener_cantidad_bultos_v() {
        return a_cantidad_bultos_v;
    }

    public void poner_cantidad_bultos_v(int cantBultosV) {
        this.a_cantidad_bultos_v = cantBultosV;
    }

    public BigDecimal obtener_peso_total_v() {
        return a_peso_total_v;
    }

    public void poner_peso_total_v(BigDecimal pesoTotalV) {
        this.a_peso_total_v = pesoTotalV;
    }

    public BigDecimal obtener_precio_unid() {
        return a_precio_unid;
    }

    public void poner_precio_unid(BigDecimal precioUnid) {
        this.a_precio_unid = precioUnid;
    }

    public BigDecimal obtener_importe() {
        return a_importe;
    }

    public void poner_importe(BigDecimal importe) {
        this.a_importe = importe;
    }
    
    private Material a_material_vendido;
    private Lista_Empaque a_lista_empaque;
    private int a_cantidad_bultos_v;
    private BigDecimal a_peso_total_v;
    private BigDecimal a_precio_unid;
    private BigDecimal a_importe;

}//Fin de la clase Total_Material_Vendido
