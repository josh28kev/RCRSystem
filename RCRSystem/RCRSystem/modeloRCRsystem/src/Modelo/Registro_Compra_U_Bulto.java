package Modelo;

public class Registro_Compra_U_Bulto {

    public Registro_Compra_U_Bulto() {
    }

    public Registro_Compra_U_Bulto(Registro_Compra regCompra, Bulto bulto) {
        this.a_registro_compra = regCompra;
        this.a_bulto = bulto;
    }

    public Registro_Compra obtener_registro_compra() {
        return a_registro_compra;
    }

    public void poner_registro_compra(Registro_Compra regCompra) {
        this.a_registro_compra = regCompra;
    }

    public Bulto obtener_bulto() {
        return a_bulto;
    }

    public void poner_bulto(Bulto bulto) {
        this.a_bulto = bulto;
    }

    private Registro_Compra a_registro_compra;
    private Bulto a_bulto;
}//Fin de la clase Registro_Compra_U_Bulto
