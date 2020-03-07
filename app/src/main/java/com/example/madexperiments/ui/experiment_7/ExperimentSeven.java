package com.example.madexperiments.ui.experiment_7;

import com.example.madexperiments.AppLocationService;
import androidx.lifecycle.ViewModelProviders;

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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.widget.Button;

import com.example.madexperiments.LocationAddress;
import com.example.madexperiments.MainActivity;
import com.example.madexperiments.R;


public class ExperimentSeven extends Fragment {

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
    }

}
