package fr.cours.coincoins_v1_0.ws;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WSInterface {
    @GET("marker/findAll")
    Call<List<MarkerWs>> getAllMarkers();
}
