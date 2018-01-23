package rcrsystem.presentation.controller;

import Modelo.BD.Conexion;
import Modelo.Reporte_Compra;
import Modelo.dao.Reporte_Compra_DAO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import rcrsystem.presentation.model.ReporteCompra_Modelo;
import rcrsystem.presentation.view.VentanaConsultaCompras;
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
import rcrsystem.presentation.view.VentanaCarga;

public class ReporteCompras_controlador {

    public ReporteCompras_controlador(VentanaConsultaCompras vista, ReporteCompra_Modelo modelo) {
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
        this.colocar_materiales(Reporte_Compra_DAO.obtener_reportes_compras_fecha(nuevaFecha1, nuevaFecha1));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public List<Reporte_Compra> obtener_materiales() {
        return a_materiales;
    }

    public Reporte_Compra obtener_material(int pos) {
        return a_materiales.get(pos);
    }

    public void colocar_materiales(List<Reporte_Compra> materiales) {
        this.a_materiales = materiales;
    }

    public void buscarFecha(String Fecha1, String Fecha2) {
        this.colocar_materiales(Reporte_Compra_DAO.obtener_reportes_compras_fecha(Fecha1, Fecha2));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarProveedor(String proveedor) {
        this.colocar_materiales(Reporte_Compra_DAO.obtener_reportes_compras_proveedor(proveedor));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarNumCompra(String numcompra) {
        this.colocar_materiales(Reporte_Compra_DAO.obtener_reportes_compras_numero_compra(numcompra));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarProveedorNombre(String proveedor) {
        this.colocar_materiales(Reporte_Compra_DAO.obtener_reportes_compras_proveedor_nombre(proveedor));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void buscarBulto(String codBulto) {
        this.colocar_materiales(Reporte_Compra_DAO.obtener_reportes_compras_bulto(codBulto));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public void liberarConexiones() {
        Reporte_Compra_DAO.liberar_conexiones();
    }

    /*public void reiniciar_filtro() {
        a_vista.jTextField_filtro.setText("");
    }*/
    public void pre_agregar() {
        a_modelo.limpiar_errores();
        a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_agregar);
        Reporte_Compra material = new Reporte_Compra();
        a_modelo.colocar_material_actual(material);
    }

    public void pre_generar_reporte(int row, VentanaCarga a_carga) {
        try {
            Reporte_Compra reporte = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            int ax = JOptionPane.showConfirmDialog(null, "¿Seguro que desea generar el reporte?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (ax == JOptionPane.YES_OPTION) {
                a_carga.setVisible(true);
                this.generar_reporte(reporte.obtener_registro_compra().obtener_numero_compra(), reporte.obtener_tipos_bultos());
                this.a_vista.toFront();
            }
        } catch (Exception ex) {
            //System.err.println(ex.getMessage());
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento");
        }
    }

    public void generar_reporte(int numeroCompa, int tiposBultos) {
        try {
            Map parametros = new HashMap();
            parametros.put("numCompra", numeroCompa);
            JasperReport contenido = null;
            switch (tiposBultos) {
                case 1:
                    contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteCompraPacas.jasper"));
                    break;
                case 2:
                    contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteCompraSacas.jasper"));
                    break;
                default:
                    contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/reporteCompraAmbas.jasper"));
                    break;
            }
            // JasperReport contenido = JasperCompileManager.compileReport("C:/RCRSystem/RCRSystem/RCRSystem/src/rcrsystem/presentation/view/reporte/reporteCompra
            JasperPrint imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.obtener_conexion(null, null, null));
            JasperViewer v = new JasperViewer(imprimir, false);
            v.setTitle("Reporte de ingreso");
            v.setVisible(true);
            contenido = (JasperReport) JRLoader.loadObject(getClass().getResource("/rcrsystem/presentation/view/reporte/bultosComprados.jasper"));
            imprimir = JasperFillManager.fillReport(contenido, parametros, Conexion.obtener_conexion(null, null, null));
            v = new JasperViewer(imprimir, false);
            v.setTitle("Detalle de ingreso");
            v.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el Reporte");
            Logger.getLogger(Compras_Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private VentanaConsultaCompras a_vista;
    private ReporteCompra_Modelo a_modelo;
    private List<Reporte_Compra> a_materiales;
}
