package Modelo.BD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Modelo_generico {
    
    public String obtener_fecha_formateada(Date fecha) {
        return a_formato.format((Date)fecha);
    }
    
    public Date string_a_fecha(String text) {        
        try {
            a_fecha_string=(Date)a_formato.parseObject(text);
        } catch (ParseException ex) {}
        return a_fecha_string;
    }
    
    private SimpleDateFormat a_formato = new SimpleDateFormat("yyyy-MM-dd");
    private Date a_fecha_string;
}//Fin de la clase GenericModel
