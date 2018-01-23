package rcrsystem.presentation.controller;

import Modelo.BD.Conexion;
import Modelo.Factura;
import Modelo.Lista_Empaque_U_Bulto;
import Modelo.Lista_Empaque;
import Modelo.TotalMaterialVendido;
import Modelo.dao.Inventario_DAO;
import Modelo.dao.Lista_Empaque_U_Bulto_DAO;
import Modelo.dao.Lista_Empaque_DAO;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import rcrsystem.presentation.model.Facturar_Modelo;
import rcrsystem.presentation.view.VentanaFacturaNacional;
import rcrsystem.presentation.view.VentanaPrecioUnidFacturacion;

public class Factura_Nacional_Controlador {

    public Factura_Nacional_Controlador(VentanaFacturaNacional vista, Facturar_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void abrir(int row, String moneda) {
        try {
            TotalMaterialVendido lE = a_modelo.obtener_total_material_lista_empaque().obtener_fila_a(row);
            a_modelo.colocar_total_material_vendido_lista_empaque_actual(lE);
            VentanaPrecioUnidFacturacion v = new VentanaPrecioUnidFacturacion(a_vista, true);
            v.lbMoneda1.setText(moneda);
            v.lbMoneda2.setText(moneda);
            Editar_Precio_Unidad_Factura_Controlador fc = new Editar_Precio_Unidad_Factura_Controlador(v, a_modelo, 1);
            v.setVisible(true);

        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡ERRORF!");
        }
    }

    public String formato_fecha(String dia, String mes, String año) {
//        return String.valueOf(a_vista.diaFactufa.getSelectedItem())
//                + "-" + this.numero_mes() + "-" + a_vista.añoFactura.getText();
        return año + "-" + this.numero_mes(mes) + "-" + dia;
    }

    public String obtener_fecha() {
        Calendar c1 = new GregorianCalendar();
        String dia = "";
        String mes = "";
        String año = "";

        if (c1.get(Calendar.DATE) < 10) {
            dia = "0" + String.valueOf(c1.get(Calendar.DATE));
        } else {
            dia = String.valueOf(c1.get(Calendar.DATE));
        }
        if ((c1.get(Calendar.MONTH) + 1) < 10) {
            mes = "0" + String.valueOf(c1.get(Calendar.MONTH) + 1);
        } else {
            mes = String.valueOf(c1.get(Calendar.MONTH) + 1);
        }
        año = String.valueOf(c1.get(Calendar.YEAR));
        return dia + "-" + mes + "-" + año;
    }

    String numero_mes(String m) {

        switch (m) {
            case "Enero":
                return "01";
            case "Febrero":
                return "02";
            case "Marzo":
                return "03";
            case "Abril":
                return "04";
            case "Mayo":
                return "05";
            case "Junio":
                return "06";
            case "Julio":
                return "07";
            case "Agosto":
                return "08";
            case "Setiembre":
                return "09";
            case "Octubre":
                return "10";
            case "Noviembre":
                return "11";
            case "Diciembre":
                return "12";
        }
        return "";
    }

    public String mes_numero(String mes) {
        switch (mes) {
            case "01":
                return "Enero";
            case "02":
                return "Febrero";
            case "03":
                return "Marzo";
            case "04":
                return "Abril";
            case "05":
                return "Mayo";
            case "06":
                return "Junio";
            case "07":
                return "Julio";
            case "08":
                return "Agosto";
            case "09":
                return "Setiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
            case "12":
                return "Diciembre";
        }
        return "";
    }

    public void cerrar() {
        this.a_vista.setVisible(false);
        a_modelo.colocar_lista_empaque_actual(new Lista_Empaque());
    }

    public int confirmar_Factura() {
        if (Conexion.conectar() == 0) {
            return 2;
        } else {
            a_vista.setVisible(false);
            Factura factura = new Factura();
            factura.poner_cliente(a_modelo.obtener_lista_empaque_actual().obtener_cliente());
            factura.poner_enviado_a(a_vista.txtEnviado.getText());
            String fecha = (1900 + a_vista.campoFecha1.getDate().getYear()) + "-" + (a_vista.campoFecha1.getDate().getMonth() + 1) + "-" + a_vista.campoFecha1.getDate().getDate();
            System.out.println(fecha);
            factura.poner_fecha(fecha);
            factura.poner_fecha_despacho(fecha);
            factura.poner_fecha_embarque(fecha);
            factura.poner_fecha_exportacion(fecha);
            factura.poner_lista_empaque(a_modelo.obtener_lista_empaque_actual());
            factura.poner_metodo_transporte(a_modelo.obtener_lista_empaque_actual().obtener_medio_transporte());
            factura.poner_numero_f(a_vista.numFactura.getText());
            factura.poner_peso_bruto(new BigDecimal(a_vista.lbPesoBruto.getText()));
            factura.poner_peso_neto(new BigDecimal(a_vista.lbPesoNeto.getText()));
            factura.poner_subtotal(new BigDecimal(a_vista.lbSubtotal.getText()));
            factura.poner_tipo_factura(1);//nacional
            factura.poner_total_bultos(Integer.parseInt(a_vista.lbTotalBultos.getText()));
            factura.poner_total_f(new BigDecimal(a_vista.lbTotal.getText()));
            a_modelo.grabar_Factura(factura);
            Lista_Empaque_DAO.actualizar(a_modelo.obtener_lista_empaque_actual());
            List<Lista_Empaque_U_Bulto> list = new ArrayList();
            list = Lista_Empaque_U_Bulto_DAO.obtener_bultos_por_lista(a_modelo.obtener_lista_empaque_actual().obtener_codigo_l());
            for (int i = 0; i < list.size(); i++) {
                Inventario_DAO.actualizar(list.get(i).obtener_bulto_vendido());
            }
            generar_reporteFactura(factura.obtener_numero_f());
            return 1;
        }
    }

    public void generar_reporteFactura(String numF) {
        try {
            Map parametros = new HashMap();
            parametros.put("nuF", numF);
            JasperReport contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/FacturaNac.jasper"));
            // JasperReport contenido = JasperCompileManager.compileReport("C:/RCRSystem/RCRSystem/RCRSystem/src/rcrsystem/presentation/view/reporte/reporteCompra
            JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.obtener_conexion(null, null, null));

            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Factura Nacional");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la factura");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private VentanaFacturaNacional a_vista;
    private Facturar_Modelo a_modelo;

} // Fin de la clase Factura_Exportacion_Controlador
