package tpmenu.temperatura;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

// Declaro los datos que voy a necestiar traer de la pagina web

public class City {
    private  int id;
    private String name;
    @SerializedName("main")
    private Clima clima;

    public City(int id, String name, Clima clima) {
        this.id = id; // numero id del lugar del clima
        this.name = name;// nombre de la ciudad
        this.clima = clima; // clima
    }
    // creo los get y los set para traerlos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    public static Clima parseJSON(String response){ // uso el formato gso para traer los datos
        Gson gson = new GsonBuilder().create();
        Clima clima = gson.fromJson(response,Clima.class);
        return clima;
    }
}
