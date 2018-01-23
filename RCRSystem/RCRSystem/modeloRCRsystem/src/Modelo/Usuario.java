package Modelo;

public class Usuario {

    public Usuario() {
        this.a_identificacion = "";
        this.a_nombre = "";
        this.a_numero_telefono = 0;
        this.a_puesto = 0;
        this.a_contraseña = "";
    }

    public Usuario(String identificacion, String nombre, int numtelefon, int puesto, String contraseña) {
        this.a_identificacion = identificacion;
        this.a_nombre = nombre;
        this.a_numero_telefono = numtelefon;
        this.a_puesto = puesto;
        this.a_contraseña = contraseña;
    }

    public String obtener_identificacion() {
        return a_identificacion;
    }

    public void colocar_identificacion(String identificacion) {
        this.a_identificacion = identificacion;
    }

    public String obtener_nombre() {
        return a_nombre;
    }

    public void poner_nombre(String nombre) {
        this.a_nombre = nombre;
    }

    public int obtener_numero_telefono() {
        return a_numero_telefono;
    }

    public void poner_numero_telefono(int numtelefono) {
        this.a_numero_telefono = numtelefono;
    }

    public int obtener_puesto() {
        return a_puesto;
    }

    public void poner_puesto(int puesto) {
        this.a_puesto = puesto;
    }

    public String obtener_contraseña() {
        return a_contraseña;
    }

    public void poner_contraseña(String contraseña) {
        this.a_contraseña = contraseña;
    }

    private String a_identificacion;
    private String a_nombre;
    private int a_numero_telefono;
    private int a_puesto;
    private String a_contraseña;
}//Fin de la clase Usuario
