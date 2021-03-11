/**
 * DialogFragment BottomSheetDialog
 *
 * Fragment construido para mostrar detalles del marcador seleccionado.
 * Se utiliza un BottomSheetDialogFragment para no cubrir toda la view principal.
 *
 * @author Jair Migliolo
 * @version 2020.1103
 * @since 1.0
 */

package com.venusdev.ecobi_c.ui.main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.venusdev.ecobi_c.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    private String name, addres, updated;
    private String bikesNumber;
    private double lat, lng;

    public BottomSheetDialog(String name, String addres, String updated, String bikesNumber, double lat, double lng) {
        this.name = name;
        this.addres = addres;
        this.updated = updated;
        this.bikesNumber = bikesNumber;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet, container, false);

        TextView t_name, t_address, t_updated, t_bikes;
        Button openWaze;

        t_name = v.findViewById(R.id.bttmInfoStationName);
        t_address = v.findViewById(R.id.bttmInfoStationAddress);
        t_updated = v.findViewById(R.id.bttmInfoStationUpdated);
        t_bikes = v.findViewById(R.id.bttminfoStationBikes);
        openWaze = v.findViewById(R.id.wazeButton);

        t_name.setText(this.name);
        t_address.setText(this.addres);
        t_updated.setText("Actualizado: " + this.updated);
        t_bikes.setText(this.bikesNumber);

        openWaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNavigation();
            }
        });

        return v;
    }

    public void startNavigation(){
        try {
            String mapRequest = "https://waze.com/ul?q=" + lat + "," + lng + "&navigate=yes&zoom=17";
            Uri gmmIntentUri = Uri.parse(mapRequest);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.waze");
            startActivity(mapIntent);

        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
            startActivity(intent);
        }
    }

}
