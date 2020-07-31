package fr.cours.coincoins_v1_0.ws;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WSInterface {
    @GET("marker/findAll")
    Call<List<MarkerWs>> getAllMarkers();

    @Headers("Content-Type: application/json")
    @POST("marker/add")
    Call<MarkerWs> postMaker(
            @Body MarkerWs markerWs
            );

}
