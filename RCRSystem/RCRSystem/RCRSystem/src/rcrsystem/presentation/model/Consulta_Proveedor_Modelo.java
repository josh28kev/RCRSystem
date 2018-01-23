package rcrsystem.presentation.model;

import Modelo.Bulto;
import Modelo.dao.Bulto_DAO;
import Modelo.dao.Inventario_DAO;

public class Consulta_Proveedor_Modelo extends java.util.Observable {

    public Consulta_Proveedor_Modelo() {
        this.inicializar();
    }

    public Bulto obtener_Bulto() {
        return a_bulto;
    }

    public void colocar_Bulto(Bulto bulto) {
        this.a_bulto = bulto;
        setChanged();
        notifyObservers(ae_bulto_modelo);
    }

    void inicializar() {
        a_bulto = new Bulto();
    }

    public int grabar_bulto(Bulto bc) {
        return Bulto_DAO.grabar(bc);
    }

    public int borrar_bulto(Bulto bc) {
        return Bulto_DAO.borrar2(bc);
    }
    
     public int actualizar_peso2(Bulto bc) {
        return Inventario_DAO.actualizar_peso2(bc);
    }

    public Bulto obtener_bulto(String codigoBulto) {
        return Bulto_DAO.obtener_bulto(codigoBulto);
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(ae_bulto_modelo);
    }

    private Bulto a_bulto;
    public static Integer ae_bulto_modelo = 0;
}
