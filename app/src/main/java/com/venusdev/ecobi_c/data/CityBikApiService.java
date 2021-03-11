/**
 * Service CityBikApiService
 *
 * Realiza el call con el metodo GET hacia el endpoint "ecobici".
 *
 * @author Jair Migliolo
 * @version 2020.1103
 * @since 1.0
 */


package com.venusdev.ecobi_c.data;

import com.venusdev.ecobi_c.data.response.StationsReponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityBikApiService {
    @GET("ecobici")
    Call<StationsReponse> getStations();
}
