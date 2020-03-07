package com.example.madexperiments.ui.experiment_7;

import com.example.madexperiments.AppLocationService;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.example.madexperiments.LocationAddress;
import com.example.madexperiments.AppLocationService;
import com.example.madexperiments.MainActivity;
import com.example.madexperiments.R;

import static androidx.core.content.ContextCompat.getSystemService;


public class ExperimentSeven extends Fragment {
    private static final int PERMISSION_ID = 44;

    private ExperimentSevenViewModel mViewModel;
    private AppLocationService appLocationService;


    public static ExperimentSeven newInstance() {
        return new ExperimentSeven();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.experiment_7, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExperimentSevenViewModel.class);
        appLocationService = new AppLocationService(getActivity());
        appLocationService = new AppLocationService(
                getActivity());
        final TextView tvAddress = getActivity().findViewById(R.id.tvAddress);
        if (!checkPermissions())
            requestPermissions();
        else {
            Button btnGPSShowLocation = getActivity().findViewById(R.id.btnGPSShowLocation);
            btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Location gpsLocation = appLocationService
                            .getLocation(LocationManager.GPS_PROVIDER);
                    if (gpsLocation != null) {
                        double latitude = gpsLocation.getLatitude();
                        double longitude = gpsLocation.getLongitude();
                        String result = "\tLatitude: " + gpsLocation.getLatitude() +
                                "\t\t\n Longitude: " + gpsLocation.getLongitude();
                        tvAddress.setText(result);
                    } else {
                        showSettingsAlert();
                    }
                }
            });
            Button btnShowAddress;
            btnShowAddress = getActivity().findViewById(R.id.btnShowAddress);
            btnShowAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    Location location = appLocationService
                            .getLocation(LocationManager.GPS_PROVIDER);

                    //you can hard-code the lat & long if you have issues with getting it
                    //remove the below if-condition and use the following couple of lines
                    //double latitude = 37.422005;
                    //double longitude = -122.084095

                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        LocationAddress locationAddress = new LocationAddress();
                        locationAddress.getAddressFromLocation(latitude, longitude,
                                getContext(), new GeocoderHandler());
                    } else if(!isLocationEnabled()) {
                        showSettingsAlert();
                    }

                }
            });
        }
    }
    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID
        );
    }
    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
            }
        }
    }

    public void showSettingsAlert() {
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getActivity().startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
    private class GeocoderHandler extends Handler {
        TextView tvAddress = getActivity().findViewById(R.id.tvAddress);
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            tvAddress.setText("\t\t"+locationAddress);
        }
    }

}
