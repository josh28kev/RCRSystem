package Modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Reporte_Compra {

    public Reporte_Compra() {
    }

    public Reporte_Compra(Registro_Compra regCompra, Date fecha, Proveedor proveedor, String chofer, String placaVehiculo) {
        this.a_registro_compra = regCompra;
        this.a_fecha = fecha;
        this.a_proveedor = proveedor;
        this.a_chofer = chofer;
        this.a_placa_vehiculo = placaVehiculo;
    }

    public Registro_Compra obtener_registro_compra() {
        return a_registro_compra;
    }

    public void poner_registro_compra(Registro_Compra regCompra) {
        this.a_registro_compra = regCompra;
    }

    public Date obtener_fecha() {
        return a_fecha;
    }

    public void poner_fecha(Date fecha) {
        this.a_fecha = fecha;
    }

    public Proveedor obtener_proveedor() {
        return a_proveedor;
    }

    public void poner_proveedor(Proveedor proveedor) {
        this.a_proveedor = proveedor;
    }

    public String obtener_chofer() {
        return a_chofer;
    }

    public void poner_chofer(String chofer) {
        this.a_chofer = chofer;
    }

    public String obtener_placa_vehiculo() {
        return a_placa_vehiculo;
    }

    public void poner_placa_vehiculo(String placaVehiculo) {
        this.a_placa_vehiculo = placaVehiculo;
    }

    public String obtener_codigo_bulto() {
        return a_codigo_bulto;
    }

    public void poner_codigo_bulto(String codigoBulto) {
        this.a_codigo_bulto = codigoBulto;
    }

    public BigDecimal obtener_total() {
        return a_total;
    }

    public void poner_total(BigDecimal total) {
        this.a_total = total;
    }

    public int obtener_tipos_bultos() {
        return a_tipos_bultos;
    }

    public void poner_tipos_bultos(int a_tipos_bultos) {
        this.a_tipos_bultos = a_tipos_bultos;
    }

    private Registro_Compra a_registro_compra;
    private Date a_fecha;
    private Proveedor a_proveedor;
    private String a_chofer;
    private String a_placa_vehiculo;
    private String a_codigo_bulto;
    private BigDecimal a_total;
    private int a_tipos_bultos;
}//Fin de la clase Reporte_Compra
