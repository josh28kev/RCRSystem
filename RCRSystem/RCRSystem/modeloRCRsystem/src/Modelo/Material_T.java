package Modelo;

public class Material_T {

    public Material_T() {
    }

    public Material_T(String codigo, Material Tmaterial) {
        this.a_codigo = codigo;
        this.a_t_material = Tmaterial;
    }

    public String obtener_codigo() {
        return a_codigo;
    }

    public void poner_codigo(String codigo) {
        this.a_codigo = codigo;
    }

    public Material obtener_t_material() {
        return a_t_material;
    }

    public void poner_t_material(Material Tmaterial) {
        this.a_t_material = Tmaterial;
    }

    private String a_codigo;
    private Material a_t_material;
}//Fin de la clase Material_T
