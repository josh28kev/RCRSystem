package Modelo;

import Modelo.Lista_Empaque;

public class Lista_Empaque_U_Bulto {

    public Lista_Empaque_U_Bulto() {
    }

    public Lista_Empaque_U_Bulto(Lista_Empaque listE, Bulto bultoVendido) {
        this.a_list_empaque = listE;
        this.a_bulto_vendido = bultoVendido;
    }

    public Lista_Empaque obtener_lista_empaque() {
        return a_list_empaque;
    }

    public void poner_lista_empaque(Lista_Empaque listE) {
        this.a_list_empaque = listE;
    }

    public Bulto obtener_bulto_vendido() {
        return a_bulto_vendido;
    }

    public void poner_bulto_vendido(Bulto bultoVendido) {
        this.a_bulto_vendido = bultoVendido;
    }

    Lista_Empaque a_list_empaque;
    Bulto a_bulto_vendido;
}//Fin de la clase Lista_Empaque_U_Bulto
