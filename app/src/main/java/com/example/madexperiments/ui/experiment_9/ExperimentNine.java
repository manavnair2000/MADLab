package com.example.madexperiments.ui.experiment_9;


import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.madexperiments.R;

public class ExperimentNine extends Fragment {

    private ExperimentNineViewModel mViewModel;

    public static ExperimentNine newInstance() {
        return new ExperimentNine();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.experiment_9, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExperimentNineViewModel.class);
        // TODO: Use the ViewModel
    }

}
