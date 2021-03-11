/**
 * Activity MapsActivity
 *
 * Activity principal en la que se inicializa el mapa y
 * se obtienen los datos proporcionados por el adapter CityBikAdapter
 *
 * @author Jair Migliolo
 * @version 2020.1103
 * @since 1.0
 */

package com.venusdev.ecobi_c.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.venusdev.ecobi_c.R;
import com.venusdev.ecobi_c.data.CityBikApiAdapter;
import com.venusdev.ecobi_c.data.response.StationsReponse;
import com.venusdev.ecobi_c.model.DataResponse;
import com.venusdev.ecobi_c.model.Station;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, Callback<StationsReponse> {

    private GoogleMap mMap;
    private ArrayList<Station> stations;
    private ArrayList<String> stationNames = new ArrayList<String>();
    private Marker seachedMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Elementos
        ImageButton refresh = findViewById(R.id.refresh);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stationNames);
        AutoCompleteTextView search = findViewById(R.id.search);

        search.setAdapter(adapter);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStations();
            }
        });
        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
                String option = parent.getItemAtPosition(position).toString();
                Station s = new Station();
                for(Station st:stations){
                    if(st.getName()==option){
                        s=st;
                        break;
                    }
                }
                mMap.clear();
                addMarkers();
                LatLng ubicacion = new LatLng(s.getLatitude(),s.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,17.0f));
                MarkerOptions marker = new MarkerOptions().position(ubicacion);
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_selected));
                mMap.addMarker(marker);
                search.setText("");
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);
        mMap = googleMap;
        getStations();
    }


    @Override
    public void onResponse(Call<StationsReponse> call, Response<StationsReponse> response) {
        if (response.isSuccessful()){
            StationsReponse stations = response.body();
            LatLng ubicacion = new LatLng(stations.getNetwork().getLocation().getLatitude(), stations.getNetwork().getLocation().getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,17.0f));
            this.stations = stations.getNetwork().getStations();
            addMarkers();
        }
    }

    @Override
    public void onFailure(Call<StationsReponse> call, Throwable t) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Station s = new Station();
        for(Station st:this.stations){
            if(st.getLatitude()==marker.getPosition().latitude && st.getLongitude()==marker.getPosition().longitude) {
                s = st;
                break;
            }
        }
        openBottomSheet(s);
        return false;
    }

    public void openBottomSheet(Station s){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(s.getName(),
                s.getExtraData().getAddress(),
                s.getTimestamp(),
                String.valueOf(s.getFree_bikes()),
                s.getLatitude(),
                s.getLongitude());
        bottomSheetDialog.show(getSupportFragmentManager(),"ModalBottomSheet");
    }

    public void getStations(){
        Call<StationsReponse> call = CityBikApiAdapter.getApiService().getStations();
        call.enqueue(this);
    }

    public void addMarkers(){
        stationNames.clear();
        mMap.clear();
        for(Station s:this.stations){
            if(s.getFree_bikes()>0){
                stationNames.add(s.getName());
                LatLng ubicacion = new LatLng(s.getLatitude(), s.getLongitude());
                MarkerOptions marker = new MarkerOptions().position(ubicacion);
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bike));
                mMap.addMarker(marker);
            }
        }
    }

}