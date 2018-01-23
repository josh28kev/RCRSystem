package rcrsystem.presentation.controller;

import Modelo.Bulto;
import Modelo.Material_T;
import Modelo.dao.Proveedor_DAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import rcrsystem.presentation.model.Consulta_Proveedor_Modelo;
import rcrsystem.presentation.view.VentanaConsultaProveedor;

public class Consulta_Proveedor_Controlador {

    public Consulta_Proveedor_Controlador(VentanaConsultaProveedor vista, Consulta_Proveedor_Modelo modelo, String b) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        vista.setController(this);
        vista.setModel(modelo);
    }

    public boolean agregar_bulto(Bulto bulto, ArrayList<BigDecimal> pesos) {
        BigDecimal confirmar = new BigDecimal("0");
        BigDecimal bulto_peso = bulto.obtener_peso();
        for (BigDecimal peso : pesos) {
            if (peso.intValue() < 10) {
                a_vista.MensajeAviso("No se permiten pacas con peso menor a 10 Kg");
                return false;
            }
            confirmar = confirmar.add(peso);
        }
        if (confirmar.equals((BigDecimal) bulto_peso)) {
            for (int i = 0; i < pesos.size(); i++) {
                Bulto nuevoBulto = new Bulto();
                nuevoBulto.poner_codigo(bulto.obtener_codigo() + "." + String.valueOf(i + 1));
                nuevoBulto.poner_peso(pesos.get(i));
                nuevoBulto.poner_material(new Material_T("P" + bulto.obtener_material().obtener_codigo().substring(1),
                        bulto.obtener_material().obtener_t_material()));
                nuevoBulto.poner_tipo(1);
                nuevoBulto.poner_estado(0);
                a_modelo.grabar_bulto(nuevoBulto);
            }
            borrar_bulto(bulto);
            actualizar_peso2(bulto);
            a_vista.MensajeAviso("Bulto modificado correctamente");
            return true;
        } else {
            a_vista.MensajeAviso("La suma de los pesos no puede ser mayor ni menor a la saca");
            return false;
        }
    }

    public void borrar_bulto(Bulto bulto) {
        a_modelo.borrar_bulto(bulto);
    }

    public int actualizar_peso2(Bulto bc) {
        return a_modelo.actualizar_peso2(bc);
    }

    public Bulto obtener_bulto(String bulto) {
        return a_modelo.obtener_bulto(bulto);
    }

    private VentanaConsultaProveedor a_vista;
    private Consulta_Proveedor_Modelo a_modelo;
    private Proveedor_DAO a_modelo_logico;
}
