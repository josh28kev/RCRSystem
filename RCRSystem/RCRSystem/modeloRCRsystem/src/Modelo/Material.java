package Modelo;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Serializable{

    public Material() {
        this.a_codigo = "";
        this.a_nombre = "";
    }

    public Material(String codigo, String nombre, float precio) {
        this.a_codigo = codigo;
        this.a_nombre = nombre;
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
        final Material other = (Material) obj;
        if (!Objects.equals(this.a_codigo, other.a_codigo)) {
            return false;
        }
        return true;
    }
    
    private String a_codigo;
    private String a_nombre;
}//Fin de la clase Material
