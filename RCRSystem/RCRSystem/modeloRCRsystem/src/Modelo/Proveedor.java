package Modelo;

import java.io.Serializable;
import java.util.Objects;

public class Proveedor implements Serializable {

    public Proveedor() {
        this.a_lugar = "";
        this.a_personaContacto = "";
    }

    public Proveedor(String a_codigo, String a_nombre, int a_telFijo, int a_telCelular, String a_lugar, String a_personaContacto) {
        this.a_codigo = a_codigo;
        this.a_nombre = a_nombre;
        this.a_telFijo = a_telFijo;
        this.a_telCelular = a_telCelular;
        this.a_lugar = a_lugar;
        this.a_personaContacto = a_personaContacto;
    }

    public String obtener_codigo() {
        return a_codigo;
    }

    public void poner_codigo(String codigo) {
        this.a_codigo = codigo;
    }

    public String obtener_nombre() {
        return a_nombre;
    }

    public void poner_nombre(String nombre) {
        this.a_nombre = nombre;
    }

    public String obtener_lugar() {
        return a_lugar;
    }

    public void poner_lugar(String lugar) {
        this.a_lugar = lugar;
    }

    public int obtener_telFijo() {
        return a_telFijo;
    }

    public void poner_telFijo(int a_telFijo) {
        this.a_telFijo = a_telFijo;
    }

    public int obtener_telCelular() {
        return a_telCelular;
    }

    public void poner_telCelular(int a_telCelular) {
        this.a_telCelular = a_telCelular;
    }

    public String obtener_personaContacto() {
        return a_personaContacto;
    }

    public void poner_personaContacto(String a_personaContacto) {
        this.a_personaContacto = a_personaContacto;
    }

    public String toString() {
        return a_nombre;
    }

    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.a_codigo);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proveedor other = (Proveedor) obj;
        if (!Objects.equals(this.a_codigo, other.a_codigo)) {
            return false;
        }
        return true;
    }

    private String a_codigo;
    private String a_nombre;
    private int a_telFijo;
    private int a_telCelular;
    private String a_lugar;
    private String a_personaContacto;
}//Fin de la clase Proveedor
