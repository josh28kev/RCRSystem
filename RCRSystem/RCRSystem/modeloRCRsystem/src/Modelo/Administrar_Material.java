package Modelo;


public class Administrar_Material {

    public Administrar_Material(Material material, float precioSaca, float precioPaca) {
        this.a_material = material;
    }

     public Administrar_Material() {
        this.a_material = new Material();
    }
     
    public Material obtener_material() {
        return a_material;
    }

    public void poner_material(Material material) {
        this.a_material = material;
    }

    private Material a_material;
}//Fin de la clase Administrar_Material
