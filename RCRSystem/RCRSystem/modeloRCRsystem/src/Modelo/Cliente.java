package Modelo;

public class Cliente {

    public Cliente(String a_codigo_c, String a_nombre_c, String a_telefono_c, String a_direccion_c, String a_contacto_c) {
        this.a_codigo_c = a_codigo_c;
        this.a_nombre_c = a_nombre_c;
        this.a_telefono_c = a_telefono_c;
        this.a_direccion_c = a_direccion_c;
        this.a_contacto_c = a_contacto_c;
    }

    public Cliente() {
        a_codigo_c = "";
        a_nombre_c = "";
        a_telefono_c = "";
        a_contacto_c = "";
        a_direccion_c = "";
    }

    public String obtener_codigo_c() {
        return a_codigo_c;
    }

    public void poner_codigo_c(String codigoC) {
        this.a_codigo_c = codigoC;
    }

    public String obtener_nombre_c() {
        return a_nombre_c;
    }

    public void poner_nombre_c(String nombreC) {
        this.a_nombre_c = nombreC;
    }

    public String obtener_telefono_c() {
        return a_telefono_c;
    }

    public void poner_telefono_c(String a_telefono_c) {
        this.a_telefono_c = a_telefono_c;
    }

    public String obtener_direccion_c() {
        return a_direccion_c;
    }

    public void poner_direccion_c(String a_direccion_c) {
        this.a_direccion_c = a_direccion_c;
    }

    public String obtener_contacto_c() {
        return a_contacto_c;
    }

    public void poner_contacto_c(String a_contacto_c) {
        this.a_contacto_c = a_contacto_c;
    }

    private String a_codigo_c;
    private String a_nombre_c;
    private String a_telefono_c;
    private String a_direccion_c;
    private String a_contacto_c;
}//Fin de la clase Cliente
