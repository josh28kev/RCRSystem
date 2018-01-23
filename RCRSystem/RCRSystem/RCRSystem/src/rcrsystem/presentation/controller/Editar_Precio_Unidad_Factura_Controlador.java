package rcrsystem.presentation.controller;

import Modelo.TotalMaterialVendido;
import Modelo.dao.Total_Material_Vendido_DAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import rcrsystem.presentation.model.Facturar_Modelo;
import rcrsystem.presentation.view.VentanaPrecioUnidFacturacion;

public class Editar_Precio_Unidad_Factura_Controlador {

    public Editar_Precio_Unidad_Factura_Controlador(VentanaPrecioUnidFacturacion vista, Facturar_Modelo modelo, int moneda) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_modelo_logico = new Total_Material_Vendido_DAO();
        this.a_moneda = moneda;
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void modificar() {
        BigDecimal precioUnid = new BigDecimal("0");
        boolean bandera = false;
        TotalMaterialVendido totalMV = a_modelo.obtener_total_material_vendido_lista_empaque_actual();
        a_modelo.limpiar_errores();
        if (a_vista.precio_JTextField.getText().length() != 0) {
            for (int i = 0; i < a_vista.precio_JTextField.getText().length(); i++) {
                if (!Character.isDigit(a_vista.precio_JTextField.getText().charAt(i)) && a_vista.precio_JTextField.getText().charAt(i) != '.') {
                    a_modelo.obtener_errores().put("Precio", "Digitó una letra");
                    precioUnid = new BigDecimal("-1");
                    bandera = true;
                }
            }
            if (precioUnid.compareTo(new BigDecimal("-1")) != 0) {
                precioUnid = new BigDecimal(a_vista.precio_JTextField.getText());
            }
            if (precioUnid.compareTo(new BigDecimal("0")) == 1 && bandera == false) {
                totalMV.poner_precio_unid(precioUnid);
                totalMV.poner_importe(precioUnid.multiply(totalMV.obtener_peso_total_v()));
            } else if (bandera == false) {
                a_modelo.obtener_errores().put("Precio", "Precio de material incorrecta");
            }
        } else {
            a_modelo.obtener_errores().put("Precio", "Precio de material incorrecta");
        }
        if (a_modelo.obtener_errores().isEmpty()) {
            try {
                a_modelo_logico.actualizar(totalMV);
                a_vista.setVisible(false);
                a_modelo.colocar_mensaje("¡Material modificado correctamente!");
                a_modelo.colocar_total_material_vendido_lista_empaque_actual(totalMV);
                a_modelo.colocar_mensaje("");
                a_modelo.colocar_total_material_vendido_lista_empaque(Total_Material_Vendido_DAO.obtener_i_total_materiales_vendidos(a_modelo.obtener_lista_empaque_actual().obtener_codigo_l()), a_moneda);
                a_modelo.colocar_total_material_vendido_lista_empaque_actual(new TotalMaterialVendido());
                a_modelo.limpiar_errores();
                a_modelo.colocar_reposiciones(new ArrayList());
            } catch (Exception e) {
                System.out.println("Hubo un error editando");
            }
        } else {
            a_modelo.colocar_mensaje("¡Hay errores!");
            a_modelo.colocar_total_material_vendido_lista_empaque_actual(totalMV);
        }
    }

    public void cerrar() {
        this.a_vista.setVisible(false);
        a_modelo.colocar_total_material_vendido_lista_empaque_actual(new TotalMaterialVendido());
        a_modelo.limpiar_errores();
    }

    private VentanaPrecioUnidFacturacion a_vista;
    private Facturar_Modelo a_modelo;
    private Total_Material_Vendido_DAO a_modelo_logico;
    private int a_moneda;
}
