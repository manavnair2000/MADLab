package com.example.madexperiments.ui.experiment_5;

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

public class ExperimentFive extends Fragment {

    private ExperimentFiveViewModel experimentFiveViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        experimentFiveViewModel =
                ViewModelProviders.of(this).get(ExperimentFiveViewModel.class);
        View root = inflater.inflate(R.layout.experiment_5, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        experimentFiveViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}