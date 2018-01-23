package rcrsystem.presentation.controller;

import Modelo.Reporte_Venta_Materiales;
import Modelo.dao.Reporte_Venta_Material_DAO;
import java.util.ArrayList;
import java.util.List;
import rcrsystem.presentation.model.ReporteVentaMaterial_Modelo;
import rcrsystem.presentation.view.VentanaReporteVentasMateriales;

public class ReporteVentasMateriales_Controlador {

    public ReporteVentasMateriales_Controlador(VentanaReporteVentasMateriales vista, ReporteVentaMaterial_Modelo modelo, int regCompra) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        a_materiales = new ArrayList();
        cargar(regCompra);
        vista.setController(this);
        vista.setModel(modelo);
    }

    public void cargar(int regCompra) {
        this.colocar_materiales(Reporte_Venta_Material_DAO.obtener_reportes_compras_materiales(regCompra));
        a_modelo.colocar_materiales(a_materiales, 0);
    }

    public List<Reporte_Venta_Materiales> obtener_materiales() {
        return a_materiales;
    }

    public void colocar_materiales(List<Reporte_Venta_Materiales> materiales) {
        this.a_materiales = materiales;
    }

    /*public void reiniciar_filtro() {
        a_vista.jTextField_filtro.setText("");
    }*/
    public void pre_agregar() {
        a_modelo.limpiar_errores();
        a_modelo.colocar_modo(rcrsystem.Aplicacion.ae_modo_agregar);
        Reporte_Venta_Materiales material = new Reporte_Venta_Materiales();
        a_modelo.colocar_material_actual(material);
    }

    private VentanaReporteVentasMateriales a_vista;
    private ReporteVentaMaterial_Modelo a_modelo;
    private List<Reporte_Venta_Materiales> a_materiales;
}
