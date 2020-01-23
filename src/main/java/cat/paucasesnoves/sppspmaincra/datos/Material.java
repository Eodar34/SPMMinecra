package cat.paucasesnoves.sppspmaincra.datos;

public class Material {

    private int id;
    private String nombre;
    private String imagen;
    private String obtencion;

    public Material(String nombre, String imagen, String obtencion) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.obtencion = obtencion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public String getObtencion() {
        return obtencion;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", obtencion='" + obtencion + '\'' +
                '}';
    }
}
