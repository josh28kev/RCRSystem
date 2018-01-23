package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Bulto implements Serializable {

    public Bulto() {
        this.a_codigo = "";
        this.a_tipo = -1;
        this.a_material = new Material_T();
        this.a_estado = -1;
    }

    public Bulto(String codigo, int tipo, BigDecimal peso, Material_T material, int estado) {
        this.a_codigo = codigo;
        this.a_tipo = tipo;
        this.a_peso = peso;
        this.a_material = material;
        this.a_estado = estado;
    }

    public String obtener_codigo() {
        return a_codigo;
    }

    public void poner_codigo(String codigo) {
        this.a_codigo = codigo;
    }

    public int obtener_tipo() {
        return a_tipo;
    }

    public void poner_tipo(int tipo) {
        this.a_tipo = tipo;
    }

    public BigDecimal obtener_peso() {
        return a_peso;
    }

    public void poner_peso(BigDecimal peso) {
        this.a_peso = peso;
    }

    public Material_T obtener_material() {
        return a_material;
    }

    public void poner_material(Material_T material) {
        this.a_material = material;
    }

    public int obtener_estado() {
        return a_estado;
    }

    public void poner_estado(int estado) {
        this.a_estado = estado;
    }

    public String toString() {
        if (a_tipo == 1) {
            return "Paca";
        }
        return "Saca";
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
        final Bulto other = (Bulto) obj;
        if (!Objects.equals(this.a_codigo, other.a_codigo)) {
            return false;
        }
        return true;
    }
    
    private String a_codigo;
    private int a_tipo;
    private BigDecimal a_peso;
    private Material_T a_material;
    private int a_estado;
}//Fin de la clase Bulto
