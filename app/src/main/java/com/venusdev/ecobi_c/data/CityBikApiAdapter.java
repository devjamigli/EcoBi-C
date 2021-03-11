/**
 * Adapter CityBikApiAdapter
 *
 * CityBikApiAdapter inicializa un elemento de tipo Retrofit
 * el cual apunta hacia la base url de la API
 * Crea un elemento de tipo CityBikApiService el cual
 * especifica el endpoint a consultar.
 *
 * @author Jair Migliolo
 * @version 2020.1103
 * @since 1.0
 */


package com.venusdev.ecobi_c.data;

import com.venusdev.ecobi_c.data.response.StationsReponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityBikApiAdapter {

    private static CityBikApiService API_SERVICE;

    public static CityBikApiService getApiService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl = "https://api.citybik.es/v2/networks/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(CityBikApiService.class);
        }

        return API_SERVICE;

    }

}
