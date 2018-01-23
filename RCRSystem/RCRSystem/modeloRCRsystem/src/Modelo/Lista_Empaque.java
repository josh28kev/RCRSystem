package Modelo;

import java.math.BigDecimal;

public class Lista_Empaque {

    public Lista_Empaque() {
        this.a_codigo_l = 0;
        this.a_fecha = "";
        this.a_medio_transporte = 0;
        this.a_cliente = new Cliente();
        this.a_destino = "";
        this.a_peso_bruto = new BigDecimal("0");
        this.a_peso_neto = new BigDecimal("0");
        this.a_conductor = new Conductor();
        this.a_placa = "";
        this.a_marca = "";
        this.a_chasis = "";
        this.a_furgon = "";
        this.a_marchamo = "";
        this.a_total_bultos = 0;
        this.a_codigoTransportista = "";
        this.a_transportista = "";
        this.a_numero_contenedor = "";
    }

    public Lista_Empaque(int codigoL, String fecha, int medioTransporte, Cliente cliente, String destino, BigDecimal pesoBruto, BigDecimal pesoNeto, Conductor conductor, String placa, String marca, String chasis, String furgon, String a_marchamo, String a_codigoTransportista, String a_transportista, int a_total_bultos, String a_numero_contenedorF) {
        this.a_codigo_l = codigoL;
        this.a_fecha = fecha;
        this.a_medio_transporte = medioTransporte;
        this.a_cliente = cliente;
        this.a_destino = destino;
        this.a_peso_bruto = pesoBruto;
        this.a_peso_neto = pesoNeto;
        this.a_conductor = conductor;
        this.a_placa = placa;
        this.a_marca = marca;
        this.a_chasis = chasis;
        this.a_furgon = furgon;
        this.a_marchamo = a_marchamo;
        this.a_total_bultos = a_total_bultos;
        this.a_codigoTransportista = a_codigoTransportista;
        this.a_transportista = a_transportista;
        this.a_numero_contenedor = a_numero_contenedor;
    }

    public int obtener_codigo_l() {
        return a_codigo_l;
    }

    public void poner_codigo_l(int codigoL) {
        this.a_codigo_l = codigoL;
    }

    public String obtener_fecha() {
        return a_fecha;
    }

    public void poner_fecha(String fecha) {
        this.a_fecha = fecha;
    }

    public int obtener_medio_transporte() {
        return a_medio_transporte;
    }

    public void poner_medio_transporte(int medioTransporte) {
        this.a_medio_transporte = medioTransporte;
    }

    public Cliente obtener_cliente() {
        return a_cliente;
    }

    public void poner_cliente(Cliente cliente) {
        this.a_cliente = cliente;
    }

    public String obtener_destino() {
        return a_destino;
    }

    public void poner_destino(String destino) {
        this.a_destino = destino;
    }

    public Conductor obtener_conductor() {
        return a_conductor;
    }

    public void poner_conductor(Conductor conductor) {
        this.a_conductor = conductor;
    }

    public String obtener_placa() {
        return a_placa;
    }

    public void poner_placa(String placa) {
        this.a_placa = placa;
    }

    public String obtener_marca() {
        return a_marca;
    }

    public void poner_marca(String marca) {
        this.a_marca = marca;
    }

    public String obtener_chasis() {
        return a_chasis;
    }

    public void poner_chasis(String chasis) {
        this.a_chasis = chasis;
    }

    public String obtener_furgon() {
        return a_furgon;
    }

    public void poner_furgon(String furgon) {
        this.a_furgon = furgon;
    }

    public BigDecimal obtener_peso_bruto() {
        return a_peso_bruto;
    }

    public void poner_peso_bruto(BigDecimal pesoBruto) {
        this.a_peso_bruto = pesoBruto;
    }

    public BigDecimal obtener_peso_neto() {
        return a_peso_neto;
    }

    public void poner_peso_neto(BigDecimal pesoNeto) {
        this.a_peso_neto = pesoNeto;
    }

    public String obtener_bulto() {
        return a_bulto;
    }

    public void poner_bulto(String bulto) {
        this.a_bulto = bulto;
    }

    public int obtener_total_bultos() {
        return a_total_bultos;
    }

    public void poner_total_bultos(int a_total_bultos) {
        this.a_total_bultos = a_total_bultos;
    }

    public String obtener_marchamo() {
        return a_marchamo;
    }

    public void poner_marchamo(String a_marchamo) {
        this.a_marchamo = a_marchamo;
    }

    public String obtener_transportista() {
        return a_transportista;
    }

    public void poner_transportista(String a_transportista) {
        this.a_transportista = a_transportista;
    }

    public String obtener_codigoTransportista() {
        return a_codigoTransportista;
    }

    public void poner_codigoTransportista(String a_codigoTransportista) {
        this.a_codigoTransportista = a_codigoTransportista;
    }

    public String obtener_numero_contenedor() {
        return a_numero_contenedor;
    }

    public void poner_numero_contenedor(String a_numero_contenedor) {
        this.a_numero_contenedor = a_numero_contenedor;
    }

    private int a_codigo_l;
    private String a_fecha;
    private int a_medio_transporte;
    private Cliente a_cliente;
    private String a_destino;
    private BigDecimal a_peso_bruto;
    private BigDecimal a_peso_neto;
    private Conductor a_conductor;
    private String a_placa;
    private String a_marca;
    private String a_chasis;
    private String a_furgon;
    private String a_bulto;
    private int a_total_bultos;
    private String a_marchamo;
    private String a_transportista;
    private String a_codigoTransportista;
    private String a_numero_contenedor;

}//Fin de la clase Lista_Empaque
