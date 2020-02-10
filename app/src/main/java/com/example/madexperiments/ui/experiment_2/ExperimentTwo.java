package com.example.madexperiments.ui.experiment_2;

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

public class ExperimentTwo extends Fragment {

    private ExperimentTwoViewModel experimentTwoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        experimentTwoViewModel =
                ViewModelProviders.of(this).get(ExperimentTwoViewModel.class);
        View root = inflater.inflate(R.layout.experiment_2, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        experimentTwoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}