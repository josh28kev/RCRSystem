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
import rcrsystem.presentation.view.VentanaFacturaExportacion;
import rcrsystem.presentation.view.VentanaPrecioUnidFacturacion;

public class Factura_Exportacion_Controlador {

    public Factura_Exportacion_Controlador(VentanaFacturaExportacion vista, Facturar_Modelo modelo) {
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
            Editar_Precio_Unidad_Factura_Controlador fc = new Editar_Precio_Unidad_Factura_Controlador(v, a_modelo, 0);
            v.setVisible(true);

        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡ERRORF!");
        }
    }

    public String formato_fecha(String dia, String mes, String año) {
//        return String.valueOf(a_vista.diaFactufa.getSelectedItem())
//                + "-" + this.numero_mes() + "-" + a_vista.añoFactura.getText();
        return dia + "-" + this.numero_mes(mes) + "-" + año;
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
            String fechaFactura = (1900 + a_vista.campoFecha1.getDate().getYear()) + "-" + (a_vista.campoFecha1.getDate().getMonth() + 1) + "-" + a_vista.campoFecha1.getDate().getDate();
            String fechaEmbarque = (1900 + a_vista.campoFecha2.getDate().getYear()) + "-" + (a_vista.campoFecha2.getDate().getMonth() + 1) + "-" + a_vista.campoFecha2.getDate().getDate();
            String fechaExportacion = (1900 + a_vista.campoFecha3.getDate().getYear()) + "-" + (a_vista.campoFecha3.getDate().getMonth() + 1) + "-" + a_vista.campoFecha3.getDate().getDate();
            String fechaDespacho = (1900 + a_vista.campoFecha4.getDate().getYear()) + "-" + (a_vista.campoFecha4.getDate().getMonth() + 1) + "-" + a_vista.campoFecha4.getDate().getDate();
            factura.poner_fecha(fechaFactura);
            factura.poner_fecha_despacho(fechaEmbarque);
            factura.poner_fecha_embarque(fechaExportacion);
            factura.poner_fecha_exportacion(fechaDespacho);
            factura.poner_flete(new BigDecimal(a_vista.txtFlete.getText()));
            factura.poner_lista_empaque(a_modelo.obtener_lista_empaque_actual());
            factura.poner_metodo_transporte(a_modelo.obtener_lista_empaque_actual().obtener_medio_transporte());
            factura.poner_numero_f(a_vista.numFactura.getText());
            factura.poner_pais_origen(a_vista.txtOrigen.getText());
            factura.poner_peso_bruto(new BigDecimal(a_vista.lbPesoBruto.getText()));
            factura.poner_peso_neto(new BigDecimal(a_vista.lbPesoNeto.getText()));
            factura.poner_puerto_destino(a_vista.txtPuertoDestino.getText());
            factura.poner_puerto_exportacion(a_vista.txtPuertoExportacion.getText());
            factura.poner_po(a_vista.txtPO.getText());
            factura.poner_puerto_embarque(a_vista.txtPuertoEmbarque.getText());
            factura.poner_icoterm(a_vista.txtIcoterm.getText());
            factura.poner_subtotal(new BigDecimal(a_vista.lbSubtotal.getText()));
            factura.poner_tipo_factura(0);//exportacion
            factura.poner_total_bultos(Integer.parseInt(a_vista.lbTotalBultos.getText()));
            factura.poner_total_f(new BigDecimal(a_vista.lbTotal.getText()));
            a_modelo.grabar_Factura(factura);
            Lista_Empaque_DAO.actualizar(a_modelo.obtener_lista_empaque_actual());
            List<Lista_Empaque_U_Bulto> list = new ArrayList();
            list = Lista_Empaque_U_Bulto_DAO.obtener_bultos_por_lista(a_modelo.obtener_lista_empaque_actual().obtener_codigo_l());
            for (int i = 0; i < list.size(); i++) {
                Inventario_DAO.actualizar(list.get(i).obtener_bulto_vendido());
            }
            generar_reporteFactura(factura.obtener_numero_f(), a_modelo.obtener_lista_empaque_actual().obtener_medio_transporte());
            return 1;
        }
    }

    public void generar_reporteFactura(String numF, int tipo) {
        try {
            Map parametros = new HashMap();
            parametros.put("nuF", numF);
            JasperReport contenido = null;
            if (tipo == 1) {
                contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/FacturaExpoMaritimo.jasper"));
            } else {
                contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/FacturaExpoTerrestre.jasper"));
            }

            // JasperReport contenido = JasperCompileManager.compileReport("C:/RCRSystem/RCRSystem/RCRSystem/src/rcrsystem/presentation/view/reporte/reporteCompra
            JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.obtener_conexion(null, null, null));

            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Factura de exportación");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la factura");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private VentanaFacturaExportacion a_vista;
    private Facturar_Modelo a_modelo;

} // Fin de la clase Factura_Exportacion_Controlador
