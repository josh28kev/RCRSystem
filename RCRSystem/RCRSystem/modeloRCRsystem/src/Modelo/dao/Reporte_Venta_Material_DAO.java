package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Reporte_Venta_Materiales;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reporte_Venta_Material_DAO {

    public static List<Reporte_Venta_Materiales> obtener_reportes_compras_materiales(int regcompra) {
        ResultSet reporteVentaMaterial
                = Conexion.obtener_registros("select Bulto.codigoBulto,\n"
                        + "	Materiales.nombre,\n"
                        + "	Bulto.pesoBulto\n"
                        + "     from  Bulto,Materiales,TipoMaterial,ListEmpaque_U_Bulto\n"
                        + "     where Bulto.materialBulto = TipoMaterial.codigoTM\n"
                        + "	and TipoMaterial.Tmaterial = codigoMaterial\n"
                        + "	and Bulto.codigoBulto = ListEmpaque_U_Bulto.bultoVendido\n"
                        + "	and ListEmpaque_U_Bulto.listEmpaque = " + regcompra + ";");
        List<Reporte_Venta_Materiales> ListaBultos = new ArrayList();

        try {
            while (reporteVentaMaterial.next()) {
                Reporte_Venta_Materiales cnt = new Reporte_Venta_Materiales();
                cnt.poner_bulto(reporteVentaMaterial.getString(1));
                cnt.poner_material(reporteVentaMaterial.getString(2));
                cnt.poner_peso(new BigDecimal(reporteVentaMaterial.getString(3)));
                ListaBultos.add(cnt);
            }

            reporteVentaMaterial.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }
}//Fin de la clase Reporte_Venta_Material_DAO
