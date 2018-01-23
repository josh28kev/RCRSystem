package Modelo;

import java.math.BigDecimal;

public class Registro_Compra {

    public Registro_Compra() {
    }

    public Registro_Compra(int numCompra, BigDecimal pesoTotal, int bultosTotal) {
        this.a_numero_compra = numCompra;
        this.a_peso_total = pesoTotal;
        this.a_bultos_total = bultosTotal;
    }

    public int obtener_numero_compra() {
        return a_numero_compra;
    }

    public void poner_numero_compra(int numCompra) {
        this.a_numero_compra = numCompra;
    }

    public BigDecimal obtener_peso_total() {
        return a_peso_total;
    }

    public void poner_peso_total(BigDecimal pesoTotal) {
        this.a_peso_total = pesoTotal;
    }

    public int obtemer_bultos_total() {
        return a_bultos_total;
    }

    public void poner_bultos_total(int a_bultos_total) {
        this.a_bultos_total = a_bultos_total;
    }

    private int a_numero_compra;
    private BigDecimal a_peso_total;
    private int a_bultos_total;
}//Fin de la clase Registro_Compra
