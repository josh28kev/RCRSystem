package rcrsystem.presentation.controller;

import Modelo.BD.Conexion;
import Modelo.Bulto;
import Modelo.Material;
import Modelo.Material_T;
import Modelo.Proveedor;
import Modelo.Registro_Compra_U_Bulto;
import Modelo.Registro_Compra;
import Modelo.Reporte_Compra;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import rcrsystem.presentation.model.Compras_Modelo;
import rcrsystem.presentation.view.VentIngresarRegCompra;

public class Compras_Controlador {

    public Compras_Controlador(VentIngresarRegCompra vista, Compras_Modelo modelo) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        vista.setControlador(this);
        vista.setModelo(modelo);
        this.numero_bulto = 0;
        this.peso_total = new BigDecimal("0");
        this.bultos_total = 0;
        cargar();
    }

    public void cargar() {
        String secRegC = a_modelo.obtener_secuencia_registro_compra();
        a_vista.lbReg.setText("");
        a_vista.lbReg.setText(secRegC);
        a_vista.lbBulto.setText("B" + obtener_numero_bulto() + secRegC);
        a_vista.material_JComboBox.removeAllItems();
        List<Material> lista = new ArrayList<Material>();
        lista = a_modelo.obtener_materiales_2();
        for (int i = 0; i < lista.size(); i++) {
            a_vista.material_JComboBox.addItem("(" + lista.get(i).obtener_codigo() + ")    " + lista.get(i).obtener_nombre());
        }
        List<Proveedor> lista2 = new ArrayList<Proveedor>();
        lista2 = a_modelo.obtener_proveedores();
        for (int i = 0; i < lista2.size(); i++) {
            a_vista.proveedor_JComboBox.addItem("(" + lista2.get(i).obtener_codigo() + ")    " + lista2.get(i).obtener_nombre());
        }
    }

    public void limpia() {
        String secRegC = a_modelo.obtener_secuencia_registro_compra();
        a_vista.lbReg.setText("");
        a_vista.lbReg.setText(secRegC);
        a_vista.lbBulto.setText("B" + 0 + secRegC);
        a_vista.lbPesoTotal.setText("0.0");
        a_modelo.obtener_compra().clear();
        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
        a_vista.txtChofer.setText("");
        a_vista.txtPesoBulto.setText("");
        a_vista.txtPlacaVehiculo.setText("");
        a_vista.lbCantBultos.setText("0");
        bultos_total = 0;
        peso_total = new BigDecimal("0");;
    }

    public void agregar_bulto() {
        Bulto bulto = new Bulto();
        Material_T mT = new Material_T();
        Material m = new Material();
        bulto.poner_codigo((a_vista.lbBulto.getText()));
        StringTokenizer st = new StringTokenizer(a_vista.material_JComboBox.getSelectedItem().toString(), "()");
        String codigo = "";
        String nombre = "";
        while (st.hasMoreTokens()) {

            codigo = st.nextToken();
            nombre = st.nextToken();

        }
        m.poner_codigo(codigo);
        m.poner_nombre(nombre);
        mT.poner_t_material(m);
        if (a_vista.paca_JRadioButton.isSelected()) {
            mT.poner_codigo("P" + codigo);
            bulto.poner_tipo(1);
        } else {
            mT.poner_codigo("S" + codigo);
            bulto.poner_tipo(2);
        }
        bulto.poner_material(mT);

        bulto.poner_peso(new BigDecimal(a_vista.txtPesoBulto.getText()));
        bulto.poner_estado(1);
        a_modelo.obtener_compra().add(bulto);
        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
        colocar_numero_bulto(numero_bulto + 1);
        colocar_peso_total(obtener_peso_total().add(new BigDecimal(a_vista.txtPesoBulto.getText())));
        poner_bultos_total(obtener_bultos_total() + 1);
        a_vista.lbBulto.setText("B" + obtener_numero_bulto() + a_vista.lbReg.getText());
        a_vista.lbPesoTotal.setText(String.valueOf(obtener_peso_total()));
        a_vista.lbCantBultos.setText(String.valueOf(obtener_bultos_total()));
        a_vista.txtPesoBulto.setText("");
    }

    public void borrar_bulto(int row) {

        if (row > -1) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el bulto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                Bulto bulto = a_modelo.obtener_modelo_material().obtener_fila_a(row);
                try {
                    a_modelo.eliminar_compra(bulto);
                    BigDecimal p = new BigDecimal(a_vista.lbPesoTotal.getText()).subtract(bulto.obtener_peso());
                    colocar_peso_total(p);
                    a_vista.lbPesoTotal.setText(p.toString());
                    int n = Integer.parseInt(a_vista.lbCantBultos.getText()) - 1;
                    poner_bultos_total(n);
                    a_vista.lbCantBultos.setText(Integer.toString(n));
                } catch (Exception ex) {
                    a_modelo.colocar_mensaje("¡No se ha seleccionado ningún bulto!");
                    a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
                }
            }
        } else {
            a_modelo.colocar_mensaje("¡No se ha seleccionado ningún bulto!");
            a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
        }

        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
    }

    public void borrar_todo() throws Exception {
        a_modelo.eliminar_todo();
        a_modelo.colocar_materiales_compra(a_modelo.obtener_compra(), 0);
    }

    public int obtener_numero_bulto() {
        return numero_bulto;
    }

    public void colocar_numero_bulto(int numBulto) {
        this.numero_bulto = numBulto;
    }

    public BigDecimal obtener_peso_total() {
        return peso_total;
    }

    public void colocar_peso_total(BigDecimal pesoTotal) {
        this.peso_total = pesoTotal;
    }

    public int obtener_bultos_total() {
        return bultos_total;
    }

    public void poner_bultos_total(int bultos_total) {
        this.bultos_total = bultos_total;
    }

    public int confirmar_compra() {
        if (a_modelo.obtener_compra().isEmpty() == true) {
            return 0;
        } else if (Conexion.conectar() == 0) {
            return 2;
        } else {
            boolean hayPacas, haySacas, hayAmbas;
            hayAmbas = false;
            hayPacas = false;
            haySacas = false;
            Registro_Compra regCompra = new Registro_Compra();
            Proveedor p = new Proveedor();
            List<Bulto> compras = new ArrayList<>();
            compras = a_modelo.obtener_compra();
            regCompra.poner_numero_compra(Integer.parseInt(a_vista.lbReg.getText()));
            regCompra.poner_peso_total(obtener_peso_total());
            regCompra.poner_bultos_total(obtener_bultos_total());
            a_modelo.grabar_registro_compra(regCompra);
            for (int i = 0; i < compras.size(); i++) {
                a_modelo.grabar_bulto_comprado(compras.get(i));
                if (compras.get(i).obtener_tipo() == 1) {
                    hayPacas = true;
                } else {
                    haySacas = true;
                }
                if (haySacas == true && hayPacas == true) {
                    hayAmbas = true;
                }
                Registro_Compra_U_Bulto reg_U_B = new Registro_Compra_U_Bulto();
                reg_U_B.poner_bulto(compras.get(i));
                reg_U_B.poner_registro_compra(regCompra);
                a_modelo.grabar_registro_compra_u_bulto(reg_U_B);
                a_modelo.procedimiento_ingresar_total_material_comprado(compras.get(i).obtener_material().obtener_t_material().obtener_codigo(), regCompra.obtener_numero_compra(), compras.get(i).obtener_peso());
            }
            Reporte_Compra reporte = new Reporte_Compra();
            if (hayAmbas == true) {
                reporte.poner_tipos_bultos(3);
            } else if (hayPacas == true) {
                reporte.poner_tipos_bultos(1);
            } else {
                reporte.poner_tipos_bultos(2);
            }
            reporte.poner_chofer(a_vista.txtChofer.getText());
            reporte.poner_placa_vehiculo(a_vista.txtPlacaVehiculo.getText());
            reporte.poner_registro_compra(regCompra);
            StringTokenizer st = new StringTokenizer(a_vista.proveedor_JComboBox.getSelectedItem().toString(), "()");
            String codigo = "";
            while (st.hasMoreTokens()) {

                codigo = st.nextToken();

                st.nextToken();
            }
            p.poner_codigo(codigo);
            reporte.poner_proveedor(p);
            a_modelo.grabar_reporte_compra(reporte);
            generar_reporte(regCompra.obtener_numero_compra(), reporte.obtener_tipos_bultos());
            return 1;
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

    private Compras_Modelo a_modelo = new Compras_Modelo();
    private VentIngresarRegCompra a_vista = new VentIngresarRegCompra();
    private int numero_bulto = 0;
    private BigDecimal peso_total = new BigDecimal("0");
    private int bultos_total = 0;
} // Fin de la clase Compras_Controlador
