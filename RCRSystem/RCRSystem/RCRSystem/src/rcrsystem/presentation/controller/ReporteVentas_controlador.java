package rcrsystem.presentation.controller;

import Modelo.BD.Conexion;
import Modelo.Factura;
import Modelo.Lista_Empaque;
import Modelo.dao.Factura_DAO;
import Modelo.dao.Lista_Empaque_DAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
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
import rcrsystem.presentation.model.ReporteVenta_Modelo;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaConsultaVentas;

public class ReporteVentas_controlador {

    public ReporteVentas_controlador(VentanaConsultaVentas vista, ReporteVenta_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_materiales = new ArrayList();
        cargar();
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar() {
        Date Fecha1 = new Date();
        String nuevaFecha1 = (1900 + Fecha1.getYear()) + "-" + (Fecha1.getMonth() + 1) + "-" + Fecha1.getDate();
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_fecha(nuevaFecha1, nuevaFecha1));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public List<Lista_Empaque> obtener_materiales() {
        return a_materiales;
    }

    public Lista_Empaque obtener_material(int pos) {
        return a_materiales.get(pos);
    }

    public void colocar_materiales(List<Lista_Empaque> materiales) {
        this.a_materiales = materiales;
    }

    public void buscarFecha(String Fecha1, String Fecha2) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_fecha(Fecha1, Fecha2));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarCliente(String cliente) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_cliente(cliente));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarTransporte(int transporte) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_transporte(transporte));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarClienteNombre(String cliente) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_cliente_nombre(cliente));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarContenedor(String contenedor) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_contenedor(contenedor));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarNumVenta(String venta) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_numero_venta(venta));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarNumFactura(String factura) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_numero_factura(factura));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarBulto(String codBulto) {
        this.colocar_materiales(Lista_Empaque_DAO.obtener_lista_empaque_bulto(codBulto));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void liberarConexiones() {
        Lista_Empaque_DAO.liberar_conexiones();
    }

    /*public void reiniciar_filtro() {
        a_vista.jTextField_filtro.setText("");
    }*/
    public void pre_agregar() {
        a_modelo.limpiar_errores();
        a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_agregar);
        Lista_Empaque material = new Lista_Empaque();
        a_modelo.colocar_material_actual(material);
    }

    public void pre_generar_reporte(int row, VentanaCarga a_carga) {
        try {
            Lista_Empaque reporte = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            int ax = JOptionPane.showConfirmDialog(null, "¿Seguro que desea generar el reporte?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ax == JOptionPane.YES_OPTION) {
                a_carga.setVisible(true);
                if (reporte.obtener_medio_transporte() == 1) {
                    generarReporteMarit(reporte.obtener_codigo_l());
                } else {
                    generarReporteTerr(reporte.obtener_codigo_l());
                }
                a_vista.toFront();
            }
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento");
        }
    }

    public void generarReporteTerr(int num) {
        try {
            Map parametros = new HashMap();
            parametros.put("codigoListEm", num);
            JasperReport contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteListEmpTerr.jasper"));

            JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.obtener_conexion(null, null, null));

            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Lista de Empaque");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la lista de empaque");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarReporteMarit(int num) {
        try {
            Map parametros = new HashMap();
            parametros.put("codigoListEm", num);
            JasperReport contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteListEmpMarit.jasper"));
            JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.obtener_conexion(null, null, null));

            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Lista de Empaque");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la lista de empaque");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pre_generar_reporteFactura(int row, VentanaCarga a_carga) {
        try {
            Lista_Empaque reporte = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            Factura factura = Factura_DAO.obtener_facturaXLE(reporte.obtener_codigo_l());
            int ax = JOptionPane.showConfirmDialog(null, "¿Seguro que desea generar la Factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ax == JOptionPane.YES_OPTION) {
                a_carga.setVisible(true);
                if (factura.obtener_tipo_factura() == 1) {
                    generar_reporteFacturaNacional(factura.obtener_numero_f());
                } else {
                    generar_reporteFactura(factura.obtener_numero_f(), reporte.obtener_medio_transporte());
                }
                a_vista.toFront();
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No se ha realizado la factura de esta lista de empaque");
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento");
        }
    }

    public void generar_reporteFacturaNacional(String numF) {
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

    private VentanaConsultaVentas a_vista;
    private ReporteVenta_Modelo a_modelo;
    private List<Lista_Empaque> a_materiales;
}
