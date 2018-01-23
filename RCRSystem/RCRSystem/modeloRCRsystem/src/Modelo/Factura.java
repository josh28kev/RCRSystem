package Modelo;

import java.math.BigDecimal;

public class Factura {

    public Factura() {
        this.a_numero_f = "";
        this.a_lista_empaque = new Lista_Empaque();
        this.a_tipo_factura = -1;
        this.a_cliente = new Cliente();
        this.a_enviado_a = "";
        this.a_fecha = "";
        this.a_pais_origen = "";
        this.a_fecha_embarque = "";
        this.a_puerto_embarque = "";
        this.a_fecha_exportacion = "";
        this.a_metodo_transporte = -1;
        this.a_puerto_exportacion = "";
        this.a_puerto_destino = "";
        this.a_po = "";
        this.a_fecha_despacho = "";
        this.a_total_bultos = 0;
        this.a_peso_net = new BigDecimal("0");
        this.a_peso_brut = new BigDecimal("0");
        this.a_flete = new BigDecimal("0");
        this.a_subtotal = new BigDecimal("0");
        this.a_total_f = new BigDecimal("0");
    }

    public Factura(String numeroF, Lista_Empaque listEmpaque, int tipoFactura, Cliente cliente, String enviadoA, String fecha, String paisOrigen, String fechaEmbarque, String puertoEmbarque, String fechaExportacion, int metodoTransporte, String puertoExportacion, String puertoDestino, String PO, String fechaDespacho, int totalBultos, BigDecimal pesoNet, BigDecimal pesoBrut, BigDecimal flete, BigDecimal subTotal, BigDecimal totalF) {
        this.a_numero_f = numeroF;
        this.a_lista_empaque = listEmpaque;
        this.a_tipo_factura = tipoFactura;
        this.a_cliente = cliente;
        this.a_enviado_a = enviadoA;
        this.a_fecha = fecha;
        this.a_pais_origen = paisOrigen;
        this.a_fecha_embarque = fechaEmbarque;
        this.a_puerto_embarque = puertoEmbarque;
        this.a_fecha_exportacion = fechaExportacion;
        this.a_metodo_transporte = metodoTransporte;
        this.a_puerto_exportacion = puertoExportacion;
        this.a_puerto_destino = puertoDestino;
        this.a_po = PO;
        this.a_fecha_despacho = fechaDespacho;
        this.a_total_bultos = totalBultos;
        this.a_peso_net = pesoNet;
        this.a_peso_brut = pesoBrut;
        this.a_flete = flete;
        this.a_subtotal = subTotal;
        this.a_total_f = totalF;
    }

    public String obtener_numero_f() {
        return a_numero_f;
    }

    public void poner_numero_f(String numeroF) {
        this.a_numero_f = numeroF;
    }

    public Lista_Empaque obtener_lista_empaque() {
        return a_lista_empaque;
    }

    public void poner_lista_empaque(Lista_Empaque listEmpaque) {
        this.a_lista_empaque = listEmpaque;
    }

    public int obtener_tipo_factura() {
        return a_tipo_factura;
    }

    public void poner_tipo_factura(int tipoFactura) {
        this.a_tipo_factura = tipoFactura;
    }

    public Cliente obtener_cliente() {
        return a_cliente;
    }

    public void poner_cliente(Cliente cliente) {
        this.a_cliente = cliente;
    }

    public String obtener_fecha() {
        return a_fecha;
    }

    public void poner_fecha(String fecha) {
        this.a_fecha = fecha;
    }

    public String obtener_pais_origen() {
        return a_pais_origen;
    }

    public void poner_pais_origen(String paisOrigen) {
        this.a_pais_origen = paisOrigen;
    }

    public String obtener_fecha_embarque() {
        return a_fecha_embarque;
    }

    public void poner_fecha_embarque(String fechaEmbarque) {
        this.a_fecha_embarque = fechaEmbarque;
    }

    public String obtener_puerto_embarque() {
        return a_puerto_embarque;
    }

    public void poner_puerto_embarque(String puertoEmbarque) {
        this.a_puerto_embarque = puertoEmbarque;
    }

    public String obtener_fecha_exportacion() {
        return a_fecha_exportacion;
    }

    public void poner_fecha_exportacion(String fechaExportacion) {
        this.a_fecha_exportacion = fechaExportacion;
    }

    public int obtener_metodo_transporte() {
        return a_metodo_transporte;
    }

    public void poner_metodo_transporte(int metodoTransporte) {
        this.a_metodo_transporte = metodoTransporte;
    }

    public String obtener_puerto_destino() {
        return a_puerto_destino;
    }

    public void poner_puerto_destino(String puertoDestino) {
        this.a_puerto_destino = puertoDestino;
    }

    public String obtener_po() {
        return a_po;
    }

    public void poner_po(String PO) {
        this.a_po = PO;
    }

    public String obtener_fecha_despacho() {
        return a_fecha_despacho;
    }

    public void poner_fecha_despacho(String fechaDespacho) {
        this.a_fecha_despacho = fechaDespacho;
    }

    public int obtener_total_bultos() {
        return a_total_bultos;
    }

    public void poner_total_bultos(int totalBultos) {
        this.a_total_bultos = totalBultos;
    }

    public BigDecimal obtener_peso_neto() {
        return a_peso_net;
    }

    public void poner_peso_neto(BigDecimal pesoNet) {
        this.a_peso_net = pesoNet;
    }

    public BigDecimal obtener_peso_bruto() {
        return a_peso_brut;
    }

    public void poner_peso_bruto(BigDecimal pesoBrut) {
        this.a_peso_brut = pesoBrut;
    }

    public BigDecimal obtener_flete() {
        return a_flete;
    }

    public void poner_flete(BigDecimal flete) {
        this.a_flete = flete;
    }

    public BigDecimal obtener_subtotal() {
        return a_subtotal;
    }

    public void poner_subtotal(BigDecimal subTotal) {
        this.a_subtotal = subTotal;
    }

    public BigDecimal obtener_total_f() {
        return a_total_f;
    }

    public void poner_total_f(BigDecimal totalF) {
        this.a_total_f = totalF;
    }

    public String obtener_enviado_a() {
        return a_enviado_a;
    }

    public void poner_enviado_a(String enviadoA) {
        this.a_enviado_a = enviadoA;
    }

    public String obtener_icoterm() {
        return a_icoterm;
    }

    public void poner_icoterm(String icoterm) {
        this.a_icoterm = icoterm;
    }

    public String obtener_puerto_exportacion() {
        return a_puerto_exportacion;
    }

    public void poner_puerto_exportacion(String a_puerto_exportacion) {
        this.a_puerto_exportacion = a_puerto_exportacion;
    }

    private String a_numero_f;
    private Lista_Empaque a_lista_empaque;
    private int a_tipo_factura;
    private Cliente a_cliente;
    private String a_enviado_a;
    private String a_fecha;
    private String a_pais_origen;
    private String a_fecha_embarque;
    private String a_puerto_embarque;
    private String a_fecha_exportacion;
    private int a_metodo_transporte;
    private String a_puerto_exportacion;
    private String a_puerto_destino;
    private String a_po;
    private String a_icoterm;
    private String a_fecha_despacho;
    private int a_total_bultos;
    private BigDecimal a_peso_net;
    private BigDecimal a_peso_brut;
    private BigDecimal a_flete;
    private BigDecimal a_subtotal;
    private BigDecimal a_total_f;
}//Fin de la clase Factura
