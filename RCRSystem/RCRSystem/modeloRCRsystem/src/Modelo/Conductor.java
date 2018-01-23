package Modelo;

public class Conductor {

    public Conductor(String id, String nombre, String nacionalidad, String fechaNacimiento) {
        this.a_id = id;
        this.a_nombre = nombre;
        this.a_nacionalidad = nacionalidad;
        this.a_fecha_nacimiento = fechaNacimiento;
    }

    public Conductor() {
        this.a_id = "";
        this.a_nombre = "";
        this.a_nacionalidad = "";
        this.a_fecha_nacimiento = "";
    }

    public String obtener_id() {
        return a_id;
    }

    public void poner_id(String id) {
        this.a_id = id;
    }

    public String obtener_nombre() {
        return a_nombre;
    }

    public void poner_nombre(String nombre) {
        this.a_nombre = nombre;
    }

    public String obtener_nacionalidad() {
        return a_nacionalidad;
    }

    public void poner_nacionalidad(String nacionalidad) {
        this.a_nacionalidad = nacionalidad;
    }

    public String obtener_fecha_nacimiento() {
        return a_fecha_nacimiento;
    }

    public void poner_fecha_nacimiento(String fechaNacimiento) {
        this.a_fecha_nacimiento = fechaNacimiento;
    }

    private String a_id;
    private String a_nombre;
    private String a_nacionalidad;
    private String a_fecha_nacimiento;
}//Fin de la clase Conductor
