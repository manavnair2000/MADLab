package com.example.madexperiments.ui.experiment_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.madexperiments.R;

public class ExperimentOne extends Fragment {

    private ExperimentOneViewModel experimentOneViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        experimentOneViewModel =
                ViewModelProviders.of(this).get(ExperimentOneViewModel.class);
        View root = inflater.inflate(R.layout.experiment_1, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        experimentOneViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }



}