package tpmenu.temperatura;

public class Ciudad { // delaro las variable y las clases para llenar la base de datos
    private int id;
    private String nombre;

    public Ciudad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;}

    public int getTId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
