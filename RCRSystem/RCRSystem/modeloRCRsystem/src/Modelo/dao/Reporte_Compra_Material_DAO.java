package Modelo.dao;

import Modelo.BD.Conexion;
import Modelo.Reporte_Compra_Materiales;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reporte_Compra_Material_DAO {

    public static List<Reporte_Compra_Materiales> obtener_reportes_compras_materiales(int regcompra) {
        ResultSet reporteCompraMaterial
                = Conexion.obtener_registros("select Materiales.nombre as \"Material\",\n"
                        + "	TotalMaterialComprado.pesoTotalC as \"Total\"\n"
                        + "	from Materiales,TotalMaterialComprado\n"
                        + "	where TotalMaterialComprado.materialComprado = Materiales.codigoMaterial\n"
                        + "	and TotalMaterialComprado.regComp = "+ regcompra + ";");
        List<Reporte_Compra_Materiales> ListaBultos = new ArrayList();

        try {
            while (reporteCompraMaterial.next()) {
                Reporte_Compra_Materiales cnt = new Reporte_Compra_Materiales();
                cnt.poner_nombre(reporteCompraMaterial.getString(1));
                cnt.poner_precio(new BigDecimal(reporteCompraMaterial.getString(2)));
                ListaBultos.add(cnt);
            }

            reporteCompraMaterial.close();
            Conexion.ae_con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListaBultos;
    }
}//Fin de la clase Reporte_Compra_Material_DAO
