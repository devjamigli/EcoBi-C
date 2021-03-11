package com.venusdev.ecobi_c.data;

import com.venusdev.ecobi_c.data.response.StationsReponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityBikApiService {
    @GET("ecobici")
    Call<StationsReponse> getStations();
}
