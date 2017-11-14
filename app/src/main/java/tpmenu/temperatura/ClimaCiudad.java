package tpmenu.temperatura;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ClimaCiudad {
    @GET("weather") //interfas para realiza la consulta
    Call<City> getCity(@Query("id") int idCity, @Query("appid") String key,
                       @Query("units") String value, @Query("lang") String lang);
}
