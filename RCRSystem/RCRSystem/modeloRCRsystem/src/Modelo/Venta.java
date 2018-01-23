package Modelo;

public class Venta {

    public Venta() {
        this.a_bulto = new Bulto();
        this.a_numero = -1;
    }
    
    public Venta(Bulto bulto, int numero) {
        this.a_bulto = bulto;
        this.a_numero = numero;
    }

    public Bulto obtener_bulto() {
        return a_bulto;
    }

    public void poner_bulto(Bulto bulto) {
        this.a_bulto = bulto;
    }

    public int obtener_numero() {
        return a_numero;
    }

    public void poner_numero(int numero) {
        this.a_numero = numero;
    }
    
    private Bulto a_bulto;
    private int a_numero;
}//Fin de la clase Venta
